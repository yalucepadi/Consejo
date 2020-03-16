package modeloRanking;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ranking {

    /*private  String entidades;
    
    private     int nroMenciones ;
     */
    private final IntegerProperty id= new SimpleIntegerProperty();

    private StringProperty entidades=  new SimpleStringProperty();

    private IntegerProperty nroMenciones= new SimpleIntegerProperty();

   

  public String getEntidades() {
        return entidades.get();
    }

    public void setEntidades(String entidades) {
        this.entidades.set(entidades);
    }

    public int getNroMenciones() {
        return nroMenciones.get();
    }

    public void setNroMenciones(int nroMenciones) {
        this.nroMenciones.set(nroMenciones);
    }
  
      public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
    public String toString()
    {
        return String.format("%s %s",entidades);
    }
    public int toInt(){
    
    return  0;
    }
            
            
    
    /*public String getEntidades() {
        return entidades;
    }

    public void setEntidades(String entidades) {
        this.entidades = entidades;
    }

    public int getNroMenciones() {
        return nroMenciones;
    }

    public void setNroMenciones(int nroMenciones) {
        this.nroMenciones = nroMenciones;
    }
    
*/
    
    
    




  

    
    
     }
