<!DOCTYPE html>
<html>
<head>
    <title>Course Details</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <form class="form-inline" action="/student.do" method="GET">
        <input type="hidden" name="action" value="stat">
        <input type="text" class="form-control" id="searchCourse" name="courseName" placeholder="Search for a course">
        <div class="input-group-append">
            <button class="btn btn-primary" type="submit" id="searchButton" value="stat">Search</button>
        </div>
    </form>

    <div class="mt-4">
        <h4>Course Statistics for ${param.courseName}:</h4>
        <br>
        <div class="container">
            <div class="row">
                <div class="col">
                    <label for="medianProgress">Median:</label>
                    <div class="progress" id="medianProgress">
                        <div class="progress-bar" role="progressbar" style="width: ${statistics.Median}%" aria-valuenow="${statistics.Median}" aria-valuemin="0" aria-valuemax="100">${statistics.Median}</div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col">
                    <label for="meanProgress">Mean:</label>
                    <div class="progress" id="meanProgress">
                        <div class="progress-bar" role="progressbar" style="width: ${statistics.Mean}%" aria-valuenow="${statistics.Mean}" aria-valuemin="0" aria-valuemax="100">${statistics.Mean}</div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col">
                    <label for="minProgress">Min:</label>
                    <div class="progress" id="minProgress">
                        <div class="progress-bar" role="progressbar" style="width: ${statistics.Min}%" aria-valuenow="${statistics.Min}" aria-valuemin="0" aria-valuemax="100">${statistics.Min}</div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col">
                    <label for="maxProgress">Max:</label>
                    <div class="progress" id="maxProgress">
                        <div class="progress-bar" role="progressbar" style="width: ${statistics.Max}%" aria-valuenow="${statistics.Max}" aria-valuemin="0" aria-valuemax="100">${statistics.Max}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- Include Bootstrap JS and jQuery (optional) if needed -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
