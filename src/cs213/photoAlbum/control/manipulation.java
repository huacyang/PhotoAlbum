package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.LinkedList;

import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.backend;
import cs213.photoAlbum.model.photo;
import cs213.photoAlbum.model.tag;
import cs213.photoAlbum.model.user;

/**
 * @author Alexio Mota
 * @author Hua Yang
 */
public class manipulation{
	static backend model = new backend();
	
	/** Attempts to add the photo object into the album object
	 * @param fileName
	 * @param caption
	 * @param albumName
	 * @return true if successfully added the photo, false otherwise
	 */
	public boolean addPhoto(user login_user, String fileName, String caption, String albumName) {
		
		album album = login_user.getAlbum(albumName);
		if (album != null) {
			return album.add(fileName, caption);
		} else {
			return false;
		}
	}
	
	/** Attempts to remove the photo object from the album object
	 * @param fileName
	 * @param albumName
	 * @return true if successfully removed the photo, false otherwise
	 */
	public photo removePhoto(user login_user, String fileName, String albumName) {
		
		return login_user.getAlbum(albumName).delete(fileName);
	}
	
	/** Creates an album object with the given name
	 * @param name
	 * @return the album object if successfully created, null otherwise
	 */
	public boolean createAlbum(user login_user ,String name) {
		if (login_user.addAlbum(name)) {
			System.out.println("created album for user <" + login_user.get_id() + ">:");
			System.out.println(name);
			return true;
		}
		System.out.println("album exists for user <" + login_user.get_id() + ">: ");
		System.out.println(name);
		return false;
	}
	
	/** Deletes an album object with the given name
	 * @param name
	 * @return the album object if successfully deleted, null otherwise
	 */
	public boolean deleteAlbum(user login_user, String name) {
		
		if (login_user.deleteAlbum(name)) {
			System.out.println("deleted album from user <" + login_user.get_id() + ">: ");
			System.out.println(name);
			return true;
		} else {
		System.out.println("album does not exist for user <" + login_user.get_id() + ">: ");
		System.out.println(name);
			return false;
		}
	}
	
	/** Prints out a list of existing album objects */
	public void listAlbums(user login_user) {
		
		login_user.listAlbums();
	}
	
	/** Prints out a list of existing photo objects */
	public void listPhotos(user login_user, String album) {
		
		login_user.getAlbum(album).listphotos();
	}
	
	/** Prints out the information of the targeted photo */
	public void listPhotoInfo(user login_user, String filename) {
		boolean once = false;
		for(int index = 0; index < login_user.getAlbums().size(); index++){
			for(int photoIndex = 0; photoIndex < login_user.getAlbums().get(index).get_photoList().size(); photoIndex++){

				if(login_user.getAlbums().get(index).get_photoList().get(photoIndex).get_photo().equalsIgnoreCase(filename)){
					if (!once) {
						System.out.println("Photo Name :" + filename);
						once = true;
					}
					System.out.println("Album: " + login_user.getAlbums().get(index).get_album());
					System.out.println("Date: " + login_user.getAlbums().get(index).get_photoList().get(photoIndex).get_Time().getTime().toString());
					
					LinkedList<tag> tag = login_user.getAlbums().get(index).get_photoList().get(photoIndex).getTag();
					if (tag != null) {
						for (int i = 0; i < tag.size(); i++) {
							System.out.println("Tag: " + tag.get(i).get_type() + ": " + tag.get(i).get_value());
						}
					}
				}
			}
		}
	}
	
	/**Moves the specified photo to a new album
	 * @param login_user
	 * @param photoName
	 * @param oldAlbum
	 * @param newAlbum
	 */
	public void movePhoto(user login_user, String photoName, String oldAlbum, String newAlbum){
		
		login_user.movePhoto(photoName, oldAlbum, newAlbum);
	}
	
	
	/** Prints out a linked list of photos between the given start and end date 
	 * sorted in chronological order by date.
	 * @param startDate
	 * @param endDate
	 */
	public void getPhotosByDate(user login_user, Calendar startDate, Calendar endDate) {
		login_user.getPhotosByDate(startDate, endDate);
	}
	
	/** Prints out a linked list of photos by the given tag
	 * @param tag
	 * @return a linked list of photo object
	 */
	public void getPhotosByTag(user login_user, String tagtype, String tagValue) {
		login_user.getPhotosByTag(tagtype, tagValue);
	}
	
	/** Attempts to add the given tag to the targeted file
	 * @param fileName
	 * @param tagType
	 * @param tagValue
	 * @return true if successfully added the tag, false otherwise
	 */
	public boolean addTag(user login_user, String fileName, String tagType, String tagValue) {
		LinkedList<album> album_list = login_user.getAlbums();
		for (int i = 0; i < album_list.size(); i++) {
			LinkedList<photo> photo_list = album_list.get(i).get_photoList();
			for (int n = 0; n < photo_list.size(); n++) {
				if (photo_list.get(n).get_photo().equalsIgnoreCase(fileName)) {
					if (photo_list.get(n).add_tag(tagValue, tagType)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/** Attempts to remove the targeted tag from the targeted file
	 * @param fileName
	 * @param tagType
	 * @param tagValue
	 * @return true if successfully removed the tag, false otherwise
	 */
	public boolean removeTag(user login_user, String fileName, String tagType, String tagValue) {
		LinkedList<album> album_list = login_user.getAlbums();
		for (int i = 0; i < album_list.size(); i++) {
			LinkedList<photo> photo_list = album_list.get(i).get_photoList();
			for (int n = 0; n < photo_list.size(); n++) {
				if (photo_list.get(n).get_photo().equalsIgnoreCase(fileName)) {
					if (photo_list.get(n).delete_tag(tagValue, tagType)) {
						return true;
					}
				}
			}
		}
		System.out.println("Tag does not exist!");
		return false;
	}
	
	/**Adds the user by the given user's ID
	 * @param user
	 * @return true if the user was added, false otherwise
	 */
	public boolean addUser(String user, String ID){
		return model.addUser(user, ID);
	}
	
	/** Deletes the user by the given user's ID
	 * @param user
	 * @return false if failed to delete because user doesn't exist, true otherwise
	 */
	public boolean deleteUser(String user){
		return model.deleteUser(user);
	}
	
	/** Lists all the existing users' IDs
	 * @return a linked list of users if users exist, null otherwise
	 */
	public LinkedList<user> listUsers(){
		return model.listUser();
	}
	
	/** Attempts to log into the given user account stored in the database
	 * @param ID
	 * @return the user object if the given ID exists, null otherwise
	 */
	public user login(String ID) {
		LinkedList<user> users = model.get_users();
		if (users == null)
			return null;
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).get_id().equalsIgnoreCase(ID))
				return users.get(i);
		return null;
	}
	
	/** Reads the user's ID from storage to memory
	 * @param ID
	 * @return false if user's ID doesn't exist, true otherwise
	 */
	public boolean read(String ID) {
		return model.readUser(ID);
	}
	
	public album open_album(user user, String name) {
		return user.getAlbum(name);
	}
	
	public boolean rename_album(user user, String existing, String new_name) {
		return user.renameAlbum(existing, new_name);
	}
	
	/** Writes the user from memory to storage
	 * @param user
	 * @return false if user already exist, true otherwise
	 */
	public boolean write(String user) {
		return model.writeUser(user);
	}
}
