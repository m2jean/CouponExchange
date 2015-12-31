import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
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
		Panel pnl = createFileChooser();
		mainfr.add(pnl);
		
		mainfr.setVisible(true);
	}
	
	private static Panel createFileChooser(){

		Panel pnl = new Panel();
		TextField txtfld = new TextField();
		txtfld.setColumns(40);
		pnl.add(txtfld);
		Button btn_brs = new Button("Browse");
		pnl.add(btn_brs);
		Button btn_chs = new Button("Choose");
		pnl.add(btn_chs);
		Canvas cvs = new Canvas();
		cvs.setPreferredSize(new Dimension(600,200));
		
		pnl.add(cvs);
		
		btn_brs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Images(jpeg,png,gif,bmp,webp)","jpeg", "jpg", "png", "gif", "bmp", "webp");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(pnl);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					txtfld.setText(fc.getSelectedFile().getAbsolutePath());
				}

			}
		});

		btn_chs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Image img;
				try {
					img = ImageIO.read(new File(txtfld.getText().trim()));
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				int cvs_width = cvs.getWidth();
				int cvs_height = cvs.getHeight();
				Graphics g = cvs.getGraphics();
				//g.drawRect(0, 0, cvs_width, cvs_height);
				g.drawImage(stretchInto(img, cvs_width, cvs_height), 0, 0, null);
			}
		});
		
		return pnl;
	}
	
	public static Image stretchInto(Image img, int width, int height){
		double widthp = img.getWidth(null)/(double)width;
		double heightp = img.getHeight(null)/(double)height;
		if(widthp > heightp){
			return img.getScaledInstance(width, (int) (height*heightp/widthp), Image.SCALE_SMOOTH);
		}
		else{
			return img.getScaledInstance((int) (width*widthp/heightp), height, Image.SCALE_SMOOTH);
		}
	}

}
