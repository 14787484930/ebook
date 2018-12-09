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
        <!-- 标题      123-->
        <div class="row">
            <div class="col-md-12">
                <h1>ebook测试页面</h1>
            </div>
        </div>

        <%--操作按钮--%>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary" id="book_add">新增</button>
                <button class="btn btn-danger" id="book_delete">删除</button>
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
                        <th>出版日期</th>
                        <th>图片</th>
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

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">图书添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">书名：</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" value="1" class="form-control" id="name" placeholder="书名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格：</label>
                        <div class="col-sm-10">
                            <input type="text" name="price" value="1" class="form-control" id="price" placeholder="价格">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">出版日期</label>
                        <div class="col-sm-10">
                            <input type="text" name="pubDate" value="2018-11-17" class="form-control" id="pubDate" placeholder="出版日期">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input type="text" name="pic" value="1" class="form-control" id="pic" placeholder="图片">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<%--修改图书--%>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">图书修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id" id="id1"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">书名：</label>
                        <div class="col-sm-10">
                            <input type="text" name="name"  class="form-control" id="name1" placeholder="书名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">价格：</label>
                        <div class="col-sm-10">
                            <input type="text" name="price" class="form-control" id="price1" placeholder="价格">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">出版日期</label>
                        <div class="col-sm-10">
                            <input type="text" name="pubDate" value="2018-11-20" class="form-control" id="pubDate1" placeholder="出版日期">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input type="text" name="pic" class="form-control" id="pic1" placeholder="图片">
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="book_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

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

    //清空表单样式及内容
  /*  function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }*/

    //点击新增按钮弹出模态框。
    $("#book_add").click(function(){
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
        reset_form("#addModal form");
        //弹出模态框
        $("#addModal").modal({
            backdrop:"static"
        });
    });

    //点击保存，保存图书
    $("#save_btn").click(function(){

        if(!validate_add_form()){
            return false;
        };

        //2、发送ajax请求保存图书
        $.ajax({
            url:"${APP_PATH}/book/save",
            type:"POST",
            data:$("#addModal form").serialize(),
            dataType:"json",
            success:function(result){
                //alert(result.msg);
                if(result.code == 100){
                    //1、关闭模态框
                    $("#addModal").modal('hide');
                    to_page(1);
                    console.log(result);
                }else{
                    //后端校验结果打印
                    console.log(result);

                }
            }
        });
    });

    //校验表单数据
    function validate_add_form(){
        //书名校验
        var name = $("#name").val();
        if(name === ""){
            show_validate_msg("#name", "error", "图书名称不能为空");
            return false;
        }else{
            show_validate_msg("#name", "success", "");
        };

        //价格校验
        var price = $("#price").val();
        if(price === ""){
            show_validate_msg("#price", "error", "价格不能为空");
            return false;
        }else{
            show_validate_msg("#price", "success", "");
        };

        //日期校验
        var pubDate = $("#pubDate").val();
        if(pubDate === ""){
            show_validate_msg("#pubDate", "error", "出版日期不能为空");
            return false;
        }else{
            show_validate_msg("#pubDate", "success", "");
        };

        var pic = $("#pic").val();
        if(pic === ""){
            show_validate_msg("#pic", "error", "图片不能为空");
            return false;
        }else{
            show_validate_msg("#pic", "success", "");
        };
        return true;
    }
    function validate_update_form(){
        //书名校验
        var name = $("#name1").val();
        if(name === ""){
            show_validate_msg("#name1", "error", "图书名称不能为空");
            return false;
        }else{
            show_validate_msg("#name1", "success", "");
        };

        //价格校验
        var price = $("#price1").val();
        if(price === ""){
            show_validate_msg("#price1", "error", "价格不能为空");
            return false;
        }else{
            show_validate_msg("#price1", "success", "");
        };

        //日期校验
        var pubDate = $("#pubDate1").val();
        if(pubDate === ""){
            show_validate_msg("#pubDate1", "error", "出版日期不能为空");
            return false;
        }else{
            show_validate_msg("#pubDate1", "success", "");
        };

        var pic = $("#pic").val();
        if(pic === ""){
            show_validate_msg("#pic1", "error", "图片不能为空");
            return false;
        }else{
            show_validate_msg("#pic1", "success", "");
        };
        return true;
    }
    function show_validate_msg(ele,status,msg){
        //清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if("success"==status){
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        }else if("error" == status){
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    //清空表单样式及内容
    function reset_form(ele){
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    $(document).on("click",".edit_btn",function(){
        //alert("edit");

        //2、查出员工信息，显示员工信息
        var a = $(this).attr("edit-id");
        //alert(a);
        getEmp($(this).attr("edit-id"));

        //3、把员工的id传递给模态框的更新按钮
        $("#book_update_btn").attr("edit-id",$(this).attr("edit-id"));
        $("#updateModal").modal({
            backdrop:"static"
        });
    });

    function getEmp(id){
        $.ajax({
            url:"${APP_PATH}/book/getById/"+id,
            type:"GET",
            success:function(result){
                console.log(result);
                var book = result.page.info;
                $("#name1").val(book.name);
                $("#price1").val(book.price);
                //$("#pubDate1").val([empData.gender]);
                $("#pic1").val(book.pic);
                $("#id1").val(book.id);
            }
        });
    }

    //点击更新
    $("#book_update_btn").click(function(){

        validate_update_form();

        $.ajax({
            url:"${APP_PATH}/book/update",
            type:"POST",
            data:$("#updateModal form").serialize(),
            success:function(result){
                //alert(result.msg);
                //1、关闭对话框
                $("#updateModal").modal("hide");
                //2、回到本页面
                to_page(1);
            }
        });
    });

    //点击删除
    $(document).on("click",".delete_btn",function(){
        //1、弹出是否确认删除对话框
        var name = $(this).parents("tr").find("td:eq(1)").text();
        var id = $(this).attr("del-id");
        alert(name);
        if(confirm("确认删除【"+name+"】吗？")){
            //确认，发送ajax请求删除即可
           $.ajax({
                url:"${APP_PATH}/book/delete/"+id,
                type:"GET",
                success:function(result){
                    alert(result.msgs.msg);
                    //回到本页
                    to_page(1);
                }
            });
        }
    });

</script>

</body>
</html>
