package viewsControllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entites.Produtos;

public class ControllerListaProdutos implements Initializable{
	
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
	
	
	@FXML
	public void onBtNovo() {
		System.out.println("apertei");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iniciaNo();
	}
	
	private void iniciaNo() {
		tableCollumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableCollumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));		
		tableCollumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));	
		
		Stage palco =(Stage)Main.getMainScene().getWindow();
		
		tableViewProdutos.prefHeightProperty().bind(palco.heightProperty());

	}
	

}
