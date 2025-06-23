@echo off
call mvn clean install
docker compose up --build -d
pause