#Square Root Method for Eternity
Testing and choice implementations avaiable in the squareroot package.
<br>Finalized algorithm available in the calculatorfunction package.
<br><br>Accuracy of both methods were tested using JUnit 4
and compared against Java's implementation from the 
Math library.

<br> <b>Method 1: Bakhshali approximation</b>
<br> http://www.codeproject.com/Articles/69941/Best-Square-Root-Method-Algorithm-Function-Precisi
<br> <br> <img src="http://latex.codecogs.com/gif.latex?P%20%3D%20%5Cfrac%7Bd%7D%7B2N%7D" border="0"/>
<br> <img src="http://latex.codecogs.com/gif.latex?A%20%3D%20N%20&plus;%20P" border="0"/>
<br> <img src="http://latex.codecogs.com/gif.latex?%5Csqrt%7BS%7D%20%5Capprox%20A%20-%20%5Cdfrac%20%7BP%5E%7B2%7D%7D%20%7B2A%7D" border="0"/>

<br> The equivalent to 2 iterations of the babylonian method:
<br> https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Babylonian_method
This is a quick method to calculate the square root of a positive number. The approximations are accurate to 4 digits in the mantissa for integers. However, this method proves ineffective for computing the square roots of irrational numbers. When compared to Java's Pi implementation, it failed within an error tolerance of 1e-2. 

<b>Method 2: Newton's Iteration</b> 
<br>http://introcs.cs.princeton.edu/java/13flow/Sqrt.java.html
<br><br> <img src="http://latex.codecogs.com/gif.latex?x%20_%7Bk&plus;1%7D%20%3D%20%5Cdfrac%7B1%7D%7B2%7D%20%5Cleft%28%20x_%7Bk%7D%20&plus;%20%5Cdfrac%7Bn%7D%7Bx_%7Bk%7D%7D%20%5Cright%29" border="0"/>

<br> Applying Newton's method on the reciprocal of the square root. This method proved accurate beyond 1e-10 when compared to Java's Pi implementation and thus will be used for the final calculator. 
