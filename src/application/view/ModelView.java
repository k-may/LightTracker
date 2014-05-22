package application.view;

import processing.core.PApplet;
import processing.core.PVector;
import application.base.Triangle;
import application.collada.ColladaModelAdapter;

public class ModelView extends View {

	private ColladaModelAdapter _adapter;

	public ModelView(ColladaModelAdapter data) {
		_adapter = data;
	}

	@Override
	public void draw(PApplet p) {

		p.pushMatrix();
		
		p.stroke(100);
		
		Triangle[] triangles = _adapter.getTriangles();
		
		for (int i = 0; i < triangles .length; i++) {
			drawTriangle(triangles[i], p);
		}
		
		p.popMatrix();
	}

	private void drawTriangle(Triangle triangle, PApplet p) {
		p.fill(triangle.lightValue);
		p.beginShape();
		p.vertex(triangle.pt1.x, triangle.pt1.y, triangle.pt1.z);
		p.vertex(triangle.pt2.x, triangle.pt2.y, triangle.pt2.z);
		p.vertex(triangle.pt3.x, triangle.pt3.y, triangle.pt3.z);
		p.endShape();
	}
}
