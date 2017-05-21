package GladeProject;

import org.gnome.gtk.Button;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainWindow extends DataBaseConnect {
	private static Window mainWindow;
	private static ListStore listNumberPhone;
	private static DataColumnString[] columns = new DataColumnString[6];
	private static TreeView treeview;

	private OpenWindow openWindow = new OpenWindow();

	public void openWindow() {
		openWindow.open();

		mainWindow = (Window) openWindow.builder.getObject("MainWindow");

		listNumberPhone = (ListStore) openWindow.builder.getObject("listNumberPhone");
		treeview = (TreeView) openWindow.builder.getObject("treeview");

		MainWindow.init();
		MainWindow.ReadDB();

		Button buttonBackToMenu = (Button) openWindow.builder
				.getObject("buttonBackToMenu");
		buttonBackToMenu.connect(new Clicked() {
			public void onClicked(Button arg0) {
				MenuWindow menuWindow = new MenuWindow();
				menuWindow.openWindow();
				mainWindow.destroy();
			}
		});

		Button buttonExitMainWindow = (Button) openWindow.builder
				.getObject("buttonExitMainWindow");
		buttonExitMainWindow.connect(new Clicked() {
			public void onClicked(Button arg0) {
				mainWindow.destroy();
			}
		});

		mainWindow.showAll();
	}

	static void init() {
		for (int i = 0; i < 6; i++) {
			columns[i] = new DataColumnString();
		}
		listNumberPhone = new ListStore(columns);
		treeview.setModel(listNumberPhone);
	}

	static void fillTreeStore() {
		TreeIter row = listNumberPhone.appendRow();
		for (int i = 0; i < 6; i++) {
			listNumberPhone.setValue(row, columns[i], count[i]);
		}
	}

	static void ReadDB() {
		try {
			String selectTableSQL = "SELECT * from tablePhoneList";
			Connection con = DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);

			for (;;) {
				rs.next();
				for (int i = 0; i < 6; i++) {
					count[i] = rs.getString(i + 1);
				}
				MainWindow.fillTreeStore();
			}

		} catch (Exception e) {
		}
	}

}