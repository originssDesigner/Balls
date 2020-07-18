//By 202171103Wang
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
public class BallsCanvas extends Canvas implements Runnable
{
		public ArrayList<Ball> balls;
		public ArrayList<Ball> lost;
		Thread thread;
		int sleeptime=250;
		private Ballbuffer buffer;
		public BallsCanvas(Ballbuffer buffer)
		{
			this.balls=new ArrayList<Ball>();
			this.buffer=buffer;
			thread=new Thread(this);
		}
		public void addBall(Ball b)
		{
			Ball n=new Ball(790,b.y,b.size,b.color,b.v,b.left,b.up);
			this.balls.add(n);
		}
		public void addballs(Color color,int num,int delay,int size,int v)
		{
			for(int i=0,x=40;i<num;i++,x+=40)
			{
				Ball b=new Ball(x,x,size,color,v,true,true);
				this.balls.add(b);
			}
		}
		public void setDelay(int delay)
		{
			this.sleeptime=delay;
		}
		public void paint(Graphics g)
		{
			try
			{
			lost=new ArrayList<Ball>();
			for(int i=0;i<balls.size();i++)
			{
				g.setColor(this.balls.get(i).color);
				this.balls.get(i).x=this.balls.get(i).left?this.balls.get(i).x-this.balls.get(i).v:this.balls.get(i).x+this.balls.get(i).v;  //控制球的水平速度
				if(this.balls.get(i).x<=0)
				{
					this.balls.get(i).left=!this.balls.get(i).left;
					this.balls.get(i).x=0;
				}
				if(balls.get(i).x>=this.getWidth())   //接收者的位置
				{
				    Ball n=new Ball(balls.get(i));
					 this.lost.add(n);
					 new send(this.buffer,this.lost).start();
					    this.balls.remove(i);
				}	
				this.balls.get(i).y=this.balls.get(i).up?this.balls.get(i).y-this.balls.get(i).v:this.balls.get(i).y+this.balls.get(i).v;  //控制球的垂直速度
				if(this.balls.get(i).y<=0)
				{
					this.balls.get(i).up=!this.balls.get(i).up;
					this.balls.get(i).y=0;
				}
				if(this.balls.get(i).y>=this.getHeight())
				{
					this.balls.get(i).up=!this.balls.get(i).up;
					this.balls.get(i).y=this.getHeight();
				}
				g.fillOval(this.balls.get(i).x,this.balls.get(i).y, this.balls.get(i).size,this.balls.get(i).size);      //控制球的大小和方向
			}
			}
			catch(IndexOutOfBoundsException e) {}
		}
		public void run()                                   //线程
		{
			while(true)
				try
			{
					repaint();
					Thread.sleep(sleeptime);
			}
			catch(InterruptedException ex)
			{
				break;
			}
		}
	}
