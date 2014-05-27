package application.interaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import blobDetection.Blob;
import blobDetection.BlobDetection;
import SimpleOpenNI.SimpleOpenNI;
import application.base.Rectangle;
import application.collada.ColladaModelAdapter;
import application.collada.IAdapterObserver;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class SONRegion{

	private Map<Integer, HeadData> _headData;

	public SONRegion() {

	}
	

	public void digestTrackerStream(ArrayList<TrackerData> arrayList) {

	}


	public void onLostHead(int id) {

	}

	public void onTrackedHead(int id, PVector pos) {

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

	private ArrayList<HeadData> getHeadDataAsList() {
		ArrayList<HeadData> headDataList = new ArrayList<>();
		Iterator it = _headData.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, HeadData> pairs = (Entry<Integer, HeadData>) it
					.next();
			headDataList.add(pairs.getValue());
		}
		return headDataList;
	}


}
