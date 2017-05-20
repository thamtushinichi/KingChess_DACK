package Pieces;

import java.awt.Image;
import java.awt.Toolkit;

public class PieceIcon {
	private Toolkit toolkit ; //hàm để vẽ icon bằng đường dẫn
	private Image image; //đối tượng image lưu trữ icon
	public PieceIcon(String pathName_Icon)
	{
		toolkit= Toolkit.getDefaultToolkit();
		image=toolkit.getImage(pathName_Icon);
	}
	public Image getImage() {
		return image;
	}
	
	
}
