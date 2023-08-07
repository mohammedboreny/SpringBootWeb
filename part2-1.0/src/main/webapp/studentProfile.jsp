<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title> Student Grading System</title>

    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <p class="navbar-brand"> Welcome ${User} in Student Grading System</p>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form class="form-inline" action="/student.do" method="GET">
                    <input type="hidden" name="action" value="scores">
                    <input type="hidden" name="idValue" value="${sessionScope.ID}">
                    <button class="btn btn-link" type="submit" name="button" value="scores">Show My Grades</button>
                </form>
            </li>
            <li>
                <form class="form-inline" action="/scores.jsp" method="GET">
                    <button class="btn btn-link" type="submit" name="button" value="stat">Statistics</button>
                </form>
            </li>
            <li>
                <form class="form-inline" action="/student.do" method="POST">
                    <input type="hidden" name="action" value="logout">
                    <button class="btn btn-link" type="submit" name="button" value="logout">Logout</button>
                </form>
            </li>
        </ul>

    </div>
</nav>
<div class="container mt-5">
    <h4>Welcome ${User}</h4>
    <br>
    <p><strong>Student Grades:</strong></p>
    <ul>
        <c:forEach var="grade" items="${studentGrades}">
            <li>${grade}</li>
        </c:forEach>
    </ul>
</div>

<!-- ... Rest of the HTML code ... -->

<!-- Include Bootstrap JS and jQuery (optional) if needed -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
</script>

</body>
</html>
