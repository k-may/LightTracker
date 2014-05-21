package application;

public class Event {

	public void dispatch(){
		Controller.instance.addEvent(this);
	}
}
