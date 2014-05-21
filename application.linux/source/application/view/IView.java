package application.view;

import processing.core.PApplet;
import processing.core.PVector;

public interface IView {
	void draw(PApplet p);
	void addChild(IView child);
	void removeChild(IView child);
	void set_parent(IView view);
	IView get_parent();
	void setVisible(Boolean isVisible);
	int get_numChildren();
	IView get_childAt(int index);
	float get_x();
	float get_y();
	float get_width();
	float get_height();
	int get_id();
	PVector get_absPos();

}
