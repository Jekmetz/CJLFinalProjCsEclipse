import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VideoFrame {
	//Declare all of the stuff that the class needs
	private JFrame frame;
	private JLabel label;
	private Timer timer = new Timer();

	public VideoFrame(String name) {
		/*
		 *Constructor that sets the frame name that initializes the JFrame
		 */
		frame = new JFrame( name );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		label = new JLabel();
		frame.setContentPane( label );
	}

	public void display() {
		/*
		 *Display the JFrame
		 */
		frame.pack();
		frame.setMinimumSize( frame.getPreferredSize() );
		frame.setVisible( true );
	}

	public void repeatedImages( Images imgs , long framerate ) {
		/*
		 *See what this stuff does below
		 */
		TimerTask task = new TimerTask() {	//This is the object that the Timer depends on.
			int index = 0;			//Specifier for the frame to display
			public void run() {		//The method that defines what the timer will do
				index++;
				ImageIcon icon = new ImageIcon( imgs.getImage( index%imgs.getLength() ));	//Make the icon the next frame from 0-however many frames there are
				label = new JLabel(icon);
				frame.setContentPane(label);
				frame.pack();
			}
		};
		
		timer.schedule(task,0,1000/framerate);
	}

}

