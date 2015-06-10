<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>

<div class="header">
    <div class="container">
        <div class="header-main">
            <div class="logo">
                <a href="view?command=index"> <img src="/images/logo.png" alt="" title=""> </a>
            </div>
            <div class="head-right">
                <div class="top-nav">
                    <span class="menu"> <img src="/images/icon.png" alt=""/></span>
                    <ul class="res">
                        <li><a href="view?command=index"><cdg:l18n key="header.home"/></a></li>
                        <li><a href="/views/pages/index.jsp#places"><cdg:l18n key="header.places"/></a></li>
                        <li><a href="http://www.booking.com"><cdg:l18n key="header.hotels"/></a></li>
                        <li><a href="/views/pages/map.jsp"><cdg:l18n key="header.plan"/></a></li>
                        <li><a href="#" id="login"><cdg:l18n key="header.sign"/></a></li>
                    </ul>
                    <!-- script-for-menu -->
                    <script>
                        $( "span.menu" ).click(function() {
                            $( "ul.res" ).slideToggle( 300, function() {
                            });
                        });
                    </script>
                </div>

                <div class="local">
                    <ul >
                        <li><a href="/local?lang=EN"><img src="/images/localization/EN.png"></a></li>
                        <li><a href="/local?lang=UA"><img src="/images/localization/UA.png"></a></li>
                    </ul>
                </div>

                <div class="search-box">
                    <div id="sb-search" class="sb-search">
                        <form>
                            <input class="sb-search-input" placeholder='Search' type="search" name="search" id="search">
                            <input class="sb-search-submit" type="submit" value="">
                            <span class="sb-icon-search"> </span>
                        </form>
                    </div>
                </div>
                <div class="clearfix"> </div>
                <!-- search-scripts -->
                <script src="/js/classie.js"></script>
                <script src="/js/uisearch.js"></script>
                <script>
                    new UISearch( document.getElementById( 'sb-search' ) );
                </script>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
