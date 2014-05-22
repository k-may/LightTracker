package application.base;

import processing.core.PMatrix3D;
import processing.core.PVector;

public class Triangle {
	public PVector pt1;
	public PVector pt2;
	public PVector pt3;

	public PVector perp;
	public PVector perpNorm;
	public PVector center;

	public int lightValue;
	
	public Triangle(PVector pt12, PVector pt22, PVector pt32) {
		pt1 = pt12;
		pt2 = pt22;
		pt3 = pt32;
		
		//create perpendicular
		PVector v1 = PVector.sub(pt1, pt2);
		v1.normalize();
		PVector v2 = PVector.sub(pt1, pt3);
		v2.normalize();
		
		//might need to check direction here
		perpNorm = v1.cross(v2);
		
	}

	public Triangle applyMatrix(PMatrix3D mat) {
		// TODO Auto-generated method stub
		PVector pt1T = mat.mult(pt1,null);
		PVector pt2T= mat.mult(pt2, null);
		PVector pt3T = mat.mult(pt3, null);
		return new Triangle(pt1T, pt2T, pt3T);
	}

}
