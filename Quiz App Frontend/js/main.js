document.addEventListener('DOMContentLoaded', function() {
    fetchQuestions();

    function fetchQuestions() {
        fetch('http://localhost:8080/question/allQuestions')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                const questionsContainer = document.getElementById('questions-container');
                questionsContainer.innerHTML = ''; // Clear existing content
                data.forEach(question => {
                    if (question.question && question.rightanswer) {
                        const questionElement = document.createElement('div');
                        questionElement.classList.add('list-group-item');
                        questionElement.innerHTML = `
                        
                            <h5>${question.id}.${question.question}</h5>
                            <ul style="disc">
                                <li> ${question.option1}</li>
                                <li> ${question.option2}</li>
                                <li> ${question.option3 || ''}</li>
                                <li> ${question.option4 || ''}</li>
                            </ul>
                            <p><strong>Answer:</strong> ${question.rightanswer}</p>
                        `;
                        questionsContainer.appendChild(questionElement);
                    }
                });
            })
            .catch(error => console.error('Error fetching questions:', error));
    }
});
