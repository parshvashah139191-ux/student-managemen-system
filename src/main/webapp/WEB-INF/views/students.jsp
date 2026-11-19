
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            margin: 0;
        }

        header {
            background: #0f172a;
            color: white;
            padding: 25px;
            text-align: center;
        }

        .container {
            width: 90%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 12px;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th {
            background: #1e293b;
            color: white;
            padding: 14px;
        }

        td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        .btn {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            color: white;
            text-decoration: none;
            cursor: pointer;
            display: inline-block;
        }

        .add { background: #16a34a; }
        .edit { background: #2563eb; }
        .delete { background: #dc2626; }

        .success { color: green; }
        .error { color: red; }
    </style>
</head>

<body>

<header>
    <h1>Student Management System</h1>
    <p>Advanced Java Technology Project</p>
</header>

<div class="container">

    <h2>Student Dashboard</h2>
    
<form method="post"
      action="${pageContext.request.contextPath}/logout"
      style="float:right;">

    <button type="submit" class="btn delete">
        Logout
    </button>

</form>

    

    <a class="btn add"
       href="${pageContext.request.contextPath}/add">
        + Add Student
    </a>

    <c:if test="${not empty message}">
        <p class="success">
            <c:out value="${message}"/>
        </p>
    </c:if>

    <c:if test="${not empty error}">
        <p class="error">
            <c:out value="${error}"/>
        </p>
    </c:if>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Age</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="student" items="${students}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.course}"/></td>
                <td><c:out value="${student.age}"/></td>
                <td><c:out value="${student.phone}"/></td>

                <td>
                    <a class="btn edit"
                       href="${pageContext.request.contextPath}/edit?id=${student.id}">
                        Edit
                    </a>

                    <form method="post"
                          action="${pageContext.request.contextPath}/delete"
                          style="display:inline;"
                          onsubmit="return confirm('Delete this student?');">

                        <input type="hidden"
                               name="id"
                               value="${student.id}">

                        <button class="btn delete" type="submit">
                            Delete
                        </button>

                    </form>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty students}">
            <tr>
                <td colspan="7">No student records found.</td>
            </tr>
        </c:if>

    </table>
</div>

</body>
</html>
