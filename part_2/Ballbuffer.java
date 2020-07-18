//By 202171103Wang
import java.awt.Color;
import java.util.ArrayList;
public class Ballbuffer  //»º³åÇø
{
	private Ball ball;
	boolean isEmpty=true;
	public Ballbuffer()
	{}
	public synchronized void put( Ball ball)
	{
		while(!isEmpty)
		try{this.wait();} catch(InterruptedException e) {}
		this.ball=new Ball(ball.x,ball.y,ball.size,ball.color,ball.v,ball.left,ball.up);
		isEmpty=false;
		this.notify();
	}
	public synchronized Ball get()
	{
		  while(isEmpty)
		   try {this.wait();}catch(InterruptedException e) {}
		 isEmpty=true;
		 this.notifyAll();
		 return this.ball;
		
	}
}
class send extends Thread
{
	private Ballbuffer buffer;
	private ArrayList<Ball> balls;
	public send(Ballbuffer buffer,ArrayList<Ball> balls)
	{
		this.buffer=buffer;
		this.balls=new ArrayList<Ball>(balls);
	}
	public void run()
	{
		for(int i=0;i<this.balls.size();i++)
		   {
			  buffer.put(balls.get(i));
			  try
				{
					Thread.sleep(1);
				}catch(InterruptedException e) {}
		   }
	}
}
