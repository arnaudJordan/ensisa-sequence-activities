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

import javax.swing.UIManager;

/**
 * The Class Config. It save the state of the application to be serialized and deserialized.
 */
public class Config implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant FILENAME. */
	private static final String FILENAME="config.ser";
	
	/** The last opened directory. */
	private File lastOpenedDirectory;
	
	/** The last opened files. */
	private List<File> lastOpenedFiles;
	
	/** The style. */
	private String style;
	
	/** The window size. */
	private Dimension windowSize;
	
	/** The extended state. */
	private int extendedState;
	
	/**
	 * Instantiates a new config.
	 */
	public Config()
	{
		this.lastOpenedFiles = new ArrayList<File>();
		setWindowSize(new Dimension(800, 600));
		for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
			if (laf.getName().equals("Nimbus"))
				try {
					setStyle(laf.getClassName());
				} catch (Exception e) {
				}
		}
	}
	
	/**
	 * Adds the opened file.
	 *
	 * @param file the file
	 */
	public void addOpenedFile(File file)
	{
		this.lastOpenedFiles.add(file);
	}
	
	/**
	 * Gets the last opened file.
	 *
	 * @return the last opened file
	 */
	public File getLastOpenedFile()
	{
		return this.lastOpenedFiles.get(lastOpenedFiles.size()-1);
	}
	
	/**
	 * Gets the last opened files.
	 *
	 * @return the last opened files
	 */
	public List<File> getLastOpenedFiles()
	{
		return this.lastOpenedFiles;
	}

	/**
	 * Gets the last opened directory.
	 *
	 * @return the last opened directory
	 */
	public File getLastOpenedDirectory() {
		return lastOpenedDirectory;
	}

	/**
	 * Sets the last opened directory.
	 *
	 * @param lastOpenedDirectory the new last opened directory
	 */
	public void setLastOpenedDirectory(File lastOpenedDirectory) {
		this.lastOpenedDirectory = lastOpenedDirectory;
	}
	
	/**
	 * Gets the style.
	 *
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	
	/**
	 * Sets the style.
	 *
	 * @param string the new style
	 */
	public void setStyle(String string) {
		this.style = string;
	}
	
	/**
	 * Gets the window size.
	 *
	 * @return the window size
	 */
	public Dimension getWindowSize() {
		return windowSize;
	}
	
	/**
	 * Sets the window size.
	 *
	 * @param windowSize the new window size
	 */
	public void setWindowSize(Dimension windowSize) {
		this.windowSize = windowSize;
	}
	
	/**
	 * Removes the opened file.
	 *
	 * @param file the file
	 */
	public void removeOpenedFile(File file) {
		this.lastOpenedFiles.remove(file);
	}
	
	/**
	 * Sets the extended state.
	 *
	 * @param extendedState the new extended state
	 */
	public void setExtendedState(int extendedState) {
		this.extendedState = extendedState;
	}
	
	/**
	 * Gets the extended state.
	 *
	 * @return the extended state
	 */
	public int getExtendedState() {
		return this.extendedState;
	}
	
	/**
	 * Serialize.
	 *
	 * @param config the config
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	static public void serialize(Config config) throws IOException
	{
		FileOutputStream configFile = new FileOutputStream(FILENAME);
    	ObjectOutputStream oos = new ObjectOutputStream(configFile);
    	oos.writeObject(config);
    	oos.flush();
    	oos.close();
	}
	
	/**
	 * Deserialize.
	 *
	 * @return the config
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	static public Config deserialize() throws IOException, ClassNotFoundException
	{
		FileInputStream configFile = new FileInputStream(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(configFile);
		return ((Config) ois.readObject());
	}	
}
