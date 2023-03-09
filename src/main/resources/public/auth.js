// First, define the API endpoint URL
let apiUrl = 'http://127.0.0.1:8080/login';

// Next, define a function to make the API request with the user's credentials
async function authenticateUser(username, password) {
  // Create a JSON payload with the user's credentials
  const payload = {
    username: username.value,
    password: password.value,
  };

  // var details = {
  //   'username': username.value,
  //   'password': password.value
  // };

  // console.log(details)
  // var formBody = [];
  // for (var property in details) {
  //   var encodedKey = encodeURIComponent(property);
  //   var encodedValue = encodeURIComponent(details[property]);
  //   formBody.push(encodedKey + "=" + encodedValue);
  // }
  // formBody = formBody.join("&");

  console.log("API Url Ready")
  console.log(apiUrl)
  console.log(payload)
  console.log(typeof(payload))
  console.log(JSON.stringify(payload))
  // console.log(formBody)
  // Use the fetch API to make a POST request to the API endpoint
  // const response = await fetch(apiUrl, {
  //   method: 'POST',
  //   headers: {
  //     'Content-Type': 'application/json',
  //     'Access-Control-Allow-Origin': '*',
  //   },
  //   mode: 'no-cors',
  //   body: JSON.stringify(payload)
  //   // body: payload,
  //   // body: formBody,
  // });
  // console.log(response.status)
  // console.log("Response Ready")
  // alert(response.status)
  // // Parse the response JSON
  // const data = await response.json()
  // console.log(data)

  // const payload = {
  //   username: username.value,
  //   password: password.value,
  // };
  
  fetch(apiUrl, {
    method: 'POST',
    headers: {
      'Accept' : 'application/json',
      'Content-Type': 'application/json',
    },
    // mode: 'no-cors',
    body: JSON.stringify(payload)
  })
    .then(response => {
      console.log("Getting Response Body!")
      response.json()
    })
    .then(data => {
      // handle the response data
      console.log(data)
    })
    .catch(error => {
      // handle any errors
      console.log("Error" + error)
    });
  

  // console.log(data)
  // Return the authentication result
  // return data.privilege;
}
