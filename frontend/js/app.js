const registerForm = document.getElementById("registerForm");

if (registerForm) {
    registerForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value;
        const phone = document.getElementById("phone").value.trim();
        const role = document.getElementById("role").value;

        const message = document.getElementById("message");
        message.innerText = "";

        try {
            const response = await fetch("http://localhost:8080/api/users", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name,
                    email: email,
                    password: password,
                    phoneNumber: phone,
                    role: role
                })
            });

            const result = await response.text();

            if (!response.ok) {
                throw new Error(result || "Registration failed");
            }

            message.style.color = "green";
            message.innerText = "User registered successfully";

            registerForm.reset();

        } catch (error) {
            message.style.color = "red";
            message.innerText = error.message;
            console.error("Register error:", error);
        }
    });
}