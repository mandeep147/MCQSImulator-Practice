import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mcq extends settings implements ActionListener{
	static JFrame mainFrame;
	private JButton student,admin;
	static JPanel controlPanel;
	public Mcq(){
		createGUI();
	}
	public static void main(String []args){
		Mcq mainScreen=new Mcq();
		mainScreen.welcome();
	}
	
	//display properties of frame
	private static void createGUI(){		
		
		mainFrame= new JFrame("MCQ");
		mainFrame.setSize(800,800);
	
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() -mainFrame.getHeight()) / 2);
	   
	    mainFrame.setLocation(x, y);
	    screenDisplay();
	}
	
	//home screen----proceed as either student or admin
	private void welcome(){
		controlPanel = new JPanel();
	    controlPanel.setLayout(null);
	    
		student=new JButton("Student");
		admin=new JButton("Admin");
		
		student.addActionListener(this);
		admin.addActionListener(this);
		
		student.setBounds(200, 200, 250, 50);
		admin.setBounds(200, 380, 250, 50);

		controlPanel.add(student);
		controlPanel.add(admin);
		
		mainFrame.add(controlPanel);
		screenDisplay();
		mainFrame.repaint();
	}
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == student){
			login login=new login();
			login.loginScreen();
	    }
	    else if(e.getSource()== admin){
	    	//add code to handle functionality of admin
	    }
	}
}