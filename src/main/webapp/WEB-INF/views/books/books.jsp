<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>books</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>

<center>
    <div class="container">
        <!-- 标题 -->
        <div class="row">
            <div class="col-md-12">
                <h1>ebook测试页面</h1>
            </div>
        </div>
        <%--table--%>
       <div class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <tr>
                        <th>书名</th>
                        <th>价格</th>
                        <th>图片</th>
                        <th>出版日期</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageinfo.list }" var="book">
                        <tr>
                            <th>${book.name }</th>
                            <th>${book.price }</th>
                            <th>${book.pic }</th>
                            <th>${book.pubDate}</th>
                            <th>
                                <button class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    编辑
                                </button>
                                <button class="btn btn-danger btn-sm">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    删除
                                </button>
                            </th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <%--分页信息--%>
        <div class="row">
            <!--分页文字信息  -->
            <div class="col-md-6">当前 ${pageinfo.pageNum }页,总${pageinfo.pages }
                页,总 ${pageinfo.total } 条记录
            </div>
            <!-- 分页条信息 -->
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li><a href="${APP_PATH }/book/books?pn=1">首页</a></li>
                        <c:if test="${pageinfo.hasPreviousPage }">
                            <li><a href="${APP_PATH }/book/books?pn=${pageinfo.pageNum-1}"
                                   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                            </a></li>
                        </c:if>

                        <c:forEach items="${pageinfo.navigatepageNums }" var="page_Num">
                            <c:if test="${page_Num == pageinfo.pageNum }">
                                <li class="active"><a href="#">${page_Num }</a></li>
                            </c:if>
                            <c:if test="${page_Num != pageinfo.pageNum }">
                                <li><a href="${APP_PATH }/book/books?pn=${page_Num }">${page_Num }</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageinfo.hasNextPage }">
                            <li><a href="${APP_PATH }/book/books?pn=${pageinfo.pageNum+1 }"
                                   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                            </a></li>
                        </c:if>
                        <li><a href="${APP_PATH }/book/books?pn=${pageinfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>

    </div>
</center>

</body>
</html>
