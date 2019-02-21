package houseSystem.addOfferView;

import houseSystem.TextFieldFilter;
import houseSystem.Kuntoluokka;
import houseSystem.MainController;
import houseSystem.searchOfferView.AsuntoGeneraattori;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.util.Random;

public class AddOfferController extends MainController {

    //todo: image chooser
    private FileChooser chooser;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField rentField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField builtYearField;

    @FXML
    private ChoiceBox<Kuntoluokka> classField;

    @FXML
    private TextField emailField;

    @FXML
    private TextArea addInfoField;

    @FXML
    private TextField conditionField;


    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        classField.getItems().setAll(Kuntoluokka.values());
        classField.setValue(Kuntoluokka.EI_KUNTOLUOKKAA);

        TextFieldFilter.setIntegerFilter(rentField);
        TextFieldFilter.setIntegerFilter(builtYearField);
        TextFieldFilter.setEmailFilter(emailField);
        TextFieldFilter.setAddressFilter(addressField);
        TextFieldFilter.setFloatNumberFilter(areaField);

    }

    @FXML
    public void submit() {
        Asunto a = new Asunto();
        a.setKuvaTiedosto("house" + (1+new Random().nextInt(6)) + ".png");

        a.setNimi(nameField.getText());
        a.setOsoite(addressField.getText());
        a.setVuokra(Integer.parseInt(rentField.getText()));
        a.setNeliömäärä(Float.parseFloat(areaField.getText()));
        a.setRakennusvuosi(Integer.parseInt(builtYearField.getText()));
        a.setKuntoluokka(classField.getValue());
        a.setSähköposti(emailField.getText());
        a.setKohteenKuvaus(addInfoField.getText().split("\n"));
        a.setMuutEhdot(conditionField.getText());

        AsuntoGeneraattori.lisaaAsunto(a);
        System.out.println("Käsin luotu: "+a);
    }

}