<%@ page import="java.util.List" %>
<%@ page import="nl.hu.bep.shopping.model.Vragenlijst" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="nl.hu.bep.shopping.model.Gebruiker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Overzicht Vragenlijsten</title>
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
            color: #000; /* Set text color to black */
        }
        .custom-dialog {
            background-color: #FCFBF2;
            padding: 20px;
            border-radius: 15px;
            max-width: 80%;
            width: 50%;
            margin: 20px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Adding shadow for a better look */
        }
        .back_button {
            background-color: #FCFBF2;
            border-radius: 5px;
            width: 50px; /* Adjust the width as needed */
            height: 30px; /* Adjust the height as needed */
            position: absolute;
            top: 20px; /* Adjust the top margin as needed */
            left: 20px; /* Adjust the left margin as needed */
            z-index: 1000;
        }
        .center-aligned {
            text-align: center;
        }
        textarea {
            resize: none;
            max-width: 90%;
            min-width: 200px;
        }
        .form-content {
            padding-left: 10%;
            padding-right: 10%;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 10%;
        }
        .button-container button {
            width: 150px;
        }
        #errorLabel {
            flex-grow: 1;
            text-align: center;
            margin: 0 10px;
            color: red;
        }
        .card {
            max-width: 33rem;
            background: #fff;
            margin: 1rem 0; /* Add some margin for spacing between cards */
            padding: 1rem;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            width: 100%;
            border-radius: 0.5rem;
        }
        .nav-bar {
            width: 100%;
            background-color: #FFE800;
            overflow: hidden;
            display: flex;
            justify-content: space-between; /* Hiermee verdeel je de links over de breedte */
            padding: 10px; /* Optioneel: voeg wat ruimte toe rondom de links */
        }

        .nav-bar a {
            color: #000000;
            text-align: center;
            text-decoration: none;
            font-size: 17px;
            padding: 14px 16px;
        }

        .nav-bar a:hover {
            background-color: #C6B400;
            color: #5B5B5B;
        }

        .right-align {
            margin-left: auto; /* Dit duwt de rechterlink naar rechts */
            padding: 10px;
        }
        .question-list {
            width: 80%;
            margin: auto;
            background-color: #FCFBF2; /* Removing transparency */
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Adding shadow for a better look */
        }
        .question-item {
            display: flex; /* Use flexbox for horizontal alignment */
            justify-content: space-between; /* Space between text and button */
            align-items: center; /* Align items vertically center */
            margin-bottom: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Adding shadow for a better look */
            color: #585858; /* Set text color to black */
        }
        .question-item h3 {
            margin: 0;
            flex-grow: 1; /* Allow text to take up available space */
        }
        .question-item form {
            margin: 0; /* Remove margin from form */
        }
        .question-item button {
            background-color: #006E04;
            color: #FCFBF2;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
        .question-item button:hover {
            background-color: #015B04;
        }
    </style>
</head>
<body>
<div class="nav-bar">
    <a href="/agenda.jsp">Agenda</a>
    <a href="/overzicht.jsp">Overzicht</a>
    <a href="/mijn-historie.jsp">Mijn Historie</a>
    <a href="/mijn-profiel.jsp">Mijn Profiel</a>
    <div class="right-align">
        <a href="/logout">Uitloggen</a>
    </div>
</div>
<div class="question-list">
    <h1 class="center-aligned">Overzicht Vragenlijsten</h1>
    <%
        Gebruiker currentGebruiker = (Gebruiker) session.getAttribute("huidigeGebruiker");
        if (currentGebruiker != null) {
        ArrayList<Vragenlijst> vragenlijsten = (ArrayList<Vragenlijst>) application.getAttribute("vragenlijsten");
        if (vragenlijsten != null && !vragenlijsten.isEmpty()) {
            for (Vragenlijst vragenlijst : vragenlijsten) {
                if (!currentGebruiker.hasFilledInLastWeek(vragenlijst))  {
    %>
    <div class="question-item">
        <h3><%= vragenlijst.getName() %></h3>
        <form method="get" action="<%= request.getContextPath() %>/vragenlijst">
            <input type="hidden" name="id" value="<%= vragenlijst.getId() %>">
            <button type="submit">Invullen</button>
        </form>
    </div>
    <%
            }
        }
    } else {
        System.out.println("geen vragenlijsten gevonden");
    %>
    <p class="center-aligned">Er zijn geen openstaande vragenlijsten.</p>
    <%
        }
        } else {response.sendRedirect("/inlogPagina.html");}
    %>
</div>
</body>
</html>
