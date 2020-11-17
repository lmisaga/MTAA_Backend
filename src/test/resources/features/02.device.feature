Feature: Device management

  Managing devices is one key functionality of the designated system. We should be able to filter
  devices by certain attributes, with regard to the security and integrity of data.

  #4
  Scenario: Searching for devices matching provided category
  	Since users are required to be logged in in order to browse the branch database, we initially check
	if the user is logged in, and then we attempt to search for devices matching the provided category.

	Given user 'exampleUser' with password 'exampleUser' is registered
	And user 'exampleUser' is logged in
	When user wants to search devices by category 'notebook'
  	Then list of devices matching provided category 'notebook' is returned
  #10
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
  #7
  Scenario: Creating device with invalid product code
	Given user 'exampleAdmin' with password 'exampleAdmin' is registered
	And user 'exampleAdmin' is logged in
	And user has permission to perform device creation
	When user wants to create device with device name 'Some other device'
	And device product number is ''
	Then device should not be created
	And error message saying product number is invalid should be displayed

