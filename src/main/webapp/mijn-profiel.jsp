<%@ page import="nl.hu.bep.shopping.model.Gebruiker" %>
<%@ page import="nl.hu.bep.shopping.model.Vragenlijst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mijn Profiel</title>
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
        .profile-container {
            width: 80%;
            margin: auto;
            background-color: #FCFBF2;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .profile-details {
            margin-top: 20px;
        }
        .profile-details p {
            margin: 10px 0;
        }
        .profile-details strong {
            font-weight: bold;
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
    </style>
</head>
<body>
<div class="nav-bar">
    <a href="/agenda.jsp">Agenda</a>
    <a href="/overzicht.jsp">Overzicht</a>
    <a href="/mijn-historie.jsp">Mijn Historie</a>
    <a href="/gegevensAanpassen.jsp">Gegevens aanpassen</a>
    <div class="right-align">
        <a href="/logout">Uitloggen</a>
    </div>
</div>
<div class="profile-container">
    <h1 class="center-aligned">Mijn Profiel</h1>
    <div class="profile-details">
        <%
            Gebruiker currentGebruiker = (Gebruiker) session.getAttribute("huidigeGebruiker");
            if (currentGebruiker != null) {
        %>
        <p><strong>Naam:</strong> <%= currentGebruiker.getNaam() %></p>
        <p><strong>Email:</strong> <%= currentGebruiker.getMailAdres() %></p>
        <p><strong>Telefoonnummer:</strong> <%= currentGebruiker.getTelefoonnummer() %></p>
        <p><strong>KlantenNr:</strong> <%= currentGebruiker.getKlantenNr() %></p>
        <p><strong>Aantal ingevulde Lijsten:</strong> <%= currentGebruiker.getIngevuldeLijsten().size() %></p>
        <%
            } else {
                response.sendRedirect("/inlogPagina.html");
                return;
            }
        %>
    </div>
</div>
</body>
</html>
