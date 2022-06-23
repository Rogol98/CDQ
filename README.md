Project created with java 17 and build with Maven
In order to run app, uncomment relevant lines in App.java

Additional comments on tasks:
a) - The Problem was, that there is no "state" column in the CSV file. If the name of the province is not a state or its shortcut, the program is searching 
through another CSV file with USA cities and states they are located in and comparing the city to the original CSV "city" column and getting the state for the city.
After finishing the implementation I noticed that there are many cities in USA that have the same name in diffrent states (unlike in Poland), so the outcome 
of this assigning is rather poor. The algorithm could be uprgaded by ie. getting the state from the coordinates.
b) - Counting in how many rows the particular city is stated in "city" column.
c) - Counting in how many rows the field "websites" is not empty and unique.
d) - A brewery counts as one oferring tacos, when there is word "taco" included in the menu. This algorithm can be optimized by using a regex instead.
e) - have issues both from d) and a)
3 - Record counts as a duplicate when all of its coulmns are the same as any in any other row excluding ID column. Breweries beeing listed more than once are simply
rows that have duplicate/s.

General note: The solution could have been more optimized by doing all of the above tasks by one CSV iteration, but it's divided in order to provide more clarity
and testability.
*few tests included
