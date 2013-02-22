package cs213.photoAlbum.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs213.photoAlbum.control.manipulation;
import cs213.photoAlbum.model.user;

/**
 * @author Alexio Mota
 * @author Hua Yang
 */
public class Admin extends JFrame{
	
	private static final long serialVersionUID = -7914752009957520791L;
	private static manipulation manipulator = new manipulation();
	private static JFrame Lib;
	private JButton add_User;
	private JButton delete_User;
	private JButton logout;
	private JButton listUsers;
	
	private static DefaultListModel userInfo;
	private JList list;
	
	public Admin(String title){
		super(title);
		
		add_User = new JButton("Add User");
		delete_User = new JButton("Delete User");
		logout = new JButton("Logout");
		listUsers = new JButton("List Users");
		
		add_User.addActionListener(new AddListener());
		delete_User.addActionListener(new DeleteListener());
		logout.addActionListener(new LogoutListener());
		listUsers.addActionListener(new ListUsersListener());
		
		add_User.setAlignmentX(Component.CENTER_ALIGNMENT);
		delete_User.setAlignmentX(Component.CENTER_ALIGNMENT);
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		userInfo = new DefaultListModel();
		list = new JList(userInfo);
		list.setSelectionMode(1);
		//list.addListSelectionListener(new selectListener());
		
		//right panel of split
		JPanel right_panel = new JPanel();
		right_panel.setLayout(new BoxLayout(right_panel, BoxLayout.Y_AXIS));
		right_panel.setBackground(Color.WHITE);

		JScrollPane userList = new JScrollPane(list);
		userList.setBorder(BorderFactory.createTitledBorder("List Of Users"));
		
		JPanel inner = new Borders("", "");
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.add(listUsers);
		right_panel.add(inner);
		right_panel.add(userList);
		
		//header
		JPanel pane = new Borders("Project", "Administrator");
		// left pane of split
		JPanel left_panel = new Borders("Options", "");
		left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
		left_panel.add(Box.createVerticalStrut(20));
		left_panel.add(add_User);
		left_panel.add(Box.createVerticalStrut(20));
		left_panel.add(delete_User);
		left_panel.add(Box.createVerticalStrut(60));
		left_panel.add(logout);
		
		JSplitPane splits = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				left_panel, right_panel);
		
		add(pane, BorderLayout.NORTH);
		add(splits, BorderLayout.CENTER);
	}
	
	class AddListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			addUser.init_add();
		}
		
	}
	
	class DeleteListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				deleteUser.init_delete();
			}
			
		}
	
	class LogoutListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			Login.init_login();
			Lib.dispose();
		}
		
	}
	
	class ListUsersListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			LinkedList<user> list = new LinkedList<user>();
			list = manipulator.listUsers();
			userInfo.clear();
			if (list == null) {
				JOptionPane.showMessageDialog(null,
						"No users exist.", "Error: empty", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				for(int index = 0; index < list.size(); index++)
					userInfo.addElement("<" + list.get(index).get_id() + ">");
			}
		}
	}
	
	public static void init_admin() {
		
		Lib = new Admin("Admin");
		Lib.setSize(600,500);
		Lib.setVisible(true);
		// center on screen
		Lib.setLocationRelativeTo(null);
		Lib.setResizable(true);  
		Lib.setMinimumSize(Lib.getMinimumSize());

		Lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
