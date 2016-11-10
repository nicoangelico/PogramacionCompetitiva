/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author pichon
 */
public class Redes {
    
    public static ArrayList<ArrayList<Integer[]>> red;
    public static ArrayList<ArrayList<Integer>> pesoObjetivo;
    public static int nodoDestino;
//    public static ArrayList<Integer> visitados;
    public static Stack visitados;
    
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int origen;
        int cantDatos = sc.nextInt();
        for (int i = 0; i < cantDatos; i++) {
            red = new ArrayList<>();
            pesoObjetivo = new ArrayList<>();
            int cantNodos = sc.nextInt();
            for (int j = 0; j < cantNodos; j++) {
                red.add(new ArrayList<>());
                pesoObjetivo.add(new ArrayList<>());
            }
            int cantAristas = sc.nextInt();
            int cantNuevasAristas = sc.nextInt();
            int nodoOrigen = sc.nextInt() -1;
            nodoDestino = sc.nextInt() -1;
            for (int j = 0; j < cantAristas+cantNuevasAristas; j++) {
                Integer[] arista =  new Integer[2];
                origen = sc.nextInt()-1;
                arista[0] = sc.nextInt()-1;
                arista[1] = sc.nextInt();
                red.get(origen).add(arista);
                pesoObjetivo.get(origen).add(null);
            }
// Evaluar si cargaba bien la entrada
//            for (int j = 0; j < red.size(); j++) {
//                System.out.println(j);
//                for (int k = 0; k < red.get(j).size(); k++) {
//                    System.out.println(Arrays.toString(red.get(j).get(k)));
//                }
//            }
            visitados =  new Stack<>();
            int yiyu = getMin(nodoOrigen);
//            if (yiyu == 10000) {
//                System.out.println("-1");
//            }else{
//                System.out.println(getMin(nodoOrigen));
//            }
            System.out.println(getMin(nodoOrigen));
        }
    }
    
    //este algoritmo esta hecho a partir del pseudocodigo disponible en el wikipedia
    private static int getMin(int origen){
        int salida = 10000;
//        System.out.println("Nodo " + (origen+1));
        if ( origen == nodoDestino) {
//            System.out.println("nodo objetivo alcanzado");
            return 0;
        }
        if(visitados.contains(origen)){
//            System.out.println("ya vistido");
            return 10000;
        }
        visitados.push(origen);
        for (int i = 0; i < red.get(origen).size(); i++) {
            int peso;
            if (pesoObjetivo.get(origen).get(i)!= null) {
//                System.out.println("peso precalculado");
                peso = pesoObjetivo.get(origen).get(i);
            } else {
//                System.out.println("calcular peso");
                peso = getMin(red.get(origen).get(i)[0])+ red.get(origen).get(i)[1];
                pesoObjetivo.get(origen).set(i, peso);
            }
            if (peso < salida) {
                salida = peso;
                System.out.println(salida);
            }
        }
        visitados.pop();
        if (visitados.empty()) {
            return -1;
        }
//        System.out.println(visitados);
        
        return salida;
    }
}
