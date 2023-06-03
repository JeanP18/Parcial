package negocio;

import datos.PropiedadesDAO;
import dominio.Propiedades;
import datos.impl.PropiedadesDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PropiedadesControl {
    
    private final PropiedadesDAO DATOS;
    private Propiedades obj;
    
    public PropiedadesControl() {
        
        this.DATOS = new PropiedadesDAOImpl();
        this.obj = new Propiedades();
    }
    
    private DefaultTableModel modeloTabla;
    public DefaultTableModel listar(String texto){
        
        List<Propiedades> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos = {"ID", "NOMBRE","DIRECCION","CARACTERISTICAS","ESTADO","PRECIO_ALQUILER"};
        String[] registro = new String[2];
        this.modeloTabla = new DefaultTableModel(null, titulos);
        
        for (Propiedades item : lista) {
            
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            this.modeloTabla.addRow(registro);
          
         }
        
         return this.modeloTabla;
    }
    
}
