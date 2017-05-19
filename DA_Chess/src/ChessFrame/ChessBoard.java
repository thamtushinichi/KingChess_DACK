package ChessFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ChessBoard extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPieces;
	int xNew;
	int yNew;
	int xOld,yOld;
	int hasMove=0;
	// ham dung khoi tao
	public ChessBoard() {
		Dimension boardSize = new Dimension(600, 600);
		
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		// khoi tao cac o vuong ban co vua
		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);
			int row = (i / 8) % 2;
			// lay chan le
			if (row == 0) {
				// set mau cho moi o vuong
				 square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
			}
			else
			{
				 square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
			}
		}
		//sau khi khoi tao mau cho cac o vuong ta lam tiep gan quan co vao ban co
		//chay thuat toan load toan bo ban co
		 LoadChessBoard();
		
	}

	private void LoadChessBoard() {
		// TODO Auto-generated method stub
		//load toan bo ban co theo co dinh
		JLabel piece ;
		
        JPanel panel ;
        //load quan co Player 2 , tuc la nguoi nhin ban co tu tren xuong ( hoac co the la bot)
        for(int i=0;i<16;i++)
        {
        	switch(i)
        	{
        	case 0:
        	case 7:
        		//  vi tri quan Rook
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/br.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 1:
        	case 6:
        		//la quan Knight
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/bn.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 2:
        	case 5:
        		//vi tri quan Bishop
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/bb.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 3:
        		//vi tri quan hau : Queen
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/bq.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 4:
        		//vi tri quan Vua: King
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/bk.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        		default:
        			//vi tri quan tot trong 16 quan dau tien
        			piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/bp.gif")) );
                	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                    panel= (JPanel)chessBoard.getComponent(i);
                    panel.add(piece);
        			break;
        	}
        	
        }
        //load nguoi choi doi dien ( co the la player 1 hoac bot)
        for(int i=48;i<64;i++)
        {
        	switch(i)
        	{
        	case 56:
        	case 63:
        		//  vi tri quan Rook
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/wr.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 57:
        	case 62:
        		//la quan Knight
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/wn.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 58:
        	case 61:
        		//vi tri quan Bishop
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/wb.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 59:
        		//vi tri quan hau : Queen
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/wq.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        	case 60:
        		//vi tri quan Vua: King
        		piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/wk.gif")) );
            	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                panel= (JPanel)chessBoard.getComponent(i);
                panel.add(piece);
        		break;
        		default:
        			//vi tri quan tot trong 16 quan dau tien
        			piece= new JLabel(  new ImageIcon(getClass().getClassLoader().getResource("resources/images/wp.gif")) );
                	//phai tinh toan doi voi tung gia tri i thi phai cho ra gia tri quan co tuong ung
                    panel= (JPanel)chessBoard.getComponent(i);
                    panel.add(piece);
        			break;
        	}
        	
        }
	}


	public void mouseDragged(java.awt.event.MouseEvent em) {
		// TODO Auto-generated method stub
		//khi dang di chuyen chuot
		if(chessPieces==null) return;
		int x= em.getX()+xNew;
		int y= em.getY()+yNew;
		chessPieces.setLocation(x, y);
		System.out.println(x + " " + y);
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
        
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
		chessPieces=null;
		Component component = chessBoard.findComponentAt(e.getX(), e.getY());
		//khong phai la vi tri quan co
		if(component instanceof JPanel)
		{
			return;
		}
		
		Point parentLocation = component.getParent().getLocation(); // lay vi tri diem nhan
		
		xNew = parentLocation.x-e.getX();
		yNew= parentLocation.y-e.getY();
		chessPieces = (JLabel)component; //ep kieu ve JLabel 
		//set vi tri cua label
		chessPieces.setLocation(e.getX()+xNew,e.getY()+yNew);
		
		chessPieces.setSize(chessPieces.getWidth(),chessPieces.getHeight());
		
		//add vao layeredPane
		layeredPane.add(chessPieces,layeredPane.DRAG_LAYER);
		System.out.println("da toi day");
		System.out.println(xNew+" "+yNew+" "+parentLocation.x+" "+parentLocation.y+" "+chessPieces.getWidth()+" "+chessPieces.getHeight());
	}


	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		// sau khi nha chuot ra 1 o trong
				if(chessPieces==null)  return;
				//an di chessPieces 
				chessPieces.setVisible(false);
				//tim kiem component duoc tha'
				Component component = chessBoard.findComponentAt(e.getX(),e.getY());
				//neu ko phai la quan co thi
				if(component instanceof JLabel)
				{
					Container parent = component.getParent();
					//xoa vi tri hien tai
					parent.remove(0);
					//add lai cai quan co duoc tha vao trong
					parent.add(chessPieces);
					
				}
				else
				{
					//neu do la 1 quan co thi
					Container parent = (Container) component; //ep kieu quan co do ve container
					//add vao parent 
					parent.add(chessPieces);
					
				}
				//hien thi quan co do len
				chessPieces.setVisible(true);
	}

}
