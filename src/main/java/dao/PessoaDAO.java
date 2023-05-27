package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Pessoa;

public class PessoaDAO implements Serializable{
	
	private int TotalDeRegistros = 0; 
	
	public boolean inserir(Pessoa pessoa) {
		try {
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = 
					
					conn.prepareStatement("insert into usuarios (nome, email, senha, imagem) values (?,?,?,?)");
			
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getEmail());
			statement.setString(3, pessoa.getSenha());
			statement.setString(4, pessoa.getImagem());
		
			
			statement.execute();
			
			conn.close();
			
			return true;
		}
		catch (Exception e) { System.out.println(e.toString());
			return false;
		}
	}
	
	//public List<Pessoa> buscar(){
	public List<Pessoa> getPessoas(){
		List<Pessoa> lista = new ArrayList<Pessoa>();
		
		try {
			Connection  conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("select * from usuarios");
			
			ResultSet retorno = statement.executeQuery();
			
			while(retorno.next()) {
				Pessoa p = new Pessoa();
				
				p.setId(retorno.getInt("id"));
				p.setEmail(retorno.getString("email"));
				p.setNome(retorno.getString("nome"));
				p.setSenha(retorno.getString("senha"));
				p.setImagem(retorno.getString("imagem"));
				
				lista.add(p);
			}
			conn.close();
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		finally {
			return lista;
		}
	}
	
	public Pessoa buscarPorId(int id) {
		Pessoa p = new Pessoa();
		
		try {
			
			Connection  conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("select * from usuarios where id=?");
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				p.setId(resultSet.getInt("id"));
				p.setNome(resultSet.getString("nome"));
				p.setEmail(resultSet.getString("email"));
				p.setSenha(resultSet.getString("senha"));
				
			}
			return p;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public Pessoa buscarPorEmail(String email) {
		Pessoa p = new Pessoa();
		
		try {
			
			Connection  conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("select * from usuarios where email=?");
			
			statement.setString(1, email);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				p.setId(resultSet.getInt("id"));
				p.setNome(resultSet.getString("nome"));
				p.setEmail(resultSet.getString("email"));
				p.setSenha(resultSet.getString("senha"));
				p.setCodigo(resultSet.getInt("codigo"));
			}
			return p;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public Pessoa login(String email, String senha) {
		try {

			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = 
					conn.prepareStatement
					("select * from usuarios where email=? and senha=?");
			
			statement.setString(1, email);
			statement.setString(2, senha);
			
			ResultSet resultSet = statement.executeQuery();
			
			//Forma 1 - só validar 
			// O retorno do método tem que ser um booleano.
			/*
			if(resultSet.next()) {
				return true;
			}
			else 
				return false;
			*/
			
			//Forma 2 - Retornar um objeto preenchido
			if(resultSet.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(resultSet.getInt("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setEmail(resultSet.getString("email"));
				conn.close();
				return pessoa;
			}
			else {
				conn.close();
				return null;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}		
	}
	
	public Pessoa atualizar(Pessoa p) {
		try {
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("update usuarios set nome=?, email=? where id=?");
			
			statement.setString(1, p.getNome());
			statement.setString(2, p.getEmail());
			statement.setInt(3, p.getId());
			
			int qtdLinhasModif = statement.executeUpdate();
			
			conn.close();
			
			return p;
			
		}
		catch (Exception e) {System.out.print(e.toString());
			return null;
		}
	}
	
	public Boolean atualizarsenha(String senha, String email) {
		try {
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("update usuarios set senha=? where email=?");
			
			statement.setString(1, senha);
			statement.setString(2, email);
			
			int qtdLinhasModif = statement.executeUpdate();
			
			conn.close();
			
			if(qtdLinhasModif > 0) {
				return true;
			}
			else 
				return false;
			
		}
		catch (Exception e) {System.out.print(e.toString());
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean apagar(int id) {
		
		boolean resultado = false;
		
		try {
			
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("delete from usuarios where id=?");
			
			statement.setInt(1, id);
			
			resultado = statement.execute();
			
			statement.close();
			
			conn.close();
		}catch (Exception e) {
			
		} finally {
			return resultado;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Pessoa> buscar(int inicial, int quantidade){
		List<Pessoa> lista = new ArrayList<Pessoa>();

		try {
			Connection conn = new ConexaoDAO().conectar();

			PreparedStatement statement = conn.prepareStatement("select * from usuarios limit ?, ?");

			statement.setInt(1, inicial);
			
			statement.setInt(2, quantidade);
			
			ResultSet retorno = statement.executeQuery();

			while (retorno.next()) {
				Pessoa p = new Pessoa();

				p.setId(retorno.getInt("id"));
				p.setEmail(retorno.getString("email"));
				p.setNome(retorno.getString("nome"));
				p.setSenha(retorno.getString("senha"));
				p.setImagem(retorno.getString("imagem"));

				lista.add(p);
			}
			
			retorno.close();
						
			retorno = statement.executeQuery("select count(*) as totalRegistros from usuarios");
			
			if(retorno.next())
				this.TotalDeRegistros = retorno.getInt(1);
			
			conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			return lista;
		}
	}

	public int getTotalDeRegistros() {
		return TotalDeRegistros;
	}

	public void setTotalDeRegistros(int totalDeRegistros) {
		TotalDeRegistros = totalDeRegistros;
	}
	
}