package Home;

import Home.Dashboard.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;


    @FXML
    private Button Registerbtn;

    @FXML
    private void RegisterAct(ActionEvent event) throws Exception {

        Stage stage = new Stage();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/Home/User/Newuser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Loginact(ActionEvent event) {

        String user = username.getText();
        String pass = password.getText();

        String sql = "Select * from UserInfo where Username = '" + user + "' and Password = '" + pass + "' ";

        try {

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                Stage stage = new Stage();

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/Home/Dashboard/Dashboard.fxml"));
                Loader.load();
                Parent root;
                root = Loader.getRoot();
                DashboardController controller = Loader.getController();
                controller.setUser(user);

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SafeHouse IO - System Alert");
                alert.setHeaderText("Username or Password Incorrect \n  \nKindly Check Details and Retry");
                alert.setContentText("Click \"OK\" to Continue.");
                alert.showAndWait();
            }

        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {

            }
        }

    }

    public void initialize() {
        conn = DataCenter.DbConnector();
    }

}
