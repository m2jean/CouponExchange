import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
				
		Frame mainfr = new Frame("CouponExchangeSystem");
		mainfr.setMinimumSize(new Dimension(800,600));
		mainfr.setMaximumSize(ScreenDimension.getVisibleSpace());
		mainfr.setSize(ScreenDimension.getDimension(12, 8));
		mainfr.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				int msg = JOptionPane.showConfirmDialog(mainfr, "Are you sure you want to quit the system?", "Exiting",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(msg == JOptionPane.OK_OPTION){
					mainfr.dispose();
					System.exit(0);
				}
			}
			@Override
			public void windowOpened(WindowEvent we){
				ScreenDimension.centerWindow(mainfr);
			}
		});
		
		
		mainfr.setVisible(true);
	}
	
	

}
