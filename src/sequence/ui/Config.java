package sequence.ui;

import java.io.File;
import java.io.Serializable;

public class Config implements Serializable{
	private File lastOpenedDirectory;

	public File getLastOpenedDirectory() {
		return lastOpenedDirectory;
	}

	public void setLastOpenedDirectory(File lastOpenedDirectory) {
		this.lastOpenedDirectory = lastOpenedDirectory;
	}
}
