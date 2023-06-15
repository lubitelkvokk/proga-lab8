package client.global;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleSingleton {

    private static ResourceBundle resourceBundle;

    public static void setResourceBundle(ResourceBundle x){
        resourceBundle = x;
    }

    public static ResourceBundle getResourceBundle(){
        if (resourceBundle == null){
            resourceBundle = ResourceBundle.
                    getBundle("com.example.lab8.locale.locale",Locale.ENGLISH);
        }
        return resourceBundle;
    }
}
