package segundoparcialpc;


//package segundoparcialpc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pichon
 */
// Titulo: B. A and B and Compilation Errors
// Link: http://codeforces.com/problemset/problem/519/B

public class ErrorCompilacion {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cantErrores = sc.nextInt();
        int errores = 0;
        int errores1 = 0;
        int errores2 = 0;
        ArrayList<Integer> salida = new ArrayList<>();
        for (int i = 0; i < cantErrores; i++) {
            errores = errores + sc.nextInt();
        }
        for (int i = 0; i < cantErrores-1; i++) {
            errores1 = errores1 + sc.nextInt();
        }
        for (int i = 0; i < cantErrores-2; i++) {
            errores2 = errores2 + sc.nextInt();
        }
        System.out.println((errores-errores1));
        System.out.println((errores1-errores2));
            
    }
}
/*
entrada
5 
1 5 8 123 7 
123 7 5 1 
5 1 7
salida
8 
123

entrada 2
5 
1 5 8 123 7 
123 7 5 1 
5 123 7
salida
1
8

entrada 3
6 
1 4 3 3 5 7 
3 7 5 4 3 
4 3 5 7
salida
1 
3

entrada 4
3
1 2 3
3 2
2
salida
1
3

100000
351167748 351167748 536199253 22361850 941676274 536199253 941676274 22361850 351167748 426334027 596449963 426334027 511847416 22361850 596449963 22361850 770188930 536199253 511847416 536199253 511847416 482946680 511847416 482946680 511847416 536199253 880684411 482946680 426334027 511847416 22361850 880684411 941676274 351167748 351167748 511847416 511847416 482946680 482946680 482946680 351167748 941676274 770188930 536199253 482946680 482946680 351167748 880684411 482946680 351167748 22361850...
*/