package cs213.photoAlbum.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs213.photoAlbum.control.manipulation;

/**
 * @author Hua Yang
 */
public class User_Cmd {

	public static manipulation manipulate = new manipulation();
	public static JTextField existing_input = new JTextField(25);
	public static JTextField new_input = new JTextField(25);
	/**
	 * Creates the JFrame object for the create album function
	 * @return the JFrame object
	 */
	public static JFrame create_album() {
		JFrame create = new JFrame("Create Album");
		JLabel existing_label = new JLabel("Enter new Album name:");
		JButton button = new JButton("Create");
		button.addActionListener(new create_listener(create));
		// top panel
		JPanel titlePanel = new Borders("Create", "Create Album");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(existing_label);
		inputPanel.add(existing_input);
		inputPanel.add(button);
		
		create.setLayout(new BorderLayout());
		create.add(titlePanel, BorderLayout.NORTH);
		create.add(inputPanel, BorderLayout.CENTER);
		return create;
	}
	
	/**
     * Implements create listener
     */
    static class create_listener implements ActionListener {
    	private JFrame frame;
    	
		public create_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			String name = existing_input.getText();
			if (manipulate.createAlbum(User.user, name)) {
				JOptionPane.showMessageDialog(null, 
						"Created album <" + name + ">.", 
						"Created", JOptionPane.INFORMATION_MESSAGE);

				User.update_album_list();
				manipulate.write(null);
			} else {
				JOptionPane.showMessageDialog(null, 
						"Album <" + name + "> already exist!", 
						"Fail to create", JOptionPane.INFORMATION_MESSAGE);
			}
			this.frame.dispose();
		}
    }
	
	/**
	 * Creates the JFrame object for the delete album function
	 * @return the JFrame object
	 */
	public static JFrame delete_album() {
		JFrame delete = new JFrame("Delete Album");
		JLabel existing_label = new JLabel("Enter existing Album name:");
		JButton button = new JButton("Delete");
		button.addActionListener(new delete_listener(delete));
		// top panel
		JPanel titlePanel = new Borders("Delete", "Delete Album");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(existing_label);
		inputPanel.add(existing_input);
		inputPanel.add(button);
		
		delete.setLayout(new BorderLayout());
		delete.add(titlePanel, BorderLayout.NORTH);
		delete.add(inputPanel, BorderLayout.CENTER);
		return delete;
	}
	
	/**
     * Implements delete listener
     */
    static class delete_listener implements ActionListener {
    	private JFrame frame;
    	
		public delete_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			String name = existing_input.getText();
			if (manipulate.deleteAlbum(User.user, name)) {
				JOptionPane.showMessageDialog(null, 
						"Deleted album <" + name + ">.", 
						"Deleted", JOptionPane.INFORMATION_MESSAGE);
				manipulate.write(null);
				User.update_album_list();
			} else {
				JOptionPane.showMessageDialog(null, 
						"Album <" + name + "> does not exist!", 
						"Fail to delete", JOptionPane.INFORMATION_MESSAGE);
			}
			this.frame.dispose();
		}
    }
	
	/**
	 * Creates the JFrame object for the rename album function
	 * @return the JFrame object
	 */
	public static JFrame rename_album() {
		JFrame rename = new JFrame("Rename Album");
		JLabel existing_label = new JLabel("Enter existing Album name:");
		JLabel new_label = new JLabel("Enter new Album name:");
		JButton button = new JButton("Rename");
		button.addActionListener(new rename_listener(rename));
		// top panel
		JPanel titlePanel = new Borders("Rename", "Rename Album");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(existing_label);
		inputPanel.add(existing_input);
		inputPanel.add(new_label);
		inputPanel.add(new_input);
		inputPanel.add(button);
		
		rename.setLayout(new BorderLayout());
		rename.add(titlePanel, BorderLayout.NORTH);
		rename.add(inputPanel, BorderLayout.CENTER);
		return rename;
	}
	
	/**
     * Implements rename listener
     */
    static class rename_listener implements ActionListener {
    	private JFrame frame;
    	
		public rename_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			if (manipulate.rename_album(User.user, existing_input.getText(), new_input.getText())) {
				JOptionPane.showMessageDialog(null, 
						"Renamed album <" + existing_input.getText() + ">.", 
						"Renamed", JOptionPane.INFORMATION_MESSAGE);
				manipulate.write(null);
				User.update_album_list();
			} else {
				JOptionPane.showMessageDialog(null, 
						"Fail to rename album <" + existing_input.getText() + ">.", 
						"Fail to rename", JOptionPane.INFORMATION_MESSAGE);
			}
			this.frame.dispose();
		}
    }
}
