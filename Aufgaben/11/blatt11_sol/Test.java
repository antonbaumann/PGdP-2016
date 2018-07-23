package pgdp;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

        List<Grundflaeche> gf = new LinkedList<Grundflaeche>();
        gf.add(new Kreis(7));
        gf.add(new Rechteck(2, 3));
        gf.add(new Rechteck(4, 4));
        gf.add(new NEck(7, 3));
        gf.add(new NEck(4, 5));
        gf.add(new Quadrat(6));
        
        List<Prisma> pf = new LinkedList<Prisma>();
        Random randomGenerator = new Random();
        int n;
        for(Grundflaeche i : gf){
            n = randomGenerator.nextInt(20);
            pf.add(new Prisma(i, n));
            n++;
        }

        System.out.println("unsortierte Liste Grundflaechen\n" + gf);
        Collections.sort(gf);
        System.out.println("sortierte Liste Grundflaechen\n" + gf);
        System.out.println();

        System.out.println("unsortierte Liste Prismen\n" + pf);
        Collections.sort(pf);
        System.out.println("sortierte Liste Prismen\n" + pf);
        System.out.println();

        List<Comparable> cf = new LinkedList<Comparable>();
        for(Grundflaeche i : gf)
            cf.add(i);
        for(Prisma p : pf)
            cf.add(p);
        System.out.println("Liste von Prismen und Grundflaechen\n" + cf);

        System.out.println();
        for (Comparable i : cf) {
            if (i instanceof Quadrierbar) {
                System.out.println(i + " ist quadrierbar");
                if (((Quadrierbar) i).istQuadrat())
                    System.out.println(i + " == " + ((Quadrierbar) i).zuQuadrat());
                //System.out.println();
            } else
                System.out.println(i + " ist NICHT quadriebar");
            if(i instanceof Polygon){
                System.out.println(i + " hat " + ((Polygon) i).getEckenAnzahl() + " Ecken");
            }
            else
                System.out.println(i + "hat keine endliche Anzahl an Ecken in Grundfläche");
            System.out.println();
        }

    }
}
