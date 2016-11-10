/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pichon
 */
public class Redes2 {
    
    public static void main(String[] args) throws IOException{
        ArrayList<ArrayList<Integer>> red;
        ArrayList<ArrayList<Integer>> redPeso;
        ArrayList<Integer> visitados = new ArrayList<>();
        ArrayList<Integer> abiertosNodos = new ArrayList<>();
        ArrayList<Integer> abiertosPesos = new ArrayList<>();
        int origen, max = 99999999;
        //Ingreso la entrada y armo el grafo...
        Reader sc = new Reader();
        int cantDatos = sc.nextInt();
        for (int i = 0; i < cantDatos; i++) {
            red = new ArrayList<>();
            redPeso = new ArrayList<>();
            int cantNodos = sc.nextInt();
            //Creo los arrayList para cada nodo
            for (int j = 0; j < cantNodos; j++) {
                red.add(new ArrayList<>());
                redPeso.add(new ArrayList<>());
            }
//            System.out.println(red);
//            System.out.println(redPeso);
            //cargo con las aristas
            int cantAristas = sc.nextInt();
            int cantNuevasAristas = sc.nextInt();
            int nodoOrigen = sc.nextInt() -1;
            int nodoDestino = sc.nextInt() -1;
            for (int j = 0; j < cantAristas+cantNuevasAristas; j++) {
                origen = sc.nextInt()-1;
                red.get(origen).add(sc.nextInt()-1);
                redPeso.get(origen).add(sc.nextInt());
            }
            //Evaluar si cargaba bien la entrada
//            System.out.println(red);
//            System.out.println(redPeso);
            
//            System.out.println("nodo origen: " + nodoOrigen + " nodo destino: " + nodoDestino);
            abiertosNodos.add(nodoOrigen);
            abiertosPesos.add(0);
            int minimoIndex;
            //Comienza la busqueda del camino mas corto
            while(true){
//                System.out.println("abiertos: " + abiertosNodos);
//                System.out.println("pesos   : " + abiertosPesos);
//                System.out.println(visitados);
//                System.out.println("-- -- --");
                
                //si esta vacio, no se encontro camino y retorno -1
                if (abiertosPesos.isEmpty()) {
                    System.out.println("-1");
                    break;
                }
                //busco el minimo camino abierto
                minimoIndex = getMinimo(abiertosPesos);
                
                //Pregunto si el minimo en el nodo final termino
                if(abiertosNodos.get(minimoIndex) == nodoDestino){
//                    System.out.println("Nodo destino alcanzado!");
                    System.out.println(abiertosPesos.get(minimoIndex));
                    break;
                }
                
                int aux = abiertosNodos.get(minimoIndex); //nodoMinimo (valor)
//                System.out.println("nodo con valor minimo: " + aux);
//                System.out.println("hijos de " + aux + ": " + red.get(aux));
//                System.out.println("         " + redPeso.get(aux));

                //expando los hijos del nodo con camino minimo
                ArrayList<Integer> hijos = red.get(aux);
                ArrayList<Integer> pesoHijos = redPeso.get(aux);
                        
                int cantHijos = hijos.size();
                for (int w = 0; w < cantHijos; w++) {
                    //preguntar si ya existe el noto en la lista de abiertos
                    int unHijo = hijos.get(w);
//                    System.out.println("Nodo hijo: " + unHijo);
                    if (!visitados.contains(unHijo)) {
                        if (abiertosNodos.contains(unHijo)) {
                            //Si ya esta en abiertos, actualizo el peso con el de menos coste
//                            System.out.println("Ya esta en abiertos");
                            int pesoActual = abiertosPesos.get(abiertosNodos.indexOf(unHijo));
                            int pesoNuevo = abiertosPesos.get(minimoIndex) + pesoHijos.get(w);
                            if (pesoActual > pesoNuevo) {
//                                System.out.println("Actualizo peso por el menor");
                                abiertosPesos.set(abiertosNodos.indexOf(unHijo), pesoNuevo);
                            }
                        } else {
                            //si no esta, lo agrego y calculo el costo hasta el
                            //para eso sumo al costo acumulado el peso de la arista
//                            System.out.println("No esta en abiertos, lo agrego");
                            abiertosPesos.add(abiertosPesos.get(minimoIndex) + pesoHijos.get(w));
                            abiertosNodos.add(unHijo);
                        }
                    }
//                    System.out.println(abiertosNodos);
//                    System.out.println(abiertosPesos);
                }
                visitados.add(abiertosNodos.get(minimoIndex));
                abiertosNodos.remove(minimoIndex);                
                abiertosPesos.remove(minimoIndex);
            }
            abiertosNodos = new ArrayList<>();
            abiertosPesos = new ArrayList<>();
            visitados = new ArrayList<>();
//            System.out.println(" -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        }
    }
    
    private static int getMinimo(ArrayList<Integer> abiertosPesos){
        int posicion = 0, min = 9999999;
//        System.out.println("funcion buscar minimo recibe" + abiertosPesos);
        for (int i = 0; i < abiertosPesos.size(); i++) {
            if (min > abiertosPesos.get(i)) {
                min = abiertosPesos.get(i);
                posicion = i;
            }
        }
//        System.out.println("Minimo: " + min + " nodo " + abiertosPesos.get(posicion));
        return posicion;
    }
}

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