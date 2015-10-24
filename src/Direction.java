
/**
 * Used to tell if the Timer is going counting up or down. 
 * @author Alex
 *
 */
public enum Direction {
	UP("Up"),
	DOWN("Down");
	
	/**
	 * Text for the direction.
	 */
	private final String m_text;
	
	/**
	 * Private constructor for Direction. 
	 * @param text East to read text
	 */
	private Direction(String text) {
		m_text = text;
	}
	
	/**
	 * @return An easy to read text for the Direction.
	 */
	public String getText() {
		return m_text;
	}

}
