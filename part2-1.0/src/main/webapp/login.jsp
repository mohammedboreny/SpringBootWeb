<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title>Student Grading System</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="signInTab" data-toggle="tab" href="#signIn" role="tab" aria-controls="signIn" aria-selected="true">Sign In</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="signUpTab" data-toggle="tab" href="#signUp" role="tab" aria-controls="signUp" aria-selected="false">Sign Up</a>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <!-- Sign In Form -->
        <div class="tab-pane fade show active" id="signIn" role="tabpanel" aria-labelledby="signInTab">
            <h3 class="my-3">Sign in </h3>
            <form action="${pageContext.request.contextPath}/login.do" method="POST">
                <div class="form-group">
                    <label for="signInId">user name</label>
                    <input class="form-control" id="signInId" name="name" type="text" required>
                </div>
                <div class="form-group">
                    <label for="signInPassword">Password:</label>
                    <input class="form-control" id="signInPassword" name="password" type="password" required>
                </div>
                <div class=" text-center" >
                <button type="submit" class="btn btn-primary">Sign In</button>
                </div>>
            </form>
        </div>
        <!-- Sign Up Form -->
        <div class="tab-pane fade" id="signUp" role="tabpanel" aria-labelledby="signUpTab">
            <h3 class="my-3">Sign Up</h3>
            <form action="${pageContext.request.contextPath}/signup.do" method="POST">
                <div class="form-group">
                    <label for="signUpId">user name:</label>
                    <input class="form-control" id="signUpId" name="name" type="text" required>
                </div>
                <div class="form-group">
                    <label for="signUpPassword">Password:</label>
                    <input class="form-control" id="signUpPassword" name="password" type="password" required>
                </div>
                <div class="form-group">
                    <label for="userRole">User Role:</label>
                    <select class="form-control" id="userRole" name="role" required>
                        <option value="" disabled selected>Select User Role</option>
                        <option value="Admin">Admin</option>
                        <option value="Instructor">Instructor</option>
                        <option value="Student">Student</option>
                    </select>
                </div>
                <div class=" text-center" >
                    <button type="submit" class="btn btn-primary ">Sign Up</button>
                </div>
                <!-- Add other sign-up fields (e.g., email, role, etc.) as needed -->
            </form>
        </div>

    </div>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center" role="alert">
                ${errorMessage}
        </div>
    </c:if>
</div>

<!-- Include Bootstrap JS and jQuery (optional) if needed -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
