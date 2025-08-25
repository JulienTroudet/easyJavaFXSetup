package myapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import myapp.App;
import myapp.services.LocaleService;
import myapp.services.UserSettingsService;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane mainPane;
    public TextArea textArea;
    public ChoiceBox<Locale> localChoiceBox;
    public Stage stage;



    public static void load(Stage stage) {
        try {
            ResourceBundle resourceBundle =
                    ResourceBundle.getBundle("App", LocaleService.getLocale());
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainController.class.getResource("main.fxml"),
                    resourceBundle);
            Parent node = fxmlLoader.load();
            MainController controller = fxmlLoader.getController();
            controller.stage = stage;
            controller.stage.setScene(new Scene(node));

        } catch (IOException e) {
            Logger.error(" caught a {}\n with message: {}", e.getClass(), e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText(UserSettingsService.getSavedText());
        textArea.textProperty().addListener((e,o,n)-> UserSettingsService.setSavedText(n));

        localChoiceBox.getItems().add(Locale.ENGLISH);
        localChoiceBox.getItems().add(Locale.FRENCH);
        localChoiceBox.setValue(LocaleService.getLocale());
        localChoiceBox.valueProperty().addListener((e,o,n)-> {
            LocaleService.setLocale(n);
            load(stage);
            App.updateAppWindowTitle(stage);
        });
    }

    public void toDark() {
        UserSettingsService.setTheme(UserSettingsService.DARK);
    }

    public void toLight() {
        UserSettingsService.setTheme(UserSettingsService.LIGHT);
    }


}
