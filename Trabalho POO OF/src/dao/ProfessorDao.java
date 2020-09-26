package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoDb;
import entidade.Professor;

public class ProfessorDao extends ConexaoDb {
	
	final String SQL_INSERT_PROFESSOR = "INSERT INTO Professor (NOME, EMAIL, PESO) VALUES ( ?, ?, ?)";
	final String SQL_SELECT_PROFESSOR = "SELECT * FROM Professor";
	final String SQL_SELECT_PROFESSOR_ID = "SELECT * FROM Professor WHERE ID = ?";
	final String SQL_ALTERA_PROFESSOR = "UPDATE Professor SET NOME=?, EMAIL=? , PESO=? WHERE ID = ?";
	final String DELETA_Professor = "DELETE FROM Professor WHERE ID = ?";
	
	public int inserir(Professor professor) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_PROFESSOR);) {
			pst.setString(1, professor.getNome());
			pst.setString(2, professor.getEmail());
			pst.setDouble(3, professor.getPeso());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
	}
	
	
	
	public List<Professor> listAll(){
		List<Professor> listaProfessor = new ArrayList<Professor>();
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PROFESSOR);){

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Professor professor = new Professor();
				
				professor.setId(rs.getInt("ID"));
				professor.setNome(rs.getString("NOME"));
				professor.setEmail(rs.getString("EMAIL"));
				professor.setPeso(rs.getDouble("PESO"));
				
				listaProfessor.add(professor);
				System.out.println(professor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaProfessor;
	}
		
		
		
		public Professor findByID (int id) {
			Professor professor = null;
			try (Connection connection = this.conectar();
					PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PROFESSOR_ID)){

				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					professor = new Professor();
					
					professor.setId(rs.getInt("ID"));
					professor.setNome(rs.getString("NOME"));
					professor.setEmail(rs.getString("EMAIL"));
					professor.setPeso(rs.getDouble("PESO"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return professor;
		}



		public int alterar(Professor professor) {
			int quantidade = 0;

			try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_PROFESSOR);) {
				pst.setString(1, professor.getNome());
				pst.setString(2, professor.getEmail());
				pst.setDouble(3, professor.getPeso());
				pst.setInt(4, professor.getId());
				quantidade = pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return quantidade;
		}
		
		public int deletar(int id) {
	        int quantidade = 0;
	        try (Connection connection = this.conectar();
	            PreparedStatement pst = connection.prepareStatement(DELETA_Professor);) {
	            pst.setInt(1, id);
	            quantidade = pst.executeUpdate();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return quantidade;
	    }
	}
