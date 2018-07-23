public class Kaninchen extends MiniJava {
    public static void main(String[] args) {
        int i, tmp;
        int month = 0;

        int young, teen, adult;
        young = 1;
        teen  = 0;
        adult = 0;

        month = 0;
        while (month < 1) {
            month = read("Geben Sie den Monat ein");
        }

        i = 1;
	while (i < month) {
	    tmp = young;
            // 1. Teilaufgabe:
	    young = adult + teen + young;
            // 2. Teilaufgabe:
	    // young = adult + (3 * teen) + young;
	    adult = teen;
	    teen = tmp;
            // Schleifenzaehler:
            i = i + 1;
	}
	
	// Anzahl Kaninchen der 1. Generation 
	//write(young);
	// Anzahl Kaninchen der 2. Generation 
	//write(teen);
	// Anzahl Kaninchen der 3. Generation 
	//write(adult);
	// Gesamtzahl Kaninchen:
	write("Anzahl geschlechtsreifer Kaninchen: " + (young + adult + teen));
    }
}
