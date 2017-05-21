package GladeProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.gnome.gtk.Button;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class SearchWindow extends DataBaseConnect {
	private Entry entrySearch;
	private OpenWindow ow = new OpenWindow();
	private static ListStore listPhoneSearch;
	private static DataColumnString[] columns = new DataColumnString[6];
	private static TreeView treeviewSearch;
	private static String search;

	public void openWindow() {
		ow.open();
		final Window searchWindow = (Window) ow.builder
				.getObject("SearchWindow");
		listPhoneSearch = (ListStore) ow.builder.getObject("listPhoneSearch");
		treeviewSearch = (TreeView) ow.builder.getObject("treeviewSearch");
		entrySearch = (Entry) ow.builder.getObject("entrySearch");
		SearchWindow.init();

		Button buttonSearch = (Button) ow.builder.getObject("buttonSearch");
		buttonSearch.connect(new Clicked() {
			public void onClicked(Button arg0) {
				search = "%" + entrySearch.getText().trim() + "%";
				listPhoneSearch.clear();
				SearchWindow.ReadDB();
			}
		});
		Button buttonBackSearch = (Button) ow.builder
				.getObject("buttonBackSearch");
		buttonBackSearch.connect(new Clicked() {
			public void onClicked(Button arg0) {
				MenuWindow menuWindow = new MenuWindow();
				menuWindow.openWindow();
				searchWindow.destroy();
			}
		});

		searchWindow.showAll();
	}

	static void init() {
		for (int i = 0; i < 6; i++) {
			columns[i] = new DataColumnString();
		}
		listPhoneSearch = new ListStore(columns);
		treeviewSearch.setModel(listPhoneSearch);
	}

	static void fillTreeStore() {
		TreeIter row = listPhoneSearch.appendRow();
		for (int i = 0; i < 6; i++) {
			listPhoneSearch.setValue(row, columns[i], count[i]);
		}
	}

	static void ReadDB() {
		try {
			if (search.length() != 2) {
				String selectTableSQL = "Select * From tablePhoneList Where Subscriber Like '"
						+ search
						+ "' Or Kafedra Like '"
						+ search
						+ "' Or Location Like '"
						+ search
						+ "' Or Type Like '"
						+ search
						+ "' Or Number Like '"
						+ search
						+ "' Or Information Like '" + search + "'";

				Connection con = DriverManager.getConnection(url, user,
						password);
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(selectTableSQL);

				for (;;) {
					rs.next();
					for (int i = 0; i < 6; i++) {
						count[i] = rs.getString(i + 1);
					}
					SearchWindow.fillTreeStore();
				}
			}
		} catch (Exception e) {
		}

	}
}
