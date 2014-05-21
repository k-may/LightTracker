package application.view;

import static processing.core.PApplet.println;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class View implements IView {

	private static int ID_COUNT = 0;

	protected Boolean _isVisible = true;

	protected int _id;
	protected float _x;
	protected float _y;
	protected float _width = -1;
	protected float _height = -1;
	protected String _name;
	protected IView _parent;
	private ArrayList<IView> _childs;
	protected Boolean _invalidated = false;

	public View() {
		_id = getUniquID();
		_childs = new ArrayList<IView>();
	}

	public int get_id() {
		return _id;
	}

	public View(String name) {
		this();
		_name = name;
		println("new view : " + _name);
	}

	@Override
	public void draw(PApplet p) {
		if (!_isVisible)
			return;
		// TODO Auto-generated method stub
		for (IView view : _childs)
			view.draw(p);
	}

	@Override
	public void addChild(IView child) {
		// TODO Auto-generated method stub
		child.set_parent(this);

		if (!_childs.contains(child))
			_childs.add(child);
		else {
			_childs.remove(child);
			_childs.add(child);
		}
	}

	@Override
	public void removeChild(IView child) {
		// TODO Auto-generated method stub
		child.set_parent(null);

		if (_childs.contains(child))
			_childs.remove(child);
	}

	public float get_x() {
		return _x;
	}

	public void set_x(float _x) {
		this._x = _x;
	}

	public float get_y() {
		return _y;
	}

	public void set_y(float _y) {
		this._y = _y;
	}

	public float get_width() {
		return _width;
	}

	public void set_width(float _width) {
		this._width = _width;
	}

	public float get_height() {
		return _height;
	}

	public void set_height(float _height) {
		this._height = _height;
	}

	@Override
	public void set_parent(IView view) {
		// TODO Auto-generated method stub
		_parent = view;
	}

	@Override
	public IView get_parent() {
		// TODO Auto-generated method stub
		return _parent;
	}

	protected void log(String message) {
		println(message);
	}

	public void removeAllChildren() {
		for (IView child : _childs) {
			removeChild(child);
		}

		_childs = new ArrayList<IView>();
	}

	public int get_numChildren() {
		return _childs.size();
	}

	public View get_childAt(int i) {
		return (View) _childs.get(i);
	}

	public String get_name() {
		return _name;
	}

	public void setVisible(Boolean isVisible) {
		_isVisible = isVisible;
	}

	public PVector get_absPos() {
		// Rectangle rect = get_rect();
		IView tempParent = _parent;
		PVector pos = new PVector(_x, _y);
		while (tempParent != null) {
			
			pos = PVector.add(pos, new PVector(tempParent.get_x(), tempParent.get_y()));
			tempParent = tempParent.get_parent();
		}

		return pos;
	}

	public void set_alpha(float value) {

	}

	private int getUniquID() {
		return ID_COUNT++;
	}
}
