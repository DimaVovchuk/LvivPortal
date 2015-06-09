<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<body>

<script src="/resources/js/responsiveslides.min.js"></script>
<script>
  $(function () {
    $("#slider").responsiveSlides({
      auto: true,
      speed: 500,
      namespace: "callbacks",
      pager: false,
      nav:true,
    });
  });
</script>
<script>
  $(function() {
    $('a.page-scroll').bind('click', function(event) {
      var $anchor = $(this);
      $('html, body').stop().animate({
        scrollTop: $($anchor.attr('href')).offset().top
      }, 1500, 'easeInOutExpo');
      event.preventDefault();
    });
  });
</script>

<div class="banner">
  <div class="container">
    <div class="banner-main">
          <h3>Temporibus autem quibusdam</h3>
          <p>We are Professional web developer Company</p>
          <div class="bann-btn">
            <a href="#">Create trip</a>
            <a href="#information" class="page-scroll">Read More</a>
          </div>
    </div>
  </div>
</div>

</div>

</body>
</html>