/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialpc;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author pichon
 */
public class Akbar2 {
    
    private static ArrayList<ArrayList<Integer>> ciudades;
    private static HashSet<Integer> ciudadesProtejidas;
    private static Stack<Object> visitados;
    
    public static void main(String[] args) throws IOException{
        ArrayList<Boolean> proteccion;
        Reader sc =  new Reader();
        boolean banderaSalir = false;
        
        int cantProblemas = sc.nextInt();
        //Tratar cada problema
        for (int i = 0; i < cantProblemas; i++) {
            banderaSalir = false;
            ciudades = new ArrayList<>();
            proteccion = new ArrayList<>();
            int cantCiudades = sc.nextInt();
            for (int j = 0; j < cantCiudades; j++) {
                ciudades.add(new ArrayList<>());
                proteccion.add(false);
            }
            int cantCaminos = sc.nextInt();
            int cantSoldados = sc.nextInt();
            //Cargamos los caminos entre ciudades
            for (int j = 0; j < cantCaminos; j++) {
                int ciudad1 = sc.nextInt()-1;
                int ciudad2 = sc.nextInt()-1;
                ciudades.get(ciudad1).add(ciudad2);
                ciudades.get(ciudad2).add(ciudad1);
            }
            //Cargamos la proteccion
//            System.out.println(ciudades);
//            System.out.println(proteccion);
//            System.out.println("Comienza");
            for (int j = 0; j < cantSoldados; j++) {
                ciudadesProtejidas = new HashSet<Integer>();
                int ciudad = sc.nextInt()-1;
                int fuerza = sc.nextInt();
                visitados = new Stack<>();
//                System.out.println(fuerza +" "+ ciudad);
                ciudadesConectadasADIstancia(fuerza, ciudad, 99999);
//                System.out.println(ciudadesProtejidas);
                //que mierda pasa acaaaa esto no esta funcionando
                for (Integer s : ciudadesProtejidas) {
//                    System.out.println(s);
                    boolean protec = proteccion.get(s);
                    if (protec) {
                        System.out.println("No");
                        banderaSalir = true;
                        break;
                    } else {
                        proteccion.set(s, true);
                    }
//                    System.out.println(proteccion);
                }
                if (banderaSalir) {
                    break;
                }
            }
            if (!banderaSalir) {
                boolean misterio = true;
                for (int j = 0; j < cantCiudades; j++) {
                    if (!proteccion.get(j)) {
                        System.out.println("No");
                        misterio = false;
                        break;
                    }
                }
                if (misterio) {
                    System.out.println("Yes");    
                }
            }
            
        }
    }
    
    public static void ciudadesConectadasADIstancia(int fuerza, int ciudad, int padre){
        if (fuerza == 0) {
            ciudadesProtejidas.add(ciudad);
        } else {
            ciudadesProtejidas.add(ciudad);
            ArrayList<Integer> vecinos = ciudades.get(ciudad);
            visitados.push(ciudad);
//            System.out.println(visitados);
            for (int i = 0; i < vecinos.size(); i++) {
                if (!visitados.contains(vecinos.get(i))) {
                    ciudadesConectadasADIstancia((fuerza-1), vecinos.get(i), ciudad);    
                }                  
            }
            visitados.pop();
        }
    }
    
    
}
/*
2
3 2 2
1 2
2 3
1 2
2 0
4 5 2
1 4
1 2
1 3
4 2
3 4
2 1
3 0

*/


