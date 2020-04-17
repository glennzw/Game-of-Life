/* The Game Of Life	Author: Glenn Wilkinson
 * 			Student:603w1065
 *			email	narcos@rucus.net
 *
 * saveLife.java
 * I was going to use this to save the state of the grid
 * to a file, but applets don't have permission to do that.
 */
import java.io.*;
import java.util.StringTokenizer;

public class saveLife
{
	private int[][] gameState;
	private String nameOfFile;
	private int rows,cols;
		
	public saveLife(int[][] state, String theName, int nRows, int nCols) throws IOException
	{	
		this.gameState=state;
		this.nameOfFile=theName;
		rows=nRows;
		cols=nCols;
			
		FileWriter fw = new FileWriter(nameOfFile);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter outFile = new PrintWriter(bw);		
		
		for(int x=0; x< rows; x++){
			for(int y=0; y< cols; y++){
			 outFile.print(gameState[x][y]+" ");
			}
		outFile.println();
		}
		outFile.close();		
	}

}