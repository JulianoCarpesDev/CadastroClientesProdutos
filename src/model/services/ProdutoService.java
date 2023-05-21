package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entites.Produtos;

public class ProdutoService {

	public List<Produtos> buscarTodos(){
		
		List<Produtos> list = new ArrayList<>();
		
		list.add(new Produtos(1, "DVR 8 Canais", 455.80));
		list.add(new Produtos(2, "DVR 4 Canais", 380.50));
		list.add(new Produtos(3, "DVR 16 Canais", 965.88));
		return list;
		
	}
}
