<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="views/deashboardHeader.jsp"%>
<%@include file="views/dashboardSideMenu.jsp"%>

<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href="">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis do planu</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Liczba przepisów: ${numOfRecipes}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Liczba planów: ${numOfPlans}</span>
            </div>
        </div>
    </div>

    <%-- Show last plan schedule only if it exists --%>
    <c:if test="${not empty lastPlan.name}">

        <div class="m-4 p-4 border-dashed">
            <h2 class="dashboard-content-title">
                <span>Ostatnio dodany plan:</span> ${lastPlan.name}
            </h2>

            <%-- Show weekly schedule for last plan --%>
            <c:forEach var="day" items="${lastDetailedPlan}">
                <%-- List same day only once --%>
                <c:if test="${day[0] != dayAlreadyExists}">
                    <c:set var="dayAlreadyExists" value="${day[0]}" />
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2 week-day">${day[0]}</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>
                </c:if>
                        <tr class="d-flex">
                            <td class="col-2">${day[1]}</td>
                            <td class="col-8">${day[2]}</td>
                            <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                        </tr>
                <%-- close table if next day is different than previous --%>
                <c:if test="${day[0] != dayAlreadyExists}">
                        </tbody>
                    </table>
                </c:if>
            </c:forEach>

        </div>
    </c:if>
</div>

<%@include file="views/dashboardFooter.jsp"%>