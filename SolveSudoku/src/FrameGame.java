import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameGame extends JFrame {

	private JButton btnOk;
	private JButton btnClear;
	private JPanel Cell, bigCells[][], buttons;
	private JTextField smallCells[][];
	
	private int[][] cells=new int[9][9];
	private boolean hasResult=true;
	private FrameError frameError;

	public FrameGame() {
		this.setTitle("Sudoku");
		this.setSize(500, 550);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createButton();
		this.createCell();
		this.setVisible(true);
	}

	private void createCell() {
		
		Cell = new JPanel();
		bigCells = new JPanel[3][3];
		smallCells = new JTextField[9][9];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bigCells[i][j] = new JPanel();
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				smallCells[i][j] = new JTextField();
				smallCells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
				smallCells[i][j].setFont(new Font("TimesRoman", Font.BOLD, 32));
			}
		}

		Cell.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bigCells[i][j].setLayout(new GridLayout(3, 3));
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						bigCells[i][j].add(smallCells[i * 3 + k][j * 3 + l]);
					}
					bigCells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
					Cell.add(bigCells[i][j]);
				}
			}
		}
		this.add(Cell, BorderLayout.CENTER);
	}

	private void createButton() {
		Solve solve=new Solve();
		btnOk = new JButton("Solve");
		btnClear = new JButton("Clear");
		buttons = new JPanel();
		buttons.add(btnOk);
		buttons.add(btnClear);
		this.add(buttons, BorderLayout.NORTH);

		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<9;i++){
					for (int j = 0; j < 9; j++) {
						if(smallCells[i][j].getText().equals("")){
							cells[i][j]=0;
						}else{
							cells[i][j]=Integer.parseInt(smallCells[i][j].getText());
						}
					}
				}

				solve.solution(cells);
				
				for(int i=0;i<9;i++){
					for(int j=0;j<9;j++){
						smallCells[i][j].setText(Integer.toString(cells[i][j]));
					}
				}
			}
		});

		btnClear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<9;i++){
					for (int j = 0; j < 9; j++) {
						smallCells[i][j].setText("");
					}
				}
			}
		});
	}
	
}
