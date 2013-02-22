package cs213.photoAlbum.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import cs213.photoAlbum.control.manipulation;
import cs213.photoAlbum.model.user;
import cs213.photoAlbum.simpleview.InterView;

/**
 * @author Alexio Mota
 */
public class Login extends JFrame{

	private static final long serialVersionUID = 8301979061800203383L;
	private JLabel IDtext;
	private JTextField user;
	private JButton loginB;
	public static String UserName;
	private static JFrame Lib;
	private static manipulation manipulator = new manipulation();
	
	Login(String userName){
		super(userName);
		
		user = new JTextField(10);
		user.setMinimumSize(user.getMinimumSize());
		user.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		IDtext = new JLabel("Enter User ID:");
		IDtext.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		loginB = new JButton("Login");
		loginB.addActionListener(new LoginListener());
		loginB.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel top_pane = new Borders("Project", "PhotoAlbum - Group 24");
		
		JPanel logPane = new JPanel();
		logPane.setLayout(new BoxLayout(logPane, BoxLayout.Y_AXIS));
		logPane.add(IDtext);
		JPanel textF = new JPanel();
		textF.setLayout(new BoxLayout(textF, BoxLayout.X_AXIS));
		textF.add(user);
		logPane.add(textF);
		logPane.add(loginB);
		
		add(top_pane, BorderLayout.NORTH);
		add(logPane, BorderLayout.CENTER);
		
		manipulator.read(null);
	}
	
	class LoginListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			UserName = user.getText();
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
			if (UserName.equals("admin")) {
				Admin.init_admin();
			} else {
				try {
					user login_user = manipulator.login(UserName);
					if (login_user != null) {
						manipulator.write(login_user.get_user());
						User.init_user(login_user);
					} else {
						JOptionPane.showMessageDialog(null, 
								"User <" + UserName + "> does not exist.", 
								"Fail to login", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	public static void init_login() {
		
		Lib = new Login("UserName");
		Lib.setSize(250,150);
		Lib.setVisible(true);
		// center on screen
		Lib.setLocationRelativeTo(null);
		Lib.setResizable(true); 
		Lib.setMinimumSize(Lib.getMinimumSize());

		Lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
