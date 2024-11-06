module ca.centennialcollege.carlnicolasmendoza_comp228lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.centennialcollege.carlnicolasmendoza_comp228lab4 to javafx.fxml;
    exports ca.centennialcollege.carlnicolasmendoza_comp228lab4;
}