package com.api.toggle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.toggle.coverter.ConverterToEntity;
import com.api.toggle.exception.BusinessInvalidParameterException;
import com.api.toggle.exception.BusinessToggleNotFoundException;
import com.api.toggle.model.ToggleTypeEnum;
import com.api.toggle.model.dto.OverriddenToggleDto;
import com.api.toggle.model.dto.ServiceApplicationDto;
import com.api.toggle.model.dto.SpecificToggleDto;
import com.api.toggle.model.dto.ToggleDto;
import com.api.toggle.model.entity.ServiceApplication;
import com.api.toggle.model.entity.Toggle;
import com.api.toggle.repository.ToggleRepository;

/**
 * 
 * @author emuraro
 *
 */
@Service
public class ToggleRouterService {
	
	@Autowired
	private ToggleRepository toggleRepository;
	
	/**
	 * 
	 * @param serviceDto
	 * @return
	 * @throws Exception
	 */
	public List<ToggleDto> getAllServiceToggle(ServiceApplicationDto serviceDto)throws Exception{

		if (serviceDto == null){
			throw new Exception();
		}
		List<Toggle> toggles = null;
		ServiceApplication service = new ServiceApplication(serviceDto.getId(), serviceDto.getVersion());
		toggles = toggleRepository.listAllToggles(service);

		return processToggleResult(toggles, service);
	}
	
	private List<ToggleDto> processToggleResult(List<Toggle> toggles, ServiceApplication service){
		if (toggles == null || toggles.isEmpty() || service == null){
			return null;
		}
		List<ToggleDto> toggleResult = new ArrayList<ToggleDto>();
		for (Toggle toggle : toggles) {
			
			ToggleDto toggleDto=null;
			if (ToggleTypeEnum.OVERRIDDEN.equals(toggle.getType())){
				OverriddenToggleDto overriddenToggleDto = new OverriddenToggleDto(toggle, service);
				toggleDto = new ToggleDto(overriddenToggleDto.getName(), overriddenToggleDto.getValue());
			}else{
				toggleDto = new ToggleDto(toggle);
			}
			toggleResult.add(toggleDto);
		}
		
		return toggleResult;
	}
	
	/**
	 * 
	 * @param toggleDto
	 * @return
	 * @throws Exception
	 */
	public Object getToggle(String name)throws Exception{
		if (name.isEmpty()){
			throw new Exception();
		}
		Toggle toggle = toggleRepository.findToggleByName(name);

		return processToggleResultByType(toggle);
	}
	
	private ToggleDto processToggleResultByType(Toggle toggle){
		switch (toggle.getType()) {
		case ALL:
			return new ToggleDto(toggle);
		case SPECIFIC:
			return new SpecificToggleDto(toggle);
		case OVERRIDDEN:
			return new OverriddenToggleDto(toggle);
		default:
			break;
		}

		return null;
	}
	
	
	/**
	 * Register a general toggle
	 * @param toggleDto
	 * @throws BusinessInvalidParameterException
	 */
	public void addGeneralToggle(ToggleDto toggleDto){
		Toggle toggle = new Toggle(toggleDto.getName(), toggleDto.getValue(), ToggleTypeEnum.ALL);
		toggleRepository.saveToggle(toggle);
	}
	
	/**
	 * 
	 * @param toggleDto
	 * @throws BusinessToggleNotFoundException
	 */
	public void deleteToggle(ToggleDto toggleDto)throws BusinessToggleNotFoundException{
		Toggle toggle = toggleRepository.findToggleByName(toggleDto.getName());
		if (toggle == null){
			throw new BusinessToggleNotFoundException("Toggle not found: " + toggleDto.getName()); 
		}
		toggleRepository.deleteToggle(toggle);
	}
	
	/**
	 * Update toggle value
	 * @param toggleDto
	 */
	public void updateToggle(ToggleDto toggleDto)throws BusinessToggleNotFoundException{
		
		Toggle toggle = toggleRepository.findToggleByName(toggleDto.getName());
		if (toggle == null){
			throw new BusinessToggleNotFoundException("Toggle not found: " + toggleDto.getName()); 
		}
		toggle.setValue(toggleDto.getValue());
		toggleRepository.updateToggle(toggle);
	}
	
	/**
	 * Register a toggle used for specific service(s) 
	 * @param specificToggleDto
	 * @throws BusinessInvalidParameterException
	 */
	public void addSpecificToggle(SpecificToggleDto specificToggleDto){
		Toggle toggle = new Toggle(specificToggleDto.getName(), specificToggleDto.getValue(), ToggleTypeEnum.SPECIFIC);
		toggle.setServices(ConverterToEntity.converterServiceListToEntityList(specificToggleDto.getServices()));
		toggleRepository.updateToggle(toggle);
	}
	
	/**
	 * Register a toggle of type overridden, so, the services in the list have the opposite value of the others
	 * @param overriddenToggleDto
	 * @throws BusinessInvalidParameterException
	 */
	public void addOverriddenToggle(OverriddenToggleDto overriddenToggleDto){
		Toggle toggle = new Toggle(overriddenToggleDto.getName(), overriddenToggleDto.getValue(), ToggleTypeEnum.OVERRIDDEN);
		toggle.setServices(ConverterToEntity.converterServiceListToEntityList(overriddenToggleDto.getOverriddenServices()));
		toggleRepository.updateToggle(toggle);
	}
}
