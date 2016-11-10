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
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
public class Insectos {
    
    public static void main(String[] args) throws IOException{
        ArrayList<ArrayList<Integer>> red;
//        Scanner sc = new Scanner(System.in);
        Reader sc = new Reader();
        int cantEscenarios = sc.nextInt();
        int cantInsectos;
        int cantRelaciones;
        boolean bandera = false;
        
        for (int i = 0; i < cantEscenarios; i++) {
            System.out.println("Scenario #"+(i+1)+":");
            cantInsectos = sc.nextInt();
            cantRelaciones = sc.nextInt();
            
            red =  new ArrayList<>();
            for (int j = 0; j < cantInsectos; j++) {
                red.add(new ArrayList<>());
            }
            for (int j = 0; j < cantRelaciones; j++) {
                int insecto1 = sc.nextInt()-1;
                int insecto2 = sc.nextInt()-1;
                red.get(insecto1).add(insecto2);
                red.get(insecto2).add(insecto1);
            }
//            System.out.println(red);
            //armo la lista del sexo del insecto
            char[] sexo = new char[cantInsectos];
            for (int j = 0; j < cantInsectos; j++) {
                sexo[j] = 'N';
            }
//            System.out.println(Arrays.toString(sexo));
            bandera = true;
            
            for (int j = 0; j < cantInsectos; j++) {
                //lo pongo hembra por defecto
                if (sexo[j]=='N') {
                        sexo[j] = 'H';
                    }
                int cantHijos = red.get(j).size();
                ArrayList<Integer> hijos = red.get(j);
//                System.out.println("Hijos de insecto "+j+": "+hijos);
                
                if (sexo[j] == 'H') {
                    //Si es hembra, pongo a todos sus hijo como machos si no tiene sexo asignado
                    for (int k = 0; k < cantHijos; k++) {
                        if (sexo[hijos.get(k)] == 'H') {
                            System.out.println("Suspicious bugs found!");
                            bandera = false;
                            break;
                        }else{
                            if (sexo[hijos.get(k)]=='N') {
                                sexo[hijos.get(k)] = 'M';
                            }
                        }
                    }
                    if(!bandera){
                        break;
                    }
                } else{
                    //Por el no, es macho, y pongo a todos sus hijo como hembras
                    for (int k = 0; k < cantHijos; k++) {
                        if (sexo[hijos.get(k)]=='M') {
                            System.out.println("Suspicious bugs found!");
                            bandera = false;
                            break;
                        }else{
                            if (sexo[hijos.get(k)]=='N') {
                                sexo[hijos.get(k)] = 'H';
                            }
                        }
                    }
                    if(!bandera){
                        break;
                    }
                }   
//                System.out.println(Arrays.toString(sexo));
            }
            if (bandera) {
                System.out.println("No suspicious bugs found!");
            }
        }
    }
}
