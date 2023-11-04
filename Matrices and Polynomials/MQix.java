/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqix;

import java.util.Scanner;

/**
 *
 * @author Luis
 */
public class MQix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MatFP a, b, c;
        FracPoli det;
        int n;

        Scanner reading = new Scanner(System.in);
        System.out.println("Introducir el orden de las matrices");
        n = reading.nextInt();
        a = new MatFP().leerMatFP(n);
        b = new MatFP().leerMatFP(n);
        
        System.out.println("A:");
        a.escMatFP();
        System.out.println("B:");
        b.escMatFP();
        System.out.println("A+B:");
        c = a.sumaMatFP(b);
        c.escMatFP();
        System.out.println("A*B:");
        c = a.prodMatFP(b);
        c.escMatFP();
        System.out.println("A^-1:");
        c = a.invMatFP();
        c.escMatFP();
        System.out.println("det(A):");
        det = a.det();
        det.escFCQ();
        System.out.println("det(A^-1):");
        det = new FracPoli(det.q,det.p);
        det.escFCQ();
        
        System.out.println("Fin del programa.");
    }
}
