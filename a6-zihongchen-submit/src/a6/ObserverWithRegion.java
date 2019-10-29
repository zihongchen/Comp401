package a6;

import java.util.ArrayList;

public class ObserverWithRegion {
	protected ROIObserver o;
	protected Region region;
	
	
	public ObserverWithRegion(ROIObserver o, Region region) {
		if(o==null||region==null) {
			throw new IllegalArgumentException();
		}
		
		this.o=o;
		this.region =region;
	}
	public ROIObserver getROIObserver() {
		return o;
	}
	public Region getRegion() {
		return region;
	}
	

}
