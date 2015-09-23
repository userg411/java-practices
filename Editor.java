
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
import java.io.File;
import javafx.scene.control.TextArea;

import javafx.event.EventHandler;
 
public class Editor extends Application  {
	
	
    public Parent createListView() throws Exception{
		
		TextArea ta = new TextArea();
		ta.setText("1234567890");
		ta.positionCaret(4);
		Button b1 = new Button("$$ $$");
		Button b2 = new Button("$ $");
		Button b3 = new Button("∫");
		Button b4 = new Button("↙");
		Button b5 = new Button("⋅");
		Button b6 = new Button("ω");
		Button b7 = new Button("φ");
		Button b8 = new Button("Δ");
		Button b9 = new Button("π");
		Button b10 = new Button("λ");
		Button b11 = new Button("°");
		
		    
		
 		
 		EventHandler<ActionEvent> goAction = (ActionEvent event) -> {

			String text="";
			
			
			if(event.getSource()==b1)
				text = b1.getText();
			else if(event.getSource()==b2)
				text = b2.getText();	
			else if(event.getSource()==b3)
				text = b3.getText();
			else if(event.getSource()==b4)
				text = b4.getText();
			else if(event.getSource()==b5)
				text = b5.getText();
			else if(event.getSource()==b6)
				text = b6.getText();
			else if(event.getSource()==b7)
				text = b7.getText();
			else if(event.getSource()==b8)
				text = b8.getText();
			else if(event.getSource()==b8)
				text = b9.getText();
			else if(event.getSource()==b10)
				text = b10.getText();
			else if(event.getSource()==b11)
				text = b11.getText();	
			
			int index = ta.getCaretPosition();
			ta.setText(ta.getText().substring(0,index)+text+ta.getText().substring(index));
			ta.positionCaret(index+1);
			
        };

        

		
        b1.setOnAction(goAction);
 		b2.setOnAction(goAction);
 		b3.setOnAction(goAction);
 		b4.setOnAction(goAction);
 		b5.setOnAction(goAction);
 		b6.setOnAction(goAction);
 		b7.setOnAction(goAction);
 		b8.setOnAction(goAction);
 		b9.setOnAction(goAction);
 		b10.setOnAction(goAction);
 		 
 		HBox hBox = new HBox(5);

        hBox.getChildren().setAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10,b11);

        

        VBox vBox = new VBox(5);
        vBox.getChildren().setAll(ta,hBox);
        vBox.setPrefSize(800, 400);	
 

        

        return vBox;
		
	}
	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setScene(new Scene(createListView()));
        primaryStage.show();
    }
	 public static void main(String[] args) {
        launch(args);
    }
   
}
