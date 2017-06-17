package Pieces;

import java.awt.Image;
import java.awt.Point;

public class Pawn {
	private int x,y; //toa do x,y cua quan co 
	//ví dụ ở đây là bàn cờ vua 8x8 = 64 ô
	//thì tọa độ [x,y] = [1,1] là ô thứ 1 trong 64 ô đếm từ trái sang phải và ở trên cùng
	// tương tự [x,y] =[8,8] là ô thứ 64 của bàn cờ đếm từ trái -> phải của tất cả các hàng
	private int pixelX;//toa do pixel cua X, tọa độ của x trong máy tính hiểu
	private int pixelY; //toa do pixel cua y, tọa độ của y trong máy tính hiểu
	private boolean hasLife; //cho biet quan co con song hay ko
	private PieceIcon pieceIcon; // avatar quan co
	private Point point; //điểm hiện tại quân cờ đang đứng ví dụ quân cờ ở vị trí [1,1] 
	private Point pointOld; //điểm trước đó quân cờ đang đứng ví dụ [1,2]
	private Point pixelPoint; //điểm quân cờ đang đứng dưới dạng pixel cho máy tính hiểu
	//khởi tạo quân cờ bằng tên đường dẫn , điểm x,y
	private boolean movedBefore;
	private boolean mySeen;
	private boolean pawn_becomes_Queen=false; // xác định quân tốt tiến hóa thành quân Hậu
	private boolean move_sucess_one_times=false;
	public Pawn(String pathName_Icon,int startX,int startY)
	{
		point = new Point();
		pointOld= new Point();
		pixelPoint=new Point();
		pieceIcon = new PieceIcon(pathName_Icon);
		//gan gia tri x va y khoi diem
		this.x=startX;
		this.y=startY;
		//gan vao doi tuong Point
		this.point.x=this.x;
		this.point.y=this.y;
		hasLife=true;
		movedBefore=false;
		mySeen=false;
	}
	public Image getImageIcon() 
	{
		return pieceIcon.getImage();
	}
	public Point getPixelPoint() {
		return pixelPoint;
	}
	public void setPixelPoint(Point pixelPoint) {
		this.pixelPoint = pixelPoint;
	}
	public void setPixelPoint(int newPixelX,int newPixelY) {
		this.pixelPoint.x=newPixelX;
		this.pixelPoint.y=newPixelY;
	}
	public int getX() {
		x= point.x;
		
		return x;
	}
	public void setX(int x) {
		this.x = x;
		point.x=x;
	}
	public int getY() {
		y= point.y;
		return y;
	}
	public void setY(int y) {
		this.y = y;
		point.y=y;
	}
	public int getPixelX() {
		return pixelX;
	}
	
	public int getPixelY() {
		return pixelY;
	}
	
	public boolean isHasLife() {
		return hasLife;
	}
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		//gan 1 diem moi vao thi luu lai diem cu roi moi gan diem moi vao
		
		this.pointOld.x=this.point.x;
		this.pointOld.y=this.point.y;
		this.x=point.x;
		this.y=point.y;
		this.point.x=point.x;
		this.point.y=point.y;
		
		movedBefore=true;
        mySeen=false;
        if(move_sucess_one_times==false)
		{
        	movedBefore=false;
		}
	}
	public Point getPointOld() {
		return pointOld;
	}
	
	//ham chuyen sang từ Point mới sang Point Cũ
	public void toPointOld(Point pointOld)
	{
		this.point.x=pointOld.x;
		this.point.y=pointOld.y;
	}
	public Point returnPosition()
	{
		//Trả về điểm vị trí của quân cờ
		return (Point) point.clone();
	}
	
	public boolean isPawn_becomes_Queen() {
		return pawn_becomes_Queen;
	}
	public void setPawn_becomes_Queen(boolean pawn_becomes_Queen) {
		this.pawn_becomes_Queen = pawn_becomes_Queen;
	}
	public boolean inThisPosition(int xPresent,int yPresent){
		if(point.x==xPresent && point.y==yPresent)
		{
			return true;
		}
		return false;
	}
	public boolean canMove(int x,int y, String typeColor)
	{
		
			
			if((typeColor.equals("black"))) {
            if((((y-1==this.y)&&(x==(this.x)))) /*&&!Check_Solider_Sees(x,y)*/) {
                
                return true;
                
            } else if((((y-2==this.y)&&(x==(this.x))))&&!movedBefore&& !move_sucess_one_times ) {
                
                return true;
            } else if((y-1==this.y&&x+1==(this.x)||(y-1==this.y&&x-1==(this.x)))&&mySeen ) {
                return true;
            }
            else  return false;
        }
        
        else if (typeColor=="white") {
            if(((y+1==this.y)&&(x==(this.x))) /*&&!Check_Solider_Sees(x,y)*/) {
                return true;
            } else if((((y+2==this.y)&&(x==(this.x)))) &&!movedBefore && !move_sucess_one_times) {
                return true;
            } else if((y+1==this.y&&x+1==(this.x)||(y+1==this.y&&x-1==(this.x)))&& mySeen  ) {
                return true;
            }
            else
                return false;
        }
        return false;
	}
	public boolean isPieceInMyWay(int x,int y,Point othersPosition,String typeColor)
	{
		
			if(this.y-y==2||this.y-y==-2) {
	            if((typeColor.equals("black"))) {
	                
	                if((((y-1==othersPosition.y)&&(x==(othersPosition.x))))&&!movedBefore ) {
	                    return true;
	                } else  return false;
	            }
	            
	            else  if (typeColor.equals("white")) {
	                
	                if(((y+1==othersPosition.y)&&(x==(othersPosition.x)) &&!movedBefore)) {
	                    
	                    return true;
	                } else
	                    return false;
	            }
	        }
	        return false;
	}
	public boolean isMySeen() {
		return mySeen;
	}
	public void setMySeen(boolean mySeen) {
		this.mySeen = mySeen;
	}
	 public boolean setSeenByCheckKing(Point newP,String Color) {
	     
	 mySeen=false;
	 
		 if((Color.equals("black"))) {
	            if((newP.y-1==this.y&&newP.x+1==(this.x)||(newP.y-1==this.y&&newP.x-1==(this.x)))) {
	                
	                mySeen=true;
	                return true;
	            } else return false;
	        } else if(Color.equals("white")) {
	            if((newP.y+1==this.y&&newP.x+1==(this.x)||(newP.y+1==this.y&&newP.x-1==(this.x)))) {
	                mySeen=true;
	                
	                return true;
	            } else return false;
	        }
	        return false;
	    }
	 public String tell_me() {
	        return "Pawn = ("+point.x+','+point.y+")";
	    }
		//tao ra 1 diem de move 
		public Point generatePossible_Moves() {
	        return new Point();
	    }
		public boolean isMove_sucess_one_times() {
			return move_sucess_one_times;
		}
		public void setMove_sucess_one_times(boolean move_sucess_one_times) {
			this.move_sucess_one_times = move_sucess_one_times;
		}
		
}
