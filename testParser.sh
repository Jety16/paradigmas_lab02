#!/usr/bin/bash

echo("Compiling the parser")

javac -classpath dependency/*:. -d target -sourcepath . src/parser/SubscriptionParser.java src/parser/GeneralParser.java test/java/SubscriptionParserTest.java
