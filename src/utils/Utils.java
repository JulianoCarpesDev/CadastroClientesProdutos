package utils;



import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage palcoAtual(ActionEvent evento) {
		return (Stage)((Node)evento.getSource()).getScene().getWindow();
	}
	public static Integer converteParaString(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	public static Double converteParaDouble(String str) {
		try {
			return Double.parseDouble(str);
			
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
