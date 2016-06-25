import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class quizScreen extends settings implements ActionListener {
	String ques,ans, opta, optb, optc,optd;
	int score,count=1,var=5, port=3306;
	int course;
	private JRadioButton[] choice;
	private JLabel test;
	private JButton next,back,finish;
	private ButtonGroup group ;
	String user="root",pass="root",database="mcq";
	public quizScreen(String cid) {
		// TODO Auto-generated constructor stub
		course=Integer.parseInt(cid);
		quiz(course);
	}

	void quiz(int qid){
		frame("Quiz");

		test=new JLabel();
		next=new JButton("Next");
		back=new JButton("Back");
		finish=new JButton("Finish");
		choice=new JRadioButton[var-1];
		group = new ButtonGroup();
		
		next.addActionListener(this);
		back.addActionListener(this);
		finish.addActionListener(this);
		Connection con = null;
				
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+database,user,pass);
			Statement stmt = (Statement) con.createStatement();
			String query = "select question,optA,optB,optC,optD from question where c_id='"+course+"'and qid='"+count+"'";
			
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				ques = rs.getString("question");
				
				opta = rs.getString("optA");
				optb = rs.getString("optB");
				optc = rs.getString("optC");
				optd = rs.getString("optD");
				
	//			System.out.println(ques+" "+opta+" "+optb+" "+optc+" "+optd);
				
				test.setText("<html>"+ques+"<br></html>");
				Mcq.controlPanel.add(test);
				
				choice[0]= new JRadioButton(opta);
				choice[1]= new JRadioButton(optb);
				choice[2]= new JRadioButton(optc);
				choice[3]= new JRadioButton(optd);
				
				displayButton();
		
				//count++;
			}		
			for(int i=0;i<var-1;i++)
				Mcq.controlPanel.add(choice[i]);
			
			for(int i=0;i<var-1;i++)
				group.add(choice[i]);
		
			choice[0].setBounds(200, 250, 400, 50);
			choice[1].setBounds(200, 310, 400, 50);
			choice[2].setBounds(200, 370, 400, 50);
			choice[3].setBounds(200, 430, 400, 50);
			
			test.setBounds(100, 50, 600, 200);
			next.setBounds(300, 500, 150, 50);
			back.setBounds(450, 500, 210, 50);
			finish.setBounds(600, 500, 270, 50);
				
		}catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		screenDisplay();
	}
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == next){
	    	if(count<=var)
				quiz(count++);
	    }
	    else if(e.getSource()==back){
			quiz(count--);
	    }
	    else if(e.getSource()==finish){
	    	result result=new result();
	    	result.displayResult();
	    }
	}
	public void displayButton(){
		if(count==1)
			Mcq.controlPanel.add(next);
		if(count>1&&count<var){
			Mcq.controlPanel.add(back);
			Mcq.controlPanel.add(next);
		}
		if(count==var){
			Mcq.controlPanel.add(back);
			Mcq.controlPanel.add(finish);
		}
	}
}