
##Runable code under folder calcfunctions named <b>Functions.java

##Test cases under calcfunctions folder named <b>AllTests.java



##sinh(x) Implementation:

##Taylor expansion of f(x) = sinh(x);

Reference: https://en.wikipedia.org/wiki/Taylor_series

![Taylor] (https://upload.wikimedia.org/math/d/3/1/d313574308b26ae8292951977a7ee638.png)

-----------------------------------------------------------------------------------------------------------------------------------------

##Natural logarithm Implementation:

##Taylor-Maclaurin series

<img src="http://latex.codecogs.com/gif.latex?%5Clog(1%2Bx)%3Dx-%5Cfrac%7Bx%5E%7B2%7D%7D%7B2%7D%2B%5Cfrac%7Bx%5E%7B3%7D%7D%7B3%7D-%5Cfrac%7Bx%5E%7B4%7D%7D%7B4%7D+..." border="0"/>


simplified as:

<img src="http://latex.codecogs.com/gif.latex?%5Clog(1%2Bx)%3D%5Csum_%7Bn%3D1%7D%5E%5Cinfty%7B(-1)%7D%5E%7Bn-1%7D%5Cfrac%7Bx%5E%7Bn%7D%7D%7Bn%7D" border="0"/>

Source: https://cims.nyu.edu/~kiryl/Calculus/Section_8.7--Taylor_and_Maclaurin_Series/Taylor_and_Maclaurin_Series.pdf


-----------------------------------------------------------------------------------------------------------------------------------------
##Power Function Implementation: 

##Taylor Expansion

![xindi](https://cloud.githubusercontent.com/assets/17072375/13233843/afdf1d14-d983-11e5-9236-b96988d252be.png)


-----------------------------------------------------------------------------------------------------------------------------------------
##Sine(x) Implementation:

##Taylor Series using MacLaurin Expansion of Sin(x)
	That is; Taylor Series Centered at Zero
	
http://www.saylor.org/site/wp-content/uploads/2012/09/MA102-5.5.3-Taylor-and-MacLaurin-Series.pdf

https://en.wikipedia.org/wiki/Taylor_series

![taylor 1](https://cloud.githubusercontent.com/assets/17072375/13200680/131b71b6-d821-11e5-8f6f-85a7ed4cc33c.PNG)

Expressed as:

![taylor2](https://cloud.githubusercontent.com/assets/17072375/13200682/13203dea-d821-11e5-95f9-d2504d42181c.PNG)

  
 
Integrating MacLaurin Series we get 

![t3](https://cloud.githubusercontent.com/assets/17072375/13200683/132184c0-d821-11e5-94b1-dcdcc528f12a.PNG)

-----------------------------------------------------------------------------------------------------------------------------------------

##SquareRoot(x) Implementation:

##Newton's Iteration</b> 
<br>http://introcs.cs.princeton.edu/java/13flow/Sqrt.java.html
<br><br> <img src="http://latex.codecogs.com/gif.latex?x%20_%7Bk&plus;1%7D%20%3D%20%5Cdfrac%7B1%7D%7B2%7D%20%5Cleft%28%20x_%7Bk%7D%20&plus;%20%5Cdfrac%7Bn%7D%7Bx_%7Bk%7D%7D%20%5Cright%29" border="0"/>

Applying Newton's method on the reciprocal of the square root. This method proved accurate beyond 1e-10 when compared to Java's Pi implementation and thus will be used for the final calculator. 

-----------------------------------------------------------------------------------------------------------------------------------------
