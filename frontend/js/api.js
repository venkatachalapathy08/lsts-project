const BASE_URL = "http://localhost:8080/api";

async function apiRequest(url, method, data = null) {
    const token = localStorage.getItem("token");

    const headers = {
        "Content-Type": "application/json"
    };

    if (token) {
        headers["Authorization"] = "Bearer " + token;
    }

    const response = await fetch(BASE_URL + url, {
        method: method,
        headers: headers,
        body: data ? JSON.stringify(data) : null
    });

    if (!response.ok) {
        const error = await response.text();
        throw new Error(error || "API Error");
    }

    return response;
}