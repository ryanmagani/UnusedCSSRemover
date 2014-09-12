#!/bin/bash
# Simple script to run the perl script with test files and diff the results against expected results
declare -a cssFiles=("comment-test.css" "empty-selector-test.css" "general-test.css" "newline-test.css" "special-cases-test.css")
declare -a txtFiles=("empty.txt" "empty.txt" "general-test.txt" "empty.txt" "special-cases-test.txt")
declare -a cssFilesExpected=("comment-test-expected.css" "empty-selector-test-expected.css" "general-test-expected.css" "newline-test-expected.css" "special-cases-test-expected.css")
declare -a cssFilesActual=("comment-test-actual.css" "empty-selector-test-actual.css" "general-test-actual.css" "newline-test-actual.css" "special-cases-test-actual.css")
declare -r path="test/"
declare -i START=0
declare -i END="${#cssFiles[@]}"

for ((i=$START; i<$END; i++))
do
	perl unused_css_remover.pl "$path${cssFiles[$i]}" "$path${txtFiles[$i]}" "$path${cssFilesActual[$i]}"
	diff "$path${cssFilesActual[$i]}" "$path${cssFilesExpected[$i]}"
done