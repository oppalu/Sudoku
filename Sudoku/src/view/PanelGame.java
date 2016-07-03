package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.GameController;
import model.Game;
import model.UpdateAction;

public class PanelGame extends JPanel implements Observer{
	private static final Color COLOR_CANDIDATE = new Color(102, 153, 255);
	
	private Cell[][] cells;
	private JPanel[][] bigCells;
	
	public PanelGame() {
		super(new GridLayout(3,3));
		
		bigCells=new JPanel[3][3];
		for (int i = 0; i<3; i++) {
			for (int j = 0; j < 3; j++) {
				bigCells[i][j]=new JPanel(new GridLayout(3, 3));
				bigCells[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				this.add(bigCells[i][j]);
			}
		}
		
		cells=new Cell[9][9];
		for (int i = 0; i<9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j]=new Cell(j, i);
				bigCells[i/3][j/3].add(cells[i][j]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		switch ((UpdateAction)arg) {
        	case NEW_GAME:
        		setGame((Game)o);
        		break;
        	case CHECK:
        		setGameCheck((Game)o);
        		break;
        	case SELECTED_NUMBER:
        	case CANDIDATES:
        	case HELP:
        		setCandidates((Game)o);
        		break;
		}
    }

	private void setCandidates(Game game) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].setBackground(Color.WHITE);
				//TODO
				if (game.isHelp()&&game.candidateOk(i,j)) {
					cells[i][j].setBackground(COLOR_CANDIDATE);
				}
			}
		}
		
	}

	private void setGameCheck(Game game) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].setBackground(Color.WHITE);
				if (cells[i][j].getForeground().equals(Color.BLUE)) {
					cells[i][j].setBackground(game.checkOk(i,j) ? Color.GREEN : Color.RED);
				}
			}
		}
	}

	public void setGame(Game game) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].setBackground(Color.WHITE);
				cells[i][j].setNumber(false, game.getNumber(i, j));
			}
		}
	}

	public void setController(GameController gameController) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bigCells[i][j].addMouseListener(gameController);
			}
		}
	}
}
