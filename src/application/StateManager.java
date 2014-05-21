package application;

public class StateManager {
	private static StateManager _instance;
	private TrackerState _state;
	
	private StateManager(){
		_state = TrackerState.Alignment;
	}
	
	private TrackerState getState(){
		return _state;
	}
	
	public static TrackerState GetState(){
		if(_instance == null){
			_instance = new StateManager();
		}
		
		return _instance.getState();
	}
}
