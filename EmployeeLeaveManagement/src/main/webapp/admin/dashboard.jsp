<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, mayuri.AdminDAO, mayuri.LeaveRequestDTO" %>
<%
    // SECURITY: If not an Admin, kick them back to login
    String role = (String) session.getAttribute("userRole");
    if (session.getAttribute("userCode") == null || !"Admin".equalsIgnoreCase(role)) {
        response.sendRedirect("../index.jsp");
        return; 
    }

    // DATA: Get the list from your DAO
    AdminDAO dao = new AdminDAO();
    List<LeaveRequestDTO> leaveList = dao.getAllLeaves();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-dark text-white">
            <h4 class="mb-0">Employee Leave Requests</h4>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Code</th>
                        <th>Type</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(LeaveRequestDTO leave : leaveList) { %>
                    <tr>
                        <td><%= leave.getEmpCode() %></td>
                        <td><%= leave.getLeaveType() %></td>
                        <td><%= leave.getStartDate() %></td>
                        <td><%= leave.getEndDate() %></td>
                        <td>
                            <span class="badge <%= leave.getStatus().equals("Pending") ? "bg-warning" : "bg-success" %>">
                                <%= leave.getStatus() %>
                            </span>
                        </td>
                        <td>
                            <% if(leave.getStatus().equals("Pending")) { %>
                                <a href="../ApprovalServlet?id=<%= leave.getId() %>&status=Approved" class="btn btn-sm btn-success">Approve</a>
                                <a href="../ApprovalServlet?id=<%= leave.getId() %>&status=Rejected" class="btn btn-sm btn-danger">Reject</a>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>