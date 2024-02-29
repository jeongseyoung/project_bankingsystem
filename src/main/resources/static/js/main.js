document.addEventListener("DOMContentLoaded", () => {
  //console.log("token: ", Request.prototype.headers);

  const loginForm = document.querySelector("#login");
  const createAccountForm = document.querySelector("#createAccount");
  document
    .querySelector("#linkCreateAccount")
    .addEventListener("click", (e) => {
      e.preventDefault();
      loginForm.classList.add("form--hidden");
      createAccountForm.classList.remove("form--hidden");
    });

  document.querySelector("#linkLogin").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.remove("form--hidden");
    createAccountForm.classList.add("form--hidden");
  });
});

// const test = document.querySelector(".form__button");
// test.addEventListener("click", () => {
//   const name = document.getElementById("username");
//   console.log("username: ", username);
// });
function c() {
  const name = document.getElementById("#username");
  console.log("username: ", name);
}

function login() {
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
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error");
      }
    })
    .then((request) => {
      localStorage.setItem(
        "Authorization",
        request.headers.get("Authorization")
      );
    });
}
const getToken = localStorage.getItem("Authorization");
//async await fetch로도 할 수 있음.
// refreshtoken
