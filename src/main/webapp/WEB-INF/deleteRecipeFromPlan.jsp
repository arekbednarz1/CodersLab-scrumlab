<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/deashboardHeader.jsp"%>
<%@include file="fragments/dashboardSideMenu.jsp"%>
<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-3">

                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col-9 noPadding">
                        <h3 class="color-header text-uppercase">Czy na pewno chcesz usunąć ten przepis z planu?</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <form method="post">
                            <input type="hidden" name="recipePlanId" value="${recipePlanId}">
                            <input type="hidden" name="planId" value="${planId}">
                            <input type="submit" class="btn btn-warning rounded-0 text-light m-1" value="Tak"/>
                            <a href="<c:url value='/app/plan/details?planId=${planId}' />" class="btn btn-warning rounded-0 text-light m-1">Anuluj</a>
                        </form>
                    </div>
                </div>

                <table class="table borderless">
                    <tbody>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Nazwa planu:</th>
                        <td class="col-7">${planName}</td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Nazwa przepisu</th>
                        <td class="col-7">${recipeName}</td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

<%@include file="fragments/dashboardFooter.jsp"%>
