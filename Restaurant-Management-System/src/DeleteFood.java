import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteFood {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JPanel controlPanel;
   private JLabel id,name,price,quantity;
   private static int count = 0;
   GridLayout experimentLayout = new GridLayout(1,1);
    ResultSet rs;

    DeleteFood(){


      prepareGUI();
   }

   public static void main(String[] args){
      UpdateFood  swingControlDemo = new UpdateFood();
      swingControlDemo.showButtonDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Delete!");
      mainFrame.setSize(700,400);
	  mainFrame.getContentPane().setBackground(Color.gray);

      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            mainFrame.setVisible(false);
         }
      });
      headerLabel = new JLabel("", JLabel.CENTER);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);
   }


   public void showButtonDemo(){

		headerLabel.setText("Hotel Swaad");
		headerLabel.setFont(new Font(null, Font.BOLD, 27));
		headerLabel.setForeground(Color.white);
  
        name = new JLabel("Enter Name");
        JTextField tf2=new JTextField();
        tf2.setSize(100,30);
        
        JButton okButton = new JButton("DELETE");


      okButton.addActionListener(new ActionListener() {
         /**
         *
         */
        public void actionPerformed(ActionEvent e) {
            try{
            	String name= tf2.getText();
            	
            	String q1="select * from food;";
            	ResultSet rs=Database.Retrieve(q1);
            	boolean f=false;
            	while(rs.next()) {
            		if(name.equals(rs.getString(2))) 
            			f=true;
            		
            	}
            	if(f==false)
            		throw new Exception("hello");
                String q="delete from food where f_name='"+name+"';";
                int x=Database.insertTable(q);
                
                if(x==1)
                	 JOptionPane.showMessageDialog(null, tf2.getText()+" deleted");
                else
                	 JOptionPane.showMessageDialog(null, "deletion Error : ");
                mainFrame.setVisible(false);
                
            }catch(Exception ex){
                System.out.println(ex);
                System.out.println("EEEE");
                JOptionPane.showMessageDialog(null, "Item not found : " + tf2.getText());
            }finally{
                
            }
         }
      });


      
      JPanel jp = new JPanel();
      jp.add(name);
      jp.add(tf2);
    
      jp.setSize(700,400);
      jp.setLayout(experimentLayout);
      controlPanel.add(jp);
      jp.add(okButton);


      
      mainFrame.setVisible(true);
	  mainFrame.setLocationRelativeTo(null);
   }
}

