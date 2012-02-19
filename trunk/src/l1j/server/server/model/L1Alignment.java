/**
 * 
 */
package l1j.server.server.model;

public class L1Alignment {
	private final int _value;

	private L1Alignment(int value) {
		_value = value;
	}

	public static L1Alignment NEUTRAL = new L1Alignment(0);
	public static L1Alignment LAWFUL = new L1Alignment(1);
	public static L1Alignment CHAOTIC = new L1Alignment(2);

	public static L1Alignment fromValue(int value) {
		if (value == 0) {
			return NEUTRAL;
		}
		if (value == 1) {
			return LAWFUL;
		}
		if (value == 2) {
			return CHAOTIC;
		}
		throw new IllegalArgumentException("value is invalid");
	}

	public int value() {
		return _value;
	}
}