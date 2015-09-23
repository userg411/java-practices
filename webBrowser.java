

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.layout.HBox;
import javafx.beans.value.ObservableValue; 
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
 
public class webBrowser extends Application {
	
	public static final String DEFAULT_URL = "http://www.oracle.com/us/index.html";
    
    public Parent createListView(){
		WebView webView = new WebView();

 

        final WebEngine webEngine = webView.getEngine();

        webEngine.load(DEFAULT_URL);

 

        final TextField locationField = new TextField(DEFAULT_URL);

        webEngine.locationProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            locationField.setText(newValue);

        });

        EventHandler<ActionEvent> goAction = (ActionEvent event) -> {

            webEngine.load(locationField.getText().startsWith("http://")

                    ? locationField.getText()

                    : "http://" + locationField.getText());

        };

        locationField.setOnAction(goAction);

 

        Button goButton = new Button("Go");

        goButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        goButton.setDefaultButton(true);

        goButton.setOnAction(goAction);

 

        // Layout logic

        HBox hBox = new HBox(5);

        hBox.getChildren().setAll(locationField, goButton);

        HBox.setHgrow(locationField, Priority.ALWAYS);

 

        VBox vBox = new VBox(5);

        vBox.getChildren().setAll(hBox, webView);

        vBox.setPrefSize(800, 400);

        VBox.setVgrow(webView, Priority.ALWAYS);

        return vBox;
		
	}
	
	
	
	@Override
    public void start(Stage primaryStage) {
        
        primaryStage.setScene(new Scene(createListView()));
        primaryStage.show();
    }
	 public static void main(String[] args) {
        launch(args);
    }
   
}
