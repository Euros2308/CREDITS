package sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataDogovora {

    private StringProperty nomer = new SimpleStringProperty(this, "nomer", "");
    public String getNomer(){return nomer.get();}
    public StringProperty nomerProperty(){return nomer;}
    public void setNomer(String nomer) {this.nomer.set(nomer);}

    private StringProperty client = new SimpleStringProperty(this, "client", "");
    public String getClient(){return client.get();}
    public StringProperty clientProperty(){return client;}
    public void setClient(String client) {this.client.set(client);}

    private StringProperty zakluchenie = new SimpleStringProperty(this, "zakluchenie", "");
    public String getZakluchenie(){return zakluchenie.get();}
    public StringProperty zakluchenieProperty(){return zakluchenie;}
    public void setZakluchenie(String zakluchenie) {this.zakluchenie.set(zakluchenie);}

    private StringProperty summa = new SimpleStringProperty(this, "summa", "");
    public String getSumma(){return summa.get();}
    public StringProperty summaProperty(){return summa;}
    public void setSumma(String summa) {this.summa.set(summa);}

    private StringProperty stavka = new SimpleStringProperty(this, "stavka", "");
    public String getStavka(){return stavka.get();}
    public StringProperty stavkaProperty(){return stavka;}
    public void setStavka(String stavka) {this.stavka.set(stavka);}

    private StringProperty srok = new SimpleStringProperty(this, "srok", "");
    public String getSrok(){return srok.get();}
    public StringProperty srokProperty(){return srok;}
    public void setSrok(String srok) {this.srok.set(srok);}

    private StringProperty sposob = new SimpleStringProperty(this, "sposob", "");
    public String getSposob(){return sposob.get();}
    public StringProperty sposobProperty(){return sposob;}
    public void setSposob(String sposob) {this.sposob.set(sposob);}

    public String toString(){
        return "№ Договора: " + getNomer() + " | Клиент: " + getClient() + " | Дата заключения: " +
                getZakluchenie() + " | Сумма: " + getSumma() +" | Процентная ставка:" + getStavka() +
                " | Срок погашения: " + getSrok() + " | Способ погашения: " + getSposob();
    }
}

