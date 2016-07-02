import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Properties;
import java.util.UUID;

public class login extends settings implements ActionListener{
	private JTextField emailId;
	private JPasswordField passwd;
	private JButton forgot,submit;
	private JLabel mail,password;
	private String uuid,username,passid;
	private String user = "root", pass = "root", database = "mcq", errorMessage = "fill all the fields", loginError = "login failed";
	int port = 3306;
	
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
	public void displayData(){
		Connection con = null;  
		
		username = "mndpkaur14@gmail.com";
		passid = "mandeepkaur";
/**if text fields are empty or not; if yes then verify credentials 
 * from database else prompt to enter both fields first
*/
		if((username.length()!= 0) && (passid.length()!= 0)){
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == forgot){
			//add code to send email for resetting the password
			uuid = UUID.randomUUID().toString();
			username = emailId.getText();
			System.out.println(uuid);
			sendEmail();
		}
		else if(e.getSource() == submit){
			displayData();
		}
	}
	
	public void sendEmail(){
		String usernameEmail = email;
		String passwordEmail = passwordSign; 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usernameEmail, passwordEmail);
			}		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mndpkaur14@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(username));
			message.setSubject("Forgot Passowrd");
			message.setText("Copy following code,"
				+ "\n\n to reset password"+ uuid);
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}//end of class