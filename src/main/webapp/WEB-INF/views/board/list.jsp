<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--<fmt:parseDate value="${ board.regDate }" pattern="yyyy-MM-dd" var="parsedDateTime" type="both"/>--%>
<%--<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />--%>

<!DOCTYPE html>
<%@include file="../layouts/header.jsp" %>
<h1 class="page-header my-4"><i class="fas fa-list"></i> 글 목록${result}</h1>


<c:if test="${not empty result}">
    <p>게시글이 성공적으로 작성되었습니다. 게시글 번호: ${result}</p>
</c:if>

<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 130px">등록일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${listData}">
        <tr>
            <td>${board.no}</td>
            <td>
                <a href="get?no=${board.no}">${board.title}</a>
            </td>
            <td>${board.writer}</td>
            <td>${board.writerFirstname}</td>
            <td>${board.writerLastname}</td>
            <td>
                    <%--                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>--%>
                <fmt:parseDate value="${ board.regDate }" pattern="yyyy-MM-dd" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${ parsedDateTime }"/>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="text-right">
    <a href="create" class="btn btn-primary">
        <i class="far fa-edit"></i>
        글쓰기
    </a>
</div>
<%@include file="../layouts/footer.jsp" %>
