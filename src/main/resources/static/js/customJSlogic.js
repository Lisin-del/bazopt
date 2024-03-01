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
        fetch("http://127.0.0.1:8080/csrf")
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                throw new Error("Unknown status: " + response.status);
            }).then(csrf => {
                if (csrf && csrf.headerName) {
                    headers = {};
                    headers[csrf.headerName] = csrf.token;
                    headers['Content-Type'] = 'application/json';
                    return fetch("http://127.0.0.1:8080/register", {
                        method: "POST",    
                        headers: headers,
                        body: userJson
                    });
                } else {
                    throw new Error("There are errors with CSRF token getting");
                }
                
            });
    } catch (e) {
        console.error(e);
    }
    redirectToLoginPage();
}

function sendLoginData() {
    try {
        let formData = new FormData(document.getElementById("loginFormContainer"));
        fetch("http://127.0.0.1:8080/csrf")
                    .then(response => {
                        if (response.status === 200) {
                            return response.json();
                        }
                        throw new Error("Unknown status: " + response.status);
                    }).then(csrf => {
                        if (csrf && csrf.headerName) {
                            headers = {};
                            formData.append('_csrf', csrf.token);
                            headers[csrf.headerName] = csrf.token;
                            return fetch("http://127.0.0.1:8080/login-process", {
                                method: "POST",
                                body: formData,
                                headers: headers
                            });
                        } else {
                            throw new Error("There are errors with CSRF token getting");
                        }

                    })
    } catch (e) {
        console.error(e);
    }
}

//
//        let response = await fetch("http://127.0.0.1:8080/login-process", {
//            method: 'POST',
//            body: formData
//        });
