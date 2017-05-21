package GladeProject;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.gnome.gtk.Builder;

public class OpenWindow {

	Builder builder = new Builder();

	public void open() {
		try {
			builder.addFromFile("src/one.glade");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {

		}
	}
}
