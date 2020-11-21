Feature: Device management

  Managing devices is one key functionality of the designated system. We should be able to filter
  devices by certain attributes, with regard to the security and integrity of data.

  Scenario: Searching for devices matching provided category
	Given user 'exampleUser' with password 'exampleUser' is registered
	And user 'exampleUser' is logged in
	When user wants to search devices by category 'notebook'
  	Then list of devices matching provided category 'notebook' is returned

  Scenario: Creating device with correct attributes
	Given user 'exampleAdmin' with password 'exampleAdmin' is registered
	And user 'exampleAdmin' is logged in
	And user has permission to perform device creation
	When user wants to create device with device name 'Some device'
	And device product number is 'some_device_serial_no'
	And device initial quantity is 42
	And device quantity threshold is 4
	Then device is created in the database
	And device object with all attributes is returned
	And returned device object automatically has category "notebook" assigned

  Scenario: Creating device with invalid product code
	Given user 'exampleAdmin' with password 'exampleAdmin' is registered
	And user 'exampleAdmin' is logged in
	And user has permission to perform device creation
	When user wants to create device with device name 'Some other device'
	And device product number is ''
	Then device should not be created
	And error message saying product number is invalid should be displayed

  Scenario: Testing scheduled device stocks task
	Given user 'exampleAdmin' with password 'exampleAdmin' is registered
	And user 'exampleAdmin' is logged in
	And there is configuration for scheduled tasks present
	When a scheduled task for stock check has been executed
	Then list of insufficiently stocked devices is returned
	And system prints out information about these devices

