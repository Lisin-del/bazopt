function redirectToRegisterPage() {
    window.location.href = "http://127.0.0.1:8080/registration";
}

function redirectToLoginPage() {
    window.location.href = "http://127.0.0.1:8080/login";
}

function redirectToHomePage() {
    window.location.href = "home.html";
}

function redirectToWholesaleBasePage() {
    window.location.href = "http://127.0.0.1:8080/wholesaleBaseHome";
}

function logOut() {
    window.location.href = "http://127.0.0.1:8080/logout";
}

function allProducts() {
    window.location.href = "http://127.0.0.1:8080/allProducts";
}

function basketProduct() {
    window.location.href = "http://127.0.0.1:8080/basket"
}

function allOrders() {
    window.location.href = "http://127.0.0.1:8080/order/all"
}

function redirectToUserProfile() {
    window.location.href = "http://127.0.0.1:8080/user/getUserProfile";
}

function getOrderCreationInfo() {
    window.location.href = "http://127.0.0.1:8080/order/getOrderCreationInfo";
}

function getAllOrders() {
    window.location.href = "http://127.0.0.1:8080/order/all"
}

function getProductWithBestPrice(productID) {
    window.location.href = "http://127.0.0.1:8080/product/bestPrice?productID=".concat(productID);
}

async function createOrder() {
    try {
        csrfResponse = await fetch("http://127.0.0.1:8080/csrf");
        
        if (csrfResponse.status === 200) {
            csrfJson = await csrfResponse.json();
            
            headers = {};
            headers[csrfJson.headerName] = csrfJson.token;
            headers['Content-Type'] = 'application/json';
            
            cardUpdateResponse = await fetch("http://127.0.0.1:8080/order/create", {
                method: "POST",
                headers: headers
            });

            getAllOrders();
        }
    } catch(e) {
        log.error("Order creation failed " + e);
    }
}

async function updateUserDebitCard() {
    try {
        let formData = new FormData(document.getElementById("userInfo"));

        let fullNameVar = formData.get("cardFullName");
        let cardNumberVar = formData.get("cardNumber");
        let dateVar = formData.get("date");
        let cvvVar = formData.get("cvv");
        
        let jsonString = {
            userFullName: fullNameVar,
            cardNumber: cardNumberVar,
            expirationDate: dateVar,
            cvv: cvvVar
        };

        let cardJson = JSON.stringify(jsonString);
        
        csrfResponse = await fetch("http://127.0.0.1:8080/csrf");
        
        if (csrfResponse.status === 200) {
            csrfJson = await csrfResponse.json();
            headers = {};
            headers[csrfJson.headerName] = csrfJson.token;
            headers['Content-Type'] = 'application/json';
            cardUpdateResponse = await fetch("http://127.0.0.1:8080/debitCard/save", {
                method: "POST",
                headers: headers,
                body: cardJson
            });
        }
        redirectToUserProfile();
    } catch (e) {
        console.error(e);
    }
    
}

async function updateUserInfo() {
    try {
        csrfResponse = await fetch("http://127.0.0.1:8080/csrf");

        if (csrfResponse.status === 200) {
            csrfJson = await csrfResponse.json();
            headers = {};
            headers[csrfJson.headerName] = csrfJson.token;
            headers['Content-Type'] = 'application/json';
            
            let formData = new FormData(document.getElementById("userInfo"));
            let firstNameVar = formData.get("FirstName");
            let lastNameVar = formData.get("LastName");
            let emailVar = formData.get("Email");
            let addressVar = formData.get("Address");
            let passwordVar = formData.get("Password");
            let jsonString = {
                firstname: firstNameVar,
                lastname: lastNameVar,
                address: addressVar,
                email: emailVar,
                password: passwordVar,
                role: "user"
            }
            let userJson = JSON.stringify(jsonString);
            registrationResponse = await fetch("http://127.0.0.1:8080/user/update", {
                method: "POST",    
                headers: headers,
                body: userJson
            });
        }
        redirectToUserProfile();
    } catch (e) {
        console.error(e);
    }
}

function redirectToRegistrationErrorPage() {
    window.location.href = "http://127.0.0.1:8080/registrationError"
}

async function sendRegistrationData() {
    try {
        let formData = new FormData(document.getElementById("registrationFormContainer"));
        let firstNameVar = formData.get("Firstname");
        let lastNameVar = formData.get("Lastname");
        let emailVar = formData.get("Email");
        let addressVar = formData.get("Address");
        let passwordVar = formData.get("Password");
        let jsonString = {
            firstname: firstNameVar,
            lastname: lastNameVar,
            address: addressVar,
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
        
        errorText = await registrationResponse.text();
        
        if (errorText !== "") {
            redirectToRegistrationErrorPage();
        } else {
            redirectToLoginPage();
        }

    } catch (e) {
        console.error(e);
    }
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

function openProductSeparatePage(id) {
    url = "http://127.0.0.1:8080/product/".concat(id);
    window.location.href = url;
}

//todo: replace the search for bases with search for products
function searchProducts() {
    searchFormData = new FormData(document.getElementById("searchForm"));
    userSearch = searchFormData.get("searchField");
    url = "http://127.0.0.1:8080/products/search?userSearch=".concat(userSearch);
    window.location.href = url;
}

function getProductsByBaseID(baseID) {
    url = "http://127.0.0.1:8080/products/bases?baseID=".concat(baseID);
    window.location.href = url;
}

function getUserProfile() {
    window.location.href = "http://127.0.0.1:8080/user/getUserProfile";
}

async function getProductsWithFilter() {
    countrySelector = document.getElementById("countrySelector");
    countrySelectorValue = countrySelector.options[countrySelector.selectedIndex].text;
    
    baseSelector = document.getElementById("baseSelector");
    baseSelectorValue = baseSelector.options[baseSelector.selectedIndex].text;
    
    priceValue = Number(document.getElementById("priceFilter").value);
    
    quantityValue = Number(document.getElementById("quantityFilter").value);

    url = "http://127.0.0.1:8080/allProducts/filter?country="
        .concat(countrySelectorValue).concat("&")
        .concat("base=").concat(baseSelectorValue).concat("&")
        .concat("price=").concat(priceValue).concat("&")
        .concat("quantity=").concat(quantityValue)

    window.location.href = url;
}

async function addToBasket(productId) {
    quantityValue = document.getElementById("quantity").value;
    
    console.log("Quantity: " + quantityValue);
    
    url = "http://127.0.0.1:8080/basket/put?productId=".concat(productId).concat("&quantity=").concat(quantityValue);

    console.log("URL: " + url);

    csrfResponse = await fetch("http://127.0.0.1:8080/csrf");
    if (csrfResponse.status === 200) {
        csrfJson = await csrfResponse.json();
        headers = {};
        headers[csrfJson.headerName] = csrfJson.token;
        
        basketResponse = await fetch(url, {
            method: "POST",
            headers: headers
        });
        
        if (basketResponse.status === 200) {
            window.location.href = basketResponse.url;
        }
    } else {
        throw new Error("There are errors with CSRF token getting");
    }
}

async function deleteOrderByID(orderID) {
    url = "http://127.0.0.1:8080/order/delete?id=".concat(orderID);

    csrfResponse = await fetch("http://127.0.0.1:8080/csrf");

    if (csrfResponse.status === 200) {
        csrfJson = await csrfResponse.json();
        
        headers = {};
        headers[csrfJson.headerName] = csrfJson.token;
        
        orderResponse = await fetch(url, {
            method: "DELETE",
            headers: headers
        });
        
        if (orderResponse.status === 200) {
            getAllOrders();
        }
    } else {
        throw new Error("There are errors with CSRF token getting");
    }
}

async function deleteFromBasket(basketProductId) {
    url = "http://127.0.0.1:8080/basket/delete?id=".concat(basketProductId);

    csrfResponse = await fetch("http://127.0.0.1:8080/csrf");
    if (csrfResponse.status === 200) {
        csrfJson = await csrfResponse.json();
        headers = {};
        headers[csrfJson.headerName] = csrfJson.token;
        
        basketResponse = await fetch(url, {
            method: "DELETE",
            headers: headers
        });
        
        console.log(basketResponse.status);

        if (basketResponse.status === 200) {
            basketProduct();
        }
    } else {
        throw new Error("There are errors with CSRF token getting");
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