package application.clients;

import lighttracker.LightTracker;
import application.view.KinectView;
import processing.data.XML;

public class DataXMLClient extends XMLClient {

	private XML xml;
	private String _filePath;
	
	public DataXMLClient(String filePath){
		_filePath = filePath + "config.xml";
		try {
			xml = loadXML(LightTracker.instance,_filePath + "config.xml");
		} catch (Exception e) {
			System.out.println("can't load : " + _filePath);
		}
	}

	@Override
	protected String getContent(String name) {
		try {
			return getContent(name, xml);
		} catch (Exception e) {
			return null;
		}
	}

	public String getModelPath() {
		return getContent("model_path");
	}

}
