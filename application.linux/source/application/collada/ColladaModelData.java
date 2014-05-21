package application.collada;

import cooladaLoader.Triangle;

public class ColladaModelData {

	private Triangle[] _triangles;
	
	public ColladaModelData(Triangle[] triangles){
		_triangles = triangles;
	}

	public Triangle[] getTriangles() {
		// TODO Auto-generated method stub
		return _triangles;
	}
}
