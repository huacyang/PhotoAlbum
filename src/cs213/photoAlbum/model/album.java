package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.LinkedList;

import cs213.photoAlbum.GUI.Album;

/**
 * @author Hua Yang
 */
public class album implements Serializable {

	private static final long serialVersionUID = 5070739206239902858L;
	/** represents an unique album name */
	private String album_name;
	/** represents the number of photos in the album */
	private int num_photo;
	/** represents a list of photos in the album*/
	LinkedList<photo> photolist;
	
	/** Adds a photo with the given string file name and string caption
	 * @param file
	 * @param caption
	 * @return false if the given file name already exist, true otherwise
	 */
	public boolean add(String file, String caption) {
		// checks if the photo file already exist in the list 
		if (check_photo(file, caption) == true) {
			return false;
		} else {
			photo photo = new photo();
			photo.set_file(file);
			photo.set_caption(caption);
			photo.set_time();
			photolist.add(photo);
			num_photo++;
		}
		return true;
	}
	
	/** Checks if the photo file already exists in the album
	 * @param file
	 * @return false if photo file doesn't exist, true otherwise
	 */
	public boolean check_photo(String file, String caption) {
		for (int i = 0; i < photolist.size(); i++)
			if (photolist.get(i).get_photo().equalsIgnoreCase(file)) {
				photolist.get(i).set_caption(caption);
				return true;
			}
		return false;
	}
	
	/** Attempts to delete a photo with the given string name
	 * @param file
	 * @return false if no such photo exist, true otherwise
	 */
	public photo delete(String file) {
		for (int i = 0; i < photolist.size(); i++) {
			if (photolist.get(i).get_photo().equalsIgnoreCase(file)) {
				return photolist.remove(i);
			}
		}
		return null;
	}
	
	public void init() {
		photolist = new LinkedList<photo>();
		num_photo = 0;
	}
	
	/** @return a string of the name of the album */
	public String get_album() {
		return album_name;
	}
	
	/** @return the number of existing photos in the album as an int */
	public int get_num() {
		return num_photo;
	}
	
	/** Sets the name of the album with the given string name
	 * @param name
	 */
	public void set_album(String name) {
		album_name = name;
	}
	
	public LinkedList<photo> get_photoList(){
		return photolist;
	}
	
	public void listphotos(){
		
		if(photolist.size() == 0){
			
			System.out.println("No photos in album.");
			Album.photo_list_model.clear();
		} else {
		
			System.out.println("Photos for album: "+ album_name);
			for(int index = 0; index < photolist.size(); index++){
				
				System.out.println("Photo Name: " + photolist.get(index).get_photo() + 
						"\nCaption: " + photolist.get(index).get_caption() +
						"\nTime: " + photolist.get(index).get_Time().getTime().toString());
				Album.photo_list_model.addElement(photolist.get(index).get_photo());
			}
		}
	}
}
