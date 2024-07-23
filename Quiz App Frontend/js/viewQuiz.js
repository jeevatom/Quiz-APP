// $(document).ready(function() {

//         // Get Quiz Questions
//         $('#getQuizForm').on('submit', function(e) {
//             e.preventDefault();
//             const quizId = $('#quizId').val();

//             $.ajax({
//                 url: `http://localhost:8080/quiz/get/${quizId}`,
//                 type: 'GET',
//                 success: function(response) {
//                     $('#questionsList').empty();
//                     if (response.length === 0) {
//                         $('#questionsList').append('<li class="list-group-item">No questions found.</li>');
//                     } else {
//                         response.forEach(function(question) {
//                             $('#questionsList').append(`<li class="list-group-item">${question.questionText}</li>`);
//                         });
//                     }
//                 },
//                 error: function(xhr) {
//                     alert('Error fetching questions: ' + xhr.responseText);
//                 }
//             });
//         });
//     });
