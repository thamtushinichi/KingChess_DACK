package ChessPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private PrintWriter out;
	public MainGameBoardPane()
	{
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
					if(isLocal())
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
										P1.changePosition(newP,P1.GetInHand()); //kết thúc sự di chuyển
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
						P1.SetInHand(-1);
						repaint();
						if(can_send )
						{
							// chỗ này để thực hiện việc send đi dữ liệu 
							//chạy hàm Send_move(); để send dữ liệu
							
						}
						if(isGameOver())
						{
							JOptionPane.showConfirmDialog(getParent(), "Check Mate\n White won the game", "Game Over",
									JOptionPane.DEFAULT_OPTION);
							System.out.println("game over 1");
						}
					}
				}
				
				
				else if(P2.GetInHand()!=-1)
				{
					if(isLocal())
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
										P2.changePosition(newP, P2.GetInHand());
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
										if(P1.see_EnemyKingIsChecked(P2))
										{
											// nếu king bị check thì không được di chuyển
											//và quân cờ phải bị trở về vị trí cũ
											P1.checkKing(true);
											end_move=false;
											if(P1.checkEmenyGameOver(P2))
											{
												Box = Integer.toString(P2.GetInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
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
						if(can_send)
						{
							//thực hiện send dữ liệu
							//Send_move();
						}
						if(isGameOver())
						{
							JOptionPane.showConfirmDialog(null, "Check Mate\n Black won the game", "Game Over",
									JOptionPane.DEFAULT_OPTION);
							System.out.println("game over 2");
						}
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
			if(players_turn==1||isLocal()||players_turn==2)
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
		if(isGame_Started())
		{
			if(players_turn==1)
			{
				return Board_getPosition(x, y);
			} else if(players_turn==2)
			{
				return  Board_getPosition(x, y);
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
			//myTool.add_to_History("White : " + P1.Tell_me_About_last_move());
			//myTool.change_to_Timer2();
		} else if (players_turn == 2) {

			players_turn = 1;
			//myTool.add_to_History("Black : " + P2.Tell_me_About_last_move());
			//myTool.change_to_Timer1();
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
}
