package ChessPane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlayOnlinePane extends JPanel implements MouseListener {
	private ImageIcon bg;

	public PlayOnlinePane(){
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
		// TODO Auto-generated method stub
		
	}
}
