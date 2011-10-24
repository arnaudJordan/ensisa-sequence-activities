package sequence.ui.test;

import java.awt.EventQueue;
import sequence.ui.MainWindow;

public class UiTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MainWindow("Sequence activities");
			}
		});
	}
}
