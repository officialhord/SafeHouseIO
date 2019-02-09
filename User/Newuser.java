package Home.User;

import Home.Dashboard.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.java2d.Disposer;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Newuser {


    Connection conn = null;
    PreparedStatement pst = null;


    @FXML
    private Button bcktologin;

    @FXML
    private Button creat;

    @FXML
    private TextField accountname;

    @FXML
    private TextField accountusername;

    @FXML
    private TextField accountemail;

    @FXML
    private PasswordField accountpassword;

    @FXML
    private void creatact(ActionEvent evt) {
        try {

            String acctname, acctusername, acctpassword, acctemail;
            acctname = accountname.getText();
            acctusername = accountusername.getText();
            acctemail = accountemail.getText();
            acctpassword = accountpassword.getText();

            String sql = "Insert into UserInfo (Username, Password, Name, Email) Valuse(?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1,acctusername);
            pst.setString(1,acctpassword);
            pst.setString(1,acctname);
            pst.setString(1,acctemail);
            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SafeHouse IO - System Alert");
            alert.setHeaderText("User Account Created Successfully");
            alert.setContentText("Click \"OK\" to continue to Login");

            alert.showAndWait();
            System.exit(0);
            Stage stage = new Stage();
            ((Node)(evt.getSource())).getScene().getWindow().hide();

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/Home/Login.fxml"));
            Loader.load();
            Parent root;
            root = Loader.getRoot();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @FXML
    private void returntologin(ActionEvent evt) throws IOException {

        ((Node)(evt.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Home/Login.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
