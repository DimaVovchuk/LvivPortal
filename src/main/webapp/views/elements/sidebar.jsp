<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<div id="sidebar-icon">
    <ul class="sidebar-nav">
        <li>
            <a href="#" id="menu-toggle-first"><i class="fa fa-university fa-fx"></i></a>
        </li>
        <li>
            <a href="#" id="menu-toggle-second"><i class="fa fa-university fa-fw"></i></a>
        </li>
        <li>
            <a href="#" id="menu-toggle-third"><i class="fa fa-university fa-fw"></i> </a>
        </li>
    </ul>
</div>

<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li>
            <a id="menu-first" href="#">First</a>
        </li>
        <li>
            <a id="menu-second" href="#">Second</a>
        </li>
        <li>
            <a id="menu-third" href="#">Third</a>
        </li>
    </ul>
</div>

<div id="sidebar-info">
    Information
</div>

<script>
    $("#menu-toggle-first").hover(function (e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("toggled");
        $("#menu-first").toggleClass("hovered");
    });

    $("#menu-toggle-second").hover(function (e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("toggled");
        $("#menu-second").toggleClass("hovered");
    });

    $("#menu-toggle-third").hover(function (e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("toggled");
        $("#menu-third").toggleClass("hovered");
    });
</script>