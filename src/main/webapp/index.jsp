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
                <table class="table table-hover" id="books_table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>书名</th>
                        <th>价格</th>
                        <th>图片</th>
                        <th>出版日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>

        <%--分页信息--%>
        <div class="row">
            <!--分页文字信息  -->
            <div class="col-md-6" id="page_info_area"></div>
            <!-- 分页条信息 -->
            <div class="col-md-6" id="page_nav_area">

            </div>
        </div>

    </div>
</center>
<script type="text/javascript">

    var totalRecord,currentPage;
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function(){
        //去首页
        to_page(1);
    });

    function to_page(pn){
        $.ajax({
            url:"${APP_PATH}/book/books",
            data:"pn="+pn,
            type:"GET",
            success:function(result){
                //console.log(result);
                //1、解析并显示员工数据
                build_emps_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
            }
        });
    }

    function build_emps_table(result){
        //清空table表格
        $("#books_table tbody").empty();
        var books = result.page.pageInfo.list;
        $.each(books,function(index,book){
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
            var bookName = $("<td></td>").append(book.name);
            var bookPrice = $("<td></td>").append(book.price);
            var bookPubDate = $("<td></td>").append(book.pubDate);
            var bookPic = $("<td></td>").append(book.pic);

            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前员工id
            editBtn.attr("edit-id",book.id);

            var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的员工id
            delBtn.attr("del-id",book.id);

            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);

            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>").append(checkBoxTd)
                .append(bookName)
                .append(bookPrice)
                .append(bookPrice)
                .append(bookPubDate)
                .append(bookPic)
                .append(btnTd)
                .appendTo("#books_table tbody");
        });
    }
    //解析显示分页信息
     function build_page_info(result){
         $("#page_info_area").empty();
         $("#page_info_area").append("当前"+result.page.pageInfo.pageNum+"页,总"+
             result.page.pageInfo.pages+"页,总"+
             result.page.pageInfo.total+"条记录");
         totalRecord = result.page.pageInfo.total;
         currentPage = result.page.pageInfo.pageNum;
     }
     //解析显示分页条，点击分页要能去下一页....
     function build_page_nav(result){
         //page_nav_area
         $("#page_nav_area").empty();
         var ul = $("<ul></ul>").addClass("pagination");

         //构建元素
         var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
         var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
         if(result.page.pageInfo.hasPreviousPage == false){
             firstPageLi.addClass("disabled");
             prePageLi.addClass("disabled");
         }else{
             //为元素添加点击翻页的事件
             firstPageLi.click(function(){
                 to_page(1);
             });
             prePageLi.click(function(){
                 to_page(result.page.pageInfo.pageNum -1);
             });
         }



         var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
         var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
         if(result.page.pageInfo.hasNextPage == false){
             nextPageLi.addClass("disabled");
             lastPageLi.addClass("disabled");
         }else{
             nextPageLi.click(function(){
                 to_page(result.page.pageInfo.pageNum +1);
             });
             lastPageLi.click(function(){
                 to_page(result.page.pageInfo.pages);
             });
         }



         //添加首页和前一页 的提示
         ul.append(firstPageLi).append(prePageLi);
         //1,2，3遍历给ul中添加页码提示
         $.each(result.page.pageInfo.navigatepageNums,function(index,item){

             var numLi = $("<li></li>").append($("<a></a>").append(item));
             if(result.page.pageInfo.pageNum == item){
                 numLi.addClass("active");
             }
             numLi.click(function(){
                 to_page(item);
             });
             ul.append(numLi);
         });
         //添加下一页和末页 的提示
         ul.append(nextPageLi).append(lastPageLi);

         //把ul加入到nav
         var navEle = $("<nav></nav>").append(ul);
         navEle.appendTo("#page_nav_area");
     }


</script>

</body>
</html>
