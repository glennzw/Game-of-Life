//This is how saveLife would have worked, well it'll work in an application, just not an applet.
//I used it to create some of the predefined layouts tho

import java.io.*;

public class testSaveLife
{
		
	public static void main (String[] args) throws Exception
	{
		test();
	}
	
	public static void test() throws Exception
	{
		int rows=50;
		int cols=50;
		int test[][] = new int[rows][cols];		
		
		test[1][0]=1;		//Let's feed some data into
		test[2][1]=1;		//the matrix for test purposes
		test[2][2]=1;
		test[1][2]=1;
		test[0][2]=1;
		test[0][3]=0;
		
		saveLife myWay = new saveLife(test, "foobar.lfe",rows,cols);
	}
}