This program was developed as a kata exercise.
This Java SpringBoot program expose a simple REST service API to manage a Tennis Game.

To test services, main method in TennisGameBootApplication Class has to be launched.
REST services will be deployed in Jetty container on 8080 port.

Example of call of services below (with player1):

- http://localhost:8080/gameScore/player1
- http://localhost:8080/winPoint/player1
- http://localhost:8080/winner
- http://localhost:8080/players
- http://localhost:8080/initGame
- http://localhost:8080//matchScore

This program was developed with TDD tests to cover UserStories use cases.

FYI:
Many enhancements can be added like:
- JUnit test code better coverage
- Exception management
- Refacto and code enhancement