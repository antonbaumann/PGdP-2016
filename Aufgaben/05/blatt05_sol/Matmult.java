public class Matmult {


    public static int vecvecmul(int[] v1, int[] v2) {
        int res = 0;
        for (int i = 0; i < v1.length; i++)
            res += v1[i] * v2[i];
        return res;
    }

    public static int[] matvecmul(int[][] m, int[] v) {
        int[] resvec = new int[m.length];
        for (int i = 0; i < resvec.length; i++) 
            resvec[i] = vecvecmul(m[i], v);
        return resvec;
    }
    public static int[][] transpose(int[][] m) {
        int[][] resmat = new int[m[0].length][m.length];
        for (int i = 0; i < resmat.length; i++) 
            for (int j = 0; j < resmat[0].length; j++) 
                resmat[i][j] = m[j][i];
        return resmat;
    }
    public static int[][] matmatmul(int[][] a,int b[][]){
        int [][] resmat = new int[a.length][];
        int [][] transposed = transpose(b);
        for (int i = 0; i < resmat.length; i++) 
            resmat[i]=matvecmul(a,transposed[i]);
        return transpose(resmat);
    }
    public static void printmat(int[][] resmat){
        for (int i = 0; i < resmat.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < resmat[0].length; j++) 
                System.out.print(resmat[i][j]+" ");
            System.out.println("|");
        }
    }

    public static void main(String [] args){
        int[][] matrix1 = {{1,0,0},{1,0,0},{0,1,0},{0,0,1}};
        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] resmat = matmatmul(matrix1,matrix2);
        printmat(matrix1);
        System.out.println("*");
        printmat(matrix2);
        System.out.println("=");
        printmat(resmat);
    }

}
