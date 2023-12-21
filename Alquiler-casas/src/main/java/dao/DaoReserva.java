
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Ofrece;
import entidades.Reserva;
import entidades.Usuario;

//import entidades.Libro;
public class DaoReserva {
	Dao dao;
	public DaoReserva(Dao dao) {
		this.dao=dao;
	}
	
	
	
	//public List<Ofrece> listado() throws SQLException,Exception{
		//return buscarPorCasa(null);
	//}
	
	public List<Reserva> buscarPorCasa(int casaId) throws SQLException,Exception{
		
		ArrayList<Reserva> listado = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM Reserva INNER JOIN Ofrece ON Ofrece.Reserva_id = Reserva.Id"; // sentencia a ejecutar
			//if (provincia != null)
				ordenSQL += " WHERE Casa_id=?";
			st=dao.prepareStatement(ordenSQL);
			//if (provincia != null)
				st.setInt(1, casaId);
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Reserva r = new Reserva();
				r.setId(rs.getInt("id"));
            	r.setUsuarioId(rs.getInt("usuario_id"));
				listado.add(r);
			}			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception ex) {
			//ex.printStackTrace();
			throw ex;
		}
		finally {      
			// liberamos los recursos en un finally para asegurarnos que se cierra
			// todo lo abierto
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
		}
		return listado; // retornamos el resultado en forma de array
	}
	

	public List<Ofrece> buscarPorUsuario(int usuarioId) throws SQLException,Exception{
		
		ArrayList<Ofrece> listado = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM Ofrece INNER JOIN Reserva ON Ofrece.reserva_id=Reserva.id"; // sentencia a ejecutar
			//if (provincia != null)
				ordenSQL += " WHERE Reserva.usuario_id=?";
			st=dao.prepareStatement(ordenSQL);
			//if (provincia != null)
				st.setInt(1, usuarioId);
			rs = st.executeQuery(ordenSQL);	
			while (rs.next()) {
				Ofrece o = new Ofrece();
				o.setId(rs.getInt("id"));
            	o.setCasaId(rs.getInt("casa_id"));
				Reserva r = new Reserva();
				r.setId(rs.getInt("id"));
            	r.setUsuarioId(rs.getInt("usuario_id"));
				r.setUsuarioId(rs.getInt("Reserva.usuario_id"));
            	o.setReserva(r);
            	o.setFechaInicioSemana(rs.getDate("fecha_inicio_semana"));
				listado.add(o);
			}			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception ex) {
			//ex.printStackTrace();
			throw ex;
		}
		finally {      
			// liberamos los recursos en un finally para asegurarnos que se cierra
			// todo lo abierto
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
		}
		return listado; // retornamos el resultado en forma de array
	}
	


	public boolean inserta(Reserva r) throws SQLException, Exception {
        PreparedStatement st=null;
        try {
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncioCreaSerie
        	String ordenSQL = "INSERT INTO Reserva (usuario_id ) VALUES(?)";//cada interrogacion es una cosa que no se y me dan 
            String generatedColumns[] = {"id"};//array de columnas que se generan automaticamente
            st = dao.prepareStatement(ordenSQL,generatedColumns);
            st.setInt(1, r.getUsuarioId());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            // Cargo resto de valores de o
            r.setId(rsgk.getInt(1)); 
            st.close();
            return true;
        } catch (SQLException se) {
        	se.printStackTrace();
//            throw se;
        } catch (Exception e) {
        	e.printStackTrace();
//            throw e;
        } finally{
         	if(st!=null)
                st.close();
        }
        return false;
    }

	public boolean elimina(Reserva r) throws SQLException, Exception {
        PreparedStatement st=null;
        try {
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncioCreaSerie
        	String ordenSQL = "DELETE FROM Reserva WHERE id=?";//cada interrogacion es una cosa que no se y me dan
            st = dao.prepareStatement(ordenSQL);
            st.setInt(1, r.getId());
            int deleted = st.executeUpdate();
            st.close();
            return deleted > 0;
        } catch (SQLException se) {
        	se.printStackTrace();
//            throw se;
        } catch (Exception e) {
        	e.printStackTrace();
//            throw e;
        } finally{
         	if(st!=null)
                st.close();
        }
        return false;
    }
	
}

