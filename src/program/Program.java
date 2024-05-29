
package program;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Program {

	private JFrame frame;
	private JTextField textField;
	private JTextField textedition;
	private JTextField textprice;
	private JTable table;
	private JTextField textSearch;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program();
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
	public Program() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/program1","root","");
			System.out.println("Connection established successfully.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database Driver Not Found");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database Connection Failed");
		}
	}
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to load data");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 864, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 31));
		lblNewLabel.setBounds(304, 27, 204, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 89, 380, 212);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(39, 38, 108, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(39, 157, 108, 26);
		panel.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(170, 37, 178, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		textedition = new JTextField();
		textedition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textedition.setColumns(10);
		textedition.setBounds(170, 98, 178, 34);
		panel.add(textedition);
		
		textprice = new JTextField();
		textprice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textprice.setColumns(10);
		textprice.setBounds(170, 156, 178, 34);
		panel.add(textprice);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Edition:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(39, 99, 89, 26);
		panel.add(lblNewLabel_1_2_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname = textField.getText();
				String edition = textedition.getText();
				String price = textprice.getText();
				
				if(bname.equals("") || edition.equals("") || price.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill Complete Information!!!");
				} else {
					try {
						pst = con.prepareStatement("insert into book(name, edition, price) values(?, ?, ?)");
						pst.setString(1, bname);
						pst.setString(2, edition);
						pst.setString(3, price);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added!!!");
						table_load();
						textField.setText("");
						textedition.setText("");
						textprice.setText("");
						textField.requestFocus();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(30, 316, 106, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEdit = new JButton("Exit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBounds(146, 316, 106, 40);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textedition.setText("");
				textprice.setText("");
				textField.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(262, 316, 106, 40);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(400, 89, 426, 315);
		frame.getContentPane().add(scrollPane);
		
	
		table = new JTable(); // Instantiating the table
		scrollPane.setViewportView(table); // Adding the table to the scroll pane
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) 
		        { // Check if a row is selected
		            String name = table.getValueAt(selectedRow, 1).toString(); // Assuming name is in the first column
		            String edition = table.getValueAt(selectedRow, 2).toString(); // Assuming edition is in the second column
		            String price = table.getValueAt(selectedRow, 3).toString(); // Assuming price is in the third column
		            textField.setText(name);
		            textedition.setText(edition);
		            textprice.setText(price);
		        }
		    }
		});

		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 384, 380, 110);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel bn = new JLabel("Book Name:");
		bn.setFont(new Font("Tahoma", Font.BOLD, 15));
		bn.setBounds(38, 20, 99, 29);
		panel_1.add(bn);
		
		textSearch = new JTextField();
		textSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textSearch.setColumns(10);
		textSearch.setBounds(173, 18, 178, 34);
		panel_1.add(textSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(116, 66, 100, 34);
		panel_1.add(btnSearch);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery = textSearch.getText();
				if (searchQuery.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a book name to search!");
				} else {
					try {
						pst = con.prepareStatement("select * from book where name LIKE ?");
						pst.setString(1, "%" + searchQuery + "%");
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Failed to search records");
					}
				}
			}
		});
		 
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String bookName = textField.getText(); // Obtain book name from text field
		        if (!bookName.isEmpty()) {
		            try {
		                pst = con.prepareStatement("DELETE FROM book WHERE name = ?");
		                pst.setString(1, bookName);
		                int rowsDeleted = pst.executeUpdate();
		                if (rowsDeleted > 0) {
		                    JOptionPane.showMessageDialog(null, "Record Deleted!");
		                    table_load(); // Refresh table data
		                } else {
		                    JOptionPane.showMessageDialog(null, "No record found with this name.");
		                }
		                // Clear text fields after deletion
		                textField.setText("");
		                textedition.setText("");
		                textprice.setText("");
		                textField.requestFocus();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Failed to delete record: " + ex.getMessage());
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Please enter a book name to delete.");
		        }
		    }
		});

		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(636, 427, 115, 40);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String bname = textField.getText();
		        String edition = textedition.getText();
		        String price = textprice.getText();
		        if (!bname.isEmpty()) {
		            try {
		                pst = con.prepareStatement("UPDATE book SET edition = ?, price = ? WHERE name = ?");
		                pst.setString(1, edition);
		                pst.setString(2, price);
		                pst.setString(3, bname);
		                int rowsUpdated = pst.executeUpdate();
		                if (rowsUpdated > 0) {
		                    JOptionPane.showMessageDialog(null, "Record Updated!");
		                    table_load(); // Refresh table data
		                } else {
		                    JOptionPane.showMessageDialog(null, "No record found with this name.");
		                }
		                // Clear text fields after update
		                textField.setText("");
		                textedition.setText("");
		                textprice.setText("");
		                textField.requestFocus();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Failed to update record: " + ex.getMessage());
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Please enter a book name to update.");
		        }
		    }
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(468, 427, 121, 40);
		frame.getContentPane().add(btnUpdate);
		
	}
}
