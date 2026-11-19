
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Login</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #0f172a, #2563eb);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-box {
            background: white;
            width: 400px;
            max-width: 92%;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0 10px 30px #00000040;
        }

        h1 {
            text-align: center;
            color: #0f172a;
            font-size: 25px;
        }

        .subtitle {
            text-align: center;
            color: #64748b;
            margin-bottom: 30px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-top: 15px;
        }

        input {
            width: 100%;
            padding: 13px;
            margin-top: 8px;
            border: 1px solid #cbd5e1;
            border-radius: 7px;
            font-size: 15px;
        }

        button {
            width: 100%;
            padding: 13px;
            background: #2563eb;
            color: white;
            border: none;
            border-radius: 7px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 25px;
        }

        button:hover {
            background: #1d4ed8;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 6px;
            text-align: center;
        }

        .footer {
            text-align: center;
            color: #64748b;
            font-size: 12px;
            margin-top: 25px;
        }
    </style>
</head>

<body>

<div class="login-box">

    <h1>Student Management System</h1>

    <p class="subtitle">Administrator Login</p>

    <c:if test="${not empty error}">
        <div class="error">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/login">

        <label for="username">Username</label>

        <input type="text"
               id="username"
               name="username"
               placeholder="Enter username"
               autocomplete="username"
               required>

        <label for="password">Password</label>

        <input type="password"
               id="password"
               name="password"
               placeholder="Enter password"
               autocomplete="current-password"
               required>

        <button type="submit">Login</button>

    </form>

    <p class="footer">
        Student Management System | AJT Mini Project
    </p>

</div>

</body>
</html>
