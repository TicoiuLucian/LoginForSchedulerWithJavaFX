package ro.itschool.scheduler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import ro.itschool.scheduler.frontend.HomeStage;
import ro.itschool.scheduler.frontend.RegisterStage;
import ro.itschool.scheduler.service.MessageService;
import ro.itschool.scheduler.service.UserService;

@SpringBootApplication
@EnableScheduling
public class SchedulerApplication extends Application {

    private static UserService userService;
    private static MessageService messageService;
    private static Text textAlert = new Text();


    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = SpringApplication.run(SchedulerApplication.class, args);
        userService = appContext.getBean(UserService.class);
        messageService = appContext.getBean(MessageService.class);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Hello World!");

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        TextField usernameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();

        Button loginBtn = new Button();

        //----------------LOGIN-------------------------
        loginBtn.setText("Login");
        loginBtn.setOnAction(x -> {
            if (userService.findUserByUserNameAndPassword(usernameTextField.getText(), passwordTextField.getText())) {
                //Ne mutam pe alta pagina
                primaryStage.close();
                new HomeStage(messageService, usernameTextField.getText())
                        .setTitle("Home page");
            } else
                textAlert.setStyle("-fx-font-weight: bold");
            textAlert.setStyle("-fx-font: 16 arial;");
            textAlert.setFill(Color.color(1, 0, 0));
            textAlert.setText("Username and/or password not valid");
        });

        //-------------------------------------------------

        //---------------------Register--------------------
        Button registerBtn = new Button();
        registerBtn.setText("Register");

        registerBtn.setOnAction(x ->

        {
            new RegisterStage(userService)
                    .setTitle("register page");
            primaryStage.close();
        });
        //--------------------------------

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(usernameTextField, 1, 0);
        gridPane.add(passwordTextField, 1, 1);
        gridPane.add(loginBtn, 0, 2);
        gridPane.add(registerBtn, 1, 2);
        gridPane.add(textAlert, 0, 3, 3, 1);

        primaryStage.setScene(new

                Scene(gridPane, 300, 250));
        primaryStage.show();
    }

}
