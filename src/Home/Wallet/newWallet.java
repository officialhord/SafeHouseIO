package Home.Wallet;

import Home.DataCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class newWallet {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    private TextField websitetext;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField usernametext;

    @FXML
    private TextField passwordtext;

    @FXML
    private TextField walletaddresstext;

    @FXML
    private Button submit;

    @FXML
    private String ownername = "";

    @FXML
    private void Submit(ActionEvent event){

        String username = usernametext.getText();
        String password = passwordtext.getText();
        String website = websitetext.getText();
        String walletaddress = walletaddresstext.getText();
        String email = emailtext.getText();

        String sql = "INSERT INTO Wallets( OwnerUser, WalletUsername, WalletEmail, WalletWebsite, WalletPassword, WalletAddress)" +
                " VALUES (?,?,?,?,?,?); ";

        try{

            pst = conn.prepareStatement(sql);
            pst.setString(1, ownername);
            pst.setString(2, username);
            pst.setString(3, email);
            pst.setString(4, website);
            pst.setString(5, password);
            pst.setString(6, walletaddress);
            pst.execute();


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SafeHouse IO - System Alert");
            alert.setHeaderText("Wallet Information Saved - Kindly refresh wallets on your dashboard to view new information");
            alert.setContentText("Click \"OK\" to Continue.");
            alert.showAndWait();

        }catch(Exception e){
            e.printStackTrace();

        }finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
        }


    }

    @FXML
    public void getUser(String user){
        this.ownername = user;
    }


    public void initialize(){
        conn = DataCenter.DbConnector();

    }
}
