// sign up + login 화면전환
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

// 로그인 fetch(비동기처리)
function login() {
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
      //return request;
    })
    .then(() => (window.location.href = "/myhome"));
}

// const getToken = localStorage.getItem("Authorization");

// form 하고 버튼(submit)하고 같이 쓰면 안됨.

// 통신은 다른 로직에 비해 오래 걸리기 때문에 비동기 처리돼서 then 메서드를 사용
