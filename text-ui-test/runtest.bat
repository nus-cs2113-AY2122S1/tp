@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

xcopy duke.jar ..\..\text-ui-test

cd ..\..\text-ui-test

java -jar duke.jar < input.txt > ACTUAL.TXT

FC ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!

del data\contacts.txt
del data\me.txt
del duke.jar