import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.UUID;

public class login extends settings implements ActionListener{
	
	private JTextField emailId, resetCode ;
	private JPasswordField passwd, newPassword;
	private JButton forgot,submit, resetButton;
	private JLabel mail,password, reset, forgotPassword;
	private String uuid,username,passid, newpassid, resetError="incorrect code";
	private String user = "root", pass = "root", database = "mcq", errorMessage = "fill all the fields", loginError = "login failed";
	int port = 3306;
	Connection con;
	
	void loginScreen(){		
		frame("Login");
		
		emailId = new JTextField();
		passwd = new JPasswordField();
		
		mail = new JLabel("EMail-ID: ");
		password = new JLabel("Password: ");
		 		
		forgot = new JButton("Forgot Password");
		submit = new JButton("Submit");
		
		forgot.addActionListener(this);
//verify the credentials first if correct then call courses.listcourses else pop up error msg
		submit.addActionListener(this);
		
		mail.setBounds(200, 100, 150, 50);
		password.setBounds(200, 250, 150, 50);
		
		emailId.setBounds(400, 200, 500, 50);
		passwd.setBounds(400, 320, 500, 50);

		forgot.setBounds(300, 460, 250, 50);
		submit.setBounds(500, 460, 250, 50);
		
		Mcq.controlPanel.add(mail);
		Mcq.controlPanel.add(password);
		Mcq.controlPanel.add(emailId);
		Mcq.controlPanel.add(passwd);
		Mcq.controlPanel.add(forgot);
		Mcq.controlPanel.add(submit);
				
		Mcq.mainFrame.add(Mcq.controlPanel);
		screenDisplay();
	}
	@SuppressWarnings("deprecation")
	public void displayData(){
		username = "mndpkaur14@gmail.com";
		//passid = "mandeepkaur";
		passid = passwd.getText();
		System.out.println(passid);

		if(username.isEmpty() || passid.isEmpty()){
			JOptionPane.showMessageDialog(Mcq.controlPanel, loginError);
		}
		else if((username.length()!= 0) && (passid.length()!= 0)){
			try {
				con= DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+database,user,pass);
				Statement stmt = (Statement) con.createStatement();
				String query = "select * from login where email='"+username+"' and password='"+passid+"'";				
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					instructions instr = new instructions();
					instr.showInstruction();
				}
				else
					JOptionPane.showMessageDialog(Mcq.controlPanel, loginError);
			}catch(Exception se){
			      se.printStackTrace();
			   }//end of try-catch
		}//end of if
		else
			JOptionPane.showMessageDialog(Mcq.controlPanel,errorMessage,"login",JOptionPane.WARNING_MESSAGE);		
	}//end of displayData()

	public void setPassword(){
		frame("Reset Password");
		
		reset = new JLabel("Reset password code");
		forgotPassword = new JLabel("New Password");
		
		resetButton = new JButton("Confirm");
		
		resetCode = new JTextField();
		newPassword = new JPasswordField();
		
		resetButton.addActionListener(this);
		reset.setBounds(200, 100, 150, 50);
		forgotPassword.setBounds(200, 250, 150, 50);
		
		resetCode.setBounds(400, 200, 500, 50);
		newPassword.setBounds(400, 320, 500, 50);

		resetButton.setBounds(300, 460, 250, 50);
		
		Mcq.controlPanel.add(reset);
		Mcq.controlPanel.add(forgotPassword);
		Mcq.controlPanel.add(resetCode);
		Mcq.controlPanel.add(newPassword);
		Mcq.controlPanel.add(resetButton);
				
		Mcq.mainFrame.add(Mcq.controlPanel);
		screenDisplay();

	}
	
	public void resetPassword() throws SQLException{
		String codeConfirm = resetCode.getText();
		newpassid = newPassword.getText();
		System.out.println(newpassid+ "   "+ username);
		if(codeConfirm.isEmpty() || newpassid.isEmpty()){
			JOptionPane.showMessageDialog(Mcq.controlPanel, "Mandatory Fields.");
		}
		else if(!(codeConfirm.equals(uuid)) ){
			JOptionPane.showMessageDialog(Mcq.controlPanel, resetError);
		}
		else{
			PreparedStatement preparedStatement = null;
			try {
				con= DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+database+"?useSSL=false",user,pass);
			
				String queryUpdate = "update login set password = ? where email = ?";
				preparedStatement = con.prepareStatement(queryUpdate);
				preparedStatement.setString(1, newpassid);
				preparedStatement.setString(2, username);
				preparedStatement.executeUpdate();
				
				JOptionPane.showMessageDialog(Mcq.controlPanel, "Updated Passowrd");
				loginScreen();
				
				
			}catch(Exception se){
			      se.printStackTrace();
			   }//end of try-catch
			finally {
				preparedStatement.close();
				con.close();
			}
		}		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == forgot){
			uuid = UUID.randomUUID().toString();
			//username = emailId.getText();
			username = "mndpkaur14@gmail.com";
			Email send = new Email();
			send.sendEmail(username, uuid);
			setPassword();
		}
		else if(e.getSource() == submit){
			displayData();
		}
		else if(e.getSource() == resetButton){
			try {
				resetPassword();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}// end of function	
}//end of class