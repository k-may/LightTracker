package application;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import processing.core.PApplet;
import application.clients.DataXMLClient;
import application.collada.ColladaModelAdapter;
import application.collada.ColladaModelData;
import application.collada.ColladaModelLoader;
import application.collada.ModelFacade;
import application.interaction.SONRegion;
import application.sculpture.SculptureFacade;
import application.sculpture.SculptureSerialProxy;

public class AppBuilder {

	private PApplet _parent;
	private SONRegion _region;
	//private ColladaModelAdapter _adapter;

	//private SculptureSerialProxy _sculpture;
	private String _modelPath;
	private String _basePath;
	private SculptureFacade _sculpture;
	private ModelFacade _model;

	public AppBuilder(PApplet parent) {
		_parent = parent;
		_parent.noLoop();
		_basePath = getClassPath();
		initConfig();
		initModel();
		initInteraction();
		initSculpture();
		start();
	}

	private void initConfig() {
		// TODO Auto-generated method stub
		DataXMLClient xmlClient = new DataXMLClient(_basePath);
		_modelPath = xmlClient.getModelPath();
		
	}

	private String getClassPath() {
		String path = AppBuilder.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			path = URLDecoder.decode(path, "UTF-8");
			return path;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	private void initSculpture() {
		_sculpture = new SculptureFacade(_parent);
	}

	private void initModel() {
		// load model & send to adapter

		ColladaModelData data = ColladaModelLoader.LoadModel(_modelPath, _parent);
		//_adapter = new ColladaModelAdapter(data);
		_model = new ModelFacade(_parent);
	}

	private void start() {
		Controller controller = new Controller(_sculpture, _model);
		_parent.loop();
	}

	private void initInteraction() {
		_region = new SONRegion(_adapter);
	}

	public SONRegion getRegion() {
		// TODO Auto-generated method stub
		return _region;
	}

}
