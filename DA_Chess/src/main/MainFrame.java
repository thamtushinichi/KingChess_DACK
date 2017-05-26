package main;

import javax.swing.JFrame;

import ChessFrame.ChessBoard;
import ChessFrame.MenuFrame;
import players.Player1;

import java.awt.*;
import javax.swing.*;
import java.util.*;
public class MainFrame extends JFrame {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Player1 player1= new Player1();
//		JFrame frame= new MenuFrame();
//		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		frame.pack();
//		frame.setResizable(false);
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
		JFrame frame = new MenuFrame();
		//JFrame frame = new WaitingFrame();
        // mcg.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true); 
		
	}

}
