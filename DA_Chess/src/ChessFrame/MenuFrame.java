package ChessFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import javax.swing.SwingUtilities;

import ChessPane.HumanAndComputerBoardPane;
import ChessPane.MainGameBoardPane;

public class MenuFrame extends JFrame implements MouseListener {

	JPanel menu_pane;
	JPanel playonline_menu_pane;
	JPanel grid_menu, grid_playing_option;
	JPanel grid_playonline_menu;
	JLabel single, quit, twoplayer, playonline;
	JLabel createroom, joinroom, back;
	JLabel quit_while_playing;
	Color bg_color = Color.decode("#efd39c");
	// set up
	private boolean this_is_Server;
	private boolean this_is_Client;
	private boolean this_is_Local;
	private String myIp_Address;
	private String myPort;
	private String currentHostIpAddress = null;

	private JFrame mainFrame;
	//
	private PlayOnlineDialog create_dlg;
	private PlayOnlineDialog join_dlg;
	private boolean mouse_state = true;
	private final MainGameBoardPane mainGameBoardPane = new MainGameBoardPane();// cho
																				// local
	private final HumanAndComputerBoardPane human_Computer_BoardPane = new HumanAndComputerBoardPane();
	
	private MainGameBoardPane mainGameBoardPaneServer;
	private MainGameBoardPane mainGameBoardPaneClient;
	public MenuFrame() {
		menu_pane = new MenuPane();
		setContentPane(menu_pane);
		mainFrame = (JFrame) SwingUtilities.getWindowAncestor(menu_pane);

		single = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/single.png")));
		twoplayer = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("resources/images/twoplayer.png")));
		playonline = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("resources/images/playonline.png")));
		quit = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit.png")));

		createroom = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("resources/images/createroom.png")));
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
		private final JPanel panel = new JPanel();

		private final JLabel IP_address = new JLabel(" IP Address : ");
		private final JLabel Port_number = new JLabel(" Port : ");
		private final JTextField ip_text = new JTextField(12);
		private final JTextField port_text = new JTextField(5);

		public PlayOnlineDialog(int choice) {

			setSize(330, 180);
			setLocation(500, 300);
			panel.setLayout(null);
			Container cp = getContentPane();

			panel.setSize(330, 180);

			if (choice == 0) {
				button1 = new JButton("Create");
				this_is_Server = true;
				this_is_Client = false;
				button2 = new JButton("Cancel");
			} else {
				button1 = new JButton("Join");
				this_is_Server = false;
				this_is_Client = true;
				button2 = new JButton("Cancel");
			}

			button1.setSize(80, 24);
			button2.setSize(80, 24);

			button1.setLocation(70, 100);
			button2.setLocation(170, 100);

			IP_address.setSize(80, 24);
			IP_address.setLocation(20, 20);

			ip_text.setSize(150, 24);
			ip_text.setLocation(130, 20);

			Port_number.setSize(80, 24);
			Port_number.setLocation(20, 50);

			port_text.setSize(150, 24);
			port_text.setLocation(130, 50);
			// khi nhấn nút Create trong dialog Create Room thì chạy hàm này
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mouse_state = true;
					// chúng ta phải truyền dữ liệu để cho bên MainGameBoard
					// biết bạn đã nhấn nút create room
					// kiem tra du lieu hop le truoc o day truoc
					if (this_is_Server) {
						create_dlg.setVisible(false);
						myIp_Address = ip_text.getText();
						myPort = port_text.getText();
						System.out.println("Server: " + myIp_Address + "  " + myPort);

						mainGameBoardPaneServer = new MainGameBoardPane();
						mainGameBoardPaneServer.setMyIp_Address(myIp_Address);
						mainGameBoardPaneServer.setMyPort(myPort);
						mainGameBoardPaneServer.setThis_is_Server(true);
						mainGameBoardPaneServer.setThis_is_Client(false);
						mainGameBoardPaneServer.start_As_Server();
						// set ip and port
						if (mainGameBoardPaneServer.getCheckCancel() == false) {
							menu_pane.removeAll();

							quit_while_playing = new JLabel(new ImageIcon(
									getClass().getClassLoader().getResource("resources/images/quit.png")));
							quit_while_playing.addMouseListener(MenuFrame.this);

//							grid_playing_option = new JPanel(new GridLayout(1, 1));
//							grid_playing_option.add(quit_while_playing, BorderLayout.SOUTH);
//							menu_pane.add(grid_playing_option, BorderLayout.WEST);

							menu_pane.add(mainGameBoardPaneServer, BorderLayout.CENTER);

							mainGameBoardPaneServer.getTurnPane().add(quit_while_playing);
							menu_pane.add(mainGameBoardPaneServer.getTurnPane(), BorderLayout.EAST);
							menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
							mainFrame.setTitle("Server");
							pack();
							repaint();
							Dimension size = getSize();
							setSize(size);
							JOptionPane.showMessageDialog(null, "You Are White!", "Infomation",
									JOptionPane.INFORMATION_MESSAGE);
						}

					} else if (this_is_Client) {
						myIp_Address = ip_text.getText();
						myPort = port_text.getText();
						System.out.println("Client: " + myIp_Address + "  " + myPort);
						mainGameBoardPaneClient = new MainGameBoardPane();
						mainGameBoardPaneClient.setMyIp_Address(myIp_Address);
						mainGameBoardPaneClient.setMyPort(myPort);
						mainGameBoardPaneClient.setThis_is_Server(false);
						mainGameBoardPaneClient.setThis_is_Client(true);
						mainGameBoardPaneClient.start_As_Client();
						// set ip and port
						menu_pane.removeAll();
						
						quit_while_playing = new JLabel(new ImageIcon(
								getClass().getClassLoader().getResource("resources/images/quit.png")));
						quit_while_playing.addMouseListener(MenuFrame.this);

//						grid_playing_option = new JPanel(new GridLayout(1, 1));
//						grid_playing_option.add(quit_while_playing, BorderLayout.SOUTH);
//						menu_pane.add(grid_playing_option, BorderLayout.WEST);
						
						menu_pane.add(mainGameBoardPaneClient, BorderLayout.CENTER);
						
						mainGameBoardPaneClient.getTurnPane().add(quit_while_playing);
						menu_pane.add(mainGameBoardPaneClient.getTurnPane(), BorderLayout.EAST);
						menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
						mainFrame.setTitle("Client");

						pack();
						repaint();
						JOptionPane.showMessageDialog(null, "You Are Black!", "Infomation",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
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

			if (choice == 0) {
				ip_text.setText(GetHostIpAddress());
				// ip_text.setEnabled(false);
				port_text.setText("5000");
			}
		}

		private String GetHostIpAddress() {
			Enumeration<NetworkInterface> netInterfaces = null;
			try {
				netInterfaces = NetworkInterface.getNetworkInterfaces();

				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = netInterfaces.nextElement();
					Enumeration<InetAddress> address = ni.getInetAddresses();
					while (address.hasMoreElements()) {
						InetAddress addr = address.nextElement();
						if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
								&& !(addr.getHostAddress().indexOf(":") > -1)) {
							currentHostIpAddress = addr.getHostAddress();
						}
					}
				}
				if (currentHostIpAddress == null) {
					currentHostIpAddress = "127.0.0.1";
				}

			} catch (SocketException ex) {
				currentHostIpAddress = "127.0.0.1";
			}
			return currentHostIpAddress;
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
		}
		if (source == single) {

			menu_pane.removeAll();
			menu_pane.add(human_Computer_BoardPane, BorderLayout.CENTER);
			menu_pane.add(mainGameBoardPane.getTurnPane(), BorderLayout.EAST);
			menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			pack();
			repaint();

		} else if (source == playonline) {
			// playonline_menu_pane = new PlayOnlinePane();
			// setContentPane(playonline_menu_pane);
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
		} else if (source == twoplayer) {
			menu_pane.removeAll();
			
			this_is_Local = true;
			
			quit_while_playing = new JLabel(new ImageIcon(
					getClass().getClassLoader().getResource("resources/images/quit.png")));
			quit_while_playing.addMouseListener(MenuFrame.this);

			grid_playing_option = new JPanel(new GridLayout(1, 1));
			grid_playing_option.add(quit_while_playing, BorderLayout.SOUTH);
			menu_pane.add(grid_playing_option, BorderLayout.WEST);
			
			menu_pane.add(mainGameBoardPane, BorderLayout.CENTER);
			menu_pane.add(mainGameBoardPane.getTurnPane(), BorderLayout.EAST);
			menu_pane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			pack();
			// Dimension size = getSize();
			// setSize(size);
			repaint();

		} else if (source == back) {
			// setContentPane(menu_pane);
			menu_pane.remove(grid_playonline_menu);
			menu_pane.add(grid_menu, BorderLayout.SOUTH);
			menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));
			pack();
			Dimension size = getSize();
			setSize(size);
		}
		if (mouse_state == false) {
			return;
		} else {
			source = e.getSource();
			// if (source == quit) {
			// quit();
			// }
			// else

			if (source == playonline) {
				// playonline_menu_pane = new PlayOnlinePane();
				// setContentPane(playonline_menu_pane);
				menu_pane.remove(grid_menu);

				grid_playonline_menu = new JPanel(new GridLayout(3, 1));
				grid_playonline_menu.add(createroom);
				grid_playonline_menu.add(joinroom);
				grid_playonline_menu.add(back);

				menu_pane.add(grid_playonline_menu, BorderLayout.SOUTH);
				menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));

				pack();
				Dimension size = getSize();
				setSize(size);
			} else if (source == back) {
				// setContentPane(menu_pane);
				menu_pane.remove(grid_playonline_menu);
				menu_pane.add(grid_menu, BorderLayout.SOUTH);
				menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));
				pack();
				Dimension size = getSize();
				setSize(size);
			} else if (source == createroom) {
				if(mainGameBoardPaneServer != null){
					System.out.println("khac null");
//					try {
//						mainGameBoardPaneServer.getSocket().close();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
					mainGameBoardPaneServer.getServerThread().interrupt();
					mainGameBoardPaneServer = null;
				}
				create_dlg = new PlayOnlineDialog(0);
				create_dlg.setTitle("Create Room");
				create_dlg.setVisible(true);
				mouse_state = false;
			} else if (source == joinroom) {
				join_dlg = new PlayOnlineDialog(1);
				join_dlg.setTitle("Join Room");
				join_dlg.setVisible(true);
				mouse_state = false;
			} else if (source == quit_while_playing) {
				if(this_is_Local == true){
					this_is_Local = false;
					menu_pane.removeAll();
					menu_pane.add(grid_menu, BorderLayout.SOUTH);
				}
				else{
					try {
						if(this_is_Server){
							mainGameBoardPaneServer.getSocket().shutdownInput();
							mainGameBoardPaneServer.getServerSocket().close();
							mainGameBoardPaneServer.getSocket().close();
						}
						else{
							mainGameBoardPaneClient.getSocket().shutdownInput();
							mainGameBoardPaneClient.getSocket().close();
						}
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(this_is_Server){
						mainGameBoardPaneServer.getTransferThread().interrupt();
						mainGameBoardPaneServer.getServerThread().interrupt();
						mainGameBoardPaneServer = null;
						this_is_Server = false;
					}
					else{
						mainGameBoardPaneClient.getTransferThread().interrupt();
						mainGameBoardPaneClient = null;
						this_is_Client = false;
					}
					
					myIp_Address = "";
					myPort = "";
					
					menu_pane.removeAll();
					menu_pane.add(grid_playonline_menu, BorderLayout.SOUTH);
				}
				
				
				
				menu_pane.setBorder(BorderFactory.createEmptyBorder(380, 20, 20, 0));
				pack();
				Dimension size = getSize();
				setSize(size);
				
				
				
				
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (mouse_state == false) {
			return;
		} else {
			Object source = e.getSource();
			if (source == single) {
				single.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/single_hover.png")));
			} else if (source == twoplayer) {
				twoplayer.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/twoplayer_hover.png")));
			} else if (source == playonline) {
				playonline.setIcon(new ImageIcon(
						getClass().getClassLoader().getResource("resources/images/playonline_hover.png")));
			} else if (source == quit) {
				quit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit_hover.png")));
			} else if (source == createroom) {
				createroom.setIcon(new ImageIcon(
						getClass().getClassLoader().getResource("resources/images/createroom_hover.png")));
			} else if (source == joinroom) {
				joinroom.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/joinroom_hover.png")));
			} else if (source == back) {
				back.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/back_hover.png")));
			} else if (source == quit_while_playing) {
				quit_while_playing.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit_hover.png")));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (mouse_state == false) {
			return;
		} else {
			Object source = e.getSource();
			if (source == single) {
				single.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/single.png")));
			} else if (source == twoplayer) {
				twoplayer.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/twoplayer.png")));
			} else if (source == playonline) {
				playonline.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/playonline.png")));
			} else if (source == quit) {
				quit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit.png")));
			} else if (source == createroom) {
				createroom.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/createroom.png")));
			} else if (source == joinroom) {
				joinroom.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource("resources/images/joinroom.png")));
			} else if (source == back) {
				back.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/back.png")));
			} else if (source == quit_while_playing) {
				quit_while_playing.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/quit.png")));
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
