package pl.edu.pg.eti.kaims.aod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final String[] interpunctionAndWhiteSpace = {
            " ", "\n", ",", ".", ":", ";", "?", "!"
    };

    private static String censor() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> wordsToCensor = new LinkedList<>();
        String sentence;

        // read input

        String current = br.readLine();
        while (current != null && !current.equals("") && current.length() != 0) {
            wordsToCensor.add(current);
            current = br.readLine();
        }
        sentence = br.readLine();
        String tmp = br.readLine();
        while (tmp != null && !tmp.equals("")) {
            sentence += "\n" + tmp;
            tmp = br.readLine();
        }
        // censor

        for (String w : wordsToCensor) {
            for (String ip : interpunctionAndWhiteSpace) {
                for (String ip2 : interpunctionAndWhiteSpace) {
                    String word = ip2 + w + ip;
                    StringBuilder sb = new StringBuilder();
                    sb.append(ip2);
                    sb.append(word.charAt(1));
                    sb.append("*".repeat(Math.max(0, w.length() - 2)));
                    sb.append(word.charAt(w.length()));
                    sb.append(ip);
                    String censoredWord = sb.toString();
                    sentence = sentence.replace(word, censoredWord);
                }
                // no interpunction
                String word = w + ip;
                StringBuilder sb = new StringBuilder();
                sb.append(word.charAt(0));
                sb.append("*".repeat(Math.max(0, w.length() - 2)));
                sb.append(word.charAt(w.length() - 1));
                sb.append(ip);
                String censoredWord = sb.toString();
                sentence = sentence.replace(word, censoredWord);

                // big letter
                StringBuilder wordUpperCase = new StringBuilder();
                wordUpperCase.append((w.charAt(0) + "").toUpperCase());
                for (int i = 1; i < w.length(); i++) {
                    wordUpperCase.append(w.charAt(i));
                }
                word = wordUpperCase.toString() + ip;

                sb = new StringBuilder();
                sb.append((word.charAt(0) + "").toUpperCase());
                sb.append("*".repeat(Math.max(0, w.length() - 2)));
                sb.append(word.charAt(w.length() - 1));
                sb.append(ip);
                censoredWord = sb.toString();
                sentence = sentence.replace(word, censoredWord);
            }
        }

        System.out.println(sentence);

        return sentence;
    }

    public static void countLetters() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();

        int topLetterEven = 0;  // most frequent small letter occurrences count
        int topLetterOdd = 0;   // most frequent big letter occurrences count

        Map<String, Integer> smallLetterOccurrences = new HashMap<>();
        Map<String, Integer> bigLetterOccurrences = new HashMap<>();

        while (tmp != null && !tmp.equals("") && tmp.length() != 0) {
            if (tmp.length() % 2 == 0) {
                for (int i = 0; i < tmp.length(); i++) {
                    String key = tmp.charAt(i) + "";
                    if (key.charAt(0) >= 'a' && key.charAt(0) <= 'z') {
                        Integer value = smallLetterOccurrences.getOrDefault(key, 0);
                        smallLetterOccurrences.put(key, value + 1);
                    }
                }
            } else {
                for (int i = 0; i < tmp.length(); i++) {
                    String key = tmp.charAt(i) + "";
                    if (key.charAt(0) >= 'A' && key.charAt(0) <= 'Z') {
                        Integer value = bigLetterOccurrences.getOrDefault(key, 0);
                        bigLetterOccurrences.put(key, value + 1);
                    }
                }
            }

            tmp = br.readLine();
        }

        topLetterEven = smallLetterOccurrences.values().stream().max(Comparator.naturalOrder()).orElse(0);
        topLetterOdd = bigLetterOccurrences.values().stream().max(Comparator.naturalOrder()).orElse(0);
        System.out.println(topLetterEven + " " + topLetterOdd);
    }

    public static void main(String[] args) throws IOException {
        censor();

        //countLetters();
    }

}
