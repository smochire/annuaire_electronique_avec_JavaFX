module com.example.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.projet to javafx.fxml;
    exports com.example.projet;
    exports Donnee;
    opens Donnee to javafx.fxml;
}