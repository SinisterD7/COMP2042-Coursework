module comp2042 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.junit.jupiter.api;
    
    // If you're using JavaFX media features
    // requires javafx.media;
    
    // If you're using JavaFX web features
    // requires javafx.web;
    
    // Export your packages that need to be accessible to FXML
    // Replace with your actual package name
    exports com.example.yourapp;
    
    // If you're using FXML, you need to open your packages to javafx.fxml
    opens com.example.yourapp to javafx.fxml;
}
