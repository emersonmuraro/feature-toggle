package com.api.toggle.coverter;

import java.util.ArrayList;
import java.util.List;

import com.api.toggle.model.dto.ServiceApplicationDto;
import com.api.toggle.model.entity.ServiceApplication;

/**
 * Converter DTO to Entity
 * @author emuraro
 *
 */
public class ConverterToEntity {
	
	/**
	 * Converter a List of ServiceApplication DTO to a List of Entity. 
	 * @param services
	 * @return
	 */
	public static List<ServiceApplication> converterServiceListToEntityList(List<ServiceApplicationDto> servicesDto){
		if (servicesDto == null || servicesDto.isEmpty()){
			return null;
		}
		List<ServiceApplication> servicesResult = new ArrayList<ServiceApplication>();
		for (ServiceApplicationDto serviceApplicationDto : servicesDto) {
			servicesResult.add(new ServiceApplication(serviceApplicationDto.getId(), 
					serviceApplicationDto.getVersion()));
		}
		return servicesResult;
	}
}
