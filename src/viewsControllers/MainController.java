package viewsControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import utils.Alerts;

public class MainController implements Initializable{
	
	@FXML
	private MenuItem MenuOrçamento;
	
	@FXML
	private MenuItem MenuAbout;

	
	public void novoOrcamento() {
		Alerts.showAlerts("Msg", "ola", null, AlertType.INFORMATION);
		System.out.println("novo orçamento");
	}
	
	public void aboutAction() {
		carregaViewAbout("/viewsControllers/Ajuda.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	private void carregaViewAbout(String nomeTela) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeTela));
	
		try {
			VBox novoVbox = loader.load();
			
			Scene novaCena = Main.getMainScene();
			
			VBox cenaPrincipal = (VBox)((ScrollPane) novaCena. getRoot()).getContent();
			
			Node menuTelaPrincipal = cenaPrincipal.getChildren().get(0);
			cenaPrincipal.getChildren().clear();
			cenaPrincipal.getChildren().add(menuTelaPrincipal);
			cenaPrincipal.getChildren().addAll(novoVbox.getChildren());
		} catch (IOException e) {
			Alerts.showAlerts("Erro", "Tela About", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
		
	}

}
