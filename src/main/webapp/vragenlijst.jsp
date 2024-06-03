<%@ page import="java.util.List" %>
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

        </style>

    </head>

    <body>

        <dialog class="custom-dialog" open>

            <form method="post" action="/vragenlijst">

                <h1 class="center-aligned">Vragenlijst</h1>
                <h2 class="center-aligned">Jumbo Foodmarkt Leidsche Rijn</h2>
                <h4>Voordat U onze vragenlijst invult, willen wij U graag bedanken voor het winkelen bij Jumbo.
                    Deze vragenlijst is volledig anoniem, wij vragen u daarom om deze vragen zo eerlijk mogelijk te beantwoorden.
                    Wij proberen Uw feedback zo snel mogelijk te verwerken.</h4>

                <br><br>

                <div class="form-content">

                    <%

                        List<String> questions = (List<String>) request.getAttribute("questions");

                        if (questions != null) {

                            for (int i = 0; i < questions.size(); i++) {        //voor elke vraag in de vragenlijst wordt er een label en een textarea aangemaakt

                                String question = questions.get(i);

                    %>

                    <label for="vraag<%= (i + 1) %>"><%= (i + 1) %>. <%= question %></label><br>
                    <textarea id="vraag<%= (i + 1) %>" name="vraag<%= (i + 1) %>" rows="3" cols="50"></textarea>
                    <br><br>

                    <%
                            }
                        }
                    %>

                </div>

                <br><br>

                <div class="button-container">

                    <button id="backToTheSurface" onclick="topFunction()">naar boven</button>
                    <label id="errorLabel"></label>
                    <button type="submit" id="buttonJoin">afronden</button>

                </div>

            </form>

        </dialog>

        <script type="text/javascript">


            document.getElementById("backToTheSurface").onclick = function(event) {
                event.preventDefault();
                topFunction();
            }


            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }


        </script>

    </body>

</html>
