/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STJ_UserForm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Andrew Gottilla
 * @author Akshay Sharma
 */
public class ScoutTheJason extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        // Build Form --------------------------------------------------------------
        Parent root = FXMLLoader.load(getClass().getResource("UserLoginForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("STJ - Login");
        stage.setScene(scene);
        stage.show();
        // Build Form --------------------------------------------------------------
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        //Scanner keyb = new Scanner(System.in);
        
        // Launch the User Form. Calls the Initialize function in FormController.java
        launch(args);
        
    }
}