package application.clients;

import java.util.ArrayList;

import cooladaLoader.ColladaLoader;
import cooladaLoader.Line;
import cooladaLoader.Point3D;
import lighttracker.LightTracker;
import processing.core.PApplet;
//import colladaLoader.ColladaLoader;//xmlMapping.ColladaRawLoader;
import processing.core.PVector;
import application.base.Triangle;

public class ColladaXMLClient extends XMLClient {

	private ColladaLoader loader;
	
	public ColladaXMLClient(String path) {
		 loader = new ColladaLoader(path, LightTracker.instance);//ColladaLoader.getInstance(path, LightTracker.instance);  //ColladaLoader(path, LightTracker.instance);
	}

	@Override
	protected String getContent(String name) {
		try {
			return getContent(name, xml);
		} catch (Exception e) {
			return null;
		}
	}

	public application.base.Triangle[] getTrianglesFromLines(){

		Line[] lines = loader.getLines();

		Triangle[] trianges = new Triangle[lines.length];
		
		for(int i = 0; i < lines.length; i ++){
				Triangle triange = createTriangleFromLines(lines[i], lines[(i + 1) % lines.length]);
				trianges[i] = triange;
		}
	
		return trianges;
		
	}
	
	private Triangle createTriangleFromLines(Line a, Line b){
		ArrayList<PVector> vectors = new ArrayList<PVector>();
		vectors.add(p3DToPV(a.A));
		vectors.add(p3DToPV(a.B));
		vectors.add(p3DToPV(b.A));
		vectors.add(p3DToPV(b.B));
		
		ArrayList<PVector> duplicates = new ArrayList<PVector>();
		for(int i = 0; i <vectors.size(); i ++){
			for(int v = 0; v < vectors.size(); v ++){
				if(vectors.get(i).equals(vectors.get(v)) && i != v){
					duplicates.add(vectors.get(v));
					vectors.remove(v);
					break;
				}
			}
			if(vectors.size() == 3)
				break;
		}

		return new Triangle(vectors.get(0), vectors.get(1), vectors.get(2));
	}
	
	private PVector p3DToPV(Point3D input){
		PVector out =  new PVector(input.x, input.y, input.z);
		return out;
	}
	
	
	
	public application.base.Triangle[] getTriangles() {
		
		cooladaLoader.Triangle[] colTriangles = loader.getTriangles();
		
		application.base.Triangle[] triangles = new application.base.Triangle[colTriangles.length];
		
		int tCount = 0;
		while(tCount < colTriangles.length){
			
			cooladaLoader.Triangle triangle = colTriangles[tCount];
			triangles[tCount] = convertColTriangleToTriange(triangle);//new application.base.Triangle(pt1, pt2, pt3);
			tCount ++;
			
		}

		if (triangles.length > 0)
			return triangles;
		else
			return getTrianglesFromLines();
	}

	Triangle convertColTriangleToTriange(cooladaLoader.Triangle original){
		PVector a = convertPoint3DToPVector(original.A);
		PVector b = convertPoint3DToPVector(original.B);
		PVector c = convertPoint3DToPVector(original.C);
		return new Triangle(a, b, c);
	}
	
	PVector convertPoint3DToPVector(Point3D original){
		return new PVector(original.x, original.y, original.z);
	}
}

