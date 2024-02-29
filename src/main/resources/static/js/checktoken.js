function f() {
  fetch("/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: "sysy@sy.com",
      password: "sy",
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

async function ff() {}

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
