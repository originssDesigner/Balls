//By 202171103Wang
import java.util.ArrayList;
import javax.swing.*;
public class receiveJFrame extends JFrame   implements Runnable  //接收球的界面
{
	public receiveCanvas canvas;
	private Ballbuffer buffer;
	Thread thread,thread2;                           //thread:画布线程   
	public receiveJFrame(Ballbuffer buffer,Ballbuffer buffer2)     
	{
		super("弹弹球");
		this.setBounds(1100,200,300,300);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.buffer=buffer;
		this.canvas=new receiveCanvas(buffer2);  
		thread2=new Thread(this);
		thread=new Thread(this.canvas);
		
		thread.start();
		thread2.start();
		this.getContentPane().add(this.canvas);
		this.setVisible(true);
	}
	public void run()
	{
		while(true)
		{
		   for(int i=0;i<2;i++)
			 {
			    this.canvas.addBalls(buffer.get());
			 }
		   thread=new Thread(this.canvas);
		   thread.start();
		   try{Thread.sleep(100);}
		   catch(InterruptedException e)
		   {
				  break;
		   }  
		}
	}
	
}

