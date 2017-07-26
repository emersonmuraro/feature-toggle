package com.api.toggle.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.api.toggle.model.entity.Toggle;

public class ToggleDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2280264796674283008L;
	@NotNull
	protected String name;
	@NotNull
	protected Boolean value;
	
	public ToggleDto(){
	}
	
	public ToggleDto(String name, Boolean value){
		this.name = name;
		this.value = value;
	}
	
	public ToggleDto(Toggle toggle){
		this.name = toggle.getName();
		this.value = toggle.getValue();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public Boolean getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Boolean value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ToggleDto)) {
			return false;
		}
		ToggleDto other = (ToggleDto) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
