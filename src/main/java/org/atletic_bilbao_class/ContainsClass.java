package org.atletic_bilbao_class;

public class ContainsClass {
    public static boolean contains(String mainString, String Substring) {
        boolean flag = false;

        if (mainString == null || mainString.trim().equals("")) {
                throw new IllegalArgumentException("mainString can't be null o empty");
        }

        //Aggiunta verifica che la sub String non sia vuota
        if (Substring == null || Substring.trim().equals("")) {
            throw new IllegalArgumentException("subString can't be null o empty");
        }

        char fullstring[] = mainString.toLowerCase().toCharArray();
        char subString[] = Substring.toLowerCase().toCharArray();
        int counter = 0;
        int saveIndex = 0;
        if (subString.length == 0) {
            flag = true;
            return flag;
        }

        for (int i = 0; i < fullstring.length; i++) {

            if (fullstring[i] == subString[counter]) {
                if(counter==0) {
                    saveIndex = i;
                }
                counter++;

            } else {
                if (counter > 0) {
                    i = saveIndex;
                }
                counter = 0;


            }

            if (counter == subString.length) {
                flag = true;
                return flag;
            }

        }


        return flag;
    }
}