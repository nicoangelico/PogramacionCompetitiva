/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package segundoparcialpc;

import java.util.Scanner;

/**
 *
 * @author pichon
 */
public class ColasCantina {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int colaSize = sc.nextInt();
        int periodos = sc.nextInt();
        String cola = "", colaSalida = "";
        sc.nextLine();
        cola = sc.nextLine();
//        System.out.println(cola);
        for (int j = 0; j < periodos; j++){
            for (int i = 0; i < colaSize-1; i++) {
                if ((cola.charAt(i) == 'B') && (cola.charAt(i+1) == 'G')) {
                    colaSalida = colaSalida + "G" + "B";
                    i++;
                } else{
                    if((cola.charAt(i) == 'B')){
                        colaSalida = colaSalida + "B";
                    } else{
                        colaSalida = colaSalida + "G";
                    }
                }
            }
            if (colaSalida.length() != cola.length()) {
                colaSalida = colaSalida + cola.charAt(colaSize-1);
            }
            cola = colaSalida;
//            System.out.println(cola);
            colaSalida = "";
        }
        System.out.println(cola);
    }

}
/*
input
5 1
BGGBG
output
GBGGB

input
5 2
BGGBG
output
GGBGB

input
4 1
GGGB
output
GGGB

no
3 1
BBG
si
3 1
BBB
si
3 1 
BGG
si
3 1 
BGB
si
3 1
GGG
*/