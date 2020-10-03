<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="fragments/deashboardHeader.jsp" %>
<%@include file="fragments/dashboardSideMenu.jsp" %>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="m-4 border-dashed view-height">
                <div class="mt-4 ml-4 mr-4">
                    <!-- fix action, method -->
                    <!-- add name attribute for all inputs -->
                    <form action="/app/editPassword" method="post">
                        <input type="hidden" name="id" value="${admin.id}">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Zmień hasło</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                                </button>
                            </div>
                        </div>

                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>Nowe hasło</h4></th>
                                <td class="col-7">
                                    <input class="w-100 p-1" id="password" name="password" type="password" value="">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>Powtórz hasło</h4></th>
                                <td class="col-7">
                                    <input class="w-100 p-1" id="repassword" name="repassword" type="password" value="">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>


<%@include file="fragments/dashboardFooter.jsp" %>
