Intro:
--
Hi Aspen Capital Dev team. My name is Raul Rodriguez, I am a developer within the asset managmenet division of my firm.
I currently work on developing and supporting an investment platform used by portfolio managers globally. I am looking
for opportunities in a cohesive team with business exposure. Most importantly, I want to be exposed to the latest tech
stacks. Although my experience has been primarily in backend development, I enjoy picking up new technologies and learning.
Currently, I am working on an Angular project to become experienced in the "full-stack".

Enclosed you will find my War Game implementation. I've had a busy work week and will also have a busy weekend coming up,
so I was only able to dedicate ~9 hours to this project over two days. I will therefore use this document to explain my
implementation.


Testing:
--
- I usually implement using TDD at work. Given the time constraint, I did not use TDD for this project. TDD would have
significantly increased the amount and code in this submission. I have included some simple tests for the purpose of
exhibiting testing.
 
How To Run:
--
- Step 1: Please wake up the Repl project by going to https://repl.it/@rrodri/war-game.
    - Sometimes the Repl site package manager needs to re-download dependencies and they do not completely load in time for the build to succeed. Sometimes you need to "Start" twice or thrice in order to allow all dependencies to properly download. This issue is not with the build -- it's an issue with Repl.it.
- Step 2: Once the repl has been woken up the project's Swagger link can be accessed via https://war-game.rrodri.repl.co
or https://war-game.rrodri.repl.co/swagger-ui.html.

Alternatively, you can manually load the project. The command for running this Maven project is:
  mvn package && java -jar target/war-1.0.jar.

Implementation:
--
- This project uses Spring-Boot for the REST service and an in-memory H2 database (instructions stated no preferance for
the type of DB used). The database is populated using Hibernate JPA to generate DB from Entity and fetches/saves using a
JPA Repository.
- I opted for a generic implementation to make this project easily extensible in case more games are introduced.
- Instead of a singleton game object, there is a factory which generates a new game object every time the endpoint is hit.
This prevents the same state being mutated simultaneously by multiple threads. If we wanted to reuse the same game object
we could use locks on the object so that only one game is played at once, or create a non-blocking queue to process games
sequentially. These implementations would both increase complexity and are overkill, so on-demand throwaway objects seemed
the way to go.


Future implementation:
--
- I did not have a UI interface for this project. Given more time, I could have spun up a simple UI showing the game
unfolding. The REST service does produce JSON, so it makes it easy to parse into JS for a UI.
- For a production-grade implementation, we obviously would not use an in-memory database. I have a preference for MySQL,
but there is any number of implementations which would work here.
- A full implementation would also have test, dev, uat, prod environments.
- For more complex SQL queries, perhaps a more robust DAO implementation will be required with transactional behavior.
The requirements for DB in this assignment are quite simple so I did not think it necessary to do this.

Game:
--
The game has been modified slightly to shorten the number of rounds played. When cards are put down on the table, they are
shuffled prior to being placed at the bottom of the winner's hand when war is won. This increased randomness removes any
win-loss cycles, not removing these cycles can cause the game to potentially last forever (or at least much longer).

