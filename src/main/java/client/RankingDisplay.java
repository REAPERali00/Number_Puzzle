package client;

import java.util.Comparator;

import Models.Ranking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RankingDisplay {
    @FXML
    private TableView<Ranking> rankTableView;

    @FXML
    private TableColumn<Ranking, Integer> rankColumn;

    @FXML
    private TableColumn<Ranking, String> usernameColumn;

    @FXML
    private TableColumn<Ranking, String> timeColumn;

    @FXML
    public void initialize() {
        // Initialize your table columns here
        // rankColumn.setCellValueFactory(cellData ->
        // cellData.getValue().rankProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        rankColumn.setCellFactory(column -> new TableCell<Ranking, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null) {
                    setText(null);
                } else {
                    setText(Integer.toString(getIndex() + 1)); // Dynamic rank based on the sorted position
                }
            }
        });
        ObservableList<Ranking> rankData = getRankData();
        rankData.sort(Comparator.comparing(Ranking::getTime)); // Sort by time
        rankTableView.setItems(rankData);

    }

    private ObservableList<Ranking> getRankData() {
        // This method should return an ObservableList<Ranking> containing your data
        ObservableList<Ranking> data = FXCollections.observableArrayList();

        data.add(new Ranking("User2", "00:02:10"));
        data.add(new Ranking("User4", "00:01:00"));
        // Add more data as needed
        return data;
    }
}
