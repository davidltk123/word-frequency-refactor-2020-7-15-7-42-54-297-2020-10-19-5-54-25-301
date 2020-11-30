import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = calculateWorldFrequency(sentence);

            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            return buildWordFrequencyResult(wordFrequencyList);
        } catch (Exception exception) {
            return "Calculate Error";
        }
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner wordFrequencyResult = new StringJoiner(LINE_FEED);
        for (WordFrequency wordFrequency : wordFrequencyList) {
            String wordFrequencyLine = buildWorldFrequencyLine(wordFrequency);
            wordFrequencyResult.add(wordFrequencyLine);
        }
        return wordFrequencyResult.toString();
    }

    private String buildWorldFrequencyLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getCount());
    }

    private List<WordFrequency> calculateWorldFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        return words.stream().distinct().map(word -> new WordFrequency(word,Collections.frequency(words,word))).collect(Collectors.toList());
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), arr);
            } else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordFrequencyMap;
    }
}
