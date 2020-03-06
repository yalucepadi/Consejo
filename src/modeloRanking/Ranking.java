
package modeloRanking;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Ranking {
    private  StringProperty entidades;
    
    private     IntegerProperty nroMenciones ;
    
    public Ranking(String entidad,Integer nroMencion){
        this.entidades=new SimpleStringProperty(entidad);
        this.nroMenciones= new SimpleIntegerProperty(nroMencion);
        
    
    }

    public Ranking() {
    }
    

    public String getEntidades() {
        return entidades.get();
    }

    public void setEntidades(String entidades) {
        this.entidades.set(entidades);
    }

    public Integer getNroMenciones() {
        return nroMenciones.get();
    }

    public void setNroMenciones(Integer nroMenciones) {
        this.nroMenciones.set(nroMenciones);
    }
    
    
    




  

    
    
}
