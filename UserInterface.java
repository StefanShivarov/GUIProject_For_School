import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserInterface {

	private JFrame frame;
	private JTextField bookField;
	private JTextField discountCodeField;
	private JTextField priceField;

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
		frame.setResizable(false);
		
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
		
		discountCodeField = new JTextField();
		discountCodeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		discountCodeField.setBounds(485, 129, 163, 22);
		frame.getContentPane().add(discountCodeField);
		
		JLabel discountCodeLabel = new JLabel("Discount code (if you have one): ");
		discountCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		discountCodeLabel.setBounds(247, 129, 241, 20);
		frame.getContentPane().add(discountCodeLabel);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceField.setBounds(82, 129, 150, 22);
		frame.getContentPane().add(priceField);
		
		TextArea textArea = new TextArea();
		textArea.setEnabled(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setBounds(205, 202, 443, 285);
		frame.getContentPane().add(textArea);
		
		JButton addToCartButton = new JButton("Add to Cart");
		addToCartButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addToCartButton.setBounds(21, 202, 164, 57);
		addToCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double price = Double.parseDouble(priceField.getText());
				String[] bookArr = bookField.getText().split(" - ");
				String bookName = bookArr[0];
				String bookAuthor = bookArr[1];
				textArea.append(String.format("Added %s by %s to the cart -----> %.2f lv.%n", bookName, bookAuthor, price));
				
			}
		});
		frame.getContentPane().add(addToCartButton);
		
		JButton emptyCartButton = new JButton("Empty Cart");
		emptyCartButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emptyCartButton.setBounds(21, 301, 164, 57);
		emptyCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookField.setText("");
				discountCodeField.setText("");
				priceField.setText("");
				textArea.setText("");
				
			}
		});
		frame.getContentPane().add(emptyCartButton);
		
		JButton checkoutButton = new JButton("CHECKOUT");
		checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkoutButton.setBounds(21, 403, 164, 57);
		checkoutButton.setForeground(Color.RED);
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!discountCodeField.getText().isBlank()) {
					int discountPercentage = Integer.parseInt(discountCodeField.getText()
						.substring(discountCodeField.getText().length()-2,
								discountCodeField.getText().length()));
				
					textArea.append(String.format("Discount code \"%s\" successfully added! -> %d%c discount!",
							discountCodeField.getText(), discountPercentage, '%'));
				}
				
				
			}
		});
		frame.getContentPane().add(checkoutButton);
		
	}

}
