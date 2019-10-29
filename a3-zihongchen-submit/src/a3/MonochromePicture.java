package a3;

public class MonochromePicture implements Picture{
	private int width; 
	private int height; 
	Pixel value;
	
	public MonochromePicture(int width, int height, Pixel value) {
		if (width <= 0 || height <= 0||value==null) {
			throw new IllegalArgumentException("Incorrect Value");
		}
		
		this.width=width;
		this.height=height;
		this.value= value;
		
		
	}
	
	

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
			throw new IllegalArgumentException("x or y are not in range");
		}

		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1||p==null) {
			throw new IllegalArgumentException("x or y are not in range");
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(width,height,value);
		sb.paint(x, y, p);
		return sb;
		
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x > width - 1 || y > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}

		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(width,height,value);
		sb.paint(x, y, p,factor);
		return sb;
		
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {// not validater
		if ( ax > width - 1 || ay > height - 1|| bx > width - 1 || by > height - 1||p==null) {
			throw new IllegalArgumentException("No hommie");
		}
		if(ax<0) ax=0;
		if(ay<0) ay=0;
		if(bx<0) bx=0;
		if(by<0) by=0;
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(width,height,value);
		sb.paint(ax, ay, bx,by,p);
		return sb;

	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if ( ax > width - 1 || ay > height - 1|| bx > width - 1 || by > height - 1||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("No hommie");
		}
		if(ax<0) ax=0;
		if(ay<0) ay=0;
		if(bx<0) bx=0;
		if(by<0) by=0;
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(width,height,value);
		sb.paint(ax, ay, bx,by,p,factor);
		return sb;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius <=0||p==null) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(width,height,value);
		sb.paint(cx, cy, radius,p);
		return sb;

	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius <= 0||p==null||factor<0||factor>1) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		MutablePixelArrayPicture sb=new MutablePixelArrayPicture(width,height,value);
		sb.paint(cx, cy, radius,p,factor);
		return sb;

}
}