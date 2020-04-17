/* The Game Of Life	Author: Glenn Wilkinson
 * 			Student:603w1065
 *			email	narcos@rucus.net
 *
 * An implementation of Conaway's rules of Life (very much an alpha release :P )
 *
*/
import java.applet.Applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.io.*;

public class myLife extends Applet implements MouseListener,MouseMotionListener
{
public int x,y,xPoint,yPoint;
public int numOfCycles=0;
public int sizeOfCell = 10;		//Width and height of the cell.jpg file, if you wish to change the image, it must
public boolean lineMode=true;		//be reflected here, all the other runtime variables will be automatically updated
					
   private Timer timer;			//Timer to run simulation (cells are redrawn at each cycle)
   private final int DELAY=40;		//delay between redraws
   					
   private int ROWS,COLS;
   private final int APPLET_WIDTH = 500;
   private final int APPLET_HEIGHT = 500;
   public int[][] gridTemp;			//gridTemp is used to setup the initial game state (i.e draw the cells)
   public int[][] grid;				//grid is used whilst the simulation is running
   						
   Font myFont = new Font("serif", Font.ITALIC, 10);
   Font myOtherFont = new Font("serif", Font.PLAIN, 12);


   public void init()
   {
      ROWS=(int)APPLET_WIDTH/sizeOfCell;				//We calculate the rows and columns depending
      COLS=(int)APPLET_HEIGHT/sizeOfCell;				//on the size of the applet and the size of the
      gridTemp = new int[ROWS][COLS];					//image, allowing easy changing of either.
      grid = new int[ROWS][COLS];
      
      timer = new Timer (DELAY, new CellsActionListener());		//Let's create a timer
      //splashScreen letsGo = new splashScreen();
      
      addKeyListener(new myKeyListener());
      addMouseListener (this);
      addMouseMotionListener (this);
      setBackground (Color.black);
      setSize (APPLET_WIDTH, APPLET_HEIGHT);
      
   }


   public void paint(Graphics page)
   {
   	int numOfLiveCells=0;
   	Image theCell = getImage(getCodeBase(), "cell.jpg");
      	
   	if(!timer.isRunning()){				//Draws the x and y coords next to the targetting lines
   		page.setFont(myFont);
   		page.setColor(Color.gray);
   		page.drawString("x: "+x,x+5,10);   
   		page.drawString("y: "+y,5,y-5);
   	}
      
   	page.drawLine(x,0,x,APPLET_HEIGHT);	//Draws the targetting lines	
   	page.drawLine(0,y,APPLET_WIDTH,y);   	
   	
   	//page.drawRect(0,0,APPLET_WIDTH,APPLET_HEIGHT);	//Border
   	
   	for(int x=0; x<ROWS; x++)		//Checks if a cell is alive via the matrix
	 {					//i.e 1 = alive. If it is alive, it gets 
		for(int y=0; y<COLS; y++)	//drawn
		 if(gridTemp[x][y]==1)
		  { 
		   	page.drawImage(theCell,x*sizeOfCell,y*sizeOfCell,this);
		   	numOfLiveCells++;
		  }
	}
	
	/*Let's print some stats*/
	page.setColor(Color.red);
	page.setFont(myOtherFont);
	page.drawString("Living Cells: "+numOfLiveCells,5,APPLET_HEIGHT-25);
	page.drawString("Cycles: "+numOfCycles,5,APPLET_HEIGHT-10);	
}

/*Let's have a look at keyboard input*/
private class myKeyListener implements KeyListener
{
	AudioClip alive = getAudioClip(getCodeBase(), "itsAlive.wav");
	
	public void keyTyped(KeyEvent event2)
	{
			
		switch (event2.getKeyChar())
			{
				case 's':
				if(timer.isRunning()) timer.stop(); 
					else{ timer.start(); alive.play();}
				repaint(); //in order to repaint the targetting lines
				break;
				
				case 'e':						//d for delete mode
				if(lineMode) lineMode=false; else lineMode=true;
				break;
				
				case 'c': 						//Clear all cells
				for(int x=0; x<ROWS; x++)
					for(int y=0; y<COLS; y++)
					gridTemp[x][y]=0;
				repaint();
					
				break;
				
				case 'f':		//Let's save the state to a text file
					/*try{
						saveLife myWay = new saveLife(test, "foobar.txt");
					}
					catch(IOException foobar){
					 System.out.println(foobar);
					}*/
				/* I realised after several hours of getting secutriy and access
				 * denied errors, that an applet can't have write access to your data
				 * or it could just chow your HDD. Oops.
				 * You can see what I wanted to do in "testSaveLife.java"
				 */
				break;
				
				case 'r':	//Create a random grid
					for(int x=0; x<ROWS; x++)
						for(int y=0; y<COLS; y++){
						gridTemp[x][y]=(int)Math.round(Math.random());
						}
				break;
				/*Let's load some predefined patterns:*/
				
				case '1': loadGrid("slider.lfe"); break;	//Classical Slider
				case '2': loadGrid("tenrow.lfe"); break;	//Ten in a row infinite
				case '3': loadGrid("exploder.lfe"); break;	//Exploder, 'nuff said
				case '4': loadGrid("smallexploder.lfe"); break; //small exploder
				case '5': loadGrid("fish.lfe"); break;		//a fish!
				case '6': loadGrid("fountain.lfe"); break;	//a fountain, for the fish?
				case '7': loadGrid("justaline.lfe"); break;	//Just a line, but gives nice output
				case '8': loadGrid("greeting.lfe"); break;	//Hello world?
				case '9': loadGrid("java.lfe"); break;		//ode to Java, and me ;-)
										
				default:
				break;
			}
	}
	
	/*Not used*/
	public void keyPressed(KeyEvent event2){}
	public void keyReleased(KeyEvent event2){}
}   

/*And now for the mouse*/

public void mouseMoved (MouseEvent event2) 
 {
	x=event2.getX(); 
	y=event2.getY();
	repaint();
}		
public void mouseDragged (MouseEvent event2)
	{
		
		x=event2.getX(); 	//for the targetting lines
		y=event2.getY();
		
		xPoint=event2.getX();
		yPoint=event2.getY();
		
		xPoint=xPoint/sizeOfCell;
		yPoint=yPoint/sizeOfCell;
								
		if(lineMode) gridTemp[xPoint][yPoint]=1;	//Basically, if we're not in delete mode reverse the status of a cell
			else gridTemp[xPoint][yPoint]=0;
		
		repaint();
	}
 
public void mouseClicked (MouseEvent event2)
	{
		AudioClip blop = getAudioClip(getCodeBase(), "blop.wav");	
		blop.play();
		
		xPoint=event2.getX();
		yPoint=event2.getY();
		
		xPoint=xPoint/sizeOfCell;
		yPoint=yPoint/sizeOfCell;
		
		if (gridTemp[xPoint][yPoint]==0) gridTemp[xPoint][yPoint]=1;
			else
			gridTemp[xPoint][yPoint]=0;
		repaint();
		
		
	}

	/*Not used*/
   	public void mouseReleased (MouseEvent event) {}
   	public void mouseEntered (MouseEvent event) {}
   	public void mouseExited (MouseEvent event) {}
   	public void mousePressed (MouseEvent event){}

   
/* ---------------------------------- */   
/*|The following methods run the game|*/
/* ---------------------------------- */

   
 /*this method calculates how many neighbours a living cell has, the coords of the cell are passed*/
	public int checkNeighbours(int x, int y,int[][] theGrid)
	{
		/*The codes a bit sloppy but it made it easier to see what was going on*/
		
		int nay=0;  //Number of neighbours a cell has
	
		if(x<(COLS-1)  && (theGrid[x+1][y])==1) nay++;		//check horizontal
		if(x>0) if((theGrid[x-1][y])==1) nay++;
	
		if(y<(COLS-1) && (theGrid[x][y+1])==1) nay++;		//check vertical
		if(y>0)	if((theGrid[x][y-1])==1) nay++;
	
		if(x>0) if(y>0 && (theGrid[x-1][y-1])==1) nay++;	//check diagonals
		if(x<(ROWS-1)) if(y>0 && (theGrid[x+1][y-1])==1) nay++;
		if(x<(ROWS-1)) if(y<(COLS-1) && (theGrid[x+1][y+1])==1) nay++;
		if(x>0) if(y<(COLS-1) && (theGrid[x-1][y+1])==1) nay++;
	
		return nay;
	
	}  
   
   
   /*This method determines if a cell is alive or dead, based on some simple rules*/
	public static int rules(int nbrs,int status)
	 {
		if(status==0 && nbrs==3) return 1;	//cell dead, but has 3 neighbours then comes back to life
		if(status==1)                           //if cell is alive:
		 {
		  if(nbrs<2) return 0;			//death from exposure
		  if(nbrs>3) return 0;			//death from overcrowding
		 }
	 return status;					//else return original
	}	
	

	/*This listener event runs the timer, the cells are redrawn at each tick, then the matrices are swapped*/
	private class CellsActionListener implements ActionListener
   	{
   		private int nbrs,status,fstatus;
   		
   		public void actionPerformed (ActionEvent event)
      		{
      			numOfCycles++;
      			for(int x=0; x<ROWS; x++)
			    for(int y=0; y<COLS; y++)
			     {
				nbrs=checkNeighbours(x,y,gridTemp);	 //check how many neighbours this cell has
				status=gridTemp[x][y];			//either 0 or 1, ie dead or alive
				fstatus=rules(nbrs,status);
				grid[x][y]=fstatus;
			     }
			  
			  
			  
			//printGrid(grid);	//This was for debugging output, i.e the 1s and 0s to the terminal screen
      			repaint();
      			
      			for(int t=0;t<ROWS;t++)	//swap data, i.e grid = grid2
				for(int s=0;s<COLS;s++)
				 gridTemp[s][t]=grid[s][t];
	  			
      		}
   	}
   	
   	/*This method loads some of my predefined grids, as called from the switch in the keylistener*/
   	public void loadGrid(String filename)
   	{
		timer.stop();	//just in case it's running
						
		try{
			loadLife test = new loadLife(filename,ROWS,COLS);
			gridTemp=test.getGrid();
		}
		catch(IOException foobar){
			System.out.println(foobar);
		}
		repaint();				   		
	}

	
	/*Debugging purposes, prints the grid to the terminal*/
	public void printGrid(int[][] s)
	{			
		for(int x=0; x<ROWS; x++)
		 {
		 	System.out.println("");	
		 		for(int y=0; y<COLS; y++)
		   		System.out.print(s[x][y]);
		 }
		
	
	}
	   
}
