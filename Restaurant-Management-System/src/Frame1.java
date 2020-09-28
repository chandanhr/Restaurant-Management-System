import java.awt.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import com.mysql.cj.protocol.Resultset;


public class Frame1 extends JFrame{

    JLabel idLabel;
    JLabel passLabel;
	JLabel background;
	JLabel headerLabel;
	JLabel devInfo;
	
    JTextField id;
    JPasswordField pass;

    JButton submit;
    
    	public static void main(String[] args){
    		Frame1 f = new Frame1();
    		f.setVisible(true);
    		
    	}

   public Frame1(){
		setTitle("Restaurant Billing System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//Background
		this.background = new JLabel(new ImageIcon("C:\\Users\\Asus\\Downloads\\Java-Project-Final (1)\\Java Project Final\\image\\burger.jpg"));

		
		this.init();

		add(background);
		background.setVisible(true);  //C:\\Java Project Final\\image
        this.pack();
		this.setSize(700,400);
		this.setLocationRelativeTo(null);
    }



    public void init(){
		headerLabel = new JLabel();
		this.headerLabel.setText("Hotel Swaad");
		this.headerLabel.setBounds(270,1,200,100);
		this.headerLabel.setFont(new Font("Geomanist", Font.BOLD, 25));
		headerLabel.setForeground(Color.blue);
		add(headerLabel);
		
		
		idLabel = new JLabel();
		this.idLabel.setText("Username");
		this.idLabel.setBounds(190,110,100,50);
		this.idLabel.setFont(new Font(null, Font.BOLD, 20));
		idLabel.setForeground(Color.red);
		add(idLabel);
		
        passLabel=new JLabel("Password");
		this.passLabel.setBounds(190,165,100,50);
		this.passLabel.setFont(new Font(null, Font.BOLD, 20));
		passLabel.setForeground(Color.red);
		add(passLabel);
		
		
		devInfo = new JLabel();
		this.devInfo.setText("© all Rights Reserved by   -    Redoy    ||    Nahid     ||    Shihab");
		this.devInfo.setBounds(130,300,1000,30);
		this.devInfo.setFont(new Font("Geomanist", Font.PLAIN, 15));
		devInfo.setForeground(Color.white);
		add(devInfo);
			
			
		id=new JTextField();
		this.id.setBounds(300,125,200,30);
		add(id);
		
		pass=new JPasswordField();
		this.add(pass);
		this.pass.setBounds(300,175,200,30);
		
		
	
        
		this.id.setVisible(true);
	  
		
      

       this.submit=new JButton("Login");
	   this.submit.setBounds(400,230,100,25);
	   add(submit);
	   
       submit.addActionListener(this::submitActionPerformed);


    } 



   public void submitActionPerformed(java.awt.event.ActionEvent evt){
	   try {
		   String Q="select * from users ;";
		   ResultSet rs= Database.Retrieve(Q);
		   boolean f=false;
		   while(rs.next()) {
			   if(id.getText().equals(rs.getString(1)) && pass.getText().equals(rs.getString(2)))
				   f=true;
		   }
	
		   if(f==false) {
			   throw new Exception("Hi");
		   }
		   this.dispose();
		   Frame2new fn=new Frame2new();
		   fn.showButtonDemo();
		   

	   }catch(Exception e) {
		   JOptionPane.showMessageDialog(null, "Invalid password!");
	   }

   }



}


