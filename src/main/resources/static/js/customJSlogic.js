function redirectToRegisterPage() {
    window.location.href = "registration.html";
}

function redirectToLoginPage() {
    window.location.href = "login.html";
}

function redirectToHomePage() {
    window.location.href = "home.html";
}

function redirectToWholesaleBasePage() {
    window.location = "http://127.0.0.1:8080/wholesaleBaseHome";
}

function logOut() {
    window.location = "http://127.0.0.1:8080/logout";
}

function allProducts() {
    window.location = "http://127.0.0.1:8080/allProducts";
}

async function sendRegistrationData() {
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
        headers = {};
        headers['Content-Type'] = 'application/json';
        registrationResponse = await fetch("http://127.0.0.1:8080/register", {
            method: "POST",    
            headers: headers,
            body: userJson
        });
    } catch (e) {
        console.error(e);
    }
    redirectToLoginPage();
}

async function sendLoginData() {
    try {
        let formData = new FormData(document.getElementById("loginFormContainer"));
        csrfResponse = await fetch("http://127.0.0.1:8080/csrf");
        if (csrfResponse.status === 200) {
            csrfJson = await csrfResponse.json();
            headers = {};
            headers[csrfJson.headerName] = csrfJson.token;
            loginResponse = await fetch("http://127.0.0.1:8080/login/process", {
                method: "POST",
                body: formData,
                headers: headers
            });
            // redirectToHomePage();
            redirectToWholesaleBasePage();
        } else {
            throw new Error("There are errors with CSRF token getting");
        }

        // fetch("http://127.0.0.1:8080/csrf")
        //             .then(response => {
        //                 if (response.status === 200) {
        //                     return response.json();
        //                 }
        //                 throw new Error("Unknown status: " + response.status);
        //             }).then(csrf => {
        //                 if (csrf && csrf.headerName) {
        //                     headers = {};
        //                     //formData.append('_csrf', csrf.token);
        //                     headers[csrf.headerName] = csrf.token;
        //                     return fetch("http://127.0.0.1:8080/login/process", {
        //                         method: "POST",
        //                         body: formData,
        //                         headers: headers
        //                     });
        //                 } else {
        //                     throw new Error("There are errors with CSRF token getting");
        //                 }

        //             }).then(login => {
        //                 console.log("Try to redirect to the home page...")
        //                 // redirectToHomePage();
        //             })
    
    } catch (e) {
        console.error(e);
    }
}

function openWholesaleBaseSeparatePage(wholesaleBaseName) {
    url = "http://127.0.0.1:8080/wholesaleBase/".concat(wholesaleBaseName);
    window.location.href = url;
}

//todo: replace the search for bases with search for products
function searchWholesaleBase() {
    searchFormData = new FormData(document.getElementById("searchForm"));
    userSearch = searchFormData.get("searchField");
    url = "http://127.0.0.1:8080/wholesaleBaseHome/filter?userSearch=".concat(userSearch);
    window.location.href = url;
}

async function getProductsWithFilter() {
    countrySelector = document.getElementById("countrySelector");
    countrySelectorValue = countrySelector.options[countrySelector.selectedIndex].text;
    console.log(countrySelectorValue);
    baseSelector = document.getElementById("baseSelector");
    baseSelectorValue = baseSelector.options[baseSelector.selectedIndex].text;
    console.log(baseSelectorValue);
    try {
        csrfResponse = await fetch("http://127.0.0.1:8080/csrf");
        if (csrfResponse.status === 200) {
            console.log("Token has been got");
            csrfJson = await csrfResponse.json();
            headers = {};
            headers[csrfJson.headerName] = csrfJson.token;
            headers['Content-Type'] = 'application/json';
            let bodyString = {
                producer: countrySelectorValue,
                base: baseSelectorValue,
                price: 0,
                quantity: 0
            }
            let jsonString = JSON.stringify(bodyString);
            products = await fetch("http://127.0.0.1:8080/allProducts/filter", {
                method: "POST",
                body: jsonString,
                headers: headers
            });
            // redirectToHomePage();
            // redirectToWholesaleBasePage();
        } else {
            throw new Error("There are errors with CSRF token getting");
        }
    } catch (e) {
        console.error(e);
    }
}
//
//        let response = await fetch("http://127.0.0.1:8080/login-process", {
//            method: 'POST',
//            body: formData
//        });


// fetch("http://127.0.0.1:8080/csrf")
//             .then(response => {
//                 if (response.status === 200) {
//                     return response.json();
//                 }
//                 throw new Error("Unknown status: " + response.status);
//             }).then(csrf => {
//                 if (csrf && csrf.headerName) {
//                     headers = {};
//                     headers[csrf.headerName] = csrf.token;
//                     headers['Content-Type'] = 'application/json';
//                     return fetch("http://127.0.0.1:8080/register", {
//                         method: "POST",    
//                         headers: headers,
//                         body: userJson
//                     });
//                 } else {
//                     throw new Error("There are errors with CSRF token getting");
//                 }
                
//             });