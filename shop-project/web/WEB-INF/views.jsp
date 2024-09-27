<!-- web/WEB-INF/views/test.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Test User API</title>
</head>
<body>
<h1>Test User API</h1>
<form action="/api/users" method="post">
    <label for="jsonEntity">User JSON:</label>
    <textarea id="jsonEntity" name="jsonEntity"></textarea>
    <button type="submit">Create User</button>
</form>
</body>
</html>