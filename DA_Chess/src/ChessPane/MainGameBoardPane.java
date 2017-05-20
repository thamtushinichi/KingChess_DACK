package ChessPane;

import java.awt.Dimension;
import java.awt.Point;
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
	private boolean local=false;//check xem chế độ chơi là local 2 người chơi ko
	private boolean game_Started=false;//check xem game đã bắt đầu chưa
	public MainGameBoardPane()
	{
		setSize(new Dimension(600, 600));
		setLocation(3,10);
		//MousewhenMove mouse_Drag_And_Drop= new MousewhenMove();
		setLayout(null);
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
							
						}
					}
				}
			}
		}
	}
	//đổi vị trí
	public boolean 
}
