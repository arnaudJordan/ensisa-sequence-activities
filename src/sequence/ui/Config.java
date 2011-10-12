package sequence.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Config implements Serializable{
	private final String FILENAME="config.ser";
	private File lastOpenedDirectory;

	public File getLastOpenedDirectory() {
		return lastOpenedDirectory;
	}

	public void setLastOpenedDirectory(File lastOpenedDirectory) {
		this.lastOpenedDirectory = lastOpenedDirectory;
	}
	
	public void serialize() throws IOException
	{
		FileOutputStream configFile = new FileOutputStream(FILENAME);
    	ObjectOutputStream oos = new ObjectOutputStream(configFile);
    	oos.writeObject(MainWindow.getConfig());
    	oos.flush();
    	oos.close();
	}
	public Config deserialize() throws IOException, ClassNotFoundException
	{
		FileInputStream configFile = new FileInputStream(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(configFile);
		return ((Config) ois.readObject());
	}
}
