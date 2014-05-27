package application.interaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PVector;
import application.collada.ColladaModelAdapter;
import application.collada.IAdapterObserver;
import blobDetection.Blob;
import blobDetection.BlobDetection;

public class Tracker implements IAdapterObserver {

	public static int CAM_WIDTH;
	public static int CAM_HEIGHT;
	public static int UNIT_SIZE = 10;
	public static int TEXTURE_WIDTH;
	public static int TEXTURE_HEIGHT;
	public static int REGION_WIDTH;
	public static int REGION_HEIGHT;

	private static final int MAX_LOST_COUNT = 3;
	private static final float MAX_DISTANCE = 0.01f;

	private PVector[][] _blobs;
	private PVector[] _depthStream;
	private BlobDetection _blobDetector;
	private ColladaModelAdapter _adapter;

	private ArrayList<TrackerData> _outputStream;

	public Tracker(ColladaModelAdapter adapter, int streamWidth,
			int streamHeight) {

		_outputStream = new ArrayList<>();

		_adapter = adapter;
		_adapter.addObserver(this);

		CAM_WIDTH = streamWidth;
		CAM_HEIGHT = streamHeight;

		REGION_WIDTH = CAM_WIDTH / UNIT_SIZE;
		REGION_HEIGHT = CAM_HEIGHT / UNIT_SIZE;

		_blobDetector = new BlobDetection(REGION_WIDTH, REGION_HEIGHT);
		_blobDetector.setPosDiscrimination(true);
		_blobDetector.setThreshold(_adapter.getBlobThreshold());
	}

	public void digestDepthStream(PVector[] input) {
		
		_depthStream = input;
		
		// update blobs
		_blobDetector.computeBlobs(getRegions());
		
		// update data
		PVector[] points = getBlobPoints();
		ArrayList<PVector> allPoints = new ArrayList<PVector>(
				Arrays.asList(points));

		ArrayList<TrackerData> lost = new ArrayList<>();

		for (TrackerData data : _outputStream) {
			// find point
			Iterator<PVector> pI = allPoints.iterator();
			PVector intension = null;
			PVector pt = null;
			boolean found = false;
			while (pI.hasNext()) {
				pt = pI.next();
				intension = PVector.sub(pt, data.position);
				if (intension.mag() < 0.01) {
					pI.remove();
					found = true;
					break;
				}
			}

			if (found && pt != null) {
				// update item
				data.lostFrame = 0;
				data.intension = intension;
				data.position = pt;
			} else {
				data.lostFrame++;

				if (data.lostFrame == 1)
					lost.add(data);
			}

			data.accuracy = data.lostFrame / MAX_LOST_COUNT;
		}

		// dispose data
		for (TrackerData l : lost) {
			_outputStream.remove(l);
		}

		// create new
		for (PVector pt : allPoints) {
			TrackerData data = new TrackerData(pt);
			_outputStream.add(data);
		}
		PApplet.println(_outputStream.size() + " / " + points.length);
	}
	
	public ArrayList<TrackerData> getStream(){
		return _outputStream;
	}

	private PVector[] getBlobPoints() {
		PVector[][] blobs = getBlobs();
		int numBlobs = blobs.length;
		PVector[] points = new PVector[numBlobs];

		for (int i = 0; i < numBlobs; i++) {
			float meanX = 0;
			float meanY = 0;
			int numPoints = blobs[i].length;
			for (int j = 0; j < numPoints; j++) {
				meanX += blobs[i][j].x;
				meanY += blobs[i][j].y;
			}
			meanX /= numPoints;
			meanY /= numPoints;

			points[i] = new PVector(meanX, meanY);
		}

		return points;
	}

	public PVector[][] getBlobs() {

		int numBlobs = _blobDetector.getBlobNb();

		_blobs = new PVector[numBlobs][];

		for (int i = 0; i < numBlobs; i++) {
			Blob blob = _blobDetector.getBlob(i);
			// PVector center = new PVector();
			int numPoints = blob.getEdgeNb();

			PVector[] points = new PVector[numPoints];

			for (int p = 0; p < numPoints; p++) {
				points[p] = new PVector(blob.getEdgeVertexA(p).x,
						blob.getEdgeVertexA(p).y);
			}
			_blobs[i] = points;
		}

		return _blobs;
	}

	/*
	 * Returns an array of surface angles
	 */
	public float[] getTexture() {
		double dist;
		double angle;
		double minAngle;
		int unitSize = 20;

		TEXTURE_WIDTH = (CAM_WIDTH - unitSize * 2) / unitSize;
		TEXTURE_HEIGHT = (CAM_HEIGHT - unitSize * 2) / unitSize;

		float[] angles = new float[TEXTURE_HEIGHT * TEXTURE_WIDTH];

		int[] offsets = { -1, 0, 1 };
		PVector dest, point;
		int index, destIndex;
		int count = 0;

		for (int y = 0; y < TEXTURE_HEIGHT; y++) {
			for (int x = 0; x < TEXTURE_WIDTH; x++) {

				int realX = (unitSize + x * unitSize);
				int realY = (unitSize + y * unitSize);

				index = realX + realY * CAM_WIDTH;
				point = _depthStream[index];
				minAngle = Double.MAX_VALUE;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						int offsetX = realX + offsets[i] * unitSize;
						int offsetY = realY + offsets[j] * unitSize;
						destIndex = offsetX + offsetY * CAM_WIDTH;
						dest = _depthStream[destIndex];

						dist = Math.hypot(dest.x - point.x, dest.y - point.y);
						angle = Math.atan2(dest.z - point.z, dist);
						minAngle = minAngle > angle ? angle : minAngle;

					}
				}

				angles[count] = (float) minAngle;
				count++;
			}
		}

		return angles;

	}

	public int[] getRegions() {

		float[] rects = new float[REGION_WIDTH * REGION_HEIGHT];
		int totalMax = Integer.MIN_VALUE;
		int totalMin = Integer.MAX_VALUE;

		for (int x = 0; x < CAM_WIDTH / UNIT_SIZE; x++) {
			for (int y = 0; y < CAM_HEIGHT / UNIT_SIZE; y++) {
				int maxValue = Integer.MIN_VALUE;
				for (int i = 0; i < UNIT_SIZE; i++) {
					for (int j = 0; j < UNIT_SIZE; j++) {
						int index = (x * UNIT_SIZE + i) + (y * UNIT_SIZE + j)
								* CAM_WIDTH;
						// PApplet.println("index : " + index);

						int value = (int) _depthStream[index].z;

						maxValue = value > maxValue ? value : maxValue;

						totalMin = value < totalMin ? value : totalMin;
						totalMax = value > totalMax ? value : totalMax;

					}
				}

				rects[x + y * REGION_WIDTH] = maxValue;
			}
		}

		int diff = totalMax - totalMin;
		diff = diff == 0 ? 1 : diff;

		int[] pixels = new int[rects.length];

		for (int r = 0; r < rects.length; r++) {
			rects[r] = rects[r] == 0.0 ? totalMax : rects[r];
			rects[r] = (rects[r] - totalMin) / diff;
			rects[r] = 1 - (float) Math.pow(1 - rects[r], 5);
			pixels[r] = (int) (rects[r] * 255);
		}

		return pixels;
	}

	@Override
	public void changed() {
		if (_blobDetector != null)
			_blobDetector.setThreshold(_adapter.getBlobThreshold());
	}
}
