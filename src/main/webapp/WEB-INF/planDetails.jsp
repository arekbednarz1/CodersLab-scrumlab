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

            <%-- Show weekly schedule for plan --%>
            <c:forEach var="day" items="${detailedPlan}">
                <%-- List same day only once --%>
                <c:if test="${day[0] != dayAlreadyExists}">
                    <c:set var="dayAlreadyExists" value="${day[0]}" />
            <table class="table">
                <thead>
                    <tr class="d-flex">
                        <th class="col-2 week-day">${day[0]}</th>
                        <th class="col-7"></th>
                        <th class="col-2-btns"></th>
                    </tr>
                </thead>
                <tbody class="text-color-lighter">
                </c:if>
                <tr class="d-flex">
                    <td class="col-2">${day[1]}</td>
                    <td class="col-7">${day[2]}</td>
                    <td class="col-2-btns">
                        <a href="<c:url value="/app/recipe/plan/delete?id=${day[4]}&planId=${planId}" />" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                        <a href="<c:url value="/app/recipe/details?id=${day[3]}" />" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                    </td>
                </tr>
                <%-- close table if next day is different than previous --%>
                <c:if test="${day[0] != dayAlreadyExists}">
                </tbody>
            </table>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="views/dashboardFooter.jsp"%>