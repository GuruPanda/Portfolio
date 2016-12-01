class IntMan{
    public static void main(String[] args){
    }
    public static int factorial(int n){
	if(n==0){
	    return 1;
	}
	else if(n<0){
	    return 0;
	}
	else{
	    return n*factorial(n-1);
	}
    }
    public static boolean isPrime(int a){
	if(a>1){
	    for(int i=2; i<a; i++){
		if((a%i)==0){
		    return false;
		}
	    }
	    return true;
	}
	else{
	    return false;
	}
    }
    public static int largest(int x, int y, int z){
	if(x>y && x>z){
	    return x;
	}
	else if(y>x && y>z){
	    return y;
	}
	else{
	    return z;
	}
    }
}
