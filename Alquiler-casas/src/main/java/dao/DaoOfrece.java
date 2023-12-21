
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
public class DaoOfrece {
	Dao dao;
	public DaoOfrece(Dao dao) {
		this.dao=dao;
	}
	
	
	
	//public List<Ofrece> listado() throws SQLException,Exception{
		//return buscarPorCasa(null);
	//}
	
	public List<Ofrece> buscarPorCasa(int casaId) throws SQLException,Exception{
		
		ArrayList<Ofrece> listado = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM Ofrece LEFT JOIN Reserva ON Ofrece.reserva_id=Reserva.id"; // sentencia a ejecutar
			ordenSQL += " WHERE casa_id = " + casaId;
			st=dao.prepareStatement(ordenSQL);
//			st.setInt(1, casaId);
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Ofrece o = new Ofrece();
				o.setId(rs.getInt("id"));
            	o.setCasaId(rs.getInt("casa_id"));
            	rs.getInt("reserva_id");
            	if (!rs.wasNull()) {
					Reserva r = new Reserva();
					r.setId(rs.getInt("reserva_id"));
					r.setUsuarioId(rs.getInt("usuario_id"));
	            	o.setReserva(r);
				}
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


	public boolean inserta(Ofrece o) throws SQLException, Exception {
        PreparedStatement st=null;
        try {
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncioCreaSerie
        	String ordenSQL = "INSERT INTO Ofrece (casa_id, fecha_inicio_semana ) VALUES(?,?)";//cada interrogacion es una cosa que no se y me dan 
            String generatedColumns[] = {"id"};//array de columnas que se generan automaticamente
            st = dao.prepareStatement(ordenSQL,generatedColumns);
            st.setInt(1, o.getCasaId());
            st.setDate(2, o.getFechaInicioSemana());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            
            // Cargo resto de valores de o
            o.setId(rsgk.getInt(1)); 
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
	
	
	public boolean reserva(Ofrece o) throws SQLException,Exception{
		PreparedStatement st=null;//lcase sentencia sql pero con prepare se pueden poner los parametros(?)
        try {
            // Las secuencias siguen el formato seq_Usuarios_id
            String ordenSQL = "UPDATE Ofrece SET reserva_id=? WHERE id=?";//cada interrogacion es una cosa que no se y me dan 
            st = dao.prepareStatement(ordenSQL);
            if (o.getReserva() != null)
            	st.setInt(1, o.getReserva().getId());
            else
            	st.setNull(1, java.sql.Types.INTEGER);
            st.setInt(2, o.getId());
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

