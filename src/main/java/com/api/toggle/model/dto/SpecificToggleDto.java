package com.api.toggle.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.api.toggle.coverter.ConverterToDto;
import com.api.toggle.model.entity.Toggle;

public class SpecificToggleDto extends ToggleDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2068419124166843910L;
	@NotNull
	protected List<ServiceApplicationDto> services;
	
	public SpecificToggleDto(){
	}
	
	public SpecificToggleDto(Toggle toggle){
		super(toggle.getName(), toggle.getValue());
		this.services = ConverterToDto.converterServiceListToDtoList(toggle.getServices());
	}
	
	public SpecificToggleDto(String name, Boolean value, List<ServiceApplicationDto> services){
		super(name, value);
		this.services = services;
	}

	/**
	 * @return the services
	 */
	public List<ServiceApplicationDto> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<ServiceApplicationDto> services) {
		this.services = services;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((services == null) ? 0 : services.hashCode());
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
		if (!(obj instanceof SpecificToggleDto)) {
			return false;
		}
		SpecificToggleDto other = (SpecificToggleDto) obj;
		if (services == null) {
			if (other.services != null) {
				return false;
			}
		} else if (!services.equals(other.services)) {
			return false;
		}
		return true;
	}

	
}
