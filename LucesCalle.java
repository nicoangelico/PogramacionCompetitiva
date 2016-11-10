//package segundoparcialpc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
// Titulo: B. Vanya and Lanterns
// Link: http://codeforces.com/problemset/problem/492/B


public class LucesCalle {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int faroles = sc.nextInt();
        DecimalFormat numberFormat = new DecimalFormat("#.0000000000");
        double longitudCalle = sc.nextInt();
        ArrayList<Double> posicion = new ArrayList<>();
        double disMax = 0;
        for (int i = 0; i < faroles; i++) {
            posicion.add(sc.nextDouble());
        }
        Collections.sort(posicion);
//        System.out.println(posicion);
        double pos = 0;
        disMax = (posicion.get(0)- pos);
//        System.out.println(disMax);
        pos = posicion.get(0);
        for (int i = 1; i < faroles; i++) {
//            System.out.println(posicion.get(i)+"-"+pos);
            if (disMax < (posicion.get(i)-pos)/2f) {
                disMax = (posicion.get(i)-pos)/2f;
            }
//            System.out.println(disMax);
            pos = posicion.get(i);
        }
//        System.out.println(Math.abs(posicion.get(faroles-1)-longitudCalle));
        if (disMax < Math.abs(posicion.get(faroles-1)-longitudCalle)) {
            disMax = Math.abs(posicion.get(faroles-1)-longitudCalle);
        }
        
        System.out.println(numberFormat.format(disMax));
    }
}
/*
Ejemplos
entrada
7 15 
15 5 3 7 9 14 0
salida
2,5000000000

entrada
2 5 
2 5
salida
2,0000000000

46 615683844
431749087 271781274 274974690 324606253 480870261 401650581 13285442 478090364 266585394 425024433 588791449 492057200 391293435 563090494 317950 173675329 473068378 356306865 311731938 192959832 321180686 141984626 578985584 512026637 175885185 590844074 47103801 212211134 330150 509886963 565955809 315640375 612907074 500474373 524310737 568681652 315339618 478782781 518873818 271322031 74600969 539099112 85129347 222068995 106014720 77282307

2 555
200 300

255.0000000000
*/