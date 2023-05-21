package viewsControllers;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entites.Produtos;
import model.services.ProdutoService;

public class ControllerListaProdutos implements Initializable{
	
	private ProdutoService service;
	@FXML
	private TableView<Produtos> tableViewProdutos;
	
	@FXML
	private TableColumn<Produtos, Integer> tableCollumnId;
	
	@FXML
	private TableColumn<Produtos, String> tableCollumnName;
	
	@FXML
	private TableColumn<Produtos, Double> tableCollumnPreco;
	
	@FXML
	private Button btnNovo;
	
	private ObservableList<Produtos> obsList;
	
	@FXML
	public void onBtNovo() {
		System.out.println("apertei");
	}
	
	public void setProdutosService(ProdutoService service) {
		this.service= service;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iniciaNo();
	}
	
	private void iniciaNo() {
		tableCollumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableCollumnName.setCellValueFactory(new PropertyValueFactory<>("name"));		
		tableCollumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));	
		
		Stage palco =(Stage)Main.getMainScene().getWindow();
		
		tableViewProdutos.prefHeightProperty().bind(palco.heightProperty());

	}
	public void atualizaTableView() {
		if (service == null) {
			throw new IllegalStateException("Service esta nulo");
		}
		List<Produtos>list  = service.buscarTodos();
		obsList = FXCollections.observableArrayList(list);
		tableViewProdutos.setItems(obsList);
	}

}
