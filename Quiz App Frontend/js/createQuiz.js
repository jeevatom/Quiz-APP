document.getElementById('quizForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const category = document.getElementById('category').value;
    const numQ = document.getElementById('numQ').value;
    const title = document.getElementById('title').value;

    fetch('http://localhost:8080/quiz/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `category=${category}&numQ=${numQ}&title=${title}`
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('responseMessage').innerText = data;
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('responseMessage').innerText = 'An error occurred while creating the quiz.';
    });
});
