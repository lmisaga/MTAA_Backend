README aneb Tomas sa nauci spring za 4 minuty aj ked neni opity - ScladApp

1. What do I need to compile this project?
	You will probably just need a proper java SDK (java 8, already discussed), postgreSQL database "sclad" (with created user "sclad" with password "sclad", who has full permissions to the "sclad" database), maven, and that's hopefully it

2. What the hell is going on?
	We defined entities as classes, which have their attribudes annotated with various shit like @NotNull, @Column and stuff. This helps Spring JPA (Java Persistence API) to automatically generate database tables (nice yesss). Since we have an entity, we need a way to handle them - with repositories. Repositories are like your queries (save, getById() etc.). 
	Controllers are used to map selected urls (@RequestMapping with value corresponding to the url you want to map), thus creating a rest api controller for your backend calls. You can also select which http method you want to specify for selected url (method = RequestMethod.GET for get, etc.). 

3. application.properties
	Everything we want to keep as much isolated as possible (never-changing constants, spring configuration values) goes to application.properties. DO NOT TOUCH unless you are 100% sure what you are doing. If not - create a new branch, test there.

4. always generate getter & setter methods please, this is very important in entities (right click -> generate -> getter&setter...) - if you dont provide getter&setter you will get fucked and will debug shit for weeks