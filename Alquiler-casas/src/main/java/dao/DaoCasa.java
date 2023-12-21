package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Usuario;

//import entidades.Libro;
public class DaoCasa {
	Dao dao;
	public DaoCasa(Dao dao) {
		this.dao=dao;
	}
	
	public Casa buscarPorId(int id)throws SQLException,Exception {
		Casa c=null;
        PreparedStatement st=null;
        ResultSet rs = null;
        try {
            String ordenSQL = "SELECT * FROM Casa"+
            				  " WHERE id=?";
            st = dao.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs=st.executeQuery();   
            if(rs.next()) {             
            	c=new Casa();
            	c.setId(rs.getInt("id"));
            	c.setDireccion(rs.getString("direccion"));
            	c.setProvincia(rs.getString("provincia"));
            	c.setRegion(rs.getString("region"));
            	c.setHabitaciones(rs.getInt("habitaciones"));
            	c.setCamas(rs.getInt("camas"));
            	c.setBanios(rs.getInt("banio"));
            	c.setUsuarioId(rs.getInt("usuario_id"));
            	c.setPuntos(rs.getInt("puntosdia"));
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
        return c;
	}
	
	public List<Casa> listado() throws SQLException,Exception{
		return buscarPorProvincia(null);
	}
	
	public List<Casa> buscarPorProvincia(String provincia) throws SQLException,Exception{
		
		ArrayList<Casa> listado = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM Casa"; // LEFT JOIN Ofrece ON Casa.id=Ofrece.casa_id"; // sentencia a ejecutar
			if (provincia != null)
				ordenSQL += " WHERE provincia=?";//no utilizamos el buscar por provincias
			st=dao.prepareStatement(ordenSQL);
			if (provincia != null)
				st.setString(1, provincia);
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Casa c = new Casa();
				c.setId(rs.getInt("id"));
            	c.setDireccion(rs.getString("direccion"));
            	c.setProvincia(rs.getString("provincia"));
            	c.setRegion(rs.getString("region"));
            	c.setHabitaciones(rs.getInt("habitaciones"));
            	c.setCamas(rs.getInt("camas"));
            	c.setBanios(rs.getInt("banio"));
            	c.setUsuarioId(rs.getInt("usuario_id"));
            	c.setPuntos(rs.getInt("puntosdia"));
				listado.add(c);
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
		
		DaoOfrece daoOfrece = new DaoOfrece(dao);
		for (Casa c : listado) {
			c.setOfertas(daoOfrece.buscarPorCasa(c.getId()));
			System.out.println(c.getOfertas());
		}
		
		return listado; // retornamos el resultado en forma de array
	}
	
	public List<Casa> buscarPorUsuario(int usuario_id) throws SQLException,Exception{
		
		ArrayList<Casa> listado = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			// Creamos un objeto Statement
			 
			String ordenSQL = "SELECT * FROM Casa WHERE usuario_id=?"; // sentencia a ejecutar
			st=dao.prepareStatement(ordenSQL);
			st.setInt(1, usuario_id);
			rs = st.executeQuery();
			while (rs.next()) {
				Casa c = new Casa();
				c.setId(rs.getInt("id"));
            	c.setDireccion(rs.getString("direccion"));
            	c.setProvincia(rs.getString("provincia"));
            	c.setRegion(rs.getString("region"));
            	c.setHabitaciones(rs.getInt("habitaciones"));
            	c.setCamas(rs.getInt("camas"));
            	c.setBanios(rs.getInt("banio"));
            	c.setUsuarioId(rs.getInt("usuario_id"));
            	c.setPuntos(rs.getInt("puntosdia"));
				listado.add(c);
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
		
		DaoOfrece daoOfrece = new DaoOfrece(dao);
		for (Casa c : listado) {
			c.setOfertas(daoOfrece.buscarPorCasa(c.getId()));
			System.out.println(c.getOfertas());
		}
		
		return listado; // retornamos el resultado en forma de array
	}


	public boolean inserta(Casa c) throws SQLException, Exception {
        PreparedStatement st=null;
        try {
            // El registro 1 de estado siempre será el de estado normal
            // uso un funcion creada con fichero CreaFuncioCreaSerie
        	 String ordenSQL = "INSERT INTO Casa (direccion, provincia, region, habitaciones, camas, banio, usuario_id, puntosdia ) VALUES(?,?,?,?,?,?,?,?)";//cada interrogacion es una cosa que no se y me dan 
             String generatedColumns[] = {"id"};//array de columnas que se generan automaticamente
            st = dao.prepareStatement(ordenSQL,generatedColumns);
            st.setString(1, c.getDireccion());
            st.setString(2, c.getProvincia());
            st.setString(3, c.getRegion());
            st.setInt(4, c.getHabitaciones());
            st.setInt(5, c.getCamas());
            st.setInt(6, c.getBanios());
            st.setInt(7, c.getUsuarioId());
            st.setInt(8, c.getPuntos());
            st.executeUpdate();
            ResultSet rsgk = st.getGeneratedKeys();
            rsgk.next();
            
            // Cargo resto de valores de c
            c.setId(rsgk.getInt(1)); 
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
	public boolean modifica(Casa c) throws SQLException,Exception{
		PreparedStatement st=null;//lcase sentencia sql pero con prepare se pueden poner los parametros(?)
        try {
            // Las secuencias siguen el formato seq_Usuarios_id
            String ordenSQL = "UPDATE Casa SET direccion=?,provincia=?, region=?, habitaciones=?, camas=?,banio=? WHERE id=?";//cada interrogacion es una cosa que no se y me dan 
            st = dao.prepareStatement(ordenSQL);
            st.setString(1, c.getDireccion());
            st.setString(2, c.getProvincia());
            st.setString(3, c.getRegion());
            st.setInt(4, c.getHabitaciones());
            st.setInt(5, c.getCamas());
            st.setInt(6, c.getBanios());
            st.setInt(7, c.getId());
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

