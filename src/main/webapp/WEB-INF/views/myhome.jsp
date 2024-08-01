<!DOCTYPE html>
<span style="font-family: verdana, geneva, sans-serif"
  ><!DOCTYPE html>
  <html lang="en">
    <head>
      <title>Job Dashboard | By Code Info</title>
      <link rel="stylesheet" href="css/style1.css" />
      <link rel="stylesheet" href="css/style2.css" />
      <!-- Font Awesome Cdn Link -->
      <!-- Font Awesome Cdn Link -->
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
      />
    </head>
    <body>
      <div class="container">
        <nav>
          <div class="navbar">
            <div class="logo">
              <img src="pic/logo.jpg" alt="" />
              <h1>BANK</h1>
            </div>
            <ul>
              <li>
                <a onclick="depotrans()" style="cursor: pointer">
                  <i class="fas fa-user"></i>
                  <span class="nav-item">Depo/Trans</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fas fa-chart-bar"></i>
                  <span class="nav-item">Analytics</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fas fa-tasks"></i>
                  <span class="nav-item">Jobs Board</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fab fa-dochub"></i>
                  <span class="nav-item">Documnents</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fas fa-cog"></i>
                  <span class="nav-item">Setting</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <i class="fas fa-question-circle"></i>
                  <span class="nav-item">Help</span>
                </a>
              </li>
              <li>
                <a href="#" class="logout">
                  <i class="fas fa-sign-out-alt"></i>
                  <span class="nav-item">Logout</span>
                </a>
              </li>
            </ul>
          </div>
        </nav>
        <section class="main">
          <!-- 맨위 글자 <p></p>에다가 쓰셈-->
          <div class="main-top">
            <p></p>
          </div>
          <div class="main-body">
            <h1>Recent Jobs</h1>
            <div class="search_bar">
              <input type="search" placeholder="Search job here..." />
              <select name="" id="">
                <option>Category</option>
                <option>Web Design</option>
                <option>App Design</option>
                <option>App Development</option>
              </select>
              <select class="filter">
                <option>Filter</option>
              </select>
            </div>
            <div class="tags_bar">
              <div class="tag">
                <i class="fas fa-times"></i>
                <span>Programming</span>
              </div>
              <div class="tag">
                <i class="fas fa-times"></i>
                <span>Design</span>
              </div>
              <div class="tag">
                <i class="fas fa-times"></i>
                <span>PHP</span>
              </div>
              <div class="tag">
                <i class="fas fa-times"></i>
                <span>JavaScript</span>
              </div>
            </div>
            <h1>TABLE Test.</h1>
            <table class="content-table">
              <thead>
                <tr>
                  <th>date</th>
                  <th>disposal</th>
                  <th>balance</th>
                  <th>name</th>
                </tr>
              </thead>
              <!-- private String myaccount; // 계좌
    private String opponent_account;
    private String password;

    private int transferFee; // 이체요청
    private int depositFee; // 입금
    private int withdrawalFee; // 출금
    private int balance; // 잔고

    private Date thisDate -->
              <c:set var="list" value="${list}" />
              <p>${list.name}님의 계좌내역</p>
              <tbody>
                <c:forEach var="list" items="${list}">
                  <tr>
                    <td>${list.name} ></td>
                    <td>${list.account}</td>
                    <td>${list.balance}</td>
                    <td>${list.name}</td>
                  </tr>
                </c:forEach>
                <!-- <tr class="active-row">
                  <td>1</td>
                  <td>2</td>
                  <td>3</td>
                  <td>4</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>2</td>
                  <td>3</td>
                  <td>4</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>2</td>
                  <td>3</td>
                  <td>4</td>
                </tr>
                <tr>
                  <td>1</td>
                  <td>2</td>
                  <td>3</td>
                  <td>4</td>
                </tr> -->
              </tbody>
            </table>
          </div>
        </section>
      </div>
      <script src="./js/main.js" defer></script>
      <script src="./js/depotrans.js" defer></script>
    </body></html
></span>
