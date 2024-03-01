function f() {
  const email = document.getElementById("login_email").value;
  const password = document.getElementById("pw").value;
  console.log(email);
  fetch("/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: email,
      password: password,
    }),
  })
    .then((request) => {
      localStorage.setItem(
        "Authorization",
        request.headers.get("Authorization")
      );
    })
    .catch((e) => {
      console.log("Error", e);
    });
}
const getToken = localStorage.getItem("Authorization");

function login() {
  const email = document.getElementById("login_email").value;
  const password = document.getElementById("pw").value;

  // const email = $("login_email").value; ////////////////
  // const password = $("pw").value; //////////////////////

  fetch("/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: email,
      password: password,
    }),
  }).then(function (request) {
    localStorage.setItem("Authorization", request.headers.get("Authorization"));
    sessionStorage.setItem(
      "Authorization_refresh",
      request.headers.get("Authorization_refresh")
    );
  });
  // .then((request) =>
  //   localStorage.setItem("Authorization", request.headers.get("Authorization"))
  // );
}
// function login() {
//   const email = document.getElementById("login_email").value;
//   const password = document.getElementById("pw").value;
//   fetch("/login", {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//     },
//     body: JSON.stringify({
//       email: email,
//       password: password,
//     }),
//   })
// .then((response) => {
//   console.log("autocomplete=");
//   if (!response.ok) {
//     throw new Error("Error");
//   }
// })
//     .then((request) => {
//       localStorage.setItem(
//         "Authorization",
//         request.headers.get("Authorization")
//       );
//     });
// }
//sample
// async function postData() {
//   try {
//     const response = await fetch('https://api.example.com/post', {
//       method: 'POST',
//       body: JSON.stringify({ key: 'value' }),
//       headers: {
//         'Content-Type': 'application/json',
//       },
//     });
//     const result = await response.json();
//     console.log(result);
//   } catch (error) {
//     console.error('Error:', error);
//   }
// }

// postData();

// 옜날방식! -> fetch가 요즘방식
// function last() {
//   console.log("last");
//   const xhr = new XMLHttpRequest();
//   xhr.open("POST", "/login");
//   xhr.addEventListener("loadend", (e) => {
//     console.log("e: ", e.target);
//   });
//xhr.send();
//console.log(xhr.status);
//}
