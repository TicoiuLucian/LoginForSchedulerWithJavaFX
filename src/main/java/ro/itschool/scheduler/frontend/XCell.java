package ro.itschool.scheduler.frontend;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import ro.itschool.scheduler.entity.Message;
import ro.itschool.scheduler.service.MessageService;


public class XCell extends ListCell<Message> {
    private final Text textAlert = new Text();
    HBox hbox = new HBox();
    Label label = new Label("");
    Pane pane = new Pane();
    Button button = new Button("Delete");

    public XCell(MessageService messageService) {
        super();

        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> {
            try {
                messageService.deleteMessage(getItem());
                textAlert.setText(getItem() + " was removed");
                getListView().getItems().remove(getItem());
            } catch (Exception e) {
                textAlert.setText("Cannot delete item");
            }
        });
    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item != null && !empty) {
            label.setText(item.getSender() + " " + item.getContent());
            setGraphic(hbox);
        }
    }
}

