# Project 3: Hashtable Experiments

* Author: Benjamin Dustin
* Class: CS321 Section 2
* Semester: Spring '25

## Overview

This program enables a user to experiment with the insertion performance of hashtables using linear probing and double hashing.
While there a couple of supporting classes, HashtableExperiment.java tracks the average number of probes required to insert data
when the hashtable has target load factor.

## Reflection

I feel that most assignments have less room for creativity. However, the solution to this project seemed very open ended. This was mostly
true for the HashtableExperiment class, and less true for the supporting classes. Although I really enjoyed the creative liberty, it also made the 
project harder (for me). I think it was difficult because I reguarly saw my solutions as sub-optimal. There are some large parts of the primary class that
are garbage. However, I had to remind myself of the true purpose of the project.

Initially, I struggled a bit with modifying run-tests.sh. While I did take the class on working with linux, it has been a while. However, the generate-results.sh file was a very helpful example, and I just followed that format. Similarly, I had some initial problems connecting to AWS. However, I was able to generate and add a public key by following the github documentation referenced by the instructions.

## Compiling and Using

The simplest way to compile the project would be to use the following command

    XXXX$ javac HashtableExperiment.java

This will prepare and compile HashtableExperiment.java, and any classes that it depends on.

To run the program, use the following command template

    XXXX$ javac HashtableExperiment < data-source = 1-3 > < load-factor > [< debug-level = 0-2 >] 

where... 
* data-source is an integer that selects the source of the experiments data,
* load-factor is a positive decimal, less than one, load factor that hashtable permformance needs to be evaluated at
* debug level is an optional integer that selects what kind of debug behavior is run by the program. This optional argument defaults to 0

## Results 

### Average Number of Probes Required for Inserting Sequential Date Objects
| Type   | Load Factor = 0.5 | Load Factor = 0.6 | Load Factor = 0.7 | Load Factor = 0.8 | Load Factor = 0.9 | Load Factor = 0.95 | Load Factor = 0.99 |
|--------|-------------------|-------------------|-------------------|-------------------|-------------------|--------------------|--------------------|
| Linear |         1.28      |         1.44      |         1.60      |         1.82      |        2.18       |         2.70       |         5.41       |
| Double |         1.38      |         1.66      |         2.01      |         2.43      |        3.16       |         3.77       |         5.39       |


### Average Number of Probes Required for Inserting Random Integer Objects
| Type   | Load Factor = 0.5 | Load Factor = 0.6 | Load Factor = 0.7 | Load Factor = 0.8 | Load Factor = 0.9 | Load Factor = 0.95 | Load Factor = 0.99 |
|--------|-------------------|-------------------|-------------------|-------------------|-------------------|--------------------|--------------------|
| Linear |         1.51      |         1.77      |         2.20      |         3.08      |        5.89       |         11.85      |         44.79      |
| Double |         1.39      |         1.53      |         1.73      |         2.02      |        2.57       |          3.16      |          4.71      |



### Average Number of Probes Required for Inserting Provided String Objects
| Type   | Load Factor = 0.5 | Load Factor = 0.6 | Load Factor = 0.7 | Load Factor = 0.8 | Load Factor = 0.9 | Load Factor = 0.95 | Load Factor = 0.99 |
|--------|-------------------|-------------------|-------------------|-------------------|-------------------|--------------------|--------------------|
| Linear |         1.60      |         2.15      |         3.60      |         6.71      |        19.81      |        110.59      |        471.67      |
| Double |         1.39      |         1.53      |         1.72      |         2.02      |         2.57      |          3.19      |          4.70      |


## Sources used

* https://www.baeldung.com/java-number-formatting
* https://www.w3schools.com/java/java_files_read.asp
* https://www.w3schools.com/java/java_try_catch.asp#:~:text=When%20executing%20%EE%80%80Java%EE%80%81

