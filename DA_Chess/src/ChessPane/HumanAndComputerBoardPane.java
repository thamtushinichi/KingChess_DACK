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

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ChessPane.MainGameBoardPane.Mouse_Here;
import ChessPane.MainGameBoardPane.Mouse_when_Move;
import players.Computer;
import players.Player1;

@SuppressWarnings("serial")
public class HumanAndComputerBoardPane extends JPanel {
	private Player1 P1 = new Player1();
	private Computer computer = new Computer();
	private final int Divide = 600 / 8; // 600 là kích thước bề ngang hoặc rộng
										// của bàn cờ, 8 là số ô cờ
	// chúng ta tính được cạnh của 1 ô cờ là bao nhiêu , đáp án: 75 pixel
	private Rectangle2D rec; // 1 ô cờ là 1 Rectangle2D
	private short players_turn = 1; // 1 la human, 2 la computer
	private boolean game_Over = false; // check game over
	private boolean game_Started = true;// check xem game đã bắt đầu chưa

	public HumanAndComputerBoardPane() {
		setPreferredSize(new Dimension(600, 600));

		Mouse_when_Move mouse_Drag_And_Drop = new Mouse_when_Move();
		Mouse_Here mouseHereEvent = new Mouse_Here();
		addMouseMotionListener(mouse_Drag_And_Drop);
		addMouseListener(mouseHereEvent);
		setLayout(null);
	}

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
		// đặt quân cờ vào vị trí các ô cờ
		Point positionP;
		int postX;
		int postY;
		Image image;
		for (int i = 1; i <= 32; i++) {
			if (i < 17) {
				if (i == computer.GetInHand()) {
					positionP = computer.getPixelPoint(i);

				} else {
					positionP = computer.returnPosition(i);

				}
				image = computer.returnIconImage(i);
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

			} else if (i == computer.GetInHand()) {
				g2.drawImage(image, positionP.x - 25, positionP.y - 25, Divide - 40, Divide - 12, this);
			} else {
				postX = rowToX(positionP.x);
				postY = colToY(positionP.y);
				g2.drawImage(image, postX + 20, postY + 4, Divide - 40, Divide - 12, this);
			}
		}
	}

	// doi 1 row sang toa do X
	private int rowToX(int row) {
		int myX;
		int height = this.getHeight();
		myX = (row * height / 8) - Divide;
		return myX;
	}

	// đổi cột sang tọa độ Y
	private int colToY(int column) {
		int myY;
		int width = getWidth();
		myY = (column * width / 8) - Divide;
		return myY;
	}

	public boolean handle_when_click_Piece(int x, int y) {
		if (players_turn == 1 && P1.GetInHand() != -1) {
			P1.changePixel(x, y, P1.GetInHand());
			return true;
		} else if (players_turn == 2 && computer.GetInHand() != -1) {
			computer.changePixel(x, y, computer.GetInHand());
			return true;
		}
		return false;
	}

	public boolean Board_getPosition(int x, int y) {
		if (game_Started && !game_Over) {
			if (players_turn == 1 || players_turn == 2) {
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
				} else if (players_turn == 2 && computer.GetInHand() == -1) {
					for (int i = 1; i <= 16; i++) {
						Point p = computer.returnPosition(i);
						if (p.x == newX && p.y == newY) {
							computer.SetInHand(i);
							handle_when_click_Piece(x, y);
							return true;
						}
					}
				} else if (players_turn == 1 && P1.GetInHand() != -1) {
					handle_when_click_Piece(x, y);

					return true;
				} else if (players_turn == 2 && computer.GetInHand() != -1) {
					handle_when_click_Piece(x, y);
					System.out.println(x + "," + y);
					return true;
				}
				P1.SetInHand(-1);
				computer.SetInHand(-1);

				return false;
			}
		}
		return false;
	}

	public boolean controll_Game_Type(int x, int y) {
		return Board_getPosition(x, y);
	}

	private class Mouse_when_Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();
			if (controll_Game_Type(x, y)) {
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {

		}

	}

	private class Mouse_Here implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (!game_Over) {
				Point newP;
				Point samePosition;
				if (P1.GetInHand() != -1) {
					newP = P1.getPixelPoint(P1.GetInHand());
					newP.x = newP.x / Divide;
					newP.y = newP.y / Divide;
					newP.x++;
					newP.y++;
					//Point oldP = P1.returnOldPosition(P1.GetInHand());
					Point presentP = P1.returnPosition(P1.GetInHand());
					///////////////// ______________________________________

					// con dung de check dang la server
					if (P1.GetInHand() < 33 && P1.GetInHand() > 24) {
						// Inhand la quan địch
						for (int i = 1; i < 17; i++) {
							samePosition = computer.returnPosition(i);
							if (samePosition.x == newP.x && samePosition.y == newP.y) {
								if (P1.setSeentoPawns(P1.GetInHand(), samePosition)) {
									break;
								}
							}
						}
					}
					if (!(newP.x == presentP.x && newP.y == presentP.y)) {
						if (P1.checkMove(newP, P1.GetInHand())) {
							// check nếu như di chuyển đến ô không hợp lệ
							boolean flag = false;
							for (int i = 1; i <= 32; i++) {
								if (P1.GetInHand() != i) {
									// check nếu ở đây có quân cờ
									if (i < 17) {
										// trả về nếu như trên đường đi gặp cản
										// trở và không thể di chuyển
										flag = P1.checkWay(newP, computer.returnPosition(i), P1.GetInHand());
									} else {
										flag = P1.checkWay(newP, P1.returnPosition(i), P1.GetInHand());
									}
									if (flag == true) {
										break; // có quân cờ ở đây
									}
								}
							}
							// ________________________
							if (!flag && P1.piece_Already_There(newP)) {
								// nếu như di chuyển hợp lệ và check có quân cờ
								// đối phương tại vị trí sắp đến ko
								// check nếu vị trí mới có chiếu vua Black không
								Point myOld = new Point();
								Point myOldTemp = P1.returnPosition(P1.GetInHand());
								myOld.x = myOldTemp.x;
								myOld.y = myOldTemp.y;
								Point other = new Point();
								Point f = new Point();
								boolean kill = false;
								int killed = -1;
								boolean end_move = true;
								// bắt đầu check king
								for (int k = 1; k < 17; k++) {
									other = computer.returnPosition(k);
									if (newP.x == other.x && newP.y == other.y) {
										int inHand = P1.GetInHand();
										if (inHand > 24 && P1.returnsoliderSeen(inHand)) {
											kill = true;// có quân cờ đã chết
											f.x = other.x;
											f.y = other.y;
											computer.killedPiece(k);// player 2
																	// giet quan
																	// co co gia
																	// tri k do
																	// di
										} else if (inHand <= 24) {
											kill = true;
											f.x = other.x;
											f.y = other.y;
											computer.killedPiece(k);
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
									P1.changePosition(newP, P1.GetInHand()); // kết
																				// thúc
																				// sự
																				// di
																				// chuyển
																				// P1
									if (computer.checkEmenyGameOver(P1)) {
										// neu nhu P2 game over
										JOptionPane.showConfirmDialog(getParent(), "Check Mate\n White won the game",
												"Game Over", JOptionPane.DEFAULT_OPTION);
										return;
									}

									if (P1.GetInHand() >= 25 && P1.GetInHand() <= 32) {
										Point pawn_position = P1.returnPosition(P1.GetInHand());
										// check tốt đã di chuyển thành công
										// chưa
										if (pawn_position.y != 7) {
											P1.moved_Success(P1.GetInHand());
										}
										// nếu là quân tốt thì check tiếp có nằm
										// ở vị trí được sắc phong Hậu không

										if (pawn_position.y == 1) {
											P1.honor_Queen(P1.GetInHand());
										}
									}
								}
								P1.checkKing(false);// ko check king nữa
								if (P1.see_EnemyKingIsChecked(computer)) {
									// nếu vua của tôi bị chiếu nếu tôi di
									// chuyen, vì vậy
									// tôi không thể di chuyển quân cờ và phải
									// return back to old position
									P1.changePosition(myOld, P1.GetInHand());
									P1.checkKing(true);// set king bị check là
														// true
									end_move = false;// chưa kết thúc được

								}
								if (kill && P1.returncheckKing()) {
									computer.changePosition(f, killed);

								}
								if (!P1.returncheckKing()) {
									if (computer.see_EnemyKingIsChecked(P1)) {
										// nếu như vua đối phương bị check
										computer.checkKing(true);
										end_move = false;
										// chưa kết thúc lượt được
										if (computer.checkEmenyGameOver(P1)) {
											// nếu như Player 1 game over
											game_Over = true;

										} else {
											// CheckStatus();
										}
									}
									if (end_move) {
										ChangeTurn();
									}
								}
							}
						}
					}
					// khi kết thúc lượt thì check
					P1.SetInHand(-1);
					repaint();

					if (game_Over) {
						System.out.println("game over 1");
						JOptionPane.showConfirmDialog(getParent(), "Check Mate\n White won the game", "Game Over",
								JOptionPane.DEFAULT_OPTION);

					}

				} else if (computer.GetInHand() != -1) {

				}
			}
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
}
