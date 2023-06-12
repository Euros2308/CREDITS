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

public class Clients implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private ComboBox<String> cbGender;
    ObservableList<String> cbGenderData = FXCollections.observableArrayList();
    @FXML
    private TableView<DataClients> clientsTable;
    @FXML
    private TableColumn<DataClients, String> colAdres;
    @FXML
    private TableColumn<DataClients, String> colBirthD;
    @FXML
    private TableColumn<DataClients, String> colBirthP;
    @FXML
    private TableColumn<DataClients, String> colDoljnost;
    @FXML
    private TableColumn<DataClients, String> colFio;
    @FXML
    private TableColumn<DataClients, String> colGender;
    @FXML
    private TableColumn<DataClients, String> colGrajd;
    @FXML
    private TableColumn<DataClients, String> colKod;
    @FXML
    private TableColumn<DataClients, String> colMail;
    @FXML
    private TableColumn<DataClients, String> colNomer;
    @FXML
    private TableColumn<DataClients, String> colPhone;
    @FXML
    private TableColumn<DataClients,String> colSeriya;
    @FXML
    private TableColumn<DataClients, String> colVidachi;
    @FXML
    private TableColumn<DataClients, String> colVidan;
    @FXML
    private TableColumn<DataClients, String> colWork;
    @FXML
    private DatePicker dPBirthD;
    @FXML
    private DatePicker dpVidachi;
    @FXML
    private MenuBar fileMenu;
    @FXML
    private TextField txfAdres;
    @FXML
    private TextField txfBirthP;
    @FXML
    private TextField txfDoljnost;
    @FXML
    private TextField txfFIO;
    @FXML
    private ComboBox<String> cbGrajdan;
    ObservableList<String> cbGrajdanData = FXCollections.observableArrayList();
    @FXML
    private TextField txfKod;
    @FXML
    private TextField txfMail;
    @FXML
    private TextField txfNomer;
    @FXML
    private TextField txfPhone;
    @FXML
    private TextField txfPoisk;
    @FXML
    private TextField txfSeriya;
    @FXML
    private TextField txfVidan;
    @FXML
    private TextField txfWork;

    ObservableList<DataClients> observableDataClientsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        txfPoisk.textProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                filterDataClients((String) oldValue, (String) newValue);
            }
        });
        //инициализировать редактируемые атрибуты
        clientsTable.setEditable(true);
        colFio.setOnEditCommit(e -> colFio_OnEditCommit(e));
        colGender.setOnEditCommit(e -> colGender_OnEditCommit(e));
        colBirthD.setOnEditCommit(e -> colBirthD_OnEditCommit(e));
        colBirthP.setOnEditCommit(e -> colBirthP_OnEditCommit(e));
        colGrajd.setOnEditCommit(e -> colGrajd_OnEditCommit(e));
        colSeriya.setOnEditCommit(e -> colSeriya_OnEditCommit(e));
        colNomer.setOnEditCommit(e -> colNomer_OnEditCommit(e));
        colVidachi.setOnEditCommit(e -> colVidachi_OnEditCommit(e));
        colVidan.setOnEditCommit(e -> colVidan_OnEditCommit(e));
        colKod.setOnEditCommit(e -> colKod_OnEditCommit(e));
        colWork.setOnEditCommit(e -> colWork_OnEditCommit(e));
        colDoljnost.setOnEditCommit(e -> colDoljnost_OnEditCommit(e));
        colPhone.setOnEditCommit(e -> colPhone_OnEditCommit(e));
        colAdres.setOnEditCommit(e -> colAdres_OnEditCommit(e));
        colMail.setOnEditCommit(e -> colMail_OnEditCommit(e));

        clientsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colFio.setCellFactory(TextFieldTableCell.forTableColumn());
        colGender.setCellFactory(TextFieldTableCell.forTableColumn());
        colBirthD.setCellFactory(TextFieldTableCell.forTableColumn());
        colBirthP.setCellFactory(TextFieldTableCell.forTableColumn());
        colGrajd.setCellFactory(TextFieldTableCell.forTableColumn());
        colSeriya.setCellFactory(TextFieldTableCell.forTableColumn());
        colNomer.setCellFactory(TextFieldTableCell.forTableColumn());
        colVidachi.setCellFactory(TextFieldTableCell.forTableColumn());
        colVidan.setCellFactory(TextFieldTableCell.forTableColumn());
        colKod.setCellFactory(TextFieldTableCell.forTableColumn());
        colWork.setCellFactory(TextFieldTableCell.forTableColumn());
        colDoljnost.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colAdres.setCellFactory(TextFieldTableCell.forTableColumn());
        colMail.setCellFactory(TextFieldTableCell.forTableColumn());

        colFio.setCellValueFactory(cellData -> cellData.getValue().fioProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        colBirthD.setCellValueFactory(cellData -> cellData.getValue().birthDProperty());
        colBirthP.setCellValueFactory(cellData -> cellData.getValue().birthPProperty());
        colGrajd.setCellValueFactory(cellData -> cellData.getValue().grajdanProperty());
        colSeriya.setCellValueFactory(cellData -> cellData.getValue().seriyaProperty());
        colNomer.setCellValueFactory(cellData -> cellData.getValue().nomerProperty());
        colVidachi.setCellValueFactory(cellData -> cellData.getValue().vidachiProperty());
        colVidan.setCellValueFactory(cellData -> cellData.getValue().vidanProperty());
        colKod.setCellValueFactory(cellData -> cellData.getValue().kodProperty());
        colWork.setCellValueFactory(cellData -> cellData.getValue().workProperty());
        colDoljnost.setCellValueFactory(cellData -> cellData.getValue().doljnostProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        colAdres.setCellValueFactory(cellData -> cellData.getValue().adresProperty());
        colMail.setCellValueFactory(cellData -> cellData.getValue().mailProperty());

        //инициализировать комбоксы
        cbGenderData.add(new String("женский"));
        cbGenderData.add(new String("мужской"));
        cbGender.setItems(cbGenderData);
        cbGrajdanData.add(new String("Российская Федерация"));
        cbGrajdan.setItems(cbGrajdanData);

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        clientsTable.setItems(observableDataClientsList);
        clientsTable.setEditable(true);
        clientsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        clientsTable.setPlaceholder(new Label("Ваша таблица пуста"));

        txfFIO.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (txfFIO.isFocused()){
                    btnAdd.setDisable(false);
                }
            }
        });
        clientsTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(clientsTable.isFocused()){
                    btnDelete.setDisable(false);
                }
            }
        });
    }//конец инициализации
    /*
    -------------------------------------------------обработчики управления------------------------------------------
     */
    public void handleAddButtonClick(ActionEvent event){
        /*
        получение ввода от пользователя и добавление в таблицу
         */
        if(observableDataClientsList.size()<10){
            if(isValidInput(event)){
                if(cbGender.getValue().equals("женский")){
                    DataClients dataClients = new DataClients();
                    dataClients.setFio(txfFIO.getText()); dataClients.setGender(cbGender.getValue());
                    dataClients.setBirthD(dPBirthD.getValue().toString()); dataClients.setBirthP(txfBirthP.getText());
                    dataClients.setGrajdan(cbGrajdan.getValue()); dataClients.setSeriya(txfSeriya.getText());
                    dataClients.setNomer(txfNomer.getText()); dataClients.setVidachi(dpVidachi.getValue().toString());
                    dataClients.setVidan(txfVidan.getText()); dataClients.setKod(txfKod.getText());
                    dataClients.setWork(txfWork.getText()); dataClients.setDoljnost(txfDoljnost.getText());
                    dataClients.setPhone(txfPhone.getText()); dataClients.setAdres(txfAdres.getText());
                    dataClients.setMail(txfMail.getText());
                    observableDataClientsList.add(dataClients);
                    System.out.println(dataClients.toString());
                    txfFIO.clear(); cbGender.setValue("Пол"); dPBirthD.setValue(null); txfBirthP.clear();
                    cbGrajdan.setValue("Гражданство"); txfSeriya.clear(); txfNomer.clear(); dpVidachi.setValue(null);
                    txfVidan.clear(); txfKod.clear(); txfWork.clear(); txfDoljnost.clear(); txfPhone.clear();
                    txfAdres.clear(); txfMail.clear();
                }
                if(cbGender.getValue().equals("мужской")){
                    DataClients dataClients = new DataClients();
                    dataClients.setFio(txfFIO.getText()); dataClients.setGender(cbGender.getValue());
                    dataClients.setBirthD(dPBirthD.getValue().toString()); dataClients.setBirthP(txfBirthP.getText());
                    dataClients.setGrajdan(cbGrajdan.getValue()); dataClients.setSeriya(txfSeriya.getText());
                    dataClients.setNomer(txfNomer.getText()); dataClients.setVidachi(dpVidachi.getValue().toString());
                    dataClients.setVidan(txfVidan.getText()); dataClients.setKod(txfKod.getText());
                    dataClients.setWork(txfWork.getText()); dataClients.setDoljnost(txfDoljnost.getText());
                    dataClients.setPhone(txfPhone.getText()); dataClients.setAdres(txfAdres.getText());
                    dataClients.setMail(txfMail.getText());
                    observableDataClientsList.add(dataClients);
                    System.out.println(dataClients.toString());
                    txfFIO.clear(); cbGender.setValue("Пол"); dPBirthD.setValue(null); txfBirthP.clear();
                    cbGrajdan.setValue("Гражданство"); txfSeriya.clear(); txfNomer.clear(); dpVidachi.setValue(null);
                    txfVidan.clear(); txfKod.clear(); txfWork.clear(); txfDoljnost.clear(); txfPhone.clear();
                    txfAdres.clear(); txfMail.clear();
                }
                if(cbGrajdan.getValue().equals("Российская Федерация")){
                    DataClients dataClients = new DataClients();
                    dataClients.setFio(txfFIO.getText()); dataClients.setGender(cbGender.getValue());
                    dataClients.setBirthD(dPBirthD.getValue().toString()); dataClients.setBirthP(txfBirthP.getText());
                    dataClients.setGrajdan(cbGrajdan.getValue()); dataClients.setSeriya(txfSeriya.getText());
                    dataClients.setNomer(txfNomer.getText()); dataClients.setVidachi(dpVidachi.getValue().toString());
                    dataClients.setVidan(txfVidan.getText()); dataClients.setKod(txfKod.getText());
                    dataClients.setWork(txfWork.getText()); dataClients.setDoljnost(txfDoljnost.getText());
                    dataClients.setPhone(txfPhone.getText()); dataClients.setAdres(txfAdres.getText());
                    dataClients.setMail(txfMail.getText());
                    observableDataClientsList.add(dataClients);
                    System.out.println(dataClients.toString());
                    txfFIO.clear(); cbGender.setValue("Пол"); dPBirthD.setValue(null); txfBirthP.clear();
                    cbGrajdan.setValue("Гражданство"); txfSeriya.clear(); txfNomer.clear(); dpVidachi.setValue(null);
                    txfVidan.clear(); txfKod.clear(); txfWork.clear(); txfDoljnost.clear(); txfPhone.clear();
                    txfAdres.clear(); txfMail.clear();
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
                txfFIO.clear();
                cbGender.setValue("Пол");
                dPBirthD.setValue(null);
                txfBirthP.clear();
                cbGrajdan.setValue("Гражданство");
                txfSeriya.clear();
                txfNomer.clear();
                dpVidachi.setValue(null);
                txfVidan.clear();
                txfKod.clear();
                txfWork.clear();
                txfDoljnost.clear();
                txfPhone.clear();
                txfAdres.clear();
                txfMail.clear();
            }
        }
    }
    /*
    в случае пустых полей.
    выдает предупреждение о соответствующем пустом поле и запрашивает фокусировку на этом поле
     */
    private boolean isValidInput(ActionEvent event){
        Boolean validInput = true;
        if(txfFIO == null || txfFIO.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyFio = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFio.setContentText("Поле ввода ФИО пуст");
            emptyFio.initModality(Modality.APPLICATION_MODAL);
            emptyFio.initOwner(owner);
            emptyFio.showAndWait();
            if (emptyFio.getResult() == ButtonType.OK){
                emptyFio.close();
                txfFIO.requestFocus();
            }
        }
        if(cbGender == null || cbGender.getValue().isEmpty()){
            validInput = false;
            Alert emptyGender = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGender.setContentText("Поле 'Пол' пуст");
            emptyGender.initModality(Modality.APPLICATION_MODAL);
            emptyGender.initOwner(owner);
            emptyGender.showAndWait();
            if (emptyGender.getResult() == ButtonType.OK){
                emptyGender.close();
                cbGender.requestFocus();
            }
        }
        if(dPBirthD == null || dPBirthD.getValue().toString().isEmpty()){
            validInput = false;
            Alert emptyBirthD = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyBirthD.setContentText("Поле 'Дата рождения' пуст");
            emptyBirthD.initModality(Modality.APPLICATION_MODAL);
            emptyBirthD.initOwner(owner);
            emptyBirthD.showAndWait();
            if (emptyBirthD.getResult() == ButtonType.OK){
                emptyBirthD.close();
                dPBirthD.requestFocus();
            }
        }
        if(txfBirthP == null || txfBirthP.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyBirthP = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyBirthP.setContentText("Поле ввода 'Место рождения' пуст");
            emptyBirthP.initModality(Modality.APPLICATION_MODAL);
            emptyBirthP.initOwner(owner);
            emptyBirthP.showAndWait();
            if (emptyBirthP.getResult() == ButtonType.OK){
                emptyBirthP.close();
                txfBirthP.requestFocus();
            }
        }
        if(cbGrajdan == null || cbGrajdan.getValue().isEmpty()){
            validInput = false;
            Alert emptyGrajdan = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGrajdan.setContentText("Поле 'Гражданство' пуст");
            emptyGrajdan.initModality(Modality.APPLICATION_MODAL);
            emptyGrajdan.initOwner(owner);
            emptyGrajdan.showAndWait();
            if (emptyGrajdan.getResult() == ButtonType.OK){
                emptyGrajdan.close();
                cbGrajdan.requestFocus();
            }
        }
        if(txfSeriya == null || txfSeriya.getText().trim().isEmpty()){
            validInput = false;
            Alert emptySeriya = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptySeriya.setContentText("Поле ввода Паспорт_серия пуст");
            emptySeriya.initModality(Modality.APPLICATION_MODAL);
            emptySeriya.initOwner(owner);
            emptySeriya.showAndWait();
            if (emptySeriya.getResult() == ButtonType.OK){
                emptySeriya.close();
                txfSeriya.requestFocus();
            }
        }
        if(txfNomer == null || txfNomer.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyNomer = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNomer.setContentText("Поле ввода Паспорт_номер пуст");
            emptyNomer.initModality(Modality.APPLICATION_MODAL);
            emptyNomer.initOwner(owner);
            emptyNomer.showAndWait();
            if (emptyNomer.getResult() == ButtonType.OK){
                emptyNomer.close();
                txfNomer.requestFocus();
            }
        }
        if(dpVidachi == null || dpVidachi.getValue().toString().isEmpty()){
            validInput = false;
            Alert emptyVidachi = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyVidachi.setContentText("Поле 'Дата выдачи' пуст");
            emptyVidachi.initModality(Modality.APPLICATION_MODAL);
            emptyVidachi.initOwner(owner);
            emptyVidachi.showAndWait();
            if (emptyVidachi.getResult() == ButtonType.OK){
                emptyVidachi.close();
                dpVidachi.requestFocus();
            }
        }
        if(txfVidan == null || txfVidan.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyVidan = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyVidan.setContentText("Поле ввода 'Кем выдан' пуст");
            emptyVidan.initModality(Modality.APPLICATION_MODAL);
            emptyVidan.initOwner(owner);
            emptyVidan.showAndWait();
            if (emptyVidan.getResult() == ButtonType.OK){
                emptyVidan.close();
                txfVidan.requestFocus();
            }
        }
        if(txfKod == null || txfKod.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyKod = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyKod.setContentText("Поле ввода 'Паспорт_код' пуст");
            emptyKod.initModality(Modality.APPLICATION_MODAL);
            emptyKod.initOwner(owner);
            emptyKod.showAndWait();
            if (emptyKod.getResult() == ButtonType.OK){
                emptyKod.close();
                txfKod.requestFocus();
            }
        }
        if(txfWork == null || txfWork.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyWork = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyWork.setContentText("Поле ввода 'Место работы' пуст");
            emptyWork.initModality(Modality.APPLICATION_MODAL);
            emptyWork.initOwner(owner);
            emptyWork.showAndWait();
            if (emptyWork.getResult() == ButtonType.OK){
                emptyWork.close();
                txfWork.requestFocus();
            }
        }
        if(txfDoljnost == null || txfDoljnost.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyDoljnost = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyDoljnost.setContentText("Поле ввода Должность пуст");
            emptyDoljnost.initModality(Modality.APPLICATION_MODAL);
            emptyDoljnost.initOwner(owner);
            emptyDoljnost.showAndWait();
            if (emptyDoljnost.getResult() == ButtonType.OK){
                emptyDoljnost.close();
                txfDoljnost.requestFocus();
            }
        }
        if(txfPhone == null || txfPhone.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyPhone = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyPhone.setContentText("Поле ввода Телефон пуст");
            emptyPhone.initModality(Modality.APPLICATION_MODAL);
            emptyPhone.initOwner(owner);
            emptyPhone.showAndWait();
            if (emptyPhone.getResult() == ButtonType.OK){
                emptyPhone.close();
                txfPhone.requestFocus();
            }
        }
        if(txfAdres == null || txfAdres.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyAdres = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyAdres.setContentText("Поле ввода Адрес пуст");
            emptyAdres.initModality(Modality.APPLICATION_MODAL);
            emptyAdres.initOwner(owner);
            emptyAdres.showAndWait();
            if (emptyAdres.getResult() == ButtonType.OK){
                emptyAdres.close();
                txfAdres.requestFocus();
            }
        }
        if(txfMail == null || txfMail.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyMail = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyMail.setContentText("Поле ввода Эл.почта пуст");
            emptyMail.initModality(Modality.APPLICATION_MODAL);
            emptyMail.initOwner(owner);
            emptyMail.showAndWait();
            if (emptyMail.getResult() == ButtonType.OK){
                emptyMail.close();
                txfMail.requestFocus();
            }
        }
        return validInput;
    }
    //обработчик изменения столбцов
    public void colFio_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setFio(cellEditEvent.getNewValue());
    }
    public void colGender_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setGender(cellEditEvent.getNewValue());
    }
    public void colBirthD_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setBirthD(cellEditEvent.getNewValue());
    }
    public void colBirthP_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setBirthP(cellEditEvent.getNewValue());
    }
    public void colGrajd_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setGrajdan(cellEditEvent.getNewValue());
    }
    public void colSeriya_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setSeriya(cellEditEvent.getNewValue());
    }
    public void colNomer_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setNomer(cellEditEvent.getNewValue());
    }
    public void colVidachi_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setVidachi(cellEditEvent.getNewValue());
    }
    public void colVidan_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setVidan(cellEditEvent.getNewValue());
    }
    public void colKod_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setKod(cellEditEvent.getNewValue());
    }
    public void colWork_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setWork(cellEditEvent.getNewValue());
    }
    public void colDoljnost_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setDoljnost(cellEditEvent.getNewValue());
    }
    public void colPhone_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setPhone(cellEditEvent.getNewValue());
    }
    public void colAdres_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setAdres(cellEditEvent.getNewValue());
    }
    public void colMail_OnEditCommit(Event e){
        TableColumn.CellEditEvent<DataClients, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<DataClients, String>) e;
        DataClients dataClients = cellEditEvent.getRowValue();
        dataClients.setMail(cellEditEvent.getNewValue());
    }
    public void handleDeleteButtonClick(ActionEvent event){
        if(!observableDataClientsList.isEmpty()){
            System.out.println("Delete button cliked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Вы уверены, что хотите удалить это? \n\nОтменить действие невозможно.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK){
                observableDataClientsList.removeAll(clientsTable.getSelectionModel().getSelectedItem());
                clientsTable.getSelectionModel().clearSelection();
            }else{deleteAlert.close();}
        }
    }
    public void handleClearButtonClick(ActionEvent event){
        txfFIO.clear(); cbGender.setValue("Пол"); dPBirthD.setValue(null); txfBirthP.clear();
        cbGrajdan.setValue("Гражданство"); txfSeriya.clear(); txfNomer.clear(); dpVidachi.setValue(null);
        txfVidan.clear(); txfKod.clear(); txfWork.clear(); txfDoljnost.clear(); txfPhone.clear();
        txfAdres.clear(); txfMail.clear();
    }
    //Поиск информации по ФИО клиента
    public void filterDataClients(String oldValue, String newValue){
        ObservableList<DataClients> filteredList = FXCollections.observableArrayList();
        if (txfPoisk == null || (newValue.length() < oldValue.length()) || newValue == null){
            clientsTable.setItems(observableDataClientsList);
        }else{
            newValue = newValue.toUpperCase();
            for(DataClients dataClients : clientsTable.getItems()){
                String filterFio = dataClients.getFio();
                if(filterFio.toUpperCase().contains(newValue)){
                    filteredList.add(dataClients);
                }
            }
            clientsTable.setItems(filteredList);
        }
    }
    public void handleSave(ActionEvent event){
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить таблицу Клиенты");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableDataClientsList.isEmpty()){
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
                saveFile(clientsTable.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<DataClients> observableDataClientsList, File file){
        try{
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
            for(DataClients dataClients : observableDataClientsList){
                outWriter.write(dataClients.toString());
                outWriter.newLine();
            }
            System.out.println(observableDataClientsList.toString());
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
