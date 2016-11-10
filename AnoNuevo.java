/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
public class AnoNuevo { //Anda regio
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cantCelulas = sc.nextInt();
        int celulaDestina = sc.nextInt();
        int[] lista = new int[cantCelulas-1];
        for (int i = 0; i < cantCelulas-1; i++) {
            lista[i] = sc.nextInt();
        }
        int posicion = 1;
        boolean bandera = true;
//        System.out.println(Arrays.toString(lista));
        for (int i = 0; i < cantCelulas-1; i++) {
            if (posicion > cantCelulas-1) {
                break;
            }
            posicion = posicion + lista[posicion-1];
            
//            System.out.println(posicion);
            if (posicion == celulaDestina) {
                System.out.println("YES");
                bandera = false;
                break;
            }
        }
        if (bandera) {
            System.out.println("NO");
        }
        
    }
}

/*
Examples

input
8 4
1 2 1 2 1 2 1
output
YES

input
8 5
1 2 1 2 1 1 1
output
NO
*/