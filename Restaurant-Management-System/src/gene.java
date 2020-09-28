import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class gene {

	private JFrame frame;
	private JTextField q;
	private JTable cart;
	
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gene window = new gene();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gene() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Generate Bill");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setBounds(195, 24, 95, 24);
		frame.getContentPane().add(lblFoodName);
		
		JComboBox f = new JComboBox();
		f.setBounds(262, 25, 198, 24);
		frame.getContentPane().add(f);
		try {
			 String Q2="select f_name from food;";
             ResultSet rs1=Database.Retrieve(Q2);

             while (rs1.next()){
            	 f.addItem(rs1.getString(1));
             }
			
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(getParent(), "Error!");
		}
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(195, 59, 95, 24);
		frame.getContentPane().add(lblQuantity);
		
		q = new JTextField();
		q.setBounds(262, 59, 57, 24);
		frame.getContentPane().add(q);
		q.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 149, 616, 155);
		frame.getContentPane().add(scrollPane);
		
		cart = new JTable();
		scrollPane.setViewportView(cart);
		Button button = new Button("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try{
					 String food=(String) f.getSelectedItem();

					 	String Q10="select f_quantity from food where f_name ='"+food+"';";
		                ResultSet rs10=Database.Retrieve(Q10);
		                while(rs10.next()) {
		                	if(rs10.getDouble(1)<=0 || Integer.parseInt(q.getText())>rs10.getDouble(1)) {
		                		JOptionPane.showMessageDialog(getParent(), "No Stock of item "+food);
		                		throw new Exception("hi");
		                	}
		                }

					 
					 
		                String Q1="select f_prize from food where f_name ='"+food+"';";
		                ResultSet rs=Database.Retrieve(Q1);

		                double pr=0;
		                while (rs.next()){
		                   pr=rs.getDouble(1);
		                }
		                String Q4="insert into orders values('"+food+"',"+Integer.parseInt(q.getText())+","+pr+");";
		                int z=Database.insertTable(Q4);		     
		                if(z==0)
		                	throw new Exception("insert table error");
		                String Q5="select * from orders;";
		                ResultSet rs3=Database.Retrieve(Q5);
		                cart.setModel(DbUtils.resultSetToTableModel(rs3));
		                
		                String Q9="update food set f_quantity=f_quantity-"+Integer.parseInt(q.getText())+" where f_name='"+food+"';";
		                Database.insertTable(Q9);
		            }
		            catch(Exception ex){
		            	JOptionPane.showMessageDialog(getParent(), "Invalid data!");
		            }
			}
		});
		button.setBounds(262, 99, 70, 22);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(110, 349, 483, 136);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		scrollPane_1.setViewportView(textArea);
		frame.setBounds(100, 100, 694, 535);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Button button_1 = new Button("Checkout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Q5="select * from orders;";
	                ResultSet rs3=Database.Retrieve(Q5);
	                 
	                String msg="";
	                double tp=0;
	                while(rs3.next()) {
	                	msg+="Food Name : " + rs3.getString(1) + " || Quantity : "+ rs3.getInt(2) + " || Price : RS " + rs3.getDouble(3)*rs3.getInt(2) + "\n";
	                	tp+=rs3.getDouble(3)*rs3.getInt(2);
	                }
	                msg+="******* Total Price= RS "+tp+"  *******";
	                
	                String Q7="insert into bills(price) values("+tp+")";
	                Database.insertTable(Q7);
	                
	                textArea.setText(msg);
	                Database.insertTable("delete from orders");
	          
	                
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(getParent(), "Invalid Data!");
				}
			}
		});
		button_1.setBounds(292, 310, 70, 22);
		frame.getContentPane().add(button_1);
		
		
	}
	private Component getParent() {
		// TODO Auto-generated method stub
		return null;
	}
}
