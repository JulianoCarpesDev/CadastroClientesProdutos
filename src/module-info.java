module budget {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens viewsControllers to javafx.fxml;
	opens model.entites to javafx.base;


}
