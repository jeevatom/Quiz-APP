document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('languageForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const language = document.getElementById('language').value;
        fetchQuestions(language);
    });

    function fetchQuestions(programminglanguage) {
        fetch(`http://localhost:8080/question/language/${programminglanguage}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                const questionsContainer = document.getElementById('questionsContainer');
                questionsContainer.innerHTML = ''; // Clear existing content

                // Group questions by category
                const categories = {};
                data.forEach(question => {
                    const category = question.programminglanguage || 'Uncategorized';
                    if (!categories[category]) {
                        categories[category] = [];
                    }
                    categories[category].push(question);
                });

                // Create and append category sections
                Object.keys(categories).forEach(category => {
                    const categoryContainer = document.createElement('div');
                    categoryContainer.className = 'mb-4';

                    const categoryHeader = document.createElement('h3');
                    categoryHeader.textContent = category;
                    categoryHeader.className = 'mb-3';

                    categoryContainer.appendChild(categoryHeader);

                    categories[category].forEach(question => {
                        const questionElement = document.createElement('div');
                        questionElement.classList.add('list-group-item');
                        questionElement.innerHTML = `
                            <h5>${question.id}. ${question.question}</h5>
                            <ul>
                                <li>${question.option1}</li>
                                <li>${question.option2}</li>
                                <li>${question.option3 || ''}</li>
                                <li>${question.option4 || ''}</li>
                            </ul>
                            <p><strong>Answer:</strong> ${question.rightanswer}</p>
                        `;
                        categoryContainer.appendChild(questionElement);
                    });

                    questionsContainer.appendChild(categoryContainer);
                });
            })
            .catch(error => {
                const errorContainer = document.getElementById('error');
                errorContainer.textContent = 'Error fetching questions: ' + error.message;
            });
    }
});
