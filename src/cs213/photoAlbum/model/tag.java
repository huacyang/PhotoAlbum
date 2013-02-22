package cs213.photoAlbum.model;

/**
 * @author Hua Yang
 */
public class tag {
	
	private String location;
	private String tag_type;
	private String tag_value;
	
	public String get_location() {
		return location;
	}
	public String get_type() {
		return tag_type;
	}
	public String get_value() {
		return tag_value;
	}
	public void set_location(String loc) {
		location = loc;
	}
	public void set_type(String ty) {
		tag_type = ty;
	}
	public void set_value(String va) {
		tag_value = va;
	}
}
