package Home.Account;

import Home.DataCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NewAccount {

    Connection conn = null;
    PreparedStatement ps = null;

    @FXML
    private String owner;

    @FXML
    private TextField websitetext;
    @FXML
    private TextField usernametext;
    @FXML
    private TextField passwordtext;
    @FXML
    private TextField emailtext;
    @FXML
    private TextField recovtext;
    @FXML
    private TextField recovanstext;


    @FXML
    private void addAcount(ActionEvent event) {
        String website = websitetext.getText();
        String username = usernametext.getText();
        String password = passwordtext.getText();
        String email = emailtext.getText();
        String recoverq = recovtext.getText();
        String recovans = recovanstext.getText();


        String sql = "INSERT INTO Accounts(Website_Account_Address, Username, EmailAddress, AccountPassword, RecoveryQuestion, RecoverAnswer, OwnerUser ) " +
                "VALUES (?,?,?,?,?,?,?); ";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, website);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, recovans);
            ps.setString(6, recoverq);
            ps.setString(7, owner);

            ps.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SafeHouse IO - System Alert");
            alert.setHeaderText("Account Detail saved, refresh the table in the dashboard to see detail");
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

    }
}
