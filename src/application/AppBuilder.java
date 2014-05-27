package application;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import lighttracker.LightTracker;
import processing.core.PApplet;
import processing.event.MouseEvent;
import SimpleOpenNI.SimpleOpenNI;
import application.clients.DataXMLClient;
import application.collada.ColladaModelData;
import application.collada.ColladaModelLoader;
import application.collada.ModelFacade;
import application.interaction.SONRegion;
import application.interaction.Tracker;
import application.sculpture.SculptureFacade;
import application.view.ControlsView;
import application.view.KinectView;
import application.view.MainView;
import application.view.IView;
import application.view.ModelView;
import application.view.DepthView;
import application.view.SurfaceView;

public class AppBuilder {

	private PApplet _parent;
	private SONRegion _region;
	// private ColladaModelAdapter _adapter;

	// private SculptureSerialProxy _sculpture;
	private String _modelPath;
	private String _basePath;
	private SculptureFacade _sculpture;
	private ModelFacade _model;
	DataXMLClient _xmlClient;

	private static AppBuilder _instance;
	public static void start(PApplet parent) {
		if (_instance == null)
			_instance = new AppBuilder(parent);
	}

	private AppBuilder(PApplet parent) {
		_parent = parent;
		_parent.noLoop();
		_basePath = getClassPath();
		initConfig();

		// load 3D model and translate geometry
		initModel();

		// setup kinect region
		initInteraction();

		// setup microcontroller and comm
		// initSculpture();

		initUI();
		
		// start interface
		_parent.loop();
	}

	private void initUI() {
		MainView view = new MainView();
		((IView) LightTracker.instance).addChild(view);
		((LightTracker) _parent).registerMainView(view);

		view.set_width(_parent.displayWidth);
		view.set_height(_parent.displayHeight);

		ControlsView controls = new ControlsView(_model.getAdapter());
		controls.set_height(_parent.displayHeight);
		view.registerControlsView(controls);

		ModelView modelView = new ModelView(_model.getAdapter());
		view.registerModelView(modelView);

		KinectView kinectView = new KinectView();
		view.registerKinectView(kinectView);

		DepthView trackerView = new DepthView(
				((LightTracker) _parent).getTracker());
		controls.addChild(trackerView);
		trackerView.set_x(20);
		trackerView.set_y(500);

		SurfaceView surfaceView = new SurfaceView(
				((LightTracker) _parent).getTracker());
		controls.addChild(surfaceView);
		surfaceView.set_x(20);
		surfaceView.set_y(700);

	}

	private void initConfig() {
		// TODO Auto-generated method stub
		_xmlClient = new DataXMLClient(_basePath);
		_modelPath = _xmlClient.getModelPath();

	}

	private String getClassPath() {
		String path = AppBuilder.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
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
		ColladaModelData data = ColladaModelLoader.LoadModel(_modelPath,
				_parent);
		_model = new ModelFacade(_parent, data, _xmlClient);
	}

	private void initInteraction() {
		SimpleOpenNI son = new SimpleOpenNI(_parent);
		son.enableDepth();
		son.setMirror(false);
		((LightTracker) _parent).registerSON(son);

		Tracker tracker = new Tracker(_model.getAdapter(), son.depthWidth(),
				son.depthHeight());
		((LightTracker) _parent).registerTracker(tracker);

		_region = new SONRegion();
		((LightTracker) _parent).registerSONRegion(_region);
	}

}
