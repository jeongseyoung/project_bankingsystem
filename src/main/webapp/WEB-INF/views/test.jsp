<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Page Title</title>
  </head>
  <body>
    <form action="<%=request.getContextPath()%>/test" method="post">
      <input type="submit" />
    </form>
    <br />
    <div class="form__input-group">
      <input
        type="text"
        id="login_email"
        name="email"
        class="form__input"
        autofocus
        placeholder="email"
      />
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
    <button class="form__button" type="submit" onclick="plz()">button</button
    ><br />
    <button onclick="tokenheader()">TOKEN_1</button><br />
    <button onclick="tokenfetch()">TOKEN_2</button><br />
    <button onclick="tokensession()">TOKEN_3</button><br />
    <script src="./js/test.js" defer></script>
  </body>
</html>
