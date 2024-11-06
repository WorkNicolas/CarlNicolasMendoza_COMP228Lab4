package ca.centennialcollege.carlnicolasmendoza_comp228lab4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("form-view.fxml"));

        // Labels and TextField
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label addressLabel = new Label("Address:");
        TextField addressTextField = new TextField();
        Label provinceLabel = new Label("Province:");
        TextField provinceTextField = new TextField();
        Label cityLabel = new Label("City:");
        TextField cityTextField = new TextField();
        Label postalCodeLabel = new Label("Postal Code:");
        TextField postalCodeTextField = new TextField();
        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberTextField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();

        // Checkboxes
        CheckBox studentCouncilCheckBox = new CheckBox("Student Council");
        CheckBox volunteerWorkCheckbox = new CheckBox("Volunteer Work");

        // Radio Buttons
        RadioButton computerScienceRadioButton = new RadioButton("Computer Science");
        RadioButton businessRadioButton = new RadioButton("Business");
        ToggleGroup courseToggleGroup = new ToggleGroup();
        computerScienceRadioButton.setToggleGroup(courseToggleGroup);
        businessRadioButton.setToggleGroup(courseToggleGroup);
        computerScienceRadioButton.setSelected(true); // default radio button

        // skillsComboBox
        ComboBox<String> skillsComboBox = new ComboBox<>();
        skillsComboBox.getItems().addAll(
                "Java",
                "Python",
                "C#"
        );
        skillsComboBox.setPrefWidth(150);

        // skillsListView
        ListView<String> skillsListView = new ListView<>();
        skillsListView.setPrefWidth(60);
        skillsListView.setPrefHeight(170);
        ObservableList<String> observableSkillsListView = FXCollections.observableArrayList();
        skillsListView.setItems(observableSkillsListView);

        // leftGridPane
        GridPane leftGridPane = new GridPane(10, 10);
        leftGridPane.setPadding(new javafx.geometry.Insets(10));

        // displayButton
        Button displayButton = new Button("Display");

        // displayListView
        ListView<String> displayListView = new ListView<>();
        displayListView.setMinWidth(1000);
        displayListView.setMinHeight(500);
        ObservableList<String> observableDisplayListView = FXCollections.observableArrayList();
        displayListView.setItems(observableDisplayListView);
        ScrollPane displayScrollPane = new ScrollPane();
        displayScrollPane.setContent(displayListView);
        displayScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // leftGridPane Positioning
        leftGridPane.add(nameLabel, 0, 0);
        leftGridPane.add(nameTextField, 1, 0);
        leftGridPane.add(addressLabel, 0, 1);
        leftGridPane.add(addressTextField, 1, 1);
        leftGridPane.add(studentCouncilCheckBox, 2, 1);
        leftGridPane.add(provinceLabel, 0, 2);
        leftGridPane.add(provinceTextField, 1, 2);
        leftGridPane.add(cityLabel, 0, 3);
        leftGridPane.add(cityTextField, 1, 3);
        leftGridPane.add(postalCodeLabel, 0, 4);
        leftGridPane.add(postalCodeTextField, 1, 4);
        leftGridPane.add(phoneNumberLabel, 0, 5);
        leftGridPane.add(phoneNumberTextField, 1, 5);
        leftGridPane.add(volunteerWorkCheckbox, 2, 5);
        leftGridPane.add(emailLabel, 0, 6);
        leftGridPane.add(emailTextField, 1, 6);

        // rightGridPane
        GridPane rightGridPane = new GridPane(10, 10);
        rightGridPane.setPadding(new javafx.geometry.Insets(10));

        // rightGridPane Positioning
        rightGridPane.add(computerScienceRadioButton, 0, 0);
        rightGridPane.add(businessRadioButton, 1, 0);
        rightGridPane.add(skillsComboBox, 0, 1);
        rightGridPane.add(skillsListView, 0, 2);

        // flowPane
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(leftGridPane);
        flowPane.getChildren().add(rightGridPane);

        // vBox
        VBox vbox = new VBox(10);

        // vBox Positioning
        vbox.getChildren().add(displayButton);
        vbox.getChildren().add(displayScrollPane);
        vbox.setAlignment(Pos.CENTER);

        // borderPane
        BorderPane borderPane = new BorderPane();

        // borderPane Positioning
        borderPane.setTop(flowPane);
        borderPane.setBottom(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);

        // ActionEvents
        skillsComboBox.setOnAction(e -> {
            String selectedItem = skillsComboBox.getSelectionModel().getSelectedItem();
            if (!observableSkillsListView.contains(selectedItem)) {
                observableSkillsListView.add(selectedItem);
            }
        });

        displayButton.setOnAction(e -> {
            // Get Values from TextFields
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String province = provinceTextField.getText();
            String city = cityTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();
            String email = emailTextField.getText();

            // Get Values from Radio Buttons
            String courseSelected = ((RadioButton) courseToggleGroup.getSelectedToggle()).getText();

            // Get Values from CheckBoxes
            boolean isStudentCouncil = studentCouncilCheckBox.isSelected();
            boolean isVolunteerWork = volunteerWorkCheckbox.isSelected();

            // Get Values from ListView
            List<String> skillList = new ArrayList<>(skillsListView.getItems());

            // Build Profile
            String profile = name + ", " +
                    address + ", " +
                    city + ", " +
                    province + ", " +
                    city + ", " +
                    postalCode + ", " +
                    phoneNumber + ", " +
                    email;

            // Clear ComboBox
            skillsComboBox.getSelectionModel().clearSelection();

            // Clear All ListViews
            observableSkillsListView.clear();
            observableDisplayListView.clear();

            // Final Display
            if (!name.isEmpty() || !address.isEmpty() || !city.isEmpty() || !province.isEmpty() || !postalCode.isEmpty() || !phoneNumber.isEmpty() || !email.isEmpty()) {
                observableDisplayListView.add(profile);
                observableDisplayListView.add("Courses:");
                observableDisplayListView.addAll(skillList);
                observableDisplayListView.add("Student Council: " + (isStudentCouncil ? "Yes" : "No"));
                observableDisplayListView.add("Volunteer Work: " + (isVolunteerWork ? "Yes" : "No"));
                observableDisplayListView.add("Degree in " + ((RadioButton) courseToggleGroup.getSelectedToggle()).getText());

                // Clear All TextFields
                nameTextField.clear();
                addressTextField.clear();
                provinceTextField.clear();
                cityTextField.clear();
                postalCodeTextField.clear();
                phoneNumberTextField.clear();
                emailTextField.clear();

                // Default Radio Button
                computerScienceRadioButton.setSelected(true);

                // Clear All Checkboxes
                studentCouncilCheckBox.setSelected(false);
                volunteerWorkCheckbox.setSelected(false);
            }
        });

        // Scene
        int w = 800;
        int h = 400;
        Scene scene = new Scene(borderPane, w, h);
        stage.setMinWidth(w);
        stage.setMinHeight(h);
        stage.setScene(scene);
        stage.setTitle("Course Form");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}