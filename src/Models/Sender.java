package Models;

import Exceptions.NumberNotAPeselException;

import java.time.LocalDate;

public class Sender {

    private int pesel;
    private String imie;
    private String nazwisko;
    private String adres;
    private int warnings;

    public Sender(int pesel,String imie, String nazwisko, String adres) throws NumberNotAPeselException {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.warnings = 0;

        int digits = (int) (Math.log10(pesel) + 1);
        if (digits!=11)
            throw new NumberNotAPeselException(digits);

    }

    public LocalDate getBirthDate(){
        int num = pesel/100000;
        int yy = (num/10000);
        int mm = (num-yy)/100;
        int dd = num-mm;
        if (mm >20){
            yy =+ 2000;
            mm =- 20;
        }
        else yy =+ 1900;
        LocalDate date = LocalDate.of(yy,mm,dd);
        return date;
    }
    public String getSex(){
        return pesel%2==1?"Male":"Female";
    }
    public String getTitle(){
        return pesel%2==1?"Pan":"Pani";
    }

    public int getWarnings() {
        return warnings;
    }

    public void addWarnings() {
        this.warnings++;
    }

    @Override
    public String toString() {
        return "Sender{" +
                imie + ' ' +
                nazwisko +
                '}';
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }
    //    Nadawca posiada ponad wymienione wczesniej informacje takie dane jak imie, nazwisko,
//    pesel, adres, date urodzenia (data urodzenia nie jest przechowywana specjalnie w ramach pola
//            klasy - realizujemy ja w w postaci metody, która wyswietli date urodzenia jako obiekt klasy
//                                          LocalDate na podstawie numeru PESEL).
}
