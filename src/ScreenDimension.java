import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;

public class ScreenDimension {

	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static Dimension scr_size = kit.getScreenSize();
	private static int dpi = kit.getScreenResolution();
	private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	public static void setDefault(Toolkit kit, GraphicsDevice device){
		ScreenDimension.kit = kit;
		scr_size = kit.getScreenSize();
		dpi = kit.getScreenResolution();
		ScreenDimension.device = device;
	}
	
	public static Dimension getVisibleSpace(){
		Insets insets = kit.getScreenInsets(device.getDefaultConfiguration());
		return new Dimension(scr_size.width - insets.left - insets.right, scr_size.height - insets.top - insets.bottom);
	}
	
	public static Dimension getDimension(int winch, int hinch){
		return new Dimension(winch*dpi, hinch*dpi);
	}
	
	/**
	 * This method should be call after the window is visible and has its size defined. 
	 */
	public static void centerWindow(Window win){
		Dimension scr = getVisibleSpace();
		Dimension size = win.getSize();
		win.setLocation((scr.width-size.width)/2, (scr.height-size.height)/2);
	}
}
