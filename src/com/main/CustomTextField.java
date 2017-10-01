package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.entities.GameObject;
import com.game.ID;

public class CustomTextField extends GameObject {
	private Font font;
	private String text,fontStyle;
	private Color textColor,backgroundColor,foregroundColor;
	private int thickness,width,height,fontSize;
	private boolean focus;
	
	public CustomTextField(int x, int y,int width, int height, ID id) {
		super(x, y, 0, 0, id);
		this.width = width;
		this.height = height;
		textColor = Color.white;
		text = "";
		fontStyle = "04B";
		fontSize = 10;
		font = new Font(fontStyle, Font.PLAIN, fontSize);
		thickness = 0;
		backgroundColor = Color.black;
		foregroundColor = Color.black;
		focus = false;
	}
	public void tick() {
			
	}
	public void render(Graphics g) {	
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(foregroundColor);
		g.fillRect(x+thickness, y+thickness, width-(thickness*2), height-(thickness*2));
		g.setColor(textColor);
		g2d.drawString(text, x+2, y+height-2);
		
	//--Debugging purposes only--//
	//g.setColor(Color.red);
	//g.drawRect(x, y, width, height);
	//--------------------------//
	}
	public Rectangle getBounds() {
		return null;
	}
	
	
	public boolean getFocus() {
		return focus;
	}
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	public String getFontStyle() {
		return fontStyle;
	}
	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public Color getTextColor() {
		return textColor;
	}
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getForegroundColor() {
		return foregroundColor;
	}
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
}
