document.getElementById('getPartsBtn').addEventListener('click', () => {
    fetch('http://localhost:8080/api/parts')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error at connection: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('resultArea').textContent = JSON.stringify(data, null, 2);
            console.log("Data successfully fetched:", data);
        })
        .catch(error => {
            document.getElementById('resultArea').textContent = "Error: " + error.message;
            console.error("Error at fetch:", error);
        });
});