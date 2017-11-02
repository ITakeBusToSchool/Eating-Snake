import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class egg 
{
	int row, col;
	int w=Yard.BLOCK_SIZE;
	int h=Yard.BLOCK_SIZE;
	private static Random r=new Random(); 
	private Color color=Color.green;
public egg(int row,int col)
{
	this.row=row;
	this.col=col;
}

public egg()
{
	this(r.nextInt(Yard.ROWS-2)+2,r.nextInt(Yard.COLUMN));
}

public void reAppear()
{
	this.row=r.nextInt(Yard.ROWS-2)+2;
	this.col=r.nextInt(Yard.COLUMN);
}

Rectangle getRect()
{
	return new Rectangle(Yard.BLOCK_SIZE *col,Yard.BLOCK_SIZE * row,w,h);
}
public void draw(Graphics g)
{
	Color c=g.getColor();
	g.setColor(color);
	g.fillRect(Yard.BLOCK_SIZE*col,Yard.BLOCK_SIZE*col,w,h);
	//a Node finished
	
	g.setColor(c);
	if(color==Color.GREEN)
		color=Color.RED;
	else
		color=Color.GREEN;
	
}

public int getCol()
{
	return col;
}
public void setCol(int col)
{
	this.col=col;
}
public int getRow()
{
	return row;
}

}
