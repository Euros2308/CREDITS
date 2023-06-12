package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class SignIn {
    @FXML
    private Button btnSignIn;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfUser;
    @FXML
    private Label lblMessage;

    //Функция кнопки для перехода в другую форму
    @FXML
    void signIn(ActionEvent event) throws IOException {
        //Постоянный логин и пароль приложения
        if(tfUser.getText().toString().equals("creditS@98") && tfPassword.getText().toString().equals("A23@M08%T")) {
            Stage stage = (Stage) btnSignIn.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(tfUser.getText().isEmpty() && tfPassword.getText().isEmpty()) {
            lblMessage.setText("Введите логин и пароль");
        }
        else {
            lblMessage.setText("Неверный логин или пароль");
        }
    }
}
