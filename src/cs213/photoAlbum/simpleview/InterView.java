package cs213.photoAlbum.simpleview;

import java.io.*;
import java.util.Calendar;
import java.util.StringTokenizer;

import cs213.photoAlbum.model.*;
import cs213.photoAlbum.control.manipulation;

/**
 * @author Hua Yang
 * @author Alexio Mota
 */
public class InterView {
	
	static manipulation manipulator = new manipulation();
	static backend backend = new backend();
	static String token1;
	static String token2;
	static String token3;
	public static void interactive(user login_user){
		
		while (true) {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String command = " ";
			try{
				command = br.readLine();
			}
			catch(IOException ioe){
				System.exit(1);
			}
			
			StringTokenizer tokens = new StringTokenizer(command, "\"");
			//String[] tokens = command.split("");
			String option = tokens.nextToken();
			
			if(option.equals("createAlbum")){
				token1 = tokens.nextToken();
				if (token1 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					manipulator.createAlbum(login_user, token1);
				}
			}
			else if(option.equals("deleteAlbum")){
				token1 = tokens.nextToken();
				if (token1 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					manipulator.deleteAlbum(login_user, token1);
				}
			}
			else if(option.equals("listAlbums")){
				manipulator.listAlbums(login_user);
			}
			else if(option.equals("listPhotos")){
				token1 = tokens.nextToken();
				if (token1 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					manipulator.listPhotos(login_user, token1);
				}
			}
			else if(option.equals("addPhoto")){
				while (tokens.countTokens() == 3) {
					token1 = tokens.nextToken();
					token2 = tokens.nextToken();
					token3 = tokens.nextToken();
				}
				if (token1 == null || token2 == null || token3 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					manipulator.addPhoto(login_user, token1, token2, token3);
				}
			}
			else if(option.equals("movePhoto")){
				while (tokens.countTokens() == 3) {
					token1 = tokens.nextToken();
					token2 = tokens.nextToken();
					token3 = tokens.nextToken();
				}
				if (token1 == null || token2 == null || token3 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					manipulator.movePhoto(login_user, token1, token2, token3);
				}
			}
			else if(option.equals("removePhoto")){
				while (tokens.countTokens() == 2) {
					token1 = tokens.nextToken();
					token2 = tokens.nextToken();
				}
				if (token1 == null || token2 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					if (manipulator.removePhoto(login_user,token1, token2) == null) {
						System.out.println("Photo does not exist!");
					}
				}
			}
			else if(option.equals("addTag")){
				while (tokens.countTokens() == 3) {
					token1 = tokens.nextToken();
					token2 = tokens.nextToken();
					token3 = tokens.nextToken();
				}
				if (token1 == null || token2 == null || token3 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					if (manipulator.addTag(login_user,token1, token2, token3)) {
						System.out.println("Added tag:");
						System.out.println("<" + token1 + "> <" + token2 + ">:<" + token3 +">");
					} else {
						System.out.println("Tag <" + token2 + ">:<" + token3 +">" + " already exist for photo <" + token1 + ">");
					}
				}
			}
			else if(option.equals("deleteTag")){
				while (tokens.countTokens() == 3) {
					token1 = tokens.nextToken();
					token2 = tokens.nextToken();
					token3 = tokens.nextToken();
				}
				if (token1 == null || token2 == null || token3 == null) {
					System.out.println("Invalid number of inputs");
				} else {
					manipulator.removeTag(login_user,token1, token2, token3);
				}
			}
			else if(option.equals("listPhotoInfo")){
				manipulator.listPhotoInfo(login_user,tokens.nextToken());
				
			}
			else if(option.equals("getPhotosByDate")){
				if (tokens.countTokens() != 2) {
					System.out.println("Incorrect number of inputs");
				} else {
					token1 = tokens.nextToken();
					if(token1.length() <= 1){
						System.out.println("Invalid date");
					} else {
						token2 = tokens.nextToken();
						if(token2.length() <= 1){
							System.out.println("Invalid date");
						} else {
							StringTokenizer date1 = new StringTokenizer(token1, "/-:");
							StringTokenizer date2 = new StringTokenizer(token2, "/-:");
							
							String month = date1.nextToken();
							String day = date1.nextToken();
							String year = date1.nextToken();
							Calendar start = Calendar.getInstance();
							start.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(date1.nextToken()),
									Integer.parseInt(date1.nextToken()), Integer.parseInt(date1.nextToken()));
							
							month = date2.nextToken();
							day = date2.nextToken();
							year = date2.nextToken();
							Calendar end = Calendar.getInstance();
							end.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(date2.nextToken()),
									Integer.parseInt(date2.nextToken()), Integer.parseInt(date2.nextToken()));
							
							manipulator.getPhotosByDate(login_user,start, end);
						}
					}
				}
			}
			else if(option.equals("getPhotosByTag")){
				
				if (!tokens.hasMoreTokens()) {
					System.out.println("Invalid input");
				}else{
					token1 = tokens.nextToken();
					if(!tokens.hasMoreTokens()){
						manipulator.getPhotosByTag(login_user, "", token1);
					}else{
						token2 = tokens.nextToken();
						manipulator.getPhotosByTag(login_user, token1, token2);
					}
				}
			}
			else if (option.equals("logout")) {
				backend.writeUser(null);
				break;
			}
			else {
				System.out.println("incorrect format, try again");
			}
			
			token1 = null;
			token2 = null;
			token3 = null;
		}
	}
}
	

