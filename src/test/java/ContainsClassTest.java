import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;
import org.atletic_bilbao_class.ContainsClass;


import static org.assertj.core.api.Assertions.*;

public class ContainsClassTest {


    @Property
    void subStringContained(@ForAll("mainStringGenerated")String mainString,
                           @ForAll @IntRange(min = 0, max = 4) int startIndex,
                           @ForAll @IntRange(min = 5, max = 9) int endIndex){

        String subString = mainString.substring(startIndex, endIndex);
        assertThat(ContainsClass.contains(mainString, subString)).isTrue();

    }

    @Property
    void invalidMainString(@ForAll("invalidStringGenerated") String mainString, @ForAll String subString){
        assertThatThrownBy(() -> ContainsClass.contains(mainString, subString)).isInstanceOf(IllegalArgumentException.class);
    }


    @Property
    void invalidSubString (@ForAll String mainString, @ForAll("invalidStringGenerated") String subString){
        assertThatThrownBy(() -> ContainsClass.contains(mainString, subString)).isInstanceOf(IllegalArgumentException.class);

    }


    @Property
    void subStringNotContained(@ForAll("mainStringGeneratedToBeFalse") String mainString,
                               @ForAll("substringGeneretedToBeFalse")@NotEmpty @NotBlank String subString){

        assertThat(ContainsClass.contains(mainString, subString)).isFalse();

    }

    //Generazione di statistiche
    //Statistica per verificare che la stringa sia contenuta o meno in percentuale
    //con gli stessi parametri
    @Property
    void containedOrNotStats(@ForAll("mainStringGenerated") String mainString,
                             @ForAll("subStringGenerated") String subString){

        String result = ContainsClass.contains(mainString, subString) ? "Contained String" : "Not Contained String";
        Statistics.label("Esito").collect(result);
    }


    //Statistica per verificare che all'aumentare della lunghezza della main string, la percentuale di stringhe contenute aumenta
    @Property(generation = GenerationMode.RANDOMIZED)
    @StatisticsReport(format = Histogram.class)
    void increaseMainStringLenghtStats(@ForAll("mainStringGenerated") String mainString,
                                  @ForAll("subStringGenerated") String subString){

        for(int i=10; i<100; i+=10) {
            mainString = Arbitraries
                    .strings()
                    .withCharRange('a', 'z')
                    .withChars('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '|','0','1','2','3','4','5','6','7','8','9')
                    .ofLength(i)
                    .sample();
            boolean isContained = ContainsClass.contains(mainString, subString);
            Statistics.label("How change")
                    .collect(Tuple.of(i, isContained ? "Contained" : "Not contained"));

        }
    }


    //Statistiche per verificare cha al diminuire della lunghezza della subString, la percentuale delle stringhe contenute aumenta
    @Property
    @StatisticsReport(format = Histogram.class)
    void decreaseSubStringLenghtStats(@ForAll("mainStringGenerated") String mainString,
                                 @ForAll("subStringGenerated") String subString){
        for(int i=10; i>0; i--) {
            subString = Arbitraries
                    .strings()
                    .withCharRange('a', 'z')
                    .withChars('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '|','0','1','2','3','4','5','6','7','8','9')
                    .ofLength(i)
                    .sample();
            boolean isContained = ContainsClass.contains(mainString, subString);
            Statistics.label("How change")
                    .collect(Tuple.of(i, isContained ? "Contained" : "Not contained"));

        }
    }

    //Generazione di stringhe per test
    @Provide
    Arbitrary<String> mainStringGenerated() {
        return Arbitraries.strings().withCharRange('a', 'z').withChars('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '|','0','1','2','3','4','5','6','7','8','9').ofLength(10);
    }
    @Provide
    Arbitrary<String> subStringGenerated() {

        return Arbitraries.strings().withCharRange('a','z').withChars('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '|','0','1','2','3','4','5','6','7','8','9').ofMinLength(1).ofMaxLength(9);
    }

    @Provide
    private Arbitrary<String> invalidStringGenerated() {
        return Arbitraries.oneOf(
                Arbitraries.strings().filter(s-> s == null || s.length() == 0)
        );
    }

    @Provide
    Arbitrary<String> mainStringGeneratedToBeFalse() {
        return Arbitraries.strings().withCharRange('a', 'l').withChars('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '|','0','1','2','3','4','5','6','7','8','9').ofLength(10);
    }

    @Provide
    private Arbitrary<String> substringGeneretedToBeFalse() {
        return Arbitraries.strings().withCharRange('m', 'z').ofMinLength(1).ofMaxLength(10);
    }




}
