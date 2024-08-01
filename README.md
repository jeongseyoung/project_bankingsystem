트랜잭션을 이용한 은행 입출금 기능 구현<br><br>

환경<br>
- VsCode
- git, github

Tools<br>
- SpringBoot Framework
- SpringSecurity
- JAVA
- JAVASCRIPT
- MySQL
- Bcrypt, JWT
- HTML, Css
- LOMBOK, JPA <br>




1. 회원가입​<br>
​간단한 정보를 기입하여 회원가입을 하게되면 계좌번호 발급하고 회원정보와 계좌번호를 DB에 저장합니다.<br>
사용자의 비밀번호는 Bcrypt를 사용하여 암호화하여 DB에 저장합니다. ​​<br><br>

2. 로그인<br>
ㄱ. Spring Security와 JWT를 연동하여 로그인 기능과 권한에 따른 접근 제어를 구현하였습니다. 사용자가 로그인하면 JwtManager 클래스에서 토큰 생성, 생성된 토큰에서 사용자 이름 및 권한 추출,<br>
토큰 유효성 검사 등을 진행하고, AuthContoller에서 로그인 요청을 처리합니다.SecurityConfig에서 Spring Security 설정을 구성하고, 특정 경로에 대한 접근 권한을 설정합니다.<br>
ㄴ. 사용자가 로그인하면 Access Token과 Refresh Token이 생성됩니다. Access Token은 이후 Http의 Authorization 헤더를 사용하여 각종 인증을 수행합니다.<br>
Refresh Token은 Access Token이 만료되었을 때 새로운 토큰 발급에 사용됩니다. 토큰은 JSON Web Token(JWT)을 사용합니다.<br><br>  
​​
3. 입금 / 출금<br>
​사용자 A가 사용자 B의 계좌에 입금한다고 가정하면,<br>
먼저 은행 메인DB에서 사용자 A의 잔고가 요청한 금액보다 많은지 확인합니다. 사용자 A 계좌에서 요청된 금액을 차감한 후 사용자 B의 잔고를 증가시킵니다. 이 과정이 모두 완료되어야 모든 DB에 결과가 반영됩니다. <br><br>​​

4. 트랜잭션 활용<br>
​만약 위의 과정 중에 오류가 발생했다면 특정 DB의 상태만 변경될 수 있습니다. 이러한 상황을 방지하기 위해 트랜잭션이 사용됩니다.<br>
트랜잭션의 특성 중 하나는 원자성으로, 모든 결과가 반영되거나 전혀 반영되지 않도록 보장합니다. 이를 확인하기 위해 계좌번호를 틀리게 입력하는 등의 의도적인 오류를 발생시켜 실제 트랜잭션의 동작 여부를 직접 확인했습니다. (롤백)<br>
또한, 정상적으로 실행된 후 결과가 영구적으로 반영되는 것도 확인했습니다. (커밋)​​<br><br>

   
  

​
