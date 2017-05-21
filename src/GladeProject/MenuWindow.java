package GladeProject;

import org.gnome.gtk.Button;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class MenuWindow {
	private OpenWindow openWindow = new OpenWindow();
	private Window menuWindow;
	private Button buttonGoEditAdmin;
	private Button buttonGoNumberList;
	private Button buttonGoSearch;

	public void openWindow() {
		openWindow.open();
		menuWindow = (Window) openWindow.builder.getObject("MenuWindow");

		buttonGoEditAdmin = (Button) openWindow.builder
				.getObject("buttonGoEditAdmin");
		buttonGoEditAdmin.connect(new Clicked() {
			public void onClicked(Button arg0) {
				LogInWindow liw = new LogInWindow();
				liw.openWindow();
				menuWindow.destroy();
			}
		});
		buttonGoNumberList = (Button) openWindow.builder
				.getObject("buttonGoNumberList");
		buttonGoNumberList.connect(new Clicked() {
			public void onClicked(Button arg0) {
				MainWindow mainWindow = new MainWindow();
				mainWindow.openWindow();
				menuWindow.destroy();
			}
		});
		buttonGoSearch = (Button) openWindow.builder
				.getObject("buttonGoSearch");
		buttonGoSearch.connect(new Clicked() {
			public void onClicked(Button arg0) {
				SearchWindow searchWindow = new SearchWindow();
				searchWindow.openWindow();
				menuWindow.destroy();
			}
		});

		menuWindow.showAll();
	}
}
