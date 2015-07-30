//Jordan did this file 

//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.*;
    
    
    public class Block  {
    
    	char character;                                         //variable of type character, called character
    	Block previousEntry;                             //make a variable previousCharacter, that is type character (this class)
    	int x, y, length, width, status;
    	DrawingClass block;
    	

    	public Block(int x, int y, int length, int width, int status)

    	{
    		
    		this.x = x;
    		this.y = y;
    		this.length = length;
    		this.width = width;
    		this.status = status;
    		
    	}
    
    	
    	public void setx (int x)
    	{
    		this.x = x;
    		
    	}
    	
    	public void sety (int y)
    	{
    		this.y = y;
    		
    	}
    	
    	public void setlength (int length)
    	{
    		this.length = length;
    		
    	}
    	
    	public void setwidth (int width)
    	{
    		this.width = width;
    		
    	}
    	
    	public int getx ()
    	{
    		
    		return x;
    	}
    	
    	public int gety ()
    	{
    		
    		return y;
    	}
    	
    	public int getlength ()
    	{
    		
    		return length;
    	}
    	
    	public int getwidth ()
    	{
    		
    		return width;
    	}
    	
    	public int getStatus(){
    		
    		return status;
    	}
   
    
    }
    