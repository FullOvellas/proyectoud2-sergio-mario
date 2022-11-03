module com.sergiomario.countryapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens com.sergiomario.countryapi.controller to javafx.fxml;
    opens com.sergiomario.countryapi.model.country to com.fasterxml.jackson.databind;
    exports com.sergiomario.countryapi;
    exports com.sergiomario.countryapi.model.country;
    exports com.sergiomario.countryapi.dao;
    opens com.sergiomario.countryapi.dao to com.fasterxml.jackson.databind;
    opens com.sergiomario.countryapi to com.fasterxml.jackson.databind, javafx.fxml;

}