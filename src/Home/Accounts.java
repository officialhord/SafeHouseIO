package Home;

public class Accounts {

    String acctname;
    String acctpassword;
    String acctemail;
    String website;

    public Accounts(String acctname, String acctpassword, String acctemail, String website) {
        this.acctname = acctname;
        this.acctpassword = acctpassword;
        this.acctemail = acctemail;
        this.website = website;
    }

    public String getAcctname() {
        return acctname;
    }

    public void setAcctname(String acctname) {
        this.acctname = acctname;
    }

    public String getAcctpassword() {
        return acctpassword;
    }

    public void setAcctpassword(String acctpassword) {
        this.acctpassword = acctpassword;
    }

    public String getAcctemail() {
        return acctemail;
    }

    public void setAcctemail(String acctemail) {
        this.acctemail = acctemail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void clear(){
        this.acctname = "";
        this.acctpassword = "";
        this.acctemail = "";
        this.website = "";
    }
}
