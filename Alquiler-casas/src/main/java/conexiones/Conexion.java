package conexiones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

/*
 *  @Autor: F.Ortiz retocado por Julio León
 *  Clase para obtener una conexión (Objeto java.sql.Connection)
 *  a una base de datos Oracle. 
 */
public class Conexion {

	public Conexion() {
		// TODO Auto-generated constructor stub
	}
	/*
	 *  Obtiene un objecto java.sql.Connection para poder crear y ejecutar consultas sobre 
	 *  una base de datos, Oracle en nuestro caso.
	 *  SQLException: Si se produce un error en el acceso a la base de datos
	 */
	
	// CASO 3
	/*
	public Connection getConexion() throws SQLException {
		// Uso el caso 3
		Connection con=null;
		try {
		  con=DriverManager.getConnection("jdbc:oracle:thin:@oracle-clouding.s2daw.com:1521:ORCLCDB","usuariodb","Azarquiel2021");
		} catch (SQLException e) {
			// Imprimo el mensaje y propago el error
			e.printStackTrace();
			throw e;
		}
		return con;
	}*/
	
	// CASO 1 (usando jks)
	/*
	public Connection getConexion() throws SQLException {
		Connection con = null;
		try {
			// El fichero ojdbc.properties hay que configurarlo para que use JKS
			// (comentar wallet y descomentar y conf lines de jks)
			// En C:/Diurno/oracle/instantclient_19_12/network/admin tenemos esa configuración
			final String DB_URL = "jdbc:oracle:thin:@s2dawdwes2021_low";
			final String DB_USER = "usuariodb";
			final String DB_PASSWORD = "Azarquiel2021";
			Properties info = new Properties();
			// Propiedades de la Conexión relativas a SSL
			info.put(OracleConnection.CONNECTION_PROPERTY_THIN_SSL_SERVER_DN_MATCH,"true");
			info.put(OracleConnection.CONNECTION_PROPERTY_TNS_ADMIN,"C:/Diurno/oracle/instantclient_19_12/network/admin");
			info.put(OracleConnection.CONNECTION_PROPERTY_THIN_SSL_VERSION,"1.2");
			// Propiedades de la conexión relativas a JKS (los datos del fichero ojdbc.properties
			info.put(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORE,"C:/Diurno/oracle/instantclient_19_12/network/admin/keystore.jks");
			info.put(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTOREPASSWORD,"Azarquiel2021");
			info.put(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORE,"C:/Diurno/oracle/instantclient_19_12/network/admin/truststore.jks");       
			info.put(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTOREPASSWORD,"Azarquiel2021");
			// Usuario/password de la BD
			info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
			info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
			info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
			
			// Sirve para mostrar los valores reales de las propiedades
			System.out.println(OracleConnection.CONNECTION_PROPERTY_THIN_SSL_SERVER_DN_MATCH);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_TNS_ADMIN);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_THIN_SSL_VERSION);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORE);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTOREPASSWORD);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORE);  
			System.out.println(OracleConnection.CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTOREPASSWORD);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_USER_NAME);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_PASSWORD);
			System.out.println(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH);
			OracleDataSource ods = new OracleDataSource();
			ods.setURL(DB_URL);
			ods.setConnectionProperties(info);
			con = ods.getConnection();
		} catch (SQLException e) {
			// Imprimo el mensaje y propago el error
			e.printStackTrace();
			throw e;
		}
		return con;
	} */
	// CASO 1 (usando Wallet)
	public Connection getConexion() throws SQLException {
		Connection con = null;
		try {
			// EL caso 1 con wallet usamos el fichero ojdbc.properties con la 
			// configuración original, en Diurno/test disponemos de esa configuración
			// Necesita además oraclepki, osdt_core y osdt_cert.jar
			final String DB_URL = "jdbc:oracle:thin:@s2daw2022_high?TNS_ADMIN=C:/Diurno/oracle/instantclient_19_16/network/admin";
			final String DB_USER = "usuariodb";
			// >>>> SUSTITUIR POR PASSWORD ENCRIPTADO, NO ES BUENA IDEA QUE SE VEA
			final String DB_PASSWORD = "Azarquiel2022";
			Properties info = new Properties();
			// Usuario/password de la BD
			info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
			info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
			info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
			OracleDataSource ods = new OracleDataSource();
			ods.setURL(DB_URL);
			ods.setConnectionProperties(info);
			con = ods.getConnection();
		} catch (SQLException e) {
			// Imprimo el mensaje y propago el error
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return con;
	}

}
