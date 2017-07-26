package com.api.toggle.repository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.api.toggle.model.ToggleTypeEnum;
import com.api.toggle.model.entity.ServiceApplication;
import com.api.toggle.model.entity.Toggle;
import com.api.toggle.utils.ToggleData;


@RunWith(SpringJUnit4ClassRunner.class)
public class ToggleRepositoryTest{
	
	@InjectMocks
	private ToggleRepository toggleRepositoryMock;
	
	@Mock
	private SessionFactory sessionFactoryMock;
	
	@Mock
	private Session sessionMock;
	
	@Mock
	private Query queryMock;
	
	private ToggleData dataBuild;
	
	@Before
    public void setup() throws Exception {
		dataBuild = new ToggleData(); 
    }
	
	@Test
	public void testPersitToggleSuccess() {
		Toggle toggleMock = dataBuild.createToggleAll();
		
		when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
		
		toggleRepositoryMock.saveToggle(toggleMock);
		verify(sessionMock, times(1)).persist(toggleMock);
	}
	
	@Test
	public void testDeleteToggleSuccess() {
		Toggle toggleMock = dataBuild.createToggleAll();
		
		when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
		
		toggleRepositoryMock.deleteToggle(toggleMock);
		verify(sessionMock, times(1)).delete(toggleMock);
	}
	
	@Test
	public void testUpdateToggleSuccess() {
		Toggle toggleMock = dataBuild.createToggleAll();
		
		when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
		
		toggleRepositoryMock.updateToggle(toggleMock);
		verify(sessionMock, times(1)).merge(toggleMock);
	}
	
	@Test
	public void testListAllTogglesByIdVersionSuccess() {
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
		
		when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
		when(sessionMock.createQuery(any(String.class))).thenReturn(queryMock);
		when(queryMock.list()).thenReturn(toggles);
		
		List<Toggle> togglesResult = toggleRepositoryMock.listAllToggles(service);
		assertEquals(toggles,togglesResult);
	}
	
	@Test
	public void testListAllTogglesLessSpecificSuccess() {
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
		
		when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
		when(sessionMock.createQuery(any(String.class))).thenReturn(queryMock);
		when(queryMock.list()).thenReturn(toggles);
		
		ServiceApplication serviceADM = services.get(0);
		List<Toggle> togglesResult = toggleRepositoryMock.listAllToggles(serviceADM);
		toggles.remove(toggleMock);
		assertEquals(toggles,togglesResult);
	}
	
	@Test
	public void testFindToggleByNameSuccess(){
		String name = "toggle3";
		Toggle toggleMock = dataBuild.createToggle(name, ToggleTypeEnum.ALL);
		
		when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
		when(sessionMock.createQuery(any(String.class))).thenReturn(queryMock);
		when(queryMock.uniqueResult()).thenReturn(toggleMock);
		
		Toggle toggleResult = toggleRepositoryMock.findToggleByName(name);
		assertEquals(toggleMock,toggleResult);
	}
	
	@Test
	public void testFindToggleByNameNullValue(){
		Toggle toggleResult = toggleRepositoryMock.findToggleByName("");
		assertNull(toggleResult);
	}

}
