public class GGTTable extends MiniJava {
  public static void main(String [] args){
    int i, j, x, y, z;
    x = read("GGT-Tabelle von 1 bis ..?");
    while (x < 1) {
	  x = read("Bitte geben Sie eine positive Zahl groesser als 0 ein.");
	}
    i = 1;
    j = 1;
    String out = " ";
    while(i <= x){
      out = out + "\t" + i;
      i = i+1;
    }
    out = out + "\n";
    i = 1;
    while (i <= x){
      j = 1;
      out = out + i + "\t";
      while (j <= x){
        
        z = i;
        y = j;
        while (z != y)
            if (z < y)
                y = y - z;
            else
                z = z - y;
        out = out + z + "\t";
        j = j+1;
      }
      out = out + "\n";
      i = i+1;
    }
    System.out.println(out);
  }
}