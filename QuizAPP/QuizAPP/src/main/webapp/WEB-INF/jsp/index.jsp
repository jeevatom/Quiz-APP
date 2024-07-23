<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz App</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>Quiz Questions</h1>
    <div id="quiz-container">
        <p>Loading questions...</p>
    </div>

    <script>
        $(document).ready(function() {
            // URL to fetch data from the REST API
            var apiUrl = '/question/allQuestions';

            $.get(apiUrl, function(data) {
                var quizContainer = $('#quiz-container');
                quizContainer.empty();

                if (data && data.length > 0) {
                    $.each(data, function(index, question) {
                        quizContainer.append('<div class="question-container">');
                        quizContainer.append('<h2>' + question.question + '</h2>'); // Adjust based on the actual field name
                        $.each(question.options, function(i, option) {
                            quizContainer.append('<p>' + option + '</p>');
                        });
                        quizContainer.append('</div>');
                    });
                } else {
                    quizContainer.append('<p>No questions available</p>');
                }
            }).fail(function() {
                $('#quiz-container').html('<p>Error loading questions.</p>');
            });
        });
    </script>
</body>
</html>
