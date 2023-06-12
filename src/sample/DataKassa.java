package sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataKassa {

    private StringProperty client = new SimpleStringProperty(this, "client", "");
    public String getClient(){return client.get();}
    public StringProperty clientProperty(){return client;}
    public void setClient(String client) {this.client.set(client);}

    private StringProperty summa = new SimpleStringProperty(this, "summa", "");
    public String getSumma(){return summa.get();}
    public StringProperty summaProperty(){return summa;}
    public void setSumma(String summa) {this.summa.set(summa);}

    private StringProperty dolg = new SimpleStringProperty(this, "dolg", "");
    public String getDolg(){return dolg.get();}
    public StringProperty dolgProperty(){return dolg;}
    public void setDolg(String dolg) {this.dolg.set(dolg);}

    private StringProperty vozvrat = new SimpleStringProperty(this, "vozvrat", "");
    public String getVozvrat(){return vozvrat.get();}
    public StringProperty vozvratProperty(){return vozvrat;}
    public void setVozvratr(String vozvrat) {this.vozvrat.set(vozvrat);}

    private StringProperty date = new SimpleStringProperty(this, "date", "");
    public String getDate(){return date.get();}
    public StringProperty dateProperty(){return date;}
    public void setDate(String date) {this.date.set(date);}

    private StringProperty ostatok = new SimpleStringProperty(this, "ostatok", "");
    public String getOstatok(){return ostatok.get();}
    public StringProperty ostatokProperty(){return ostatok;}
    public void setOstatok(String ostatok) {this.ostatok.set(ostatok);}

    public String toString(){
        return "Клиент: " + getClient() + " | Сумма кредита: " + getSumma() + " | Долг: " +
                getDolg() + " | Возврат: " + getVozvrat() +" | Дата возврата:" + getDate() +
                " | Остаток: " + getOstatok();
    }
}
