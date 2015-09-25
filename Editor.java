
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
import javafx.scene.layout.Priority;
import javafx.event.EventHandler;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.Enum;
import java.nio.file.StandardOpenOption;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
 
public class Editor extends Application  {
	
	
    public Parent createListView() throws Exception{
		
		TextArea ta = new TextArea();
		ta.positionCaret(4);
		ta.setPrefSize(500,400);
		
		String t = "\u222B";
		byte[] array = t.getBytes("UTF-8");
		String s = new String(array, Charset.forName("UTF-8"));
		
		//Math symbols
		
		ArrayList <Button> mathButtons = new ArrayList<Button>();
		mathButtons.add(new Button("$$ $$"));
		mathButtons.add(new Button("$ $"));
		mathButtons.add(new Button("\u2196"));
		mathButtons.add(new Button("\u22C5"));
		mathButtons.add(new Button("\u2192"));
		mathButtons.add(new Button("\u03C9")); //
		mathButtons.add(new Button("\u03C6"));
		mathButtons.add(new Button("\u2206"));
		mathButtons.add(new Button("\u03C0"));
		mathButtons.add(new Button("\u03BB"));
		mathButtons.add(new Button("\u221A"));
		
		//Controls
		Button newQuestion = new Button("New question");
		Button append = new Button("Append to file");
		Button clear = new Button("Clear");
		Button htmlStart = new Button("start");
		Button htmlEnd = new Button("end");
		
		
 		
 		EventHandler<ActionEvent> goAction = (ActionEvent event) -> {

			String text  = "";
			Button  b = (Button)event.getSource();
			
			if(event.getSource()==newQuestion)
				text = "<a id=\"q2\"></a>\n"+
						"<div class=\"container\">\n"+
						"<div class=\"jumbotron\">\n"+
						"<h1> Vopros  </h1>\n"+
						"<p>      </p>\n"+
						"A) </br>\n"+
						"B) </br>\n"+
						"C) </br>\n"+
						"D) </br>\n"+
						"E) </br>\n"+
						"<p><b> Otvet: </b></p>\n"+
						"<p><i> Rewenie:\n\n\n"+ 
						"</i> </p>\n"+
						"</div>\n"+
						"</div>\n";
			else if(event.getSource()==htmlStart)
				text = "<html>\n <head><meta charset=\"utf-8\">\n<body>\n";
			else if(event.getSource()==htmlEnd)
				text = "<//body>\n<//head>\n<//html>\n";
			else if (event.getSource()==append)
				appendToFile(ta.getText());
			else if(event.getSource()==clear)
				ta.setText("");
			else
				text = b.getText();
			
			int index = ta.getCaretPosition();
			ta.setText(ta.getText().substring(0,index)+text+ta.getText().substring(index));
			ta.positionCaret(index+text.length());
			
        };

        for(Button b:mathButtons)
			b.setOnAction(goAction);

		
      
		newQuestion.setOnAction(goAction);
		append.setOnAction(goAction);
		clear.setOnAction(goAction);
		htmlStart.setOnAction(goAction);
		htmlEnd.setOnAction(goAction);
		
 		HBox hBox1 = new HBox(5);
		HBox hBox2 = new HBox(5);
		HBox hBox3 = new HBox(5);
		
        hBox1.getChildren().setAll(mathButtons);
		hBox2.getChildren().setAll(newQuestion, append, clear,htmlStart, htmlEnd);
		
		
		

        VBox vBox = new VBox(5);
		vBox.getChildren().setAll(ta,hBox1, hBox2);
        HBox.setHgrow(ta, Priority.ALWAYS);
        
		vBox.setPrefSize(700, 700);	
 
        return vBox;
		
	}
	public static void appendToFile (String text){
		try {
			//Files.write(Paths.get("myfile.html"), text.getBytes(), StandardOpenOption.APPEND);
			File fileDir = new File("myfile.html");
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));

			out.append(text);
			
			out.flush();
			out.close();
			
		}catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
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
