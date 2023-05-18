module budget {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens viewsControllers to javafx.fxml;


}
