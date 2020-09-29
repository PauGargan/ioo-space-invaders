package modelo;


import java.util.*;


public class Tablero {

    private List<PuntajePartida> ListaTop10;

    public Tablero() {
    	ListaTop10 = new ArrayList<PuntajePartida>();
    }

    public void agregarOrdenado(PuntajePartida puntaje) {
        ListaTop10.add(puntaje);
        Collections.sort(ListaTop10, new Comparator<PuntajePartida>()
        {
        		public int compare(PuntajePartida o1, PuntajePartida o2)
        		{
        			return	o2.getPuntaje() - o1.getPuntaje();
        		}
        });
        
       ListaTop10 = ListaTop10.subList(0, 10); // acota a 10

    }  
    
    public List<PuntajePartida> getTablero() {
    	return ListaTop10;
    }
}