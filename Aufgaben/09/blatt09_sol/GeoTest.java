package pgdp;

public class GeoTest {

    public static void main(String[] args) {
        Grundflaeche g1 = new Kreis(7);
        Grundflaeche g2 = new Rechteck(2, 3);
        Grundflaeche g3 = new Rechteck(4, 4);
        Grundflaeche g4 = new NEck(7, 3);
        Grundflaeche g5 = new NEck(4, 5);
        Grundflaeche g6 = new Quadrat(8);
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(g3);
        System.out.println(g4);
        System.out.println(g5);
        System.out.println(g6);
        Prisma p1 = new Prisma(g1, 1);
        Prisma p2 = new Prisma(g2, 2);
        Prisma p3 = new Prisma(g3, 3);
        Prisma p4 = new Prisma(g4, 4);
        Prisma p5 = new Prisma(g5, 5);
        Prisma p6 = new Prisma(g6, 6);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
    }
}
