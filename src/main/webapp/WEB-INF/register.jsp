<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="views/header.jsp"%>

<div class="border-dashed view-height">
    <div class="container w-25">
        <!-- fix action, method -->
        <!-- add name attribute for all inputs -->
        <form class="padding-small text-center" action="/register" method="post">
            <h1 class="text-color-darker">Rejestracja</h1>
            <div class="form-group">
                <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="surname" name="surname" placeholder="podaj nazwisko">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="podaj email">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="podaj hasło">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="repassword" name="password" placeholder="powtórz hasło">
            </div>
            <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
            <c:if test="${not empty wrong}"><p>${wrong}</p></c:if>
            <c:if test="${not empty all}"><p>${all}</p></c:if>
            <c:if test="${not empty userExists}"><p>${userExists}</p></c:if>

        </form>

    </div>
</div>

<%@include file="views/footer.jsp"%>


