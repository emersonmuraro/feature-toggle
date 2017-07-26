package com.api.toggle.coverter;

import java.util.ArrayList;
import java.util.List;

import com.api.toggle.model.dto.ServiceApplicationDto;
import com.api.toggle.model.entity.ServiceApplication;

/**
 * Converter entity to DTO
 * @author emuraro
 *
 */
public class ConverterToDto {
	
	/**
	 * Converter a List of ServiceApplication to a List of DTOs 
	 * @param services
	 * @return
	 */
	public static List<ServiceApplicationDto> converterServiceListToDtoList(List<ServiceApplication> services){
		if (services == null || services.isEmpty()){
			return null;
		}
		List<ServiceApplicationDto> servicesResult = new ArrayList<ServiceApplicationDto>();
		for (ServiceApplication serviceApplication : services) {
			servicesResult.add(new ServiceApplicationDto(serviceApplication));
		}
		return servicesResult;
	}
}
