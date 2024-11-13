<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Guess Number</title>
</head>
<body>
<h1>Game: Guess the number</h1>
<p>The number is greater, less, or equal to <strong><%= request.getAttribute("guess") %></strong>?</p>
<form method="post" action="guess-number">
  <input type="hidden" name="guess" value="<%= request.getAttribute("guess") %>"/>
  <button type="submit" name="action" value="Greater">Greater</button>
  <button type="submit" name="action" value="Less">Less</button>
  <button type="submit" name="action" value="Equal">Equal</button>
</form>
</body>
</html>
