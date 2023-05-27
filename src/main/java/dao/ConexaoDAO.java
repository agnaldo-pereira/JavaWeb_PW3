package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO{
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/alessandra?useTimezone=true&ServerTimezone=UTC";
	private String usuario = "root";
	private String senha = "";
	
	public Connection conectar() {
		try {
			
			Connection conexao = null;
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectado ao banco");
			return conexao;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
	
	
	
}
