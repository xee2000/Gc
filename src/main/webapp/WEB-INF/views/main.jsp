<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Your head content goes here -->
</head>
<body>
    <!-- Your body content goes here -->

    <div>
        <c:if test="${not empty map}">
            <c:choose>
                <c:when test="${not empty map.map}">
                    <c:set var="sensor" value="${map.map}" />
                    <c:out value="device_num: ${sensor.device_number}" /><br/>
                    <c:out value="measure_time: ${sensor.alarm}" /><br/>
                </c:when>
                <c:otherwise>
                    <!-- Handle the case when the map is empty -->
                    The map is empty.
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>

    <!-- Your other HTML content goes here -->
</body>
</html>