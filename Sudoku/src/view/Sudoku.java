package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.ButtonController;
import controller.GameController;
import model.Game;

public class Sudoku extends JFrame{
	
	private Game game;
	
	public Sudoku() {
		this.setTitle("Sudoku");
		this.setSize(788, 389);
		this.setLayout(new BorderLayout());
		this.createFrame();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		game=new Game();
	}
	
	public void createFrame() {
		PanelGame panelGame=new PanelGame();
		GameController gameController=new GameController(panelGame,game);
		panelGame.setGame(game);
		panelGame.setController(gameController);
		this.add(panelGame, BorderLayout.CENTER);
		
		PanelButton panelButton=new PanelButton();
		ButtonController buttonController=new ButtonController(game);
		panelButton.setController(buttonController);
		this.add(panelButton,BorderLayout.EAST);
		
		game.addObserver(panelGame);
        game.addObserver(panelButton);
		
	}
	
	public static void main(String[] args) {
		Sudoku sudoku=new Sudoku();
		sudoku.setVisible(true);
	}
}
