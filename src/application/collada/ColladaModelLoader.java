package application.collada;

import application.clients.ColladaXMLClient;
import processing.core.PApplet;

public class ColladaModelLoader {

	public static ColladaModelData LoadModel(String path, PApplet parent){
		ColladaXMLClient loader = new ColladaXMLClient(path);
		application.base.Triangle[] triangles = loader.getTriangles();
		return new ColladaModelData(triangles);
	}
}
	