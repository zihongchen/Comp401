package a3;

public class Threshold implements PixelTransformation {
	private double threshold;
	
	public Threshold (double threshold) {
		if (threshold<0||threshold>1) {
			throw new IllegalArgumentException("Wrong value");
		}
		this.threshold=threshold;
	}

	@Override
	public Pixel transform(Pixel p) {
		if (p==null) {
			throw new IllegalArgumentException("negative radius hommie");
		}
		if(p.getIntensity()>threshold) {
			return Pixel.WHITE;
			
		}
		
			return Pixel.BLACK;
			
		
		
		
		
	}
}
