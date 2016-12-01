    elif n < 0:
    if n ==0:
        return 1
    
def factorial(n):
        return 0
    
    else:
        return n*factorial(n-1)

def isPrime(a):
    if a>1:
        for i in range(2, a):
            if a%i == 0:
                return False
            
        return True
    return False

def largest(x,y,z):
    if x>y and x>z:
        return x
    
    elif y>x and y>z:
        return y
    else:
        return z


    
