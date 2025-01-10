<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Form</title>
</head>
<body>
<h2>Add a New Patient</h2>
<form action="/patients" method="post">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>

    <label for="birthDate">Birth Date:</label>
    <input type="date" id="birthDate" name="birthDate" required><br><br>

    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" required><br><br>

    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required><br><br>

    <label for="ssn">SSN:</label>
    <input type="text" id="ssn" name="ssn" required><br><br>

    <label for="countryName">Country Name:</label>
    <input type="text" id="countryName" name="countryName" required><br><br>

    <label for="cityId">City ID:</label>
    <input type="text" id="cityId" name="city_id" required><br><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address"><br><br>

    <label for="contactId">Contact ID:</label>
    <input type="text" id="contactId" name="contact_id" required><br><br>

    <button type="submit">Submit</button>
</form>
</body>
</html>
