package org.atletic_bilbao_class;

public class Main {
    public static void main(String[] args) {

        //Homework_1
        String prova ="Hello world!";
        String output;

        output = PascalClass.toPascalCase(prova);

        System.out.println("Stringa di prova: " + prova);
        System.out.println("output: " + output);




        //Homework_2
        String mainString= "aabb63334234232436";
        String subString = "abb633";
        mainString.contains(subString);
        Boolean esito;

        esito = ContainsClass.contains(mainString, subString);
        System.out.println("Esito: " + esito);

    }
}