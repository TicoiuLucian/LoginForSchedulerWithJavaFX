package ro.itschool.scheduler.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ro.itschool.scheduler.entity.Message;
import ro.itschool.scheduler.service.MessageService;

public class HomeStage extends Stage {

    private static Text textAlert = new Text();

    public HomeStage(MessageService messageService, String userName) {
        ObservableList<Message> list = FXCollections.observableArrayList(messageService.getAllMessagesForUsername(userName));
        ListView<Message> listView = new ListView<>(list);
        listView.setCellFactory(param -> new XCell(messageService));
        listView.setPrefHeight(150);
        listView.setPrefWidth(300);

        GridPane gridPane = new GridPane();
        gridPane.add(listView, 0, 0);
        gridPane.add(textAlert, 0, 1);
        this.setScene(new Scene(gridPane, 500, 500));
        this.show();
    }
}
