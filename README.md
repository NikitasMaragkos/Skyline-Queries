# Skyline-Queries

Given a dominance relationship in a dataset, a skyline query returns the objects that cannot be dominated by any other objects. In the case of a dataset consisting of multidimensional objects, an object dominates another object if it is as good in all dimensions, and better in at least one dimension. The definition of skyline queries in multidimensional datasets is identical with the known maximum vector problem. (Source: "Skyline Queries: an Introduction")

In this Java implemantation, given a text file of multiple (x,y) coordinates we produce as output the (x,y) points that form the skyline. For instance,
in the following image the skyline points are a, b, e, h and o.  ![Skyline](https://user-images.githubusercontent.com/49310269/190111919-54772eff-b5e5-4f35-a2d0-e1646126ecd5.PNG)

The algorithm runs in O(nlogn) time, since we use a sorting algorithm of O(nlogn) time complexity. After having all points sorted the remaining process runs at O(n).
