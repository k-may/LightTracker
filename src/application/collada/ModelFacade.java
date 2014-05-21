package application.collada;

import application.clients.DataXMLClient;
import processing.core.PApplet;

public class ModelFacade {
	ColladaModelAdapter _adapter;
	
	public ModelFacade(PApplet parent, ColladaModelData data, DataXMLClient xml){
		 _adapter = new ColladaModelAdapter(data, xml);
	}
	
	public ColladaModelAdapter getAdapter(){
		return _adapter;
	}
}
