<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // SECURITY: Ensure only Employees can access
    String role = (String) session.getAttribute("userRole");
    if (session.getAttribute("userCode") == null || !"Employee".equalsIgnoreCase(role)) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apply for Leave</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary shadow-sm">
    <div class="container">
        <span class="navbar-brand">Leave Portal</span>
        <span class="text-white">Welcome, <%= session.getAttribute("userName") %></span>
    </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-white">
                    <h4 class="mb-0 text-center">New Leave Application</h4>
                </div>
                <div class="card-body">
                    <form action="../LeaveServlet" method="post">
                        <div class="mb-3">
                            <label class="form-label">Leave Type</label>
                            <select name="leaveType" class="form-select" required>
                                <option value="Sick Leave">Sick Leave</option>
                                <option value="Casual Leave">Casual Leave</option>
                                <option value="Vacation">Vacation</option>
                            </select>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Start Date</label>
                                <input type="date" name="startDate" class="form-control" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">End Date</label>
                                <input type="date" name="endDate" class="form-control" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Reason</label>
                            <textarea name="reason" class="form-control" rows="3" placeholder="Tell us why..." required></textarea>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Submit Application</button>
                            <a href="../index.jsp" class="btn btn-link text-muted mt-2">Logout</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<hr class="mt-5">

<div class="container">
    <h4 class="text-center">My Leave Requests</h4>

    <table class="table table-bordered mt-3">
        <thead class="table-dark">
            <tr>
                <th>Leave Type</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Reason</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>

<%
    try {
        java.sql.Connection con = mayuri.DBConnection.getConnection();
        String empCode = (String) session.getAttribute("userCode");

        String query = "SELECT * FROM leave_requests WHERE employeeCode=? ORDER BY id DESC";
        java.sql.PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, empCode);

        java.sql.ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            String status = rs.getString("status");
            String color = "secondary";

            if(status.equalsIgnoreCase("pending")) color = "warning";
            else if(status.equalsIgnoreCase("approved")) color = "success";
            else if(status.equalsIgnoreCase("rejected")) color = "danger";
%>

<tr>
    <td><%= rs.getString("leaveType") %></td>
    <td><%= rs.getString("startDate") %></td>
    <td><%= rs.getString("endDate") %></td>
    <td><%= rs.getString("reason") %></td>
   <td>
    <span class="badge 
        <%= rs.getString("status").equals("approved") ? "bg-success" : 
            rs.getString("status").equals("rejected") ? "bg-danger" : 
            "bg-warning text-dark" %>">
        <%= rs.getString("status") %>
    </span>
</td>
</tr>

<%
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
%>

        </tbody>
    </table>
</div>


</body>
</html>