package myapp.services;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Load, save and modify application settings
 */
public class UserSettingsService {

    public static final String DARK = "DARK";
    public static final String LIGHT = "LIGHT";
    private static final Gson gson = new GsonBuilder().create();
    private static final String WINDOW_X = "WINDOW_X";
    private static final String WINDOW_Y = "WINDOW_Y";
    private static final String WINDOW_WIDTH = "WINDOW_WIDTH";
    private static final String WINDOW_HEIGHT = "WINDOW_HEIGHT";
    private static final String WINDOW_MAXIMIZED = "WINDOW_MAXIMIZED";
    private static final String SCREENS_HASH = "SCREENS_HASH";
    private static final String LOCALE = "LOCALE";
    private static final String THEME = "THEME";
    private static final String DEMO = "DEMO";
    private static Map<String, String> settings = new HashMap<>();

    public static final String APP_SETTINGS_FILE = "settings";

    private static File getSettingsFile() throws IOException {
        String path = LocalDirService.getUserDataDirPath();
        return new File(path + File.separator + APP_SETTINGS_FILE);
    }

    private static synchronized void loadSettings() {
        try {
            File file = getSettingsFile();
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
            Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
            FileReader f = new FileReader(file);
            settings = gson.fromJson(f, mapType);
            f.close();
            if (settings == null) {
                settings = new HashMap<>();
            }

        } catch (IOException | JsonSyntaxException e) {
            settings = new HashMap<>();
        }
    }

    public static synchronized void saveSettings() {
        try {
            File filePath = getSettingsFile();
            FileWriter f = new FileWriter(filePath);
            gson.toJson(settings, f);
            f.flush();
            f.close();

        } catch (IOException e) {
            Logger.error(" caught a {}\n with message: {}", e.getClass(), e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> getSettings() {
        if (settings == null || settings.isEmpty()) {
            loadSettings();
        }
        return settings;
    }


    //**************************************************
    //* WINDOW
    //**************************************************
    public static void setWindowPosition(
            double x,
            double y,
            double width,
            double height,
            boolean maximized,
            String screensHash) {
        getSettings().put(WINDOW_X, String.valueOf(x));
        getSettings().put(WINDOW_Y, String.valueOf(y));
        getSettings().put(WINDOW_WIDTH, String.valueOf(width));
        getSettings().put(WINDOW_HEIGHT, String.valueOf(height));
        getSettings().put(WINDOW_MAXIMIZED, String.valueOf(maximized));
        getSettings().put(SCREENS_HASH, screensHash);
    }

    public static Optional<Double> getWindowX() {
        return Optional.ofNullable(getSettings().get(WINDOW_X)).map(Double::valueOf);
    }

    public static Optional<Double> getWindowY() {
        return Optional.ofNullable(getSettings().get(WINDOW_Y)).map(Double::valueOf);
    }

    public static Optional<Double> getWindowWidth() {
        return Optional.ofNullable(getSettings().get(WINDOW_WIDTH)).map(Double::valueOf);
    }

    public static Optional<Double> getWindowHeight() {
        return Optional.ofNullable(getSettings().get(WINDOW_HEIGHT)).map(Double::valueOf);
    }

    public static Optional<Boolean> getWindowMaximized() {
        return Optional.ofNullable(getSettings().get(WINDOW_MAXIMIZED)).map(Boolean::valueOf);
    }

    public static Optional<String> getScreensHash() {
        return Optional.ofNullable(getSettings().get(SCREENS_HASH));
    }

    //**************************************************
    //* LOCALE
    //**************************************************
    public static Optional<String> getLocale() {
        return Optional.ofNullable(getSettings().get(LOCALE));
    }

    public static void setLocale(String str) {
        getSettings().put(LOCALE, str);
    }

    //**************************************************
    //* THEME
    //**************************************************
    public static String getTheme() {
        return Optional.ofNullable(getSettings().get(THEME))
                .orElse(LIGHT);
    }

    public static void setTheme(String theme) {
        getSettings().put(THEME, theme);
        initTheme();

    }

    public static void initTheme() {
        if (UserSettingsService.LIGHT.equals(UserSettingsService.getTheme())) {
            Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());
        } else {
            Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
        }
    }

    //**************************************************
    //* DEMO
    //**************************************************
    public static String getSavedText() {
        return Optional.ofNullable(getSettings().get(DEMO))
                .orElse("");
    }

    public static void setSavedText(String str) {
        getSettings().put(DEMO, str);

    }
}