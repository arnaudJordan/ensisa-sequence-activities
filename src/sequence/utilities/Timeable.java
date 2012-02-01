package sequence.utilities;

/**
 * Defines the interface for time positionned classes.
 *
 * @see Scaleable
 * @see sequence.ui.utilities.TimeLayout
 */
public interface Timeable {
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public int getStartTime();
	
	/**
	 * Gets the stop time.
	 *
	 * @return the stop time
	 */
	public int getStopTime();
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration();
}
