package viewsControllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entites.Produtos;
import model.services.ProdutoService;
import utils.Alerts;
import utils.Utils;

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
	private TableColumn<Produtos, Double> tableCollumnDescricao;
	
	@FXML
	private Button btnNovo;
	
	private ObservableList<Produtos> obsList;
	
	@FXML
	public void onBtNovo(ActionEvent evento) {
		Stage pai = Utils.palcoAtual(evento);
		criaDialogoForm("/viewsControllers/FormularioProdutos.fxml",pai);
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
		tableCollumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));		
		tableCollumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));	
		tableCollumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));	

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
	
	private void criaDialogoForm(String caminho,Stage palco) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Pane novaTela = loader.load();
			
			Stage novoPalco = new Stage();
			novoPalco.setTitle("Entre com os dados do Produto");
			novoPalco.setScene(new Scene(novaTela));
			novoPalco.setResizable(false);
			novoPalco.initOwner(palco);
			novoPalco.initModality(Modality.WINDOW_MODAL);
			novoPalco.showAndWait();
		} catch (IOException e) {
			Alerts.showAlerts("IO Exception", "Erro ao carregar tela", e.getMessage(), AlertType.ERROR);
		}
	}

}
