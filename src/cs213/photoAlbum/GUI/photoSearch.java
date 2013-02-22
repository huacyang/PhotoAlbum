package cs213.photoAlbum.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * @author Alexio Mota
 */
public class photoSearch extends JFrame{

	private static final long serialVersionUID = -6475301508762174134L;
	private static JFrame frame;
	private JButton logout;
	private JButton back;
	private JButton saveToAlbum;
	private JButton searchByDate;
	private JButton searchBytag;
	
	private JTextField byDate;
	private JTextField byTag;
	
	private static DefaultListModel picInfo;
	private JList list;

	photoSearch(String title){
		super(title);
		
		byDate = new JTextField(10);
		byTag = new JTextField(10);
		
		logout = new JButton("Logout");
		logout.addActionListener(new logoutListener());
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		back = new JButton("Back");
		back.addActionListener(new backListener());
		back.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		saveToAlbum = new JButton("Save To Album");
		saveToAlbum.addActionListener(new saveToAlbumListener());
		saveToAlbum.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		searchByDate = new JButton("Search By Date");
		searchByDate.addActionListener(new searchByDateListener());
		searchByDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		searchBytag = new JButton("Search By Tag");
		searchBytag.addActionListener(new searchByTagListener());
		searchBytag.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		picInfo = new DefaultListModel();
		list = new JList(picInfo);
		list.setSelectionMode(1);
		list.addListSelectionListener(new selectListener());
		
		JPanel header = new Borders("Project", "Photo Search");
		header.add(logout);
		
		JPanel logOut = new Borders("", "");
		logOut.setLayout(new BoxLayout(logOut, BoxLayout.Y_AXIS));
		logOut.add(logout);
		//left panel
		JPanel left_panel = new Borders("Search Options", "");
		left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
		left_panel.add(Box.createVerticalStrut(10));
		left_panel.add(byDate);
		left_panel.add(Box.createVerticalStrut(10));
		left_panel.add(searchByDate);
		left_panel.add(Box.createVerticalStrut(10));
		left_panel.add(byTag);
		left_panel.add(Box.createVerticalStrut(10));
		left_panel.add(searchBytag);
		//right panel
		JPanel right_panel = new Borders("", "");
		right_panel.setLayout(new BoxLayout(right_panel, BoxLayout.Y_AXIS));
		
		JScrollPane show = new JScrollPane(list);
		show.setBorder(BorderFactory.createTitledBorder("Picture Search Results:"));
		
		JPanel option = new JPanel();
		option.setLayout(new BoxLayout(option, BoxLayout.X_AXIS));
		option.setBackground(Color.WHITE);
		option.add(saveToAlbum);
		option.add(Box.createRigidArea(new Dimension(100, 0)));
		option.add(back);
		right_panel.add(option);
		right_panel.add(show);
		
		JSplitPane splits = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				left_panel, right_panel);
		
		add(header, BorderLayout.NORTH);
		add(logOut, BorderLayout.SOUTH);
		add(splits, BorderLayout.CENTER);
		
	}
	
	class logoutListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
			Login.init_login();
			frame.dispose();
		}
		
	}
	
	class backListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
			try {
				User.init_user(User.user);
				frame.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	 
	class saveToAlbumListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
		}
		
	}
	
	class searchByDateListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
		}
		
	}
	
	class searchByTagListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
		}
		
	}
	
	class selectListener implements ListSelectionListener{
		
		public void valueChanged(ListSelectionEvent e){
			
		}
	}
	
	public static void init_search() {
		
		frame = new photoSearch("Search Photos");
		frame.setSize(600,400);
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
