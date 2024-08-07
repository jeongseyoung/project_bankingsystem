<!DOCTYPE html>
<span style="font-family: verdana, geneva, sans-serif"
  ><!DOCTYPE html>
  <html lang="en">
    <head>
      <title>Job Dashboard | By Code Info</title>
      <link rel="stylesheet" href="css/style1.css" />
      <link rel="stylesheet" href="css/style1_login.css" />
      <link rel="stylesheet" href="css/style2.css" />
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
                <a href="#">
                  <i class="fas fa-user"></i>
                  <span class="nav-item">My Info</span>
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
            <div class="container3">
              <h1>info.</h1>
              <br />
              <c:set var="userDto" value="${userDto}" />
              name : ${userDto.name} <br />
              account : ${userDto.account} <br />
              email : ${userDto.email} <br />
              phone : ${userDto.phone} <br />
              role : ${userDto.role}
            </div>
          </div>
        </section>
      </div>
      <script src="./js/main.js"></script>
    </body></html
></span>
