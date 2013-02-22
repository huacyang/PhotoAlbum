package cs213.photoAlbum.model;
import java.io.*;
import java.util.LinkedList;

/**
 * @author Alexio Mota
 */
public class backend {

	private LinkedList<user> User_List;
	
	/**Adds the user by the given user's ID
	 * @param user
	 * @return false if user with given name and ID already existed, true otherwise
	 */
	public boolean addUser(String user, String ID){
		
		if(User_List == null)
			User_List = new LinkedList<user>();
			
		for (int index = 0; index < User_List.size(); index++)
			if(User_List.get(index).get_user().equalsIgnoreCase(user)
					&& User_List.get(index).get_id().equalsIgnoreCase(ID))
					return false;
		
		user newOne = new user();
		newOne.set_user(user);
		newOne.set_id(ID);
		
		User_List.add(newOne);
		return true;
	}
	
	/** Deletes the user by the given user's ID
	 * @param user
	 * @return false if failed to delete because user doesn't exist, true otherwise
	 */
	public boolean deleteUser(String ID) {
		for (int index = 0; index < User_List.size(); index++){
			if(User_List.get(index).get_id().equalsIgnoreCase(ID)){
					User_List.remove(index);
					return true;
			}
		}
		return false;
	}
	
	/** Lists all the existing users' IDs
	 * @return false if there are no user IDs, true otherwise
	 */
	public LinkedList<user> listUser() {
		
		if(User_List == null || User_List.size() == 0)
			return null;
		
		return User_List;
	}
	
	/** Reads the user's ID from storage to memory
	 * @param user
	 * @return false if user's ID doesn't exist, true otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean readUser(String ID) {
		try{
			FileInputStream filestream = new FileInputStream("data.txt");
			ObjectInputStream objectRead = new ObjectInputStream(filestream);
			User_List = (LinkedList<user>) objectRead.readObject();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/** Writes the user from memory to storage
	 * @param user
	 * @return false if user already exist, true otherwise
	 */
	public boolean writeUser(String user) {
		 try{
			 FileOutputStream filestream = new FileOutputStream("data.txt");
			 ObjectOutputStream objectS = new ObjectOutputStream(filestream);
			 objectS.writeObject(User_List);
			 objectS.close();
			 
			 /* // Create file 
			  FileWriter fstream = new FileWriter("data.txt");
			  BufferedWriter saveFile = new BufferedWriter(fstream);
			 
			  for(int userIndex = 0; userIndex < User_List.size(); userIndex++){
				  
				  String writeThis = "User_ID " + User_List.get(userIndex).get_id() + " User_Name " + User_List.get(userIndex).get_user();
				  saveFile.write(writeThis, 0, writeThis.length());
				  saveFile.newLine();
				  
				  for(int albumIndex = 0; albumIndex < User_List.get(userIndex).getAlbums().size() ; albumIndex++){
					  
					  writeThis = " Album # " + albumIndex+1 + " Album Name " + User_List.get(userIndex).getAlbums().get(albumIndex).get_album() +
							  		" Number of photos " + User_List.get(userIndex).getAlbums().get(albumIndex).get_num();
					  saveFile.write(writeThis, 0, writeThis.length());
					  saveFile.newLine();
					  
					  for(int photoIndex = 0; photoIndex < User_List.get(albumIndex).getAlbums().get(albumIndex).photolist.size() ; photoIndex++){
						  
						  writeThis = " Photo " +  User_List.get(userIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).get_photo() +
								  		" Caption: " +  User_List.get(userIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).get_caption() +
								  		" Time: " +  User_List.get(userIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).get_Time();
						  saveFile.write(writeThis, 0, writeThis.length());
						  saveFile.newLine();
						  for(int tagIndex = 0; tagIndex < User_List.get(albumIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).tag.size() ; tagIndex++){
							  
							  writeThis = " Tag " + User_List.get(userIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).tag.get(tagIndex).get_value() + 
									  " location " + User_List.get(userIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).tag.get(tagIndex).get_location()
									  + " tagtype " + User_List.get(userIndex).getAlbums().get(albumIndex).photolist.get(photoIndex).tag.get(tagIndex).get_type();
						  }
					  }
				  }
			  }
			  //Close the output stream
			  saveFile.close();*/
			  return true;
			  
		 }catch (Exception e){//Catch exception if any
			 System.err.println("Error: " + e.getMessage());
			 return false;
		 }  
	}
	
	/** Gets the list of users
	 * @return a linked list of users
	 */
	public LinkedList<user> get_users() {
		return User_List;
	}
	
	public user getOneUser(user login_user){
		
		for(int index = 0; index < User_List.size(); index++){
			
			if(User_List.get(index).get_user().equals(login_user.get_user())){
				return User_List.get(index);
			}
		}
		return null;
	}
}
