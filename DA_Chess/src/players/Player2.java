package players;

import java.awt.Image;
import java.awt.Point;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class Player2 {
	// khởi tạo các quân cờ
		public Rook br1; // quan xe ben trai
		public Rook br2; // quan xe ben phai
		public Knight bn1;
		public Knight bn2;
		public Bishop bb1;
		public Bishop bb2;
		public Queen bq;
		public King bk;
		// khởi tạo 8 con tốt white
		public Pawn[] bPawns;
		private int inHand; //
		private boolean kingIsChecked; // bị chiếu vua
		private int choosenOne;
		private String Color; // màu sắc quân
		private Point other;
		private int beginPosition; // vị trí khởi đầu

		public Player2() {
			bPawns = new Pawn[8];
			inHand = -1;
			kingIsChecked = false;
			Color = "white";
			String fileSeparator = new String(System.getProperty("file.separator")); // lấy
																						// dấu
																						// chéo
																						// ngược(
																						// \
																						// )
			br1 = new Rook("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "br.gif", 1, 8);
			br2 = new Rook("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "br.gif", 8, 8);
			bn1 = new Knight("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bn.gif", 2,
					8);
			bn2 = new Knight("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bn.gif", 7,
					8);
			bb1 = new Bishop("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bb.gif", 3,
					8);
			bb2 = new Bishop("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bb.gif", 6,
					8);
			bq = new Queen("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bq.gif", 4, 8);
			bk = new King("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bk.gif", 5, 8);
			// khởi tạo tốt
			int j = 1;
			for (int i = 0; i < 8; i++) {
				bPawns[i] = new Pawn(
						"src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bp.gif", j, 7);
				j++;
			}
		}

		public Point returnPosition(int i) {

			switch (i) {

			case 1:
				return br1.returnPosition();
			case 2:
				return br2.returnPosition();
			case 3:
				return bn1.returnPosition();
			case 4:
				return bn2.returnPosition();
			case 5:
				return bb1.returnPosition();
			case 6:
				return bb2.returnPosition();
			case 7:
				return bq.returnPosition();
			case 8:
				return bk.returnPosition();
			case 9:
				return bPawns[0].returnPosition();
			case 10:
				return bPawns[1].returnPosition();
			case 11:
				return bPawns[2].returnPosition();
			case 12:
				return bPawns[3].returnPosition();
			case 13:
				return bPawns[4].returnPosition();
			case 14:
				return bPawns[5].returnPosition();
			case 15:
				return bPawns[6].returnPosition();
			case 16:
				return bPawns[7].returnPosition();
			}
			return new Point(-1, -1);
		}

		public Point returnOldPosition(int i) {
			switch (i) {

			case 1:
				return br1.getPointOld();
			case 2:
				return br2.getPointOld();
			case 3:
				return bn1.getPointOld();
			case 4:
				return bn2.getPointOld();
			case 5:
				return bb1.getPointOld();
			case 6:
				return bb2.getPointOld();
			case 7:
				return bq.getPointOld();
			case 8:
				return bk.getPointOld();
			case 9:
				return bPawns[0].getPointOld();
			case 10:
				return bPawns[1].getPointOld();
			case 11:
				return bPawns[2].getPointOld();
			case 12:
				return bPawns[3].getPointOld();
			case 13:
				return bPawns[4].getPointOld();
			case 14:
				return bPawns[5].getPointOld();
			case 15:
				return bPawns[6].getPointOld();
			case 16:
				return bPawns[7].getPointOld();
			}
			return new Point(-1, -1);
		}

		public Image returnIconImage(int i) {

			switch (i) {
			case 1: {
				return br1.getImageIcon();
			}
			case 2: {
				return br2.getImageIcon();
			}
			case 3: {
				return bn1.getImageIcon();
			}
			case 4: {
				return bn2.getImageIcon();
			}
			case 5:
				return bb1.getImageIcon();
			case 6:
				return bb2.getImageIcon();
			case 7:
				return bq.getImageIcon();
			case 8:
				return bk.getImageIcon();
			case 9:
				return bPawns[0].getImageIcon();
			case 10:
				return bPawns[1].getImageIcon();
			case 11:
				return bPawns[2].getImageIcon();
			case 12:
				return bPawns[3].getImageIcon();
			case 13:
				return bPawns[4].getImageIcon();
			case 14:
				return bPawns[5].getImageIcon();
			case 15:
				return bPawns[6].getImageIcon();
			case 16:
				return bPawns[7].getImageIcon();
			}
			return null;
		}

		public void changePosition(Point newPoint, int i) {
			switch (i) {
			case 1:
				br1.setPoint(newPoint);
				break;
			case 2:
				br2.setPoint(newPoint);
				break;
			case 3:
				bn1.setPoint(newPoint);
				break;
			case 4:
				bn2.setPoint(newPoint);
				break;
			case 5:
				bb1.setPoint(newPoint);
				break;
			case 6:
				bb2.setPoint(newPoint);
				break;
			case 7:
				bq.setPoint(newPoint);
				break;
			case 8:
				bk.setPoint(newPoint);
				break;
			case 9:
				bPawns[0].setPoint(newPoint);
				break;
			case 10:
				bPawns[1].setPoint(newPoint);
				break;
			case 11:
				bPawns[2].setPoint(newPoint);
				break;
			case 12:
				bPawns[3].setPoint(newPoint);
				break;
			case 13:
				bPawns[4].setPoint(newPoint);
				break;
			case 14:
				bPawns[5].setPoint(newPoint);
				break;
			case 15:
				bPawns[6].setPoint(newPoint);
				break;
			case 16:
				bPawns[7].setPoint(newPoint);
				break;
			}
		}
		public void changePositionToOld(Point newPoint,int i) {
	        switch(i) {
	            
	            case 1:br1.toPointOld(newPoint);break;
	            case 2:br2.toPointOld(newPoint);break;
	            case 3:bn1.toPointOld(newPoint);break;
	            case 4:bn2.toPointOld(newPoint);break;
	            case 5: bb1.toPointOld(newPoint);break;
	            case 6: bb2.toPointOld(newPoint);break;
	            case 7: bq.toPointOld(newPoint);break;
	            case 8: bk.toPointOld(newPoint);break;
	            case 9:  bPawns[0].toPointOld(newPoint);break;
	            case 10:  bPawns[1].toPointOld(newPoint);break;
	            case 11:  bPawns[2].toPointOld(newPoint);break;
	            case 12:  bPawns[3].toPointOld(newPoint);break;
	            case 13:  bPawns[4].toPointOld(newPoint);break;
	            case 14:  bPawns[5].toPointOld(newPoint);break;
	            case 15:  bPawns[6].toPointOld(newPoint);break;
	            case 16:  bPawns[7].toPointOld(newPoint);break;
	        }
	    }
		public void changePixel(int newPixelX,int newPixelY,int i) {
	        choosenOne=i;
	        switch(choosenOne) {
	            
	            case 1:br1.setPixelPoint(newPixelX,newPixelY);break;
	            case 2:br2.setPixelPoint(newPixelX,newPixelY);break;
	            case 3:bn1.setPixelPoint(newPixelX,newPixelY);break;
	            case 4:bn2.setPixelPoint(newPixelX,newPixelY);break;
	            case 5: bb1.setPixelPoint(newPixelX,newPixelY);break;
	            case 6: bb2.setPixelPoint(newPixelX,newPixelY);break;
	            case 7: bq.setPixelPoint(newPixelX,newPixelY);break;
	            case 8: bk.setPixelPoint(newPixelX,newPixelY);break;
	            case 9:  bPawns[0].setPixelPoint(newPixelX,newPixelY);break;
	            case 10:  bPawns[1].setPixelPoint(newPixelX,newPixelY);break;
	            case 11:  bPawns[2].setPixelPoint(newPixelX,newPixelY);break;
	            case 12:  bPawns[3].setPixelPoint(newPixelX,newPixelY);break;
	            case 13:  bPawns[4].setPixelPoint(newPixelX,newPixelY);break;
	            case 14:  bPawns[5].setPixelPoint(newPixelX,newPixelY);break;
	            case 15:  bPawns[6].setPixelPoint(newPixelX,newPixelY);break;
	            case 16:  bPawns[7].setPixelPoint(newPixelX,newPixelY);break;
	        }
	    }
		public Point getPixelPoint(int i) {
	        choosenOne=i;
	        switch(choosenOne) {
	            
	            case 1:return br1.getPixelPoint();
	            case 2:return br2.getPixelPoint();
	            case 3:return bn1.getPixelPoint();
	            case 4:return bn2.getPixelPoint();
	            case 5: return bb1.getPixelPoint();
	            case 6: return bb2.getPixelPoint();
	            case 7: return bq.getPixelPoint();
	            case 8: return bk.getPixelPoint();
	            case 9: return  bPawns[0].getPixelPoint();
	            case 10:  return bPawns[1].getPixelPoint();
	            case 11: return  bPawns[2].getPixelPoint();
	            case 12:  return bPawns[3].getPixelPoint();
	            case 13: return  bPawns[4].getPixelPoint();
	            case 14: return bPawns[5].getPixelPoint();
	            case 15: return bPawns[6].getPixelPoint();
	            case 16: return bPawns[7].getPixelPoint();
	        }
	        return null;
	    }
		public boolean checkMove(Point newP,int i)
		{
			 choosenOne=i;
		        switch(choosenOne) {
		            
		            case 1:return br1.canMove(newP.x,newP.y);
		            case 2:return br2.canMove(newP.x,newP.y);
		            case 3:return bn1.canMove(newP.x,newP.y);
		            case 4:return bn2.canMove(newP.x,newP.y);
		            case 5: return bb1.canMove(newP.x,newP.y);
		            case 6: return bb2.canMove(newP.x,newP.y);
		            case 7: return bq.canMove(newP.x,newP.y);
		            case 8: return bk.canMove(newP.x,newP.y);
		            case 9: return  bPawns[0].canMove(newP.x,newP.y,Color);
		            case 10:  return bPawns[1].canMove(newP.x,newP.y,Color);
		            case 11: return  bPawns[2].canMove(newP.x,newP.y,Color);
		            case 12:  return bPawns[3].canMove(newP.x,newP.y,Color);
		            case 13: return  bPawns[4].canMove(newP.x,newP.y,Color);
		            case 14: return bPawns[5].canMove(newP.x,newP.y,Color) ;
		            case 15: return bPawns[6].canMove(newP.x,newP.y,Color);
		            case 16: return bPawns[7].canMove(newP.x,newP.y,Color);
		        }
		        return false;
		}
		public boolean setSeentoPawns(int i,Point P) {
	        switch(i) {
	            case 9:   return  bPawns[0].setSeenByCheckKing(P,"black");
	            case 10:   return bPawns[1].setSeenByCheckKing(P,"black");
	            case 11:  return   bPawns[2].setSeenByCheckKing(P,"black");
	            case 12:  return    bPawns[3].setSeenByCheckKing(P,"black");
	            case 13:  return  bPawns[4].setSeenByCheckKing(P,"black");
	            case 14:  return  bPawns[5].setSeenByCheckKing(P,"black");
	            case 15:  return  bPawns[6].setSeenByCheckKing(P,"black");
	            case 16: return bPawns[7].setSeenByCheckKing(P,"black");
	        }
	        return false;
	    }
	    public boolean returnsoliderSeen(int i) {
	        switch(i) {
	            case 9:   return  bPawns[0].isMySeen();
	            case 10:   return bPawns[1].isMySeen();
	            case 11:  return   bPawns[2].isMySeen();
	            case 12:  return    bPawns[3].isMySeen();
	            case 13:  return  bPawns[4].isMySeen();
	            case 14:  return  bPawns[5].isMySeen();
	            case 15:  return  bPawns[6].isMySeen();
	            case 16: return bPawns[7].isMySeen();
	        }
	        return false;
	    }
	    public boolean checkWay(Point newP,Point postionFromOthers,int i) {
	        switch(i) {
	            case 1:return br1.isPieceInMyWay(newP.x,newP.y,postionFromOthers);
	            case 2:return br2.isPieceInMyWay(newP.x,newP.y,postionFromOthers);
	            case 5: return bb1.isPieceInMyWay(newP.x,newP.y,postionFromOthers);
	            case 6: return bb2.isPieceInMyWay(newP.x,newP.y,postionFromOthers);
	            case 7: return bq.isPieceInMyWay(newP.x,newP.y,postionFromOthers);
	            case 9: return  bPawns[0].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 10:  return bPawns[1].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 11: return  bPawns[2].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 12:  return bPawns[3].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 13: return  bPawns[4].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 14: return bPawns[5].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 15: return bPawns[6].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            case 16: return bPawns[7].isPieceInMyWay(newP.x,newP.y,postionFromOthers,Color);
	            
	        }
	        return false;
	    }
	    public boolean killedPiece(int i) {
	        Point out=new Point(20,20);
	        switch(i) {
	            
	            case 1:  br1.setPoint(out);return true;
	            case 2:   br2.setPoint(out);return true;
	            case 3:  bn1.setPoint(out);return true;
	            case 4:  bn2.setPoint(out);return true;
	            case 5:   bb1.setPoint(out);return true;
	            case 6:   bb2.setPoint(out);return true;
	            case 7:   bq.setPoint(out);return true;
	            case 8:    return false;
	            case 9:    bPawns[0].setPoint(out);return true;
	            case 10:   bPawns[1].setPoint(out);return true;
	            case 11:   bPawns[2].setPoint(out);return true;
	            case 12:    bPawns[3].setPoint(out);return true;
	            case 13:    bPawns[4].setPoint(out);return true;
	            case 14:   bPawns[5].setPoint(out);return true;
	            case 15:   bPawns[6].setPoint(out);return true;
	            case 16:   bPawns[7].setPoint(out);return true;
	        }
	        
	        return false;
	    }
	    public boolean checkKing(Point p1,Point p2,int i) {
	        switch(i) {
	            case 1:return  br1.checkKing(p1.x,p1.y,p2);
	            case 2:return br2.checkKing(p1.x,p1.y,p2);
	            case 3:return  bn1.canMove(p1.x,p1.y);
	            case 4:return  bn2.canMove(p1.x,p1.y);
	            case 5:return   bb1.checkKing(p1.x,p1.y,p2);
	            case 6:return   bb2.checkKing(p1.x,p1.y,p2);
	            case 7:return   bq.checkKing(p1.x,p1.y,p2);
	            
	            case 9:return    bPawns[0].canMove(p1.x,p1.y,Color);
	            case 10:return   bPawns[1].canMove(p1.x,p1.y,Color);
	            case 11:return   bPawns[2].canMove(p1.x,p1.y,Color);
	            case 12:return    bPawns[3].canMove(p1.x,p1.y,Color);
	            case 13:return    bPawns[4].canMove(p1.x,p1.y,Color);
	            case 14: return  bPawns[5].canMove(p1.x,p1.y,Color);
	            case 15:return   bPawns[6].canMove(p1.x,p1.y,Color);
	            case 16:return   bPawns[7].canMove(p1.x,p1.y,Color);
	        }
	        return false;
	    }
	    public int returnChosen() {
	        return choosenOne;
	    }
	    public void SetInHand(int i ) {
	        inHand=i;
	    }
	    public int GetInHand() {
	        return inHand;
	    }
	    public boolean canMove(int x,int y) {
	        return true;
	    }
	    public void checkKing(boolean newkingcheck) {
	        kingIsChecked=newkingcheck;
	    }
	    public boolean returncheckKing() {
	        return kingIsChecked;
	    }
	    public boolean if_MyKingIsChecked(Player2 Black) //neu vua cua toi bi chieu
	    {
	    	 boolean isCheckmate=false;
	         boolean flag=false;
	         return false;
	    }
}
