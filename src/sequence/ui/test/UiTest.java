package sequence.ui.test;

import java.awt.EventQueue;

import sequence.ui.window.MainWindow;

/**
 * The Class UiTest.
 */
public class UiTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow("Sequence activities");
			}
		});
	}
}
