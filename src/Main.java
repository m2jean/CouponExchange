import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

public class Main {

	public static Dimension scr_size = Toolkit.getDefaultToolkit().getScreenSize();
	public static void main(String[] args) {
		
		getVisibleSpace();
		
		Frame mainfr = new Frame("Titile");
		
	}
	
	public static Dimension getVisibleSpace(){
		Toolkit kit = Toolkit.getDefaultToolkit();		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		Insets insets = kit.getScreenInsets(ge.getDefaultScreenDevice().getDefaultConfiguration());
		return new Dimension(scr_size.width - insets.left - insets.right, scr_size.height - insets.top - insets.bottom);
	}

}
