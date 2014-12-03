csc207Project
=============

CSC 207 Project
by Noah Schlager, Patrick Slough, and Samee Zahid

This application generates a double round robin basketball schedule subject
to restrictions. This was intended to work for the Midwest Conference, and 
this work is a part of a project for Grinnell College's CSC 207 Course. 

In order to Use this schedule, download or clone the repository. 

Navigate to wherever the csc207Project folder was placed and double click
on the program.jar file. This has only been confirmed to work on machines
running Java 8+. Inputs for the Midwest Conference have been provided in
the mwc.txt, dates.txt, and milage.txt files. If you would like to make
your own inputs, you must follow EXACTLY the formats found in those
files. In mwc.txt, the sequence of numbers and dashes found below the team
names are the restrictions that each team faces. Here, a 0 in the i'th
entry means that the team has no travel restrictions on the i'th day in the
dates file, and a 1 means the team should play a home or close away game on
that date. 

