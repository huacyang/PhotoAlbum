package cs213.photoAlbum.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cs213.photoAlbum.control.manipulation;

/**
 * @author Alexio Mota
 * @author Hua Yang
 */
public class deleteUser extends JFrame{

	private static final long serialVersionUID = -4927575956372650806L;
	private static manipulation manipulator = new manipulation();
	private static JFrame frame;
	private JButton deleteUserB;
	private JTextField userID;
	
	deleteUser(String title){
		super(title);
		
		deleteUserB = new JButton(" Delete User");
		deleteUserB.addActionListener(new deleteUserListener());
		deleteUserB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		userID = new JTextField(5);
		
		JPanel pane = new Borders("Project", "Delete User");
		
		JPanel actions = new JPanel();
		actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
		
		JPanel IDpane = new Borders("Enter User ID", "");
		IDpane.add(userID);
		
		actions.add(IDpane);
		actions.add(deleteUserB);
		
		add(pane, BorderLayout.NORTH);
		add(actions, BorderLayout.CENTER);
	}
	
	class deleteUserListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String ID = userID.getText();

			if (manipulator.deleteUser(ID) == true) {
				JOptionPane.showMessageDialog(null, 
						"Deleted user <" + ID + ">.", 
						"Deleting file", JOptionPane.INFORMATION_MESSAGE);
				manipulator.write(ID);
			} else {
				JOptionPane.showMessageDialog(null, 
						"User <" + ID + "> does not exist.", 
						"Fail to delete", JOptionPane.INFORMATION_MESSAGE);
			}
			
			frame.dispose();
		}
		
	}
	
	public static void init_delete() {
		
		frame = new deleteUser("Delete User");
		frame.setSize(300,150);
		frame.setVisible(true);
		// center on screen
		frame.setLocationRelativeTo(null);
		frame.setResizable(false); 
		frame.setMinimumSize(frame.getMinimumSize());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
	}
}
