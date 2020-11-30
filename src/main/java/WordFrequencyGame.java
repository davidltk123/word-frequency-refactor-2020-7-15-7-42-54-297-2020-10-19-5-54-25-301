import java.util.*;

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
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(SPACE_REGEX);

        List<WordFrequency> wordCountList = new ArrayList<>();
        for (String word : words) {
            wordCountList.add(new WordFrequency(word, 1));
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordFrequencyMap = getWordFrequencyMap(wordCountList);

        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequency>> entry : wordFrequencyMap.entrySet()) {
            wordFrequencyList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
        }
        return wordFrequencyList;
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
