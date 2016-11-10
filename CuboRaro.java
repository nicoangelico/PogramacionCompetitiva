/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

/**
 *
 * @author pichon
 */
import java.util.*;

public class CuboRaro {
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer[]> grafo = new ArrayList<>();
        grafo = armarGrafo();
        int casos = sc.nextInt();
        boolean bandera;

        int[] resultado = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};

        for (int i = 0; i < casos; i++) {
            int[] entrada = new int[16];
            ArrayList<Integer> comparador;

            for (int j = 0; j < 16; j++) {
                entrada[j] = sc.nextInt();
            }
            comparador = calcularDiferencia(entrada, resultado);
            if (comparador.size() > 6) {
                System.out.println("More");
                break;
            }
            if (comparador.size() == 6) {
                bandera = false;
                int cont = 3;
                for (int j = 0; j < 6; j++) {
                    Integer[] vecinos = grafo.get(comparador.get(j));
                    for (int w = 1; w < vecinos.length; w++) {
                        for (int k = 0; k < 6; k++) {
                            if (grafo.get(grafo.indexOf(vecinos[w])) != grafo.get(comparador.get(j))) {
                                cont++;
                                bandera = true;
                                break;
                            }
                        }
                        if (bandera) {
                            break;
                        }
                    }
                }
                if (cont < 3) {
                    System.out.println("More");
                } else {
                    System.out.println(cont);
                }
            }
            if (comparador.size() == 4) {
                bandera = false;
                int cont = 0;
                for (int j = 0; j < 4; j++) {
                    Integer[] vecinos = grafo.get(comparador.get(j));
                    for (int w = 1; w < vecinos.length; w++) {
                        for (int k = 0; k < 4; k++) {
                            if (vecinos[w] != comparador.get(j)) {
                                if (grafo.get(grafo.indexOf(vecinos[w])) != grafo.get(comparador.get(j))) {
                                    cont++;
                                    bandera = true;
                                    break;
                                }
                            }
                        }
                        if (bandera) {
                            break;
                        }
                    }
                }
                if (cont < 4) {
                    System.out.println("More");
                } else {
                    System.out.println(cont);
                }
            }
            if (comparador.size() == 2) {
                int cont = 0;
                for (int j = 0; j < 4; j++) {
                    Integer[] vecinos = grafo.get(comparador.get(0));
                    for (int k = 0; k < vecinos.length; k++) {
                        if (vecinos[k] == comparador.get(1)) {
                            if (grafo.get(grafo.indexOf(vecinos[k])) != grafo.get(comparador.get(1))) {
                                cont++;
                                bandera = true;
                                break;
                            }
                        }
                    }
                }
                if (cont < 2) {
                    System.out.println("More");
                } else {
                    System.out.println(cont);
                }
            }
            if (comparador.size() == 0) {
                System.out.println("0");
            }
            
        }//For casos

    }//Fin de main

    private static ArrayList<Integer> calcularDiferencia(int[] entrada, int[] resultado) {
        ArrayList<Integer> salida = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (entrada[i] != resultado[i]) {
                salida.add(i);
            }
        }
        return salida;
    }

    private static ArrayList<Integer[]> armarGrafo() {
        ArrayList<Integer[]> salida = new ArrayList<>();
//        salida.add(new List<Integer>(Arrays.asList(1,2,4,8)) {});
        salida.add(new Integer[]{0, 3, 9, 5});
        salida.add(new Integer[]{3, 10, 0, 6});
        salida.add(new Integer[]{1, 2, 7, 11});
        salida.add(new Integer[]{0, 6, 5, 12});
        salida.add(new Integer[]{13, 7, 4, 1});
        salida.add(new Integer[]{4, 14, 7, 2});
        salida.add(new Integer[]{3, 6, 5, 15});
        salida.add(new Integer[]{0, 12, 10, 9});
        salida.add(new Integer[]{11, 1, 8, 13});
        salida.add(new Integer[]{8, 2, 11, 14});
        salida.add(new Integer[]{3, 9, 10, 15});
        salida.add(new Integer[]{4, 14, 13, 8});
        salida.add(new Integer[]{15, 12, 5, 9});
        salida.add(new Integer[]{15, 12, 6, 10});
        salida.add(new Integer[]{7, 13, 14, 11});
        return salida;
    }
}

/*

3                    
0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 
0 1 0 0 0 0 0 0 1 0 1 1 1 1 1 1 
0 0 0 0 0 0 1 0 1 0 1 1 1 1 1 1

Case #1: 0
Case #2: 1
Case #3: more

Del SPoojjjj
1
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
 */