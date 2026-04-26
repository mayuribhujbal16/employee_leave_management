package mayuri;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public List<LeaveRequestDTO> getAllLeaves() {
        List<LeaveRequestDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM leave_requests ORDER BY appliedOn DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new LeaveRequestDTO(
                    rs.getInt("id"),
                    rs.getString("empCode"),
                    rs.getString("leaveType"),
                    rs.getString("startDate"),
                    rs.getString("endDate"),
                    rs.getString("reason"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}