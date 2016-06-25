import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class result extends settings implements ActionListener{
	private JButton exit,home;
	void displayResult(){
		frame("Result");
		
		exit=new JButton("Exit");
		home=new JButton("Home");
		
		exit.addActionListener(this);
		home.addActionListener(this);
		
		exit.setBounds(300, 100, 100, 50);
		home.setBounds(300, 220, 100, 50);
		
		Mcq.controlPanel.add(exit);
		Mcq.controlPanel.add(home);
		Mcq.mainFrame.add(Mcq.controlPanel);
		
		screenDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit){
			Mcq.mainFrame.dispose();
		}
		else if(e.getSource()==home){
			instructions instr=new instructions();
			instr.showInstruction();
		}
	}
}
