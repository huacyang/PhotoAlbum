package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

import cs213.photoAlbum.GUI.User;

/**
 * @author Alexio Mota
 */
public class user implements Serializable{

	private static final long serialVersionUID = 6782778247531681547L;
	
	/** represents the user's unique login ID */
	private String user_id;
	/** represents the user's full name */
	private String user_name;
	/** represents a list of the user's album */
	private LinkedList<album> user_album;
	
	
	/** Attempts to add the album with the given name
	 * @param name
	 * @return false if album already exist, true otherwise
	 */
	public boolean addAlbum(String name) {
		
		if(user_album == null)
			user_album = new LinkedList<album>();
		
		int index = 0;
		while(index < user_album.size()){
			if(user_album.get(index).get_album().equalsIgnoreCase(name)) {
				return false;
			}
			index++;
		}
		album newOne = new album();
		newOne.set_album(name);
		newOne.init();
		
		user_album.add(newOne);
		return true;
	}
	
	/** Attempts to delete the album with the given name
	 * @param name
	 * @return false if album doesn't exist, true otherwise
	 */
	public boolean deleteAlbum(String name) {
		
		if (user_album != null) {
			int index = 0;
			while(index < user_album.size()){
					
				if(user_album.get(index).get_album().equalsIgnoreCase(name)){
						user_album.remove(index);
						return true;
				}
				index++;
			}
		}
		
		return false;
	}
	
	/** Attempts to rename the album with the given name
	 * @param name
	 * @return false if album doesn't exist, true otherwise
	 */
	public boolean renameAlbum(String albumName, String newName) {
		
		if(user_album == null)
			user_album = new LinkedList<album>();
			
		if(albumName.equalsIgnoreCase(newName))
			System.out.println("Same name, input a different name");
				
		int index = 0;
		while(index < user_album.size()){
				
			if(user_album.get(index).get_album().equalsIgnoreCase(albumName)){
				user_album.get(index).set_album(newName);
				System.out.println("The album, " + albumName + ", has been renamed to " + newName + " for the user:"  + user_id);
				return true;
			}
			index++;
		}
		
		System.out.println("The album, " + albumName + ", does not exist for the user: " + user_id);
		return false;
	}
	
	/** Outputs the requested Album
	 * @param album name
	 * @return the requested album or null if the album doesn't exist
	 */
	public album getAlbum(String name){
		
		int index = 0;
		while(index < user_album.size()){

			if(user_album.get(index).get_album().equalsIgnoreCase(name)){
				
				return user_album.get(index);
			}
			index++;
		}
		
		System.out.println("The album, " + name + ", does not exist for the user: " + user_id);
		return null;
	}
	
	/** List a user's current albums
	 * @return false if no albums exist, true otherwise
	 */
	public void listAlbums(){

		User.album_list_model.clear();
		if(user_album == null || user_album.size() == 0){
			System.out.println("No Albums exist for the user");
		} else {
		System.out.println("Albums for " + user_id + " :");
		for(int index = 0; index < user_album.size(); index++){
			
			System.out.println("Name : " + user_album.get(index).get_album() + " Number of Photos: " + 
						user_album.get(index).get_num() + " Start Date : " + "End Date :");
			User.album_list_model.addElement("<" + user_album.get(index).get_album() + ">");
			}
		}
	}
	
	/** @return user id */
	public String get_id() {
		return user_id;
	}
	
	/** @return user name */
	public String get_user() {
		return user_name;
	}
	
	public LinkedList<album> getAlbums(){
		return user_album;
	}
	/** Sets the user id with the given ID number
	 * @param ID
	 */
	public void set_id(String ID) {
		user_id = ID;
	}
	
	/** Sets the user name with the given name string
	 * @param user
	 */
	public void set_user(String user) {
		user_name = user;
	}
	
	public void movePhoto(String photoName, String oldAlbum, String newAlbum){
		
		photo photoToMove = getAlbum(oldAlbum).delete(photoName);
		getAlbum(newAlbum).add(photoToMove.get_photo(), photoToMove.get_caption());
	}
	
	public void getPhotosByDate(Calendar start, Calendar end){
		if (getAlbums() != null) {
			for(int index = 0; index < getAlbums().size(); index++){
				
				for(int photoIndex = 0; photoIndex < getAlbums().get(index).get_photoList().size(); photoIndex++){
					
					if(getAlbums().get(index).photolist.get(photoIndex).get_Time().getTime().after(start.getTime()) && getAlbums().get(index).photolist.get(photoIndex).get_Time().getTime().before(end.getTime())){
					
						System.out.println("Photo Name: " + getAlbums().get(index).photolist.get(photoIndex).get_photo()
								+ "\n Caption " + getAlbums().get(index).photolist.get(photoIndex).get_caption()
								+ "\n Album " + getAlbums().get(index).get_album()
								+ "\n Date " + getAlbums().get(index).photolist.get(photoIndex).get_Time().getTime().toString());
					}
				}
			}
		} else {
			System.out.println("No Photos exist between given dates!");
		}
	}
	
	public void getPhotosByTag(String tag_Type, String tag_Value){
		System.out.println("Photos for user, "+ user_name + " for tag type: " + tag_Type+ " and tag Value " + tag_Value);
		
		for(int index = 0; index < getAlbums().size(); index++){
			
			for(int photoIndex = 0; photoIndex < getAlbums().get(index).get_photoList().size(); photoIndex++){
				
				if (getAlbums().get(index).get_photoList().get(photoIndex).getTag() != null) {
					for(int tagIndex = 0; tagIndex < getAlbums().get(index).get_photoList().get(photoIndex).getTag().size(); tagIndex++){
							
						if(getAlbums().get(index).photolist.get(photoIndex).getTag().get(tagIndex).equals(tag_Type)
									|| getAlbums().get(index).photolist.get(photoIndex).getTag().get(tagIndex).equals(tag_Value)){
							
								System.out.println("Photo Name: " + getAlbums().get(index).photolist.get(photoIndex).get_photo()
										+ " Caption " + getAlbums().get(index).photolist.get(photoIndex).get_caption()
										+ " Album " + getAlbums().get(index).get_album()
										+ " Date " + getAlbums().get(index).photolist.get(photoIndex).get_Time().getTime().toString());
						}
					}
				}
			}
		}
	}
}
