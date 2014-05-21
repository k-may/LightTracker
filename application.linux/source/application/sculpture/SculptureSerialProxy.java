package application.sculpture;

import processing.core.PApplet;
import processing.serial.Serial;

public class SculptureSerialProxy {

	/*
	 * Outputs instructions to the sculpture
	 */

	private Serial serial;
	public static final char HEADER = 'H';

	public SculptureSerialProxy(PApplet parent) {
		serial = new Serial(parent, Serial.list()[0], 9600);

	}

	public void sendValues(byte[] values) {
		serial.write(HEADER);
		for (int i = 0; i < values.length; i++) {
			serial.write(values[0]);
		}
	}
}
