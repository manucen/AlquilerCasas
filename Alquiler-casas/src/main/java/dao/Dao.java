package dao;
//> los commit o rollback deberían estar aquí
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import conexiones.Conexion;

public class Dao {
	private Connection conexion=null;
	// Sólo ejecuto un close o closeError
	private boolean cerrado=false;
	public Dao() throws SQLException,Exception {
		// Crea la conexión a la BD
		Conexion c = new Conexion();
		Connection con = null;
		conexion = c.getConexion();  
	}
	// Cierre sin error
	public void close() throws SQLException {
		if (!cerrado) conexion.close();	
		cerrado=true;
	}
	// cierre con error
	public void closeError() throws SQLException {
		//if (!cerrado) conexion.closeError();	
		cerrado=true;
	}
	public Connection getConnection() {
		return conexion;
	}
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return getConnection().prepareStatement(sql);
	}
	public PreparedStatement prepareStatement(String sql,String [] gc) throws SQLException {
		return getConnection().prepareStatement(sql,gc);
	}
	public Statement createStatement() throws SQLException {
		return getConnection().createStatement();
	}
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		// Si nadie ha cerrado el dao deshago operaciones
		if (!cerrado) this.closeError();
	}

}
