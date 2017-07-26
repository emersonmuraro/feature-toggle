package com.api.toggle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.toggle.exception.BusinessToggleNotFoundException;
import com.api.toggle.model.dto.ErrorDto;
import com.api.toggle.model.dto.OverriddenToggleDto;
import com.api.toggle.model.dto.ServiceApplicationDto;
import com.api.toggle.model.dto.SpecificToggleDto;
import com.api.toggle.model.dto.ToggleDto;
import com.api.toggle.service.ToggleRouterService;

@RestController
@RequestMapping("/toggle")
public class ToggleController {
	
	@Autowired
	private ToggleRouterService toggleRouterService;
	
	@RequestMapping(path="/general", method = RequestMethod.POST)
	public ResponseEntity<?> addGeneralToggle(@Valid @RequestBody ToggleDto toggleDto){
		toggleRouterService.addGeneralToggle(toggleDto);
		return new ResponseEntity<ToggleDto>(toggleDto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/{toggle_name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGeneralToggle(@PathVariable(name="toggle_name", required=true) String name){
		try {
			ToggleDto toggleDto = new ToggleDto();
			toggleDto.setName(name);
			toggleRouterService.deleteToggle(toggleDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessToggleNotFoundException e) {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensagem(e.getMessage());
			return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateGeneralToggleValue(@Valid @RequestBody ToggleDto toggleDto){
		try {
			toggleRouterService.updateToggle(toggleDto);
			return new ResponseEntity<ToggleDto>(toggleDto, HttpStatus.OK);
		} catch (BusinessToggleNotFoundException e) {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensagem(e.getMessage());
			return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.OK);
		}
	}

	@RequestMapping(path="/specific", method = RequestMethod.POST)
	public ResponseEntity<?> addSpecificToggle(@Valid @RequestBody SpecificToggleDto specificToggleDto){
		toggleRouterService.addSpecificToggle(specificToggleDto);
		return new ResponseEntity<SpecificToggleDto>(specificToggleDto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/specific", method = RequestMethod.GET)
	public ResponseEntity<?> getSpecificToggle(@RequestParam(name="toggleName", required=true) String toggleName){
		
		try {
			SpecificToggleDto specificToggleDto = (SpecificToggleDto)toggleRouterService.getToggle(toggleName);
			return new ResponseEntity<SpecificToggleDto>(specificToggleDto, HttpStatus.OK);
		} catch (Exception e) {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensagem(e.getMessage());
			return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@RequestMapping(path="/overridden", method = RequestMethod.POST)
	public ResponseEntity<?> addOverriddenToggle(@Valid @RequestBody OverriddenToggleDto overriddenToggleDto){
		toggleRouterService.addOverriddenToggle(overriddenToggleDto);
		return new ResponseEntity<OverriddenToggleDto>(overriddenToggleDto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/overridden", method = RequestMethod.GET)
	public ResponseEntity<?> getOverriddenToggle(@RequestParam(name="toggleName", required=true) String toggleName){
		
		try {
			OverriddenToggleDto overriddenToggleDto = (OverriddenToggleDto)toggleRouterService.getToggle(toggleName);
			return new ResponseEntity<OverriddenToggleDto>(overriddenToggleDto, HttpStatus.OK);
		} catch (Exception e) {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensagem(e.getMessage());
			return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path="/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllToggle(@RequestParam(name="id", required=true) String serviceId,
			@RequestParam(name="version", required=true) String serviceVersion){
		try {
			ServiceApplicationDto serviceApplicationDto = new ServiceApplicationDto(serviceId, serviceVersion);
			List<ToggleDto> toggles = toggleRouterService.getAllServiceToggle(serviceApplicationDto);
			return new ResponseEntity<List<ToggleDto>>(toggles, HttpStatus.OK);
		} catch (Exception e) {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMensagem(e.getMessage());
			return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
