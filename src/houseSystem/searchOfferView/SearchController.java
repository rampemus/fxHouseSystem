package houseSystem.searchOfferView;

import houseSystem.Kuntoluokka;
import houseSystem.MainController;
import houseSystem.addOfferView.Asunto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.util.List;

public class SearchController extends MainController {

    ObservableList<Asunto> data;

    @FXML
    private TableView<Asunto> searchResultList;

    @FXML
    private HBox searchBarBox;

    @FXML
    private TextField searchBar;

    @FXML
    private Button download;

    @FXML
    private ProgressBar progress;

    @FXML
    private ToggleButton priceToggle;

    @FXML
    private TextField minPrice;

    @FXML
    private Label priceSepLabel;

    @FXML
    private TextField maxPrice;

    @FXML
    private ToggleButton areaToggle;

    @FXML
    private TextField minArea;

    @FXML
    private Label areaSepLabel;

    @FXML
    private TextField maxArea;

    @FXML
    private ComboBox<Kuntoluokka> classToggle;

    @FXML
    private ToggleButton builtYearToggle;

    @FXML
    private TextField minBuiltYear;

    @FXML
    private Label builtYearSepLabel;

    @FXML
    private TextField maxBuiltYear;

    @FXML
    public void initialize() {
        classToggle.getItems().setAll(Kuntoluokka.values());

        AsuntoGeneraattori.kirjaaAsiakas(this);

        updateDataBasePreview();
    }

    /**
     * Updates the whole table and formats data from asuntogeneraattori
     * for fitting in and adds new search filters and listeners
     * for their controls
     */
    @FXML
    public void updateDataBasePreview() {

        List<Asunto> asunnot = AsuntoGeneraattori.annaAsunnot();
        data = FXCollections.observableArrayList(asunnot);

        //get columns from pre-created table
        TableColumn nameCol = searchResultList.getColumns().get(0);
        TableColumn priceCol = searchResultList.getColumns().get(1);
        TableColumn areaCol = searchResultList.getColumns().get(2);
        TableColumn builtYearCol = searchResultList.getColumns().get(3);
        TableColumn classCol = searchResultList.getColumns().get(4);
//        TableColumn additionalInfoCol = searchResultList.getColumns().get(5);

        //mark propertyvalues in asunto-objects
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Asunto,String>("nimi")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<Asunto,Integer>("vuokra")
        );
        areaCol.setCellValueFactory(
                new PropertyValueFactory<Asunto,Float>("neliömäärä")
        );
        builtYearCol.setCellValueFactory(
                new PropertyValueFactory<Asunto,Integer>("rakennusvuosi")
        );
        classCol.setCellValueFactory(
                new PropertyValueFactory<Asunto,String>("kuntoluokka")
        );
//        additionalInfoCol.setCellValueFactory(
//                new PropertyValueFactory<Asunto,String>("kohteenKuvaus")
//        );
        initSearchFilterListeners();

    }

    /**
     * Adds listeners for filter controls and inits the searchResultList
     */
    @FXML
    public void initSearchFilterListeners() {

        FilteredList<Asunto> filteredData = new FilteredList<>(data,a -> true);

        //searchbar changes
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        //Price filter changes
        priceToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        minPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        maxPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        //Condition "Kuntoluokka" changes
        classToggle.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        //Area filter changes
        areaToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        minArea.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        maxArea.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        //Year filter changes
        builtYearToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        minBuiltYear.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        maxBuiltYear.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( asunto -> searchResultFilter(asunto));
        });

        SortedList<Asunto> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(searchResultList.comparatorProperty());

        filteredData.setPredicate( asunto -> searchResultFilter(asunto) );

        searchResultList.setItems(sortedData);
    }

    //setting toggles to handle all related elements and if they are disabled

    @FXML
    public void togglePriceFilter() {
        boolean disabled = !priceToggle.isSelected();
        minPrice.setDisable(disabled);
        priceSepLabel.setDisable(disabled);
        maxPrice.setDisable(disabled);
    }

    @FXML
    public void toggleAreaFilter() {
        boolean disabled = !areaToggle.isSelected();
        minArea.setDisable(disabled);
        areaSepLabel.setDisable(disabled);
        maxArea.setDisable(disabled);
    }

    @FXML
    public void toggleYearFilter() {
        boolean disabled = !builtYearToggle.isSelected();
        minBuiltYear.setDisable(disabled);
        builtYearSepLabel.setDisable(disabled);
        maxBuiltYear.setDisable(disabled);
    }

    /**
     * Goes through data of one asunto and puts it through all the filters
     * @param asunto
     * @return true, if asunto passes all the filters
     */
    private boolean searchResultFilter(Asunto asunto) {
        //todo: cleanup this one, I'm sorry you are seeing this
        if ( searchBar.getText() == null || searchBar.getText().isEmpty() || asunto.getNimi().toLowerCase().contains(searchBar.getText().toLowerCase())) {
            try {
                if (!priceToggle.isSelected() ||
                        (asunto.getVuokra() <= Integer.parseInt(maxPrice.getText()) && (asunto.getVuokra() >= Integer.parseInt(minPrice.getText())))) {
                    if (classToggle.getValue() == null || classToggle.getValue().equals(Kuntoluokka.EI_KUNTOLUOKKAA) ||
                            asunto.getKuntoluokkaEnum().equals(classToggle.getValue())) {
                        if (!areaToggle.isSelected() ||
                                (asunto.getNeliömäärä() <= Integer.parseInt(maxArea.getText()) && (asunto.getNeliömäärä() >= Integer.parseInt(minArea.getText())))) {
                            if (!builtYearToggle.isSelected() ||
                                    (asunto.getRakennusvuosi() <= Integer.parseInt(maxBuiltYear.getText()) && (asunto.getRakennusvuosi() >= Integer.parseInt(minBuiltYear.getText()))))
                                return true;
                        }
                    }
                }
            } catch ( NumberFormatException e ) {
                return false;
            }
        }
        return false;
    }

    @FXML
    private void startDownload() {
        AsuntoGeneraattori.poistaKaikkiAsunnot();
        AsuntoGeneraattori.luoAsuntoja(200);

        download.toFront();
        download.setVisible(false);
        progress.setVisible(true);
        progress.setProgress(0);

    }

    /**
     * AsuntoGeneraattori-thread calls this when ever it creates a new aparment.
     * This will update progressbar
     */
    public void ilmoitaDataMuutoksesta() {
        updateDataBasePreview();
        if ( AsuntoGeneraattori.tehtyjäAsuntoja() < AsuntoGeneraattori.tehtäviäAsuntoja()) {
            double done = (double) AsuntoGeneraattori.tehtyjäAsuntoja();
            double tasks = (double) AsuntoGeneraattori.tehtäviäAsuntoja();
            progress.setProgress(done / tasks);
        } else {
            progress.setProgress(1);
            //Let javafx-thread finish this:
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    download.setVisible(true);
                    progress.toFront();
                    progress.setVisible(false);
                    updateDataBasePreview();
                }
            });
        }
    }
}
