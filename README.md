#Square Root Method for Eternity
Two distinct methods for calculating the square root of a double.
<br><br>Accuracy of both methods were tested using JUnit 4
and compared against Java's implementation from the 
Math library.

<br><b>Method 1: Bakhshali approximation</b>
<br>http://www.codeproject.com/Articles/69941/Best-Square-Root-Method-Algorithm-Function-Precisi
<br><br> <img src="http://latex.codecogs.com/gif.latex?P%20%3D%20%5Cfrac%7Bd%7D%7B2N%7D" border="0"/>
<br> <img src="http://latex.codecogs.com/gif.latex?A%20%3D%20N%20&plus;%20P" border="0"/>
<br> <img src="http://latex.codecogs.com/gif.latex?%5Csqrt%7BS%7D%20%5Capprox%20A%20-%20%5Cdfrac%20%7BP%5E%7B2%7D%7D%20%7B2A%7D" border="0"/>

<br> <b>Method 2: Newton's Iteration</b> 
<br><br>http://introcs.cs.princeton.edu/java/13flow/Sqrt.java.html
<br> <img src="http://latex.codecogs.com/gif.latex?x%20_%7Bk&plus;1%7D%20%3D%20%5Cdfrac%7B1%7D%7B2%7D%20%5Cleft%28%20x_%7Bk%7D%20&plus;%20%5Cdfrac%7Bn%7D%7Bx_%7Bk%7D%7D%20%5Cright%29" border="0"/>

