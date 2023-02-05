/******************************************************************************
 *  Canvas Group: 
 *  Name & Kt:  Haraldur Jón Friðriksson / 2111022250
 *  Name & Kt:  Sigurður Jónsson / 0608022890
 *
 *  Operating system: macOS
 *  Compiler: javac
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: No
 *
 *  Hours to complete assignment (optional): 
 *
 ******************************************************************************/


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?  *****************************************************************************/
We checked if the sites row was 0 or N and if so we connected the site to either a site named topRow (if 0) or bottomRow (if N). Then to check if the system percolates we checked if topRow and bottomRow had connected.


/******************************************************************************
 *  Using Percolation with QuickFindUF.java,  fill in the table below such that 
 *  the N values are multiples of each other.

 *  Give a formula (using tilde notation) for the running time (in seconds) of 
 *  PercolationStats.java as a function of both N and T. Be sure to give both 
 *  the coefficient and exponent of the leading term. Your coefficients should 
 *  be based on empirical data and rounded to two significant digits, such as 
 *  5.3*10^-8 * N^5.0 T^1.5.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
75		0,172
150		1,934
300		30,325
600		482,472
1200		7.567,504


(keep N constant)

 T          time (seconds)
------------------------------
100		26,425
200		53,031
400		105,358
800		211,268
1600		422,631


Running time as a function of N and T:  ~ 9,3*10^-10 * N^4 * T
Reasoning for this running time: 

/******************************************************************************
 *  Repeat the previous question, but use WeightedQuickUnionUF.java.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
200		0,299
400		1,076
800		4,401
1600		22,181
3200		136,507


(keep N constant)

 T          time (seconds)
------------------------------
100		0,297
200		0,467
400		0,914
800		1,831
1600		3,534


Running time as a function of N and T:  ~ 2.7*10^-8 * N^2 * T

Reasoning for this running time:

/**********************************************************************
 *  How much memory (in bytes) does a Percolation object use to store
 *  an N-by-N grid? Use the 64-bit memory cost model from Section 1.4
 *  of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************

24 bytes (overhead for the array of arrays)

M * 8 bytes (references to row arrays) + M * 24 bytes (overhead from the row arrays)

M * N * 1 bytes (N boolean values in M rows)

= 1NM + 32M + 24 ~ NM bytes

 
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/




/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and classes, but do
 *  include any help (or discussions) from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/





/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/

