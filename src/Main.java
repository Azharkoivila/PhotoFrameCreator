import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main implements ActionListener{
	JFrame jf;
	JLabel label;
	JButton buttonchoose;
	BufferedImage overlay;
	BufferedImage image1;
	BufferedImage orginal;
	int width=1000,height=1000;
	String formant="jpg";
	JLabel Imagelb;
	
	public Main () {
		jf=new JFrame("Frame Creator");
		
		jf.setResizable(false);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(new Color(17,17,17,255));
		
		
		Imagelb=new JLabel(new ImageIcon(getClass().getClassLoader().getResource("banner.gif")));
		
		jf.getContentPane().add(Imagelb);
		
		jf.pack();
		jf.setBounds(600,200,600,600);
		
		jf.setLayout(null);
		
		
		label=new JLabel("This is a Simple  app to Support Blood Donators");
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		label.setBounds(65,300,500,50);	
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		jf.add(label);
		
		buttonchoose=new JButton("Choose Image");
		buttonchoose.setBounds(190,450,200,50);
		buttonchoose.setBackground(new Color(250,104,0,255));
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
	                    
	                    
	                   try {
							orginal = ImageIO.read(file);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "ERROR");
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
							overlay = ImageIO.read((getClass().getClassLoader().getResource("frame.png")));
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
	    			g.drawImage(overlay, 0, 0, null);

	    			g.dispose();

	    			// Save
	    			try {
						ImageIO.write(combined, "PNG", new File("framed.jpg"));
						display();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	        }
		
		
	        }
		}



				void display() throws IOException {
						deleteCompressed();
						File file = new File("framed.jpg");
				        BufferedImage bufferedImage = ImageIO.read(file);
				        String path=file.getAbsolutePath();
						ImageIcon imageIcon = new ImageIcon(bufferedImage);
					    JFrame DisplyFrame = new JFrame("Preview");
					        
					    DisplyFrame.getContentPane().setBackground(new Color(17,17,17,255));
					    //DisplyFrame.setResizable(false);
					       
					        
					       
					        JLabel jLabel = new JLabel();
					        jLabel.setIcon(imageIcon);
					        
					        DisplyFrame.getContentPane().add(jLabel);
					        
					        DisplyFrame.pack();
					        DisplyFrame.setBounds(200,200,1000,1050);
					        
					        DisplyFrame.setLayout(null);
					        
					        
					             
					        
					        DisplyFrame.setVisible(true);
					        
					        
					        
					        
					        
					        
					        JFrame pathjf=new JFrame();
					        pathjf.setResizable(false);
							
							pathjf.setBounds(100,100,700,100);
							
							pathjf.getContentPane().setBackground(new Color(17,17,17,255));
							
							
							JLabel pathlb=new JLabel("Image Saved To:- "+path);
							pathlb.setBounds(10,0,700,100);
							pathlb.setForeground(Color.white);
							pathlb.setVisible(true);
							
							pathjf.setLayout(null);
							pathjf.add(pathlb);
							pathjf.setVisible(true);
							

		
					}




	private Component getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	void deleteCompressed() throws IOException {
		Files.deleteIfExists(
                Paths.get("compressed.jpg"));
		
	}
	
	
		 
		 
}
		 
		 
	







