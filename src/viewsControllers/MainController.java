package viewsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import utils.Alerts;

public class MainController {
	
	@FXML
	private MenuItem novo;
	
	@FXML
	private MenuItem sair;

	public void sairPrograma() {
		System.out.println("saindo");
	}
	
	public void novoOrcamento() {
		Alerts.showAlerts("Msg", "ola", null, AlertType.INFORMATION);
		System.out.println("novo orçamento");
	}


}
