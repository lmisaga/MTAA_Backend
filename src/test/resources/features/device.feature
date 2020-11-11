Feature: Testing devices

  Scenario: Searching for devices matching provided category
	When user wants to search devices by category SMARTPHONE
	Then list of devices matching provided category name is returned
