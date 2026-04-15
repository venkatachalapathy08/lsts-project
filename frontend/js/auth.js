const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value;
        const message = document.getElementById("message");

        message.innerText = "";

        try {
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || "Login failed");
            }

            const data = await response.json();

            console.log("Login response:", data);

            localStorage.setItem("token", data.token);
            localStorage.setItem("role", data.role);
            localStorage.setItem("userName",data.name);

            message.style.color = "green";
            message.innerText = "Login successful";

            setTimeout(() => {
                if (data.role === "ADMIN") {
                    window.location.href = "admin.html";
                } else {
                    window.location.href = "userDashboard.html";
                }
            }, 1000);

        } catch (error) {
            message.style.color = "red";
            message.innerText = error.message;
            console.error("Login error:", error);
        }
    });
}