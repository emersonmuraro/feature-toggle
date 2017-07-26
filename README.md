# feature-toggle
Custom feature toggle REST API for services and applications

The API consist of these services:
- Add genereal toggle: include a toggle with a unique name, that has the same value to all services/application
host: http://localhost:8080/toggle/general
method: POST
Body:
{
    "name": "toggle_general1",
    "value": true
}

- Add a overridden toggle: include a toggle with a unique name, that has the same value to all services/application, excepted for a list of services/application, informed, that they have the oposit value.
host: http://localhost:8080/toggle/overridden
method: POST
Body:
{
    "name": "toggle_overridden1",
    "value": true,
    "overriddenServices":[
    	{
    		"id":"service2",
    		"version":"1.1.0"
    	},
    	{
    		"id":"service3",
    		"version":"1.0.0"
    	}
    	]
}

- Add a specific toggle: include a toggle with a unique name, that is used only for a list of services/application, with the same value.
host: http://localhost:8080/toggle/specific
method: POST
Body:
{
    "name": "toggle_specific2",
    "value": true,
    "services":[
    	{
    		"id":"service1",
    		"version":"1.0.0"
    	},
    	{
    		"id":"service2",
    		"version":"1.1.0"
    	}
    	]
}

- Delete a specific toggle: it is possible to delete any kind of toggle passing the toggle name.
host: http://localhost:8080/toggle/{toggle_name}
method: DELETE

- Update a toggle value: it is possible to update the value of a toggle, of any kind and the values will respect the role of each toggle, for example for a overridden toggle if the value chage the overridden value will be the oposit of the new value (the same role of the overridden toggle). 
host: http://localhost:8080/toggle
method: PUT
Body:
{
    "name": "toggle_general1",
    "value": true
}

- Get all toggles of a service/application: it is possible to recovere al the toggles of service/application passing the id and version of the service/application (all the fields are String)
host: host: http://localhost:8080/toggle/all?id=feature_toggle_1&version=1.0.0
method: GET
Query parameters:
- id: text to identify a service/application
- version: the version number of the service/applicatio (it is a text)
Response:
[{
    "name": "toggle_general1",
    "value": true
},
{
    "name": "toggle_specifc1",
    "value": false
}]

- Update a Specific or Overriden list of services, or any other values: to update the values the manager should call the get of each toggle to get the complete structure. After, he should delete and add the toggle with the new configuration. Follow the service to get each toggle:
	- Get a specific toggle: recovery a toggle with a unique name.
		host: http://localhost:8080/toggle/specific?toggleName=toggle_specific1
		method: POST
		Query parameters: 
		- toggleName"the name of the toggle
		Response:
		{
		    "name": "toggle_specific2",
		    "value": true,
		    "services":[
		    	{
		    		"id":"service1",
		    		"version":"1.0.0"
		    	},
		    	{
		    		"id":"service2",
		    		"version":"1.1.0"
		    	}
		    ]
		}
	- Get a overridden toggle: recovery a toggle with a unique name.
		host: http://localhost:8080/toggle/overridden?toggleName=toggle_specific1
		method: POST
		Query parameters: 
		- toggleName"the name of the toggle
		Response:
		{
		    "name": "toggle_overridden1",
		    "value": true,
		    "overriddenServices":[
		    	{
		    		"id":"service2",
		    		"version":"1.1.0"
		    	},
		    	{
		    		"id":"service3",
		    		"version":"1.0.0"
		    	}
		    ]
		}


