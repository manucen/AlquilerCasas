//Clase que ofrecerá los métodos para interactuar con la tabla

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Usuario;

public class DaoUsuario {
	Dao daoConexion=null;
	//Nos pasa una conexion en el constructor y hace conexion con la bdd
	public DaoUsuario(Dao daoConexion) {
		this.daoConexion=daoConexion;
	}
	public Usuario buscarPorId(int id)throws SQLException,Exception {
		return buscar(id, null);
	}
	public Usuario buscarPorEmail(String email)throws SQLException,Exception {
		return buscar(-1, email);
	}
	
	private Usuario buscar(int id, String email)throws SQLException,Exception {
		Usuario u=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
        	String cond;
        	if(email != null){
        		cond = "email=?";
        	}else {
        		cond = "id=?";
        	}
            String ordenSQL = "SELECT * FROM Usuario"+
            				  " WHERE " + cond;
            st = daoConexion.prepareStatement(ordenSQL);
            if(email != null){
            	st.setString(1, email);
        	}else {
        		st.setInt(1, id);
        	}
            rs=st.executeQuery();   
            if(rs.next()) {             
            	u=new Usuario();
            	u.setId(rs.getInt("id"));
            	u.setNombre(rs.getString("nombre"));
            	u.setApellidos(rs.getString("apellidos"));
            	u.setEmail(rs.getString("email"));
            	u.setTelefono(rs.getString("telefono"));
            	u.setPuntos(rs.getInt("puntos"));
            	u.setPassword(rs.getString("password"));
            }
        } 
        catch (SQLException se) {throw se;} 
        //catch (BibliotecaException be) {throw be;}
        catch (Exception e) {throw e;}
        finally{
         	if(rs!=null)
                rs.close();
         	if(st!=null)
                st.close();
        }
        return u;
	}	
	
	public boolean inserta (Usuario u) throws SQLException, Exception {
		PreparedStatement st=null;//lcase sentencia sql pero con prepare se pueden poner los parametros(?)
        try {
            // Las secuencias siguen el formato seq_Usuarios_id
            String ordenSQL = "INSERT INTO Usuario (nombre, apellidos ,email, telefono, password, puntos) VALUES(?,?,?,?,?,0)";//cada interrogacion es una cosa que no se y me dan 
            String generatedColumns[] = {"id"};//array de columnas que se generan automaticamente
            st = daoConexion.prepareStatement(ordenSQL, generatedColumns);
            st.setString(1, u.getNombre());
            st.setString(2, u.getApellidos());
            st.setString(3, u.getEmail());
            st.setString(4, u.getTelefono());
            st.setString(5, u.getPassword());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            u.setId(rsgk.getInt(1)); 
            st.close();
            return true;
        } catch (SQLException se) {
        	se.printStackTrace();
           // throw se;
        } catch (Exception e) {
           //throw e;
        	e.printStackTrace();
        } finally{
         	if(st!=null)
                st.close();
        }
    	return false;
	}
	
	
	public boolean modifica(Usuario u) throws SQLException,Exception{
		PreparedStatement st=null;//lcase sentencia sql pero con prepare se pueden poner los parametros(?)
        try {
            // Las secuencias siguen el formato seq_Usuarios_id
            String ordenSQL = "UPDATE Usuario SET nombre=?,apellidos=?, email=?, telefono=?, puntos=?, password=? WHERE id=?";//cada interrogacion es una cosa que no se y me dan 
            st = daoConexion.prepareStatement(ordenSQL);
            st.setString(1, u.getNombre());
            st.setString(2, u.getApellidos());
            st.setString(3, u.getEmail());
            st.setString(4, u.getTelefono());
            st.setInt(5, u.getPuntos());
            st.setString(6, u.getPassword());
            st.setInt(7, u.getId());
            int modificadas = st.executeUpdate();
            st.close();
            return modificadas == 1;
        } catch (SQLException se) {
        	se.printStackTrace();
           // throw se;
        } catch (Exception e) {
           //throw e;
        	e.printStackTrace();
        } finally{
         	if(st!=null)
                st.close();
        }
        return false;
	}

}