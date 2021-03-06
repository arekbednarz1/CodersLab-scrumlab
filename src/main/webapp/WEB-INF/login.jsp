<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="fragments/header.jsp"%>

<div class="border-dashed view-height">
    <div class="container w-25">
        <form class="padding-small text-center" action="/login" method="post">
            <h1 class="text-color-darker">Logowanie</h1>
            <div class="form-group">
                <input type="text" class="form-control" id="email" name="email" placeholder="podaj adres email">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="podaj hasło">
            </div>
            <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
            <p>${wrong}</p>
        </form>
    </div>
</div>

<%@ include file="fragments/footer.jsp"%>