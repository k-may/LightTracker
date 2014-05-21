package application;

import java.util.ArrayList;

import application.collada.ModelFacade;
import application.sculpture.SculptureFacade;

public class Controller {

	private ModelFacade _model;
	private SculptureFacade _sculpture;
	private ArrayList<Event> _events;
	
	public static Controller instance; 
	
	public Controller(SculptureFacade sculpture, ModelFacade model){
		instance = this;
		_model = model;
		_sculpture = sculpture;
	}
	
	public void addEvent(Event event){
		
	}
	
	public void runEvents(){
		for(Event evt : _events){
			processEvent(evt);
		}
		_events.clear();
	}

	private void processEvent(Event evt) {
		
	}
	
}
