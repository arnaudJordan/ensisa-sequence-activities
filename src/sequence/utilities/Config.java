package sequence.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Config implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String FILENAME="config.ser";
	private File lastOpenedDirectory;
	private List<File> lastOpenedFiles;
	private String style;
	
	public Config()
	{
		this.lastOpenedFiles = new ArrayList<File>();
	}
	public void addOpenedFile(File file)
	{
		this.lastOpenedFiles.add(file);
	}
	public File getLastOpenedFile()
	{
		return this.lastOpenedFiles.get(lastOpenedFiles.size()-1);
	}
	public List<File> getLastOpenedFiles()
	{
		return this.lastOpenedFiles;
	}

	public File getLastOpenedDirectory() {
		return lastOpenedDirectory;
	}

	public void setLastOpenedDirectory(File lastOpenedDirectory) {
		this.lastOpenedDirectory = lastOpenedDirectory;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String string) {
		this.style = string;
	}
	public void removeOpenedFile(File file) {
		this.lastOpenedFiles.remove(file);
	}
	
	static public void serialize(Config config) throws IOException
	{
		FileOutputStream configFile = new FileOutputStream(FILENAME);
    	ObjectOutputStream oos = new ObjectOutputStream(configFile);
    	oos.writeObject(config);
    	oos.flush();
    	oos.close();
	}
	static public Config deserialize() throws IOException, ClassNotFoundException
	{
		FileInputStream configFile = new FileInputStream(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(configFile);
		return ((Config) ois.readObject());
	}
}
