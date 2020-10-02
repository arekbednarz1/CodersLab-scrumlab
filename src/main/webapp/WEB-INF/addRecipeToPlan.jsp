<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="views/deashboardHeader.jsp"%>
<%@include file="views/dashboardSideMenu.jsp"%>
<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">

        <form action="<c:url value="/app/recipe/plan/add" />" method="post">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                </div>
                <div class="col d-flex justify-content-end mb-2 noPadding">
                    <button type="submit" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                </div>
            </div>
            <div class="schedules-content">
                <div class="form-group row">
                    <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                        Wybierz plan
                    </label>
                    <div class="col-sm-3">
                        <select class="form-control" name="choosePlan" id="choosePlan">
                            <c:forEach var="plan" items="${plansList}">
                            <option value="${plan.id}">${plan.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 label-size col-form-label">
                        Nazwa posiłku
                    </label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="mealName" placeholder="Nazwa posiłku, np. śniadanie">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="number" class="col-sm-2 label-size col-form-label">
                        Numer posiłku
                    </label>
                    <div class="col-sm-2">
                        <input type="number" step="1" class="form-control" id="number" name="displayOrder" placeholder="Numer posiłku">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="recipe" class="col-sm-2 label-size col-form-label">
                        Przepis
                    </label>
                    <div class="col-sm-4">
                        <select class="form-control" id="recipe" name="recipe">
                            <c:forEach var="recipe" items="${recipesList}">
                            <option value="${recipe.id}">${recipe.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="day" class="col-sm-2 label-size col-form-label">
                        Dzień
                    </label>
                    <div class="col-sm-2">
                        <select class="form-control" id="day" name="day">
                            <c:forEach var="day" items="${dayList}">
                                <option value="${day.id}">${day.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <c:if test="${not empty infoField}"><h4 class="center">${infoField}</h4></c:if>
            </div>
        </form>
    </div>
</div>

<%@include file="views/dashboardFooter.jsp"%>