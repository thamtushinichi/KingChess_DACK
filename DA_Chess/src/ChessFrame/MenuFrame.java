package ChessFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ChessPane.MainGameBoardPane;


public class MenuFrame extends JFrame implements MouseListener {

	JPanel menu_pane;
	JPanel playonline_menu_pane;
	JPanel grid_menu;
	JPanel grid_playonline_menu;
	JLabel single, quit, twoplayer, playonline;
	JLabel createroom, joinroom, back;
	Color bg_color = Color.decode("#efd39c");
	//set up 
	private boolean this_is_Server;
	private boolean this_is_Client;
	private String myIp_Address;
	private String myPort;
	
	//
	private PlayOnlineDialog create_dlg;
	private PlayOnlineDialog join_dlg;
	private boolean mouse_state = true;
	 private final MainGameBoardPane mainGameBoardPane=new MainGameBoardPane();
	public MenuFrame() {
		menu_pane = new MenuPane();
		setContentPane(menu_pane);

		single = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/single.png")));
		twoplayer = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/twoplayer.png")));
		playonline = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/playonline.png")));
		quit = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit.png")));
		
		createroom = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/createroom.png")));
		joinroom = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/joinroom.png")));
		back = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/back.png")));

		single.addMouseListener(this);
		twoplayer.addMouseListener(this);
		playonline.addMouseListener(this);
		quit.addMouseListener(this);
		
		createroom.addMouseListener(this);
		joinroom.addMouseListener(this);
		back.addMouseListener(this);

		grid_menu = new JPanel(new GridLayout(4, 1));
		grid_menu.add(single);
		grid_menu.add(twoplayer);
		grid_menu.add(playonline);
		grid_menu.add(quit);
		menu_pane.add(grid_menu, BorderLayout.SOUTH);
		menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));
		// pane.setBackground(bg_color);
		pack();
		Dimension size = getSize();
		setSize(size);
	}

	public class MenuPane extends JPanel implements MouseListener {
		private ImageIcon bg;

		public MenuPane() {
			bg = new ImageIcon(getClass().getClassLoader().getResource("resources/images/KingChessBackground.jpg"));
			setPreferredSize(new Dimension(960, 640));
			addMouseListener(this);

		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image image = bg.getImage(); // transform it
			Image newimg = image.getScaledInstance(960, 640, java.awt.Image.SCALE_SMOOTH);
			bg = new ImageIcon(newimg);
			bg.paintIcon(this, g, 0, 0);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	
	public class PlayOnlineDialog extends JDialog {
	    
		private final JButton button1;
	    private final JButton button2;
	    private final JPanel panel=new JPanel();
	    
	    private final JLabel IP_address=new JLabel(" IP Address : ");
	    private final JLabel Port_number=new JLabel(" Port : ");
	    private final JTextField ip_text=new JTextField(12);
	    private final JTextField port_text=new JTextField(5);
	    
	    public PlayOnlineDialog(int choice) {

	        setSize(330,180);
	        setLocation(500,300);
	        panel.setLayout(null);
	        Container cp=getContentPane();
	        
	        panel.setSize(330,180);
	        
	        if(choice == 0){
	        	button1=new JButton("Create");
	        	this_is_Server =true;
	        	this_is_Client=false;
	 	        button2=new JButton("Cancel");
	        } else{
	        	 button1=new JButton("Join");
	        	 this_is_Server =false;
		        	this_is_Client=true;
	 	        button2=new JButton("Cancel");
	        }
	       
	        
	        button1.setSize(80,24);
	        button2.setSize(80,24);
	        
	        button1.setLocation(70,100);
	        button2.setLocation(170,100);
	        
	        IP_address.setSize(80,24);
	        IP_address.setLocation(20,20);
	        
	        ip_text.setSize(150,24);
	        ip_text.setLocation(130,20);
	        
	        Port_number.setSize(80,24);
	        Port_number.setLocation(20,50);
	        
	        port_text.setSize(150,24);
	        port_text.setLocation(130,50);
	        
	        button1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	mouse_state = true;
	            	//chúng ta phải truyền dữ liệu để cho bên MainGameBoard biết bạn đã nhấn nút create room
	            	//kiem tra du lieu hop le truoc o day truoc
	            	if(this_is_Server)
	            	{
	            		myIp_Address= ip_text.getText();
	            		myPort=port_text.getText();
	            		System.out.println("Server: "+myIp_Address + "  " + myPort);
	            		MainGameBoardPane mainGameBoardPaneServer = new MainGameBoardPane();
	            		mainGameBoardPaneServer.setMyIp_Address(myIp_Address);
	            		mainGameBoardPaneServer.setMyPort(myPort);
	            		mainGameBoardPaneServer.setThis_is_Server(true);
	            		mainGameBoardPaneServer.setThis_is_Client(false);
	            		mainGameBoardPaneServer.start_As_Server();
	            		//set ip and port
	            		menu_pane.removeAll();
	        			menu_pane.add(mainGameBoardPaneServer,BorderLayout.CENTER);
	        			menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	        			pack();
	        			//Dimension size = getSize();
	        			//setSize(size);
	        			repaint();
	            	}
	            	else if(this_is_Client)
	            	{
	            		myIp_Address= ip_text.getText();
	            		myPort=port_text.getText();
	            		System.out.println("Client: "+myIp_Address + "  " + myPort);
	            		MainGameBoardPane mainGameBoardPaneClient = new MainGameBoardPane();
	            		mainGameBoardPaneClient.setMyIp_Address(myIp_Address);
	            		mainGameBoardPaneClient.setMyPort(myPort);
	            		mainGameBoardPaneClient.setThis_is_Server(false);
	            		mainGameBoardPaneClient.setThis_is_Client(true);
	            		mainGameBoardPaneClient.start_As_Client();
	            		//set ip and port
	            		menu_pane.removeAll();
	        			menu_pane.add(mainGameBoardPaneClient,BorderLayout.CENTER);
	        			menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	        			pack();
	        			//Dimension size = getSize();
	        			//setSize(size);
	        			repaint();
	            	}
	            	else
	            	{
	            		System.out.println("ERROR: làm cách nào đó bạn đã vào game mà không nhấn vào PLayOnline ->...");
	            		
	            	}
	            	
	                dispose();
	            }
	        });
	        button2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	mouse_state = true;
	                dispose();
	            }
	        });
	        
	        
	        panel.setLayout(null);
	        
	        panel.add(button1);
	        panel.add(button2);
	        panel.add(IP_address);
	        panel.add(ip_text);
	        panel.add(Port_number);
	        panel.add(port_text);
	        
	        cp.add(panel);
	        
	        if(choice == 0){
	        	ip_text.setText("127.0.0.1");
		        port_text.setText("5000");
	        }
	    }

	    public String GetIpAddress() {
	        return ip_text.getText();
	    }
	    
	    public String GetPortnumber() {
	        return port_text.getText();
	    }
	    public void paintComponents(Graphics g) {
	        super.paintComponents(g);
	        
	    }
	    
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object source = e.getSource();
		if (source == quit) {
			quit();
		} else if (source == playonline) {
			//playonline_menu_pane = new PlayOnlinePane();
			//setContentPane(playonline_menu_pane);
			menu_pane.remove(grid_menu);
		grid_playonline_menu = new JPanel(new GridLayout(3, 1));
		grid_playonline_menu.add(createroom);
		grid_playonline_menu.add(joinroom);
		grid_playonline_menu.add(back);
			
			createroom.addMouseListener(this);
			joinroom.addMouseListener(this);
			back.addMouseListener(this);
			menu_pane.add(grid_playonline_menu, BorderLayout.SOUTH);
			menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));
			pack();
			Dimension size = getSize();
			setSize(size);
		} else if(source==twoplayer)
		{
			menu_pane.removeAll();
			menu_pane.add(mainGameBoardPane,BorderLayout.CENTER);
			menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			pack();
			//Dimension size = getSize();
			//setSize(size);
			repaint();
			
		}
		else if(source == back)
		{
			//setContentPane(menu_pane);
			menu_pane.remove(grid_playonline_menu);
			menu_pane.add(grid_menu, BorderLayout.SOUTH);
			menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));
			pack();
			Dimension size = getSize();
			setSize(size);
		} 
		if(mouse_state == false){
			return;
		}
		else{
			 source = e.getSource();
			if (source == quit) {
				quit();
			} else if (source == playonline) {
				//playonline_menu_pane = new PlayOnlinePane();
				//setContentPane(playonline_menu_pane);
				menu_pane.remove(grid_menu);
				
				grid_playonline_menu = new JPanel(new GridLayout(3, 1));
				grid_playonline_menu.add(createroom);
				grid_playonline_menu.add(joinroom);
				grid_playonline_menu.add(back);
				
				menu_pane.add(grid_playonline_menu, BorderLayout.SOUTH);
				menu_pane.setBorder(BorderFactory.createEmptyBorder(420, 20, 20, 0));
				
				pack();
				Dimension size = getSize();
				setSize(size);
			} else if(source == back){
				//setContentPane(menu_pane);
				menu_pane.remove(grid_playonline_menu);
				menu_pane.add(grid_menu, BorderLayout.SOUTH);
				menu_pane.setBorder(BorderFactory.createEmptyBorder(420, 20, 20, 0));
				pack();
				Dimension size = getSize();
				setSize(size);
			} else if(source == createroom){
				create_dlg = new PlayOnlineDialog(0);
				create_dlg.setTitle("Create Room");
				create_dlg.setVisible(true);
				mouse_state = false;
			} else if(source == joinroom){
				join_dlg = new PlayOnlineDialog(1);
				join_dlg.setTitle("Join Room");
				join_dlg.setVisible(true);
				mouse_state = false;
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(mouse_state == false){
			return;
		} else{
			Object source = e.getSource();
			if (source == single) {
				single.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/single_hover.png")));
			} else if (source == twoplayer) {
				twoplayer.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/twoplayer_hover.png")));
			} else if (source == playonline) {
				playonline.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/playonline_hover.png")));
			} else if (source == quit) {
				quit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit_hover.png")));
			} else if (source == createroom) {
				createroom.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/createroom_hover.png")));
			} else if (source == joinroom) {
				joinroom.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/joinroom_hover.png")));
			} else if (source == back) {
				back.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/back_hover.png")));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(mouse_state == false){
			return;
		} else{
			Object source = e.getSource();
			if (source == single) {
				single.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/single.png")));
			} else if (source == twoplayer) {
				twoplayer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/twoplayer.png")));
			} else if (source == playonline) {
				playonline.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/playonline.png")));
			} else if (source == quit) {
				quit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit.png")));
			} else if (source == createroom) {
				createroom.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/createroom.png")));
			} else if (source == joinroom) {
				joinroom.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/joinroom.png")));
			} else if (source == back) {
				back.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/back.png")));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void quit() {
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Game",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
