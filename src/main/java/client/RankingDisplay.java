package client;

import java.io.IOException;
import java.util.Comparator;

import Models.Ranking;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server_connection.Api;

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
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));
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
        ObservableList<Ranking> rankData = getServerRanks();
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

    private ObservableList<Ranking> getServerRanks() {
        Api serverConnection = new Api();
        ObservableList<Ranking> ranks = null;
        try {
            ranks = FXCollections.observableArrayList(serverConnection.getRanks());

        } catch (IOException e) {
            System.out.println("Could not fetch data from the server.");
            e.printStackTrace();
        }

        return ranks != null ? ranks : FXCollections.observableArrayList();
    }

    @FXML
    private void goBack() {
        App.getInstance().loadView("puzzle.fxml");
    }
}
