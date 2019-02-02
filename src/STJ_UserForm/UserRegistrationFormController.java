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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrew Godzilla
 */
public class UserRegistrationFormController implements Initializable {
    
    @FXML
    private AnchorPane window;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtPassConfirm;
    @FXML
    private Button btnRegister;
    @FXML
    private Hyperlink lblLogin;
    @FXML
    private Label idError;

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
        if // user is successfully added
        (
            User.addUser(txtFirstName.getText(), txtLastName.getText(),
                txtUser.getText(), txtEmail.getText(), txtPass.getText())
        )
        {
            handleLogin(event);
        }
        else
        {
            idError.setText("An error occured.");
        }
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("UserLoginForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("STJ - Login");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (Exception e)
        {
            idError.setText("An error occured.");
        }
    }
}
