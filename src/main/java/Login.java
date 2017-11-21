import java.sql.*;
import java.text.ParseException;

class Login {
    private String username;
    private String status;
    private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

    private String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Login(String  username, String  status){
        username=this.getUsername();
        status=this.getStatus();
    }
    Login(){

    }

    public int validation(String username,String status) throws SQLException, ParseException {
        int last_insert_id =0;
        try {
            Connection con = Connector.getConnection();
            String sql = "insert INTO test.login(users,log_time,status) values (?,?,?);";
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, username);
            pst.setTimestamp(2, date);
            pst.setString(3, status);
            pst.execute();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()){
                last_insert_id=rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println("SQL Statement error!!");
            e.printStackTrace();
        }
        return last_insert_id;
    }

    public void sqlLog (String stat,int id) throws SQLException {
        Connection con = Connector.getConnection();
        String sql = "UPDATE test.login SET test =? WHERE id=?;";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, stat);
        pst.setInt(2, id);
        pst.execute();

    }
}
