package communication;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private ResourceBundle messages;

    public LocalizationManager(String languageCode) {
        Locale locale = new Locale(languageCode);
        this.messages = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }

}
