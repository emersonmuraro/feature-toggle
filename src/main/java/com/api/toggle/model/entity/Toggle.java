package com.api.toggle.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.api.toggle.model.ToggleTypeEnum;

@Entity
public class Toggle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2754412066615648224L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy = "uuid")
//	@Column(name = "uuid", unique = true)
	private Long id;
	@Column(name="name", unique=true)
	private String name;
	private Boolean value;
	private ToggleTypeEnum type;
//	@OneToMany(targetEntity=ServiceApplication.class, cascade=CascadeType.ALL)
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "toggle_service", joinColumns = @JoinColumn(name = "toggle_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "service_app_id", referencedColumnName = "id"))
	private List<ServiceApplication> serviceApplicationList;
	
	public Toggle(){
	}
	
	public Toggle(String name, Boolean value, ToggleTypeEnum type){
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the type
	 */
	public ToggleTypeEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ToggleTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the services
	 */
	public List<ServiceApplication> getServices() {
		return serviceApplicationList;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<ServiceApplication> services) {
		this.serviceApplicationList = services;
	}
}
