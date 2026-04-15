async function createComplaint() {
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    try {
        await apiRequest("/complaints", "POST", {
            title,
            description
        });

        alert("Complaint submitted!");

    } catch (err) {
        alert("Error: " + err.message);
    }
}