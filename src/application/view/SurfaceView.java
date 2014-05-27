package application.view;

import processing.core.PApplet;
import application.interaction.SONRegion;
import application.interaction.Tracker;

public class SurfaceView extends View {

	private Tracker _tracker;

	public SurfaceView(Tracker tracker) {
		_tracker = tracker;
	}

	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub
		super.draw(p);

		this._x = 23;
		this._y = 650;
		int res = 4;

		p.noStroke();
		p.colorMode(PApplet.RGB);

		int count = 0;
		float[] angles = _tracker.getTexture();

		int regionsWidth = Tracker.TEXTURE_WIDTH;// / _region.UNIT_SIZE;
		int regionsHeight = Tracker.TEXTURE_HEIGHT;// / _region.UNIT_SIZE;

		for (int x = 0; x < regionsWidth; x++) {
			for (int y = 0; y < regionsHeight; y++) {
				// float value = (float) angles[x + y * regionsWidth];
				int index = x + y * regionsWidth;
				float value = Math.abs(PApplet.degrees((float) angles[index])) / 90;
				if (value < 0.3 && value != 0.0) {
					// PApplet.println(value);
					p.fill(value * 255, 100, 255, 100);
					p.rect(_x + x * res, _y + y * res, res, res);
				}// else
					// PApplet.println("wtf");
				count++;
			}
		}

		p.fill(255, 100, 255);
		p.ellipse(_x + 10, _y + 200, 20, 20);

		p.fill(0, 100, 255);
		p.ellipse(_x + 100, _y + 200, 20, 20);
		// PApplet.println("wtf");
	}
}
