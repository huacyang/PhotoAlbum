package cs213.photoAlbum.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import cs213.photoAlbum.control.manipulation;

/**
 * @author Alexio Mota
 * @author Hua Yang
 */
public class addUser extends JFrame{

	private static final long serialVersionUID = -778456569381728603L;
	private static JFrame frame;
	private JButton addUserB;
	private JTextField userID;
	private JTextField userName;
	public static manipulation manipulator = new manipulation();
	
	addUser(String title){
		super(title);
		
		addUserB = new JButton(" Add User");
		addUserB.addActionListener(new addUserListener());
		addUserB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		userID = new JTextField(10);
		userName = new JTextField(10);
		
		JPanel pane = new Borders("Project", "Add User");
		
		JPanel actions = new JPanel();
		actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
		
		JPanel IDpane = new Borders("Enter User ID", "");
		IDpane.add(userID);
		
		JPanel Namepane = new Borders("Enter User Name", "");
		Namepane.add(userName);
		actions.add(IDpane);
		actions.add(Namepane);
		actions.add(addUserB);
		
		add(pane, BorderLayout.NORTH);
		add(actions, BorderLayout.CENTER);
	}
	
	class addUserListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			String ID = userID.getText();
			String name = userName.getText();
			
			if (manipulator.addUser(name, ID) == true) {
				JOptionPane.showMessageDialog(null, 
						"Created user <" + ID + "> with name <" + name + ">.", 
						"Adding file", JOptionPane.INFORMATION_MESSAGE);
				manipulator.write(name);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, 
						"User <" + ID + "> already exists with name <" + name + ">.", 
						"Fail to add", JOptionPane.ERROR);
			}
		}
	}
	
	public static void init_add(){
		
		frame = new addUser("User");
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
