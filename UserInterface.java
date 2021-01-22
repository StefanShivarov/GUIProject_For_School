import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class UserInterface {

	private JFrame frame;
	private JTextField discountCodeField;
	double sum = 0;
	ArrayList<Book> boughtBooks = new ArrayList<>();

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
		frame.getContentPane().setBackground(new Color(216, 191, 216));
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Shopping Cart Menu");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel bookNameLabel = new JLabel("Book name:");
		bookNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookNameLabel.setBounds(22,65,100,15);
		frame.getContentPane().add(bookNameLabel);
		
		JLabel prLbl = new JLabel("Book price:");
		prLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prLbl.setBounds(27, 132, 80, 15);
		frame.getContentPane().add(prLbl);

		JLabel priceLabel = new JLabel("");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceLabel.setBounds(113, 132, 100, 16);
		frame.getContentPane().add(priceLabel);

		BookStore bookStore = new BookStore();
		bookStore.fillBooksInfo(new File("C:\\Users\\Stefan Jr\\Desktop\\books.txt")
				, new File("C:\\Users\\Stefan Jr\\Desktop\\prices.txt"));
		ArrayList<String> booksList = bookStore.getBookTitles();
		
		
		JComboBox comboBox = new JComboBox(booksList.toArray());
		comboBox.setSelectedIndex(-1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedIndex()!=-1) {
					String[]bookInfoArr = String.valueOf(comboBox.getSelectedItem()).split(" - ");
					String title = bookInfoArr[0];
					Book currentBook = bookStore.getBookByTitle(title);
					double price = currentBook.getPrice();
					priceLabel.setText(String.format("%.2f lv.", price));
				}
			}
		});
		comboBox.setBounds(132, 63, 516, 22);
		frame.getContentPane().add(comboBox);
		
		discountCodeField = new JTextField();
		discountCodeField.setBounds(432, 131, 216, 22);
		discountCodeField.setColumns(10);
		frame.getContentPane().add(discountCodeField);
		discountCodeField.setVisible(false);
		
		
		JCheckBox discountCheck = new JCheckBox("Use dicount code");
		discountCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				discountCodeField.setVisible(true);
				if(!discountCheck.isSelected()) {
					discountCodeField.setVisible(false);
				}
			}
		});
		discountCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		discountCheck.setBackground(new Color(216, 191, 216));
		discountCheck.setBounds(270, 130, 156, 23);
		frame.getContentPane().add(discountCheck);
		
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
				
				String[]bookInfoArr = String.valueOf(comboBox.getSelectedItem()).split(" - ");
				String title = bookInfoArr[0];
				Book addedBook = bookStore.getBookByTitle(title);
				sum+=addedBook.getPrice();
				textArea.append(String.format("Added %s to the cart.%n",addedBook.toString()));
				boughtBooks.add(addedBook);
			}
		});
		frame.getContentPane().add(addToCartButton);
		
		JButton emptyCartButton = new JButton("Empty Cart");
		emptyCartButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emptyCartButton.setBounds(21, 301, 164, 57);
		emptyCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.setSelectedIndex(-1);
				discountCodeField.setText("");
				priceLabel.setText("");
				textArea.setText("");
				discountCheck.setSelected(false);
				discountCodeField.setVisible(false);
				
			}
		});
		frame.getContentPane().add(emptyCartButton);
		
		JButton checkoutButton = new JButton("CHECKOUT");
		checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkoutButton.setBounds(21, 403, 164, 57);
		checkoutButton.setForeground(Color.RED);
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textArea.append("----------------------CHECKOUT----------------------\n");
				int pos = 1;
				for(Book b : boughtBooks) {
					textArea.append(String.format("%d) \"%s\" - %s -----> %.2f lv.\n",pos, b.getTitle(), b.getAuthor(), b.getPrice()));
					pos++;
				}
				if(discountCheck.isSelected()) {
					
					double discountPercentage = Double.parseDouble(discountCodeField.getText()
							.substring(discountCodeField.getText().length()-2, discountCodeField.getText().length()));
					
					sum*=(1-discountPercentage/100);
					textArea.append(String.format("Discount code \"%s\" used -----> - %.0f%c\n"
							,discountCodeField.getText(), discountPercentage, '%'));
				}
				
				textArea.append("----------------------------------------------------\n");
				textArea.append(String.format("TOTAL : %.2f lv.", sum));
				
			}
		});
		frame.getContentPane().add(checkoutButton);
	}
}
