package sequence.utilities;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
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
 * The Class Config. It save the state of the application to be serialized and
 * deserialized.
 */
public class Config implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant FILENAME. */
	private static final String FILENAME = "config.ser";

	/** The last opened directory. */
	private File lastOpenedDirectory;

	/** The last opened files. */
	private final List<File> lastOpenedFiles;

	/** The style. */
	private String style;

	/** The window size. */
	private Dimension windowSize;

	/** The window left top corner location. */
	private Point windowLocation;

	/** The extended state. */
	private int extendedState;

	/**
	 * Instantiates a new config.
	 */
	public Config() {
		lastOpenedFiles = new ArrayList<File>();
		setWindowSize(new Dimension(800, 600));
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		setWindowLocation(new Point(
				screenSize.width / 2 - windowSize.width / 2, screenSize.height
						/ 2 - windowSize.height / 2));
		for (final UIManager.LookAndFeelInfo laf : UIManager
				.getInstalledLookAndFeels()) {
			if (laf.getName().equals("Nimbus"))
				try {
					setStyle(laf.getClassName());
				} catch (final Exception e) {
				}
		}
	}

	/**
	 * Adds the opened file.
	 * 
	 * @param file
	 *            the file
	 */
	public void addOpenedFile(final File file) {
		lastOpenedFiles.add(file);
	}

	/**
	 * Gets the last opened file.
	 * 
	 * @return the last opened file
	 */
	public File getLastOpenedFile() {
		return lastOpenedFiles.get(lastOpenedFiles.size() - 1);
	}

	/**
	 * Gets the last opened files.
	 * 
	 * @return the last opened files
	 */
	public List<File> getLastOpenedFiles() {
		return lastOpenedFiles;
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
	 * @param lastOpenedDirectory
	 *            the new last opened directory
	 */
	public void setLastOpenedDirectory(final File lastOpenedDirectory) {
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
	 * @param string
	 *            the new style
	 */
	public void setStyle(final String string) {
		style = string;
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
	 * @param windowSize
	 *            the new window size
	 */
	public void setWindowSize(final Dimension windowSize) {
		this.windowSize = windowSize;
	}

	/**
	 * Gets the window top left corner location.
	 * 
	 * @return the window top left corner location
	 */
	public Point getWindowLocation() {
		return windowLocation;
	}

	/**
	 * Sets the window top left corner location.
	 * 
	 * @param windowLocation
	 *            the new window top left corner location
	 */
	public void setWindowLocation(final Point windowLocation) {
		this.windowLocation = windowLocation;
	}

	/**
	 * Removes the opened file.
	 * 
	 * @param file
	 *            the file
	 */
	public void removeOpenedFile(final File file) {
		lastOpenedFiles.remove(file);
	}

	/**
	 * Sets the extended state.
	 * 
	 * @param extendedState
	 *            the new extended state
	 */
	public void setExtendedState(final int extendedState) {
		this.extendedState = extendedState;
	}

	/**
	 * Gets the extended state.
	 * 
	 * @return the extended state
	 */
	public int getExtendedState() {
		return extendedState;
	}

	/**
	 * Serialize.
	 * 
	 * @param config
	 *            the config
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static public void serialize(final Config config) throws IOException {
		final FileOutputStream configFile = new FileOutputStream(FILENAME);
		final ObjectOutputStream oos = new ObjectOutputStream(configFile);
		oos.writeObject(config);
		oos.flush();
		oos.close();
	}

	/**
	 * Deserialize.
	 * 
	 * @return the config
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	static public Config deserialize() throws IOException,
			ClassNotFoundException {
		final FileInputStream configFile = new FileInputStream(FILENAME);
		final ObjectInputStream ois = new ObjectInputStream(configFile);
		return ((Config) ois.readObject());
	}
}
