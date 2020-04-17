/* The Game Of Life	Author: Glenn Wilkinson
 * 			Student:603w1065
 *			email	narcos@rucus.net
 *
 * loadLife.java
 * Loads a predefined pattern from a file
 */
 
import java.io.*;
import java.util.StringTokenizer;

public class loadLife
{
	private int[][] grid;
	private int rows,cols;
	private int value;
	private String fileName;
	
	/*The constructor reads the file that passed to it*/
	public loadLife(String fName,int nRows,int nCols) throws IOException
	{
		StringTokenizer tokenizer;
		String line,name;
		int foo;
		
		this.fileName=fName;
		rows=nRows;
		cols=nCols;
		
		grid = new int[rows][cols];
				
		FileReader fr = new FileReader (fileName);
         	BufferedReader inFile = new BufferedReader (fr);
		
		line = inFile.readLine();
		tokenizer = new StringTokenizer (line);
		
		for(int x=0; x< rows-1; x++)
		{
			for(int y=0; y< cols-1; y++)
			{ 
				grid[y][x] =Integer.parseInt(tokenizer.nextToken()); //Check later, something got inverted, works.
			}
			line = inFile.readLine();
			tokenizer = new StringTokenizer (line);
		
		}
	
		//printLoaded(); /* Let's see what we loaded*/
		
			
		

	}
	
	public int[][] getGrid()
	{
		return(grid);
	}
	
	/*Just some testing, prints the loaded file to the terminal, so we know what's been loaded*/
	public void printLoaded()
	{
		for(int x=0; x< rows; x++)
		{
			for(int y=0; y< cols; y++){
				System.out.print(grid[x][y]);
				}
			System.out.println();
		} 		
	}
	
	
}