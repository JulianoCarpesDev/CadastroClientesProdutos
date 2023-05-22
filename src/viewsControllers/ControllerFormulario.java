package viewsControllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.Constrains.Constraints;

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
	
	@FXML
	public void btnSalva() {
		System.out.println("Salvei");
	}
	
	@FXML
	public void btnCancela() {
		System.out.println("Cancelei");
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

}
