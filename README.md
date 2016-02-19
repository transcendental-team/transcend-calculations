# transcend-calculations
Repository for COMP 5541 group calculator project

Method 1: Taylor expansion of f(x) = sinh(x);

Reference: https://en.wikipedia.org/wiki/Taylor_series

sinh(x)=x+ x^3/3! +x^5/5! + x^7/7!....for all x

Method 2: Pade approximate , definition of sinh(x)

Reference: https://en.wikipedia.org/wiki/Pad%C3%A9_approximant

In mathematics a Padé approximant is the "best" approximation of a function by a rational function of given order – under this technique, the approximant's power series agrees with the power series of the function it is approximating.

sinh(x)=(exp(x)+exp(-x))/2

exp(x)=(1+(1/2)*x+(1/9)*x^2+(1/72)*x^3+(1/1008)*x^4+(1/30240)*x^5)   /   1-(1/2)*x+(1/9)*x^2-(1/72)*x^3+(1/1008)*x^4-(1/30240)*x^5

