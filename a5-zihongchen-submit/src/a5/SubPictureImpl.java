package a5;



public class SubPictureImpl extends PictureImpl implements SubPicture {

	private Picture source;//
	private int xoffset;//
	private int yoffset; //

	private int width;
	private int height;
	private boolean immutable;// if you want it to be immutable, just use the original paint method of the 

	public SubPictureImpl(Picture source, int xoffset, int yoffset, int width, int height) {
		super(source.getCaption());
		if (source == null || xoffset+width > source.getWidth() || xoffset < 0 || yoffset+height > source.getHeight() || yoffset < 0
				|| width <1  || height <1) {
			throw new IllegalArgumentException("wrong parameter");
		} 
		this.width=width;
		this.height=height;
		
		this.source = source;
		this.xoffset = xoffset;
		this.yoffset = yoffset;

		if (source.paint(0, 0, source.getPixel(0, 0)) == source) {
			immutable = false;
		} else {
			immutable = true;
		}

	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		return source.getPixel(x+xoffset, y+yoffset);
	}

	public Picture paint(int x, int y, Pixel p) {
		return paint(x, y, p, 1.0);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		

		
		if(immutable) {
			Picture a= source.paint(x+xoffset, y+yoffset, p, factor).extract(xoffset, yoffset, width, height);
			a.setCaption(this.getCaption());
			return a;
		}else {
			source=source.paint(x+xoffset, y+yoffset, p, factor);
			return this;
		}

	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return paint(ax, ay, bx, by, p, 1.0);
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if(immutable) {
			Picture a= source.paint(ax+xoffset, ay+yoffset, bx+xoffset, by+yoffset, p, factor).extract(xoffset, yoffset, width, height);
			a.setCaption(this.getCaption());
			return a;
		}else {
			source=source.paint(ax, ay, bx, by, p, factor);
			return this;
		}
		

	}

	
		
	

	public Picture paint(int x, int y, Picture p) {
		return paint(x, y, p, 1.0);
	}

	public Picture paint(int x, int y, Picture p, double factor) {
		if(immutable) {
			Picture a= source.paint(x+xoffset,y+yoffset, p, factor).extract(xoffset, yoffset, width, height);
			a.setCaption(this.getCaption());
			return a;
		}else {
			source=source.paint(x+xoffset,y+yoffset, p, factor);
			return this;
		}
		
	}

	@Override
	public int getXOffset() {
		// TODO Auto-generated method stub
		return xoffset;
	}

	@Override
	public int getYOffset() {
		// TODO Auto-generated method stub
		return yoffset;
	}

	@Override
	public Picture getSource() {

		return source;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint(cx, cy, radius, p, 1.0);
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(immutable) {
			return source.paint(cx,cy, p, factor).extract(xoffset, yoffset, width, height);
		}else {
			source=source.paint(cx,cy, p, factor);
			return this;
		}
		}

	

	
}
