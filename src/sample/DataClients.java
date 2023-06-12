package sample;
import javafx.beans.property.*;

public class DataClients {

    private StringProperty fio = new SimpleStringProperty(this, "fio", "");
    public String getFio(){return fio.get();}
    public StringProperty fioProperty(){return fio;}
    public void setFio(String fio) {this.fio.set(fio);}

    private StringProperty gender = new SimpleStringProperty(this, "gender", "");
    public String getGender(){return gender.get();}
    public StringProperty genderProperty(){return gender;}
    public void setGender(String gender) {this.gender.set(gender);}

    private StringProperty birthD = new SimpleStringProperty(this, "birthD", "");
    public String getBirthD(){return birthD.get();}
    public StringProperty birthDProperty(){return birthD;}
    public void setBirthD(String birthD) {this.birthD.set(birthD);}

    private StringProperty birthP = new SimpleStringProperty(this, "birthP", "");
    public String getBirthP(){return birthP.get();}
    public StringProperty birthPProperty(){return birthP;}
    public void setBirthP(String birthP) {this.birthP.set(birthP);}

    private StringProperty grajdan = new SimpleStringProperty(this, "grajdan", "");
    public String getGrajdan(){return grajdan.get();}
    public StringProperty grajdanProperty(){return grajdan;}
    public void setGrajdan(String grajdan) {this.grajdan.set(grajdan);}

    private StringProperty seriya = new SimpleStringProperty(this, "seriya", "");
    public String getSeriya(){return seriya.get();}
    public StringProperty seriyaProperty(){return seriya;}
    public void setSeriya(String seriya) {this.seriya.set(seriya);}

    private StringProperty nomer = new SimpleStringProperty(this, "nomer", "");
    public String getNomer(){return nomer.get();}
    public StringProperty nomerProperty(){return nomer;}
    public void setNomer(String nomer) {this.nomer.set(nomer);}

    private StringProperty vidachi = new SimpleStringProperty(this, "vidachi", "");
    public String getVidachi(){return vidachi.get();}
    public StringProperty vidachiProperty(){return vidachi;}
    public void setVidachi(String vidachi) {this.vidachi.set(vidachi);}

    private StringProperty vidan = new SimpleStringProperty(this, "vidan", "");
    public String getVidan(){return vidan.get();}
    public StringProperty vidanProperty(){return vidan;}
    public void setVidan(String vidan) {this.vidan.set(vidan);}

    private StringProperty kod = new SimpleStringProperty(this, "kod", "");
    public String getKod(){return kod.get();}
    public StringProperty kodProperty(){return kod;}
    public void setKod(String kod) {this.kod.set(kod);}

    private StringProperty work = new SimpleStringProperty(this, "work", "");
    public String getWork(){return work.get();}
    public StringProperty workProperty(){return work;}
    public void setWork(String work) {this.work.set(work);}

    private StringProperty doljnost = new SimpleStringProperty(this, "doljnost", "");
    public String getDoljnost(){return doljnost.get();}
    public StringProperty doljnostProperty(){return doljnost;}
    public void setDoljnost(String doljnost) {this.doljnost.set(doljnost);}

    private StringProperty phone = new SimpleStringProperty(this, "phone", "");
    public String getPhone(){return phone.get();}
    public StringProperty phoneProperty(){return phone;}
    public void setPhone(String phone) {this.phone.set(phone);}

    private StringProperty adres = new SimpleStringProperty(this, "adres", "");
    public String getAdres(){return adres.get();}
    public StringProperty adresProperty(){return adres;}
    public void setAdres(String adres) {this.adres.set(adres);}

    private StringProperty mail = new SimpleStringProperty(this, "mail", "");
    public String getMail(){return mail.get();}
    public StringProperty mailProperty(){return mail;}
    public void setMail(String mail) {this.mail.set(mail);}

    //для вывода в консоль
    public String toString(){
        return "ФИО : " + getFio() + " | Пол : " + getGender() + " | Дата рождения : " + getBirthD() + " | Место рождения : " + getBirthP() +
                " | Гражданство :" + getGrajdan() +" | Паспорт_серия : " + getSeriya() + " | Паспорт_номер : " + getNomer() +" | Дата выдачи : " +
                getVidachi() +" | Кем выдан : " + getVidan() + " | Паспорт_код : " + getKod() +" | Место работы : " +
                getWork() +" | Должность : " + getDoljnost() + " | Телефон : " + getPhone() + " | Адрес : " + getAdres() + " | Mail : " + getMail();
    }
}
