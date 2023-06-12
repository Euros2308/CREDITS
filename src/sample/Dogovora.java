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

public class Dogovora implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<DataDogovora, String> colClient;
    @FXML
    private TableColumn<DataDogovora, String> colNomer;
    @FXML
    private TableColumn<DataDogovora, String> colSposob;
    @FXML
    private TableColumn<DataDogovora, String> colSrok;
    @FXML
    private TableColumn<DataDogovora, String> colStavka;
    @FXML
    private TableColumn<DataDogovora, String> colSumma;
    @FXML
    private TableColumn<DataDogovora, String> colZakluchenie;
    @FXML
    private TableView<DataDogovora> dogovorTable;
    @FXML
    private DatePicker dpZakluchenie;
    @FXML
    private MenuBar fileMenu;
    @FXML
    private TextField txfClient;
    @FXML
    private TextField txfNomer;
    @FXML
    private TextField txfPoisk;
    @FXML
    private TextField txfSrok;
    @FXML
    private TextField txfStavka;
    @FXML
    private TextField txfSumma;
    @FXML
    private ComboBox<String> cbSposob;
    ObservableList<String> cbSposobData = FXCollections.observableArrayList();

    ObservableList<DataDogovora> observableDataDogovoraList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        txfPoisk.textProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                filterDataDogovora((String) oldValue, (String) newValue);
            }
        });

        dogovorTable.setEditable(true);
        colClient.setOnEditCommit(e -> colClient_OnEditCommit(e));
        colSposob.setOnEditCommit(e -> colSposob_OnEditCommit(e));
        colSrok.setOnEditCommit(e -> colSrok_OnEditCommit(e));
        colStavka.setOnEditCommit(e -> colStavka_OnEditCommit(e));
        colSumma.setOnEditCommit(e -> colSumma_OnEditCommit(e));
        colZakluchenie.setOnEditCommit(e -> colZakluchenie_OnEditCommit(e));
        colNomer.setOnEditCommit(e -> colNomer_OnEditCommit(e));

        dogovorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colClient.setCellFactory(TextFieldTableCell.forTableColumn());
        colSposob.setCellFactory(TextFieldTableCell.forTableColumn());
        colSrok.setCellFactory(TextFieldTableCell.forTableColumn());
        colStavka.setCellFactory(TextFieldTableCell.forTableColumn());
        colSumma.setCellFactory(TextFieldTableCell.forTableColumn());
        colZakluchenie.setCellFactory(TextFieldTableCell.forTableColumn());
        colNomer.setCellFactory(TextFieldTableCell.forTableColumn());

        colClient.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
        colSposob.setCellValueFactory(cellData -> cellData.getValue().sposobProperty());
        colSrok.setCellValueFactory(cellData -> cellData.getValue().srokProperty());
        colStavka.setCellValueFactory(cellData -> cellData.getValue().stavkaProperty());
        colSumma.setCellValueFactory(cellData -> cellData.getValue().summaProperty());
        colZakluchenie.setCellValueFactory(cellData -> cellData.getValue().zakluchenieProperty());
        colNomer.setCellValueFactory(cellData -> cellData.getValue().nomerProperty());

        cbSposobData.add(new String("с равномерным погашением"));
        cbSposobData.add(new String("с неравномерным погашением"));
        cbSposobData.add(new String("с одновременным погашением"));
        cbSposob.setItems(cbSposobData);

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        dogovorTable.setItems(observableDataDogovoraList);
        dogovorTable.setEditable(true);
        dogovorTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        dogovorTable.setPlaceholder(new Label("Ваша таблица пуста"));

        txfClient.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (txfClient.isFocused()){
                    btnAdd.setDisable(false);
                }
            }
        });
        dogovorTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(dogovorTable.isFocused()){
                    btnDelete.setDisable(false);
                }
            }
        });
    }

    public void handleAddButtonClick(ActionEvent event){
        if(observableDataDogovoraList.size()<10){
            if(isValidInput(event)){
                if(cbSposob.getValue().equals("с равномерным погашением")){
                    DataDogovora dataDogovora = new DataDogovora();
                    dataDogovora.setClient(txfClient.getText()); dataDogovora.setSposob(cbSposob.getValue());
                    dataDogovora.setZakluchenie(dpZakluchenie.getValue().toString()); dataDogovora.setSrok(txfSrok.getText());
                    dataDogovora.setStavka(txfStavka.getText()); dataDogovora.setSumma(txfSumma.getText());
                    dataDogovora.setNomer(txfNomer.getText());
                    observableDataDogovoraList.add(dataDogovora);
                    System.out.println(dataDogovora.toString());
                    txfClient.clear(); cbSposob.setValue("Способ погашения"); dpZakluchenie.setValue(null); txfSrok.clear();
                    txfStavka.clear(); txfSumma.clear(); txfNomer.clear();
                }
                if(cbSposob.getValue().equals("с неравномерным погашением")){
                    DataDogovora dataDogovora = new DataDogovora();
                    dataDogovora.setClient(txfClient.getText()); dataDogovora.setSposob(cbSposob.getValue());
                    dataDogovora.setZakluchenie(dpZakluchenie.getValue().toString()); dataDogovora.setSrok(txfSrok.getText());
                    dataDogovora.setStavka(txfStavka.getText()); dataDogovora.setSumma(txfSumma.getText());
                    dataDogovora.setNomer(txfNomer.getText());
                    observableDataDogovoraList.add(dataDogovora);
                    System.out.println(dataDogovora.toString());
                    txfClient.clear(); cbSposob.setValue("Способ погашения"); dpZakluchenie.setValue(null); txfSrok.clear();
                    txfStavka.clear(); txfSumma.clear(); txfNomer.clear();
                }
                if(cbSposob.getValue().equals("с одновременным погашением")){
                    DataDogovora dataDogovora = new DataDogovora();
                    dataDogovora.setClient(txfClient.getText()); dataDogovora.setSposob(cbSposob.getValue());
                    dataDogovora.setZakluchenie(dpZakluchenie.getValue().toString()); dataDogovora.setSrok(txfSrok.getText());
                    dataDogovora.setStavka(txfStavka.getText()); dataDogovora.setSumma(txfSumma.getText());
                    dataDogovora.setNomer(txfNomer.getText());
                    observableDataDogovoraList.add(dataDogovora);
                    System.out.println(dataDogovora.toString());
                    txfClient.clear(); cbSposob.setValue("Способ погашения"); dpZakluchenie.setValue(null); txfSrok.clear();
                    txfStavka.clear(); txfSumma.clear(); txfNomer.clear();
                }

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
                txfClient.clear(); cbSposob.setValue("Способ погашения"); dpZakluchenie.setValue(null); txfSrok.clear();
                txfStavka.clear(); txfSumma.clear(); txfNomer.clear();
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
        if(cbSposob == null || cbSposob.getValue().isEmpty()){
            validInput = false;
            Alert emptySposob = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptySposob.setContentText("Поле 'Способ погашения' пуст");
            emptySposob.initModality(Modality.APPLICATION_MODAL);
            emptySposob.initOwner(owner);
            emptySposob.showAndWait();
            if (emptySposob.getResult() == ButtonType.OK){
                emptySposob.close();
                cbSposob.requestFocus();
            }
        }
        if(dpZakluchenie == null || dpZakluchenie.getValue().toString().isEmpty()){
            validInput = false;
            Alert emptyZakluchenie = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyZakluchenie.setContentText("Поле 'Дата заключения' пуст");
            emptyZakluchenie.initModality(Modality.APPLICATION_MODAL);
            emptyZakluchenie.initOwner(owner);
            emptyZakluchenie.showAndWait();
            if (emptyZakluchenie.getResult() == ButtonType.OK){
                emptyZakluchenie.close();
                dpZakluchenie.requestFocus();
            }
        }
        if(txfSrok == null || txfSrok.getText().trim().isEmpty()){
            validInput = false;
            Alert emptySrok = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptySrok.setContentText("Поле ввода 'Срок погашения' пуст");
            emptySrok.initModality(Modality.APPLICATION_MODAL);
            emptySrok.initOwner(owner);
            emptySrok.showAndWait();
            if (emptySrok.getResult() == ButtonType.OK){
                emptySrok.close();
                txfSrok.requestFocus();
            }
        }
        if(txfStavka == null || txfStavka.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyStavka = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyStavka.setContentText("Поле 'Процентная ставка' пуст");
            emptyStavka.initModality(Modality.APPLICATION_MODAL);
            emptyStavka.initOwner(owner);
            emptyStavka.showAndWait();
            if (emptyStavka.getResult() == ButtonType.OK){
                emptyStavka.close();
                txfStavka.requestFocus();
            }
        }
        if(txfSumma == null || txfSumma.getText().trim().isEmpty()){
            validInput = false;
            Alert emptySumma = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptySumma.setContentText("Поле ввода 'Сумма' пуст");
            emptySumma.initModality(Modality.APPLICATION_MODAL);
            emptySumma.initOwner(owner);
            emptySumma.showAndWait();
            if (emptySumma.getResult() == ButtonType.OK){
                emptySumma.close();
                txfSumma.requestFocus();
            }
        }
        if(txfNomer == null || txfNomer.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyNomer = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNomer.setContentText("Поле ввода '№ Договора' пуст");
            emptyNomer.initModality(Modality.APPLICATION_MODAL);
            emptyNomer.initOwner(owner);
            emptyNomer.showAndWait();
            if (emptyNomer.getResult() == ButtonType.OK){
                emptyNomer.close();
                txfNomer.requestFocus();
            }
        }
        return validInput;
    }
    public void colClient_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setClient(cellEditEvent.getNewValue());
    }
    public void colSposob_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setSposob(cellEditEvent.getNewValue());
    }
    public void colSrok_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setSrok(cellEditEvent.getNewValue());
    }
    public void colStavka_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setStavka(cellEditEvent.getNewValue());
    }
    public void colSumma_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setSumma(cellEditEvent.getNewValue());
    }
    public void colZakluchenie_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setZakluchenie(cellEditEvent.getNewValue());
    }
    public void colNomer_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataDogovora, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataDogovora, String>) e;
        DataDogovora dataDogovora = cellEditEvent.getRowValue();
        dataDogovora.setNomer(cellEditEvent.getNewValue());
    }

    public void handleDeleteButtonClick(ActionEvent event){
        if(!observableDataDogovoraList.isEmpty()){
            System.out.println("Delete button cliked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Вы уверены, что хотите удалить это? \n\nОтменить действие невозможно.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK){
                observableDataDogovoraList.removeAll(dogovorTable.getSelectionModel().getSelectedItem());
                dogovorTable.getSelectionModel().clearSelection();
            }else{deleteAlert.close();}
        }
    }
    public void handleClearButtonClick(ActionEvent event){
        txfClient.clear(); cbSposob.setValue("Способ погашения"); dpZakluchenie.setValue(null); txfSrok.clear();
        txfStavka.clear(); txfSumma.clear(); txfNomer.clear();
    }
    public void filterDataDogovora(String oldValue, String newValue){
        ObservableList<DataDogovora> filteredList = FXCollections.observableArrayList();
        if (txfPoisk == null || (newValue.length() < oldValue.length()) || newValue == null){
            dogovorTable.setItems(observableDataDogovoraList);
        }else{
            newValue = newValue.toUpperCase();
            for(DataDogovora dataDogovora : dogovorTable.getItems()){
                String filterFio = dataDogovora.getClient();
                if(filterFio.toUpperCase().contains(newValue)){
                    filteredList.add(dataDogovora);
                }
            }
            dogovorTable.setItems(filteredList);
        }
    }
    public void handleSave(ActionEvent event){
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить таблицу Договора");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableDataDogovoraList.isEmpty()){
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
                saveFile(dogovorTable.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<DataDogovora> observableDataDogovoraList, File file){
        try{
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
            for(DataDogovora dataDogovora : observableDataDogovoraList){
                outWriter.write(dataDogovora.toString());
                outWriter.newLine();
            }
            System.out.println(observableDataDogovoraList.toString());
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
