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
		private int piece_Is_Killed_To_Protect_King; // vị trí khởi đầu

		public Player2() {
			bPawns = new Pawn[8];
			inHand = -1;
			kingIsChecked = false;
			Color = "black";
			String fileSeparator = new String(System.getProperty("file.separator")); // lấy
																						// dấu
																						// chéo
																						// ngược(
																						// \
																						// )
			br1 = new Rook("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "br.gif", 1, 1);
			br2 = new Rook("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "br.gif", 8, 1);
			bn1 = new Knight("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bn.gif", 2,
					1);
			bn2 = new Knight("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bn.gif", 7,
					1);
			bb1 = new Bishop("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bb.gif", 3,
					1);
			bb2 = new Bishop("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bb.gif", 6,
					1);
			bq = new Queen("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bq.gif", 4, 1);
			bk = new King("src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bk.gif", 5, 1);
			// khởi tạo tốt
			int j = 1;
			for (int i = 0; i < 8; i++) {
				bPawns[i] = new Pawn(
						"src" + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator + "bp.gif", j, 2);
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
	    public boolean if_MyKingIsChecked(Player1 White) //neu vua cua toi bi chieu
	    {
	    	 boolean isCheckmate=false;
	         boolean flag=false;
	         return false;
	    }
	    //-_____________ start code go on
	    public boolean checkEmenyGameOver(Player1 enemy)
	    {
	    	//nếu tất cả các quân cờ đều không được di chuyển , coi như game over
	    	if(!KingGenerate_Moves(enemy))
	    	{
	    		inHand=-1;
	    		return false;
	    	}else if(!RookGenerate_Moves(enemy, this.br1))
	    	{
	    		inHand=-1;
	    		return false;
	    	} else if(!RookGenerate_Moves(enemy, br2))
	    	{
	    		inHand=-1;
	    		return false;
	    	} else if(!BishopGenerate_Moves(enemy, bb1))
	    	{
	    		inHand=-1;
	    		return false;
	    	} else if(!BishopGenerate_Moves(enemy, bb2))
	    	{
	    		inHand=-1;
	    		return false;
	    	} else if(!KnightGenerate_Moves(enemy, bn1))
	    	{
	    		inHand=-1;
	    		return false;
	    	} else if(!KnightGenerate_Moves(enemy, bn2))
	    	{
	    		inHand=-1;
	    		return false;
	    	} else if(!QueenGenerate_Moves(enemy))
	    	{
	    		inHand=-1;
	    		return false;
	    	} 
	    	for(int i=0;i<=7;i++)
	    	{
	    		inHand=9+i;
	    		if(!PawnGenerate_Moves(enemy, bPawns[i]))
	    		{
	    			inHand=-1;
	        		return false;
	    		}
	    	}
	    	inHand=-1;
			return true;
	    }
		public boolean see_EnemyKingIsChecked(Player1 white) {
			Point My_King_Position = this.bk.returnPosition();
			boolean flag = false;
			// start checking the King
			// duyet qua tất cả 16 quân cờ của đối phương
			for (int i = 17; i < 33; i++) {
				if (i < 25) {
					// tức là chỉ duyệt trong phạm vi quân chủ lực hàng dưới
					if (white.checkMove(My_King_Position, i)) {
						flag = true;
						for (int j = 1; j < 33; j++) {
							if (j < 17) {
								if (white.checkWay(My_King_Position, returnPosition(j), i)) {
									// Means there is somting in the Way so can't
									// move'
									flag = false;
								}
							} else {

								if (j != 8)
									if (white.checkWay(My_King_Position, white.returnPosition(j), i)) {

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
					if (white.setSeentoPawns(i, My_King_Position)) {
						break;
					}
				}
				if (i == 32) {
					return false;
				}
			}
			return true;
		}
		//check nếu vị trí tiến tới là quân white (quân phe mình)
		public boolean piece_Already_There(Point newP)
		{
			 Point samePostion;
		        for(int i=1;i<=16;i++) {
		            if(this.GetInHand()!=i)// There is no need to check the inHand pice
		            {
		                //Check if there is White Pices in the new Point
		                //If so we Can't move (Same Color)!!
		                samePostion=returnPosition(i);
		                if(newP.x==samePostion.x&&newP.y==samePostion.y) {
		                    return false;
		                }
		            }
		        }
		        return true;
		}
		//check nếu vị trí đặt quân là quân enemy :Black
		public boolean piece_Enemy_Already_There(Point newP,Player1 enemy)
		{
			Point samePostion;
	        for(int i=17;i<=32;i++) {
	            samePostion=enemy.returnPosition(i);
	            if(newP.x==samePostion.x&&newP.y==samePostion.y) {
	                return false;
	            }
	        }
	        return true;
		}
		// lay quan co enemy ở đấy, trả về giá trị int của quân cờ
		public int getPiece_Enemy_Already_There(Point newP,Player1 enemy)
		{
			Point samePostion;
	        for(int i=17;i<=32;i++) {
	            samePostion=enemy.returnPosition(i);
	            if(newP.x==samePostion.x&&newP.y==samePostion.y) {
	                return i;
	            }
	        }
	        return -1;
		}

		public boolean KingGenerate_Moves(Player1 enemy) {
			boolean something_killed = false;
			Point oldP = new Point();
			Point placeCheck = new Point();
			inHand = 8; // set gia tri luc nay la 24 , tuc la quan Vua
			int x = bk.getX();
			int y = bk.getY();
			oldP.x = x;
			oldP.y = y;
			if (x + 1 <= 8) // khi quân vua di chuyển sang bên phải và trong bàn cờ
			{
				bk.setX(x + 1);
				bk.setY(y);
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
					if (!see_EnemyKingIsChecked(enemy)) // nếu vua đối phương không
														// bị chiếu
					{
						// Vị trí bên phải quân KIng ko đi được, phải đưa quân King
						// lại vị trí ban đầu
						bk.setPoint(oldP);
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
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				// thay đổi vị trí của quân đó
				something_killed = false;
			}
			if (y + 1 <= 8) // Vua đi xuống dưới
			{
				bk.setX(x);
				bk.setY(y + 1);
				placeCheck.x = x;
				placeCheck.y = y + 1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
			}
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King); 
				something_killed = false;
			}
			if (y - 1 > 0)// vua đi lên trên
			{
				bk.setX(x);
				bk.setY(y - 1);
				placeCheck.x = x;
				placeCheck.y = y - 1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}

			}
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King); 
				something_killed = false;
			}
			if (x - 1 > 0) // nếu vua đi sang trái
			{
				bk.setX(x - 1);
				bk.setY(y);
				placeCheck.x = x - 1;
				placeCheck.y = y;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}

			}
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King); 
				something_killed = false;
			}
			if (y - 1 > 0 && x - 1 > 0) //Vua đi sang phía trái trên 
			{
				bk.setX(x - 1);
				bk.setY(y-1);
				placeCheck.x = x - 1;
				placeCheck.y = y-1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
			}
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King); 
				something_killed = false;
			}
			if(y+1 <=8 && x+1<=8) //Vua đi sang phía phải dưới ( phía dưới bên phải)
			{
				bk.setX(x + 1);
				bk.setY(y+1);
				placeCheck.x = x + 1;
				placeCheck.y = y+1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
			}
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King); 
				something_killed = false;
			}
			if(y-1 >0 && x+1 <=8) //Vua đi sang phía phải trên( tức là lên trên và phía bên phải)
			{
				bk.setX(x + 1);
				bk.setY(y-1);
				placeCheck.x = x + 1;
				placeCheck.y = y-1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
						if (something_killed) {
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							something_killed = false;
						}
						return false;
					}
			}
			bk.setPoint(oldP);
			if (something_killed) // nếu như có quân bị giết
			{
				enemy.changePosition(other, piece_Is_Killed_To_Protect_King); 
				something_killed = false;
			}
			if(y+1 <=8 && x-1 >0) //vua đi sang phía trái dưới ( tức đi xuống dưới phía bên trái)
			{
				bk.setX(x - 1);
				bk.setY(y+1);
				placeCheck.x = x - 1;
				placeCheck.y = y+1;
				if (kill_To_Protect_King(enemy, returnPosition(inHand))) {
					something_killed = true;
				}
				if (piece_Already_There(placeCheck))
					if (!see_EnemyKingIsChecked(enemy)) {

						bk.setPoint(oldP);
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
			bk.setPoint(oldP);
			return true;
			
		}
		//di chuyển quân xe (Rook)
		public boolean RookGenerate_Moves(Player1 enemy,Rook bRook) {
			boolean something_killed=false;
			Point oldP1 = new Point();
			Point placeCheck= new Point();
			int x1=bRook.getX();
			int y1=bRook.getY();
			//kiểm tra xem quân Rook này để gán vào đối tượng đang được chọn hiện tại là quân xe này
			if(bRook ==this.br1)
			{
				inHand=1;
			}
			else
			{
				inHand=2;
			}
			//_______________________________
			oldP1.x=x1;
			oldP1.y=y1;
			placeCheck.y=y1;
			if(x1!=20)
			{
				//nếu như quân này chưa chết ( tọa độ x=20 là quân này đã chết)
				//chạy hàng ngang
				for(int i=1;i<=8;i++)
				{
					bRook.setX(i);
					placeCheck.x=i;
					if(checkWayToPosition(enemy, oldP1))
					{
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								//set lại giá trị vị trí trước đó cho quân xe
								//mean: đưa quân xe trở lại vị trí ban đầu sau nước đó
								bRook.setX(oldP1.x);
								bRook.setY(oldP1.y);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
					}
					if(something_killed)
					{
						enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				}
				bRook.setX(oldP1.x);
				placeCheck.x=oldP1.x;
				if(something_killed)
				{
					enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
					something_killed=false;
				}
				// rook chạy hàng dọc
				for(int i=1;i<=8;i++)
				{
					bRook.setY(i);
					placeCheck.y=i;
					if(checkWayToPosition(enemy, oldP1))
					{
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								//set lại giá trị vị trí trước đó cho quân xe
								//mean: đưa quân xe trở lại vị trí ban đầu sau nước đó
								bRook.setX(oldP1.x);
								bRook.setY(oldP1.y);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
					}
					if(something_killed)
					{
						enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				}
				bRook.setY(oldP1.y);
			}
			//-_______________________
			//set lại vị trí cũ cho quân xe
			if(something_killed)
			{
				enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
				something_killed=false;
			}
			bRook.setX(oldP1.x);
			bRook.setY(oldP1.y);
			
			return true;
			
		}
		public boolean BishopGenerate_Moves(Player1 enemy,Bishop bBishop)
		{
			 boolean something_killed=false;
		        Point oldP1=new Point();
		        Point placeCheck=new Point();
		        oldP1=bBishop.returnPosition();
		        if(bBishop==this.bb1)
		        {
		        	inHand=5;
		        }
		        else
		        {
		        	inHand=6;
		        }
		        if(oldP1.x!=20)
		        {
		        	//quân Tượng chạy đường chéo hướng dưới trái
		        	for(int x=oldP1.x,y=oldP1.y;x>=1&&y<=8;x--,y++)
		        	{
		        		bBishop.setX(x);
		        		bBishop.setY(y);
		        		placeCheck.x=x;
		        		placeCheck.y=y;
		        		if(checkWayToPosition(enemy, oldP1))
		        		{
		        			if(kill_To_Protect_King(enemy, returnPosition(inHand)))
		        			{
		        				something_killed=true;
		        			}
		        			if(piece_Already_There(placeCheck))
		        			{
		        				if(!see_EnemyKingIsChecked(enemy))
		        				{
		        					if(something_killed)
		        					{
		        						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
		        						something_killed=false;
		        					}
		        					bBishop.setPoint(oldP1);
		        					return false;
		        				}
		        			}
		        		}
		        		if(something_killed)
		        		{
		        			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
		        			something_killed=false;
		        		}
		        	}
		        	if(something_killed)
	        		{
	        			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
	        			something_killed=false;
	        		}
		        	//TƯợng chạy hướng trên phải
		        	for(int x=oldP1.x,y=oldP1.y;y>=1&&x<=8;x++,y--)
		        	{
		        		bBishop.setX(x);
		        		bBishop.setY(y);
		        		placeCheck.x=x;
		        		placeCheck.y=y;
		        		if(checkWayToPosition(enemy, oldP1))
		        		{
		        			if(kill_To_Protect_King(enemy, returnPosition(inHand)))
		        			{
		        				something_killed=true;
		        			}
		        			if(piece_Already_There(placeCheck))
		        			{
		        				if(!see_EnemyKingIsChecked(enemy))
		        				{
		        					if(something_killed)
		        					{
		        						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
		        						something_killed=false;
		        					}
		        					bBishop.setPoint(oldP1);
		        					return false;
		        				}
		        			}
		        		}
		        		if(something_killed)
		        		{
		        			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
		        			something_killed=false;
		        		}
		        	}
		        	bBishop.setPoint(oldP1);
		        }
		        bBishop.setPoint(oldP1);
		        if(something_killed)
	    		{
	    			enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
	    			something_killed=false;
	    		}
		       
		        return true;
		}
		public boolean KnightGenerate_Moves(Player1 enemy,Knight bKnight)
		{
			Point oldP1= new Point();
			boolean something_killed=false;
			oldP1=bKnight.returnPosition();
			Point placeCheck=new Point();
			if(bKnight==this.bn1)
			{
				inHand=3;
			}
			else
			{
				inHand=4;
			}
			int x=oldP1.x;
			int y= oldP1.y;
			//neu nhu quan Ngua nay chua chet
			if(x!=20)
			{
				if(x+1<=8 && y+2 <=8)
				{
					bKnight.setX(x+1);
					bKnight.setY(y+2);
					placeCheck.x=x+1;
					placeCheck.y=y+2;
					if(kill_To_Protect_King(enemy, returnPosition(inHand)))
					{
						something_killed=true;
					}
					if(piece_Already_There(placeCheck))
					{
						if(!see_EnemyKingIsChecked(enemy))
						{
							bKnight.setPoint(oldP1);
							if(something_killed)
							{
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed=false;
							}
							return false;
						}
					}
				}
				if(something_killed)
				{
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed=false;
				}
				if(x+1<=8 && y-2>=1)
				{
					bKnight.setX(x+1);
					bKnight.setY(y-2);
					placeCheck.x=x+1;
					placeCheck.y=y-2;
					if(kill_To_Protect_King(enemy, returnPosition(inHand)))
					{
						something_killed=true;
					}
					if(piece_Already_There(placeCheck))
					{
						if(!see_EnemyKingIsChecked(enemy))
						{
							bKnight.setPoint(oldP1);
							if(something_killed)
							{
								enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
								something_killed=false;
							}
							return false;
						}
					}
				}
				if(something_killed)
				{
					enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					something_killed=false;
				}
				 if(x+2<= 8&& y+1<=8) {
					 	bKnight.setX(x+2);
						bKnight.setY(y+1);
						placeCheck.x=x+2;
						placeCheck.y=y+1;
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bKnight.setPoint(oldP1);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
				 }
				 if(something_killed)
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				 if(x+2<=8 && y-1>=1) {
					 bKnight.setX(x+2);
						bKnight.setY(y-1);
						placeCheck.x=x+2;
						placeCheck.y=y-1;
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bKnight.setPoint(oldP1);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
				 }
				 if(something_killed)
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				 if(x-1>=1&&y+2<=8) {
					bKnight.setX(x-1);
						bKnight.setY(y+2);
						placeCheck.x=x-1;
						placeCheck.y=y+2;
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bKnight.setPoint(oldP1);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
				 }
				 if(something_killed)
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				 if(x-1>=1&&y-2>=1) {
					 bKnight.setX(x-1);
						bKnight.setY(y-2);
						placeCheck.x=x-1;
						placeCheck.y=y-2;
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bKnight.setPoint(oldP1);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
				 }
				 if(something_killed)
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				 if(x-2>=1&&y+1<=8) {
					 bKnight.setX(x-2);
						bKnight.setY(y+1);
						placeCheck.x=x-2;
						placeCheck.y=y+1;
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bKnight.setPoint(oldP1);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
				 }
				 if(something_killed)
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				 if(x-2>=1&&y-1>=1) {
					bKnight.setX(x-2);
						bKnight.setY(y-1);
						placeCheck.x=x-2;
						placeCheck.y=y-1;
						if(kill_To_Protect_King(enemy, returnPosition(inHand)))
						{
							something_killed=true;
						}
						if(piece_Already_There(placeCheck))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bKnight.setPoint(oldP1);
								if(something_killed)
								{
									enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
									something_killed=false;
								}
								return false;
							}
						}
				 }
				 if(something_killed)
					{
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						something_killed=false;
					}
				
			}
			bKnight.setPoint(oldP1);
			 return true;
		}
		public boolean QueenGenerate_Moves(Player1 enemy)
		{
			boolean something_killed=false;
			Point oldP1= new Point();
			oldP1=this.bq.returnPosition();
			Point placeCheck= new Point();
			inHand=7;
			if(oldP1.x!=20)
			{
				//quân Hậu đi đường chéo trái dưới
				 for(int x= oldP1.x,y= oldP1.y;x>=1 && y<=8;x--,y++) {
		                
		                bq.setX(x);
		                bq.setY(y);
		                placeCheck.x=x;
		                placeCheck.y=y;
		                if(checkWayToPosition(enemy, oldP1)) {
		                    if(kill_To_Protect_King(enemy,returnPosition(inHand))) {
		                        something_killed=true;
		                    }
		                    
		                    if(piece_Already_There(placeCheck))
		                        if(!see_EnemyKingIsChecked(enemy)) {
		                        bq.setPoint(oldP1);
		                        
		                        if(something_killed) {
		                            enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
		                            something_killed=false;
		                        }
		                        return false;
		                        }
		                }
		                
		                if(something_killed) {
		                    enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
		                    something_killed=false;
		                }
		                
		            }
				 //quân hậu đi đường phải trên 
				 for(int x= oldP1.x,y= oldP1.y;y>=1 && x<=8;x++,y--) {
		                
		                bq.setX(x);
		                bq.setY(y);
		                placeCheck.x=x;
		                placeCheck.y=y;
		                if(checkWayToPosition(enemy, oldP1)) {
		                    if(kill_To_Protect_King(enemy,returnPosition(inHand))) {
		                        something_killed=true;
		                    }
		                    
		                    if(piece_Already_There(placeCheck))
		                        if(!see_EnemyKingIsChecked(enemy)) {
		                        bq.setPoint(oldP1);
		                        if(something_killed) {
		                            enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
		                            something_killed=false;
		                        }
		                        return false;
		                        }
		                }
		                
		                if(something_killed) {
		                    enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
		                    something_killed=false;
		                }
		                
		            }
				 this.bq.setPoint(oldP1);
				 placeCheck.x=oldP1.x;
				 placeCheck.y=oldP1.y;
				 if(something_killed)
				 {
					 enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					 something_killed=false;
				 }
				 //quan hau chay hang ngang
				 for(int i=1;i<=8;i++)
				 {
					 bq.setX(i);
					 placeCheck.x=i;
					 if(checkWayToPosition(enemy, oldP1)) {
		                    if(kill_To_Protect_King(enemy,returnPosition(inHand))) {
		                        something_killed=true;
		                    }
		                    
		                    if(piece_Already_There(placeCheck))
		                        if(!see_EnemyKingIsChecked(enemy)) {
		                        bq.setX(oldP1.x);
		                        if(something_killed) {
		                            enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
		                            something_killed=false;
		                        }
		                        return false;
		                        }
		                }
					 if(something_killed)
					 {
						 enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						 something_killed=false;
					 }
				 }
				 bq.setX(oldP1.x);
				 placeCheck.x=oldP1.x;
				 if(something_killed)
				 {
					 enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					 something_killed=false;
				 }
				 //quân hậu chạy hàng dọc
				 for(int i=1;i<=8;i++)
				 {
					 bq.setY(i);
					 placeCheck.y=i;
					 if(checkWayToPosition(enemy, oldP1)) {
		                    if(kill_To_Protect_King(enemy,returnPosition(inHand))) {
		                        something_killed=true;
		                    }
		                    
		                    if(piece_Already_There(placeCheck))
		                        if(!see_EnemyKingIsChecked(enemy)) {
		                        bq.setY(oldP1.y);
		                        if(something_killed) {
		                            enemy.changePosition(other,piece_Is_Killed_To_Protect_King);
		                            something_killed=false;
		                        }
		                        return false;
		                        }
		                }
					 if(something_killed)
					 {
						 enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
						 something_killed=false;
					 }
				 }
				bq.setY(oldP1.y);
			}
			if(something_killed)
			 {
				 enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
				 something_killed=false;
			 }
			return true;
		}
		public boolean PawnGenerate_Moves(Player1 enemy,Pawn bPawn)
		{
			Point oldP1= new Point();
			oldP1=bPawn.returnPosition();
			Point placeCheck= new Point();
			placeCheck.x=oldP1.x;
			placeCheck.y=oldP1.y;
			//nếu quân tốt chưa chết
			if(oldP1.x!=20)
			{
				if(bPawn.canMove(oldP1.x, oldP1.y-2, this.Color) && oldP1.y-2>=1)
				{
					//chi di chuyen hang doc ( truong hợp này là 2 ô)
					bPawn.setY(oldP1.y-2);
					placeCheck.y=oldP1.y-2;
					if(piece_Already_There(placeCheck)){
						if(piece_Enemy_Already_There(placeCheck, enemy))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bPawn.setPoint(oldP1);
								return false;
							}
						}
					}
					
				}
				//di chuyển hàng dọc 1 ô
				if(bPawn.canMove(oldP1.x, oldP1.y-1, this.Color) && oldP1.y-1>=1)
				{
					//chi di chuyen hang doc ( truong hợp này là 2 ô)
					bPawn.setY(oldP1.y-1);
					placeCheck.y=oldP1.y-1;
					if(piece_Already_There(placeCheck)){
						if(piece_Enemy_Already_There(placeCheck, enemy))
						{
							if(!see_EnemyKingIsChecked(enemy))
							{
								bPawn.setPoint(oldP1);
								return false;
							}
						}
					}
					
				}
				//neu nhu ko co quan dich ben trai 
				if(!piece_Enemy_Already_There(new Point(oldP1.x-1,oldP1.y-1), enemy))
				{
					if(kill_To_Protect_King(enemy, new Point(oldP1.x-1,oldP1.y-1)))
					{
						if(!see_EnemyKingIsChecked(enemy))
						{
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							bPawn.setPoint(oldP1);
							return false;
						}
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					}
				}
				//neu nhu ko co quan dich ben phair
				if(piece_Enemy_Already_There(new Point(oldP1.x+1,oldP1.y-1), enemy))
				{
					if(kill_To_Protect_King(enemy, new Point(oldP1.x+1,oldP1.y-1)))
					{
						if(!see_EnemyKingIsChecked(enemy))
						{
							enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
							bPawn.setPoint(oldP1);
							return false;
						}
						enemy.changePosition(other, piece_Is_Killed_To_Protect_King);
					}
				}
				
			}
			bPawn.setPoint(oldP1);
			return true;
		}
		public boolean checkWayToPosition(Player1 enemy, Point newP)
		{
			boolean flag=false;
			for(int i=0;i<=32;i++)
			{
				if(inHand!=i)
				{
					//neu quân hiện tại đang chọn khác với quân duyệt
					 if(i<17)
					 {
						 flag=checkWay(newP, enemy.returnPosition(i), inHand);
					 }
					 else
						{
							flag=checkWay(newP, returnPosition(i) ,inHand);
							
						}
					 if(flag==true)
						{
							return false; // ở vị trí này có quân cờ
						}
				}
				
				
			}
			return true;
		}
		 //phải giết quân để bảo vệ vua
		 public boolean kill_To_Protect_King(Player1 enemy,Point newP)
		 {
			 //duyệt qua các quân đối phương
			 for(int i=17;i<=32;i++)
			 {
				 other= enemy.returnPosition(i);
				 if(other.x== newP.x && other.y==newP.y)
				 {
					 piece_Is_Killed_To_Protect_King=i;
					 enemy.killedPiece(i);
					 return true;
				 }
			 }
			 return false;
			 
		 }
		 public boolean hasMoreElements()
		    {
		        return false;
		    }
		    
		    public Object nextElement()
		    {
		        
		        return new Object();
		    }
		 public String Tell_me_About_last_move() {
		        switch(inHand) {
		            
		            case 17:return br1.tell_me();
		            case 18:return br2.tell_me();
		            case 19:return bn1.tell_me();
		            case 20:return bn2.tell_me();
		            case 21:return bb1.tell_me();
		            case 22:return bb2.tell_me();
		            case 23:return bq.tell_me();
		            case 24:return bk.tell_me();
		            case 25:return  bPawns[0].tell_me();
		            case 26:return  bPawns[1].tell_me();
		            case 27:return  bPawns[2].tell_me();
		            case 28:return  bPawns[3].tell_me();
		            case 29:return  bPawns[4].tell_me();
		            case 30:return  bPawns[5].tell_me();
		            case 31:return  bPawns[6].tell_me();
		            case 32:return  bPawns[7].tell_me();
		        }
		        return null;
		    }
}
