function redirectToRegisterPage() {
    window.location.href = "registration.html";
}

function redirectToLoginPage() {
    window.location.href = "login.html";
}

function sendRegistrationData() {
   
    try {
        let formData = new FormData(document.getElementById("registrationFormContainer"));
        
        let firstNameVar = formData.get("Firstname");
        let lastNameVar = formData.get("Lastname");
        let emailVar = formData.get("Email");
        let passwordVar = formData.get("Password");

        let jsonString = {
            firstname: firstNameVar,
            lastname: lastNameVar,
            email: emailVar,
            password: passwordVar,
            role: "user"
        }

        let userJson = JSON.stringify(jsonString);
        
        const response = fetch("http://127.0.0.1:8080/register", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: userJson,
        });
    } catch (e) {
        console.error(e);
    }
    redirectToLoginPage();
}