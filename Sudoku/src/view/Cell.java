package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Cell extends JLabel {

	// temply don't know its use
	private int x;
	private int y;
	
	public Cell(int x,int y) {
		super("", CENTER);
		this.x=x;
		this.y=y;
		
		this.setSize(new Dimension(40, 40));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		//设为透明
		this.setOpaque(true);
	}

	public void setNumber(boolean hasInput, int number) {
		this.setForeground(hasInput ? Color.BLUE : Color.BLACK);
		this.setText(number>0 ? String.valueOf(number) : "");
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
