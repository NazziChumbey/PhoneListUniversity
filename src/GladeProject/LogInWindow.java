package GladeProject;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class LogInWindow {
	private String password = "admin";
	
	private Window logInWindow;
	private Entry entryAdminPassword;
	private Label labelAdminPassword;
	private Button buttonLogIntoAdmin;
	
	
	private OpenWindow openWindow = new OpenWindow();

	public void openWindow() {
		openWindow.open();

		logInWindow = (Window) openWindow.builder.getObject("LogInAdmin");

		entryAdminPassword = (Entry) openWindow.builder.getObject("entryAdminPassword");
		labelAdminPassword = (Label) openWindow.builder.getObject("labelAdminPassword");

		buttonLogIntoAdmin = (Button) openWindow.builder.getObject("buttonLogIntoAdmin");
		buttonLogIntoAdmin.connect(new Clicked() {
			public void onClicked(Button arg0) {
				if (password.equals(entryAdminPassword.getText())) {
					AdminAdditionWindow adminAdd = new AdminAdditionWindow();
					adminAdd.openWindow();
					logInWindow.destroy();
				} else {
					labelAdminPassword
							.setLabel("\n    Невірний пароль !    \n");
				}
			}
		});
		logInWindow.showAll();

	}
}