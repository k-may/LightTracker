package application.clients;

import lighttracker.LightTracker;
import application.collada.ColladaModelAdapter;
import application.view.KinectView;
import processing.core.PVector;
import processing.data.XML;

public class DataXMLClient extends XMLClient {

	private String _filePath;

	public DataXMLClient(String filePath) {
		_filePath = filePath;// + "config.xml";
		try {
			xml = loadXML(LightTracker.instance, _filePath + "config.xml");
		} catch (Exception e) {
			System.out.println("can't load : " + _filePath + "config.xml");
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
		String path = xml.getChild("model").getString("path");
		return _filePath + path;
	}

	public PVector getPosition() {
		int x = xml.getChild("model").getChild("x").getIntContent();
		int y = xml.getChild("model").getChild("y").getIntContent();
		int z = xml.getChild("model").getChild("z").getIntContent();
		return new PVector(x, y, z);
	}

	public int getPitch() {
		return xml.getChild("model").getChild("pitch").getIntContent();// getInt("pitch");
	}

	public int getRoll() {
		return xml.getChild("model").getChild("roll").getIntContent();
	}

	public int getYaw() {
		return xml.getChild("model").getChild("yaw").getIntContent();
	}

	public void save(ColladaModelAdapter adapter) {
		xml.getChild("model").getChild("roll").setIntContent(adapter.getRoll());
		xml.getChild("model").getChild("pitch")
				.setIntContent(adapter.getPitch());
		xml.getChild("model").getChild("yaw").setIntContent(adapter.getYaw());
		xml.getChild("model").getChild("x")
				.setIntContent((int) adapter.getPosition().x);
		xml.getChild("model").getChild("y")
				.setIntContent((int) adapter.getPosition().y);
		xml.getChild("model").getChild("z")
				.setIntContent((int) adapter.getPosition().z);
		xml.getChild("model").getChild("scale")
				.setFloatContent(adapter.getScale());

		xml.getChild("kinect").getChild("threshold")
				.setFloatContent(adapter.getBlobThreshold());
		LightTracker.instance.saveXML(xml, _filePath + "config.xml");
	}

	public float getScale() {
		return xml.getChild("model").getChild("scale").getFloatContent();
	}

	public float getBlogThreshold() {
		return xml.getChild("kinect").getChild("threshold").getFloatContent();
	}
}
