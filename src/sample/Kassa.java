package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Kassa implements Initializable{
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<DataKassa> kassaTable;
    @FXML
    private TableColumn<DataKassa, String> colClient;
    @FXML
    private TableColumn<DataKassa, String> colDate;
    @FXML
    private TableColumn<DataKassa, String> colDolg;
    @FXML
    private TableColumn<DataKassa, String> colOstatok;
    @FXML
    private TableColumn<DataKassa, String> colSumma;
    @FXML
    private TableColumn<DataKassa, String> colVozvrat;
    @FXML
    private DatePicker dpDate;
    @FXML
    private MenuBar fileMenu;
    @FXML
    private TextField txfClient;
    @FXML
    private TextField txfDolg;
    @FXML
    private TextField txfOstatok;
    @FXML
    private TextField txfPoisk;
    @FXML
    private TextField txfSumma;
    @FXML
    private TextField txfVozvrat;

    ObservableList<DataKassa> observableDataKassaList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        txfPoisk.textProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                filterDataKassa((String) oldValue, (String) newValue);
            }
        });

        kassaTable.setEditable(true);
        colClient.setOnEditCommit(e -> colClient_OnEditCommit(e));
        colDate.setOnEditCommit(e -> colDate_OnEditCommit(e));
        colDolg.setOnEditCommit(e -> colDolg_OnEditCommit(e));
        colOstatok.setOnEditCommit(e -> colOstatok_OnEditCommit(e));
        colSumma.setOnEditCommit(e -> colSumma_OnEditCommit(e));
        colVozvrat.setOnEditCommit(e -> colVozvrat_OnEditCommit(e));

        kassaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colClient.setCellFactory(TextFieldTableCell.forTableColumn());
        colDate.setCellFactory(TextFieldTableCell.forTableColumn());
        colDolg.setCellFactory(TextFieldTableCell.forTableColumn());
        colOstatok.setCellFactory(TextFieldTableCell.forTableColumn());
        colSumma.setCellFactory(TextFieldTableCell.forTableColumn());
        colVozvrat.setCellFactory(TextFieldTableCell.forTableColumn());

        colClient.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
        colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        colDolg.setCellValueFactory(cellData -> cellData.getValue().dolgProperty());
        colOstatok.setCellValueFactory(cellData -> cellData.getValue().ostatokProperty());
        colSumma.setCellValueFactory(cellData -> cellData.getValue().summaProperty());
        colVozvrat.setCellValueFactory(cellData -> cellData.getValue().vozvratProperty());

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        kassaTable.setItems(observableDataKassaList);
        kassaTable.setEditable(true);
        kassaTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        kassaTable.setPlaceholder(new Label("Ваша таблица пуста"));

        txfClient.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (txfClient.isFocused()){
                    btnAdd.setDisable(false);
                }
            }
        });
        kassaTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(kassaTable.isFocused()){
                    btnDelete.setDisable(false);
                }
            }
        });
    }
    public void handleAddButtonClick(ActionEvent event){
        if(observableDataKassaList.size()<10){
            if(isValidInput(event)){
                DataKassa dataKassa = new DataKassa();
                dataKassa.setClient(txfClient.getText()); dataKassa.setSumma(txfSumma.getText());
                dataKassa.setDolg(txfDolg.getText()); dataKassa.setVozvratr(txfVozvrat.getText());
                dataKassa.setDate(dpDate.getValue().toString()); dataKassa.setOstatok(txfOstatok.getText());
                observableDataKassaList.add(dataKassa);
                System.out.println(dataKassa.toString());
                txfClient.clear(); txfSumma.clear(); dpDate.setValue(null); txfDolg.clear();
                txfVozvrat.clear(); txfOstatok.clear();
            }
        }else {
            Alert sizeAlert = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            sizeAlert.setContentText("В данный момент у вас может быть только 10 клиентов");
            sizeAlert.initModality(Modality.APPLICATION_MODAL);
            sizeAlert.initOwner(owner);
            sizeAlert.showAndWait();
            if(sizeAlert.getResult() == ButtonType.OK){
                sizeAlert.close();
                txfClient.clear(); txfSumma.clear(); dpDate.setValue(null); txfDolg.clear();
                txfVozvrat.clear(); txfOstatok.clear();
            }
        }
    }
    private boolean isValidInput(ActionEvent event){
        Boolean validInput = true;
        if(txfClient == null || txfClient.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyClient = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyClient.setContentText("Поле ввода 'Клиент' пуст");
            emptyClient.initModality(Modality.APPLICATION_MODAL);
            emptyClient.initOwner(owner);
            emptyClient.showAndWait();
            if (emptyClient.getResult() == ButtonType.OK){
                emptyClient.close();
                txfClient.requestFocus();
            }
        }
        if(txfSumma == null || txfSumma.getText().trim().isEmpty()){
            validInput = false;
            Alert emptySumma = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptySumma.setContentText("Поле 'Сумма' пуст");
            emptySumma.initModality(Modality.APPLICATION_MODAL);
            emptySumma.initOwner(owner);
            emptySumma.showAndWait();
            if (emptySumma.getResult() == ButtonType.OK){
                emptySumma.close();
                txfSumma.requestFocus();
            }
        }
        if(dpDate == null || dpDate.getValue().toString().isEmpty()){
            validInput = false;
            Alert emptyDate = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyDate.setContentText("Поле 'Дата возврата' пуст");
            emptyDate.initModality(Modality.APPLICATION_MODAL);
            emptyDate.initOwner(owner);
            emptyDate.showAndWait();
            if (emptyDate.getResult() == ButtonType.OK){
                emptyDate.close();
                dpDate.requestFocus();
            }
        }
        if(txfDolg == null || txfDolg.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyDolg = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyDolg.setContentText("Поле ввода 'Долг' пуст");
            emptyDolg.initModality(Modality.APPLICATION_MODAL);
            emptyDolg.initOwner(owner);
            emptyDolg.showAndWait();
            if (emptyDolg.getResult() == ButtonType.OK){
                emptyDolg.close();
                txfDolg.requestFocus();
            }
        }
        if(txfVozvrat == null || txfVozvrat.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyVozvrat = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyVozvrat.setContentText("Поле 'Возврат' пуст");
            emptyVozvrat.initModality(Modality.APPLICATION_MODAL);
            emptyVozvrat.initOwner(owner);
            emptyVozvrat.showAndWait();
            if (emptyVozvrat.getResult() == ButtonType.OK){
                emptyVozvrat.close();
                txfVozvrat.requestFocus();
            }
        }
        if(txfOstatok == null || txfOstatok.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyOstatok = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyOstatok.setContentText("Поле ввода 'Остаток' пуст");
            emptyOstatok.initModality(Modality.APPLICATION_MODAL);
            emptyOstatok.initOwner(owner);
            emptyOstatok.showAndWait();
            if (emptyOstatok.getResult() == ButtonType.OK){
                emptyOstatok.close();
                txfOstatok.requestFocus();
            }
        }
        return validInput;
    }
    public void colClient_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataKassa, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataKassa, String>) e;
        DataKassa dataKassa = cellEditEvent.getRowValue();
        dataKassa.setClient(cellEditEvent.getNewValue());
    }
    public void colDate_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataKassa, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataKassa, String>) e;
        DataKassa dataKassa = cellEditEvent.getRowValue();
        dataKassa.setDate(cellEditEvent.getNewValue());
    }
    public void colDolg_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataKassa, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataKassa, String>) e;
        DataKassa dataKassa = cellEditEvent.getRowValue();
        dataKassa.setDolg(cellEditEvent.getNewValue());
    }
    public void colOstatok_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataKassa, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataKassa, String>) e;
        DataKassa dataKassa = cellEditEvent.getRowValue();
        dataKassa.setOstatok(cellEditEvent.getNewValue());
    }
    public void colSumma_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataKassa, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataKassa, String>) e;
        DataKassa dataKassa = cellEditEvent.getRowValue();
        dataKassa.setSumma(cellEditEvent.getNewValue());
    }
    public void colVozvrat_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataKassa, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataKassa, String>) e;
        DataKassa dataKassa = cellEditEvent.getRowValue();
        dataKassa.setVozvratr(cellEditEvent.getNewValue());
    }
    public void handleDeleteButtonClick(ActionEvent event){
        if(!observableDataKassaList.isEmpty()){
            System.out.println("Delete button cliked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Вы уверены, что хотите удалить это? \n\nОтменить действие невозможно.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK){
                observableDataKassaList.removeAll(kassaTable.getSelectionModel().getSelectedItem());
                kassaTable.getSelectionModel().clearSelection();
            }else{deleteAlert.close();}
        }
    }
    public void handleClearButtonClick(ActionEvent event){
        txfClient.clear(); txfSumma.clear(); dpDate.setValue(null); txfDolg.clear();
        txfVozvrat.clear(); txfOstatok.clear();
    }
    public void filterDataKassa(String oldValue, String newValue){
        ObservableList<DataKassa> filteredList = FXCollections.observableArrayList();
        if (txfPoisk == null || (newValue.length() < oldValue.length()) || newValue == null){
            kassaTable.setItems(observableDataKassaList);
        }else{
            newValue = newValue.toUpperCase();
            for(DataKassa dataKassa : kassaTable.getItems()){
                String filterFio = dataKassa.getClient();
                if(filterFio.toUpperCase().contains(newValue)){
                    filteredList.add(dataKassa);
                }
            }
            kassaTable.setItems(filteredList);
        }
    }
    public void handleSave(ActionEvent event){
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить таблицу Долги");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableDataKassaList.isEmpty()){
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "ПУСТАЯ ТАБЛИЦА", ButtonType.OK);
            emptyTableAlert.setContentText("Вам нечего сохранять");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if (emptyTableAlert.getResult() == ButtonType.OK){
                emptyTableAlert.close();
            }
        }else {
            File file = fileChooser.showOpenDialog(secondaryStage);
            if(file != null){
                saveFile(kassaTable.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<DataKassa> observableDataKassaList, File file){
        try{
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
            for(DataKassa dataKassa : observableDataKassaList){
                outWriter.write(dataKassa.toString());
                outWriter.newLine();
            }
            System.out.println(observableDataKassaList.toString());
            outWriter.close();
        }catch (IOException e){
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Извините. Произошла ошибка.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK){
                ioAlert.close();
            }
        }
    }
    public void closeApp(ActionEvent event) throws IOException{
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Вы уверены, что хотите выйти?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();
        if(exitAlert.getResult() == ButtonType.OK){
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            exitAlert.close();
        }
    }
}
