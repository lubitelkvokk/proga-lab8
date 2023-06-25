package mid.fabrics.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleFabric {


    public static ResourceBundle getLocale(String path, String loc) {
        Locale locale;
        switch (loc) {
            case "ru":
                locale = new Locale("ru", "RU");
                break;
            case "en":
                locale = Locale.ENGLISH;
                break;
            case "pt":
                locale = new Locale("pt");
                break;
            case "is":
                locale = new Locale("is");
                break;
            case "en_IA":
                locale = new Locale("en","IE");
                break;
            case "pl":
                locale = new Locale("pl");
                break;
            default:
                locale = Locale.getDefault();
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(path, locale);
        return resourceBundle;
    }
}
