<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav flex-column long-bg">
    <li class="nav-item">
        <a class="nav-link" href="<c:url value='/app' />">
            <span>Pulpit</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value='/app/recipe/list/' />">
            <span>Przepisy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value='/app/schedules' />">
            <span>Plany</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/app-edit-user-data.html">
            <span>Edytuj dane</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled" href="/app-edit-password.html">
            <span>Zmień hasło</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/super-admin-users.html">
            <span>Użytkownicy</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="<c:url value='/logout' />">
            <span>Wyloguj</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
</ul>