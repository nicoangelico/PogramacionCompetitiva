/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
// Titulo: CSTREET - Cobbled streets
// Link: http://www.spoj.com/problems/CSTREET/
public class Calles2 {
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> red;
        ArrayList<ArrayList<Integer>> redPeso;
        ArrayList<Integer> visitados = new ArrayList<>();
        ArrayList<Integer> abiertosNodos = new ArrayList<>();
        ArrayList<Integer> abiertosPesos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int cantPruebas = sc.nextInt();
        for (int i = 0; i < cantPruebas; i++) {
            int precio = sc.nextInt();
            int cantEdificios = sc.nextInt();
            int cantCalles = sc.nextInt();
            red = new ArrayList<>();
            redPeso = new ArrayList<>();
            for (int j = 0; j < cantEdificios; j++) {
                red.add(new ArrayList<>());
                redPeso.add(new ArrayList<>());
            }
            for (int j = 0; j < cantCalles; j++) {
                int origen = sc.nextInt()-1;
                int destino = sc.nextInt()-1;
                int peso = sc.nextInt();
                red.get(origen).add(destino);
                redPeso.get(origen).add(peso);
//                red.get(destino).add(origen);
//                redPeso.get(destino).add(peso);
            }
            ArrayList<ArrayList<Integer>> arbolMinimo = new ArrayList<>(redPeso);
            ArrayList<ArrayList<Integer>> subArbol = new ArrayList<>();
            ArrayList<Integer> subArbolCantAristas = new ArrayList<>();
            ArrayList<Integer> elementosArbol;
            int caminoMinimo = 0;
            
            for (int j = 0; j < cantEdificios; j++) {
                arbolMinimo.add(new ArrayList<>());
            }
            System.out.println(redPeso);
            int cont = 0;
            while(cont < cantCalles){
                int[] index = menorOF(redPeso);
                int nodoA = index[0];
                int nodoB = red.get(index[0]).get(index[1]);
                redPeso.get(index[0]).set(index[1], 9999);
                System.out.println(red);
                System.out.println(redPeso);
                if (subArbol.isEmpty()) {
                    System.out.println("Primer sub arbol");
                    elementosArbol = new ArrayList<>();
                    elementosArbol.add(nodoA);
                    elementosArbol.add(nodoB);
                    subArbol.add(elementosArbol);
                    subArbolCantAristas.add(1);
                    caminoMinimo = caminoMinimo + index[2];
                }else{
                    int indexSubArbol = getSubArbol(nodoA, nodoB, subArbol);
                    if( indexSubArbol == -1){
                        System.out.println("Nuevo sub arbol");
                        elementosArbol = new ArrayList<>();
                        elementosArbol.add(nodoA);
                        elementosArbol.add(nodoB);
                        subArbol.add(elementosArbol);
                        subArbolCantAristas.add(1);
                        caminoMinimo = caminoMinimo + index[2];
                    } else {
                        System.out.println("El camino pertenece a un sub arbol");
                        //Debo preguntar si tambien se encuentra en otro sub arbol
                        // de ser asi debo unir esos dos sub arboles.
                        int indexSubArbol2 = getSubArbol2(nodoA, nodoB, subArbol, indexSubArbol);
                        if ( indexSubArbol2 == -1) {
                            //No debo unir dos sub arboles
                            System.out.println("SubArbol: " + subArbol.get(indexSubArbol));
                            if (subArbol.get(indexSubArbol).size() == cantEdificios) {
                                System.out.println("fin de recorrido");
                                break;
                            }
                            if( subArbol.get(indexSubArbol).size() > (subArbolCantAristas.get(indexSubArbol)) ){
                                //Se asume q no hay calles dobles entre ciudades
                                System.out.println("lo agrego a el sub arbol: " + indexSubArbol);
                                if (!subArbol.get(indexSubArbol).contains(nodoA)) {
                                    subArbol.get(indexSubArbol).add(nodoA);
                                } else {
                                    subArbol.get(indexSubArbol).add(nodoB);
                                }
                                subArbolCantAristas.set(indexSubArbol, subArbolCantAristas.get(indexSubArbol)+1);
                                caminoMinimo = caminoMinimo + index[2];
                            } else {
                                System.out.println("Ciclo encontrado: " + nodoA + " " + nodoB);
                            }
                        } else {
                            //el nodo produce la union de dos sub arboles. Como mierda hago esto? 
                            for (int j = 0; j < subArbol.get(indexSubArbol2).size(); j++) {
                                int nodo = subArbol.get(indexSubArbol2).get(j);
                                if (!subArbol.get(indexSubArbol).contains(nodo)) {
                                    subArbol.get(indexSubArbol).add(nodo);
                                }
                            }
                            subArbolCantAristas.set(indexSubArbol, subArbolCantAristas.get(indexSubArbol)+subArbolCantAristas.get(indexSubArbol2)+1);
                            caminoMinimo = caminoMinimo + index[2];
                            subArbol.remove(indexSubArbol2);
                        }
                    }
                }
                System.out.println("SubArboles: " + subArbol);
                System.out.println("AristasSubA: " + subArbolCantAristas);
                cont++;
            }//fin while
            System.out.println("Camino total: " + caminoMinimo);
            System.out.println(caminoMinimo*precio);
            
        }
    }

    private static int[] menorOF(ArrayList<ArrayList<Integer>> redPeso) {
        int min = 99999;
        int[] salida = new int[]{0,0,0};
        for (int i = 0; i < redPeso.size(); i++) {
            ArrayList<Integer> vecinos = redPeso.get(i);
            for (int j = 0; j < vecinos.size(); j++) {
                if (min > vecinos.get(j)) {
                    min = vecinos.get(j);
                    salida[0]= i;
                    salida[1]= j;
                }
            }
        }
        System.out.println("minimo de: " + salida[0] + " es " + salida[1] + " = " + min);
        salida[2] = min;
        return salida;
    }

    private static int getSubArbol(int nodoA, int nodoB, ArrayList<ArrayList<Integer>> subArbol) {
        int salida = -1;
        for (int i = 0; i < subArbol.size(); i++) {
            if (subArbol.get(i).contains(nodoA) || subArbol.get(i).contains(nodoB)) {
                salida = i;
                break;
            }
        }
        return salida;
    }
    
    //Este metodo sirve para verificar si la arista une dos sub arboles
    private static int getSubArbol2(int nodoA, int nodoB, ArrayList<ArrayList<Integer>> subArbol, int noBuscarAca) {
        int salida = -1;
        for (int i = 0; i < subArbol.size(); i++) {
            if ( i != noBuscarAca) {
                if (subArbol.get(i).contains(nodoA) || subArbol.get(i).contains(nodoB)) {
                    salida = i;
                    break;
                }
            }
        }
        return salida;
    }
    
}

/*
1
2
5
7
1 2 1
2 3 2
2 4 6
5 2 1
5 1 3
4 5 2
3 4 3

1
2
10
16
1 2 1
2 10 2
10 5 1
2 3 3
3 5 1
2 6 1
3 4 4
4 5 1
5 9 2
6 4 1
1 7 3
4 9 1
4 8 1
7 6 1
8 8 3
7 9 4
*/