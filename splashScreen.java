//No time to do you

import java.applet.Applet;
import java.applet.*;
import java.awt.*;

public class splashScreen extends Applet
{
	public splashScreen()
	{
		splashMe();
	}
	
	public void splashMe()
	{
		System.out.println("foo");
	}

	public void paint(Graphics page){
		page.drawString("Let's go", 50, 50);
		
	}

}