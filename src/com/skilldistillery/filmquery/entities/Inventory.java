package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Inventory {
	private int iD;
	private String media_condition;

	public Inventory() {
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getMedia_condition() {
		return media_condition;
	}

	public void setMedia_condition(String media_condition) {
		this.media_condition = media_condition;
	}

	public Inventory(int iD, String media_condition) {
		super();
		this.iD = iD;
		this.media_condition = media_condition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iD;
		result = prime * result + ((media_condition == null) ? 0 : media_condition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (iD != other.iD)
			return false;
		if (media_condition == null) {
			if (other.media_condition != null)
				return false;
		} else if (!media_condition.equals(other.media_condition))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(iD);
		builder.append(" (");
		builder.append(media_condition);
		builder.append(")");
		return builder.toString();
	}

}
