import org.atletic_bilbao_class.PascalClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PascalClassTest {


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("T1/T2")
    void stringNullOrEmpty(String input){
        assertEquals(input, PascalClass.toPascalCase(input));
    }

    @Test
    @DisplayName("T3")
    void stringFormedByOneWord(){
        assertEquals("Hello", PascalClass.toPascalCase("hello"));

    }

    @Test
    @DisplayName("T4")
    void stringFormedByMoreThanOneWord(){
        assertEquals("HelloWorld", PascalClass.toPascalCase("hello world"));
    }
    @Test
    @DisplayName("Boundary test: T5")
    void stringFormedByJustOneChar(){
        assertEquals("H", PascalClass.toPascalCase("h"));
    }

    @Test
    @DisplayName("Special test: T6")
    void stringFormedByOneWordOfCapitalLetters(){
        assertEquals("Hello", PascalClass.toPascalCase("HELLO"));
    }

    @Test
    @DisplayName("Special test: T7")
    void stringFormedByOneTwoWordsOfCapitalLetters(){
        assertEquals("HelloWorld", PascalClass.toPascalCase("HELLO WORLD"));
    }

    @Test
    @DisplayName("Special test: T8")
    void stringContainsSpecialCharacters(){
        assertEquals("HelloWorld", PascalClass.toPascalCase("hello@world"));
    }

    @Test
    @DisplayName("Special test: T9")
    void stringContainsNumebers(){
        assertEquals("Hello1world", PascalClass.toPascalCase("hello1world"));
    }

    @Test
    @DisplayName("Special Test: T10")
    void stringFormedByFirstWordOfLowerCaseAndSecondWordOfCapitalLetters(){
        assertEquals("HelloWorld", PascalClass.toPascalCase("helloWORLD"));
    }

    @Test
    @DisplayName("Special test: T11")
    void stringFormedByFirstWordOfCapitalLettersAndSecondWordOfLowerCase(){
        assertEquals("HellOworld", PascalClass.toPascalCase("HELLOworld"));
    }
}