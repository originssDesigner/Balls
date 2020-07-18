//By 202171103Wang
import java.awt.Color;
public class Ball implements java.io.Serializable
{
	public int x,y;
	public int size;
	public Color color;
	public int v;
	public boolean left,up=true;
	public Ball(int x,int y,int size,Color color,int v,boolean left,boolean up)
	{
		this.x=x;
		this.y=y;
		this.size=size;
		this.color=color;
		this.v=v;
		this.left=left;
		this.up=up;
	}
	public Ball(Ball b)
	{
		this.x=b.x;
		this.y=b.y;
		this.size=b.size;
		this.color=b.color;
		this.v=b.v;
		left=up=true;
	}
}
