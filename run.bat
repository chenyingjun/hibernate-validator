@echo off
rem Change to current directory
cd /d %~dp0

cls

:START
cls
call mvn clean spring-boot:run
echo.
rem set /p var=Press ENTER to restart, OR click on the right top red button of the window.
set /p var=Press ENTER to restart, OR click on the right top red button of the window.
GOTO :START

:ERR
echo.
echo. APP Project Installation has failed. See above for details.
pause>nul