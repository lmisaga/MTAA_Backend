Feature: Restock orders functionality

  #6
  Scenario: Attempting to create restock order for already ordered device
	Users should not be able to create multiple orders for the same device
	Given there is existing device with product name 'Some device'
	And with product code 'some_device_serial_no'
	And has it's quantity below quantity threshold value
	When user creates restock order for 50 items of this device
	Then a single restock order is created in the database
	And no more restock orders for this device can be created

