package Pieces;

import java.awt.Image;
import java.awt.Point;

public class Rook {
	private int x, y; // toa do x,y cua quan co
	// ví dụ ở đây là bàn cờ vua 8x8 = 64 ô
	// thì tọa độ [x,y] = [1,1] là ô thứ 1 trong 64 ô đếm từ trái sang phải và ở
	// trên cùng
	// tương tự [x,y] =[8,8] là ô thứ 64 của bàn cờ đếm từ trái -> phải của tất
	// cả các hàng
	private int pixelX;// toa do pixel cua X, tọa độ của x trong máy tính hiểu
	private int pixelY; // toa do pixel cua y, tọa độ của y trong máy tính hiểu
	private boolean hasLife; // cho biet quan co con song hay ko
	private PieceIcon pieceIcon; // avatar quan co
	private Point point; // điểm hiện tại quân cờ đang đứng ví dụ quân cờ ở vị
							// trí [1,1]
	private Point pointOld; // điểm trước đó quân cờ đang đứng ví dụ [1,2]
	private Point pixelPoint; // điểm quân cờ đang đứng dưới dạng pixel cho máy
								// tính hiểu
	// khởi tạo quân cờ bằng tên đường dẫn , điểm x,y

	public Rook(String pathName_Icon, int startX, int startY) {
		point = new Point();
		pointOld = new Point();
		pixelPoint = new Point();
		pieceIcon = new PieceIcon(pathName_Icon);
		// gan gia tri x va y khoi diem
		this.x = startX;
		this.y = startY;
		// gan vao doi tuong Point
		this.point.x = this.x;
		this.point.y = this.y;
		hasLife = true;
	}

	// lấy image của icon
	public Image getImageIcon() {
		return pieceIcon.getImage();
	}

	public Point getPixelPoint() {
		return pixelPoint;
	}

	public void setPixelPoint(Point pixelPoint) {
		this.pixelPoint = pixelPoint;
	}

	public void setPixelPoint(int newPixelX, int newPixelY) {
		this.pixelPoint.x = newPixelX;
		this.pixelPoint.y = newPixelY;
	}

	public int getX() {
		x = point.x;

		return x;
	}

	public void setX(int x) {
		this.x = x;
		point.x = x;
	}

	public int getY() {
		y = point.y;
		return y;
	}

	public void setY(int y) {
		this.y = y;
		point.y = y;
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
		// gan 1 diem moi vao thi luu lai diem cu roi moi gan diem moi vao
		this.pointOld.x = this.point.x;
		this.pointOld.y = this.point.y;
		this.x = point.x;
		this.y = point.y;
		this.point.x = point.x;
		this.point.y = point.y;
	}

	public Point getPointOld() {
		return pointOld;
	}

	// ham chuyen sang từ Point mới sang Point Cũ
	public void toPointOld(Point pointOld) {
		this.point.x = pointOld.x;
		this.point.y = pointOld.y;
	}

	public Point returnPosition() {
		// Trả về điểm vị trí của quân cờ
		return (Point) point.clone();
	}

	// kiểm tra vi trí hiện tại , quân cờ có đang ở vị trí hiện tại không
	public boolean inThisPosition(int xPresent, int yPresent) {
		if (point.x == xPresent && point.y == yPresent) {
			return true;
		}
		return false;
	}

	// kiem tra quan co co the di chuyen den 1 vi tri nao do
	public boolean canMove(int x, int y) {
		if ((y == this.y) && ((x > this.x) || (x < this.x))) {
			return true;
		} else if (((y > this.y) || (y < this.y)) && (x == this.x)) {
			return true;
		} else {
			return false;
		}
	}

	// xet 1 điểm Point có thuộc đường đi của quân cờ này không
	public boolean isPieceInMyWay(int x, int y, Point othersPosition) {
		// khoi tao 2 bien dem
		int i = x;
		int j = y;
		// vi la quân Rook nên ta chỉ xét 2 đường là đường dọc và đường ngang
		// xet đường ngang trước
		if ((y == this.y) && ((x > this.x) || (x < this.x))) {
			if (this.x < i) {
				// xét bên trái của vị trí quân xe
				while (i != (this.x + 1)) {
					i = i - 1;
					if ((othersPosition.y == j) && (othersPosition.x == i)) {
						// kiem tra nếu vị trí này là quân đồng minh thì trả về
						// true vì
						// quân đó cản đường quân Rook
						return true;
					}
				}
			} else if (this.x > i) {
				// xet bien phai quan xe
				while (i != (this.x - 1)) {
					i = i + 1;
					if ((othersPosition.y == j) && (othersPosition.x == i)) {
						// kiem tra nếu vị trí này là quân đồng minh thì trả về
						// true vì
						// quân đó cản đường quân Rook
						return true;
					}
				}
			}
		} else if (((y > this.y) || (y < this.y)) && (x == this.x)) {
			// xet vị trí hàng dọc
			// xét doc duoi cua quan xe
			if (this.y < j) {
				while (j != (this.y + 1)) {
					j = j - 1;
					if ((othersPosition.y == j) && (othersPosition.x == i)) {
						// kiem tra nếu vị trí này là quân đồng minh thì trả về
						// true vì
						// quân đó cản đường quân Rook
						return true;
					}
				}
			} else if (this.y > j) {
				// xet doc tren cua quan xe
				while (j != (this.y - 1)) {
					j = j + 1;
					if ((othersPosition.y == j) && (othersPosition.x == i)) {
						// kiem tra nếu vị trí này là quân đồng minh thì trả về
						// true vì
						// quân đó cản đường quân Rook
						return true;
					}
				}
			}
		}
		return false; // tức là không có quân cờ đồng mình trong hướng di chuyển
						// của quân Rook
	}

	// kiem tra vi tri so voi King, neu di chuyen thi King co bi chieu ko
	public boolean checkKing(int x, int y, Point othersPostion) {
		int j = y;
		int i = x;
		if (((y == this.y) && ((x > this.x) || (x < this.x)))) {

			if ((this.x < i))

				while (i != (this.x)) {
					i = i - 1;
					if (((othersPostion.y) == j) && ((othersPostion.x == i)))// there
																				// Same
																				// Color
																				// piece
					{
						return true;
					}
				}

			else if ((this.x > i)) {
				while (i != (this.x)) {
					i = i + 1;
					if (((othersPostion.y) == j) && ((othersPostion.x == i))) {
						return true;
					}
				}
			}
		} else if (((y > this.y) || (y < this.y)) && (x == this.x)) {
			if ((this.y < j)) {
				while (j != (this.y)) {
					j = j - 1;
					if (((othersPostion.y) == j) && ((othersPostion.x == i))) {
						return true;
					}
				}
			} else if (this.y > j) {
				while (j != (this.y)) {
					j = j + 1;

					if (((othersPostion.y) == j) && ((othersPostion.x == i))) {
						return true;
					}
				}

			}
		}
		return false;

	}

	public String tell_me() {
		return "Rook= (" + point.x + ',' + point.y + ")";
	}

	// tao ra 1 diem de move
	public Point generatePossible_Moves() {
		return new Point();
	}
}
