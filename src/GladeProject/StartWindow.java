package GladeProject;

import org.gnome.gtk.Button;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

public class StartWindow {
	private OpenWindow openWindow = new OpenWindow();
	private Window startWindow;
	private Button buttonGoMenu;

	public void openWindow() {
		openWindow.open();
		startWindow = (Window) openWindow.builder.getObject("StartWindow");
		buttonGoMenu = (Button) openWindow.builder.getObject("buttonGoMenu");
		buttonGoMenu.connect(new Clicked() {
			public void onClicked(Button arg0) {
				MenuWindow menuWindow = new MenuWindow();
				menuWindow.openWindow();
				startWindow.destroy();
			}
		});
		startWindow.showAll();
	}
}
