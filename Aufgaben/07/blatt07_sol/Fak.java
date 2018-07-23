public class Fak {

    // factorial recursive (not tail recursive)
    public static int facRec(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * facRec(n - 1);
        }
    }

    // factorial tail recursive
    public static int facTailRec(int n){
	return facTailRecHelper(n, 1);
    }
    
    // helper function factorial tail recursive 
    private static int facTailRecHelper(int n, int k) {
        if (n <= 1) {
            return k;
        } else {
            return facTailRecHelper(n - 1, n * k);
        }
    }

    // factorial "tail" iterative
    public static int facIt(int n, int k) {
        //tailRec into iterative version
        while (true)
        {
            if (n <= 1) {
                return k;
            } else {
                k = n * k;
                n = n - 1;
            }
        } 
    }

    //factorial iterative "intuitively"
    public static int facItNice(int n) {
        int k=1; 
        for(int i=2;i<=n;i++)
            k*=i;
        return k;
    }    
    
    public static void main(String[] args) {
    
	int n = 5;
    
        System.out.println("iterative: " + facIt(n, 1));
        System.out.println("tail-recursiv: " + facTailRec(n));
        System.out.println("recursive: " + facRec(n));
        System.out.println("iterative cleaned up: " + facItNice(n));
    }
}

