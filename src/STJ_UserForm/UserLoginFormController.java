package STJ_UserForm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import clientserver.EchoClient;
import java.io.IOException;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Andrew Godzilla
 */
public class UserLoginFormController implements Initializable {

    @FXML
    private AnchorPane window;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink lblRegister;
    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO: debugging
        System.out.println("INITIALIZATION OCCURED");
    }    

    @FXML
    private void handleRegister(ActionEvent event)
    {
        // TODO: send back to register form
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("UserRegistrationForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("STJ - Registration");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e)
        {
            lblError.setText("An error occured.");
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException
    {
        //if ( User.loginUser(txtUser.getText(), txtPass.getText()) )
        {
            System.out.print("YAY");
            // Close Current Window
            ((Node)(event.getSource())).getScene().getWindow().hide();
            // Launch EchoClient
            EchoClient.main(new String[0]);
        }
        //else
            lblError.setText("An error occured");
    }
}
