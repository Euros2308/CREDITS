package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Menu {
    @FXML
    private MenuButton btnClients;
    @FXML
    private MenuButton btnDogovora;
    @FXML
    private MenuButton btnKassa;
    @FXML
    private Button btnLogOut;

    //Функция кнопки для перехода в форму "Клиенты"
    public void formClients(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnClients.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Clients.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Функция кнопки для перехода в форму "Договора"
    public void formDogovor(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnDogovora.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Dogovora.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Функция кнопки для перехода в форму "Касса"
    public void formKassa(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnKassa.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Kassa.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Функция кнопки для выхода из программы
    public void closeApp(ActionEvent event){
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        exitAlert.setContentText("Вы уверены, что хотите выйти?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();
        if(exitAlert.getResult() == ButtonType.OK){
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }
}
