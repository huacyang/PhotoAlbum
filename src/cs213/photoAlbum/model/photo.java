package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Calendar;

/**
 * @author Hua Yang
 * @author Alexio Mota
 */
public class photo implements Serializable {
	
	private static final long serialVersionUID = 1049250008896963561L;
	/** represents an unique file name per user */
	private String file_name;
	/** represents a photo caption */
	private String photo_caption;
	/** represents the date and time the photo was taken */
	private Calendar calendar;
	/** represents a list of different tags */
	LinkedList<tag> tag;
	
	/** @return the name of the photo file in a string */
	public String get_photo() {	
		return file_name;
	}
	
	/** @return the caption of the photo file in a string */
	public String get_caption() {	
		return photo_caption;	
	}
	
	/** @return the linked list of the tag that exist within this photo */
	public LinkedList<tag> getTag() {
		return tag;
	}
	
	/** @return the date and time the photo was taken*/
	public Calendar get_Time(){
		return calendar;
	}
	
	/** Sets the time for which the photo was created */
	public void set_time() {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND,0);
	}
	
	/** Attempts to add the new tag with the given string value and type
	 * @param tagValue
	 * @param tagType
	 * @return false if tag already exist, true otherwise
	 */
	public boolean add_tag(String tagValue, String tagType) {
		if (tag == null) {
			tag = new LinkedList<tag>();
		}
		else {
			for (int i = 0; i < tag.size(); i++)
				if (tag.get(i).get_value().equalsIgnoreCase(tagValue)
						&& tag.get(i).get_type().equalsIgnoreCase(tagType))
					return false;
		}
		tag newtag = new tag();
		newtag.set_value(tagValue);
		newtag.set_type(tagType);
		tag.add(newtag);
		return true;
	}
	
	public boolean delete_tag(String tagValue, String tagType) {
		for (int i = 0; i < tag.size(); i++)
			if (tag.get(i).get_value().equalsIgnoreCase(tagValue)
					&& tag.get(i).get_type().equalsIgnoreCase(tagType)) {
				tag.remove(i);
				return true;
			}
		return false;
	}
	
	/** Changes the existing caption with the new string caption
	 * @param caption
	 */
	public void set_caption(String caption) {	photo_caption = caption;	}
	
	/** Sets the name of the picture file with the given name
	 * @param file
	 */
	public void set_file(String file) {	file_name = file;	}
}
