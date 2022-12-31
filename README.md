# Album Optimization with Relative Values

## Introduction
In this project we tried to get an optimized album of French Lebanese trumpeter Ibrahim Maalouf. There is 50 songs with their durations and popularities in total, 30 minutes total time constraint and 0.02 popularity point reduction for empty seconds in album. First part of code tries to maximize popularity points wtihin constraints with using relative values. Also, there is data about in which order listeners prefer to listen songs. Second part of code tries to put order that will give us maximum order points with using nearest neighbourhood approach. In both parts, algorithms were created with heuristics methods.

## Codding Approach
### Objects
There are two object that were used in this project which are Album and Track. We just added "public double track_relative_value" to Track object with it's getter and setter functions to record our relative values. 

Album objects is being used to collect and sort to Tracks and  getting numerical results. There is a method called "AlbumOptimizer" in main class to maximize popularity and there is an another method called "albumOrganizer" in the Album object to sort our album accoring to second objective of project. Also, "display" method of Album class is being using to print the result of our solution by code. Moreover, "tracksIdList", "getTrackWithID", "trackListSorter_IndVal", "trackListSorter_RelVal", "public int getTotalDuration", "emptyCost" are being used; to collect track's IDs which are in the Album, to get Tracks with their IDs, to sort Tracks according to their popularity, to sort Tracks according to their relative values, to calculate total duration and to calculate cost which occurs because of empty seconds, respectively. 

### 
We choosed OOP approach to make code more flexible and adjustable for other musicians and albumes. Thanks to OPP, you can apply this project directly to any other musicians with any other data directly(also data's length is unimportant for code). You just need to change total duration constraint and empty second cost.


## Algorithm for First Part - Relative Values
### What is relative values?
-Relative values are the values that are calculated like this:

-Relative Value(i) = Popularity(i) + Duration(i) x 0.02

-Durations should be or be converted to seconds before application.

First part's algorithm:
-First of all it calculates the relative values of tracks and collets them in an Album object. Then, it sorts them according to their relative values in descending order and starts to put them optimized Album Object until there will be no more seconds to put next track.
-In this way algorith tries to find most valuable track, because we assumed that we have -36 popularity points in the beggining and with collecting tracks that can give us most popularity and reduce cost mostly in total.
-This algorithm sometimes misses oppotunities because despite being able to compare songs one to one, it cannot compare other possibilities.

## Algorithm for Second Part - Nearest Neighbourhood
Second part's algorithm:
Album Orginzer finds most valuable tracks accoring to their popularity and takes most valuable track as first track and second track as last track. Then, starting from first track it searches next track which is both in our optimized album and with highest sequantial value according to track that is being considered.

## Decision
### Why did we choose to use relative values?
According to our trials, relative values approach cannot be successful as much as getting most valuables first. But we wanted to consider 0.02 cost anyway, therefore we choosed this solution way.

## Comparision and Benchmark
### Runing Time
Our algorithm runs between 0.02-0.03 seconds and excat solution runs around 0.05 seconds in our computers. So, we can say our algorithms a bit faster than exact solution.
### Numerical Results and Comparision
We cannot say same thing for numerical result because our solution with 293.97062 too far away from exact solution which is 532, but still it is promising because heuristic solutions of Gurobi are also 313 and 396.

## Conclusion
As a conclusion, our project is scalable and easy to implement for solutions but it requires lots of improvemnt abour giving precise results.
