package GladeProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class AdminAdditionWindow extends DataBaseConnect {

	private Window adminAddWindow;

	private Button buttonClearEntry;
	private Button buttonBackLogInWindow;
	private Button buttonAddFile;

	private Label labelDoneAdd;

	private Entry entrySubscriber;
	private Entry entryKafedra;
	private Entry entryLocation;
	private Entry entryType;
	private Entry entryNumber; 
	private Entry entryInformation;

	private OpenWindow openWindow = new OpenWindow();

	public void openWindow() {
		openWindow.open();

		adminAddWindow = (Window) openWindow.builder
				.getObject("AdminAdditionWindow");

		entrySubscriber = (Entry) openWindow.builder
				.getObject("entrySubscriber");
		entryKafedra = (Entry) openWindow.builder.getObject("entryKafedra");
		entryLocation = (Entry) openWindow.builder.getObject("entryLocation");
		entryType = (Entry) openWindow.builder.getObject("entryType");
		entryNumber = (Entry) openWindow.builder.getObject("entryNumber");
		entryInformation = (Entry) openWindow.builder
				.getObject("entryInformation");

		labelDoneAdd = (Label) openWindow.builder.getObject("labelDoneAdd");

		buttonClearEntry = (Button) openWindow.builder
				.getObject("buttonClearEntry");
		buttonClearEntry.connect(new Clicked() {
			public void onClicked(Button arg0) {
				entrySubscriber.setText("");
				entryKafedra.setText("");
				entryLocation.setText("");
				entryType.setText("");
				entryNumber.setText("");
				entryInformation.setText("");
			}
		});

		buttonBackLogInWindow = (Button) openWindow.builder
				.getObject("buttonBackLogInWindow");
		buttonBackLogInWindow.connect(new Clicked() {
			public void onClicked(Button arg0) {
				MenuWindow menuWindow = new MenuWindow();
				menuWindow.openWindow();
				adminAddWindow.destroy();
			}
		});
		buttonAddFile = (Button) openWindow.builder.getObject("buttonAddFile");
		buttonAddFile.connect(new Clicked() {
			public void onClicked(Button arg0) {
				try {
					Connection con = DriverManager.getConnection(url, user,
							password);
					Statement statement = con.createStatement();
					String query = "INSERT INTO tablePhoneList (Subscriber, Kafedra, Location, Type, Number, Information) \n"
							+ " VALUES ('"
							+ entrySubscriber.getText()
							+ "','"
							+ entryKafedra.getText()
							+ "', '"
							+ entryLocation.getText()
							+ "', '"
							+ entryType.getText()
							+ "', '"
							+ entryNumber.getText()
							+ "', '"
							+ entryInformation.getText() + "');";
					statement.executeUpdate(query);
					labelDoneAdd.setLabel("        Додано!        ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		adminAddWindow.showAll();
	}
}
