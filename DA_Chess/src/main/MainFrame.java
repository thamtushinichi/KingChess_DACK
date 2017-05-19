package main;

import javax.swing.JFrame;

import ChessFrame.ChessBoard;
import players.Player1;

import java.awt.*;
import javax.swing.*;
import java.util.*;
public class MainFrame extends JFrame {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player1 player1= new Player1();
		JFrame frame= new ChessBoard();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
