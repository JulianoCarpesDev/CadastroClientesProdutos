package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ClientesDao;
import model.entites.Clientes;
import model.entites.Produtos;

public class ClientesDaoJDBC implements ClientesDao {

	private Connection conn;
	
	public ClientesDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Clientes obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO clientes "
					+ "(Nome, Endereco, Telefone,ProdutosId) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEndereco());
			st.setString(3, obj.getTelefone());
			
			st.setInt(4, obj.getProdutos().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Clientes obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE clientes "
					+ "SET Nome= ?, Endereco = ?, Telefone = ?, ProdutosId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEndereco());
			st.setString(3, obj.getTelefone());
			st.setInt(4, obj.getProdutos().getId());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM clientes WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Clientes findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT clientes.*,produtos.Nome"
					+ "FROM clientes INNER JOIN produtos "
					+ "ON clientes.ProdutosId = produtos.Id "
					+ "WHERE clientes.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Produtos dep = instantiateProdutos(rs);
				Clientes obj = instantiateClientes(rs, dep);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Clientes instantiateClientes(ResultSet rs, Produtos dep) throws SQLException {
		Clientes obj = new Clientes();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setEndereco(rs.getString("Endereco"));
		obj.setTelefone(rs.getString("telefone"));
		obj.setProdutos(dep);
		return obj;
	}

	private Produtos instantiateProdutos(ResultSet rs) throws SQLException {
		Produtos prod = new Produtos();
		prod.setId(rs.getInt("ProdutosId"));
		prod.setNome(rs.getString("Nome"));
		prod.setPreco(rs.getDouble("Preco"));
		prod.setDescricao(rs.getString("Descricao"));
		return prod;
	}

	@Override
	public List<Clientes> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT clientes.*,produtos.Nome"
					+ "FROM clientes INNER JOIN produtos "
					+ "ON clientes.ProdutosId = produtos.Id "
					+ "ORDER BY Nome");
			
			rs = st.executeQuery();
			
			List<Clientes> list = new ArrayList<>();
			Map<Integer, Produtos> map = new HashMap<>();
			
			while (rs.next()) {
				
				Produtos dep = map.get(rs.getInt("ProdutosId"));
				
				if (dep == null) {
					dep = instantiateProdutos(rs);
					map.put(rs.getInt("ProdutosId"), dep);
				}
				
				Clientes obj = instantiateClientes(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Clientes> findByProdutos(Produtos produtos) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT clientes.*,produtos.Nome"
					+ "FROM clientes INNER JOIN produtos "
					+ "ON clientes.ProdutosId = produtos.Id "
					+ "WHERE ProdutosId = ? "
					+ "ORDER BY Nome");
			
			st.setInt(1, produtos.getId());
			
			rs = st.executeQuery();
			
			List<Clientes> list = new ArrayList<>();
			Map<Integer, Produtos> map = new HashMap<>();
			
			while (rs.next()) {
				
				Produtos dep = map.get(rs.getInt("ProdutosId"));
				
				if (dep == null) {
					dep = instantiateProdutos(rs);
					map.put(rs.getInt("ProdutosId"), dep);
				}
				
				Clientes obj = instantiateClientes(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
