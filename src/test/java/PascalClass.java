import org.apache.commons.lang3.StringUtils;

/**
 * Classe PascalClass: contiene un metodo per convertire una stringa in PascalCase.
 */
public class PascalClass {

    /**
     * Converte una stringa in PascalCase.
     *
     * @param inputString La stringa da convertire.
     * @return La stringa convertita in PascalCase.
     */
    public static String toPascalCase(String inputString) {
        //Verifica se la stringa è nulla
        if(inputString == null){
            return null;
        }


         String pascalCase = "";
        // Verifica se la stringa è vuota o contiene solo spazi vuoti
        if (StringUtils.isBlank(inputString)) {
            return inputString;
        }



        // Inizializza la stringa PascalCase

        char newChar;
        boolean toUpper = false;
        // Converte la stringa in minuscolos
        //inputString=inputString.toLowerCase();
        char[] charArray = inputString.toCharArray();

        // Itera attraverso i caratteri della stringa
        for (int ctr = 0; ctr <= charArray.length - 1; ctr++) {
            // Primo carattere: converte in maiuscolo e aggiunge alla stringa PascalCase

            if (ctr == 0 && Character.isLetterOrDigit(charArray[ctr])) {
                newChar = Character.toUpperCase(charArray[ctr]);
                pascalCase = Character.toString(newChar);
                continue;
            }

            // Carattere spazio: imposta il flag per convertire il prossimo carattere in maiuscolo
            if (charArray[ctr] == ' ') {
                toUpper = true;
                continue;
            }
            //fix test 8
            if (!Character.isLetterOrDigit(charArray[ctr])) {
                toUpper = true;
                continue;
            }

            // Converti il carattere successivo in maiuscolo e aggiungilo alla stringa PascalCase
            if (toUpper) {
                newChar = Character.toUpperCase(charArray[ctr]);
                pascalCase += Character.toString(newChar);
                toUpper = false;
                continue;
            }
            //Fix test 6,7,10

            if(Character.isUpperCase(charArray[ctr-1]) && Character.isUpperCase(charArray[ctr])){

                //fix test 11
                if(ctr<charArray.length-1){
                    if(Character.isUpperCase(charArray[ctr]) && Character.isLowerCase(charArray[ctr+1])) {
                        newChar = Character.toUpperCase(charArray[ctr]);
                        pascalCase += Character.toString(newChar);
                        continue;
                    }
                }


                newChar = Character.toLowerCase(charArray[ctr]);
                pascalCase += Character.toString(newChar);
                continue;

            }

            if(Character.isLowerCase(charArray[ctr-1]) && Character.isUpperCase(charArray[ctr])){
                newChar = Character.toUpperCase(charArray[ctr]);
                pascalCase += Character.toString(newChar);
                continue;

            }


            // Aggiungi il carattere corrente alla stringa PascalCase
            pascalCase += Character.toString(charArray[ctr]);
        }

        // Restituisce la stringa convertita in PascalCase
        return pascalCase;
    }
}

