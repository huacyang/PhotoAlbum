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

/**
 * @author Hua Yang
 */
public class Album extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static JFrame album_frame;
	public static String album_name;
	
	 int previousPoint;
	 JList photo_list;
     public static DefaultListModel photo_list_model;
     static manipulation manipulate = new manipulation();
    
    /**
     * Creates a bordered layout for JFrame
     */
    public Album(String user) throws IOException
    {
        super(new BorderLayout());
        
        previousPoint = -2;
        photo_list_model = new DefaultListModel();
        
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
		
		JButton add_photo = new JButton("Add Photo");
		add_photo.addActionListener(new add_photo_listener());
		add_photo.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton delete_photo = new JButton("Delete Photo");
		delete_photo.addActionListener(new delete_photo_listener());
		delete_photo.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton recap = new JButton("Recaption");
		recap.addActionListener(new recap_listener());
		recap.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton display = new JButton("Display Photo");
		display.addActionListener(new display_listener());
		display.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton add_tag = new JButton("Add Tag");
		add_tag.addActionListener(new add_tag_listener());
		add_tag.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton delete_tag = new JButton("Delete Tag");
		delete_tag.addActionListener(new delete_tag_listener());
		delete_tag.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton move = new JButton("Move Photo");
		move.addActionListener(new move_listener());
		move.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton show = new JButton("Slide Show");
		show.addActionListener(new show_listener());
		show.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(add_photo);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(delete_photo);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(recap);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(display);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(add_tag);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(delete_tag);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(move);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(show);
		
		panel.setBorder(BorderFactory.createTitledBorder("Album"));
		panel.setBackground(Color.white);
		
		return panel;
	}
    
    /**
     * Implements add photo listener
     */
    class add_photo_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.add_photo();
			create.setSize(550,200);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements delete photo listener
     */
    class delete_photo_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.delete_photo();
			create.setSize(550,150);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements recap listener
     */
    class recap_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.recap();
			create.setSize(550,150);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements display listener
     */
    class display_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.display();
			create.setSize(550,150);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements add tag listener
     */
    class add_tag_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.add_tag();
			create.setSize(550,250);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements delete tag listener
     */
    class delete_tag_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.delete_tag();
			create.setSize(550,250);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements move photo listener
     */
    class move_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame create = Album_Cmd.move();
			create.setSize(550,250);
			create.setVisible(true);
			create.setResizable(false);
			create.setLocationRelativeTo(null);
		}
    }
    
    /**
     * Implements slide show listener
     */
    class show_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			slideshow.init_show();
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
    		JButton open = new JButton("Back");
    		open.addActionListener(new back_listener());
    		top.add(open);
    		top.setBackground(Color.white);
    		// creates the mid inner JScrollPane
    		photo_list = new JList(photo_list_model);
    		photo_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		photo_list.setSelectedIndex(0);
    		photo_list.addListSelectionListener(new selectListener());
            JScrollPane mid = new JScrollPane(photo_list);
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
    class back_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				album_frame.dispose();
				User.init_user(User.user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
			album_frame.dispose();
			Login.init_login();
		}
	}
    
    /**
     * Updates the current user selection of albums
     */
    class selectListener implements ListSelectionListener
    {
		public void valueChanged(ListSelectionEvent arg0)
		{
			int pointAt = photo_list.getSelectedIndex();
			if (pointAt != previousPoint) {
				previousPoint = pointAt;
			}
		}
    }
    
    /**
     * Updates the current album list
     */
    public static void update_album_list() {
    	photo_list_model.clear();
    	manipulate.listPhotos(User.user, User.album.get_album());
    }
    
	public static void init_album() throws IOException {
		album_frame = new JFrame("User Login");
		album_name = User.album.get_album();
		JComponent jcomponent = new Album(album_name);
		jcomponent.setOpaque(true);
		
		album_frame.setSize(600,500);
		album_frame.setVisible(true);
		album_frame.setMinimumSize(album_frame.getSize());
		album_frame.setContentPane(jcomponent);
		album_frame.setLocationRelativeTo(null);
		album_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
