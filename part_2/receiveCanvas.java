//By 202171103Wang
import java.awt.*;
import java.util.ArrayList;
public class receiveCanvas extends Canvas implements Runnable
{
	public ArrayList<Ball> balls;               //ÇòµÄ´æ´¢½á¹¹
	public ArrayList<Ball> lost;
	Thread thread;
	private Ballbuffer buffer;
	public int x,y;
	public receiveCanvas(Ballbuffer buffer)
	{
		this.balls=new ArrayList<Ball>();
		this.buffer=buffer;
		thread=new Thread(this);
	}
	public void addBalls(Ball b)
	{
		Ball n=new Ball(1,b.y,b.size,b.color,b.v,b.left,b.up);
		this.balls.add(n);
	}
	public void paint(Graphics g)
	{
		lost=new ArrayList<Ball>();
		try
		{
		for(int i=0;i<this.balls.size();i++)
		{
			g.setColor(balls.get(i).color);
			int v=balls.get(i).v;
			balls.get(i).x=balls.get(i).left?balls.get(i).x+v:balls.get(i).x-v;
			balls.get(i).y=balls.get(i).up?balls.get(i).y+v:balls.get(i).y-v;
			if(balls.get(i).x<=0||balls.get(i).x>=this.getWidth())
			{
				if(balls.get(i).x<=0)
				{
					Ball n=new Ball(balls.get(i));
				    this.lost.add(n);
				    new send(this.buffer,this.lost).start();
				    this.balls.remove(i);
				}
				else
				balls.get(i).left=!balls.get(i).left;
			}
			if(balls.get(i).y<=0||balls.get(i).y>=this.getHeight())
		    {
				balls.get(i).up=!balls.get(i).up;
			}
			g.fillOval(balls.get(i).x, balls.get(i).y, balls.get(i).size, balls.get(i).size);
		}
		}catch(IndexOutOfBoundsException e) {}
	}
	public void run() 
	{
		while(true)
		try
	   {
		   repaint();
		   Thread.sleep(500);
		}catch(InterruptedException e)
		{
			break;
		}
	}
}
