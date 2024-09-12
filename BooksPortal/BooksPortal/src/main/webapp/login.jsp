<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>

<div class="container col-xl-10 col-xxl-8 px-4 py-5">
    <div class="row align-items-center g-lg-5 py-5">
        <div class="col-lg-7 text-center text-lg-start">
            <h1 class="display-4 fw-bold lh-1 mb-3">AWD Library Information System</h1>
            <p class="col-lg-10 fs-4">To get more from our library system kindly login, if you have not signed up yet, kindly do so to be able to access our resources for free.</p>
        </div>
        <div class="col-md-10 mx-auto col-lg-5">
            <form class="p-4 p-md-5 border rounded-3 bg-light" action="loginServlet" method="post">
                <div class="form-floating mb-3">
                    <p class="text-danger">${message}</p>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" name="email" class="form-control" id="floatingInput" value="${user.email}">
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" name="rememberme" value="remember-me"> Remember me
                    </label>
                </div>
                <input type="hidden" name="action" value="login">
                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
                <hr class="my-4">
                <small class="text-muted">By clicking Sign up, you agree to the terms of use.</small>
            </form>
        </div>
    </div>
</div>

</body>
</html>
