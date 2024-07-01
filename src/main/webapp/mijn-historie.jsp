<%@ page import="java.util.ArrayList" %>
<%@ page import="nl.hu.bep.shopping.model.Vragenlijst" %>
<%@ page import="nl.hu.bep.shopping.model.Gebruiker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mijn Historie</title>
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
        .nav-bar {
            width: 100%;
            background-color: #FFE800;
            overflow: hidden;
            display: flex;
            justify-content: space-between;
            padding: 10px;
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
            margin-left: auto;
            padding: 10px;
        }
        .question-list {
            width: 80%;
            margin: auto;
            background-color: #FCFBF2;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .question-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            color: #585858;
        }
        .question-item h3 {
            margin: 0;
            flex-grow: 1;
        }
        .question-item form {
            margin: 0;
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
        .center-aligned {
            text-align: center;
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
    <h1 class="center-aligned">Mijn Historie</h1>
    <%
        Gebruiker currentGebruiker = (Gebruiker) session.getAttribute("huidigeGebruiker");
        if (currentGebruiker != null) {
            ArrayList<Vragenlijst> ingevuldeLijsten = currentGebruiker.getIngevuldeLijsten();
            if (ingevuldeLijsten != null && !ingevuldeLijsten.isEmpty()) {
                for (Vragenlijst vragenlijst : ingevuldeLijsten) {
    %>
    <div class="question-item">
        <h3><%= vragenlijst.getName() %></h3>
        <form method="get" action="<%= request.getContextPath() %>/ingevuldeVragenlijst.jsp"> <!-- Adjusted action URL -->
            <input type="hidden" name="id" value="<%= vragenlijst.getId() %>">
            <input type="hidden" name="viewOnly" value="true">
            <button type="submit">Bekijken</button>
        </form>
    </div>
    <%
        }
    } else {
    %>
    <p class="center-aligned">U heeft nog geen vragenlijsten ingevuld.</p>
    <%
            }
        } else {
            response.sendRedirect("/inlogPagina.html");
        }
    %>
</div>
</body>
</html>
