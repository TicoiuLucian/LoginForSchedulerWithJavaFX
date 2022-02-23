package ro.itschool.scheduler.frontend;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ro.itschool.scheduler.SchedulerApplication;
import ro.itschool.scheduler.entity.MyUser;
import ro.itschool.scheduler.entity.Role;
import ro.itschool.scheduler.entity.RoleType;
import ro.itschool.scheduler.service.UserService;

import java.util.Set;


public class RegisterStage extends Stage {
    public RegisterStage(UserService userService) {

        Alert userNameAlert = new Alert(Alert.AlertType.ERROR);
        userNameAlert.setContentText("Username already exists");

        Alert emailAlert = new Alert(Alert.AlertType.ERROR);
        emailAlert.setContentText("Email already exists");

        Alert passwordAlert = new Alert(Alert.AlertType.ERROR);
        passwordAlert.setContentText("Password is too short");

        Label labelFullName = new Label("Full name:");
        TextField textFullName = new TextField();

        Label labelUserName = new Label("UserName:");
        TextField textUserName = new TextField();

        Label labelEmail = new Label("Email:");
        TextField textEmail = new TextField();

        Label labelPassword = new Label("Password:");
        PasswordField textPassword = new PasswordField();

        Button registerBtn = new Button("Register me");

        registerBtn.setOnAction(actionEvent -> {
            boolean valid = true;
            if (userService.findUserByUserName(textUserName.getText()) != null) {
                valid = false;
                userNameAlert.show();
            }
            if (userService.findUserByEmail(textEmail.getText()) != null) {
                valid = false;
                emailAlert.show();
            }
            if (!validate(textPassword.getText())) {
                valid = false;
                passwordAlert.show();
            }
            if (valid) {
                final MyUser myUser = new MyUser(null, textEmail.getText(), textPassword.getText(), textUserName.getText(),
                        textFullName.getText(), true, Set.of(new Role(null, "ROLE_" + RoleType.USER)));
                userService.saveUser(myUser);
                Platform.runLater(() -> new SchedulerApplication().start(new Stage()));
                this.close();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(labelFullName, 0, 1);
        gridPane.add(textFullName, 1, 1);
        gridPane.add(labelUserName, 0, 2);
        gridPane.add(textUserName, 1, 2);
        gridPane.add(labelEmail, 0, 3);
        gridPane.add(textEmail, 1, 3);
        gridPane.add(labelPassword, 0, 4);
        gridPane.add(textPassword, 1, 4);
        gridPane.add(registerBtn, 0, 5);

        this.setScene(new Scene(gridPane, 500, 500));
        this.show();
    }

    private boolean validate(String text) {
        return text.length() >= 6;
    }
}
