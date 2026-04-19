<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<title>Online Leave Management</title>

<!-- CSS -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">

</head>

<body>

<div class="container" style="margin-top: 100px; max-width: 400px;">

    <h2 style="text-align:center;">Login</h2>

    <!-- 🔴 ERROR MESSAGE -->
    <%
        Integer error = (Integer) session.getAttribute("LoginError");
        if (error != null && error == 1) {
    %>
        <p style="color:red; text-align:center;">
            Invalid Code or Password!
        </p>
    <%
            session.removeAttribute("LoginError");
        }
    %>

    <!-- LOGIN FORM -->
    <form action="LoginServlet" method="post">

        <div class="form-group">
            <label>Office Code</label>
            <input type="text" name="officeUserCode" class="form-control" placeholder="Enter Code" required>
        </div>

        <div class="form-group" style="margin-top:10px;">
            <label>Password</label>
            <input type="password" name="officeUserPass" class="form-control" placeholder="Enter Password" required>
        </div>

        <div style="margin-top:15px;">
            <button type="submit" class="btn btn-primary" style="width:100%;">
                Login
            </button>
        </div>

    </form>

</div>

</body>
</html>