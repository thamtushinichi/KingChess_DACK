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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


import ChessFrame.MenuFrame;
import main.MainFrame;

import players.Player1;
import players.Player2;

public class MainGameBoardPane extends JPanel{
	private Player1 P1= new Player1();
	private Player2 P2= new Player2();
	private final int Divide= 600/8; // 600 là kích thước bề ngang hoặc rộng của bàn cờ, 8 là số ô cờ
	//chúng ta tính được cạnh của 1 ô cờ là bao nhiêu , đáp án: 75 pixel
	private int move=0;
	private Rectangle2D rec; // 1 ô cờ là 1 Rectangle2D
	private short players_turn=1;
	private boolean GameOver=false; //check game over
	private boolean local=true;//check xem chế độ chơi là local 2 người chơi ko
	private boolean game_Started=true;//check xem game đã bắt đầu chưa
	private String Box; //đóng gói dữ liệu để gửi đi
	private PrintWriter out; //ghi dữ liệu
	private boolean this_is_Server; //đây là server
	private boolean this_is_Client; //đây là client
	//Set up ip và port there
	private String myIp_Address;
	private String myPort;
	//init socket
	private ServerSocket serverSocket;
	private Socket Sock; //init Sock giao tiếp giữa 2 cổng
	private BufferedReader in; // đọc dữ liệu
	//private Recv_Thread rec_from;
	private TranferData_Thread tranfer_Data_Thread;
	
	private JDialog dialog;
	private JLabel player_turn_1, player_turn_2;
	private JPanel turn_pane;
//	private ImageIcon black_turn_img = new ImageIcon(getClass().getClassLoader().getResource("resources/images/blackturn.png"));
//	private ImageIcon white_turn_img = new ImageIcon(getClass().getClassLoader().getResource("resources/images/whiteturn.png"));
	private JTextField txt_turn=new JTextField(20);
	
	public void start_As_Server()
	{
		tranfer_Data_Thread= new TranferData_Thread();
		setGame_Started(false);
		
		initialization_Again_Parameter();
		int port = Integer.parseInt(myPort);
		//handle button Create room
		try {
			InetAddress thisIp=InetAddress.getByName(myIp_Address);
			serverSocket= new ServerSocket(port,1,thisIp);
			
			Thread Server_Thread = new Thread(new Runnable() {
				public synchronized void run() {
					try {
						System.out.println("truoc khi cho");

						Sock= serverSocket.accept();
						dialog.setVisible(false);
						player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
						player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
						turn_pane.add(player_turn_2);
						turn_pane.add(player_turn_1);
						
						System.out.println("sau  khi cho");
						in= new BufferedReader(new InputStreamReader(Sock.getInputStream()));
						out =new PrintWriter(Sock.getOutputStream());
						tranfer_Data_Thread.start();
						game_Started=true;
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
		      dialog.setLocation(new Point(520,280));
		      dialog.setPreferredSize(new Dimension(400,60));
		      
		      
		      ImageIcon loading = new ImageIcon(getClass().getClassLoader().getResource("resources/images/waiting.gif"));
		      JPanel panel = new JPanel(new BorderLayout());
		      panel.setBackground(Color.black);
		      panel.add(new JLabel(loading, JLabel.CENTER));
		      //panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
		      dialog.add(panel);
		      
		      //dialog.setLocationRelativeTo(getParent());
		      dialog.setResizable(false);
		      dialog.setUndecorated(true);
		      //dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		      dialog.pack();
		      dialog.setVisible(true);
			

			//kết thúc việc start server và server lúc này đang chờ kết nối
			

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR : serverSocket");
		}
		setLocal(false);
		repaint();
		
	}
	public void start_As_Client()
	{
		tranfer_Data_Thread= new TranferData_Thread();
		setGame_Started(false);
		
		initialization_Again_Parameter();
		int port = Integer.parseInt(myPort);
		setLocal(false);
		//handle button Create room
		try {
			Sock = new Socket(myIp_Address,port);
			player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
			player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
			turn_pane.add(player_turn_1);
			turn_pane.add(player_turn_2);
			in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
			out = new PrintWriter(Sock.getOutputStream());
			
			
			
			tranfer_Data_Thread.start();
			game_Started=true;
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR : serverSocket");
		}
		
		repaint();
	}
	private void initialization_Again_Parameter() {
		// TODO Auto-generated method stub
		P1= new Player1();
		P2=new Player2();
		move=0;
		players_turn=1;
		GameOver=false;
		local=true;
		
		repaint();
		
	}
	
	public JPanel getTurnPane() {
		return turn_pane;
	}
	
	public MainGameBoardPane()
	{
		
		
		player_turn_1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
		player_turn_2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
		
		turn_pane = new JPanel(new GridLayout(2, 1));
		
		
		
//		setSize(new Dimension(600, 600));
		setPreferredSize(new Dimension(600,600));
		//setLocation(2,1);
		Mouse_when_Move mouse_Drag_And_Drop= new Mouse_when_Move();
		Mouse_Here mouseHereEvent= new Mouse_Here();
		addMouseMotionListener(mouse_Drag_And_Drop);
		addMouseListener(mouseHereEvent);
		setLayout(null);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		int width=600;
		int height = 600;
		// draw board
		for(int i=0;i<8;i=i+2)
		{
			for(int j=0;j<8;j=j+2)
			{
				g2.setColor(Color.GRAY);
				rec= new Rectangle2D.Double(j*width / 8,(1+i) * width /8,Divide,Divide);
				g2.fill(rec);
				rec= new Rectangle2D.Double((1+j) * width /8,i*width/8,Divide,Divide);
				g2.fill(rec);
			}
		}
		// đặt quân cờ vào vị trí các ô cờ
		Point positionP;
		int postX;
		int postY;
		Image image;
		for(int i=1;i<=32;i++)
		{
			if(i<17)
			{
				if(i==P2.GetInHand())
				{
					positionP=P2.getPixelPoint(i);
					
				} 
				else {
					positionP=P2.returnPosition(i);
					
				}
				image=P2.returnIconImage(i);
			}
			else
			{
				if(i==P1.GetInHand())
				{
					positionP= P1.getPixelPoint(i);
				
				}
				else
				{
					positionP=P1.returnPosition(i);
				}
				image=P1.returnIconImage(i);
			}
			//bat dau ve
			if(i== P1.GetInHand())
			{
				g2.drawImage(image, positionP.x-25, positionP.y-25, Divide-40, Divide-12, this);
				
			}
			else if(i==P2.GetInHand())
			{
				g2.drawImage(image, positionP.x-25, positionP.y-25, Divide-40, Divide-12, this);
			}
			else
			{
				postX=rowToX(positionP.x);
				postY=colToY(positionP.y);
				g2.drawImage(image, postX+20, postY+4, Divide -40, Divide-12, this);
				
			}
		}
	}
	private class Mouse_Here implements MouseListener{

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
			
			//khi nhả quân cờ ra thì chạy hàm này
			boolean can_send=false; //dung de send sau khi danh 1 quan co sang cho doi phuong
			if(!isGameOver())
			{
				Point newP;
				Point samePosition;
				if(P1.GetInHand()!=-1)
				{
					
					
					newP= P1.getPixelPoint(P1.GetInHand());
					newP.x= newP.x/Divide;
					newP.y=newP.y/Divide;
					newP.x++;
					newP.y++;
					int other_Index;//vi tri khac , vi tri sau khi tha chuot ra
					Point oldP = P1.returnOldPosition(P1.GetInHand());
					int x= oldP.x;
					int y=oldP.y;
					Point presentP= P1.returnPosition(P1.GetInHand());
					/////////////////______________________________________
					if(isLocal() || isThis_is_Server())
					{
						//con dung de check dang la server 
						if(P1.GetInHand() <33 && P1.GetInHand() >24)
						{
							//Inhand la quan địch
							for(int i=1;i<17;i++)
							{
								samePosition=P2.returnPosition(i);
								if(samePosition.x== newP.x && samePosition.y ==newP.y)
								{
									if(P1.setSeentoPawns(P1.GetInHand(), samePosition))
									{
										break;
									}
								}
							}
						}
						if(!(newP.x==presentP.x && newP.y == presentP.y))
						{
							if(P1.checkMove(newP,P1.GetInHand()))
							{
								int aa=P1.GetInHand();
								//check nếu như di chuyển đến ô không hợp lệ
								boolean flag =false;
								for(int i=1;i<=32;i++)
								{
									if(P1.GetInHand()!=i)
									{
										//check nếu ở đây có quân cờ
										if(i<17)
										{
											//trả về nếu như trên đường đi gặp cản trở và không thể di chuyển
											flag= P1.checkWay(newP, P2.returnPosition(i), P1.GetInHand());
										}
										else
										{
											flag = P1.checkWay(newP, P1.returnPosition(i), P1.GetInHand());
										}
										if(flag==true)
										{
											break; //có quân cờ ở đây
										}
									}
								}
								//________________________
								if(!flag && P1.piece_Already_There(newP))
								{
									//nếu như di chuyển hợp lệ và check có quân cờ đối phương tại vị trí sắp đến ko
									// check nếu vị trí mới có chiếu vua Black không
									boolean king2=true;
									Point myOld= new Point();
									Point myOldTemp= P1.returnPosition(P1.GetInHand());
									myOld.x=myOldTemp.x;
									myOld.y=myOldTemp.y;
									Point other = new Point();
									Point f = new Point();
									boolean kill=false;
									int killed =-1;
									boolean end_move=true;
									//bắt đầu check king
									for(int k=1;k<17;k++)
									{
										other = P2.returnPosition(k);
										if(newP.x==other.x && newP.y ==other.y)
										{
											int inHand=P1.GetInHand();
											if(inHand > 24 && P1.returnsoliderSeen(inHand))
											{
												kill=true;//có quân cờ đã chết
												f.x=other.x;
												f.y=other.y;
												P2.killedPiece(k);//player 2 giet quan co co gia tri k do di
											}
											else if(inHand<=24)
											{
												kill=true;
												f.x=other.x;
												f.y=other.y;
												P2.killedPiece(k);
											}
											else
											{
												P1.changePosition(myOld, inHand);
												end_move=false;
												break;
											}
											killed=k;
											break;
										}
									}
									if(end_move)
									{
										P1.changePosition(newP,P1.GetInHand()); //kết thúc sự di chuyển P1
										if(P2.checkEmenyGameOver(P1))
										{
											//neu nhu P2 game over
											JOptionPane.showConfirmDialog(getParent(), "Check Mate\n White won the game", "Game Over",
													JOptionPane.DEFAULT_OPTION);
											return;
										}
										
										if(P1.GetInHand()>=25 && P1.GetInHand()<=32)
										{
											Point pawn_position= P1.returnPosition(P1.GetInHand());
											// check tốt đã di chuyển thành công chưa
											if(pawn_position.y !=7)
											{
												P1.moved_Success(P1.GetInHand());
											}
											// nếu là quân tốt thì check tiếp có nằm ở vị trí được sắc phong Hậu không
											
											if(pawn_position.y ==1)
											{
												P1.honor_Queen(P1.GetInHand());
											}
										}
									}
										P1.checkKing(false);//ko check king nữa
										if(P1.see_EnemyKingIsChecked(P2))
										{
											//nếu vua của tôi bị chiếu nếu tôi di chuyen, vì vậy
											//tôi không thể di chuyển quân cờ và phải return back to old position
											P1.changePosition(myOld, P1.GetInHand());
											P1.checkKing(true);//set king bị check là true
											end_move=false;//chưa kết thúc được
											
										}
										if(kill && P1.returncheckKing())
										{
											P2.changePosition(f, killed);
											
										}
										if(!P1.returncheckKing())
										{
											if(P2.see_EnemyKingIsChecked(P1))
											{
												//nếu như vua đối phương bị check
												P2.checkKing(true);
												end_move=false;
												//chưa kết thúc lượt được
												if(P2.checkEmenyGameOver(P1))
												{
													//nếu như Player 1 game over
													GameOver();
													Box=Integer.toString(P2.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
													can_send=true;//co the gui di qua internet
													
												}
												else
												{
													Box=Integer.toString(P1.GetInHand())+Integer.toString(newP.x)+Integer.toString(newP.y);
													CheckStatus();
													can_send=true;
												}
											}
//											else
//											{
//												P2.checkKing(false);
//												end_move=false;
//												if(P2.checkEmenyGameOver(P1))
//												{
//													Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
//													GameOver();
//													can_send = true;
//												}
//												else
//												{
//													Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
//													CheckStatus();
//													can_send=true;
//												}
//											}
											if(end_move)
											{
												
												Box=Integer.toString(P1.GetInHand()) +Integer.toString(newP.x)+Integer.toString(newP.y);
												ChangeTurn();
												can_send=true;
												
											}
										}
								}
							}
						}
						//khi kết thúc lượt thì check
						
							
						P1.SetInHand(-1);
						repaint();
						if(can_send && ((isThis_is_Server() || isThis_is_Client())) )
						{
							// chỗ này để thực hiện việc send đi dữ liệu 
							//chạy hàm Send_move(); để send dữ liệu
							Send_move();
							
						}
						if(isGameOver())
						{
							System.out.println("game over 1");
							JOptionPane.showConfirmDialog(getParent(), "Check Mate\n White won the game", "Game Over",
									JOptionPane.DEFAULT_OPTION);
							
						}
					}
				}
				
				
				else if(P2.GetInHand()!=-1)
				{
					
					if(isLocal() || isThis_is_Client())
					{
						// chỗ này để check có phải là đánh local hoặc đánh online hình thức client
						newP= P2.getPixelPoint(P2.GetInHand());
						newP.x=newP.x/Divide;
						newP.y=newP.y/Divide;
						newP.x++;
						newP.y++;
						boolean king1= false;
						Point oldP =P2.returnOldPosition(P2.GetInHand());
						Point presentP=P2.returnPosition(P2.GetInHand());
						//set the seen of the Pawn - black
						if(P2.GetInHand() <17 && P2.GetInHand()>8)
						{
							for(int i=17;i<33;i++)
							{
								samePosition=P1.returnPosition(i);
								if(samePosition.x == newP.x && samePosition.y==newP.y)
								{
									if(P2.setSeentoPawns(P2.GetInHand(), samePosition))
									{
										break;
									}
								}
							}
						}
						if(!(newP.x == presentP.x && newP.y == presentP.y))
						{
							if(P2.checkMove(newP, P2.GetInHand()))
							{
								boolean flag =false;
								for(int i=1;i<=32;i++)
								{
									if(P2.GetInHand() !=i)
									{
										// quân chọn không phải quân được duyệt
										if(i<17)
										{
											flag=P2.checkWay(newP, P2.returnPosition(i), P2.GetInHand());
											
										}
										else
										{
											flag=P2.checkWay(newP, P1.returnPosition(i), P2.GetInHand());
										}
										if(flag==true)
										{
											break;
										}
									}
								}
								for(int i=1;i<=16 && !flag;i++)
								{
									if(P2.GetInHand() !=i)
									{
										if(flag==false)
										{
											samePosition=P2.returnPosition(i);
											if(newP.x==samePosition.x && newP.y == samePosition.y)
											{
												flag=true;
												break;
											}
										}
									}
									if(flag==true)
									{
										break;
									}
								}
								if(!flag)
								{
									Point king_Position_player2=P2.returnPosition(8);
									Point myOldP= new Point();
									Point oldTempP= P2.returnPosition(P2.GetInHand());
									myOldP.x=oldTempP.x;
									myOldP.y=oldTempP.y;
									Point other = new Point();
									Point f = new Point();
									boolean kill=false;
									boolean end_move=true;
									int killed =-1;
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
									//đồng nghĩa với king2 =true;
									if(end_move)
									{
										//khúc này là đã kết thúc lượt P2
										P2.changePosition(newP, P2.GetInHand());
										
										if(P1.checkEmenyGameOver(P2))
										{
											//neu nhu P2 game over
											JOptionPane.showConfirmDialog(getParent(), "Check Mate\n Black won the game", "Game Over",
													JOptionPane.DEFAULT_OPTION);
											return;
										}
										
										if(P2.GetInHand()>=9 && P2.GetInHand()<=16)
										{
											Point pawn_position1= P2.returnPosition(P2.GetInHand());
											// check tốt đã di chuyển thành công chưa
											
											if(pawn_position1.y !=2)
											{
												P2.moved_Success(P2.GetInHand());
											}
											// nếu là quân tốt thì check tiếp có nằm ở vị trí được sắc phong Hậu không
											
											if(pawn_position1.y ==8)
											{
												P2.honor_Queen(P2.GetInHand());
											}
										}
									}
									P2.checkKing(false);
									if(P2.see_EnemyKingIsChecked(P1))
									{
										// nếu king bị check thì không được di chuyển
										//và quân cờ phải bị trở về vị trí cũ
										P2.changePosition(myOldP, P2.GetInHand());
										P2.checkKing(true);
										end_move=false;
									}
									if(kill && P2.returncheckKing())
									{
										P1.changePosition(f, killed);
									}
									if(P2.returncheckKing())
									{
										P2.changePosition(myOldP, P2.GetInHand());
									}
									if(!P2.returncheckKing())
									{
										//mean: di chuyen mà King2 không bi chiếu
										if(P1.see_EnemyKingIsChecked(P2))
										{
											// nếu king 1 bị check thì không được di chuyển
											//và quân cờ phải bị trở về vị trí cũ
											P1.checkKing(true);
											end_move=false;
											if(P1.checkEmenyGameOver(P2))
											{
												Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
												GameOver();
												can_send = true;
											}
											else
											{
												Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
												CheckStatus();
												can_send=true;
											}
										}
										//check vua game over ma ko can phai chieu
//										else
//										{
//											P1.checkKing(false);
//											end_move=false;
//											if(P1.checkEmenyGameOver(P2))
//											{
//												Box = Integer.toString(P1.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
//												GameOver();
//												can_send = true;
//											}
//											else
//											{
//												Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
//												CheckStatus();
//												can_send=true;
//											}
//										}
											
										if(end_move)
										{
											
											Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
											ChangeTurn();
											can_send = true;
										}
									}
								}
							}
							
						}
						P2.SetInHand(-1);
						repaint();
						if(can_send && ((isThis_is_Server() || isThis_is_Client())))
						{
							//thực hiện send dữ liệu
							//Send_move();
							Send_move();
						}
						if(isGameOver())
						{
							System.out.println("game over 2");
							JOptionPane.showConfirmDialog(getParent(), "Check Mate\n Black won the game", "Game Over",
									JOptionPane.DEFAULT_OPTION);
							
						}
					}
				}
				// sau khi mouse release, doi turn image 
				if(isThis_is_Server() || isLocal())
				{
					if(players_turn==1)
					{
						player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
						player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
					}
					else
					{
						player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
						player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
					}
				}
				
				if(isThis_is_Client())
				{
					if(players_turn==1)
					{
						player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
						player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
						
					}
					else
					{
						player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
						player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
					}
				}
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
	
	public boolean Board_getPosition(int x,int y){
		if(!isGameOver() && isGame_Started())
		{
			if((isThis_is_Server() &&players_turn==1)||(isLocal())||(isThis_is_Client() && players_turn==2))
			{
				int newX= x/Divide;
				int newY= y/Divide;
				newX++;
				newY++;
				if(newX>8 || newY>8 || newX<1||newY<1)
				{
					repaint();
					return false;
				}
				if(players_turn==1 && P1.GetInHand()==-1) //player1
				{
					for(int i=17;i<=32;i++)
					{
						Point p = P1.returnPosition(i);
						if(p.x==newX && p.y ==newY)
						{
							P1.SetInHand(i);
							handle_when_click_Piece(x, y);
							return true;
						}
					}
				} else if(players_turn==2 && P2.GetInHand()==-1)
				{
					for(int i=1;i<=16;i++)
					{
						Point p = P2.returnPosition(i);
						if(p.x==newX && p.y ==newY)
						{
							P2.SetInHand(i);
							handle_when_click_Piece(x, y);
							return true;
						}
					}
				}
				else if(players_turn==1 && P1.GetInHand() !=-1)
				{
					handle_when_click_Piece(x, y);
					return true;
				}
				else if(players_turn==2 && P2.GetInHand() != -1)
				{
					handle_when_click_Piece(x, y);
					return true;
				}
				P1.SetInHand(-1);
				this.move=0;
				return false;
			}
		}
		return false;
	}
	//đổi vị trí (x,y) khi nhan 1 quan co sang pixel sang pixel
	public boolean handle_when_click_Piece(int x,int y)
	{
		if(players_turn==1 && P1.GetInHand() != -1)
		{
			P1.changePixel(x, y, P1.GetInHand());
			return true;
		}
		else if(players_turn==2 && P2.GetInHand() != -1)
		{
			P2.changePixel(x, y, P2.GetInHand());
			return true;
		}
		return false;
	}
	//doi 1 row sang toa do X
	private int rowToX(int row)
	{
		int myX;
		int height= this.getHeight();
		myX=(row* height /8) - Divide;
		return myX;
	}
	//đổi cột sang tọa độ Y
	private int colToY(int column)
	{
		int myY;
		int width= getWidth();
		myY=(column * width /8) - Divide;
		return myY;
	}
	private class Mouse_when_Move implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			int x= e.getX();
			int y= e.getY();
			if(controll_Game_Type(x, y))
			{
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	//điều khiển loại game nào ví dụ khi nào là lượt player 1 đi, khi nào đến player 2, khi nào 
	public boolean controll_Game_Type(int x,int y)
	{
		if((isThis_is_Server()==true || isThis_is_Client()==true) && isGame_Started())
		{
			if(isThis_is_Server()&& players_turn==1)
			{
				return Board_getPosition(x, y);
			} else if(isThis_is_Client()&&players_turn==2)
			{
				return Board_getPosition(x, y);
			}
			else 
				return false;
		}
		else
		{
			return Board_getPosition(x,y);
		}
	}
	public void ChangeTurn()
	{
		if(players_turn==1)
		{
			players_turn=2;
			
		}
		else if(players_turn==2)
		{
			players_turn=1;
			
		}
	}
	private void CheckStatus() {
		if (players_turn == 1) {
			
			players_turn = 2;
		} else if (players_turn == 2) {
			
			players_turn = 1;
		}

		//myStatus.changeStatus(" Check! ");
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
	class TranferData_Thread extends Thread{
		public synchronized void run()
		{
			while(true)
			{
				// lắng nghe
				try
				{
					Box=in.readLine(); // đọc dữ liệu
					
					
				}catch(IOException ex)
				{
					ex.printStackTrace();
					System.out.println("ERROR when i handle Thread method");
				}
				if(Box!=null)
				{
					int newInHand=Integer.parseInt(Box);
					int newX= Integer.parseInt(Box);
					int newY=Integer.parseInt(Box);
					newInHand /= 100;
					newX -= (newInHand * 100);
					newX /= 10;
					newY -= (newInHand * 100) + (newX * 10);
					if(players_turn==1)
					{
						P1.SetInHand(newInHand);
						players_turn =2;
						
						
						P1.changePosition(new Point(newX,newY), newInHand); //set vi tri cho quan co
						P2.killedPiece(P1.getPiece_Enemy_Already_There(new Point(newX,newY), P2));
						P2.checkKing(false);
						if(P2.see_EnemyKingIsChecked(P1))
						{
							//nếu P1 di chuyển mà làm cho King của P1 bị check thì 
							//không được di chuyển
							P2.checkKing(true); // mean P2 chiếu Vua P1
							if(P2.checkEmenyGameOver(P1))
							{
								//mean : 
								System.out.println("Game over P1 win!!!");
								GameOver();
							}
							else
							{
								
								CheckStatusByInternet();
							}
						}
						else
						{
							ChangeTurnByInternet();
						}
						P1.SetInHand(-1);//ket thuc luot P1
						
					}
					else
					{
						//mean: den luot P2
						P2.SetInHand(newInHand);
						Point newP= new Point(newX,newY);
						P2.changePosition(newP, newInHand);
						P1.killedPiece(P2.getPiece_Enemy_Already_There(newP, P1));
						players_turn=1;
						
						
						P1.checkKing(false);
						if(P1.see_EnemyKingIsChecked(P2))
						{
							//mean: if 
							P1.checkKing(true);
							if(P1.checkEmenyGameOver(P2))
							{
								System.out.println("Game Over P2 win !!!");
								GameOver();
							}
							else
							{
								CheckStatusByInternet();
							}
						}
						else
						{
							ChangeTurnByInternet();
						}
						P2.SetInHand(-1);
					}
					
					if(isThis_is_Server())
					{
						//client
						if(players_turn==1)
						{
							player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
							player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
						}
						else
						{
							player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
							player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
						}
					}
					
					if(isThis_is_Client())
					{
						//System.out.println("client");
						if(players_turn==1)
						{
							player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_disable.png")));
							player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_enable.png")));
							
						}
						else
						{
							player_turn_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/yourturn_enable.png")));
							player_turn_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/enemyturn_disable.png")));
						}
					}
					
					
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
