import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.*;

public class UserInterface {

	private JFrame frame;
	private JTextField bookField;
	private JTextField priceField;
	private JTextField discountCodeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
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
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 239, 213));
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Shopping Cart Menu");
		frame.getContentPane().setLayout(null);
		
		JLabel bookNameLabel = new JLabel("Book name:");
		bookNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookNameLabel.setBounds(27,64,100,15);
		frame.getContentPane().add(bookNameLabel);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceLabel.setBounds(27, 132, 50, 15);
		frame.getContentPane().add(priceLabel);
		
		bookField = new JTextField();
		bookField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookField.setBounds(121, 61, 527, 22);
		frame.getContentPane().add(bookField);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceField.setBounds(485, 129, 163, 22);
		frame.getContentPane().add(priceField);
		
		JLabel discountCodeLabel = new JLabel("Discount code (if you have one): ");
		discountCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		discountCodeLabel.setBounds(247, 129, 241, 20);
		frame.getContentPane().add(discountCodeLabel);
		
		discountCodeField = new JTextField();
		discountCodeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		discountCodeField.setBounds(82, 129, 150, 22);
		frame.getContentPane().add(discountCodeField);
		
		TextArea textArea = new TextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setEnabled(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setBounds(205, 202, 443, 285);
		frame.getContentPane().add(textArea);
		
		JButton addToCartButton = new JButton("Add to Cart");
		addToCartButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addToCartButton.setBounds(21, 299, 164, 57);
		frame.getContentPane().add(addToCartButton);
		
		JButton emptyCartButton = new JButton("Empty Cart");
		emptyCartButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emptyCartButton.setBounds(21, 202, 164, 57);
		frame.getContentPane().add(emptyCartButton);
		
		JButton checkoutButton = new JButton("CHECKOUT");
		checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkoutButton.setBounds(21, 403, 164, 57);
		checkoutButton.setForeground(Color.RED);
		frame.getContentPane().add(checkoutButton);
		
	}

}
