package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDb {
	
	private String usuario = "SA";
	private String senha = "";
	private String PathBase = "C:\\Users\\GABRIEL\\eclipse-workspace\\Trabalho POO OF\\Dados\\dados";
	private String URL = "jdbc:hsqldb:file:" + PathBase + ";";
	
	public Connection conectar() {
		
		try {
			return DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException e) {
			throw new Error("SQLException");
			
		}
	}
}
