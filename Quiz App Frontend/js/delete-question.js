document.getElementById('delete-question-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const questionId = document.getElementById('question-id').value;

    fetch(`http://localhost:8080/question/delete/${questionId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(result => {
        console.log('Server response:', result);
        alert('Question deleted successfully!');
        document.getElementById('delete-question-form').reset();
        // Optionally, refresh the list of questions or update the UI
    })
    .catch(error => console.error('Error deleting question:', error));
});
