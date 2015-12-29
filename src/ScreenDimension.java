import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

public class ScreenDimension {

	private Toolkit kit;
	private Dimension scr_size;
	private int dpi;
	private GraphicsDevice device;
	
	public ScreenDimension(){
		this(Toolkit.getDefaultToolkit(),GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
	}
	
	public ScreenDimension(Toolkit kit, GraphicsDevice device){
		this.kit = kit;
		scr_size = kit.getScreenSize();
		dpi = kit.getScreenResolution();
		this.device = device;
	}
	
	public Dimension getVisibleSpace(){
		Insets insets = kit.getScreenInsets(device.getDefaultConfiguration());
		return new Dimension(scr_size.width - insets.left - insets.right, scr_size.height - insets.top - insets.bottom);
	}
}
