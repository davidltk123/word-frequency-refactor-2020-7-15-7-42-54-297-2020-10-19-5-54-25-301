import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = calculateWordFrequency(sentence);

            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            return buildWordFrequencyResult(wordFrequencyList);

        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream().map(wordFrequency -> buildWordFrequencyLine(wordFrequency)).collect(Collectors.joining(LINE_FEED));
    }

    private String buildWordFrequencyLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getCount());
    }

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        return words.stream().distinct().map(word -> new WordFrequency(word, Collections.frequency(words, word))).collect(Collectors.toList());
    }

}
