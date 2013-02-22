package cs213.photoAlbum.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * @author Alexio Mota
 */
public class slideshow extends JFrame{

	private static final long serialVersionUID = 4336358001211389873L;
	private static JFrame frame;
	private JButton next;
	private static DefaultListModel Info;
	private JList list;
	
	slideshow(String title){
		super(title);
		
		next = new JButton("Next");
		next.addActionListener(new slideshowListener());
		next.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Info = new DefaultListModel();
		list = new JList(Info);
		list.setSelectionMode(1);
		
		JPanel slide = new JPanel();
		slide.add(next);
		
		JScrollPane show = new JScrollPane(list);
		show.setBorder(BorderFactory.createTitledBorder("Picture:"));
		
		JPanel header = new Borders("Project", "Slide Show");
		
		add(header, BorderLayout.NORTH);
		add(show, BorderLayout.CENTER);
		add(slide, BorderLayout.SOUTH);
		
	}
	
	class slideshowListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			/**
			 * +++++++++++++++++++++++++++++
			 */
		}
		
	}
	public static void init_show() {
		
		frame = new slideshow("slideshow");
		frame.setSize(300,300);
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
