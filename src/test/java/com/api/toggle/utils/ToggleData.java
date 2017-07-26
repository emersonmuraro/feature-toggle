package com.api.toggle.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.toggle.model.ToggleTypeEnum;
import com.api.toggle.model.entity.ServiceApplication;
import com.api.toggle.model.entity.Toggle;

public class ToggleData {
	
	public Toggle createToggleAll(){
		return createToggle("isButtonBlue", ToggleTypeEnum.ALL);
	}
	
	public Toggle createToggle(String name, ToggleTypeEnum type){
		Toggle toggleMock = new Toggle();
		toggleMock.setName(name);
		toggleMock.setType(type);
		toggleMock.setValue(true);
		
		return toggleMock;
	}
	
	public Toggle createToggleListService(String name, ToggleTypeEnum type, List<ServiceApplication> services){
		Toggle toggleMock = new Toggle();
		toggleMock.setName(name);
		toggleMock.setType(type);
		toggleMock.setValue(true);
		toggleMock.setServices(services);
		
		return toggleMock;
	}
	
	public List<ServiceApplication> createServiceApplicationList(){
		ServiceApplication serviceMock = createServiceApp("adm_service", "1.0.0");
		List<ServiceApplication> servicesMock = new ArrayList<ServiceApplication>();
		servicesMock.add(serviceMock);
		return servicesMock;
	}
	
	public ServiceApplication createServiceApp(String serviceId, String serviceVersion){
		return new ServiceApplication(serviceId, serviceVersion);
	}

}
