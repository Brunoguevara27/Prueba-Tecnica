
package jugadores;

import java.util.ArrayList;
import java.util.List;


public class Jugador {
    
    public String nombre;
    
    public int probabilidad; 
    
    public List<String> juego = new ArrayList<>();

    public Jugador() {
    }

    public Jugador(String nombre, int posibilidad) {
        this.nombre = nombre;
        this.probabilidad = posibilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    public List<String> getJuego() {
        return juego;
    }

    public void setJuego(List<String> juego) {
        this.juego = juego;
    }

 

  
 

    
   
    
}
