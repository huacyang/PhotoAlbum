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
public class Album_Cmd {

	static manipulation manipulator = new manipulation();
	static JTextField first;
	static JTextField sec;
	static JTextField third;
	public static String AlbumName = Album.album_name;
	public static JFrame add_photo() {
		JFrame add_photo = new JFrame("Add Photo");
		JLabel one = new JLabel("Enter name of Photo:");
		JLabel two = new JLabel("Enter Caption of Photo");
		first = new JTextField(25);
		sec = new JTextField(25);
		JButton button = new JButton("Add");
		button.addActionListener(new add_photo_listener(add_photo));
		// top panel
		JPanel titlePanel = new Borders("Add", "Add a Photo");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(one);
		inputPanel.add(first);
		inputPanel.add(two);
		inputPanel.add(sec);
		inputPanel.add(button);
		
		add_photo.setLayout(new BorderLayout());
		add_photo.add(titlePanel, BorderLayout.NORTH);
		add_photo.add(inputPanel, BorderLayout.CENTER);
		return add_photo;
	}
	
	static class add_photo_listener implements ActionListener {
    	private JFrame frame;
    	
		public add_photo_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			
			manipulator.addPhoto(User.user, first.getText(), sec.getText(), AlbumName);
			Album.update_album_list();
			manipulator.write(null);
			this.frame.dispose();
		}
    }
	
	public static JFrame delete_photo() {
		JFrame delete_photo = new JFrame("Delete Photo");
		JLabel one = new JLabel("Enter name of Photo:");
		first = new JTextField(25);
		JButton button = new JButton("Delete");
		button.addActionListener(new delete_photo_listener(delete_photo));
		// top panel
		JPanel titlePanel = new Borders("Delete", "Delete a Photo");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(one);
		inputPanel.add(first);
		inputPanel.add(button);
		
		delete_photo.setLayout(new BorderLayout());
		delete_photo.add(titlePanel, BorderLayout.NORTH);
		delete_photo.add(inputPanel, BorderLayout.CENTER);
		return delete_photo;
	}
	
	static class delete_photo_listener implements ActionListener {
    	private JFrame frame;
    	
		public delete_photo_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			
			manipulator.removePhoto(User.user, first.getText(), AlbumName);
			Album.update_album_list();
			manipulator.write(null);
			this.frame.dispose();
		}
    }
	
	public static JFrame recap() {
		JFrame recap = new JFrame("Recaption");
		JLabel one = new JLabel("Enter photo name:");
		first = new JTextField(25);
		JLabel two = new JLabel("Enter new Caption:");
		sec = new JTextField(25);
		JButton button = new JButton("Recaption");
		button.addActionListener(new recap_listener(recap));
		// top panel
		JPanel titlePanel = new Borders("Recap", "Recaption a Photo");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(one);
		inputPanel.add(first);
		inputPanel.add(button);
		
		recap.setLayout(new BorderLayout());
		recap.add(titlePanel, BorderLayout.NORTH);
		recap.add(inputPanel, BorderLayout.CENTER);
		return recap;
	}
	
	static class recap_listener implements ActionListener {
    	private JFrame frame;
    	
		public recap_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			manipulator.addPhoto(User.user, first.getText(), sec.getText(), User.album.get_album());
			manipulator.write(null);
			this.frame.dispose();
		}
    }
	
	public static JFrame display() {
		JFrame display = new JFrame("Display a Photo");
		
		// ++++++++++++++++++++
		//
		// ++++++++++++++++++++
		
		return display;
	}
	
	public static JFrame add_tag() {
		JFrame add_tag = new JFrame("Add a Tag");
		JLabel one = new JLabel("Enter the file Name:");
		JLabel two = new JLabel("Enter the new Tag Type:");
		JLabel three = new JLabel("Enter the new Tag Value:");
		first = new JTextField(25);
		sec = new JTextField(25);
		third = new JTextField(25);
		JButton button = new JButton("Add");
		button.addActionListener(new add_listener(add_tag));
		// top panel
		JPanel titlePanel = new Borders("Add", "Add a Tag");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(one);
		inputPanel.add(first);
		inputPanel.add(two);
		inputPanel.add(sec);
		inputPanel.add(three);
		inputPanel.add(third);
		inputPanel.add(button);
		
		add_tag.setLayout(new BorderLayout());
		add_tag.add(titlePanel, BorderLayout.NORTH);
		add_tag.add(inputPanel, BorderLayout.CENTER);
		return add_tag;
	}
	
    static class add_listener implements ActionListener {
    	private JFrame frame;
    	
		public add_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			
			if (manipulator.addTag(User.user , first.getText(), sec.getText(), third.getText())) {
				JOptionPane.showMessageDialog(null, "Added tag <" + first.getText() + ">", "Add Tag", JOptionPane.WARNING_MESSAGE);
				manipulator.write(null);
			} else {
				JOptionPane.showMessageDialog(null, "Fail to add tag <" + first.getText() + ">", "Fail to add Tag", JOptionPane.WARNING_MESSAGE);
			}
			this.frame.dispose();
		}
    }
	
	public static JFrame delete_tag() {
		JFrame delete_tag = new JFrame("Delete a Tag");
		JLabel one = new JLabel("Enter the file Name:");
		JLabel two = new JLabel("Enter existing Tag Type:");
		JLabel three = new JLabel("Enter existing Tag Value:");
		first = new JTextField(25);
		sec = new JTextField(25);
		third = new JTextField(25);
		JButton button = new JButton("Delete");
		button.addActionListener(new delete_tag_listener(delete_tag));
		// top panel
		JPanel titlePanel = new Borders("Delete", "Delete a Tag");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(one);
		inputPanel.add(first);
		inputPanel.add(two);
		inputPanel.add(sec);
		inputPanel.add(three);
		inputPanel.add(third);
		inputPanel.add(button);
		
		delete_tag.setLayout(new BorderLayout());
		delete_tag.add(titlePanel, BorderLayout.NORTH);
		delete_tag.add(inputPanel, BorderLayout.CENTER);
		return delete_tag;
	}
	
    static class delete_tag_listener implements ActionListener {
    	private JFrame frame;
    	
		public delete_tag_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			if (manipulator.removeTag(User.user, first.getText(), sec.getText(), third.getText())) {
				JOptionPane.showMessageDialog(null, "Deleted Tag <" + first.getText() + ">", "Delete Tag", JOptionPane.WARNING_MESSAGE);
				manipulator.write(null);
			} else {
				JOptionPane.showMessageDialog(null, "Fail to deleted Tag <" + first.getText() + ">", "Delete Tag", JOptionPane.WARNING_MESSAGE);
			}
			this.frame.dispose();
		}
    }
	
	public static JFrame move() {
		JFrame move = new JFrame("Move a Photo");
		JLabel one = new JLabel("Enter Photo Name:");
		JLabel two = new JLabel("Enter current Album name:");
		JLabel three = new JLabel("Enter new Album name:");
		first = new JTextField(25);
		sec = new JTextField(25);
		third = new JTextField(25);
		JButton button = new JButton("Move");
		button.addActionListener(new move_listener(move));
		// top panel
		JPanel titlePanel = new Borders("Move", "Move a Photo");
		// center panel
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("-"));
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(one);
		inputPanel.add(first);
		inputPanel.add(two);
		inputPanel.add(sec);
		inputPanel.add(three);
		inputPanel.add(third);
		inputPanel.add(button);
		
		move.setLayout(new BorderLayout());
		move.add(titlePanel, BorderLayout.NORTH);
		move.add(inputPanel, BorderLayout.CENTER);
		return move;
	}
	
    static class move_listener implements ActionListener {
    	private JFrame frame;
    	
		public move_listener(JFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			manipulator.movePhoto(User.user, first.getText(), sec.getText(), third.getText());
			manipulator.write(null);
			this.frame.dispose();
		}
    }
}
