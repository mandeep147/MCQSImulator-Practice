import java.awt.event.*;
import javax.swing.*;

public class courses extends settings implements ActionListener{
	private JLabel title;
	private JButton submit;
	private JRadioButton[] courseType;
	String cid="";
	String errorString="Select at least one course to continue";
	int limit=3;
	void listCourses(){
		frame("Courses");
		
		courseType=new JRadioButton[limit];

		submit=new JButton("Submit");
		title=new JLabel("Select one course");
		
		courseType[0]=new JRadioButton("CMPE-202");
		courseType[1]=new JRadioButton("CMPE-272");
		courseType[2]=new JRadioButton("CMPE-273");
		
		ButtonGroup group = new ButtonGroup();
		for(int i=0;i<limit;i++)
			group.add(courseType[i]);
		
		submit.addActionListener(this);
		
		title.setBounds(200, 50, 300, 50);
		Mcq.controlPanel.add(title);
		
		courseType[0].setBounds(250, 200, 300, 50);
		courseType[1].setBounds(250, 350, 300, 50);
		courseType[2].setBounds(250, 500, 300, 50);
		for(int i=0;i<limit;i++)
			Mcq.controlPanel.add(courseType[i]);
		
		submit.setBounds(300, 680, 200, 50);
		Mcq.controlPanel.add(submit);
		Mcq.mainFrame.add(Mcq.controlPanel);
		
		screenDisplay();
	}
	private void selectedCourse(){
		if(courseType[0].isSelected())
			cid="202";
		else if(courseType[1].isSelected())
			cid="272";
		else 
			cid="273";
		
		new quizScreen(cid);
	}//end of function
	public void actionPerformed(ActionEvent e){
		if(courseType[0].isSelected()||courseType[1].isSelected()||courseType[2].isSelected()){
			selectedCourse();
		}
			else 
				JOptionPane.showMessageDialog(Mcq.controlPanel, errorString);
	}//end of action performed
}