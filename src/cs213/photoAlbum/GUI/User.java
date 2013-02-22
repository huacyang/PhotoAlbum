package cs213.photoAlbum.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs213.photoAlbum.control.manipulation;
import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.user;

/**
 * @author Hua Yang
 */
public class User extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static JFrame user_frame;
	public static album album;
	static user user = new user();
	static manipulation manipulate = new manipulation();
	
	 int previousPoint;
	 JList album_list;
     public static DefaultListModel album_list_model;
    
    /**
     * Creates a bordered layout for JFrame
     */
    public User(String user) throws IOException
    {
        super(new BorderLayout());
        
        previousPoint = -2;
        album_list_model = new DefaultListModel();
        
        // creates the top JPanel
        JPanel titlePanel = new Borders("User", user);
        
        // creates the middle JPanel
        JPanel left_panel = left_panel();
        JPanel right_panel = right_panel();
        JSplitPane jsplitpanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left_panel, right_panel);
        jsplitpanel.setOneTouchExpandable(false);
        jsplitpanel.setDividerLocation(150);
        
        // creates the bottom JPanel
        JPanel logoutPanel = logout();
        
        // lays out the panels
        add(titlePanel, BorderLayout.NORTH);
        add(jsplitpanel, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);
        
        update_album_list();
        validate();
    }
    
    /**
     * Creates the side button JPanel for the user,
     *  contains the follow buttons: Create Album, Delete Album, Rename Album
     * @return the JPanel object
     */
    public JPanel left_panel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton create = new JButton("Create Album");
		create.addActionListener(new create_listener());
		create.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton delete = new JButton("Delete Album");
		delete.addActionListener(new delete_listener());
		delete.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton rename = new JButton("Rename Album");
		rename.addActionListener(new rename_listener());
		rename.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(Box.createRigidArea(new Dimension(0,25)));
		panel.add(create);
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(delete);
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(rename);
		
		panel.setBorder(BorderFactory.createTitledBorder("Album"));
		panel.setBackground(Color.white);
		
		return panel;
	}
    
    /**
     * Implements open listener
     */
    class create_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = User_Cmd.create_album();
			create.setSize(550,150);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements delete listener
     */
    class delete_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame delete = User_Cmd.delete_album();
			delete.setSize(550,150);
			delete.setVisible(true);
			delete.setResizable(false);
			delete.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements rename listener
     */
    class rename_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame rename = User_Cmd.rename_album();
			rename.setSize(550,200);
			rename.setVisible(true);
			rename.setResizable(false);
			rename.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Creates the right JPanel for the center of the JFrame
     * @return the JFrame object
     */
    public JPanel right_panel() {
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    		// creates the top inner JPanel
    		JPanel top = new JPanel();
    		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
    		JButton open = new JButton("Open Album");
    		open.addActionListener(new open_listener());
    		JButton search = new JButton("Search Photo");
    		search.addActionListener(new search_listener());
    		top.add(open);
    		top.add(Box.createRigidArea(new Dimension(100, 0)));
    		top.add(search);
    		top.setBackground(Color.white);
    		// creates the mid inner JScrollPane
    		album_list = new JList(album_list_model);
    		album_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		album_list.setSelectedIndex(0);
    		album_list.addListSelectionListener(new selectListener());
    		JScrollPane mid = new JScrollPane(album_list);
            mid.setBackground(Color.white);
            mid.setBorder(BorderFactory.createTitledBorder("Existing Albums"));
    	
        panel.add(Box.createRigidArea(new Dimension(0,10)));
    	panel.add(top);
    	panel.add(Box.createRigidArea(new Dimension(0,10)));
    	panel.add(mid);
    	panel.setBackground(Color.white);
		return panel;
    }
    
    /**
     * Implements open listener
     */
    class open_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String name = JOptionPane.showInputDialog(null, "Enter album name to open : ", "Opening Album", 1);
				album = manipulate.open_album(user, name);
				if (album == null) {
					JOptionPane.showMessageDialog(null, "The album does not exist!", "Fail to open", JOptionPane.WARNING_MESSAGE);
				} else {
					Album.init_album();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
    
    /**
     * Implements search listener
     */
    class search_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Searching...", "Search", JOptionPane.WARNING_MESSAGE);
			photoSearch.init_search();
			user_frame.dispose();
		}
    }
    
    /**
     * Creates the JPanel with a logout JButton
     * @return the JPanel object
     */
    public JPanel logout() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.setBorder(BorderFactory.createTitledBorder("-"));
	    JButton logout_button = new JButton("Logout");
	    logout_button.setAlignmentX(Component.CENTER_ALIGNMENT);
	    logout_button.addActionListener(new logout_listener());
	    panel.add(logout_button);
	    
	    panel.setSize(2, 2);
	    panel.setBackground(Color.white);
	    return panel;
	}
	
    /**
     * Close the current session and opens the log in screen
     */
	class logout_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "You have logged off safely.", "Logout", JOptionPane.WARNING_MESSAGE);
			Login.init_login();
			user_frame.dispose();
		}
	}
    
    /**
     * Updates the current user selection of albums
     */
    class selectListener implements ListSelectionListener
    {
		public void valueChanged(ListSelectionEvent arg0)
		{
			int pointAt = album_list.getSelectedIndex();
			if (pointAt != previousPoint) {
				previousPoint = pointAt;
			}
		}
    }
    
    /**
     * Updates the current album list
     */
    public static void update_album_list() {
    	manipulate.listAlbums(user);
    }
    
	public static void init_user(user username) throws IOException {
		user = username;
		user_frame = new JFrame("User Login");
		
		JComponent jcomponent = new User(user.get_user());
		jcomponent.setOpaque(true);
		
		user_frame.setSize(600,500);
		user_frame.setVisible(true);
		user_frame.setMinimumSize(user_frame.getSize());
		user_frame.setContentPane(jcomponent);
		user_frame.setLocationRelativeTo(null);
		user_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
