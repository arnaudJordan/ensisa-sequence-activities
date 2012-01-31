package sequence.utilities;

import java.awt.Dimension;
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
	private Dimension windowSize;
	private int extendedState;
	
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
	public Dimension getWindowSize() {
		return windowSize;
	}
	public void setWindowSize(Dimension windowSize) {
		this.windowSize = windowSize;
	}
	public void removeOpenedFile(File file) {
		this.lastOpenedFiles.remove(file);
	}
	public void setExtendedState(int extendedState) {
		this.extendedState = extendedState;
	}
	public int getExtendedState() {
		return this.extendedState;
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
