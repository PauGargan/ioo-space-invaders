package modelo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Tablero {
	
	private String direccionArchivo = System.getProperty("user.dir") + "/src/tablero.csv";

    private List<ItemRanking> ListaTop10;
   
    public Tablero() {
    	ListaTop10 = leerTableroActual();
    }

    public void agregarOrdenado(int puntaje, String nombre) {
    	ListaTop10.add(new ItemRanking(puntaje, nombre));
    	
    	Collections.sort(ListaTop10, new Comparator<ItemRanking>()
        {
        		public int compare(ItemRanking o1, ItemRanking o2)
        		{
        			return	o2.getPuntos() - o1.getPuntos();
        		}
        });
    	
    	if(ListaTop10.size() > 10)
    		ListaTop10 = ListaTop10.subList(0, 10); // acota a 10

    }  
    
    public List<ItemRanking> getTablero() {
    	return ListaTop10;
    }
    
    private List<ItemRanking> leerTableroActual() {
    	List<ItemRanking> ranking = new ArrayList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader(this.direccionArchivo))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        String[] values = line.split(",");
    	        ItemRanking item = new ItemRanking(Integer.parseInt(values[0]), values[1]);
    	        ranking.add(item);
    	    }
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	return ranking;
    }
    
    private void grabarTableroActual() {
    	List<String[]> csvContenido = generarCSV();
        
        try {
	        FileWriter writer = new FileWriter(direccionArchivo);
	        
	        for(int i=0; i < csvContenido.size(); i++) {
	        	writer.append(csvContenido.get(i)[0]);
	        	writer.append(",");
	        	writer.append(csvContenido.get(i)[1]);
	        	writer.append("\n");
	        }
	
	        writer.flush();
	        writer.close();
        } catch(Exception e) {
        	System.out.println(e);
        }
        
    }
    
    private List<String[]> generarCSV() {
    	
    	List<String[]> listado = new ArrayList<>();
    	for(ItemRanking i : ListaTop10) {
    		listado.add(new String[] {Integer.toString(i.getPuntos()), i.getNombre()});
    	}
    	
    	return listado;
    }
    
    
    @Override
	public String toString() {
		String resultado = "";
		
		for(int i=0; i < this.ListaTop10.size(); i++) {
			resultado += ListaTop10.get(i).getPuntos() + " " + ListaTop10.get(i).getNombre() + "\n";
		}
		
		return resultado; 
	}



	private class ItemRanking {
    	private int puntos;
    	private String nombre;
    	
    	public ItemRanking(int puntos, String nombre) {
    		this.puntos = puntos;
    		this.nombre = nombre;
    	}

		public int getPuntos() {
			return puntos;
		}

		public void setPuntos(int puntos) {
			this.puntos = puntos;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
    }
}