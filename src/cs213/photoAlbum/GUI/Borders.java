package cs213.photoAlbum.GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Hua Yang
 */
public class Borders extends JPanel {

	private static final long serialVersionUID = -3157561434124319466L;

	public Borders(String title, String text) {
	    super(new GridLayout(1, 1));
	    setBackground(Color.white);
	    setBorder(BorderFactory.createTitledBorder(title));
	    JLabel jlabel = new JLabel(text);
	    jlabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	    add(jlabel);
	}
}
