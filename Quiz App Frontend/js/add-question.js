document.getElementById('question-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const questionData = {
        programminglanguage: document.getElementById('programminglanguage').value,
        difficultylevel: document.getElementById('difficultylevel').value,
        question: document.getElementById('question').value,
        option1: document.getElementById('option1').value,
        option2: document.getElementById('option2').value,
        option3: document.getElementById('option3').value || null,
        option4: document.getElementById('option4').value || null,
        rightanswer: document.getElementById('rightanswer').value
    };

    console.log('Sending question data:', questionData);

    fetch('http://localhost:8080/question/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(questionData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(result => {
        console.log('Server response:', result);
        alert('Question added successfully!');
        document.getElementById('question-form').reset();
    })
    .catch(error => console.error('Error adding question:', error));
});
