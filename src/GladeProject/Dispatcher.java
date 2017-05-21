package GladeProject;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.gnome.gtk.Gtk;

public class Dispatcher {

	public static void main(String[] args) throws FileNotFoundException,
			ParseException {

		Gtk.init(args);
		StartWindow start = new StartWindow();
		start.openWindow();

		Gtk.main();
	}
}
