package viewsControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import db.DbException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entites.Produtos;
import model.services.ProdutoService;
import utils.Alerts;
import utils.Constrains.Constraints;
import utils.Utils;

public class ControllerFormulario implements Initializable{

	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtPreco;
	
	@FXML
	private TextField txtDecricao;
	
	@FXML
	private Label labelErrorId;
	@FXML
	private Label labelErroNome;
	@FXML
	private Label labelErroPreco;
	@FXML
	private Label labelErroDecricao;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	private Produtos entidade;
	
	private ProdutoService service;
	
	private List<DataChangeListener> listener = new ArrayList<>();

	public ProdutoService getService() {
		return service;
	}

	public void setService(ProdutoService service) {
		this.service = service;
	}
	
	public Produtos getProdutos() {
		return entidade;
	}

	public void setProdutos(Produtos entidade) {
		this.entidade = entidade;
	}
	
	public void sobrescreveDataChangeList(DataChangeListener data) {
		listener.add(data);
	}
	@FXML
	public void btnSalva(ActionEvent evento) {
		if(entidade == null) {
			throw new IllegalStateException("Entidade esta nula");
			
		}
		if(service == null ) {
			throw new IllegalStateException("Service esta nulo");
			
		}
		try {
			entidade = getDadosForm();
			service.salvaAtualizarForm(entidade);
			nofiticaListener();
			Utils.palcoAtual(evento).close();
		} catch (DbException e) {
			Alerts.showAlerts("Erro ao salvar", null, e.getMessage(), AlertType.ERROR);
		}
	
		
	}
	
	private void nofiticaListener() {
		for (DataChangeListener data : listener) {
			data.onDataChanged();
			
		}
		
	}

	private Produtos getDadosForm() {
		Locale.setDefault(Locale.US);
		Produtos obj = new Produtos();
		obj.setId(Utils.converteParaString(txtId.getText()));
		obj.setNome(txtNome.getText());
		obj.setPreco(Utils.converteParaDouble(txtPreco.getText()));
		obj.setDescricao(txtDecricao.getText());
		return obj;
	}

	@FXML
	public void btnCancela(ActionEvent evento) {
		Utils.palcoAtual(evento).close();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iniciaNodes();
		
	}
	private void iniciaNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
		Constraints.setTextFieldDouble(txtPreco);
		Constraints.setTextFieldMaxLength(txtDecricao, 60);
	}

	public void updateFormulario() {
		if (entidade == null) {
			throw new IllegalStateException("entidade esta nula");
		}
		txtId.setText(String.valueOf(entidade.getId()));
		txtNome.setText(entidade.getNome());
		txtPreco.setText(String.format("%.2f", entidade.getPreco()));
		txtDecricao.setText(entidade.getDescricao());
	}



	

}
