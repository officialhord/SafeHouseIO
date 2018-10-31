package Home.Dashboard;

import Home.Account.NewAccount;
import Home.Accounts;
import Home.Crypto.newCrypto;
import Home.DataCenter;
import Home.Wallet.newWallet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardController {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    ObservableList accounts = FXCollections.observableArrayList();

    @FXML
    private TableView<Accounts> Acctstable;
    @FXML
    private TableColumn<Accounts, String> AcctsAddress;
    @FXML
    private TableColumn<Accounts, String> AcctsUsername;
    @FXML
    private TableColumn<Accounts, String> AcctsPassword;
    @FXML
    private TableColumn<Accounts, String> AcctsEmail;

    @FXML
    private Button newact;
    @FXML
    private Button newwallet;
    @FXML
    private Button newcrypto;
    @FXML
    private Text accountname;
    @FXML
    private Text accountwebsite;
    @FXML
    private TextField accountpassword;
    @FXML
    private Text usernamel;
    @FXML
    private String owner;
    @FXML
    private Button wallets;
    @FXML
    private Button accountsbtn;
    @FXML
    private Button cryptosbtn;
    @FXML
    private Text totlacunt;
    @FXML
    private Button searchbtbn;
    @FXML
    private TextField searchfield;

    @FXML
    private void addAccount(ActionEvent event) throws Exception {
        Stage stage = new Stage();

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Home/Account/NewAccount.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();
        NewAccount controller = Loader.getController();
        controller.setUser(owner);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addWallet(ActionEvent event) throws Exception {

        Stage stage = new Stage();

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Home/Wallet/newWallet.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();
        newWallet controller = Loader.getController();
        controller.getUser(owner);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addCrypto(ActionEvent event) throws Exception {

        Stage stage = new Stage();

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Home/Crypto/newCrypto.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();
        newCrypto controller = Loader.getController();
        controller.setUser(owner);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void tableClick(MouseEvent event) throws Exception {
        if (event.getClickCount() == 1) {
            accountname.setText(Acctstable.getSelectionModel().getSelectedItem().getAcctname());
            accountpassword.setText(Acctstable.getSelectionModel().getSelectedItem().getAcctpassword());
            accountwebsite.setText(Acctstable.getSelectionModel().getSelectedItem().getWebsite());
        }
    }

    @FXML
    public void setUser(String username) {
        this.usernamel.setText("@" + username);
        owner = username;

    }

    private void UpdateAccountstable() {


        try {
            accounts.clear();
            String sql = "Select * from Accounts";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Accounts s = new Accounts(rs.getString("Username"), rs.getString("AccountPassword"),
                        rs.getString("EmailAddress"),
                        rs.getString("Website_Account_Address"));
                accounts.add(s);

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {

            }
        }
        AcctsAddress.setCellValueFactory(new PropertyValueFactory<>("website"));
        AcctsUsername.setCellValueFactory(new PropertyValueFactory<>("acctname"));
        AcctsPassword.setCellValueFactory(new PropertyValueFactory<>("acctpassword"));
        AcctsEmail.setCellValueFactory(new PropertyValueFactory<>("acctemail"));

        Acctstable.setItems(accounts);

    }

    private void UpdateWalletstable() {
        try {
            accounts.clear();
            String sql = "Select * from Wallets";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                accounts.add(new Accounts(rs.getString("WalletUsername"), rs.getString("WalletPassword"),
                        rs.getString("WalletEmail"),
                        rs.getString("WalletWebsite")));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {

            }
        }
        AcctsAddress.setCellValueFactory(new PropertyValueFactory<>("website"));
        AcctsUsername.setCellValueFactory(new PropertyValueFactory<>("acctname"));
        AcctsPassword.setCellValueFactory(new PropertyValueFactory<>("acctpassword"));
        AcctsEmail.setCellValueFactory(new PropertyValueFactory<>("acctemail"));

        Acctstable.setItems(accounts);
    }

    private void UpdateCryptos() {
        try {

            accounts.clear();
            String sql = "Select * from Crypto";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                accounts.add(new Accounts(rs.getString("Cryptousername"),
                        rs.getString("CryptoPassword"),
                        rs.getString("CryptoEmail"),
                        rs.getString("CryptoWebsite")));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {

            }
        }
        AcctsAddress.setCellValueFactory(new PropertyValueFactory<>("website"));
        AcctsUsername.setCellValueFactory(new PropertyValueFactory<>("acctname"));
        AcctsPassword.setCellValueFactory(new PropertyValueFactory<>("acctpassword"));
        AcctsEmail.setCellValueFactory(new PropertyValueFactory<>("acctemail"));

        Acctstable.setItems(accounts);

    }

    @FXML
    private void accesswallets() throws Exception {

        UpdateWalletstable();

    }

    @FXML
    private void accessaccounts() throws Exception {
        UpdateAccountstable();

    }

    @FXML
    private void accesscrypto() throws Exception {
        UpdateCryptos();

    }

    @FXML
    private void Searchact(ActionEvent event) {

        try {


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void initialize() throws Exception {

        conn = DataCenter.DbConnector();

    }
}
