package application.collada;

import processing.core.PApplet;
import cooladaLoader.ColladaLoader;
import cooladaLoader.Triangle;

public class ColladaModelLoader {

	public static ColladaModelData LoadModel(String path, PApplet parent){
		ColladaLoader loader = new ColladaLoader(path, parent);
		Triangle[] triangles = loader.getTriangles(); 
		return new ColladaModelData(triangles);
	}
}
