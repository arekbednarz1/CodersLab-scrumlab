<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="views/deashboardHeader.jsp" %>
<%@include file="views/dashboardSideMenu.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding"><h3 class="color-header text-uppercase">Lista Przepisów</h3></div>
            <div class="col noPadding d-flex justify-content-end mb-2">
                <a class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4" href=<c:url value="/app/recipe/add"/>>Dodaj
                przepis</a></div>
        </div>
        <table class="table border-bottom schedules-content">
            <thead>
            <tr class="d-flex text-color-darker">
                <th scope="col" class="col-1">ID</th>
                <th scope="col" class="col-2">NAZWA</th>
                <th scope="col" class="col-7-description">OPIS</th>
                <th scope="col" class="col-2-btns center">AKCJE</th>
            </tr>
            </thead>

            <c:forEach items="${recipes}" var="recipe">
            <tbody class="text-color-lighter">
            <tr class="d-flex">
                <th scope="row" class="col-1">${recipe.id}</th>
                <td class="col-2">${recipe.name}</td>
                <td class="col-7-description">${recipe.description}</td>
                <td class="col-2-btns d-flex align-items-center justify-content-center flex-wrap">
                    <a href="/app/recipe/beforeDelete?id=${recipe.id}" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                    <a class="btn btn-info rounded-0 text-light m-1" href=<c:url
                            value="/app/recipe/details?id=${recipe.id}"/>>Szczegóły</a>
                    <a href="/app/recipe/edit?id=${recipe.id}" class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@include file="views/dashboardFooter.jsp" %>