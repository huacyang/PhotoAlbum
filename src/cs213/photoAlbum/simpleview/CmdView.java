package cs213.photoAlbum.simpleview;

import java.util.LinkedList;

import cs213.photoAlbum.control.manipulation;
import cs213.photoAlbum.model.user;
import cs213.photoAlbum.util.ultility;

/**
 * @author Hua Yang
 * @author Alexio Mota
 */
public class CmdView {
	static manipulation manipulator = new manipulation();
	ultility ulti = new ultility();
	
	/** Interprets the command line arguments */
	public static void main(String[] args) {
		// reads the default save file
		manipulator.read(null);
		
		if (args[0].equalsIgnoreCase("listusers") && args.length == 1) {
			LinkedList<user> list = new LinkedList<user>();
			list = manipulator.listUsers();
			if (list == null)
				System.out.println("no users exist.");
			else
				for(int index = 0; index < list.size(); index++)
					System.out.println("<" + list.get(index).get_id() + ">");
		}
		else if (args[0].equalsIgnoreCase("adduser") && args.length == 3) {
			if (manipulator.addUser(args[2], args[1]) == true) {
				System.out.println("created user <" + args[1] + "> with name <" + args[2] + ">");
				manipulator.write(args[2]);
			} else {
				System.out.println("user <" + args[1] + "> already exists with name <" + args[2] + ">");
			}
		}
		else if (args[0].equalsIgnoreCase("deleteuser") && args.length == 2) {
			if (manipulator.deleteUser(args[1]) == true) {
				System.out.println("deleted user <" + args[1] + ">");
				manipulator.write(args[1]);
			} else {
				System.out.println("user <" + args[1] + "> does not exist");
			}
		}
		else if (args[0].equalsIgnoreCase("login") && args.length == 2) {
			user login_user = manipulator.login(args[1]);
			if (login_user != null) {
				System.out.println("logged in as user <" + args[1] + ">");
				InterView.interactive(login_user);
				manipulator.write(login_user.get_user());
			} else {
				System.out.println("user <" + args[1] + "> does not exist");
			}
		}
		else {
			System.out.println("wrong format, try again");
		}
	}
}
