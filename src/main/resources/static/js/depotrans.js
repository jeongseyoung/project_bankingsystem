//입출금페이지로 이동
function transfer() {
  const opponent_account = document.getElementById("opponent_account").value;
  const transferFee = document.getElementById("transferFee").value;
  const password = document.getElementById("password").value;
  const token = localStorage.getItem("Authorization");
  console.log(token);
  fetch("/transfer", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      accessToken: token,
      opponent_account: opponent_account,
      transferFee: transferFee,
      password: password,
    }),
  })
    .then(alert("이체성공"))
    .then((window.location.href = "/myhome")); //200일때 alert로 바꾸기;
}
function tokenfetch() {
  //const res = new Response();
  const opponent_account = document.getElementById("opponent_account").value;
  const transferFee = document.getElementById("transferFee").value;
  const password = document.getElementById("password").value;
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
      opponent_account: opponent_account,
      transferFee: transferFee,
      password: password,
    }),
  });
  //.then((response) => response.headers.set("Authorization", token))
  //.then(() => (window.location.href = "/tokentest"));
}

function depotrans() {
  window.location.href = "/depotrans";
}
