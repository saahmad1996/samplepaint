package hw5;

import java.awt.Color;

public class Shape {
	private String type;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color color;
	private String strColor;

	public String getStrColor() {

		if (color == Color.RED) {

			strColor = "red";
		} else if (color == Color.GREEN) {

			strColor = "green";

		} else if (color == Color.BLUE) {

			strColor = "blue";

		}else {

			strColor = "black";

		}
		return strColor ;
	}

	public String getType() {
		if (type.length() <= 4 || type == null) {
			return "line";
		} else {
			return type;
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
