package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Game;
import model.UpdateAction;
import view.Cell;
import view.PanelGame;
/**
 * 用来将数字填充至空格中
 * @author user
 *
 */
public class GameController implements MouseListener{

	private PanelGame panelGame;
	private Game game;
	
	public GameController(PanelGame panelGame,Game game) {
		this.game=game;
		this.panelGame=panelGame;
	}

	public void mouseReleased(MouseEvent e) {
		JPanel panel=(JPanel)e.getSource();
		Component component=panel.getComponentAt(e.getPoint());
		if (component instanceof Cell) {
			Cell cell=(Cell) component;
			int x=cell.getX();
			int y=cell.getY();
			
			if ((e.getButton()==MouseEvent.BUTTON1) && (game.getNumber(x,y)==0)||cell.getForeground().equals(Color.BLUE)) {
				int selectedNumber=game.getSelectedNumber();
				if(selectedNumber==-1){
					return;
				}
				game.setNumber(x,y,selectedNumber);
				cell.setNumber(true, selectedNumber);
			}
			
			panelGame.update(game, UpdateAction.CANDIDATES);
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
