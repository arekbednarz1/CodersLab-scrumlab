<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/deashboardHeader.jsp"%>
<%@include file="fragments/dashboardSideMenu.jsp"%>
<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">Czy na pewno chcesz usunąć ten przepis?</h3></div>
                <div class="col d-flex justify-content-end mb-2">
                    <a href="/app/recipe/delete?id=${showDetails.id}" class="btn btn-warning rounded-0 text-light m-1">Ok</a>
                    <a href="/app/recipe/list/" class="btn btn-warning rounded-0 text-light m-1">Anuluj</a></div>
            </div>
                <table class="table borderless">
                    <tbody>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Nazwa Przepisu</th>
                        <td class="col-7">${showDetails.name}</td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Opis przepisu</th>
                        <td class="col-7">${showDetails.description}</td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                        <td class="col-7">
                            ${showDetails.preparation_time}
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="row d-flex">
                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                    <div class="col-2"></div>
                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                </div>
                <div class="row d-flex">
                    <div class="col-5 p-4">
                        <p>${showDetails.preparation}</p>
                    </div>
                    <div class="col-2"></div>
                    <ul class="col-5 p-4 list-unstyled">
                        <c:forEach var="ingredients" items="${showDetails.ingredients}">

                            <p>${ingredients}</p>
                        </c:forEach>
                    </ul>
                </div>
        </div>
    </div>
</div>


<%@include file="fragments/dashboardFooter.jsp"%>
