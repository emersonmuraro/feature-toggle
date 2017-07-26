package com.api.toggle.model.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.api.toggle.coverter.ConverterToDto;
import com.api.toggle.model.entity.ServiceApplication;
import com.api.toggle.model.entity.Toggle;

public class OverriddenToggleDto extends ToggleDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4834854482972822858L;
	private List<ServiceApplicationDto> overriddenServices;
	private ServiceApplicationDto requestedService;
	
	public OverriddenToggleDto(){
	}
	
	public OverriddenToggleDto(String name, Boolean value, List<ServiceApplicationDto> overriddenServices){
		super(name, value);
		this.overriddenServices = overriddenServices;
	}
	
	public OverriddenToggleDto(Toggle toggle, ServiceApplication requestedServiceEntity){
		super(toggle.getName(), toggle.getValue());
		this.overriddenServices = ConverterToDto.converterServiceListToDtoList(toggle.getServices());
		this.requestedService = new ServiceApplicationDto(requestedServiceEntity);
	}
	
	public OverriddenToggleDto(Toggle toggle){
		super(toggle.getName(), toggle.getValue());
		this.overriddenServices = ConverterToDto.converterServiceListToDtoList(toggle.getServices());
	}

	/**
	 * @return the services
	 */
	public List<ServiceApplicationDto> getOverriddenServices() {
		return overriddenServices;
	}

	/**
	 * @param services the services to set
	 */
	public void setOverriddenServices(List<ServiceApplicationDto> overriddenServices) {
		this.overriddenServices = overriddenServices;
	}
	
	@Override
	public Boolean getValue(){
		if (requestedService != null){
			Set<ServiceApplicationDto> setService = new HashSet<ServiceApplicationDto>(overriddenServices);
			return setService.contains(requestedService)? !value:value;
		}else{
			return value;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((overriddenServices == null) ? 0 : overriddenServices.hashCode());
		result = prime * result + ((requestedService == null) ? 0 : requestedService.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof OverriddenToggleDto)) {
			return false;
		}
		OverriddenToggleDto other = (OverriddenToggleDto) obj;
		if (overriddenServices == null) {
			if (other.overriddenServices != null) {
				return false;
			}
		} else if (!overriddenServices.equals(other.overriddenServices)) {
			return false;
		}
		if (requestedService == null) {
			if (other.requestedService != null) {
				return false;
			}
		} else if (!requestedService.equals(other.requestedService)) {
			return false;
		}
		return true;
	}
	
	
}
