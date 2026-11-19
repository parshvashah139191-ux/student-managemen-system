
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Form</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            margin: 0;
        }

        header {
            background: #0f172a;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .container {
            max-width: 450px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px #00000015;
        }

        h2 {
            text-align: center;
            color: #0f172a;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            background: #2563eb;
            color: white;
            padding: 13px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 20px;
            font-size: 16px;
        }

        button:hover {
            background: #1d4ed8;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #2563eb;
            text-decoration: none;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 12px;
            border-radius: 6px;
        }
    </style>
</head>

<body>

<header>
    <h1>Student Management System</h1>
</header>

<div class="container">

    <h2>
        ${student.id == 0 ? 'Add Student' : 'Edit Student'}
    </h2>

    <c:if test="${not empty error}">
        <p class="error">
            <c:out value="${error}"/>
        </p>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/save">

        <input type="hidden"
               name="id"
               value="${student.id}">

        <label>Full Name</label>

        <input type="text"
               name="name"
               value="<c:out value='${student.name}'/>"
               placeholder="Enter student name"
               maxlength="100"
               required>

        <label>Email Address</label>

        <input type="email"
               name="email"
               value="<c:out value='${student.email}'/>"
               placeholder="Enter email address"
               maxlength="100"
               required>

        <label>Course</label>

        <input type="text"
               name="course"
               value="<c:out value='${student.course}'/>"
               placeholder="Enter course"
               maxlength="100"
               required>

        <label>Age</label>

        <input type="number"
               name="age"
               value="${student.age == 0 ? '' : student.age}"
               placeholder="Enter age"
               min="1"
               max="100"
               required>

        <label>Phone Number</label>

        <input type="tel"
               name="phone"
               value="<c:out value='${student.phone}'/>"
               placeholder="Enter phone number"
               pattern="[0-9]{1,15}"
               maxlength="15">

        <button type="submit">
            ${student.id == 0 ? 'Add Student' : 'Update Student'}
        </button>

    </form>

    <a class="back"
       href="${pageContext.request.contextPath}/students">
        Back to Dashboard
    </a>

</div>

</body>
</html>
