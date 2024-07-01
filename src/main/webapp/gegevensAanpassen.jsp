<%@ page import="nl.hu.bep.shopping.model.Gebruiker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gegevens Aanpassen</title>
    <style>
        body {
            background-image: url("https://www.slagersvak.biz/images/featured_news/jumbo_1_1.jpg");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            margin: 0;
            height: 100vh;
            background-attachment: fixed;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            color: #000;
        }
        .center-aligned {
            text-align: center;
        }
        .form-container {
            width: 60%;
            margin-top: 50px;
            padding: 20px;
            background-color: #FCFBF2;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        .form-container input[type=text],
        .form-container input[type=email],
        .form-container input[type=password] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container button {
            background-color: #FFE800;
            color: #000;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
        }
        .form-container button:hover {
            background-color: #C6B400;
            color: #5B5B5B;
        }
    </style>
</head>
<body>
<div class="center-aligned">
    <h1>Gegevens Aanpassen</h1>
</div>
<div class="form-container">
    <form action="/update-gegevens" method="post">
        <label for="naam">Naam:</label>
        <%Gebruiker currentGebruiker = (Gebruiker) session.getAttribute("huidigeGebruiker");%>

        <input type="text" id="naam" name="naam" value="<%= currentGebruiker.getNaam() %>">

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= currentGebruiker.getMailAdres() %>">

        <label for="telefoonnummer">Telefoonnummer:</label>
        <input type="text" id="telefoonnummer" name="telefoonnummer" value="<%= currentGebruiker.getTelefoonnummer() %>">

        <label for="wachtwoord">Wachtwoord:</label>
        <input type="password" id="wachtwoord" name="wachtwoord" value="<%= currentGebruiker.getWachtwoord() %>">

        <button type="submit">Opslaan</button>
    </form>
</div>
</body>
</html>
