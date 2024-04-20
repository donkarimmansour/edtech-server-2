@echo off
start cmd /k "mvnw.cmd spring-boot:run"
start cmd /k "cd QQuiz-master && uvicorn main:app --reload"