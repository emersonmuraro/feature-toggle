package com.api.toggle.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ServiceApplication implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7828468117095772572L;
	@Id
	private String id;
	private String serviceId;
	private String serviceVersion;
	@ManyToMany(mappedBy="serviceApplicationList")
	private List<Toggle> toggles;
	
	public ServiceApplication(){}
	
	public ServiceApplication(String serviceId, String serviceVersion){
		this.serviceId = serviceId;
		this.serviceVersion = serviceVersion;
		this.id = serviceId+serviceVersion;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @return the serviceVersion
	 */
	public String getServiceVersion() {
		return serviceVersion;
	}

	/**
	 * @return the toggles
	 */
	public List<Toggle> getToggles() {
		return toggles;
	}

	/**
	 * @param toggles the toggles to set
	 */
	public void setToggles(List<Toggle> toggles) {
		this.toggles = toggles;
	}
}
