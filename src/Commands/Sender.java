package Commands;

import Exceptions.NumberNotAPeselException;

import java.time.LocalDate;

public class Sender {

    private int pesel;
    private String imie;
    private String nazwisko;
    private String adres;

    public Sender(int pesel,String imie, String nazwisko, String adres) throws NumberNotAPeselException {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;

        int digits = (int) (Math.log10(pesel) + 1);
        if (digits!=11)
            throw new NumberNotAPeselException(digits);

    }

    public LocalDate getBirthDate(){
        int num = pesel/100000;
        int yy = (num/10000);
        int mm = (num-yy)/100;
        int dd = num-mm;
        yy =+ 1900;
        return LocalDate.of(yy,mm,dd);
    }
    public String getSex(){
        return pesel%2==1?"Male":"Female";
    }

//    Nadawca posiada ponad wymienione wczesniej informacje takie dane jak imie, nazwisko,
//    pesel, adres, date urodzenia (data urodzenia nie jest przechowywana specjalnie w ramach pola
//            klasy - realizujemy ja w w postaci metody, która wyswietli date urodzenia jako obiekt klasy
//                                          LocalDate na podstawie numeru PESEL).
}
