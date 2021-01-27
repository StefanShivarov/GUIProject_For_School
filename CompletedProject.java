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

public class CompletedProject {

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
					CompletedProject window = new CompletedProject();
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
	public CompletedProject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(216, 191, 216));
		frame.setBounds(100, 100, 831, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Shopping Cart Menu");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel bookNameLabel = new JLabel("Book name:");
		bookNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		bookNameLabel.setBounds(21,33,113,31);
		frame.getContentPane().add(bookNameLabel);
		
		JLabel prLbl = new JLabel("Book price:");
		prLbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		prLbl.setBounds(21, 105, 113, 31);
		frame.getContentPane().add(prLbl);

		JLabel priceLabel = new JLabel("");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		priceLabel.setBounds(120, 105, 129, 31);
		frame.getContentPane().add(priceLabel);

		BookStore bookStore = new BookStore();
		bookStore.fillBooksInfo(new File("C:\\Users\\Stefan Jr\\Desktop\\books.txt")
				, new File("C:\\Users\\Stefan Jr\\Desktop\\prices.txt"));
		ArrayList<String> booksList = bookStore.getBookTitles();
		
		
		JComboBox comboBox = new JComboBox(booksList.toArray());
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 19));
		comboBox.setSelectedIndex(-1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//задаване на цена спрямо селектираната книга от списъка
				if(comboBox.getSelectedIndex()!=-1) {
					String[]bookInfoArr = String.valueOf(comboBox.getSelectedItem()).split(" - ");
					String title = bookInfoArr[0];
					Book currentBook = bookStore.getBookByTitle(title);
					double price = currentBook.getPrice();
					priceLabel.setText(String.format("%.2f lv.", price));
				}
			}
		});
		comboBox.setBounds(132, 31, 659, 33);
		frame.getContentPane().add(comboBox);
		
		discountCodeField = new JTextField();
		discountCodeField.setForeground(Color.WHITE);
		discountCodeField.setBackground(new Color(0, 0, 128));
		discountCodeField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		discountCodeField.setBounds(431, 105, 360, 31);
		discountCodeField.setColumns(10);
		frame.getContentPane().add(discountCodeField);
		discountCodeField.setVisible(false);
		
		
		JCheckBox discountCheck = new JCheckBox("Use discount code");
		discountCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//при натискане на check box-a за код за отстъпка се появява text field
				//съответно при повторно натискане, този text field се скрива
				discountCodeField.setVisible(true);
				if(!discountCheck.isSelected()) {
					discountCodeField.setVisible(false);
				}
			}
		});
		discountCheck.setFont(new Font("Tahoma", Font.PLAIN, 19));
		discountCheck.setBackground(new Color(216, 191, 216));
		discountCheck.setBounds(255, 109, 178, 23);
		frame.getContentPane().add(discountCheck);
		
		TextArea textArea = new TextArea();
		textArea.setEnabled(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setBounds(209, 168, 582, 319);
		frame.getContentPane().add(textArea);
		
		
		JButton addToCartButton = new JButton("Add to Cart");
		addToCartButton.setForeground(Color.WHITE);
		addToCartButton.setBackground(new Color(0, 0, 128));
		addToCartButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addToCartButton.setBounds(21, 183, 166, 79);
		addToCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//принтиране на текст за добавена книга към количката и увеличаване на сумата
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
		emptyCartButton.setForeground(Color.WHITE);
		emptyCartButton.setBackground(new Color(0, 0, 128));
		emptyCartButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		emptyCartButton.setBounds(21, 294, 166, 79);
		emptyCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//изчистване на всички полета, зануляване на сумите и изпразване на списъка със закупени книги
				comboBox.setSelectedIndex(-1);
				discountCodeField.setText("");
				priceLabel.setText("");
				textArea.setText("");
				discountCheck.setSelected(false);
				discountCodeField.setVisible(false);
				sum = 0;
				boughtBooks = new ArrayList<>();
				
			}
		});
		frame.getContentPane().add(emptyCartButton);
		
		JButton checkoutButton = new JButton("CHECKOUT");
		checkoutButton.setBackground(new Color(0, 0, 128));
		checkoutButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		checkoutButton.setBounds(21, 408, 166, 79);
		checkoutButton.setForeground(Color.YELLOW);
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//изкарване на списък със закупените книги
				textArea.append("----------------------CHECKOUT----------------------\n");
				int pos = 1;
				for(Book b : boughtBooks) {
					textArea.append(String.format("%d) \"%s\" - %s -----> %.2f lv.\n",pos, b.getTitle(), b.getAuthor(), b.getPrice()));
					pos++;
				}
				try {
					if(discountCheck.isSelected()) {
						//при използване на код за отстъпка се взимат последните 2 цифри от кода и се преобръщат в процент отстъпка
						double discountPercentage = Double.parseDouble(discountCodeField.getText()
								.substring(discountCodeField.getText().length()-2, discountCodeField.getText().length()));
						
						sum*=(1-discountPercentage/100);
						textArea.append(String.format("Discount code \"%s\" used -----> - %.0f%c\n"
								,discountCodeField.getText(), discountPercentage, '%'));
					}
				}catch(NumberFormatException ex) {
					textArea.append("Invalid discount code! No discount.\n");
				}catch(StringIndexOutOfBoundsException ex2) {
					textArea.append("Invalid discount code! No discount.\n");

				}
				textArea.append("----------------------------------------------------\n");
				textArea.append(String.format("TOTAL : %.2f lv.", sum));
				
			}
		});
		frame.getContentPane().add(checkoutButton);
	}
}
