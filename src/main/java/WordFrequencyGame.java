import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) {

        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(SPACE_REGEX);

                List<WordFrequency> wordCountList = new ArrayList<>();
                for (String word : words) {
                    wordCountList.add(new WordFrequency(word, 1));
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordFrequencyMap = getListMap(wordCountList);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordFrequencyMap.entrySet()) {
                    wordFrequencyList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
                }

                wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

                StringJoiner wordFrequencyResult = new StringJoiner(LINE_FEED);
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String wordFrequencyLine = wordFrequency.getWord() + " " + wordFrequency.getCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }
                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
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
