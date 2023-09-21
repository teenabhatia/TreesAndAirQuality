# TreesAndAirQuality
Exploring the Correlation Between Trees and Air Quality

Branching Out: Exploring the Correlation Between Trees and Air Quality
Teena Bhatia & Anushka Daga

Concepts used:
Graph and Graph Algorithms: In our investigation, we are using the concept of graphs to explore the relationship between the number of trees in a region and the air quality of that area. By constructing a graph of all the trees in New York and using connected components to analyze the structure of the graph, we can gain insights into how the trees are distributed and whether their distribution affects the air quality. Specifically, we are testing the hypothesis that larger connected components, which indicate a higher concentration of trees in a region, are associated with higher air quality. 

Information Networks (World Wide Web): As part of our investigation, we are using the airnow.gov API (https://docs.airnowapi.org/). We wrote code that uses the setRequestMethod method to set the type of the HTTP request to be made to be a GET request. We then create a new BufferedReader object, which reads the response data from the AirNow API. 

Changes made to the project: 
We were initially planning to use clustering coefficients to find the concentration of trees in a particular area, however, we realized that this would not be appropriate. This is because if we had a tree with very few neighbors that had many edges among them, this would result in a very high clustering coefficient. However, this translates to a large number of trees in one area so is likely to not have a substantial impact on the air quality. Similarly, a large number of trees might exist in one area but because there is considerable distance between some of the trees, there may not be edges between them resulting in a lower clustering coefficient even though this situation corresponds to an area with a high number of trees. 

We then decided to use cliques and wrote code for it, however, we realised that this would not work for a long stretch of trees as the nodes on the ends would not have an edge between them (since we decided to only have an edge if the distance between 2 trees is less than 0.01 km) even though we would want them to be part of the same “group”. Hence, we decided to use the concept of connected components instead.

Work breakdown:
Teena: Modified HW1’s BFS code to find the nodes in each connected component and calculate the size of the connected components. Also wrote code to read in values from the API using a get request. Wrote the introduction, analysis of results, and future improvements of results.
Anushka: Wrote code to read in values from the dataset and construct the graph as well as to find the average latitude and longitude of each connected component. Wrote the explanation for the concepts used and methodology sections in the report. 

User manual:
All our code files are in the src folder. To run the program, run the Main.java file. The number of vertices and the number of restarts (connected components) is printed first followed by the coordinates of the vertices in each connected component. Following that, the average latitude, average longitude, and size of each connected component are printed on separate lines.

In order to see the air quality finder in action, run the AirQualityFinder.java file. Please manually input each latitude and longitude when prompted in the console.

If you would like to test other data, please follow the format in the ‘coordinates’ file and change the path of the file that is being read in the AdjacencyMatrix.java file.
