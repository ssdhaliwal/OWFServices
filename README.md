All services used by the widgets

Preferences Service
  - store text data on service location
  - private/non-shared information
  - encrypted store
    - guid, type, datapacket
    - file: guid.type
  - update WEB-INF/app.config properties to meet your system deployment config
  - update WEB-INF/log4j2Config.xml propeties for logging for the service
  - all calls are POST
  - "key" is used as suffix with server key to create a unique key
  
  - searching
	- https://localhost:7443/UserPreferenceService/rs/preferences/search/03-4404-53405434/test
	- request body
		{
			"key":"my_password",
			"message":""
		}
	- response
		original message provided in store call
		
  - storing
	- https://localhost:7443/UserPreferenceService/rs/preferences/store/03-4404-53405434/test
	- request body
		{
			"key":"my_password",
			"message":"my_data_to_store"
		}
	- response body
		{"status": "SUCCESS","message": "params (03-4404-53405434, test)","result": "true"}
		{"status": "ERROR","message": "params (03-4404-53405434, test)","result": "false
		
  - removing
	- https://localhost:7443/UserPreferenceService/rs/preferences/remove/03-4404-53405434/test
	- request body
		{
			"key":"my_password",
			"message":""
		}
	- response body
		{"status": "SUCCESS","message": "params (03-4404-53405434, test)","result": "true"}
		{"status": "ERROR","message": "params (03-4404-53405434, test)","result": "false

Key Store Service
  - store key-value(s) in database table
  - public/shared information with option to restrict view
  - user/role/group/public views
  - key-value pair store
    - key, occurance, value, restriction

Database Service
  - action, type, filters
    - action = crud
    - type = mapped capability
    - filters = where clause
