//By 202171103Wang
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
class BallsCanvas extends Canvas implements Runnable
{
		private Ball balls[];
		Thread thread;
		int sleeptime=100;
		private static class Ball
		{
			int x,y;
			int size;
			int v;
			Color color;
			boolean up,left;
			Ball(int x,int y,Color color,int size,int v)
			{
				this.x=x;
				this.y=y;
				this.size=size;
				this.v=v;
				this.color=color;
			}
		}
		public BallsCanvas(Color color)
		{
			this.balls=new Ball[1];
			balls[0]=new Ball(40,40,color,20,10);
			thread=new Thread(this);
		}
		public void addballs(Color color,int num,int delay,int size,int v)
		{
			Ball olds[]=this.balls;
			int len=olds.length+num;
			this.balls=new Ball[len];
			for(int i=0;i<olds.length;i++)
				this.balls[i]=new Ball(olds[i].x,olds[i].y,olds[i].color,olds[i].size,olds[i].v);
			for(int i=olds.length,x=40;i<len;i++,x+=40)
				this.balls[i]=new Ball(x,x,color,size,v);
		}
		public void setDelay(int delay)
		{
			this.sleeptime=delay;
		}
		public void paint(Graphics g)
		{
			for(int i=0;i<balls.length;i++)
			{
				g.setColor(balls[i].color);
				balls[i].x=balls[i].left?balls[i].x-balls[i].v:balls[i].x+balls[i].v;
				if(balls[i].x<=0)
				{
					balls[i].left=!balls[i].left;
					balls[i].x=0;
				}
				if(balls[i].x>=this.getWidth())
				{
				balls[i].left=!balls[i].left;
				balls[i].x=this.getWidth();
				}
				balls[i].y=balls[i].up?balls[i].y-balls[i].v:balls[i].y+balls[i].v;
				if(balls[i].y<=0)
				{
					balls[i].up=!balls[i].up;
					balls[i].y=0;
				}
				if(balls[i].y>=this.getHeight())
				{
				 balls[i].up=!balls[i].up;
				 balls[i].y=this.getHeight();
				}
				g.fillOval(balls[i].x, balls[i].y, balls[i].size,balls[i].size);
			}
		}
		public void run()
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