import java.util.HashMap;
import java.util.Map;

public class Translator {
    private final Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    public void addWord(String englishWord, String ukrainianWord) {
        if (englishWord != null && ukrainianWord != null) {
            dictionary.put(englishWord.trim().toLowerCase(), ukrainianWord.trim().toLowerCase());
        }
    }

    public String translatePhrase(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            return "";
        }

        String[] tokens = phrase.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            String prefix = token.replaceAll("^[\\p{Punct}]+", "");
            String suffix = "";
            
            int pIndex = token.length();
            while (pIndex > 0 && isPunctuation(token.charAt(pIndex - 1))) {
                pIndex--;
            }
            String cleanWord = token.substring(0, pIndex).replaceAll("^[\\p{Punct}]+", "");
            suffix = token.substring(pIndex);

            String key = cleanWord.toLowerCase();
            String translatedWord = dictionary.getOrDefault(key, "[" + cleanWord + "]");

            result.append(translatedWord).append(suffix);
            if (i < tokens.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    private boolean isPunctuation(char c) {
        return "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".indexOf(c) != -1;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}