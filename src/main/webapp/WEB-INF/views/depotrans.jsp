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
              <h1>jobs</h1>
            </div>
            <ul>
              <li>
                <a href="#">
                  <i class="fas fa-user"></i>
                  <span class="nav-item"
                    >Depo/Trans</span>                
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
            <div class="container2">
              <div class="form form--hidden" id="login">
                <h1 class="form__title">Login</h1>
                <div class="form__message form__message--error">
                  Incorrect username/password combination.
                </div>
                <div class="form__input-group">
                  <input
                    type="text"
                    id="login_email"
                    name="email"
                    class="form__input"
                    autofocus
                    placeholder="email"
                  />
                  <div class="form__input-error-message">
                    This is an Error Message.
                  </div>
                </div>
                <div class="form__input-group">
                  <input
                    type="password"
                    id="pw"
                    name="password"
                    class="form__input"
                    autocomplete="on"
                    autofocus
                    placeholder="password"
                  />
                  <div class="form__input-error-message"></div>
                </div>
                <button class="form__button" type="submit" onclick="login()">
                  Login
                </button>
                <p class="form__text">
                  <a href="./" class="form__link">forgot your pw?</a>
                </p>
                <p class="form__text">
                  <a class="form__link" href="./" id="linkCreateAccount"
                    >sign up</a
                  >
                </p>
              </div>
               <div
                class="form"
                id="createAccount"
              > 
                <h1 class="form__title">TRANSFER</h1>
                <!-- <div class="form__message form__message--error"></div> -->
                <div class="form__input-group">
                    <!--이체하기-->
                  <input
                    name="opponent_account"
                    id="opponent_account"
                    type="text"
                    class="form__input"
                    autofocus
                    placeholder="opponent account?"
                  />
                  <!-- <div class="form__input-error-message"></div> -->
                </div>
                <div class="form__input-group">
                  <input
                    type="number"
                    name="transferFee"
                    id="transferFee"
                    class="form__input"
                    autocomplete="on"
                    autofocus
                    placeholder="transferFee"
                  />
                </div>
                <div class="form__input-group">
                    <input
                      type="password"
                      name="password"
                      id="password"
                      class="form__input"
                      autocomplete="on"
                      autofocus
                      placeholder="password"
                    />
                    <!-- <div class="form__input-error-message"></div> -->
                  </div><br>
                  <button onclick="transfer()">transfer</button>
                <!-- <input type="submit" class="form__button" value="submit" /> -->

                <!-- <p class="form__text">
                  <a class="form__link" href="./" id="linkLogin"
                    >Already have an Account? Sign in!!</a
                  > -->
                </p>
              </div>
            </div>
          </div>
        </section>
      </div>
      <script src="./js/depotrans.js" defer></script>
    </body></html
></span>
