/*module com.github.FranMarin123 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens com.github.FranMarin123 to javafx.fxml;
    opens com.github.FranMarin123.model.connection to java.xml.bind;
    exports com.github.FranMarin123;
    exports com.github.FranMarin123.view;
    opens com.github.FranMarin123.view to javafx.fxml;
    //opens com.github.FranMarin123.view to javafx.fxml;
}*/

module com.github.FranMarin123 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens com.github.FranMarin123 to javafx.fxml;
    opens com.github.FranMarin123.model.connection to java.xml.bind;
    exports com.github.FranMarin123;
    exports com.github.FranMarin123.view;
    opens com.github.FranMarin123.view to javafx.fxml;
    exports com.github.FranMarin123.controller.student;
    opens com.github.FranMarin123.controller.student to javafx.fxml;
    exports com.github.FranMarin123.controller.subject;
    opens com.github.FranMarin123.controller.subject to javafx.fxml;
    exports com.github.FranMarin123.controller;
    opens com.github.FranMarin123.controller to javafx.fxml;
    exports com.github.FranMarin123.controller.activity;
    opens com.github.FranMarin123.controller.activity to javafx.fxml;
    exports com.github.FranMarin123.controller.teacher;
    opens com.github.FranMarin123.controller.teacher to javafx.fxml;
}
