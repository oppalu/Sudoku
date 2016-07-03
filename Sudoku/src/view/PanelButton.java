package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.security.acl.Group;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import model.UpdateAction;
import controller.ButtonController;

public class PanelButton extends JPanel implements Observer{

	private JButton btnNew;
	private JButton btnCheck;
	private JButton btnExit;
	
	private JCheckBox jcbHelp;

	private JToggleButton[] btnNumbers;
	
	public PanelButton() {
		this.setLayout(new BorderLayout());
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.add(panel, BorderLayout.NORTH);
		
		JPanel buttons=this.createButton();
		panel.add(buttons);
		
		JPanel numbers=this.createNumbers();
		panel.add(numbers);
		
	}
	
	public JPanel createButton() {
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.LEADING));
		jp.setBorder(BorderFactory.createTitledBorder(" Options "));
		
		btnNew=new JButton("New");
		btnNew.setFocusable(false);
		jp.add(btnNew);
		
		btnCheck=new JButton("Check");
		btnCheck.setFocusable(false);
		jp.add(btnCheck);
		
		btnExit=new JButton("Exit");
		btnExit.setFocusable(false);
		jp.add(btnExit);
		
		return jp;
	}
	
	public JPanel createNumbers() {
		JPanel jp=new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		jp.setBorder(BorderFactory.createTitledBorder(" Numbers "));
		
		JPanel help = new JPanel(new FlowLayout(FlowLayout.LEADING));
        jcbHelp = new JCheckBox("Need help");
        help.add(jcbHelp);
        jp.add(help);
        
        JPanel numbers=new JPanel(new FlowLayout(FlowLayout.LEADING));
        btnNumbers=new JToggleButton[9];
        for (int i = 0; i < 9; i++) {
			btnNumbers[i]=new JToggleButton(String.valueOf(i+1));
			btnNumbers[i].setPreferredSize(new Dimension(40, 40));
			btnNumbers[i].setFocusable(false);
			numbers.add(btnNumbers[i]);
		}
		jp.add(numbers);

		return jp;
	}
	
	public void setController(ButtonController buttonController) {
		this.btnNew.addActionListener(buttonController);
		this.btnCheck.addActionListener(buttonController);
		this.jcbHelp.addActionListener(buttonController);
		for (int i = 0; i < 9; i++) {
			this.btnNumbers[i].addActionListener(buttonController);
		}
	}

	
	public void update(Observable o, Object arg) {
		switch ((UpdateAction)arg) {
			case NEW_GAME:
			case CHECK:
		}
	}
}
