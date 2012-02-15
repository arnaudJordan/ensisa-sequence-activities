package sequence.ui.test;

import java.awt.EventQueue;

import sequence.ui.window.MainWindow;

public class UiTest {

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow("Sequence activities");
			}
		});
	}
}
