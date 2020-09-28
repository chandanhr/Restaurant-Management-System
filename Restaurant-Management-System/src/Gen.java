import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Gen {

	private JFrame frame;
	private JTextField food;
	private JTextField quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gen window = new Gen();
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
	public Gen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 716, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		food = new JTextField();
		food.setBounds(205, 38, 263, 26);
		frame.getContentPane().add(food);
		food.setColumns(10);
		
		JLabel lblFood = new JLabel("food name");
		lblFood.setBounds(141, 42, 62, 22);
		frame.getContentPane().add(lblFood);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(141, 75, 62, 22);
		frame.getContentPane().add(lblQuantity);
		
		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(205, 75, 263, 26);
		frame.getContentPane().add(quantity);
	}
}
