package pl.kursjava.koty.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pl.kursjava.koty.domain.Kot;

public class Interfejs {
    static Scanner in = new Scanner(System.in);
    static KotDAO kotDao = new KotDAO();
    public static void main(String[] args) {
        String wybor;
        while (true) {
            System.out.println("Co chcesz zrobic?");
            System.out.println("1 - dodaj kota");
            System.out.println("2 - pokaż koty");
            System.out.println("x - wyjście");
            System.out.print("Twój wybór: ");
            wybor=getUserInput();
            switch(wybor) {
                case "1":
                    dodajKota();
                    break;
                case "2":
                    pokazKoty();
                    break;
                case "x" :
                    break;
            }
            if(wybor.equals("x")) break;
        }
    }
    public static String getUserInput() {
        return in.nextLine();
    }

    private static void dodajKota()
    {
        Kot kotek = new Kot();
        System.out.print("Podaj imie kota: ");
        kotek.setImie(getUserInput());
        System.out.print("Podaj imie opiekuna: ");
        kotek.setImieOpiekuna(getUserInput());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        do {
            System.out.print("Podaj date urodzenia kota w formacie RRRR.MM.DD: ");
            Pattern pattern = Pattern.compile("[1-9][0-9]{3}\\.[0-1][0-9]{1}\\.[0-3][0-9]{1}");
            String data;
            data = getUserInput();
            Matcher matcher = pattern.matcher(data);
            if (matcher.matches() == true) {
                try {
                    kotek.setDataUrodzenia(sdf.parse(data));
                } catch (ParseException pe) {
                    System.out.println("Niepoprawny format daty catch!");
                }
            } else System.out.println("Niepoprawny format daty!");
        } while (kotek.getDataUrodzenia() == null);
        do {
            System.out.print("Podaj masę kota w kilogramach: ");
            //try {
            Pattern pattern = Pattern.compile("[0-9]{1,2}\\.[0-9]{1,2}");
            String waga = getUserInput();
            Matcher matcher = pattern.matcher(waga);
            if (matcher.matches() == true) {
                kotek.setWaga(Float.valueOf(waga));
            } else System.out.println("Zły format liczby!");
            //}
            //catch(NumberFormatException nfe) {
            //System.out.println("Zły format liczby!");
            //}
        } while (kotek.getWaga() == null);
        kotDao.dodajKota(kotek);
    }
    private static void pokazKoty()
    {
        for(int i=0; i<kotDao.getKoty().size();i++)
        {
            System.out.println(i + ": " + kotDao.getKoty().get(i).getImie());
        }

        System.out.println("Którego kota chcesz poznać bliżej?");
        Pattern pattern = Pattern.compile("[0-9]+");
        String numerWczytany = getUserInput();
        Matcher matcher = pattern.matcher(numerWczytany);
        if(matcher.matches()==true)
        {
            Integer numerKota = Integer.parseInt(numerWczytany);
            if(kotDao.getKoty().size()>numerKota)
            {
                System.out.println(kotDao.getKoty().get(numerKota).przedstawSie());
            }
            else
            {
                System.out.println("Nie znaleziono kota o takim numerze!");
            }
        }
    }
}
