package com.challenge.puntuacion;

import jugadores.Jugador;

public class Puntuacion {

    NumerosRandom numerosRandom = new NumerosRandom();
    
    // Generamos los puntos, el numero 99 representa victoria 
    public final String puntos[] = new String[]{"15", "30", "40", "V", "WIN"};

    public Puntuacion() {
    }

    public String generarPuntos(String punto) {
        for (int i = 0; i < puntos.length; i++) {
            if (punto.equals(puntos[i])) {
                return (puntos[i + 1]);
            } else if (punto.equals("0")) {
                return puntos[0];
            }
        }
        return puntos[0];
    }

    public String generarJuegos(String punto) {
        String juego = "0";
        if (punto.equals("WIN")) {
            juego = "1";
        }
        return juego;
    }
    
    public boolean entrarASets(int juego){
        if(juego==6){
            return true;
        }
        return false;
    }

    public int generarSets(int juego, Jugador jugador) {
        int set = 0;
        if (juego == 6) {
            set = 1;
            jugador.juego.add(Integer.toString(juego));
        }
        return set;
    }
    

}
