/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
public class BaileAniversario {
    //Anda, lo acepto el jurado
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int parejasFormadas = 0;
        //insgreso de entradas
        int cantNenes = sc.nextInt();
        int index;
        ArrayList<Integer> nenes =  new ArrayList<Integer>();
        for (int i = 0; i < cantNenes; i++) {
            nenes.add(sc.nextInt());
        }
        int cantNenas = sc.nextInt();
        ArrayList<Integer> nenas =  new ArrayList<Integer>();
        for (int i = 0; i < cantNenas; i++) {
            nenas.add(sc.nextInt());
        }
        //Procesamiento
        Collections.sort(nenes);
        Collections.sort(nenas);
        index = 0;
        for (int i = 0; i < cantNenes; i++) {
            for (int j = index; j < cantNenas; j++) {
                if ((nenes.get(i)- nenas.get(j) < 2) && (nenes.get(i)- nenas.get(j) > -2)) {
                    //se forma pareja. Habra chape?
                    parejasFormadas++;
                    index = j+1;
                    break;
                }
            }
        }
        System.out.println(parejasFormadas);
    }
    
}

/*
Entrada
4 
1 4 6 2 
5 
5 1 5 7 9
salida
3
*/