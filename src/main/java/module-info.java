module plantmonitor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;
    requires java.sql;

    exports no.ntnu.idata2304.group14.plantmonitor;
    exports no.ntnu.idata2304.group14.plantmonitor.ui;


    opens no.ntnu.idata2304.group14.plantmonitor.ui;
}
