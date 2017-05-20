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
	private void GameOver()
	
	{
		this.setGameOver(true);
	}
}
