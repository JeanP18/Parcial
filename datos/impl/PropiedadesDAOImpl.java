
package datos.impl;

import java.sql.PreparedStatement;
import datos.PropiedadesDAO;
import dominio.Propiedades;
import java.util.List;
import java.sql.SQLException;
import database.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;





public class PropiedadesDAOImpl implements PropiedadesDAO<Propiedades>{
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropiedadesDAOImpl(){
        CON=Conexion.getInstancia();
    }
    

    @Override
    public List<Propiedades> listar(String texto) {
         List<Propiedades> registros= new ArrayList();
        try{
           ps=CON.conectar().prepareStatement ( "SELECT  id, nombre from propiedades where nombre like ?");
           ps.setString(1 , "%" + texto + "%");
           rs=ps.executeQuery();
            while (rs.next()) {                 
                //registros.add(new Propiedades(rs.getInt(1),rs.getString(2)));
            }             
            ps.close();             
            rs.close(); 
        }catch (SQLException e) { 
            System.out.println(e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }       
        return registros;
    }

    @Override
    public boolean insertar(Propiedades obj) {
        resp=false;
        try{
            ps=CON.conectar().prepareStatement("INSERT INTO PROPIEDADES (nombre)VALUE (?)");
            ps.setString(1,obj.getNombre());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            
            ps.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Propiedades obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
