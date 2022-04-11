package ca.ntro.app.world2d;

import ca.ntro.app.models.Value;

public abstract class Object2d<GC extends Object,
                               OBJECT2D extends Object2d<GC,OBJECT2D,WORLD2D>,
                               WORLD2D extends  World2d <GC,OBJECT2D,WORLD2D> > implements Value {

	private World2d<GC,OBJECT2D,WORLD2D> world;
	private double topLeftX;
	private double topLeftY;
	private double width;
	private double height;
	private double speedX;
	private double speedY;

	@SuppressWarnings("unchecked")
	public WORLD2D getWorld() {
		return (WORLD2D) world;
	}

	public void setWorld(World2d<GC,OBJECT2D,WORLD2D> world) {
		this.world = world;
	}

	public double getTopLeftX() {
		return topLeftX;
	}

	public void setTopLeftX(double topLeftX) {
		this.topLeftX = topLeftX;
	}

	public double getTopLeftY() {
		return topLeftY;
	}

	public void setTopLeftY(double topLeftY) {
		this.topLeftY = topLeftY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public void onTimePasses(double secondsElapsed) {
		topLeftX += speedX * secondsElapsed;
		topLeftY += speedY * secondsElapsed;
	}
	
	public boolean collidesWith(OBJECT2D other) {
		return other.collidesWith(topLeftX, topLeftY, width, height);
	}

	public boolean collidesWith(double topLeftX, double topLeftY, double width, double height) {
		return collidesOneAxis(this.topLeftX, this.width, topLeftX, width)
				&& collidesOneAxis(this.topLeftY, this.height, topLeftY, height);
	}
	
	private boolean collidesOneAxis(double coord1, double size1, double coord2, double size2) {
		return (coord1 < coord2 && coord1 + size1 >= coord2)
				|| (coord2 <= coord1 && coord2 + size2 >= coord1);
	}
	
	public abstract void draw(GC gc);

	public abstract void initialize();

	public void copyDataFrom(OBJECT2D object2d) {
		setTopLeftX(object2d.getTopLeftX());
		setTopLeftY(object2d.getTopLeftY());
		setWidth(object2d.getWidth());
		setHeight(object2d.getHeight());
		setSpeedX(object2d.getSpeedX());
		setSpeedY(object2d.getSpeedY());
	}

}
