package application.view;

import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

public class KinectView extends View {

	private SimpleOpenNI _son;

	public KinectView(SimpleOpenNI son) {
		_son = son;
	}
	
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub
	
		p.stroke(0);
		
		PVector[] depthPoints = _son.depthMapRealWorld();
		for(int i = 0; i < depthPoints.length; i += 10){
			p.point(depthPoints[i].x, depthPoints[i].y, depthPoints[i].z);
		}
		
		
		super.draw(p);
	}

}
