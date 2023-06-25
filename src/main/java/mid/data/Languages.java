package mid.data;

import java.util.Arrays;

public enum Languages {

    RU("ru"), EN("en"), PT("pt"),PL("pl"), EN_IA("en_IA"), IS("is");

    private String language;

    Languages(String language) {
        this.language = language;
    }

    public static String[] getLanguages() {

        Languages[] languages = Languages.values();
        String[] result = new String[languages.length];
        int index = 0;
        for (Languages l : languages) {
            result[index++] = l.language;
        }
        return result;
    }

}
