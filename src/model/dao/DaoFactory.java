package model.dao;

import db.DB;
import model.dao.impl.ProdutosDaoJDBC;
import model.dao.impl.ClientesDaoJDBC;

public class DaoFactory {

	public static ClientesDao createClientesDao() {
		return new ClientesDaoJDBC(DB.getConnection());
	}
	
	public static ProdutosDao createProdutosDao() {
		return new ProdutosDaoJDBC(DB.getConnection());
	}
}
