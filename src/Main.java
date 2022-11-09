import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main implements ActionListener{
	JFrame jf;
	JLabel label;
	JButton button;
	BufferedImage overlay;
	BufferedImage image1;
	public Main () {
		jf=new JFrame("Frame Creator");
		jf.setLayout(null);
		jf.setBounds(600,200,600,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(new Color(57, 62, 70));
		
		label=new JLabel("Wlcome");
		label.setBounds(10,10,200,50);	
		label.setForeground(Color.green);
		label.setVisible(true);
		jf.add(label);
		
		button=new JButton("Choose Image");
		button.setBounds(190,400,200,50);
		button.setVisible(true);
		button.addActionListener(this);
		jf.add(button);
		

		
		jf.setVisible(true);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new Main();
	}






	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource()==button) {
	        if (e.getSource() == button) {
	            JFileChooser fileChooser = new JFileChooser();
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
	            fileChooser.addChoosableFileFilter(filter);
	            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	            int result = fileChooser.showOpenDialog(getParent());
	            if (result == JFileChooser.APPROVE_OPTION) {
	                
	                    File file = fileChooser.getSelectedFile();
	                    JOptionPane.showMessageDialog(null, "Success");
	        
	             // source images
	    			 try {
						image1 = ImageIO.read(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			 try {
						overlay = ImageIO.read(new File("/home/azharkoivila/Desktop/azhar/azhar.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	    			// create the new image
	    			int w = Math.max(image1.getWidth(), overlay.getWidth());
	    			int h = Math.max(image1.getHeight(), overlay.getHeight());
	    			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    			// paint both images, preserving the alpha channels
	    			Graphics g = combined.getGraphics();
	    			g.drawImage(image1, 0, 0, null);
	    			g.drawImage(overlay, 250, 0, null);

	    			g.dispose();

	    			// Save
	    			try {
						ImageIO.write(combined, "PNG", new File("framed"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	        }
		
		
	        }
		}

	}






	private Component getParent() {
		// TODO Auto-generated method stub
		return null;
	}
		 
		 
}
		 
		 
	







