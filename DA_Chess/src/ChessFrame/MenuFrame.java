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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuFrame extends JFrame implements MouseListener {

	JPanel menu_pane;
	JPanel playonline_menu_pane;
	JLabel single, quit, twoplayer, playonline;
	JLabel createroom, joinroom, back;
	Color bg_color = Color.decode("#efd39c");

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

		JPanel pane = new JPanel(new GridLayout(4, 1));
		pane.add(single);
		pane.add(twoplayer);
		pane.add(playonline);
		pane.add(quit);
		menu_pane.add(pane, BorderLayout.SOUTH);
		menu_pane.setBorder(BorderFactory.createEmptyBorder(420, 20, 20, 0));
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
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == quit) {
			quit();
		} else if (source == playonline) {
			playonline_menu_pane = new PlayOnlinePane();
			setContentPane(playonline_menu_pane);
			
			JPanel pane = new JPanel(new GridLayout(3, 1));
			pane.add(createroom);
			pane.add(joinroom);
			pane.add(back);
			
			createroom.addMouseListener(this);
			joinroom.addMouseListener(this);
			back.addMouseListener(this);
			
			playonline_menu_pane.add(pane, BorderLayout.SOUTH);
			playonline_menu_pane.setBorder(BorderFactory.createEmptyBorder(420, 20, 20, 0));
			
			pack();
			Dimension size = getSize();
			setSize(size);
		} else if(source == back)
		{
			setContentPane(menu_pane);
			pack();
			
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
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

	@Override
	public void mouseExited(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void quit() {
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "MyChessmate1.1",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	// panel sau khi nhan nut playonline
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
}
