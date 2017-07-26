package com.api.toggle.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.api.toggle.model.entity.ServiceApplication;

public class ServiceApplicationDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -664941842410475618L;
	@NotNull
	private String id;
	@NotNull
	private String version;
	
	public ServiceApplicationDto(){}
	
	public ServiceApplicationDto(String id, String version){
		this.id = id;
		this.version = version;
	}
	
	public ServiceApplicationDto(ServiceApplication service){
		this.id = service.getServiceId();
		this.version = service.getServiceVersion();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		if (!(obj instanceof ServiceApplicationDto)) {
			return false;
		}
		ServiceApplicationDto other = (ServiceApplicationDto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}
	
	
	
}
