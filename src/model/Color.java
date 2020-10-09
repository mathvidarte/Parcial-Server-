package model;

public class Color {
	
	String color;
    String type = "color";

    public Color (String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
