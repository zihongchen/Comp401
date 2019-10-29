package a6;

import java.util.ArrayList;

public class ObservablePictureImpl implements ObservablePicture {
	private boolean startObserving;// this parameter represents whether notify() the method's status.
	private Picture source;
	private ArrayList<ObserverWithRegion> observerWithRegionList;// this parameter will wrap the oserver with regions;																// with its region.
	private ArrayList<Region> changedRegionList; // this parameter will save the changed region when notify is suspended

	// constructor
	// initialize all the parameters

	public ObservablePictureImpl(Picture temp) {
		if (temp == null) {
			throw new IllegalArgumentException("picture is null");
		}
		source = temp;
		startObserving = true;
		observerWithRegionList = new ArrayList<ObserverWithRegion>();
		changedRegionList = new ArrayList<Region>();

	}
	
	@Override
	public int getWidth() {
		return source.getWidth();
	}

	@Override
	public int getHeight() {
		return source.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return source.getPixel(x, y);
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		return this.paint(x, y, p, 1.0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a6.Picture#paint(int, int, a6.Pixel, double) the paint method will
	 * replace the original picture encapsulated in the class with the painted
	 * picture
	 *  when the observable is suspended, startObserving would be false ,and
	 * changedRegionList would record region changed after paint() when the
	 * suspendObservable is not called, startObserving would be true then the
	 * function would notify any observers that are connected to the region changed.
	 * 
	 */
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		source = source.paint(x, y, p, factor);
		Region r = new RegionImpl(x, y, x, y);// region(left,top,right,bottom);
		if (startObserving) {
			ArrayList<ObserverWithRegion> k = findObserverWithRegionArray(r);
			for (ObserverWithRegion temp : k) {
				try {
					temp.getROIObserver().notify(this, r.intersect(temp.getRegion()));
				} catch (NoIntersectionException e) {

					e.printStackTrace();
				}
			}

		}

		else {
			changedRegionList.add(r);

		}

		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return this.paint(ax, ay, bx, by, p, 1.0);

	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		source = source.paint(ax, ay, bx, by, p, factor);
		Region r = new RegionImpl(ax, ay, bx, by);// region(left,top,right,bottom);
		if (startObserving) {
			ArrayList<ObserverWithRegion> k = findObserverWithRegionArray(r);
			for (ObserverWithRegion temp : k) {
				try {
					temp.getROIObserver().notify(this, r.intersect(temp.getRegion()));
				} catch (NoIntersectionException e) {

					e.printStackTrace();
				}
			}

		}

		else {
			changedRegionList.add(r);

		}

		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return this.paint(cx, cy, radius, p, 1.0);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		source = source.paint(cx, cy, radius, p, factor);
		Region r = new RegionImpl((int) (cx - radius), (int) (cy - radius), (int) (cx + radius), (int) (cy + radius));// region(left,top,right,bottom);
		if (startObserving) {
			ArrayList<ObserverWithRegion> k = findObserverWithRegionArray(r);
			for (ObserverWithRegion temp : k) {
				try {
					temp.getROIObserver().notify(this, r.intersect(temp.getRegion()));
				} catch (NoIntersectionException e) {

					e.printStackTrace();
				}
			}

		}

		else {
			changedRegionList.add(r);

		}

		return this;
	}

	@Override
	public Picture paint(int x, int y, Picture p) {

		return this.paint(x, y, p, 1.0);
	}

	@Override
	public Picture paint(int x, int y, Picture p, double factor) {
		source = source.paint(x, y, p, factor);
		Region r = new RegionImpl(x, y, x+p.getWidth()-1, y+p.getHeight()-1);// region(left,top,right,bottom);
		if (startObserving) {
			ArrayList<ObserverWithRegion> k = findObserverWithRegionArray(r);
			for (ObserverWithRegion temp : k) {
				try {
					temp.getROIObserver().notify(this, r.intersect(temp.getRegion()));
				} catch (NoIntersectionException e) {

					e.printStackTrace();
				}
			}

		}

		else {
			changedRegionList.add(r);

		}

		return this;

	}

	@Override
	public String getCaption() {
		return source.getCaption();

	}

	@Override
	public void setCaption(String caption) {
		source.setCaption(caption);

	}

	@Override
	public SubPicture extract(int x, int y, int width, int height) {
		return source.extract(x, y, width, height);
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {

		for (ObserverWithRegion x : observerWithRegionList) {
			if (x.getROIObserver() == observer && x.getRegion() == r) {
				return;
			}
		}

		observerWithRegionList.add(new ObserverWithRegion(observer, r));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a6.ObservablePicture#unregisterROIObservers(a6.Region) this method
	 * checks the every ObserverWithRegion in the list every ObserverWithRegion
	 * connects an observer to the region encapsulated in this class thus,
	 * observerWithRegion connects an observer to one region for every
	 * observerWithRegion, we check if the parameter r would intersect with the it
	 * associated with if they are able to intersect, we remove this observer from
	 * observerWithRegionList
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Override
	public void unregisterROIObservers(Region r) {
		ArrayList<ObserverWithRegion> temp = new ArrayList<ObserverWithRegion>();
		for (ObserverWithRegion a : observerWithRegionList) {

			if (ableToIntersect(a.getRegion(), r)) {

				// observerWithRegionList.remove(a);
				temp.add(a);

			}

		}
		for (ObserverWithRegion a : temp) {
			observerWithRegionList.remove(a);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a6.ObservablePicture#unregisterROIObserver(a6.ROIObserver) in order to
	 * avoid concurrent mistake have to record the observer with region first in a
	 * new array;
	 */
	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<ObserverWithRegion> temp = new ArrayList<ObserverWithRegion>();
		for (ObserverWithRegion a : observerWithRegionList) {// this registers different regions to the same observer.
			if (a.getROIObserver() == observer) {
				// observerWithRegionList.remove(a);
				temp.add(a);
			}
		}
		for (ObserverWithRegion a : temp) {
			observerWithRegionList.remove(a);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a6.ObservablePicture#findROIObservers(a6.Region) this method will return
	 * find ROIObserver array from an array of ObserverWithRegion.
	 */
	@Override
	public ROIObserver[] findROIObservers(Region r) {
		ArrayList<ObserverWithRegion> trans = findObserverWithRegionArray(r);
		ROIObserver[] temp = new ROIObserver[trans.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = trans.get(i).getROIObserver();

		}
		return temp;

	}

	public ArrayList<ObserverWithRegion> findObserverWithRegionArray(Region r) {
		ArrayList<ObserverWithRegion> list = new ArrayList<ObserverWithRegion>();
		for (ObserverWithRegion a : observerWithRegionList) {// this registers different regions to the same observer.
			if (ableToIntersect(a.getRegion(), r)) {

				list.add(a);

			}

		}
		return list;

	}

	@Override
	public void suspendObservable() {

		startObserving = false;

	}

	@Override
	public void resumeObservable() {
		startObserving = true;
		Region pass = changedRegionList.get(0);
		for (Region sb : changedRegionList) {
			pass = pass.union(sb);}
		
			ArrayList<ObserverWithRegion> k = findObserverWithRegionArray(pass);
			for (ObserverWithRegion temp : k) {
				try {
					temp.getROIObserver().notify(this, pass.intersect(temp.getRegion()));
				} catch (NoIntersectionException e) {

					e.printStackTrace();
				}
			}
		
		changedRegionList = new ArrayList<Region>();

	}

	public boolean ableToIntersect(Region temp, Region r) {
		try {
			Region a=temp.intersect(r);
		} catch (NoIntersectionException sb) {
			return false;
		}
		return true;

	}
}
