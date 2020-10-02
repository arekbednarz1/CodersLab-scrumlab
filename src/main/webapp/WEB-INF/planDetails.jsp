<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="views/deashboardHeader.jsp"%>
<%@include file="views/dashboardSideMenu.jsp"%>

<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                        <span class="col-sm-2 label-size col-form-label">
                            Nazwa planu
                        </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${plan.name}</p>
                    </div>
                </div>
                <div class="form-group row">
                        <span class="col-sm-2 label-size col-form-label">
                            Opis planu
                        </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${plan.description}
                        </p>
                    </div>
                </div>
            </div>


            <c:if test="${not empty pon}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Poniedziałek</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${pon}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${not empty wt}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Wtorek</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${wt}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${not empty sr}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Środa</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${sr}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>


            <c:if test="${not empty czw}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Czwartek</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${czw}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>


            <c:if test="${not empty pt}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Piątek</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${pt}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>


            <c:if test="${not empty sob}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Sobota</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${sob}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>


            <c:if test="${not empty ndz}">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Niedziela</th>
                        <th class="col-7"></th>
                        <th class="col-1"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">
                    <c:forEach items="${ndz}" var="rep">
                        <tr class="d-flex">
                            <td class="col-2">${rep.mealName}</td>
                            <td class="col-7">${rep.recipeName}</td>
                            <td class="col-1 center">

                            </td>
                            <td class="col-2 center">
                                <a href="/recipeDetails?recipeId=${rep.recipeId}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>
    </div>
</div>

<%@include file="views/dashboardFooter.jsp"%>