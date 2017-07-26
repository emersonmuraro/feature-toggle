package com.api.toggle.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.api.toggle.model.ToggleTypeEnum;
import com.api.toggle.model.dto.OverriddenToggleDto;
import com.api.toggle.model.dto.ServiceApplicationDto;
import com.api.toggle.model.dto.SpecificToggleDto;
import com.api.toggle.model.dto.ToggleDto;
import com.api.toggle.model.entity.ServiceApplication;
import com.api.toggle.model.entity.Toggle;
import com.api.toggle.repository.ToggleRepository;
import com.api.toggle.utils.ToggleData;


@RunWith(SpringJUnit4ClassRunner.class)
public class ToggleRouterServiceTest{
	
	@InjectMocks
	private ToggleRouterService toggleRouterServiceMock;
	
	@Mock
	private ToggleRepository toggleRepositoryMock;
	
	private ToggleData dataBuild;
	
	@Before
    public void setup() throws Exception {
		dataBuild = new ToggleData(); 
    }
	
	@Test
	public void testGetAllToggleFromServiceWithSpecificSucess() throws Exception{
		List<Toggle> toggles = new ArrayList<Toggle>();
		List<ServiceApplication> services = dataBuild.createServiceApplicationList();
		ServiceApplication service = dataBuild.createServiceApp("service1", "1.0.1");
		services.add(service);
		List<ServiceApplication> services2 = dataBuild.createServiceApplicationList();
		Toggle toggleMock = dataBuild.createToggleListService("toggle1", ToggleTypeEnum.SPECIFIC, services);
		Toggle toggleMock2 = dataBuild.createToggleListService("toggle2", ToggleTypeEnum.OVERRIDDEN, services2);
		Toggle toggleMock3 = dataBuild.createToggle("toggle3", ToggleTypeEnum.ALL);
		toggles.add(toggleMock);
		toggles.add(toggleMock2);
		toggles.add(toggleMock3);
		
		when(toggleRepositoryMock.listAllToggles(any(ServiceApplication.class))).thenReturn(toggles);
		
		List<ToggleDto> togglesResult = toggleRouterServiceMock.getAllServiceToggle(new ServiceApplicationDto(service));
		assertNotNull(togglesResult);
		assertTrue(togglesResult.size() == 3);
		assertTrue(togglesResult.get(0).getValue());
		assertTrue(togglesResult.get(1).getValue());
		assertTrue(togglesResult.get(2).getValue());
	}
	
	@Test
	public void testGetAllToggleFromServiceWithOverriddenSucess() throws Exception{
		List<Toggle> toggles = new ArrayList<Toggle>();
		List<ServiceApplication> services = dataBuild.createServiceApplicationList();
		ServiceApplication service = dataBuild.createServiceApp("service1", "1.0.1");
		services.add(service);
		List<ServiceApplication> services2 = dataBuild.createServiceApplicationList();
		Toggle toggleMock = dataBuild.createToggleListService("toggle1", ToggleTypeEnum.SPECIFIC, services);
		Toggle toggleMock2 = dataBuild.createToggleListService("toggle2", ToggleTypeEnum.OVERRIDDEN, services2);
		Toggle toggleMock3 = dataBuild.createToggle("toggle3", ToggleTypeEnum.ALL);
		toggles.add(toggleMock);
		toggles.add(toggleMock2);
		toggles.add(toggleMock3);
		
		when(toggleRepositoryMock.listAllToggles(any(ServiceApplication.class))).thenReturn(toggles);
		
		List<ToggleDto> togglesResult = toggleRouterServiceMock.getAllServiceToggle(new ServiceApplicationDto(services2.get(0)));
		assertNotNull(togglesResult);
		assertTrue(togglesResult.size() == 3);
		assertTrue(togglesResult.get(0).getValue());
		assertFalse(togglesResult.get(1).getValue());
		assertTrue(togglesResult.get(2).getValue());
	}
	
	@Test
	public void testGetToggleByName() throws Exception{
		Toggle toggleMock = dataBuild.createToggle("toggle3", ToggleTypeEnum.ALL);
		
		when(toggleRepositoryMock.findToggleByName(any(String.class))).thenReturn(toggleMock);
		ToggleDto toggleDto = new ToggleDto(toggleMock);
		
		ToggleDto toggleResult = (ToggleDto)toggleRouterServiceMock.getToggle("toggle3");
		assertNotNull(toggleResult);
		assertEquals(toggleDto, toggleResult);
	}
	
	@Test
	public void testGetToggleSpecificByName() throws Exception{
		Toggle toggleMock = dataBuild.createToggleListService("toggle1", ToggleTypeEnum.SPECIFIC, null);
		
		when(toggleRepositoryMock.findToggleByName(any(String.class))).thenReturn(toggleMock);
		SpecificToggleDto specificToggleDto = new SpecificToggleDto(toggleMock);
		
		SpecificToggleDto toggleResult = (SpecificToggleDto)toggleRouterServiceMock.getToggle("toggle1");
		assertNotNull(toggleResult);
		assertEquals(specificToggleDto, toggleResult);
	}
	
	@Test
	public void testGetToggleOverriddenByName() throws Exception{
		List<ServiceApplication> services = dataBuild.createServiceApplicationList();
		ServiceApplication service = dataBuild.createServiceApp("service1", "1.0.1");
		services.add(service);
		Toggle toggleMock = dataBuild.createToggleListService("toggle2", ToggleTypeEnum.OVERRIDDEN, services);
		
		when(toggleRepositoryMock.findToggleByName(any(String.class))).thenReturn(toggleMock);
		OverriddenToggleDto overriddenToggleDto = new OverriddenToggleDto(toggleMock);
		
		OverriddenToggleDto toggleResult = (OverriddenToggleDto)toggleRouterServiceMock.getToggle("toggle2");
		assertNotNull(toggleResult);
		assertEquals(overriddenToggleDto, toggleResult);
	}
}
