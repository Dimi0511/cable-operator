package com.operator.enums;

public enum Category {

	ON_AIR("On air Channels"), SPORT("Sport Channels"), SCIENCE("Scientific channels"), MOVIE("Movie channels"),
	MUSIC("Music channels"), KID("Kid channels");

	public final String label;

	private Category(String label) {
		this.label = label; 
	}

	public String getLabel() {
		return label;
	}

}
