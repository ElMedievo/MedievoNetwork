package org.elmedievo.medievoapi.Util.Methods.Utility;

public class upperCaseWords {

    public static String upperCaseWords(String sentence) {
        String[] words = sentence.replaceAll("\\s+", " ").trim().split(" ");
        String newSentence = "";
        for (String word : words) {
            for (int i = 0; i < word.length(); i++)
                newSentence = newSentence + ((i == 0) ? word.substring(i, i + 1).toUpperCase():
                        (i != word.length() - 1) ? word.substring(i, i + 1).toLowerCase() : word.substring(i, i + 1).toLowerCase().toLowerCase() + " ");
        }
        return newSentence;
    }
}
