# Calculator Prototype 

## User Interface 
![screenshot_menu_small](https://cloud.githubusercontent.com/assets/15674468/14340181/ef4011b0-fc39-11e5-85b8-f0cbfd77594d.png)         &nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;  ![screenshot_calculator_small](https://cloud.githubusercontent.com/assets/15674468/14340220/376d3850-fc3a-11e5-9a23-c9b230e1abd4.png)
***
### Feature
* **Quick add to favorite list**  <br>
![quickaddtofavorite_medium](https://cloud.githubusercontent.com/assets/15674468/14356752/f82c2cc6-fc9a-11e5-97fe-2c664b5eb15a.gif)
* **Editable Expression Text**  <br>
![editabletext_medium](https://cloud.githubusercontent.com/assets/15674468/14356758/0077a720-fc9b-11e5-81f0-8839c6ec0cda.gif)
* **Auto Size Expression Text**  <br>
![autosize text_medium](https://cloud.githubusercontent.com/assets/15674468/14356766/03b41edc-fc9b-11e5-8158-54a180ec5a4e.gif)
* **Memory Slot to save expression result: short press to add, long press to remove**  <br>
![momoryslot_medium](https://cloud.githubusercontent.com/assets/15674468/14356775/09919b54-fc9b-11e5-909b-3cd775b2c047.gif)
* **History key to quickly access history**  <br>
![anshistoryaccess_medium](https://cloud.githubusercontent.com/assets/15674468/14356831/2977ec34-fc9b-11e5-9da6-28d6d9b9fa5c.gif)
* **Favorite List Management**  <br>
![favoritelist_medium](https://cloud.githubusercontent.com/assets/15674468/14356801/17649c90-fc9b-11e5-9c6f-686beaaf6d3d.gif)
* **History List Management**  <br>
![historylist_medium](https://cloud.githubusercontent.com/assets/15674468/14357335/2480fc5a-fc9d-11e5-90b6-e4ae1c0ecc51.gif)

***
### Button 
* **del** : backspace to delete the previous entry.
* **C** : Clean out the content of expression text and result text.
* **=** : Calculation will not start unitll hit "=", "=" will take the expression from first text line to calculate and display the result on the second text line.
* **abs** : absolute value of input
* **DEG** : switch input from Radian to Degree
* **m** : memory slot to save target result text.
* **Ans**: history to review the previous 10 expression 
* **textLine** : first text line displays the user entering expression, second text lie displays the result from first line's expression


***

 -##sinh(x) Implementation:
 -
 -##Taylor expansion of f(x) = sinh(x);
 -
 -Reference: https://en.wikipedia.org/wiki/Taylor_series
 -
 -![Taylor] (https://upload.wikimedia.org/math/d/3/1/d313574308b26ae8292951977a7ee638.png)
 -
 ------------------------------------------------------------------------------------------------------------------------------------------
 -
 -##Natural logarithm Implementation:
 -
 -##Taylor-Maclaurin series
 -
 -<img src="http://latex.codecogs.com/gif.latex?%5Clog(1%2Bx)%3Dx-%5Cfrac%7Bx%5E%7B2%7D%7D%7B2%7D%2B%5Cfrac%7Bx%5E%7B3%7D%7D%7B3%7D-%5Cfrac%7Bx%5E%7B4%7D%7D%7B4%7D+..." border="0"/>
 -
 -
 -simplified as:
 -
 -<img src="http://latex.codecogs.com/gif.latex?%5Clog(1%2Bx)%3D%5Csum_%7Bn%3D1%7D%5E%5Cinfty%7B(-1)%7D%5E%7Bn-1%7D%5Cfrac%7Bx%5E%7Bn%7D%7D%7Bn%7D" border="0"/>
 -
 -Source: https://cims.nyu.edu/~kiryl/Calculus/Section_8.7--Taylor_and_Maclaurin_Series/Taylor_and_Maclaurin_Series.pdf
 -
 -
 ------------------------------------------------------------------------------------------------------------------------------------------
 -##Power Function Implementation: 
 -
 -##Taylor Expansion
 -
 -![xindi](https://cloud.githubusercontent.com/assets/17072375/13233843/afdf1d14-d983-11e5-9236-b96988d252be.png)
 -
 -
 ------------------------------------------------------------------------------------------------------------------------------------------
 -##Sine(x) Implementation:
 -
 -##Taylor Series using MacLaurin Expansion of Sin(x)
 -	That is; Taylor Series Centered at Zero
 -	
 -http://www.saylor.org/site/wp-content/uploads/2012/09/MA102-5.5.3-Taylor-and-MacLaurin-Series.pdf
 -
 -https://en.wikipedia.org/wiki/Taylor_series
 -
 -![taylor 1](https://cloud.githubusercontent.com/assets/17072375/13200680/131b71b6-d821-11e5-8f6f-85a7ed4cc33c.PNG)
 -
 -Expressed as:
 -
 -![taylor2](https://cloud.githubusercontent.com/assets/17072375/13200682/13203dea-d821-11e5-95f9-d2504d42181c.PNG)
 -
 -  
 - 
 -Integrating MacLaurin Series we get 
 -
 -![t3](https://cloud.githubusercontent.com/assets/17072375/13200683/132184c0-d821-11e5-94b1-dcdcc528f12a.PNG)
 -
 ------------------------------------------------------------------------------------------------------------------------------------------
 -
 -##SquareRoot(x) Implementation:
 -
 -##Newton's Iteration</b> 
 -<br>http://introcs.cs.princeton.edu/java/13flow/Sqrt.java.html
 -<br><br> <img src="http://latex.codecogs.com/gif.latex?x%20_%7Bk&plus;1%7D%20%3D%20%5Cdfrac%7B1%7D%7B2%7D%20%5Cleft%28%20x_%7Bk%7D%20&plus;%20%5Cdfrac%7Bn%7D%7Bx_%7Bk%7D%7D%20%5Cright%29" border="0"/>
 -
 -Applying Newton's method on the reciprocal of the square root. This method proved accurate beyond 1e-10 when compared to Java's Pi implementation and thus will be used for the final calculator. 
 -
 ------------------------------------------------------------------------------------------------------------------------------------------
