#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test

if [ -d "./data" ]
then
    rm -rf ./data
fi

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

if [ -d "./data" ]
then
    rm -rf ./data
fi

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
