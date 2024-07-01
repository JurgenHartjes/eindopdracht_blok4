<%@ page import="java.util.List" %>
<%@ page import="nl.hu.bep.shopping.model.Vraag" %>
<%@ page import="nl.hu.bep.shopping.model.Vragenlijst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vragenlijst</title>
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
            justify-content: center;
            align-items: center;
        }
        .custom-dialog {
            background-color: #FCFBF2;
            padding: 20px;
            border-radius: 15px;
            max-width: 80%;
            width: 50%;
            margin: auto;
            margin-top: 15%;
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
            margin: 0 1rem;
            padding: 1rem;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            width: 100%;
            border-radius: 0.5rem;
        }
        .star {
            font-size: 3vh;
            cursor: pointer;
        }
        .one {
            color: rgb(255, 0, 0);
        }
        .two {
            color: rgb(255, 106, 0);
        }
        .three {
            color: rgb(251, 255, 120);
        }
        .four {
            color: rgb(255, 255, 0);
        }
        .five {
            color: rgb(24, 159, 14);
        }
        .readonly-textarea {
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            cursor: not-allowed;
        }
        .star.disabled {
            pointer-events: none;
            opacity: 0.5;
        }
    </style>
</head>
<body>
<%
    // Parameters ophalen
    String vragenlijstId = request.getParameter("id");
    boolean viewOnly = request.getParameter("viewOnly") != null && request.getParameter("viewOnly").equals("true");

    // Haal vragenlijst op
    Vragenlijst vragenlijst = Vragenlijst.getVragenlijstById(vragenlijstId);
    if (vragenlijst == null) { %>
<p>"Vragenlijst niet gevonden." </p>
<% } else {
    List<Vraag> questions = vragenlijst.getVragen();
%>
<form id="backForm" method="post" action="/overzicht.jsp">
    <button class="back_button"><<</button>
</form>
<dialog class="custom-dialog" open>
    <form method="post" action="/vragenlijst">
        <h1 class="center-aligned">Vragenlijst <%= vragenlijst.getName() %></h1>
        <h2 class="center-aligned">Jumbo Foodmarkt Leidsche Rijn</h2>
        <h4>Voordat U onze vragenlijst invult, willen wij U graag bedanken voor het winkelen bij Jumbo.
            Deze vragenlijst is volledig anoniem, wij vragen u daarom om deze vragen zo eerlijk mogelijk te beantwoorden.
            Wij proberen Uw feedback zo snel mogelijk te verwerken.</h4>
        <br><br>
        <div class="form-content">
            <% int i = 0;
                for (Vraag question : questions) {
                    i++;
            %>
            <label for="vraag<%= i %>"><%= i %>. <%= question.getVraag() %></label><br>
            <% if (question.getVraagtype().equals("vijfSterren")) { %>
            <span id="star1_Q<%= i %>" class="star <%= viewOnly ? "disabled" : "" %>" onclick="gfg(1, <%= i %>)">★</span>
            <span id="star2_Q<%= i %>" class="star <%= viewOnly ? "disabled" : "" %>" onclick="gfg(2, <%= i %>)">★</span>
            <span id="star3_Q<%= i %>" class="star <%= viewOnly ? "disabled" : "" %>" onclick="gfg(3, <%= i %>)">★</span>
            <span id="star4_Q<%= i %>" class="star <%= viewOnly ? "disabled" : "" %>" onclick="gfg(4, <%= i %>)">★</span>
            <span id="star5_Q<%= i %>" class="star <%= viewOnly ? "disabled" : "" %>" onclick="gfg(5, <%= i %>)">★</span>
            <input type="hidden" id="starRating<%= i %>" name="starRating<%= i %>" value="0">
            <% } else { %>
            <textarea id="vraag<%= i %>" name="vraag<%= i %>" rows="3" cols="50" <%= viewOnly ? "readonly" : "" %>></textarea>
            <% } %>
            <br><br>
            <% } %>
        </div>
        <br><br>
        <div class="button-container">
            <button id="backToTheSurface" onclick="topFunction()">naar boven</button>
            <label id="errorLabel"></label>
            <% if (!viewOnly) { %>
            <button type="submit" id="buttonJoin">afronden</button>
            <% }
            }%>
        </div>
    </form>
</dialog>
<script type="text/javascript">
    function confirmBackButton() {
        return confirm("Weet u zeker dat u wilt terugkeren? De ingevulde gegevens worden niet opgeslagen.");
    }

    function confirmBack() {
        return confirm("Weet u zeker dat u wilt verzenden? De ingevulde gegevens worden opgeslagen.");
    }

    document.getElementById("backToTheSurface").onclick = function(event) {
        event.preventDefault();
        topFunction();
    };

    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }

    function gfg(n, questionIndex) {
        if (!document.getElementById("starRating" + questionIndex).hasAttribute("readonly")) {
            remove(questionIndex);
            for (let i = 1; i <= n; i++) {
                let star = document.getElementById("star" + i + "_Q" + questionIndex);
                if (n == 1) star.className = "star one";
                else if (n == 2) star.className = "star two";
                else if (n == 3) star.className = "star three";
                else if (n == 4) star.className = "star four";
                else if (n == 5) star.className = "star five";
            }
            document.getElementById("starRating" + questionIndex).value = n; // Set the hidden input value
        }
    }

    function remove(questionIndex) {
        for (let i = 1; i <= 5; i++) {
            let star = document.getElementById("star" + i + "_Q" + questionIndex);
            star.className = "star " + (<%= request.getParameter("viewOnly") != null && request.getParameter("viewOnly").equals("true") ? "'disabled'" : "''" %>);

        }
    }
</script>
</body>
</html>
