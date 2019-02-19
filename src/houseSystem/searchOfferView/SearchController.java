package houseSystem.searchOfferView;

import houseSystem.Kuntoluokka;
import houseSystem.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class SearchController extends MainController {

    @FXML
    private TableView<?> searchResultList;

    @FXML
    private TextField searchBar;

    @FXML
    private ToggleButton priceToggle;

    @FXML
    private TextField minPrice;

    @FXML
    private TextField maxPrice;

    @FXML
    private ToggleButton areaToggle;

    @FXML
    private TextField minArea;

    @FXML
    private TextField maxArea;

    @FXML
    private ComboBox<Kuntoluokka> classToggle;

    @FXML
    private ToggleButton builtYearToggle;

    @FXML
    private TextField minBuiltYear;

    @FXML
    private TextField maxBuiltYear;

    @FXML
    public void initialize() {
        classToggle.getItems().setAll(Kuntoluokka.values());
    }
}
