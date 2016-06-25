import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class instructions extends settings implements ActionListener {
	private JButton start;
	private JLabel instruct;
	String text="";
	String savetext="";	

	void showInstruction(){
		frame("Instructions");

		readDataFromFile();
		
		start=new JButton("Start Test");
		start.addActionListener(this);
		
		Font font = new Font("Courier", Font.PLAIN,22);
		
		start.setBounds(300, 680, 100, 50);
		instruct.setBounds(80, 50, 580, 550);
		instruct.setFont(font);
		
		Mcq.controlPanel.add(instruct);
		Mcq.controlPanel.add(start);
		Mcq.mainFrame.add(Mcq.controlPanel);
		
		screenDisplay();
	}
	
	void readDataFromFile(){
		File file = new File("D:\\instructions.txt");
		BufferedReader reader = null;
		instruct=new JLabel();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			            		
			while ((text = reader.readLine()) != null) {
				savetext+=text;
			}
			String finalStr=convertToMultiline(savetext);
			System.out.println(finalStr);
			
			instruct.setText(finalStr);
		}catch(Exception se) {
			se.printStackTrace();
		}//end of try-catch
	}//end of function
	
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("/n", "<br>");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		courses co=new courses();
		co.listCourses();
	}
}