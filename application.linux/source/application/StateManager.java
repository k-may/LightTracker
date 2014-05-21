package application;

public class StateManager {
	private static StateManager _instance;
	private TrackerState _state;
	
	private StateManager(){
		_state = TrackerState.Alignment;
	}
	
	public StateManager getInstance(){
		if(_instance == null){
			_instance = new StateManager();
		}
		
		return _instance;
	}
	
	private TrackerState getState(){
		return _state;
	}
	
	public static TrackerState GetState(){
		return _instance.getState();
	}
}
