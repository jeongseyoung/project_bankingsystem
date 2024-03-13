function test() {
  const email = document.getElementById("login_email").value;
  const password = document.getElementById("pw").value;
  console.log(email);
  fetch("/login", {
    method: "GET",
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

function f() {
  const email = document.getElementById("login_email").value;
  const password = document.getElementById("pw").value;

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

function tt() {
  console.log("tt");
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

function plz() {
  const email = document.getElementById("login_email").value;
  const password = document.getElementById("pw").value;
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
      //로컬저장소에 accesstoken저장
      localStorage.setItem(
        "Authorization",
        request.headers.get("Authorization")
      );
      // 세션에 refreshtoken저장
      sessionStorage.setItem(
        "Authorization_refresh",
        request.headers.get("Authorization_refresh")
      );
    })
    .finally(console.log("ddd"));
  //.then(() => (window.location.href = "/myhome"));
}

function tokenheader() {
  const res = new Response();
  //let req = new Request(init);
  const token = localStorage.getItem("Authorization");
  res.headers.set("Authorization", token);
  console.log(res.redirected, token);
  window.location.href = "/tokentest";
  // let a = req.headers.set(
  //   "Authorization",
  //   localStorage.getItem("Authorization")
  // );
  //console.log("a: ", a);
}

function tokenfetch() {
  //const res = new Response();
  const token = localStorage.getItem("Authorization");
  console.log(token);
  //res.headers.set("Authorization", token);
  fetch("/tokentest", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      accessToken: token,
    }),
  });
  //.then((response) => response.headers.set("Authorization", token))
  //.then(() => (window.location.href = "/tokentest"));
}
function tokensession() {
  const token = localStorage.getItem("Authorization");
  //sessionStorage.getItem("Authorization_Refresh");
  console.log(token);
  //window.location.href = "/tokentest";
}
// var req = new Request(request.url, {
//   method: request.method,
//   headers: request.headers,
//   mode: "same-origin", // need to set this properly
//   credentials: request.credentials,
//   redirect: "manual", // let browser handle redirects
// });
