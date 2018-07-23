public class Arrays {
  public static void minUndMax (int[] feld) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < feld.length; i++) {
      if (feld[i] < min)
        min = feld[i];
      if (feld[i] > max)
        max = feld[i];
    }
    if (feld.length == 0)
      System.out.println("Das Array ist leer.");
    else
      System.out.println("Minimum: " + min + ", Maximum: " + max);
  }

  public static int[] invertieren (int[] feld) {
    int[] invertiert = new int[feld.length];
    for (int i = 0; i < invertiert.length; i++)
      invertiert[i] = feld[feld.length - i - 1];
    return invertiert;
  }

  public static int[] schneiden (int[] feld, int laenge) {
    int[] geschnitten = new int[laenge];
    for (int i = 0; i < geschnitten.length && i < feld.length; i++)
      geschnitten[i] = feld[i];
    return geschnitten;
  }

  public static int[] linearisieren (int[][] feld) {
    int length = 0;
    for (int i = 0; i < feld.length; i++)
      length += feld[i].length;
    int[] linearisiert = new int[length];
    int linIndex = 0;
    for (int i = 0; i < feld.length; i++)
      for (int j = 0; j < feld[i].length; j++)
        linearisiert[linIndex++] = feld[i][j];
    return linearisiert;
  }

  public static void print (int[] feld) {
    String tmp = "{" + feld[0];
    for (int i = 1; i < feld.length; i++) {
        tmp += ", ";
        tmp += feld[i];
    }
    tmp += "}";
    System.out.println(tmp);
  }

  public static void main (String[] args) {
    int[] feld = new int[] {1, -1, 5, -27, 3, 200, 10};
    minUndMax(feld);
    int[] invertiert = invertieren(feld);
    print(invertiert);
    int[] geschnitten = schneiden(feld, 3);
    print(geschnitten);
    int[][] mehrFeld = new int[][] {
      new int[] {1, 3},
      new int[] {25},
      new int[] {7, 4, 6, 9}
    };
    int[] linearisiert = linearisieren(mehrFeld);
    print(linearisiert);
  }

}
