# Android Prototypes
See branch GUI-Prototype for Prototype 1
See branch prototype2 for Prototype 2


# Natural logarithm


## First method
This method calculates the natural logarithm using Taylor-Maclaurin series, which follows the formula:

<img src="http://latex.codecogs.com/gif.latex?%5Clog(1%2Bx)%3Dx-%5Cfrac%7Bx%5E%7B2%7D%7D%7B2%7D%2B%5Cfrac%7Bx%5E%7B3%7D%7D%7B3%7D-%5Cfrac%7Bx%5E%7B4%7D%7D%7B4%7D+..." border="0"/>


simplified as:


<img src="http://latex.codecogs.com/gif.latex?%5Clog(1%2Bx)%3D%5Csum_%7Bn%3D1%7D%5E%5Cinfty%7B(-1)%7D%5E%7Bn-1%7D%5Cfrac%7Bx%5E%7Bn%7D%7D%7Bn%7D" border="0"/>

Source: https://cims.nyu.edu/~kiryl/Calculus/Section_8.7--Taylor_and_Maclaurin_Series/Taylor_and_Maclaurin_Series.pdf

## Second method
This method provides an implementation for the base 10 logarithm, and then computes the natural logarithm by dividing the result by log(E). It is slightly faster than the Taylor Series, and has the benefit of having the base 10 logarithm function included. It is based on the following principle:

<img src="http://latex.codecogs.com/gif.latex?%5Clog_%7Ba%7D%7Bx%7D%20%3D%20%5Clfloor%5Clog_%7Ba%7D%7Bx%7D%5Crfloor%20%2B%20%5Clfloor%5Clog_%7Ba%7D%7B(%5Clfloor%5Clog_%7Ba%7D%7Bx%7D%5Crfloor)%5E%7Ba%7D%5Crfloor%7D%20%2B%20%5Clfloor%5Clog_%7Ba%7D%7B(%5Clfloor%5Clog_%7Ba%7D%7B%5Clfloor%5Clog_%7Ba%7D%7Bx%7D%5Crfloor)%5E%7Ba%7D%7D%5Crfloor)%5E%7Ba%7D%5Crfloor%7D%20%2B%20..." border="0"/>



/Citation


## Third method
This method is based on the formula:


/IMAGE


It is quite slow and inefficient, and should not be used in production


/Citation


# Deriving E
E was derived using the Newton series, which has the formula:


/Image


/Citation
