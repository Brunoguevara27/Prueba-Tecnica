package com.challenge.puntuacion;

import java.util.Random;


public class NumerosRandom {
 
   public Random random = new Random();
   
   public int generarNumeroPunto(){
       return (random.nextInt(5));
   }
   
   public int generarNumeroProbabilidad(int probabilidad){
       return (random.nextInt(probabilidad));
   }
   
   public int generarNumeroPerdedorPunto(){
       return (random.nextInt(3));
   }
   
   public int generarDosNumeros(){
       return (random.nextInt(2) + 1);
   }
   
  

    
}
