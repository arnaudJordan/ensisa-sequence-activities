package sequence.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Config implements Serializable{
	private static final long serialVersionUID = 1L;
	private final String FILENAME="config.ser";
	private File lastOpenedDirectory;
	private final int NUMBEROFLASTOPENEDFILES = 5;
	private File[] lastOpenedFiles;
	private String style;
	
	public Config()
	{
		this.lastOpenedFiles = new File[NUMBEROFLASTOPENEDFILES];
	}
	public void addOpenedFile(File file)
	{
		for(int i=this.lastOpenedFiles.length - 1 ; i>0;i--)
		{
			lastOpenedFiles[i] = lastOpenedFiles[i-1];
		}
		lastOpenedFiles[0] = file;
	}
	public File getLastOpenedFile()
	{
		return lastOpenedFiles[0];
	}
	public File[] getLastOpenedFiles()
	{
		return lastOpenedFiles;
	}

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
    	oos.writeObject(this);
    	oos.flush();
    	oos.close();
	}
	public Config deserialize() throws IOException, ClassNotFoundException
	{
		FileInputStream configFile = new FileInputStream(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(configFile);
		return ((Config) ois.readObject());
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String string) {
		this.style = string;
	}
}
