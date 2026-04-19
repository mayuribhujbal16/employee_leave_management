package mayuri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean validateUser(String code, String pass) {
        boolean isFound = false;

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM employeeinfo WHERE eEmployeeCodeNumber=? AND ePassword=? AND eEmployeeVerification=1";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isFound = true;

                // Update last login
                String updateQuery = "UPDATE employeeinfo SET eLastLogin = NOW() WHERE eEmployeeCodeNumber=?";
                PreparedStatement ps2 = con.prepareStatement(updateQuery);
                ps2.setString(1, code);
                ps2.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFound;
    }
}