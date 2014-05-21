package application.clients;

import processing.core.PApplet;
import processing.data.XML;

public abstract class XMLClient {

	protected XML loadXML(PApplet parent, String filePath) throws Exception {
		XML xml = parent.loadXML(filePath);
		if (xml == null) {
			throw new Exception("couldn't load xml : " + filePath);
		}
		return xml;
	}

	protected void writeXML(PApplet parent, String filePath, XML data)
			throws Exception {
		try {
			parent.saveXML(data, filePath);
		} catch (RuntimeException e) {
			throw new Exception("couldn't save xml: " + filePath);
		}
	}

	protected int getIntContent(String name) throws Exception{
		try {
			return Integer.parseInt(getContent(name));
		} catch (NumberFormatException e) {
			throw new Exception("couldn't parse int :" + name);
		}
	}

	protected float getFloatContent(String name) {
		return Float.parseFloat(getContent(name));
	}

	protected Boolean getBooleanContent(String name) {
		Boolean value = true;
		value = Boolean.parseBoolean(getContent(name));
		return value;
	}

	protected abstract String getContent(String name);

	protected String getContent(String name, XML xml) throws Exception {
		String value = "";
		try {
			value = xml.getChild(name).getContent();// Integer.parseInt(dataXML.getChild("input_for_range").getContent());
		} catch (NullPointerException e) {
			throw new Exception("couldn't get data : " + name);
		}
		return value;
	}

}
