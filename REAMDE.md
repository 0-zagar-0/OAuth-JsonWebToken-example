Hello, a small project with JWT Token implementation and OAuth2.0 authentication.
- To get started, download this project to your computer.
- Register in the Google Cloud service, go to the APIs & Services tab and create a project.
- Add to your project locally in the application.properties file the client-id and client-secret 
  variables that will be provided by Google Cloud. You can still provide variables for GitHub OAuth authentication, remove these variables.
- A small frontend is implemented where you can use this application interactively without 
  using Postman or other utilities.
- The starting point for testing through a browser is http://localhost:8081/home, and then 
  everything will be clear.
- To add a user with the admin role, simply when you register, your email address must start 
  with "admin" and then you will have access to the endpoint with the admin role.
Good luck!!!