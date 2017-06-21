package ChessPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import players.Player1;
import players.Player2;

@SuppressWarnings("serial")
public class HumanAndComputerBoardPane extends JPanel {
	private Player1 P1 = new Player1();
	private Player2 P2 = new Player2();
	private final int Divide = 600 / 8; // 600 lÃ  kÃ­ch thÆ°á»›c bá»� ngang hoáº·c rá»™ng
										// cá»§a bÃ n cá»�, 8 lÃ  sá»‘ Ã´ cá»�
	// chÃºng ta tÃ­nh Ä‘Æ°á»£c cáº¡nh cá»§a 1 Ã´ cá»� lÃ  bao nhiÃªu , Ä‘Ã¡p Ã¡n: 75 pixel
	// private int move=0;
	private Rectangle2D rec; // 1 Ã´ cá»� lÃ  1 Rectangle2D
	private short players_turn = 1;
	private boolean GameOver = false; // check game over
	private boolean local = true;// check xem cháº¿ Ä‘á»™ chÆ¡i lÃ  local 2 ngÆ°á»�i chÆ¡i
									// ko
	private boolean game_Started = true;// check xem game Ä‘Ã£ báº¯t Ä‘áº§u chÆ°a
	private String Box; // Ä‘Ã³ng gÃ³i dá»¯ liá»‡u Ä‘á»ƒ gá»­i Ä‘i
	private PrintWriter out; // ghi dá»¯ liá»‡u
	private boolean this_is_Server; // Ä‘Ã¢y lÃ  server
	private boolean this_is_Client; // Ä‘Ã¢y lÃ  client
	// Set up ip vÃ  port there
	private String myIp_Address;
	private String myPort;
	// init socket
	private ServerSocket serverSocket;
	private Socket Sock; // init Sock giao tiáº¿p giá»¯a 2 cá»•ng
	private BufferedReader in; // Ä‘á»�c dá»¯ liá»‡u
	// private Recv_Thread rec_from;
	private TranferData_Thread tranfer_Data_Thread;

	private JDialog dialog;
	private JLabel player_turn_1, player_turn_2;
	private JPanel turn_pane;
	// private ImageIcon black_turn_img = new
	// ImageIcon(getClass().getClassLoader().getResource("resources/images/blackturn.png"));
	// private ImageIcon white_turn_img = new
	// ImageIcon(getClass().getClassLoader().getResource("resources/images/whiteturn.png"));
	private JTextField txt_turn = new JTextField(20);
	

	public void start_As_Server() {
		tranfer_Data_Thread = new TranferData_Thread();
		setGame_Started(false);

		initialization_Again_Parameter();
		int port = Integer.parseInt(myPort);
		// handle button Create room
		try {
			InetAddress thisIp = InetAddress.getByName(myIp_Address);
			serverSocket = new ServerSocket(port, 1, thisIp);

			Thread Server_Thread = new Thread(new Runnable() {
				public synchronized void run() {
					try {
						System.out.println("truoc khi cho");

						Sock = serverSocket.accept();
						dialog.setVisible(false);
						player_turn_1.setIcon(new ImageIcon(
								getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
						player_turn_2.setIcon(new ImageIcon(
								getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
						turn_pane.add(player_turn_2);
						turn_pane.add(player_turn_1);

						System.out.println("sau  khi cho");
						in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
						out = new PrintWriter(Sock.getOutputStream());
						tranfer_Data_Thread.start();
						game_Started = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("ERROR : Server_Thread");
					}

				}
			});
			Server_Thread.start();

			dialog = new JDialog(SwingUtilities.windowForComponent(this));
			dialog.setModal(true);
			dialog.setLocation(new Point(470, 300));
			dialog.setPreferredSize(new Dimension(430, 105));

			ImageIcon loading = new ImageIcon(getClass().getClassLoader().getResource("resources/images/pac.gif"));
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBackground(Color.black);
			panel.add(new JLabel(loading, JLabel.CENTER));
			// panel.add(new JLabel("Please wait......."),
			// BorderLayout.PAGE_START);
			dialog.add(panel);

			// dialog.setLocationRelativeTo(getParent());
			dialog.setResizable(false);
			dialog.setUndecorated(true);
			// dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			dialog.pack();
			dialog.setVisible(true);

			// káº¿t thÃºc viá»‡c start server vÃ  server lÃºc nÃ y Ä‘ang chá»� káº¿t ná»‘i

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR : serverSocket");
		}
		setLocal(false);
		repaint();

	}

	public void start_As_Client() {
		tranfer_Data_Thread = new TranferData_Thread();
		setGame_Started(false);

		initialization_Again_Parameter();
		int port = Integer.parseInt(myPort);
		setLocal(false);
		// handle button Create room
		try {
			Sock = new Socket(myIp_Address, port);
			player_turn_1.setIcon(
					new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
			player_turn_2.setIcon(
					new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
			turn_pane.add(player_turn_1);
			turn_pane.add(player_turn_2);
			in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
			out = new PrintWriter(Sock.getOutputStream());

			tranfer_Data_Thread.start();
			game_Started = true;

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR : serverSocket");
		}

		repaint();
	}

	private void initialization_Again_Parameter() {
		// TODO Auto-generated method stub
		P1 = new Player1();
		P2 = new Player2();
		// move=0;
		players_turn = 1;
		GameOver = false;
		local = true;

		repaint();

	}

	public JPanel getTurnPane() {
		return turn_pane;
	}
	
	public void setTurnPane(JPanel panel) {
		turn_pane = panel;
	}

	public HumanAndComputerBoardPane() {

		player_turn_1 = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
		player_turn_2 = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));

		turn_pane = new JPanel(new GridLayout(3, 1));

		// setSize(new Dimension(600, 600));
		setPreferredSize(new Dimension(600, 600));
		// setLocation(2,1);
		Mouse_when_Move mouse_Drag_And_Drop = new Mouse_when_Move();
		Mouse_Here mouseHereEvent = new Mouse_Here();
		addMouseMotionListener(mouse_Drag_And_Drop);
		addMouseListener(mouseHereEvent);
		setLayout(null);
		
		//play();
	}
//	public void play()
//	{
//		Thread t = new Thread(){
//            public synchronized void run(){
//                while(true)
//                {
//                	if(players_turn==2)
//                	{
//                		players_turn=1;
//                		Random rand= new Random();
//                		int togo= rand.nextInt((16-1)+1)+1;
//                		int aa= rand.nextInt((8-1)+1)+1;
//                		int bb= rand.nextInt((8-1)+1)+1;
//                		Box = Integer.toString(togo) + Integer.toString(aa)
//						+ Integer.toString(bb);
//                		
//                	}
//                	try{                        
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                
//            }
//		};
//		t.start();
//	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int width = 600;
		int height = 600;
		// draw board
		for (int i = 0; i < 8; i = i + 2) {
			for (int j = 0; j < 8; j = j + 2) {
				g2.setColor(Color.GRAY);
				rec = new Rectangle2D.Double(j * width / 8, (1 + i) * width / 8, Divide, Divide);
				g2.fill(rec);
				rec = new Rectangle2D.Double((1 + j) * width / 8, i * width / 8, Divide, Divide);
				g2.fill(rec);
			}
		}
		// Ä‘áº·t quÃ¢n cá»� vÃ o vá»‹ trÃ­ cÃ¡c Ã´ cá»�
		Point positionP;
		int postX;
		int postY;
		Image image;
		for (int i = 1; i <= 32; i++) {
			if (i < 17) {
				if (i == P2.GetInHand()) {
					positionP = P2.getPixelPoint(i);

				} else {
					positionP = P2.returnPosition(i);

				}
				image = P2.returnIconImage(i);
			} else {
				if (i == P1.GetInHand()) {
					positionP = P1.getPixelPoint(i);

				} else {
					positionP = P1.returnPosition(i);
				}
				image = P1.returnIconImage(i);
			}
			// bat dau ve
			if (i == P1.GetInHand()) {
				g2.drawImage(image, positionP.x - 25, positionP.y - 25, Divide - 40, Divide - 12, this);

			} else if (i == P2.GetInHand()) {
				g2.drawImage(image, positionP.x - 25, positionP.y - 25, Divide - 40, Divide - 12, this);
			} else {
				postX = rowToX(positionP.x);
				postY = colToY(positionP.y);
				g2.drawImage(image, postX + 20, postY + 4, Divide - 40, Divide - 12, this);

			}
		}
	}

	public class Mouse_Here implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {

			// khi nháº£ quÃ¢n cá»� ra thÃ¬ cháº¡y hÃ m nÃ y
			boolean can_send = false; // dung de send sau khi danh 1 quan co
										// sang cho doi phuong
			if (!isGameOver()) {
				Point newP;
				Point samePosition;
				if (P1.GetInHand() != -1) {

					newP = P1.getPixelPoint(P1.GetInHand());
					newP.x = newP.x / Divide;
					newP.y = newP.y / Divide;
					newP.x++;
					newP.y++;
					int other_Index;// vi tri khac , vi tri sau khi tha chuot ra
					Point oldP = P1.returnOldPosition(P1.GetInHand());
					int x = oldP.x;
					int y = oldP.y;
					Point presentP = P1.returnPosition(P1.GetInHand());
					///////////////// ______________________________________
					if (isLocal() || isThis_is_Server()) {
						// con dung de check dang la server
						if (P1.GetInHand() < 33 && P1.GetInHand() > 24) {
							// Inhand la quan Ä‘á»‹ch
							for (int i = 1; i < 17; i++) {
								samePosition = P2.returnPosition(i);
								if (samePosition.x == newP.x && samePosition.y == newP.y) {
									if (P1.setSeentoPawns(P1.GetInHand(), samePosition)) {
										break;
									}
								}
							}
						}
						if (!(newP.x == presentP.x && newP.y == presentP.y)) {
							if (P1.checkMove(newP, P1.GetInHand())) {
								int aa = P1.GetInHand();
								// check náº¿u nhÆ° di chuyá»ƒn Ä‘áº¿n Ã´ khÃ´ng há»£p lá»‡
								boolean flag = false;
								for (int i = 1; i <= 32; i++) {
									if (P1.GetInHand() != i) {
										// check náº¿u á»Ÿ Ä‘Ã¢y cÃ³ quÃ¢n cá»�
										if (i < 17) {
											// tráº£ vá»� náº¿u nhÆ° trÃªn Ä‘Æ°á»�ng Ä‘i gáº·p
											// cáº£n trá»Ÿ vÃ  khÃ´ng thá»ƒ di chuyá»ƒn
											flag = P1.checkWay(newP, P2.returnPosition(i), P1.GetInHand());
										} else {
											flag = P1.checkWay(newP, P1.returnPosition(i), P1.GetInHand());
										}
										if (flag == true) {
											break; // cÃ³ quÃ¢n cá»� á»Ÿ Ä‘Ã¢y
										}
									}
								}
								// ________________________
								if (!flag && P1.piece_Already_There(newP)) {
									// náº¿u nhÆ° di chuyá»ƒn há»£p lá»‡ vÃ  check cÃ³ quÃ¢n
									// cá»� Ä‘á»‘i phÆ°Æ¡ng táº¡i vá»‹ trÃ­ sáº¯p Ä‘áº¿n ko
									// check náº¿u vá»‹ trÃ­ má»›i cÃ³ chiáº¿u vua Black
									// khÃ´ng
									boolean king2 = true;
									Point myOld = new Point();
									Point myOldTemp = P1.returnPosition(P1.GetInHand());
									myOld.x = myOldTemp.x;
									myOld.y = myOldTemp.y;
									Point other = new Point();
									Point f = new Point();
									boolean kill = false;
									int killed = -1;
									boolean end_move = true;
									// báº¯t Ä‘áº§u check king
									for (int k = 1; k < 17; k++) {
										other = P2.returnPosition(k);
										if (newP.x == other.x && newP.y == other.y) {
											int inHand = P1.GetInHand();
											if (inHand > 24 && P1.returnsoliderSeen(inHand)) {
												kill = true;// cÃ³ quÃ¢n cá»� Ä‘Ã£
															// cháº¿t
												f.x = other.x;
												f.y = other.y;
												P2.killedPiece(k);// player 2
																	// giet quan
																	// co co gia
																	// tri k do
																	// di
											} else if (inHand <= 24) {
												kill = true;
												f.x = other.x;
												f.y = other.y;
												P2.killedPiece(k);
											} else {
												P1.changePosition(myOld, inHand);
												end_move = false;
												break;
											}
											killed = k;
											break;
										}
									}
									if (end_move) {
										P1.changePosition(newP, P1.GetInHand()); // káº¿t
																					// thÃºc
																					// sá»±
																					// di
																					// chuyá»ƒn
																					// P1
										if (P2.checkEmenyGameOver(P1)) {
											// neu nhu P2 game over
											JOptionPane.showConfirmDialog(getParent(),
													"Check Mate\n White won the game", "Game Over",
													JOptionPane.DEFAULT_OPTION);
											return;
										}

										if (P1.GetInHand() >= 25 && P1.GetInHand() <= 32) {
											Point pawn_position = P1.returnPosition(P1.GetInHand());
											// check tá»‘t Ä‘Ã£ di chuyá»ƒn thÃ nh cÃ´ng
											// chÆ°a
											if (pawn_position.y != 7) {
												P1.moved_Success(P1.GetInHand());
											}
											// náº¿u lÃ  quÃ¢n tá»‘t thÃ¬ check tiáº¿p cÃ³
											// náº±m á»Ÿ vá»‹ trÃ­ Ä‘Æ°á»£c sáº¯c phong Háº­u
											// khÃ´ng

											if (pawn_position.y == 1) {
												P1.honor_Queen(P1.GetInHand());
											}
										}
									}
									P1.checkKing(false);// ko check king ná»¯a
									if (P1.see_EnemyKingIsChecked(P2)) {
										// náº¿u vua cá»§a tÃ´i bá»‹ chiáº¿u náº¿u tÃ´i di
										// chuyen, vÃ¬ váº­y
										// tÃ´i khÃ´ng thá»ƒ di chuyá»ƒn quÃ¢n cá»� vÃ 
										// pháº£i return back to old position
										P1.changePosition(myOld, P1.GetInHand());
										P1.checkKing(true);// set king bá»‹ check
															// lÃ  true
										end_move = false;// chÆ°a káº¿t thÃºc Ä‘Æ°á»£c

									}
									if (kill && P1.returncheckKing()) {
										P2.changePosition(f, killed);

									}
									if (!P1.returncheckKing()) {
										if (P2.see_EnemyKingIsChecked(P1)) {
											// náº¿u nhÆ° vua Ä‘á»‘i phÆ°Æ¡ng bá»‹ check
											P2.checkKing(true);
											end_move = false;
											// chÆ°a káº¿t thÃºc lÆ°á»£t Ä‘Æ°á»£c
											if (P2.checkEmenyGameOver(P1)) {
												// náº¿u nhÆ° Player 1 game over
												GameOver();
												Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x)
														+ Integer.toString(newP.y);
												can_send = true;// co the gui di
																// qua internet

											} else {
												Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x)
														+ Integer.toString(newP.y);
												CheckStatus();
												can_send = true;
											}
										}
										
										if (end_move) {

											Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x)
													+ Integer.toString(newP.y);
											ChangeTurn();
											can_send = true;

										}
									}
								}
							}
						}
						// khi káº¿t thÃºc lÆ°á»£t thÃ¬ check

						P1.SetInHand(-1);
						repaint();
						if (can_send && ((isThis_is_Server() || isThis_is_Client()))) {
							// chá»— nÃ y Ä‘á»ƒ thá»±c hiá»‡n viá»‡c send Ä‘i dá»¯ liá»‡u
							// cháº¡y hÃ m Send_move(); Ä‘á»ƒ send dá»¯ liá»‡u
							Send_move();

						}
						if (isGameOver()) {
							System.out.println("game over 1");
							JOptionPane.showConfirmDialog(getParent(), "Check Mate\n White won the game", "Game Over",
									JOptionPane.DEFAULT_OPTION);

						}
					}
				}

				else if (P2.GetInHand() != -1) {

					if (isLocal() || isThis_is_Client()) {
						// chá»— nÃ y Ä‘á»ƒ check cÃ³ pháº£i lÃ  Ä‘Ã¡nh local hoáº·c Ä‘Ã¡nh
						// online hÃ¬nh thá»©c client
						newP = P2.getPixelPoint(P2.GetInHand());
						newP.x = newP.x / Divide;
						newP.y = newP.y / Divide;
						newP.x++;
						newP.y++;
						boolean king1 = false;
						Point oldP = P2.returnOldPosition(P2.GetInHand());
						Point presentP = P2.returnPosition(P2.GetInHand());
						// set the seen of the Pawn - black
						if (P2.GetInHand() < 17 && P2.GetInHand() > 8) {
							for (int i = 17; i < 33; i++) {
								samePosition = P1.returnPosition(i);
								if (samePosition.x == newP.x && samePosition.y == newP.y) {
									if (P2.setSeentoPawns(P2.GetInHand(), samePosition)) {
										break;
									}
								}
							}
						}
						if (!(newP.x == presentP.x && newP.y == presentP.y)) {
							if (P2.checkMove(newP, P2.GetInHand())) {
								boolean flag = false;
								for (int i = 1; i <= 32; i++) {
									if (P2.GetInHand() != i) {
										// quÃ¢n chá»�n khÃ´ng pháº£i quÃ¢n Ä‘Æ°á»£c duyá»‡t
										if (i < 17) {
											flag = P2.checkWay(newP, P2.returnPosition(i), P2.GetInHand());

										} else {
											flag = P2.checkWay(newP, P1.returnPosition(i), P2.GetInHand());
										}
										if (flag == true) {
											break;
										}
									}
								}
								for (int i = 1; i <= 16 && !flag; i++) {
									if (P2.GetInHand() != i) {
										if (flag == false) {
											samePosition = P2.returnPosition(i);
											if (newP.x == samePosition.x && newP.y == samePosition.y) {
												flag = true;
												break;
											}
										}
									}
									if (flag == true) {
										break;
									}
								}
								if (!flag) {
									Point king_Position_player2 = P2.returnPosition(8);
									Point myOldP = new Point();
									Point oldTempP = P2.returnPosition(P2.GetInHand());
									myOldP.x = oldTempP.x;
									myOldP.y = oldTempP.y;
									Point other = new Point();
									Point f = new Point();
									boolean kill = false;
									boolean end_move = true;
									int killed = -1;
									for (int k = 17; k < 33; k++) {
										other = P1.returnPosition(k);
										if (newP.x == other.x && newP.y == other.y) {

											int inHand = P2.GetInHand();

											if (inHand > 8 && P2.returnsoliderSeen(inHand)) {
												kill = true;

												other = P1.returnPosition(k);

												f.x = other.x;
												f.y = other.y;

												P1.killedPiece(k);
											} else if (inHand <= 8) {
												kill = true;

												other = P1.returnPosition(k);

												f.x = other.x;
												f.y = other.y;
												P1.killedPiece(k);
											} else {
												end_move = false;
												P2.changePosition(myOldP, inHand);
											}

											killed = k;
											break;

										}

									}
									// Ä‘á»“ng nghÄ©a vá»›i king2 =true;
									if (end_move) {
										// khÃºc nÃ y lÃ  Ä‘Ã£ káº¿t thÃºc lÆ°á»£t P2
										P2.changePosition(newP, P2.GetInHand());

										if (P1.checkEmenyGameOver(P2)) {
											// neu nhu P2 game over
											JOptionPane.showConfirmDialog(getParent(),
													"Check Mate\n Black won the game", "Game Over",
													JOptionPane.DEFAULT_OPTION);
											return;
										}

										if (P2.GetInHand() >= 9 && P2.GetInHand() <= 16) {
											Point pawn_position1 = P2.returnPosition(P2.GetInHand());
											// check tá»‘t Ä‘Ã£ di chuyá»ƒn thÃ nh cÃ´ng
											// chÆ°a

											if (pawn_position1.y != 2) {
												P2.moved_Success(P2.GetInHand());
											}
											// náº¿u lÃ  quÃ¢n tá»‘t thÃ¬ check tiáº¿p cÃ³
											// náº±m á»Ÿ vá»‹ trÃ­ Ä‘Æ°á»£c sáº¯c phong Háº­u
											// khÃ´ng

											if (pawn_position1.y == 8) {
												P2.honor_Queen(P2.GetInHand());
											}
										}
									}
									P2.checkKing(false);
									if (P2.see_EnemyKingIsChecked(P1)) {
										// náº¿u king bá»‹ check thÃ¬ khÃ´ng Ä‘Æ°á»£c di
										// chuyá»ƒn
										// vÃ  quÃ¢n cá»� pháº£i bá»‹ trá»Ÿ vá»� vá»‹ trÃ­ cÅ©
										P2.changePosition(myOldP, P2.GetInHand());
										P2.checkKing(true);
										end_move = false;
									}
									if (kill && P2.returncheckKing()) {
										P1.changePosition(f, killed);
									}
									if (P2.returncheckKing()) {
										P2.changePosition(myOldP, P2.GetInHand());
									}
									if (!P2.returncheckKing()) {
										// mean: di chuyen mÃ  King2 khÃ´ng bi
										// chiáº¿u
										if (P1.see_EnemyKingIsChecked(P2)) {
											// náº¿u king 1 bá»‹ check thÃ¬ khÃ´ng
											// Ä‘Æ°á»£c di chuyá»ƒn
											// vÃ  quÃ¢n cá»� pháº£i bá»‹ trá»Ÿ vá»� vá»‹ trÃ­
											// cÅ©
											P1.checkKing(true);
											end_move = false;
											if (P1.checkEmenyGameOver(P2)) {
												Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x)
														+ Integer.toString(newP.y);
												GameOver();
												can_send = true;
											} else {
												Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x)
														+ Integer.toString(newP.y);
												CheckStatus();
												can_send = true;
											}
										}
										if (end_move) {

											Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x)
													+ Integer.toString(newP.y);
											ChangeTurn();
											can_send = true;
										}
									}
								}
							}
						}
						P2.SetInHand(-1);
						repaint();
						if (can_send && ((isThis_is_Server() || isThis_is_Client()))) {
							// thá»±c hiá»‡n send dá»¯ liá»‡u
							// Send_move();
							Send_move();
						}
						if (isGameOver()) {
							System.out.println("game over 2");
							JOptionPane.showConfirmDialog(getParent(), "Check Mate\n Black won the game", "Game Over",
									JOptionPane.DEFAULT_OPTION);
						}
					}
				}
				// sau khi mouse release, doi turn image
//				if (isThis_is_Server() || isLocal()) {
//					if (players_turn == 1) {
//						player_turn_1.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
//						player_turn_2.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
//					} else {
//						player_turn_1.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
//						player_turn_2.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
//					}
//				}

				//if (isThis_is_Client()) {
//					if (players_turn == 1) {
//						player_turn_1.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
//						player_turn_2.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
//
//					} else {
//						player_turn_1.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
//						player_turn_2.setIcon(new ImageIcon(
//								getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
//					}
//				}
			}

		}
	}

	public boolean isGame_Started() {
		return game_Started;
	}

	public void setGame_Started(boolean game_Started) {
		this.game_Started = game_Started;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public boolean isGameOver() {
		return GameOver;
	}

	public void setGameOver(boolean gameOver) {
		GameOver = gameOver;
	}

	public boolean Board_getPosition(int x, int y) {
		if (!isGameOver() && isGame_Started()) {
			if ((isThis_is_Server() && players_turn == 1) || (isLocal()) || (isThis_is_Client() && players_turn == 2)) {
				int newX = x / Divide;
				int newY = y / Divide;
				newX++;
				newY++;
				if (newX > 8 || newY > 8 || newX < 1 || newY < 1) {
					repaint();
					return false;
				}
				if (players_turn == 1 && P1.GetInHand() == -1) // player1
				{
					for (int i = 17; i <= 32; i++) {
						Point p = P1.returnPosition(i);
						if (p.x == newX && p.y == newY) {
							P1.SetInHand(i);
							handle_when_click_Piece(x, y);
							return true;
						}
					}
				} else if (players_turn == 2 && P2.GetInHand() == -1) {
					for (int i = 1; i <= 16; i++) {
						Point p = P2.returnPosition(i);
						if (p.x == newX && p.y == newY) {
							P2.SetInHand(i);
							handle_when_click_Piece(x, y);
							return true;
						}
					}
				} else if (players_turn == 1 && P1.GetInHand() != -1) {
					handle_when_click_Piece(x, y);
					return true;
				} else if (players_turn == 2 && P2.GetInHand() != -1) {
					handle_when_click_Piece(x, y);
					return true;
				}
				P1.SetInHand(-1);
				P2.SetInHand(-1);
				// this.move=0;
				return false;
			}
		}
		return false;
	}

	// Ä‘á»•i vá»‹ trÃ­ (x,y) khi nhan 1 quan co sang pixel sang pixel
	public boolean handle_when_click_Piece(int x, int y) {
		if (players_turn == 1 && P1.GetInHand() != -1) {
			P1.changePixel(x, y, P1.GetInHand());
			return true;
		} else if (players_turn == 2 && P2.GetInHand() != -1) {
			P2.changePixel(x, y, P2.GetInHand());
			return true;
		}
		return false;
	}

	// doi 1 row sang toa do X
	private int rowToX(int row) {
		int myX;
		int height = this.getHeight();
		myX = (row * height / 8) - Divide;
		return myX;
	}

	// Ä‘á»•i cá»™t sang tá»�a Ä‘á»™ Y
	private int colToY(int column) {
		int myY;
		int width = getWidth();
		myY = (column * width / 8) - Divide;
		return myY;
	}

	public class Mouse_when_Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (controll_Game_Type(x, y)) {
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(!isGameOver())
			{
				if(players_turn==2)
				{
					
					Random random= new Random();
					boolean endMoved=false;
					int togo=0;
					int a=0;
					int b=0;
					
					while(!endMoved)
					{
						
						Point end= chooseEndPoint();
						int piece= choosePieces();
						 a= random.nextInt((8-1)+1)+1;
						 b= random.nextInt((8-1)+1)+1;
						endMoved=endMoved(piece,new Point(a,b));
					}
					
					repaint();
					return;
				}
			}
			return;
		}
	}
	public boolean endMoved(int presentInHand,Point presentPoint)
	{
		Point newP= new Point();
		Point samePosition;
		boolean end_move=true;
		if (isLocal() || isThis_is_Client()) {
			// chá»— nÃ y Ä‘á»ƒ check cÃ³ pháº£i lÃ  Ä‘Ã¡nh local hoáº·c Ä‘Ã¡nh
			// online hÃ¬nh thá»©c client
			//newP = P2.getPixelPoint(P2.GetInHand());
			//newP.x = newP.x / Divide;
			//newP.y = newP.y / Divide;
			P2.SetInHand(presentInHand);
			newP=presentPoint;
			boolean king1 = false;
			Point oldP = P2.returnOldPosition(P2.GetInHand());
			Point presentP = P2.returnPosition(P2.GetInHand());
			// set the seen of the Pawn - black
			if (P2.GetInHand() < 17 && P2.GetInHand() > 8) {
				for (int i = 17; i < 33; i++) {
					samePosition = P1.returnPosition(i);
					if (samePosition.x == newP.x && samePosition.y == newP.y) {
						if (P2.setSeentoPawns(P2.GetInHand(), samePosition)) {
							break;
						}
					}
				}
			}
			if (!(newP.x == presentP.x && newP.y == presentP.y)) {
				if (P2.checkMove(newP, P2.GetInHand())) {
					boolean flag = false;
					for (int i = 1; i <= 32; i++) {
						if (P2.GetInHand() != i) {
							// quÃ¢n chá»�n khÃ´ng pháº£i quÃ¢n Ä‘Æ°á»£c duyá»‡t
							if (i < 17) {
								flag = P2.checkWay(newP, P2.returnPosition(i), P2.GetInHand());

							} else {
								flag = P2.checkWay(newP, P1.returnPosition(i), P2.GetInHand());
							}
							if (flag == true) {
								break;
							}
						}
					}
					for (int i = 1; i <= 16 && !flag; i++) {
						if (P2.GetInHand() != i) {
							if (flag == false) {
								samePosition = P2.returnPosition(i);
								if (newP.x == samePosition.x && newP.y == samePosition.y) {
									flag = true;
									break;
								}
							}
						}
						if (flag == true) {
							break;
						}
					}
					if (!flag) {
						Point king_Position_player2 = P2.returnPosition(8);
						Point myOldP = new Point();
						Point oldTempP = P2.returnPosition(P2.GetInHand());
						myOldP.x = oldTempP.x;
						myOldP.y = oldTempP.y;
						Point other = new Point();
						Point f = new Point();
						boolean kill = false;
						end_move = true;
						int killed = -1;
						for (int k = 17; k < 33; k++) {
							other = P1.returnPosition(k);
							if (newP.x == other.x && newP.y == other.y) {

								int inHand = P2.GetInHand();

								if (inHand > 8 && P2.returnsoliderSeen(inHand)) {
									kill = true;

									other = P1.returnPosition(k);

									f.x = other.x;
									f.y = other.y;

									P1.killedPiece(k);
								} else if (inHand <= 8) {
									kill = true;

									other = P1.returnPosition(k);

									f.x = other.x;
									f.y = other.y;
									P1.killedPiece(k);
								} else {
									end_move = false;
									P2.changePosition(myOldP, inHand);
								}

								killed = k;
								break;

							}

						}
						// Ä‘á»“ng nghÄ©a vá»›i king2 =true;
						if (end_move) {
							// khÃºc nÃ y lÃ  Ä‘Ã£ káº¿t thÃºc lÆ°á»£t P2
							P2.changePosition(newP, P2.GetInHand());

							if (P1.checkEmenyGameOver(P2)) {
								// neu nhu P2 game over
								JOptionPane.showConfirmDialog(getParent(),
										"Check Mate\n Black won the game", "Game Over",
										JOptionPane.DEFAULT_OPTION);
								return true;
							}

							if (P2.GetInHand() >= 9 && P2.GetInHand() <= 16) {
								Point pawn_position1 = P2.returnPosition(P2.GetInHand());
								// check tá»‘t Ä‘Ã£ di chuyá»ƒn thÃ nh cÃ´ng
								// chÆ°a

								if (pawn_position1.y != 2) {
									P2.moved_Success(P2.GetInHand());
								}
								// náº¿u lÃ  quÃ¢n tá»‘t thÃ¬ check tiáº¿p cÃ³
								// náº±m á»Ÿ vá»‹ trÃ­ Ä‘Æ°á»£c sáº¯c phong Háº­u
								// khÃ´ng

								if (pawn_position1.y == 8) {
									P2.honor_Queen(P2.GetInHand());
								}
							}
						}
						P2.checkKing(false);
						if (P2.see_EnemyKingIsChecked(P1)) {
							// náº¿u king bá»‹ check thÃ¬ khÃ´ng Ä‘Æ°á»£c di
							// chuyá»ƒn
							// vÃ  quÃ¢n cá»� pháº£i bá»‹ trá»Ÿ vá»� vá»‹ trÃ­ cÅ©
							P2.changePosition(myOldP, P2.GetInHand());
							P2.checkKing(true);
							end_move = false;
						}
						if (kill && P2.returncheckKing()) {
							P1.changePosition(f, killed);
						}
						if (P2.returncheckKing()) {
							P2.changePosition(myOldP, P2.GetInHand());
						}
						if (!P2.returncheckKing()) {
							// mean: di chuyen mÃ  King2 khÃ´ng bi
							// chiáº¿u
							if (P1.see_EnemyKingIsChecked(P2)) {
								// náº¿u king 1 bá»‹ check thÃ¬ khÃ´ng
								// Ä‘Æ°á»£c di chuyá»ƒn
								// vÃ  quÃ¢n cá»� pháº£i bá»‹ trá»Ÿ vá»� vá»‹ trÃ­
								// cÅ©
								P1.checkKing(true);
								end_move = false;
								if (P1.checkEmenyGameOver(P2)) {
									Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x)
											+ Integer.toString(newP.y);
									GameOver();
									//can_send = true;
								} else {
									Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x)
											+ Integer.toString(newP.y);
									CheckStatus();
									//can_send = true;
								}
							}
							if (end_move) {

								Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x)
										+ Integer.toString(newP.y);
								ChangeTurn();
								//return true;
								//can_send = true;
							}
						}
					}
				}
				
			}
			P2.SetInHand(-1);
			repaint();
			if (((isThis_is_Server() || isThis_is_Client()))) {
				// thá»±c hiá»‡n send dá»¯ liá»‡u
				// Send_move();
				Send_move();
			}
			if (isGameOver()) {
				System.out.println("game over 2");
				JOptionPane.showConfirmDialog(getParent(), "Check Mate\n Black won the game", "Game Over",
						JOptionPane.DEFAULT_OPTION);
				return true;
			}
			if(end_move)
			{
				return true;
			}else
				return false;
		}
		return false;
	}
	public Point chooseEndPoint() {
		Random random= new Random();
		
		return null;
	}

	public int choosePieces() {
		Random random= new Random();
		List allPieces= getAllPieceComputer();
		int piece= random.nextInt(((allPieces.size()-1)-0)+1)+0;
		return (int) allPieces.get(piece);
	}

	private List getAllPieceComputer() {
		// TODO Auto-generated method stub
		List allPieces= new ArrayList<Integer>();
		for(int i=1;i<=16;i++)
		{
			Point temp= P2.returnPosition(i);
			if(temp.x==20 && temp.y==20)
			{
				continue;
			}
			allPieces.add(i);
		}
		return allPieces;
	}

	

	// Ä‘iá»�u khiá»ƒn loáº¡i game nÃ o vÃ­ dá»¥ khi nÃ o lÃ  lÆ°á»£t player 1 Ä‘i, khi nÃ o Ä‘áº¿n
	// player 2, khi nÃ o
	public boolean controll_Game_Type(int x, int y) {
		if ((isThis_is_Server() == true || isThis_is_Client() == true) && isGame_Started()) {
			if (isThis_is_Server() && players_turn == 1) {
				return Board_getPosition(x, y);
			} else if (isThis_is_Client() && players_turn == 2) {
				return Board_getPosition(x, y);
			} else
				return false;
		} else {
			return Board_getPosition(x, y);
		}
	}

	public void ChangeTurn() {
		if (players_turn == 1) {
			players_turn = 2;

		} else if (players_turn == 2) {
			players_turn = 1;

		}
	}

	private void CheckStatus() {
		if (players_turn == 1) {

			players_turn = 2;
		} else if (players_turn == 2) {

			players_turn = 1;
		}

		// myStatus.changeStatus(" Check! ");
	}

	public void Send_move() {

		out.print(Box);
		out.print("\r\n");
		out.flush();

	}

	private void GameOver()

	{
		this.setGameOver(true);
	}

	class TranferData_Thread extends Thread {
		public synchronized void run() {
			while (true) {
				// láº¯ng nghe
				try {
					Box = in.readLine(); // Ä‘á»�c dá»¯ liá»‡u

				} catch (IOException ex) {
					ex.printStackTrace();
					System.out.println("ERROR when i handle Thread method");
				}
				if (Box != null) {
					int newInHand = Integer.parseInt(Box);
					int newX = Integer.parseInt(Box);
					int newY = Integer.parseInt(Box);
					newInHand /= 100;
					newX -= (newInHand * 100);
					newX /= 10;
					newY -= (newInHand * 100) + (newX * 10);
					if (players_turn == 1) {
						P1.SetInHand(newInHand);
						players_turn = 2;

						P1.changePosition(new Point(newX, newY), newInHand); // set
																				// vi
																				// tri
																				// cho
																				// quan
																				// co
						P2.killedPiece(P1.getPiece_Enemy_Already_There(new Point(newX, newY), P2));
						P2.checkKing(false);
						if (P2.see_EnemyKingIsChecked(P1)) {
							// náº¿u P1 di chuyá»ƒn mÃ  lÃ m cho King cá»§a P1 bá»‹ check
							// thÃ¬
							// khÃ´ng Ä‘Æ°á»£c di chuyá»ƒn
							P2.checkKing(true); // mean P2 chiáº¿u Vua P1
							if (P2.checkEmenyGameOver(P1)) {
								// mean :
								System.out.println("Game over P1 win!!!");
								GameOver();
							} else {

								CheckStatusByInternet();
							}
						} else {
							ChangeTurnByInternet();
						}
						P1.SetInHand(-1);// ket thuc luot P1

					} else {
						// mean: den luot P2
						P2.SetInHand(newInHand);
						Point newP = new Point(newX, newY);
						P2.changePosition(newP, newInHand);
						P1.killedPiece(P2.getPiece_Enemy_Already_There(newP, P1));
						players_turn = 1;

						P1.checkKing(false);
						if (P1.see_EnemyKingIsChecked(P2)) {
							// mean: if
							P1.checkKing(true);
							if (P1.checkEmenyGameOver(P2)) {
								System.out.println("Game Over P2 win !!!");
								GameOver();
							} else {
								CheckStatusByInternet();
							}
						} else {
							ChangeTurnByInternet();
						}
						P2.SetInHand(-1);
					}

					// if(isThis_is_Server())
					// {
					// //client
					// if(players_turn==1)
					// {
					// player_turn_1.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
					// player_turn_2.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
					// }
					// else
					// {
					// player_turn_1.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
					// player_turn_2.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
					// }
					// }
					//
					// if(isThis_is_Client())
					// {
					// //System.out.println("client");
					// if(players_turn==1)
					// {
					// player_turn_1.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
					// player_turn_2.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
					//
					// }
					// else
					// {
					// player_turn_1.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
					// player_turn_2.setIcon(new
					// ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
					// }
					// }

					repaint();
				}

			}
		}
	}

	public void CheckStatusByInternet() {
		// TODO Auto-generated method stub

	}

	public void ChangeTurnByInternet() {
		// TODO Auto-generated method stub
		if (players_turn == 2) {
		}

		else if (players_turn == 1) {
		}
	}

	public String getMyIp_Address() {
		return myIp_Address;
	}

	public void setMyIp_Address(String myIp_Address) {
		this.myIp_Address = myIp_Address;
	}

	public String getMyPort() {
		return myPort;
	}

	public void setMyPort(String myPort) {
		this.myPort = myPort;
	}

	public boolean isThis_is_Server() {
		return this_is_Server;
	}

	public void setThis_is_Server(boolean this_is_Server) {
		this.this_is_Server = this_is_Server;
	}

	public boolean isThis_is_Client() {
		return this_is_Client;
	}

	public void setThis_is_Client(boolean this_is_Client) {
		this.this_is_Client = this_is_Client;
	}
}
