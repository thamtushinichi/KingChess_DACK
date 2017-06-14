package players;

import java.awt.Image;
import java.awt.Point;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class Player1 {
	// khởi tạo các quân cờ
	public Rook wr1; // quan xe ben trai
	public Rook wr2; // quan xe ben phai
	public Knight wn1;
	public Knight wn2;
	public Bishop wb1;
	public Bishop wb2;
	public Queen wq;
	public King wk;
	// khởi tạo 8 con tốt white
	public Pawn[] wPawns;
	private int inHand; //
	private boolean kingIsChecked; // bị chiếu vua
	private int choosenOne;
	private String Color; // màu sắc quân
	private Point other; // vị trí quân địch chiếu vua mình
	private int piece_Is_Killed_To_Protect_King; // là quân cờ đối phương phải
													// bị tiêu diệt nếu muốn bảo
													// vệ vua đang bị quân cờ
													// này chiếu

	public Player1() {
		wPawns = new Pawn[8];
		inHand = -1;
		kingIsChecked = false;
		Color = "white";
		String fileSeparator = new String(System.getProperty("file.separator"));
		wr1 = new Rook("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wr.gif", 1, 8);
		wr2 = new Rook("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wr.gif", 8, 8);
		wn1 = new Knight("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wn.gif", 2,
				8);
		wn2 = new Knight("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wn.gif", 7,
				8);
		wb1 = new Bishop("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wb.gif", 3,
				8);
		wb2 = new Bishop("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wb.gif", 6,
				8);
		wq = new Queen("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wq.gif", 4, 8);
		wk = new King("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wk.gif", 5, 8);
		// khởi tạo tốt
		int j = 1;
		for (int i = 0; i < 8; i++) {
			wPawns[i] = new Pawn(
					"src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "wp.gif", j, 7);
			j++;
		}
	}

	public Point returnPosition(int i) {

		switch (i) {

		case 17:
			return wr1.returnPosition();
		case 18:
			return wr2.returnPosition();
		case 19:
			return wn1.returnPosition();
		case 20:
			return wn2.returnPosition();
		case 21:
			return wb1.returnPosition();
		case 22:
			return wb2.returnPosition();
		case 23:
			return wq.returnPosition();
		case 24:
			return wk.returnPosition();
		case 25:
			return wPawns[0].returnPosition();
		case 26:
			return wPawns[1].returnPosition();
		case 27:
			return wPawns[2].returnPosition();
		case 28:
			return wPawns[3].returnPosition();
		case 29:
			return wPawns[4].returnPosition();
		case 30:
			return wPawns[5].returnPosition();
		case 31:
			return wPawns[6].returnPosition();
		case 32:
			return wPawns[7].returnPosition();
		}
		return new Point(-1, -1);
	}

	public Point returnOldPosition(int i) {
		switch (i) {

		case 17:
			return wr1.getPointOld();
		case 18:
			return wr2.getPointOld();
		case 19:
			return wn1.getPointOld();
		case 20:
			return wn2.getPointOld();
		case 21:
			return wb1.getPointOld();
		case 22:
			return wb2.getPointOld();
		case 23:
			return wq.getPointOld();
		case 24:
			return wk.getPointOld();
		case 25:
			return wPawns[0].getPointOld();
		case 26:
			return wPawns[1].getPointOld();
		case 27:
			return wPawns[2].getPointOld();
		case 28:
			return wPawns[3].getPointOld();
		case 29:
			return wPawns[4].getPointOld();
		case 30:
			return wPawns[5].getPointOld();
		case 31:
			return wPawns[6].getPointOld();
		case 32:
			return wPawns[7].getPointOld();
		}
		return new Point(-1, -1);
	}

	public Image returnIconImage(int i) {

		switch (i) {
		case 17: {
			return wr1.getImageIcon();
		}
		case 18: {
			return wr2.getImageIcon();
		}
		case 19: {
			return wn1.getImageIcon();
		}
		case 20: {
			return wn2.getImageIcon();
		}
		case 21:
			return wb1.getImageIcon();
		case 22:
			return wb2.getImageIcon();
		case 23:
			return wq.getImageIcon();
		case 24:
			return wk.getImageIcon();
		case 25:
			return wPawns[0].getImageIcon();
		case 26:
			return wPawns[1].getImageIcon();
		case 27:
			return wPawns[2].getImageIcon();
		case 28:
			return wPawns[3].getImageIcon();
		case 29:
			return wPawns[4].getImageIcon();
		case 30:
			return wPawns[5].getImageIcon();
		case 31:
			return wPawns[6].getImageIcon();
		case 32:
			return wPawns[7].getImageIcon();
		}
		return null;
	}

	public void changePosition(Point newPoint, int i) {
		switch (i) {
		case 17:
			wr1.setPoint(newPoint);
			break;
		case 18:
			wr2.setPoint(newPoint);
			break;
		case 19:
			wn1.setPoint(newPoint);
			break;
		case 20:
			wn2.setPoint(newPoint);
			break;
		case 21:
			wb1.setPoint(newPoint);
			break;
		case 22:
			wb2.setPoint(newPoint);
			break;
		case 23:
			wq.setPoint(newPoint);
			break;
		case 24:
			wk.setPoint(newPoint);
			break;
		case 25:
			wPawns[0].setPoint(newPoint);
			break;
		case 26:
			wPawns[1].setPoint(newPoint);
			break;
		case 27:
			wPawns[2].setPoint(newPoint);
			break;
		case 28:
			wPawns[3].setPoint(newPoint);
			break;
		case 29:
			wPawns[4].setPoint(newPoint);
			break;
		case 30:
			wPawns[5].setPoint(newPoint);
			break;
		case 31:
			wPawns[6].setPoint(newPoint);
			break;
		case 32:
			wPawns[7].setPoint(newPoint);
			break;
		}
	}

	public void changePositionToOld(Point newPoint, int i) {
		switch (i) {

		case 17:
			wr1.toPointOld(newPoint);
			break;
		case 18:
			wr2.toPointOld(newPoint);
			break;
		case 19:
			wn1.toPointOld(newPoint);
			break;
		case 20:
			wn2.toPointOld(newPoint);
			break;
		case 21:
			wb1.toPointOld(newPoint);
			break;
		case 22:
			wb2.toPointOld(newPoint);
			break;
		case 23:
			wq.toPointOld(newPoint);
			break;
		case 24:
			wk.toPointOld(newPoint);
			break;
		case 25:
			wPawns[0].toPointOld(newPoint);
			break;
		case 26:
			wPawns[1].toPointOld(newPoint);
			break;
		case 27:
			wPawns[2].toPointOld(newPoint);
			break;
		case 28:
			wPawns[3].toPointOld(newPoint);
			break;
		case 29:
			wPawns[4].toPointOld(newPoint);
			break;
		case 30:
			wPawns[5].toPointOld(newPoint);
			break;
		case 31:
			wPawns[6].toPointOld(newPoint);
			break;
		case 32:
			wPawns[7].toPointOld(newPoint);
			break;
		}
	}

	public void changePixel(int newPixelX, int newPixelY, int i) {
		choosenOne = i;
		switch (choosenOne) {

		case 17:
			wr1.setPixelPoint(newPixelX, newPixelY);
			break;
		case 18:
			wr2.setPixelPoint(newPixelX, newPixelY);
			break;
		case 19:
			wn1.setPixelPoint(newPixelX, newPixelY);
			break;
		case 20:
			wn2.setPixelPoint(newPixelX, newPixelY);
			break;
		case 21:
			wb1.setPixelPoint(newPixelX, newPixelY);
			break;
		case 22:
			wb2.setPixelPoint(newPixelX, newPixelY);
			break;
		case 23:
			wq.setPixelPoint(newPixelX, newPixelY);
			break;
		case 24:
			wk.setPixelPoint(newPixelX, newPixelY);
			break;
		case 25:
			wPawns[0].setPixelPoint(newPixelX, newPixelY);
			break;
		case 26:
			wPawns[1].setPixelPoint(newPixelX, newPixelY);
			break;
		case 27:
			wPawns[2].setPixelPoint(newPixelX, newPixelY);
			break;
		case 28:
			wPawns[3].setPixelPoint(newPixelX, newPixelY);
			break;
		case 29:
			wPawns[4].setPixelPoint(newPixelX, newPixelY);
			break;
		case 30:
			wPawns[5].setPixelPoint(newPixelX, newPixelY);
			break;
		case 31:
			wPawns[6].setPixelPoint(newPixelX, newPixelY);
			break;
		case 32:
			wPawns[7].setPixelPoint(newPixelX, newPixelY);
			break;
		}
	}

	public Point getPixelPoint(int i) {
		choosenOne = i;
		switch (choosenOne) {

		case 17:
			return wr1.getPixelPoint();
		case 18:
			return wr2.getPixelPoint();
		case 19:
			return wn1.getPixelPoint();
		case 20:
			return wn2.getPixelPoint();
		case 21:
			return wb1.getPixelPoint();
		case 22:
			return wb2.getPixelPoint();
		case 23:
			return wq.getPixelPoint();
		case 24:
			return wk.getPixelPoint();
		case 25:
			return wPawns[0].getPixelPoint();
		case 26:
			return wPawns[1].getPixelPoint();
		case 27:
			return wPawns[2].getPixelPoint();
		case 28:
			return wPawns[3].getPixelPoint();
		case 29:
			return wPawns[4].getPixelPoint();
		case 30:
			return wPawns[5].getPixelPoint();
		case 31:
			return wPawns[6].getPixelPoint();
		case 32:
			return wPawns[7].getPixelPoint();
		}
		return null;
	}

	public boolean checkMove(Point newP, int i) {
		choosenOne = i;
		switch (choosenOne) {

		case 17:
			return wr1.canMove(newP.x, newP.y);
		case 18:
			return wr2.canMove(newP.x, newP.y);
		case 19:
			return wn1.canMove(newP.x, newP.y);
		case 20:
			return wn2.canMove(newP.x, newP.y);
		case 21:
			return wb1.canMove(newP.x, newP.y);
		case 22:
			return wb2.canMove(newP.x, newP.y);
		case 23:
			return wq.canMove(newP.x, newP.y);
		case 24:
			return wk.canMove(newP.x, newP.y);
		case 25:
			return wPawns[0].canMove(newP.x, newP.y, Color);
		case 26:
			return wPawns[1].canMove(newP.x, newP.y, Color);
		case 27:
			return wPawns[2].canMove(newP.x, newP.y, Color);
		case 28:
			return wPawns[3].canMove(newP.x, newP.y, Color);
		case 29:
			return wPawns[4].canMove(newP.x, newP.y, Color);
		case 30:
			return wPawns[5].canMove(newP.x, newP.y, Color);
		case 31:
			return wPawns[6].canMove(newP.x, newP.y, Color);
		case 32:
			return wPawns[7].canMove(newP.x, newP.y, Color);
		}
		return false;
	}

	public boolean setSeentoPawns(int i, Point P) {
		switch (i) {
		case 25:
			return wPawns[0].setSeenByCheckKing(P, "white");
		case 26:
			return wPawns[1].setSeenByCheckKing(P, "white");
		case 27:
			return wPawns[2].setSeenByCheckKing(P, "white");
		case 28:
			return wPawns[3].setSeenByCheckKing(P, "white");
		case 29:
			return wPawns[4].setSeenByCheckKing(P, "white");
		case 30:
			return wPawns[5].setSeenByCheckKing(P, "white");
		case 31:
			return wPawns[6].setSeenByCheckKing(P, "white");
		case 32:
			return wPawns[7].setSeenByCheckKing(P, "white");
		}
		return false;
	}

	public boolean returnsoliderSeen(int i) {
		switch (i) {
		case 25:
			return wPawns[0].isMySeen();
		case 26:
			return wPawns[1].isMySeen();
		case 27:
			return wPawns[2].isMySeen();
		case 28:
			return wPawns[3].isMySeen();
		case 29:
			return wPawns[4].isMySeen();
		case 30:
			return wPawns[5].isMySeen();
		case 31:
			return wPawns[6].isMySeen();
		case 32:
			return wPawns[7].isMySeen();
		}
		return false;
	}

	public boolean checkWay(Point newP, Point postionFromOthers, int i) {
		switch (i) {
		case 17:
			return wr1.isPieceInMyWay(newP.x, newP.y, postionFromOthers);
		case 18:
			return wr2.isPieceInMyWay(newP.x, newP.y, postionFromOthers);
		case 21:
			return wb1.isPieceInMyWay(newP.x, newP.y, postionFromOthers);
		case 22:
			return wb2.isPieceInMyWay(newP.x, newP.y, postionFromOthers);
		case 23:
			return wq.isPieceInMyWay(newP.x, newP.y, postionFromOthers);
		case 25:
			return wPawns[0].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 26:
			return wPawns[1].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 27:
			return wPawns[2].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 28:
			return wPawns[3].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 29:
			return wPawns[4].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 30:
			return wPawns[5].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 31:
			return wPawns[6].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);
		case 32:
			return wPawns[7].isPieceInMyWay(newP.x, newP.y, postionFromOthers, Color);

		}
		return false;
	}

	// tiêu diệt quân cờ
	public boolean killedPiece(int i) {
		Point out = new Point(13, 13);
		switch (i) {

		case 17:
			wr1.setPoint(out);
			return true;
		case 18:
			wr2.setPoint(out);
			return true;
		case 19:
			wn1.setPoint(out);
			return true;
		case 20:
			wn2.setPoint(out);
			return true;
		case 21:
			wb1.setPoint(out);
			return true;
		case 22:
			wb2.setPoint(out);
			return true;
		case 23:
			wq.setPoint(out);
			return true;
		case 24:
			return false;
		case 25:
			wPawns[0].setPoint(out);
			return true;
		case 26:
			wPawns[1].setPoint(out);
			return true;
		case 27:
			wPawns[2].setPoint(out);
			return true;
		case 28:
			wPawns[3].setPoint(out);
			return true;
		case 29:
			wPawns[4].setPoint(out);
			return true;
		case 30:
			wPawns[5].setPoint(out);
			return true;
		case 31:
			wPawns[6].setPoint(out);
			return true;
		case 32:
			wPawns[7].setPoint(out);
			return true;
		}

		return false;
	}

	public boolean checkKing(Point p1, Point p2, int i) {
		switch (i) {
		case 17:
			return wr1.checkKing(p1.x, p1.y, p2);
		case 18:
			return wr2.checkKing(p1.x, p1.y, p2);
		case 19:
			return wn1.canMove(p1.x, p1.y);
		case 20:
			return wn2.canMove(p1.x, p1.y);
		case 21:
			return wb1.checkKing(p1.x, p1.y, p2);
		case 22:
			return wb2.checkKing(p1.x, p1.y, p2);
		case 23:
			return wq.checkKing(p1.x, p1.y, p2);

		case 25:
			return wPawns[0].canMove(p1.x, p1.y, Color);
		case 26:
			return wPawns[1].canMove(p1.x, p1.y, Color);
		case 27:
			return wPawns[2].canMove(p1.x, p1.y, Color);
		case 28:
			return wPawns[3].canMove(p1.x, p1.y, Color);
		case 29:
			return wPawns[4].canMove(p1.x, p1.y, Color);
		case 30:
			return wPawns[5].canMove(p1.x, p1.y, Color);
		case 31:
			return wPawns[6].canMove(p1.x, p1.y, Color);
		case 32:
			return wPawns[7].canMove(p1.x, p1.y, Color);
		}
		return false;
	}

	public int returnChosen() {
		return choosenOne;
	}

	public void SetInHand(int i) {
		inHand = i;
	}

	public int GetInHand() {
		return inHand;
	}

	public boolean canMove(int x, int y) {
		return true;
	}

	public void checkKing(boolean newkingcheck) {
		kingIsChecked = newkingcheck;
	}

	public boolean returncheckKing() {
		return kingIsChecked;
	}

	public boolean if_MyKingIsChecked(Player2 Black) // neu vua cua toi bi chieu
	{
		boolean isCheckmate = false;
		boolean flag = false;
		return false;
	}

	public boolean checkEmenyGameOver(Player2 enemy) {
		// nếu tất cả các quân cờ đều không được di chuyển , coi như game over
		if (!KingGenerate_Moves(enemy)) {
			inHand = -1;
			return false;
		} else if (!RookGenerate_Moves(enemy, this.wr1)) {
			inHand = -1;
			return false;
		} else if (!RookGenerate_Moves(enemy, wr2)) {
			inHand = -1;
			return false;
		} else if (!BishopGenerate_Moves(enemy, wb1)) {
			inHand = -1;
			return false;
		} else if (!BishopGenerate_Moves(enemy, wb2)) {
			inHand = -1;
			return false;
		} else if (!KnightGenerate_Moves(enemy, wn1)) {
			inHand = -1;
			return false;
		} else if (!KnightGenerate_Moves(enemy, wn2)) {
			inHand = -1;
			return false;
		} else if (!QueenGenerate_Moves(enemy)) {
			inHand = -1;
			return false;
		}

		for (int i = 0; i <= 7; i++) {
			inHand = 25 + i;
			if (!PawnGenerate_Moves(enemy, wPawns[i])) {
				inHand = -1;
				return false;
			}
		}
		inHand = -1;
		return true;
	}

	public boolean see_EnemyKingIsChecked(Player2 black) {
		Point My_King_Position = wk.returnPosition();
		boolean flag = false;
		// start checking the King
		// duyet qua tất cả 16 quân cờ của đối phương
		for (int i = 1; i < 17; i++) {
			if (i < 9) {
				// tức là chỉ duyệt trong phạm vi quân chủ lực hàng dưới
				if (black.checkMove(My_King_Position, i)) {
					flag = true;
					for (int j = 1; j < 33; j++) {
						if (j < 17) {
							if (black.checkWay(My_King_Position, black.returnPosition(j), i)) {
								// Means there is somting in the Way so can't
								// move'
								flag = false;
							}
						} else {

							if (j != 24)
								if (black.checkWay(My_King_Position, returnPosition(j), i)) {

									flag = false;
									// Means there is somting in the Way so
									// can't move'
								}
						}
					}
					if (flag) {
						break;
					}
				}
			} else {
				// for Pawn
				if (black.setSeentoPawns(i, My_King_Position)) {
					break;
				}
			}
			if (i == 16) {
				return false;
			}
		}
		return true;
	}

	//with bot AI
	public boolean see_EnemyKingIsChecked(Computer black) {
		Point My_King_Position = wk.returnPosition();
		boolean flag = false;
		// start checking the King
		// duyet qua tất cả 16 quân cờ của đối phương
		for (int i = 1; i < 17; i++) {
			if (i < 9) {
				// tức là chỉ duyệt trong phạm vi quân chủ lực hàng dưới
				if (black.checkMove(My_King_Position, i)) {
					flag = true;
					for (int j = 1; j < 33; j++) {
						if (j < 17) {
							if (black.checkWay(My_King_Position, black.returnPosition(j), i)) {
								// Means there is somting in the Way so can't
								// move'
								flag = false;
							}
						} else {

							if (j != 24)
								if (black.checkWay(My_King_Position, returnPosition(j), i)) {

									flag = false;
									// Means there is somting in the Way so
									// can't move'
								}
						}
					}
					if (flag) {
						break;
					}
				}
			} else {
				// for Pawn
				if (black.setSeentoPawns(i, My_King_Position)) {
					break;
				}
			}
			if (i == 16) {
				return false;
			}
		}
		return true;
	}
	// check nếu vị trí tiến tới là quân white (quân phe mình)
	public boolean piece_Already_There(Point newP) {
		Point samePostion;
		for (int i = 17; i <= 32; i++) {
			if (this.GetInHand() != i)// There is no need to check the inHand
										// pice
			{
				// Check if there is White Pices in the new Point
				// If so we Can't move (Same Color)!!
				samePostion = returnPosition(i);
				if (newP.x == samePostion.x && newP.y == samePostion.y) {
					return false;
				}
			}
		}
		return true;
	}

	// check nếu vị trí đặt quân là quân enemy :Black
	public boolean piece_Enemy_Already_There(Point newP, Player2 enemy) {
		Point samePostion;
		for (int i = 1; i <= 16; i++) {
			samePostion = enemy.returnPosition(i);
			if (newP.x == samePostion.x && newP.y == samePostion.y) {
				return false;
			}
		}
		return true;
	}

	// lay quan co enemy ở đấy, trả về giá trị int của quân cờ
	public int getPiece_Enemy_Already_There(Point newP, Player2 enemy) {
		Point samePostion;
		for (int i = 1; i <= 16; i++) {
			samePostion = enemy.returnPosition(i);
			if (newP.x == samePostion.x && newP.y == samePostion.y) {
				return i;
			}
		}
		return -1;
	}

	public boolean KingGenerate_Moves(Player2 enemy) {
		boolean something_killed = false;
		Point oldP = new Point();
		Point placeCheck = new Point();
		inHand = 24; // set gia tri luc nay la 24 , tuc la quan Vua
		int x = wk.getX();
		int y = wk.getY();
		oldP.x = x;
		oldP.y = y;
		if (x + 1 <= 8) // khi quân vua di chuyển sang bên phải và trong bàn cờ
		{
			wk.setX(x + 1);
			wk.setY(y);
			placeCheck.x = x + 1;
			placeCheck.y = y;
			if (kill_To_Protect_King(enemy, returnPosition(inHand)))
			// nếu như phải giết quân đối phương để bảo vệ vua thì trả về true
			{
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))// nếu như quân đồng minh đang ở
												// đây
			{
				if (!see_EnemyKingIsChecked(enemy)) // nếu vua của mình không
													// bị chiếu
				{
					// Vị trí bên phải quân KIng ko đi được, phải đưa quân King
					// lại vị trí ban đầu
					wk.setPoint(oldP);
					if (something_killed) // có nghĩa là đã giết được quân đối
											// phương để bảo vệ vua của mình
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}

			}
		}
		// qua được dòng if trên có nghĩa là vua đi sang phải
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			// thay đổi vị trí của quân đó
			something_killed = false;
		}
		if (y + 1 <= 8) // Vua đi xuống dưới
		{
			wk.setX(x);
			wk.setY(y + 1);
			placeCheck.x = x;
			placeCheck.y = y + 1;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}
		}
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		if (y - 1 > 0)// vua đi lên trên
		{
			wk.setX(x);
			wk.setY(y - 1);
			placeCheck.x = x;
			placeCheck.y = y - 1;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}

		}
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		if (x - 1 > 0) // nếu vua đi sang trái
		{
			wk.setX(x - 1);
			wk.setY(y);
			placeCheck.x = x - 1;
			placeCheck.y = y;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}

		}
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		if (y - 1 > 0 && x - 1 > 0) // Vua đi sang phía trái trên
		{
			wk.setX(x - 1);
			wk.setY(y - 1);
			placeCheck.x = x - 1;
			placeCheck.y = y - 1;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}
		}
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		if (y + 1 <= 8 && x + 1 <= 8) // Vua đi sang phía phải dưới ( phía dưới
										// bên phải)
		{
			wk.setX(x + 1);
			wk.setY(y + 1);
			placeCheck.x = x + 1;
			placeCheck.y = y + 1;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}
		}
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		if (y - 1 > 0 && x + 1 <= 8) // Vua đi sang phía phải trên( tức là lên
										// trên và phía bên phải)
		{
			wk.setX(x + 1);
			wk.setY(y - 1);
			placeCheck.x = x + 1;
			placeCheck.y = y - 1;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}
		}
		wk.setPoint(oldP);
		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		if (y + 1 <= 8 && x - 1 > 0) // vua đi sang phía trái dưới ( tức đi
										// xuống dưới phía bên trái)
		{
			wk.setX(x - 1);
			wk.setY(y + 1);
			placeCheck.x = x - 1;
			placeCheck.y = y + 1;
			if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
				something_killed = true;
			}
			if (piece_Already_There(placeCheck))
				if (!see_EnemyKingIsChecked(enemy)) {

					wk.setPoint(oldP);
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
					return false;
				}
		}

		if (something_killed) // nếu như có quân bị giết
		{
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		wk.setPoint(oldP);
		return true;

	}

	// di chuyển quân xe (Rook)
	public boolean RookGenerate_Moves(Player2 enemy, Rook wRook) {
		boolean something_killed = false;
		Point oldP1 = new Point();
		Point placeCheck = new Point();
		int x1 = wRook.getX();
		int y1 = wRook.getY();
		// kiểm tra xem quân Rook này để gán vào đối tượng đang được chọn hiện
		// tại là quân xe này
		if (wRook == this.wr1) {
			inHand = 17;
		} else {
			inHand = 18;
		}
		// _______________________________
		oldP1.x = x1;
		oldP1.y = y1;
		placeCheck.y = y1;
		if (x1 != 13) {
			// nếu như quân này chưa chết ( tọa độ x=13 là quân này đã chết)
			// chạy hàng ngang
			for (int i = 1; i <= 8; i++) {
				wRook.setX(i);
				placeCheck.x = i;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}
					if (piece_Already_There(placeCheck)) {
						if (!see_EnemyKingIsChecked(enemy)) {
							// set lại giá trị vị trí trước đó cho quân xe
							// mean: đưa quân xe trở lại vị trí ban đầu sau nước
							// đó
							wRook.setX(oldP1.x);
							wRook.setY(oldP1.y);
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							return false;
						}
					}
				}
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
			}
			wRook.setX(oldP1.x);
			placeCheck.x = oldP1.x;
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			// rook chạy hàng dọc
			for (int i = 1; i <= 8; i++) {
				wRook.setY(i);
				placeCheck.y = i;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}
					if (piece_Already_There(placeCheck)) {
						if (!see_EnemyKingIsChecked(enemy)) {
							// set lại giá trị vị trí trước đó cho quân xe
							// mean: đưa quân xe trở lại vị trí ban đầu sau nước
							// đó
							wRook.setX(oldP1.x);
							wRook.setY(oldP1.y);
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							return false;
						}
					}
				}
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
			}
			wRook.setY(oldP1.y);
		}
		// -_______________________
		// set lại vị trí cũ cho quân xe
		if (something_killed) {
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		wRook.setX(oldP1.x);
		wRook.setY(oldP1.y);

		return true;

	}

	public boolean BishopGenerate_Moves(Player2 enemy, Bishop wBishop) {
		boolean something_killed = false;
		Point oldP1 = new Point();
		Point placeCheck = new Point();
		oldP1 = wBishop.returnPosition();
		if (wBishop == this.wb1) {
			inHand = 21;
		} else {
			inHand = 22;
		}
		if (oldP1.x != 13) {
			// quân Tượng chạy đường chéo hướng dưới trái
			for (int x = oldP1.x, y = oldP1.y; x >= 1 && y <= 8; x--, y++) {
				wBishop.setX(x);
				wBishop.setY(y);
				placeCheck.x = x;
				placeCheck.y = y;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}
					if (piece_Already_There(placeCheck)) {
						if (!see_EnemyKingIsChecked(enemy)) {
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							wBishop.setPoint(oldP1);
							return false;
						}
					}
				}
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			// TƯợng chạy hướng trên phải
			for (int x = oldP1.x, y = oldP1.y; y >= 1 && x <= 8; x++, y--) {
				wBishop.setX(x);
				wBishop.setY(y);
				placeCheck.x = x;
				placeCheck.y = y;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}
					if (piece_Already_There(placeCheck)) {
						if (!see_EnemyKingIsChecked(enemy)) {
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							wBishop.setPoint(oldP1);
							return false;
						}
					}
				}
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
			}
			wBishop.setPoint(oldP1);
		}
		wBishop.setPoint(oldP1);
		if (something_killed) {
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		return true;
	}

	public boolean KnightGenerate_Moves(Player2 enemy, Knight wKnight) {
		Point oldP1 = new Point();
		boolean something_killed = false;
		oldP1 = wKnight.returnPosition();
		Point placeCheck = new Point();
		if (wKnight == this.wn1) {
			inHand = 19;
		} else {
			inHand = 20;
		}
		int x = oldP1.x;
		int y = oldP1.y;
		// neu nhu quan Ngua nay chua chet
		if (x != 13) {
			if (x + 1 <= 8 && y + 2 <= 8) {
				wKnight.setX(x + 1);
				wKnight.setY(y + 2);
				placeCheck.x = x + 1;
				placeCheck.y = y + 2;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x + 1 <= 8 && y - 2 >= 1) {
				wKnight.setX(x + 1);
				wKnight.setY(y - 2);
				placeCheck.x = x + 1;
				placeCheck.y = y - 2;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x + 2 <= 8 && y + 1 <= 8) {
				wKnight.setX(x + 2);
				wKnight.setY(y + 1);
				placeCheck.x = x + 2;
				placeCheck.y = y + 1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x + 2 <= 8 && y - 1 >= 1) {
				wKnight.setX(x + 2);
				wKnight.setY(y - 1);
				placeCheck.x = x + 2;
				placeCheck.y = y - 1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x - 1 >= 1 && y + 2 <= 8) {
				wKnight.setX(x - 1);
				wKnight.setY(y + 2);
				placeCheck.x = x - 1;
				placeCheck.y = y + 2;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x - 1 >= 1 && y - 2 >= 1) {
				wKnight.setX(x - 1);
				wKnight.setY(y - 2);
				placeCheck.x = x - 1;
				placeCheck.y = y - 2;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x - 2 >= 1 && y + 1 <= 8) {
				wKnight.setX(x - 2);
				wKnight.setY(y + 1);
				placeCheck.x = x - 2;
				placeCheck.y = y + 1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			if (x - 2 >= 1 && y - 1 >= 1) {
				wKnight.setX(x - 2);
				wKnight.setY(y - 1);
				placeCheck.x = x - 2;
				placeCheck.y = y - 1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck)) {
					if (!see_EnemyKingIsChecked(enemy)) {
						wKnight.setPoint(oldP1);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
				}
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}

		}
		wKnight.setPoint(oldP1);
		return true;
	}

	public boolean QueenGenerate_Moves(Player2 enemy) {
		boolean something_killed = false;
		Point oldP1 = new Point();
		oldP1 = this.wq.returnPosition();
		Point placeCheck = new Point();
		inHand = 23;
		if (oldP1.x != 13) {
			// quân Hậu đi đường chéo trái dưới
			for (int x = oldP1.x, y = oldP1.y; x >= 1 && y <= 8; x--, y++) {

				wq.setX(x);
				wq.setY(y);
				placeCheck.x = x;
				placeCheck.y = y;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}

					if (piece_Already_There(placeCheck))
						if (!see_EnemyKingIsChecked(enemy)) {
							wq.setPoint(oldP1);

							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							return false;
						}
				}

				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}

			}
			// quân hậu đi đường phải trên
			for (int x = oldP1.x, y = oldP1.y; y >= 1 && x <= 8; x++, y--) {

				wq.setX(x);
				wq.setY(y);
				placeCheck.x = x;
				placeCheck.y = y;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}

					if (piece_Already_There(placeCheck))
						if (!see_EnemyKingIsChecked(enemy)) {
							wq.setPoint(oldP1);
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							return false;
						}
				}

				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}

			}
			this.wq.setPoint(oldP1);
			placeCheck.x = oldP1.x;
			placeCheck.y = oldP1.y;
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			// quan hau chay hang ngang
			for (int i = 1; i <= 8; i++) {
				wq.setX(i);
				placeCheck.x = i;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}

					if (piece_Already_There(placeCheck))
						if (!see_EnemyKingIsChecked(enemy)) {
							wq.setX(oldP1.x);
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							return false;
						}
				}
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
			}
			wq.setX(oldP1.x);
			placeCheck.x = oldP1.x;
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			// quân hậu chạy hàng dọc
			for (int i = 1; i <= 8; i++) {
				wq.setY(i);
				placeCheck.y = i;
				if (checkWayToPosition(enemy, oldP1)) {
					if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
						something_killed = true;
					}

					if (piece_Already_There(placeCheck))
						if (!see_EnemyKingIsChecked(enemy)) {
							wq.setY(oldP1.y);
							if (something_killed) {
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed = false;
							}
							return false;
						}
				}
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
			}
			wq.setY(oldP1.y);
		}
		if (something_killed) {
			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
			something_killed = false;
		}
		return true;
	}

	public boolean PawnGenerate_Moves(Player2 enemy, Pawn wPawn) {
		if (!wPawn.isPawn_becomes_Queen()) {
			Point oldP1 = new Point();
			oldP1 = wPawn.returnPosition();
			Point placeCheck = new Point();
			placeCheck.x = oldP1.x;
			placeCheck.y = oldP1.y;
			// nếu quân tốt chưa chết
			if (oldP1.x != 13) {
				if (wPawn.canMove(oldP1.x, oldP1.y - 2, this.Color) && oldP1.y - 2 >= 1) {
					// chi di chuyen hang doc ( truong hợp này là 2 ô)
					wPawn.setY(oldP1.y - 2);
					placeCheck.y = oldP1.y - 2;
					if (piece_Already_There(placeCheck)) {
						if (piece_Enemy_Already_There(placeCheck, enemy)) {
							if (!see_EnemyKingIsChecked(enemy)) {
								wPawn.setPoint(oldP1);
								return false;
							}
						}
					}

				}
				// di chuyển hàng dọc 1 ô
				if (wPawn.canMove(oldP1.x, oldP1.y - 1, this.Color) && oldP1.y - 1 >= 1) {
					// chi di chuyen hang doc ( truong hợp này là 2 ô)
					wPawn.setY(oldP1.y - 1);
					placeCheck.y = oldP1.y - 1;
					if (piece_Already_There(placeCheck)) {
						if (piece_Enemy_Already_There(placeCheck, enemy)) {
							if (!see_EnemyKingIsChecked(enemy)) {
								wPawn.setPoint(oldP1);
								System.out.println("dff");
								return false;
							}
						}
					}

				}
				// neu nhu ko co quan dich ben trai
				if (!piece_Enemy_Already_There(new Point(oldP1.x - 1, oldP1.y - 1), enemy)) {
					if (kill_To_Protect_King(enemy, new Point(oldP1.x - 1, oldP1.y - 1))) {
						if (!see_EnemyKingIsChecked(enemy)) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							wPawn.setPoint(oldP1);
							return false;
						}
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					}
				}
				// neu nhu ko co quan dich ben phair
				if (!piece_Enemy_Already_There(new Point(oldP1.x + 1, oldP1.y - 1), enemy)) {
					if (kill_To_Protect_King(enemy, new Point(oldP1.x + 1, oldP1.y - 1))) {
						if (!see_EnemyKingIsChecked(enemy)) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							wPawn.setPoint(oldP1);
							return false;
						}
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					}
				}

			}
			wPawn.setPoint(oldP1);
			return true;
		} else {
			// la Queen
			boolean something_killed = false;
			Point oldP1 = new Point();
			oldP1 = wPawn.returnPosition();
			Point placeCheck = new Point();
			if (wPawn == this.wPawns[0]) {
				inHand = 25;

			} else if (wPawn == this.wPawns[1]) {
				inHand = 26;
			} else if (wPawn == this.wPawns[2]) {
				inHand = 27;
			} else if (wPawn == this.wPawns[3]) {
				inHand = 28;
			} else if (wPawn == this.wPawns[4]) {
				inHand = 29;
			} else if (wPawn == this.wPawns[5]) {
				inHand = 30;
			} else if (wPawn == this.wPawns[6]) {
				inHand = 31;
			} else if (wPawn == this.wPawns[7]) {
				inHand = 32;
			}

			if (oldP1.x != 13) {
				// quân Hậu đi đường chéo trái dưới
				for (int x = oldP1.x, y = oldP1.y; x >= 1 && y <= 8; x--, y++) {

					wPawn.setX(x);
					wPawn.setY(y);
					placeCheck.x = x;
					placeCheck.y = y;
					if (checkWayToPosition(enemy, oldP1)) {
						if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
							something_killed = true;
						}

						if (piece_Already_There(placeCheck))
							if (!see_EnemyKingIsChecked(enemy)) {
								wPawn.setPoint(oldP1);

								if (something_killed) {
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed = false;
								}
								return false;
							}
					}

					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}

				}
				// quân hậu đi đường phải trên
				for (int x = oldP1.x, y = oldP1.y; y >= 1 && x <= 8; x++, y--) {

					wPawn.setX(x);
					wPawn.setY(y);
					placeCheck.x = x;
					placeCheck.y = y;
					if (checkWayToPosition(enemy, oldP1)) {
						if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
							something_killed = true;
						}

						if (piece_Already_There(placeCheck))
							if (!see_EnemyKingIsChecked(enemy)) {
								wPawn.setPoint(oldP1);
								if (something_killed) {
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed = false;
								}
								return false;
							}
					}

					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}

				}
				wPawn.setPoint(oldP1);
				placeCheck.x = oldP1.x;
				placeCheck.y = oldP1.y;
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
				// quan hau chay hang ngang
				for (int i = 1; i <= 8; i++) {
					wPawn.setX(i);
					placeCheck.x = i;
					if (checkWayToPosition(enemy, oldP1)) {
						if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
							something_killed = true;
						}

						if (piece_Already_There(placeCheck))
							if (!see_EnemyKingIsChecked(enemy)) {
								wPawn.setX(oldP1.x);
								if (something_killed) {
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed = false;
								}
								return false;
							}
					}
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
				}
				wPawn.setX(oldP1.x);
				placeCheck.x = oldP1.x;
				if (something_killed) {
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed = false;
				}
				// quân hậu chạy hàng dọc
				for (int i = 1; i <= 8; i++) {
					wPawn.setY(i);
					placeCheck.y = i;
					if (checkWayToPosition(enemy, oldP1)) {
						if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
							something_killed = true;
						}

						if (piece_Already_There(placeCheck))
							if (!see_EnemyKingIsChecked(enemy)) {
								wPawn.setY(oldP1.y);
								if (something_killed) {
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed = false;
								}
								return false;
							}
					}
					if (something_killed) {
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed = false;
					}
				}
				wPawn.setY(oldP1.y);
			}
			if (something_killed) {
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				something_killed = false;
			}
			return true;
		}
	}

	public boolean checkWayToPosition(Player2 enemy, Point newP) {
		boolean flag = false;
		for (int i = 0; i <= 32; i++) {
			if (inHand != i) {
				if (i < 17) {
					flag = checkWay(newP, enemy.returnPosition(i), inHand);
				} else {
					flag = checkWay(newP, returnPosition(i), inHand);

				}
				if (flag == true) {
					return false; // ở vị trí này có quân cờ
				}
			}
		}
		return true;
	}

	// phải giết quân để bảo vệ vua
	public boolean kill_To_Protect_King(Player2 enemy, Point newP) {
		// duyệt qua các quân đối phương
		for (int i = 1; i < 17; i++) {
			other = enemy.returnPosition(i);
			if (other.x == newP.x && other.y == newP.y) {
				piece_Is_Killed_To_Protect_King = i;
				enemy.killedPiece(i);
				return true;
			}
		}
		return false;

	}

	public void honor_Queen(int i) {
		switch (i) {
		case 25:
			if (!wPawns[0].isPawn_becomes_Queen()) {
				wPawns[0].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 26:
			if (!wPawns[1].isPawn_becomes_Queen()) {
				wPawns[1].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 27:
			if (!wPawns[2].isPawn_becomes_Queen()) {
				wPawns[2].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 28:
			if (!wPawns[3].isPawn_becomes_Queen()) {
				wPawns[3].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 29:
			if (!wPawns[4].isPawn_becomes_Queen()) {
				wPawns[4].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 30:
			if (!wPawns[5].isPawn_becomes_Queen()) {
				wPawns[5].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 31:
			if (!wPawns[6].isPawn_becomes_Queen()) {
				wPawns[6].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		case 32:
			if (!wPawns[7].isPawn_becomes_Queen()) {
				wPawns[7].setPawn_becomes_Queen(true);
				System.out.println("Da tien hoa thanh Hau");
			}
			break;
		}
	}
	public void moved_Success(int i) {
		switch (i) {
		case 25:
			if (!wPawns[0].isMove_sucess_one_times()) {
				wPawns[0].setMove_sucess_one_times(true);
				
			}
			break;
		case 26:
			if (!wPawns[1].isMove_sucess_one_times()) {
				wPawns[1].setMove_sucess_one_times(true);
				
			}
			break;
		case 27:
			if (!wPawns[2].isMove_sucess_one_times()) {
				wPawns[2].setMove_sucess_one_times(true);
				
			}
			break;
		case 28:
			if (!wPawns[3].isMove_sucess_one_times()) {
				wPawns[3].setMove_sucess_one_times(true);
				
			}
			break;
		case 29:
			if (!wPawns[4].isMove_sucess_one_times()) {
				wPawns[4].setMove_sucess_one_times(true);
				
			}
			break;
		case 30:
			if (!wPawns[5].isMove_sucess_one_times()) {
				wPawns[5].setMove_sucess_one_times(true);
				
			}
			break;
		case 31:
			if (!wPawns[6].isMove_sucess_one_times()) {
				wPawns[6].setMove_sucess_one_times(true);
				
			}
			break;
		case 32:
			if (!wPawns[7].isMove_sucess_one_times()) {
				wPawns[7].setMove_sucess_one_times(true);
				
			}
			break;
		}
	}
	public String Tell_me_About_last_move() {
		switch (inHand) {

		case 17:
			return wr1.tell_me();
		case 18:
			return wr2.tell_me();
		case 19:
			return wn1.tell_me();
		case 20:
			return wn2.tell_me();
		case 21:
			return wb1.tell_me();
		case 22:
			return wb2.tell_me();
		case 23:
			return wq.tell_me();
		case 24:
			return wk.tell_me();
		case 25:
			return wPawns[0].tell_me();
		case 26:
			return wPawns[1].tell_me();
		case 27:
			return wPawns[2].tell_me();
		case 28:
			return wPawns[3].tell_me();
		case 29:
			return wPawns[4].tell_me();
		case 30:
			return wPawns[5].tell_me();
		case 31:
			return wPawns[6].tell_me();
		case 32:
			return wPawns[7].tell_me();
		}
		return null;
	}
}
