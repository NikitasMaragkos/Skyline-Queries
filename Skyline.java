import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.lang.String ;
import java.util.ArrayList;
import java.util.*;

public class Skyline{
    
    
    /* Java program for Merge Sort */
    //https://www.geeksforgeeks.org/merge-sort/
    static class MergeSort
    {
	    // Merges two subarrays of arr[].
	    // First subarray is arr[l..m]
	    // Second subarray is arr[m+1..r]
	    void merge(List<pair > arr, int l, int m, int r)
	    {
	    	// Find sizes of two subarrays to be merged
	    	int n1 = m - l + 1;
	    	int n2 = r - m; 

	    	/* Create temp arrays */
	    	List<pair > L = new ArrayList<pair > ();
	    	List<pair > R = new ArrayList<pair > ();    

    		/*Copy data to temp arrays*/
	    	for (int i = 0; i < n1; ++i)
	    		L.add( arr.get(l + i));
	    	for (int j = 0; j < n2; ++j)
	    		R.add( arr.get(m + 1 + j));
	    	/* Merge the temp arrays */

    		// Initial indexes of first and second subarrays
	    	int i = 0, j = 0;

		    // Initial index of merged subarray array
		    int k = l;
	    	while (i < n1 && j < n2) {
	    		if (L.get(i).first < R.get(j).first) {
		    		arr.set(k, L.get(i));
			    	i++;
			    }
			    else if( L.get(i).first > R.get(j).first )
			    {
			    	arr.set(k, R.get(j) );
			    	j++;
			    }
			    else{
			        if(L.get(i).second < R.get(j).second){
			            arr.set(k, L.get(i));
			            i++; 
			            }
			        else{
			            arr.set(k, R.get(j) );
				        j++;
			            }
			        }
			    k++;
		    }

	    	/* Copy remaining elements of L[] if any */
	    	while (i < n1) {
		        arr.set(k, L.get(i));
			    i++;
			    k++;
		    }

		    /* Copy remaining elements of R[] if any */
		    while (j < n2) {
		    	arr.set(k, R.get(j) );
		    	j++;
		    	k++;
		    }
	    }

	    // Main function that sorts arr[l..r] using
	    // merge()
	    void sort(List<pair > arr, int l, int r)
	    {
	    	if (l < r) {
		    	// Find the middle point
		    	int m =l+ (r-l)/2;

    			// Sort first and second halves
	    		sort(arr, l, m);
	    		sort(arr, m + 1, r);

    			// Merge the sorted halves
	    		merge(arr, l, m, r);
	    	}
	    }
    }

    
    // https://www.geeksforgeeks.org/sorting-vector-of-pairs-by-1st-element-in-ascending-and-2nd-element-in-descending/
    // Based on the above link code I implemented the comparable function that we need to solve efficiently Skyline problem
    static class pair implements Comparable<pair>
    {
        int first,second;
        pair(int s, int e)
        {
            first = s;
            second = e;
        }

        public int compareTo(pair b)
        {
            if (this.first != b.first)
                return (this.first < b.first)?-1:1;
            else
                return this.second < b.second?-1:1;
        }
 
    }
    
    
    //QuickSort from https://www.geeksforgeeks.org/quick-sort/
    static void swap(List<pair > arr, int i, int j)
    {
        pair temp1 = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j, temp1);
    }
    
    static int partition(List<pair > arr, int low, int high)
    {
     
        // pivot
        int index = high;
        int pivot1 = arr.get(index).first;
        int pivot2 = arr.get(index).second;
     
        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);
 
        for(int j = low; j <= high - 1; j++)
        {
         
            // If current element is smaller
            // than the pivot
            if (arr.get(j).first < pivot1)
            {
             
                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
                continue;
            }
            if( arr.get(j).first == pivot1 &&  arr.get(j).second < pivot2 )
            {
             
                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, index);
        return (i + 1);
    }
    
    static void quickSort(List<pair > arr, int low, int high)
    {
        if (low < high)
        {
         
            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);
 
            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    public static void main(String []args) throws IOException
    {
         
        MergeSort ob = new MergeSort();
        BufferedReader br = new BufferedReader(new FileReader(args[0])); //Buffer for reading the file
        int total_points = Integer.parseInt(br.readLine());              //Number of points
        List<pair > points = new ArrayList<pair > ();                    //Original points
        int current_point = 0;                                           //Index
        List<List<Integer>> final_list = new ArrayList<>();              //Points of Skyline
        String line = br.readLine();                                     //Line
        while ( line  != null) 
        {
            StringTokenizer st1 =  new StringTokenizer(line, " ");
            int first = Integer.parseInt(st1.nextToken());
            int second = Integer.parseInt(st1.nextToken());
            points.add(new pair(first,second));
            current_point = current_point + 1;
            line = br.readLine();
        }
        Collections.sort(points);                                        //Reordering my points increasingly
        //quickSort(points, 0, total_points - 1);
        //ob.sort(points, 0, total_points - 1);
        int total_min = points.get(0).second;                            //Total minimum on y-axis
        int current_x   = points.get(0).first;                           //Current x
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(current_x);
        temp.add(total_min);
        final_list.add(temp);                                            //Always the first element belongs to Skyline
        int i = 0;                                                       //Index
        while( i < total_points)
        {
            while(current_x == points.get(i).first)
            {
                i += 1;
                if(i >= total_points )
                    break;
            }
            if(i >= total_points )
                    break;
            current_x = points.get(i).first;
            if( points.get(i).second < total_min  ){                     //If current y is lower than total_min we add it to our list
                
                total_min = points.get(i).second;
                List<Integer> temp2 = new ArrayList<Integer>();
                temp2.add(current_x);
                temp2.add(total_min);
                final_list.add(temp2);
            }
        }
        final_list.forEach(
            (point) -> { System.out.println(point.get(0).toString() + ' ' + point.get(1).toString()); });
    }
}