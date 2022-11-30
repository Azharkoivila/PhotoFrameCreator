import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	JMenuItem about;
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
		
		JMenuBar Mb = new JMenuBar();
		 JMenu help = new JMenu("Help");
		 about = new JMenuItem("About");
		Mb.setBackground(new Color(33,47,60,255));
		help.setForeground(Color.white);
		Mb.setOpaque(true);
		help.add(about);
		Mb.add(help);
		jf.setJMenuBar(Mb);
		about.addActionListener(this);
		
		label=new JLabel("This is a Simple  app to Support Blood Donators");
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		label.setBounds(65,300,500,50);	
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		jf.add(label);
		
		buttonchoose=new JButton("Choose Image");
		buttonchoose.setBounds(190,450,200,50);
		buttonchoose.setBackground(new Color(207, 10, 10));
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
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png");
	            fileChooser.addChoosableFileFilter(filter);
	            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	            int result = fileChooser.showOpenDialog(getParent());
	            if (result == JFileChooser.APPROVE_OPTION) {
	                    File file = fileChooser.getSelectedFile();
						try {
							orginal = ImageIO.read(file);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "ERROR");
							e1.printStackTrace();
						}
						
	                    File compressed=new File("compressed.jpg");
	                    BufferedImage resized=new BufferedImage(width, height, orginal.getType());
	                    Graphics2D g2=resized.createGraphics();
	                    g2.drawImage(orginal,0,0,width,height, null);
	                    g2.dispose();
						try {
							ImageIO.write(resized, formant, compressed);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// source images
						try {
							image1 = ImageIO.read(new File("compressed.jpg"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							overlay = ImageIO.read((getClass().getClassLoader().getResource("frame.png")));
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
						g.drawImage(overlay, 0, 0, null);
						g.dispose();
						// Save
						try {
							ImageIO.write(combined, "PNG", new File("framed.jpg"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							display();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

	        }
	        	
	  }
	        
	        if(e.getSource()==about) {
	        	
					try {
						about();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
	
	 void about() throws URISyntaxException {
		 JFrame help=new JFrame("About Us");
		 help.setLayout(null);
		 help.getContentPane().setBackground(new Color(17,17,17,255));
		 help.setResizable(false);
		 help.setBounds(650,250,250,250);
		 
		JLabel aboutlabel=new JLabel("Developed by");
			aboutlabel.setBounds(0,10,200,20);
			aboutlabel.setBackground(Color.blue);
			aboutlabel.setForeground(Color.white);
			aboutlabel.setFont(new Font("Arial",Font.ITALIC,17));
			aboutlabel.setVisible(true);
			help.add(aboutlabel);
			
			JLabel azharkoivila=new JLabel("Azharkoivila");
			azharkoivila.setBounds(70,50,200,40);
			azharkoivila.setForeground(Color.GREEN);
			azharkoivila.setFont(new Font("Arial",Font.ITALIC,20));
			azharkoivila.setVisible(true);
			help.add(azharkoivila);
			
			final URI uri = new URI("http://azharkoivila.gitlab.io");
			JButton button = new JButton();
			button.setBounds(25,100,200,20);
			button.setForeground(Color.BLUE);
			button.setFont(new Font("Arial",Font.PLAIN,18));
			button.setText("Contact Me");
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setBackground(Color.BLUE);
			help.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						 {
								try {
									desktop.browse(uri);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					}
				}
			});
		
			JLabel special=new JLabel("Frame Designed By");
			special.setBounds(0,120,200,40);
			special.setForeground(Color.white);
			special.setFont(new Font("Arial",Font.ITALIC,17));
			special.setVisible(true);
			help.add(special);
		 
			JLabel haneefa=new JLabel("Haneefa Harish");
			haneefa.setBounds(70,150,200,40);
			haneefa.setForeground(Color.GREEN);
			haneefa.setFont(new Font("Arial",Font.ITALIC,20));
			haneefa.setVisible(true);
			help.add(haneefa);

			button.setVisible(true);
			help.setVisible(true);
		 
	 }
		 	 
}
		 
		 
	







