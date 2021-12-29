# Odyssey Energy Solutions - Java Coding Exercise 
This is coding exercise is used as a basis for discussion during an in-person interview.

### The context
Users of Odyssey's (imaginary) quiz-taking web application submit answers to a quiz. The backend needs to determine how many answers were correct and how many were wrong, and save those counts to a database table.

### Your mission
1. Clone this repo
2. Update the project to handle a POST request to a URL path `/answers/<ANY_USERNAME>` with a JSON body containing the user's answers to the 3 questions. Assume the frontend has sent the request; you do not build the frontend.
3. An example JSON body is found in `src/test/resources/test_answers.json`
4. Your result should compare the answers to the correct answers located in `src/main/resources/quiz/correct_answers.json`.
5. After determining the count of correct answers and wrong answers, save a QuizResult record to the QUIZ_RESULT table in the H2 database.

6. Commit your changes and push them to a new public cloud repository of your choosing (github, gitlab, bitbucket, etc.)
7. Send a link to the repo in a reply to the email you received from Odyssey.

### Expected result
An Odyssey engineer should be able to pull your public repo and start the application using Gradle's `bootRun` command. They should then be able to POST to `http://localhost:8080/answers/odysseyuser` with a JSON body such as 
```
{
  "question1": "Pluto",
  "question2": true,
  "question3": 3
}
```
Afterwards they should be able to use the H2 console at `http://localhost:8080/h2-console`, using the JDBC URL logged when the app started and the default credentials of username 'sa' and blank password to login to the DB. They'll query the DB to verify a record was created in the QUIZ_RESULT table with the following data (based on the request above):
```
ID: <auto generated>
QUIZ_USER: odysseyuser
QUIZ_DATE: <auto generated>
CORRECT_ANSWERS: 1
WRONG_ANSWERS: 2
```
 
### Initial setup
- This project is built using Gradle and some Spring Boot libraries based on Java 11.
- An in-memory H2 DB is setup by default, creating a QUIZ_RESULT table.
- The application already has enough pre-defined structure that it will start by running `./gradlew bootRun` (Mac/Linux)

### Additional notes
- Do not receive help from any other humans. Using online documentation is acceptable.
- There's a variety of approaches to complete this task. Choose your preferred approach, and do whatever you want to make it work (e.g. import new libraries). 
- Be prepared to talk about why you chose your approach, possible alternative approaches, how to test it, and future improvements. 
- This repo is known to work with a Mac/Linux environment. It should also work within a Windows environment by running `gradlew.bat bootRun`. Contact us if that is not working. 

Good luck! 
