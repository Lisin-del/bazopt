// const csrfToken1 = document.cookie;
// console.log(csrfToken1);

function redirectToRegisterPage() {
    window.location.href = "registration.html";
}

function redirectToLoginPage() {
    window.location.href = "login.html";
}

function getCSRFToken() {
    try {
        response = fetch("http://127.0.0.1:8080/csrf");
        if (response.status === 200) {
            json = response.json();
            console.log("getCSRFToken function()" + json);
            return json;
        }
    } catch(e) {
        console.error(e);
    }
}

function sendRegistrationData() {   
    try {
        // csrfToken = getCSRFToken();
        // console.log(csrfToken);
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

        fetch("/csrf").then(response => {
            if (response.status === 200) {
                return response.json();
            }
            throw new Error("Unknown status: " + response.status);
        }).then(csrf => {
            const headers = {};
            headers[csrf.headerName] = btoa(csrf.token);
            return fetch("http://127.0.0.1:8080/register", {
                method: "POST",    
                headers: headers,
                body: userJson
            })
        });
    } catch (e) {
        console.error(e);
    }
    //redirectToLoginPage();
}
