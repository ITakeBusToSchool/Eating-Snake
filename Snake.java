package 貪吃蛇;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Snake 
{	//composed by many Node
	
	private Node head=null;
	private Node tail=null;
	private int size=0;
	
	private Node n=new Node(20,30,Direction.L);
	private Yard y;
	
	public Snake(Yard y)
	{
		this.y=y;
		head=n;
		tail=n;
		size=1;
	}//snake初始化
	public void addToTail()
	{
		Node node=null;
		switch(tail.dir)
		{	
			case L:
				node=new Node(tail.row,tail.col+1,tail.dir);
				break;
			case U:
				node=new Node(tail.row+1,tail.col,tail.dir);
				break;
			case R:
				node=new Node(tail.row,tail.col-1,tail.dir);
				break;
			case D:
				node=new Node(tail.row-1,tail.col,tail.dir);
				break;
		}
		node.next=tail;
		node.prev=null;
		tail=node;		
		size++;
	}
	public void addTohead()
	{
		Node node=null;
		switch(tail.dir)
		{	
			case L:
				node=new Node(tail.row,tail.col-1,tail.dir);
				break;
			case U:
				node=new Node(tail.row-1,tail.col,tail.dir);
				break;
			case R:
				node=new Node(tail.row,tail.col+1,tail.dir);
				break;
			case D:
				node=new Node(tail.row+1,tail.col,tail.dir);
				break;
		}
		node.next=head;
		node.prev=null;
		head=node;
		size++;
	}
	
	private class Node
	{
		int w=Yard.BLOCK_SIZE;//rows
		int h=Yard.BLOCK_SIZE;//height
		int row,col;//where the snake placed
		Direction dir=Direction.L;//the direction of the snake
		Node next=null;
		Node prev=null;
		private Node(int row, int col,Direction dir)
		{
			//super();//you really need to get fimiliar with this
			this.row=row;
			this.col=col;			
			this.dir=dir;
			
		}
		void draw(Graphics g)
		{
			Color c=g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Yard.BLOCK_SIZE * col,Yard.BLOCK_SIZE * row,w,h);
			//a Node finished
			
			g.setColor(c);
		}
		
	}
	
	public void eat(egg e)
	{
		if(this.getRect().intersects(e.getRect()))
		{
		 e.reAppear();
		 this.addTohead();
		 y.setScore(y.getScore()+5);
		}
		
	}
	private Rectangle getRect()
	{
		return new Rectangle(Yard.BLOCK_SIZE * head.col,Yard.BLOCK_SIZE * head.row,head.w,head.h);
	}
	
	public void draw(Graphics g)
	{	if (size<=0)
		return;
		move();
		for(Node n=head;n!=null;n=n.next)
			n.draw(g);
		
	}
	private void move()
	{
		addTohead();
		deleteFormTail();
		checkDead();
	};
	private void checkDead() {
		// TODO Auto-generated method stub
		if(head.row<2|| head.col<0||head.row>Yard.ROWS||head.col>Yard.COLUMN)
		{
			y.stop();	
			
		}
		for(Node a=head.next;n!=null;n=n.next)
		{
			if(head.row==n.row&&head.col==n.col)
			{
				y.stop();
			}
		}
	}
	private void deleteFormTail() 
	{	
		if (size==0) return;
		tail=tail.prev;
		tail.next=null;
		
		
	}
	public void KeyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_LEFT:
					if(head.dir!=Direction.R)
					head.dir=Direction.L;
			break;
			case KeyEvent.VK_RIGHT:
				if(head.dir!=Direction.L)
					head.dir=Direction.R;
					break;
			case KeyEvent.VK_UP:
				if(head.dir!=Direction.D)
					head.dir=Direction.U;
					break;
			case KeyEvent.VK_DOWN:
				if(head.dir!=Direction.U)
					head.dir=Direction.D;
					break;
		}
	}
}
