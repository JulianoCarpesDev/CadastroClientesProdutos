package model.entites;

import java.util.Objects;

public class Produtos  {

	
	private Integer id;
	private String name;
	private Double preco;
	
	
	public Produtos() {
		
	}


	public Produtos(Integer id, String name, Double preco) {
		super();
		this.id = id;
		this.name = name;
		this.preco = preco;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Produtos [id=" + id + ", name=" + name + ", preco=" + preco + "]";
	}
	
	
	

}
