
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
<title>Student Grading System</title>
    <style>
    </style>

</head>

<body>

Welcome ${ID}

<br><br>
<form action="/scores.do" method="post">
    <button type="submit" name="button" value="scores">Show My Grades</button>
    <input value="${ID}" type="hidden" name="idValue" id="text">
    <button type="submit" name="button" value="stats">Show Statistics</button>
    <button type="submit" name="button" value="logout">Logout</button>
</form>

<br><br>
${scores}



</body>
</html>