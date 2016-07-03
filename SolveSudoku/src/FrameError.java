

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameError {

	private Dialog error;
	private JLabel msg;
	private JButton btnOk;
	
	public FrameError() {
		error=new Dialog(error);
		error.setBounds(500, 300, 185, 120);
		
		this.createDialog();
		error.setVisible(true);
	}
	
	private void createDialog(){
		error.setLayout(new BorderLayout());
		
		JPanel jp1=new JPanel();
		jp1.setSize(200, 40);
		msg=new JLabel("This has no answer!");
		jp1.add(msg);
		error.add(jp1,BorderLayout.CENTER);

		JPanel jp2=new JPanel();
		jp2.setSize(70, 30);
		btnOk=new JButton("Return");
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				error.setVisible(false);
			}
		});
		jp2.add(btnOk);
		error.add(jp2,BorderLayout.SOUTH);
	}
}
