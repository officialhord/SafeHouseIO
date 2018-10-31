package Home.Crypto;

import Home.DataCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class newCrypto {

    Connection conn = null;
    PreparedStatement ps = null;
    ObservableList cointype = FXCollections.observableArrayList("Token", "Coin");

    @FXML
    private String owner;

    @FXML
    private TextField Crptosite;
    @FXML
    private TextField CrptoName;
    @FXML
    private TextField CryptoUsername;
    @FXML
    private TextField CryptoPassword;
    @FXML
    private TextField CryptoEmail;
    @FXML
    private TextField Cryptowallet;
    @FXML
    private TextField CryptoAbbreviation;

    @FXML
    private ComboBox Cryptotype;

    @FXML
    private Button saveCrypto;

    @FXML
    private void addAcount(ActionEvent event) {
        String Cryptsite = Crptosite.getText();
        String Cryptusername = CryptoUsername.getText();
        String Cryptpassword = CryptoPassword.getText();
        String Cryptemail = CryptoEmail.getText();
        String Cryptwallet = Cryptowallet.getText();
        String Crypttype = Cryptotype.getValue().toString();
        String Cryptabre = CryptoAbbreviation.getText();
        String Cryptname = CrptoName.getText();


        String sql = "INSERT INTO Crypto( OwnerUser ) " +
                "VALUES (?,?,?,?,?,?,?,?,?); ";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, Cryptsite);
            ps.setString(2, Cryptusername);
            ps.setString(3, Cryptpassword);
            ps.setString(4, Cryptemail);
            ps.setString(5, Cryptwallet);
            ps.setString(6, Crypttype);

            ps.setString(7, owner);
            ps.setString(8, Cryptabre);
            ps.setString(9, Cryptname);
            ps.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SafeHouse IO - System Alert");
            alert.setHeaderText("Coin/Token Detail saved, refresh the table in the dashboard to see detail");
            alert.setContentText("Click \"OK\" to Continue.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
        }

    }


    @FXML
    public void setUser(String user) {

        owner = user;

    }


    public void initialize() {
        conn = DataCenter.DbConnector();
        Cryptotype.setItems(cointype);
    }
}
