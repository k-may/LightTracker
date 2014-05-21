package application.sculpture;

import processing.core.PApplet;

public class SculptureFacade {
	private SculptureSerialProxy _proxy;

	public SculptureFacade(PApplet parent) {
		_proxy = new SculptureSerialProxy(parent);
	}
	
	
	
}
