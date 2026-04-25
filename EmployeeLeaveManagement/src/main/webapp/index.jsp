<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Leave Management - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .login-container { margin-top: 100px; max-width: 400px; }
    </style>
</head>
<body>

<div class="container login-container">
    <div class="card shadow">
        <div class="card-body">
            <h3 class="text-center mb-4">Employee Login</h3>

            <%
                if (session.getAttribute("LoginError") != null) {
            %>
                <div class="alert alert-danger text-center" role="alert">
                    Invalid Credentials! Please try again.
                </div>
            <%
                    session.removeAttribute("LoginError");
                }
            %>

            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Office Code</label>
                    <input type="text" name="officeUserCode" class="form-control" placeholder="e.g. EMP001" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="officeUserPass" class="form-control" placeholder="Enter Password" required>
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>