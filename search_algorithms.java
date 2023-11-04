/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.esfm.progII.busqueda;


import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Luis
 */
public class Busqueda {
        private int array[];
        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_RED = "\u001B[31m";
        private static final String ANSI_BLUE = "\u001B[34m";
        
        public Busqueda(int n){
            array = new int[n]; 
        }
        
        public Busqueda(int n,int max){
            array = new int[n]; 
            Random rd = new Random();
            for(int i=0; i<array.length; i++){
                 array[i]=rd.nextInt(max);
            }
        }
       
        public static void main(String[] args){
        Busqueda array = new Busqueda(20,250);
        int n,x;
        
        Scanner toread= new Scanner(System.in);
        System.out.println("Elegir el metodo de busqueda a utilizar:\n"
                          +"1. Lineal\n"
                          +"2. Binario\n"
                          +"3. Hash\n"
                          +"Metodo a utilizar:");
        n=toread.nextInt();
        switch(n){
            case 1:System.out.println("Introducir un numero a buscar en el arreglo:");       
                   array.imprimir();
                   System.out.printf("El numero a buscar: ");
                   x = toread.nextInt();
                   array.Lineal(x);
                   break;
            case 2:System.out.println("Introducir un numero a buscar en el arreglo:");
                   array.ordenar();       
                   array.imprimir();
                   System.out.printf("El numero a buscar: ");
                   x = toread.nextInt();
                   array.Binaria(x);
                   break;
            case 3:System.out.println("Introducir un numero a bus1car en el arreglo:");
                   array.HashTable();       
                   array.imprimir();
                   System.out.printf("El numero a buscar: ");
                   x = toread.nextInt();
                   array.Hash(x);
                   break;
            }
        }
        
        private void imprimir(){
            for(int i=0;i<array.length;i++){
                System.out.printf("%d ",array[i]);
            }
            System.out.println();
        }
        
        private void buscando(int n){
            for(int i=0;i<array.length;i++){
                if(i==n) System.out.printf(ANSI_RED+"%d "+ANSI_RESET ,array[n]);
                else System.out.printf("%d ",array[i]);
            }
            System.out.println();
        }
        
        private void encontrado(int n){
            for(int i=0;i<array.length;i++){
                if(i==n) System.out.printf(ANSI_BLUE+"%d "+ANSI_RESET ,array[n]);
                else System.out.printf("%d ",array[i]);
            }
            System.out.println();
        }
        
        public void Lineal(int x){
        for(int i=0;i<array.length;i++){
            buscando(i);
            if(array[i]==x){
                encontrado(i);
                System.out.printf("El numero %d se encuentra en la posicion %d\n",x,i+1);
                return;
            }
        }
        System.out.printf("El numero %d no se encuentra en la lista\n",x);
        }
        
        public void ordenar(){
           int aux;
           
           for(int i=1;i<array.length;i++){
              while(i!=0 && array[i]<array[i-1]){
                   aux=array[i-1];
                   array[i-1]=array[i];
                   array[i]=aux;     
                   i--;
              }        
           }
        }
        
        public void Binaria(int x){ 
        int min = 0, max = array.length - 1; 
        while (min <= max){ 
            int m = min + (max - min) / 2; 
            buscando(m);
            if (array[m] == x){
                encontrado(m);
                System.out.printf("El numero %d se encuentra en la posicion %d\n",x,m+1);
                return;
            }
            if (array[m] < x) 
                min = m + 1; 
            else
                max = m - 1; 
            }
        System.out.printf("El numero %d no se encuentra en la lista\n",x);
        }
        
        private int mediocuadrado(int n){
            int l,aux,m1,m2;
            aux=n*n;
            l=(int)((int)(1+Math.log10(aux))*0.5);
            m1=(int)(aux*Math.pow(0.1,l-1))%10;
            m2=(int)(aux*Math.pow(0.1,l))%10;
            
            aux=(m1+m2*10)%20;
            return aux;
        }
        
        private void HashTable(){
            int hashtable[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
            
            for(int i=0; i<array.length;i++){
                int j=mediocuadrado(array[i]);
                if(hashtable[j]==-1){
                   hashtable[j]=array[i];
                }
                else{
                   while(j<array.length && hashtable[j]!=-1) j++;
                   if(j==array.length){
                     j=0;
                     while(j<array.length && hashtable[j]!=-1) j++;
                   }
                   hashtable[j]=array[i];
                }
            }
            
            for(int i=0;i<hashtable.length;i++){
                array[i]=hashtable[i];
            }
        }
        
        public void Hash(int x){
          int k;

          k=mediocuadrado(x);
          
          if(x==array[k]){
              buscando(k);
              encontrado(k);
              System.out.printf("El numero %d se encuentra en la posicion %d\n",x,k+1);
              return;
          }
          else{
            while(k<array.length && array[k]!=x){
                
                k++;
            }
            if(k==array.length){
                k=0;
                while(k<array.length && array[k]!=x){
                buscando(k);
                k++;
               }
            }
            if(array[k]==x){
                buscando(k);
                encontrado(k);
                System.out.printf("El numero %d se encuentra en la posicion %d\n",x,k+1);
                return;
            }
            
          }
          System.out.printf("El numero %d no se encuentra en la lista\n",x);
        }
}