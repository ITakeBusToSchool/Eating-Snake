import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.CharBuffer;

public class Yard extends Frame 
{	

	private boolean flag=true;
	
	
	public static final int ROWS=30;
	public static final int COLUMN=30;	
	public static final int BLOCK_SIZE=15;
	
	public int  score=0;
	Snake s=new Snake(this);
	
	egg e=new egg();
	Image offScreenImage=null;//don't know what it is
	
	//
	public void launch()
	{
		this.setLocation(300,300);
		this.setSize(COLUMN*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() 
		{
			
			public void windowClosing(WindowEvent e)
			{	
			System.exit(0);
			}
		
			}	
		) ;
				
			
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();;
	}
	
	public void paint(Graphics g)
	{
		Color c=g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0,0,COLUMN*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		for(int i=1;i<ROWS;i++)
		{
			g.drawLine(0,BLOCK_SIZE*i,COLUMN*BLOCK_SIZE,BLOCK_SIZE*i);
		}
		for(int i=1;i<COLUMN;i++)
		{
			g.drawLine(BLOCK_SIZE*i,0,BLOCK_SIZE*i,BLOCK_SIZE*ROWS);
		}
		
		g.setColor(Color.YELLOW);
		if(flag==false)
		{	
			//g.setFont(new Font"微軟正黑",Font.BOLD | Font.HANGING_BASELINE, 50);
			g.drawString("Game Over", 10, 20);
		}
		
		g.drawString("score:"+score,10,60);
		g.setColor(c);
		s.eat(e);
		e.draw(g);
		s.draw(g);
		
	}
	public static void main(String[] args) 
	{
		new Yard().launch();
		

	}
	public void stop()
	{
		flag= false;
	}
	
	@Override
	public void update(Graphics g)
	{
		if(offScreenImage==null)
		{
			offScreenImage=this.createImage(COLUMN*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		}
		Graphics goff=offScreenImage.getGraphics();
		paint(goff);
		g.drawImage(offScreenImage,0,0,null);
		
	}
	
	private  class PaintThread implements Runnable
	{
		public void run()
		{
			while(flag)
			{
				repaint();
				try 
				{
					Thread.sleep(100);
					
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
		}

	
	}

	private class KetMonitor extends KeyAdapter
	{
		public void KeyPressed(KeyEvent e)
		{
			s.KeyPressed(e);
		}
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	public void setScore(int scroe) 
	{
		// TODO Auto-generated method stub
		this.score=score;
	}
}
