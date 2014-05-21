package application.interaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.collada.ColladaModelAdapter;

import processing.core.PVector;

public class SONRegion {

	private Map<Integer, HeadData> _headData;
	private ArrayList<LightData> _streamData;

	private ColladaModelAdapter _adapter;
	
	public SONRegion(ColladaModelAdapter adapter){
		_adapter = adapter;
	}
	
	public void onLostHead(int id) {

	}

	public void onTrackedHeadd(int id, PVector pos) {

	}

	public void onNewHead(int id, PVector pos) {
		getHead(id, pos);
	}

	private void getHead(int id, PVector pos) {
		if (_headData == null) {
			_headData = new HashMap<Integer, HeadData>();
		}

		HeadData headData = null;

		if (_headData.containsKey(id)) {
			HeadData data = _headData.get(id);
		} else {
			headData = new HeadData();
		}

		headData.addPosition(pos);
	}

	public void runInteractions(){
		_streamData = new ArrayList<LightData>();
		
		for(HeadData data : _headData.values()){
			processInput(data);
		}
		
		_adapter.handleLightStreamData(_streamData);
	}

	private void processInput(HeadData data) {
		//invert position -- generate light vector
	}
}
