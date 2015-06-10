<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<body>

<div class="banner">
  <div class="container">
    <div class="banner-main">
          <h3>Temporibus autem quibusdam</h3>
          <p>We are Professional web developer Company</p>
          <div class="bann-btn">
              <!--<div class="bann-btn">
                  <a href="?command=showMap">Create trip</a>
              </div>-->
              <form action="view/showMap" method="POST">
                  <input type="hidden" name="command" value="showMap">
                  <input type="submit" value="Create trip">
              </form>
          </div>
    </div>
  </div>
</div>

</div>

</body>
</html>