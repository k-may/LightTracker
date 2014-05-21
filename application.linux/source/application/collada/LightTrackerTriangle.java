package application.collada;

import cooladaLoader.Triangle;

public class LightTrackerTriangle {
	private Triangle _triangle;
	private LightTrackerSurface _surface;
	
	public LightTrackerTriangle(Triangle triangle, LightTrackerSurface surface){
		_triangle = triangle;
		_surface = surface;
	}

	public Triangle get_triangle() {
		return _triangle;
	}

	public LightTrackerSurface get_surface() {
		return _surface;
	}
	
}
