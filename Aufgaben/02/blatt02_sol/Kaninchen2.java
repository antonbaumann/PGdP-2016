public class Kaninchen2 extends MiniJava {
    public static void main(String[] args) {
        int k = 1;
	int tmp;
	int time = 0;
        int adult = 0, teen = 0, young = 1;  
	//young spezifiziert Karnickel der 1.Generation, usw;
	while (time < 1) {
            time = read("Geben Sie den Monat ein");
        }

	while (k != time) {
            k++;
	    tmp = young;
	    young = adult+(3*teen)+young;
	    adult = teen;
	    teen = tmp;
	}
	//Anzahl Karnickel der 1.Generation 
	write("1. Generation = " + young);
	//Anzahl Karnickel der 1.Generation 
	write("2. Generation = " + teen);
	//Anzahl Karnickel der 1.Generation 
	write("3. Generation = " + adult);
	//Gesamtzahl gebaehrfaehiger Karnickel:
	//write("Gesamt = " + young+adult+teen);
    }
}
