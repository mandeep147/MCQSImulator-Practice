
public class settings {
	
		void frame(String title){
			Mcq.mainFrame.setTitle(title);
			Mcq.controlPanel.removeAll();
			Mcq.controlPanel.repaint();
		}
		
		static void screenDisplay(){
			 Mcq.mainFrame.setDefaultCloseOperation(1);
			 Mcq.mainFrame.setResizable(false);
			 Mcq.mainFrame.setVisible(true);
		}
		
		void addComponents(){
			
		}
}
