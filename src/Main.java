import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main implements ActionListener{
	JFrame jf;
	JLabel label;
	JButton buttonchoose;
	BufferedImage overlay;
	BufferedImage image1;
	BufferedImage orginal;
	int width=500,height=500;
	String formant="jpg";
	
	public Main () {
		jf=new JFrame("Frame Creator");
		jf.setLayout(null);
		jf.setResizable(false);
		jf.setBounds(600,200,600,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(new Color(57, 62, 70));
		
		label=new JLabel("This is a Simple  app to Support Blood Donators");
		label.setBounds(200,10,200,50);	
		label.setForeground(Color.green);
		label.setVisible(true);
		jf.add(label);
		
		buttonchoose=new JButton("Choose Image");
		buttonchoose.setBounds(190,400,200,50);
		buttonchoose.setBackground(new Color(0, 173, 181));
		buttonchoose.setForeground(Color.white);
		buttonchoose.setVisible(true);
		buttonchoose.addActionListener(this);
		jf.add(buttonchoose);
		
		
		 
     
		
		jf.setVisible(true);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		new Main();
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	        if (e.getSource() == buttonchoose) {
	            JFileChooser fileChooser = new JFileChooser();
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
	            fileChooser.addChoosableFileFilter(filter);
	            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	            int result = fileChooser.showOpenDialog(getParent());
	            if (result == JFileChooser.APPROVE_OPTION) {
	                
	                    File file = fileChooser.getSelectedFile();
	                    //JOptionPane.showMessageDialog(null, "Success");
	                    try {
							display();
						} catch (IOException e5) {
							// TODO Auto-generated catch block
							e5.printStackTrace();
						}
	          //write code here for compress
	                    
	    
						try {
							orginal = ImageIO.read(file);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
	                    File compressed=new File("compressed.jpg");
	                    BufferedImage resized=new BufferedImage(width, height, orginal.getType());
	                    Graphics2D g2=resized.createGraphics();
	                    g2.drawImage(orginal,0,0,width,height, null);
	                    g2.dispose();
	            		try {
							ImageIO.write(resized, formant, compressed);
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}
	                    
	                    
	        
	             // source images
	    		
						try {
							image1 = ImageIO.read(new File("compressed.jpg"));
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
				
				
						try {
							overlay = ImageIO.read(new File("/home/azharkoivila/Desktop/azhar/azhar.png"));
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
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



				void display() throws IOException {
					
			        File file = new File("framed");
			        BufferedImage bufferedImage = ImageIO.read(file);
					
					   ImageIcon imageIcon = new ImageIcon(bufferedImage);
				        JFrame DisplyFrame = new JFrame("Preview");
				      
				        DisplyFrame.setResizable(false);
				 
				        DisplyFrame.setLayout(new FlowLayout());
				        
				        DisplyFrame.setBounds(200,200,550,600);
				        
				        JLabel jLabel = new JLabel();

				 
				        jLabel.setIcon(imageIcon);

				        DisplyFrame.add(jLabel);
				        
				        JLabel Location=new JLabel("Location");
				        Location.setLayout(null);
				        Location.setBounds(100,600,100,100);
				        
				        Location.setVisible(true);
				        
				        DisplyFrame.add(Location);
				        
				        
				        DisplyFrame.setVisible(true);

	
	
					}




	private Component getParent() {
		// TODO Auto-generated method stub
		return null;
	}
		 
		 
}
		 
		 
	







