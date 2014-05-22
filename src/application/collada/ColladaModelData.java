package application.collada;

import application.base.Triangle;

public class ColladaModelData {

	private Triangle[] _triangles;
	
	public float x;
	public float y;
	public float z;
	
	public float xRotation;
	public float yRotation;
	public float zRotation;

	public ColladaModelData(application.base.Triangle[] triangles) {
		_triangles = triangles;
	}

	public application.base.Triangle[] getOrigTriangles(){
		return _triangles;
	}

}
