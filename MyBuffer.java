//Woulda gone something like this, next time.

import java.awt.*;

public class MyBuffer extends DoubleBuffer{



	//	class variables
	private int posX;

	public MyBuffer(){
		super();

		posX=0;
	}



	//so we don't touch update() and paint() anymore
	//we use paintbuffer() instead
	// smart hey ;-)

	public void paintBuffer(Graphics g){
		/// g is the offscreen graphics
		g.drawString("Double Buffer!",posX,20);
	}
}