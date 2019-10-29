package a6;

public class RegionImpl implements Region {
	private int top;
	private int bottom;
	private int left;
	private int right;
	
	// constructor
	public RegionImpl(int left, int top, int right, int bottom)   {
		if(left>right||top>bottom) {
			throw new  IllegalArgumentException("Wrong parameter");
		}
		
		this.top=top;
		this.left=left;
		this.right=right;
		this.bottom=bottom;
	}
	@Override
	public int getTop() {
		
		return top;
	}

	@Override
	public int getBottom() {
		
		return bottom;
	}

	@Override
	public int getLeft() {
		
		return left;
	}

	@Override
	public int getRight() {
		
		return right;
	}
	/*
	 * (non-Javadoc)
	 * @see a6.Region#intersect(a6.Region)
	 * This method return a region that is the overlap between this and parameter  passed in
	 * if there is no intersection, an exception would be thrown
	 * 
	 */
	@Override
	public Region intersect(Region other) throws NoIntersectionException  {
		if(other==null) {
			throw new NoIntersectionException();
		}
		int left = (other.getLeft()>=this.left)? other.getLeft():this.left;
		int right = (other.getRight()<=this.right)? other.getRight():this.right;
		int top = (other.getTop()>=this.top)? other.getTop():this.top;
		int bottom =  (other.getBottom()<=this.bottom)? other.getBottom():this.bottom;
		try {
			Region k= new RegionImpl( left,top,right,bottom);
		}catch (IllegalArgumentException r) {
			throw new NoIntersectionException();
		
	}
		return new RegionImpl( left,top,right,bottom);
	}


	
	
	/*
	 * (non-Javadoc)
	 * @see a6.Region#union(a6.Region)
	 * this method find the smallest left and top between this region and other;
	 * find the biggest bottom and right between this region and other; those four would form a smallest region that contains both of these.
	 */
	@Override
	public Region union(Region other) {
		if(other==null) {
			return this;
		}
		int left = (other.getLeft()<=this.left)? other.getLeft():this.left;
		int right = (other.getRight()>=this.right)? other.getRight():this.right;
		int top = (other.getTop()<=this.top)? other.getTop():this.top;
		int bottom =  (other.getBottom()>=this.bottom)? other.getBottom():this.bottom;
		return new RegionImpl( left,top,right,bottom);
	}

}
