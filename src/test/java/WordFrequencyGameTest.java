import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() {
        //Given
        String sentence = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words() {
        //Given
        String sentence = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() {
        //Given
        String sentence = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() {
        //Given
        String sentence = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() {
        //Given
        String sentence = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() {
        //Given
        String sentence = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_return_calcuate_error_with_null_input() {
        //Given
        String sentence = null;
        String expectResult = "Calculate Error";
        validate_Input_words_process_to_expected_word(sentence, expectResult);
    }

    private void validate_Input_words_process_to_expected_word(String sentence, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String actualResult = game.getResult(sentence);
        //Then
        assertEquals(expectResult, actualResult);
    }
}
