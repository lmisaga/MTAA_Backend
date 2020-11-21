Feature: Users functionality
  Users need to have secure passwords set, respect the company policy regarding email addresses
  and the system must be designed in a way that the passwords and user ID won't be returned from
  the database upon extraction.

  Scenario: User is attempting to register with correct personal data
	Given company name is 'Sclad'
	When User is providing email with value 'user123@sclad.com'
	And user provides unique username 'exampleUser'
	And user provides password 'example'
	And user provides password check 'example'
	Then registration process should succeed and user ID should be returned

  Scenario: User is attempting to register with incorrect email from domain point of view
	Given company name is 'Sclad'
	When User is providing email with value 'user123@example.com'
	And user provides unique username 'example'
	And user provides password 'example'
	And user provides password check 'example'
	Then registration process should fail and error code saying wrong email domain has been provided

  Scenario: User is attempting to register with insufficient password
	Given company name is 'Sclad'
	When User is providing email with value 'user1234@sclad.com'
	And user provides unique username 'example2'
	And user provides password 'pw'
	And user provides password check 'pw'
	Then registration process should fail and error code saying insufficient password has been provided

	
