package segundoparcialpc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Cobbled_Streets;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
// Titulo: CSTREET - Cobbled streets
// Link: http://www.spoj.com/problems/CSTREET/

public class Calles {
    public static void main(String[] args) throws IOException{
        ArrayList<ArrayList<Integer>> red;
        ArrayList<ArrayList<Integer>> redPeso;

        Reader sc = new Reader();
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
            }
            ArrayList<ArrayList<Integer>> arbolMinimo = new ArrayList<>(redPeso);
            ArrayList<ArrayList<Integer>> subArbol = new ArrayList<>();
            ArrayList<Integer> subArbolCantAristas = new ArrayList<>();
            ArrayList<Integer> elementosArbol;
            int caminoMinimo = 0;
            
            for (int j = 0; j < cantEdificios; j++) {
                arbolMinimo.add(new ArrayList<>());
            }
//            System.out.println(redPeso);
            int cont = 0;
            while(cont < cantCalles){
                int[] index = menorOF(redPeso);
                int nodoA = index[0];
                int nodoB = red.get(index[0]).get(index[1]);
                redPeso.get(index[0]).set(index[1], 9999);
//                System.out.println(red);
//                System.out.println(redPeso);
                if (subArbol.isEmpty()) {
//                    System.out.println("Primer sub arbol");
                    elementosArbol = new ArrayList<>();
                    elementosArbol.add(nodoA);
                    elementosArbol.add(nodoB);
                    subArbol.add(elementosArbol);
                    subArbolCantAristas.add(1);
                    caminoMinimo = caminoMinimo + index[2];
                }else{
                    int indexSubArbol = getSubArbol(nodoA, nodoB, subArbol);
                    if( indexSubArbol == -1){
//                        System.out.println("Nuevo sub arbol");
                        elementosArbol = new ArrayList<>();
                        elementosArbol.add(nodoA);
                        elementosArbol.add(nodoB);
                        subArbol.add(elementosArbol);
                        subArbolCantAristas.add(1);
                        caminoMinimo = caminoMinimo + index[2];
                    } else {
//                        System.out.println("El camino pertenece a un sub arbol");
                        //Debo preguntar si tambien se encuentra en otro sub arbol
                        // de ser asi debo unir esos dos sub arboles.
                        int indexSubArbol2 = getSubArbol2(nodoA, nodoB, subArbol, indexSubArbol);
                        if ( indexSubArbol2 == -1) {
                            //No debo unir dos sub arboles
//                            System.out.println("SubArbol: " + subArbol.get(indexSubArbol));
                            if (subArbol.get(indexSubArbol).size() == cantEdificios) {
//                                System.out.println("fin de recorrido");
                                break;
                            }
                            if( subArbol.get(indexSubArbol).size() > (subArbolCantAristas.get(indexSubArbol)) ){
                                //Se asume q no hay calles dobles entre ciudades
//                                System.out.println("lo agrego a el sub arbol: " + indexSubArbol);
                                if (!subArbol.get(indexSubArbol).contains(nodoA)) {
                                    subArbol.get(indexSubArbol).add(nodoA);
                                } else {
                                    subArbol.get(indexSubArbol).add(nodoB);
                                }
                                subArbolCantAristas.set(indexSubArbol, subArbolCantAristas.get(indexSubArbol)+1);
                                caminoMinimo = caminoMinimo + index[2];
                            } else {
//                                System.out.println("Ciclo encontrado: " + nodoA + " " + nodoB);
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
//                System.out.println("SubArboles: " + subArbol);
//                System.out.println("AristasSubA: " + subArbolCantAristas);
                cont++;
            }//fin while
//            System.out.println("Camino total: " + caminoMinimo);
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
//        System.out.println("minimo de: " + salida[0] + " es " + salida[1] + " = " + min);
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
*/

/*
class Reader {

    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                break;
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }
        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }
}
*/