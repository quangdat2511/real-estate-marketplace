<%@include file="/common/taglib.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27/4/2025
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content" style="font-family: 'Times New Roman', Times, serif;">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
        </div><!-- /.page-content -->
        <div class="row">
            <div class="col-xs-12">
                <form:form class="form-horizontal" role="form" id="form-edit" action="/admin/customer-edit" method="GET" modelAttribute="customerEdit">
<%--                    <div class="form-group">--%>
<%--                        <div class="col-xs-offset-3 col-xs-9">--%>
<%--                            <p style="color: red; font-style: italic; margin-top: 0;">Các trường có tiêu đề màu đỏ là bắt buộc</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Tên khách hàng</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="fullName"/>
                            <span class="error-message" style="color:red" id="fullNameError"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Số điện thoại</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="phone"/>
                            <span class="error-message" style="color:red" id="phoneError"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Email</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Tên công ty</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="companyName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Nhu cầu</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="demand"/>
                            <span class="error-message" style="color:red" id="demandError"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Trạng thái xử lý</label>
                        <div class="col-xs-2">
                            <form:select path="status" class="form-control">
                                <form:options items="${status}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label"></label>
                        <div class="col-xs-9">
                                <c:choose>
                                    <c:when test="${not empty customerEdit.id}">
                                        <button type="button" class="btn btn-warning" id="btnAddOrUpdateCustomer">
                                            Cập nhật thông tin
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">
                                            Thêm khách hàng
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                                <a href="/admin/customer-list">
                                    <button type="button" class="btn btn-danger">
                                        Hủy thao tác
                                    </button>
                                </a>
                        </div>
                    </div>
                    <input type="hidden" id="id" value="${customerEdit.id}"/>
                </form:form>

            </div>
        </div>
<%--        <h1>Chăm sóc khách hàng</h1>--%>
        <div class="col-xs-12">
            <h2 class="smaller lighter blue">
                Chăm sóc khách hàng
                <button class="btn btn-md btn-success pull-right" title="Thêm giao dịch" onclick="openCreateTransactionModal('CSKH', '${customerEdit.id}')">
                    <i class="ace-icon glyphicon glyphicon-plus smaller-80">Thêm giao dịch</i>
                </button>
            </h2>
            <div class="hr hr-16 dotted hr-dotted"></div>
        </div>
        <div class="row">
                <div class="col-xs-12">
                    <table id="CXKHList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="transaction" items="${CSKH}">
                                <tr id="cskh-row-${transaction.id}">

                                    <td>${transaction.createdDate}</td>
                                    <td>${transaction.createdBy}</td>
                                    <td>${transaction.modifiedDate}</td>
                                    <td>${transaction.modifiedBy}</td>
                                    <td>${transaction.note}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success" onclick="openEditTransactionModal('${transaction.id}', '${customerEdit.id}', '${transaction.note}', '${transaction.transactionName}')" title="Chỉnh sửa giao dịch">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </button>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button class="btn btn-xs btn-danger" title="xóa giao dịch">
                                                    <i class="ace-icon fa fa-trash-o bigger-120" onclick="deleteTransaction(${transaction.id})"></i>
                                                </button>
                                            </security:authorize>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                </div><!-- /.span -->
        </div>
        <div class="col-xs-12">
            <h2 class="smaller lighter blue">
                Dẫn đi xem
                <button class="btn btn-md btn-success pull-right" title="Thêm giao dịch" onclick="openCreateTransactionModal('DDX', '${customerEdit.id}')">
                    <i class="ace-icon glyphicon glyphicon-plus smaller-80">Thêm giao dịch</i>
                </button>
            </h2>
            <div class="hr hr-16 dotted hr-dotted"></div>
        </div>
        <div class="row">
                <div class="col-xs-12">
                    <table id="DDXList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="transaction" items="${DDX}">
                                <tr id="ddx-row-${transaction.id}">

                                    <td>${transaction.createdDate}</td>
                                    <td>${transaction.createdBy}</td>
                                    <td>${transaction.modifiedDate}</td>
                                    <td>${transaction.modifiedBy}</td>
                                    <td>${transaction.note}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success" onclick="openEditTransactionModal('${transaction.id}', '${customerEdit.id}', '${transaction.note}', '${transaction.transactionName}')" title="Chỉnh sửa giao dịch">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </button>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button class="btn btn-xs btn-danger" title="xóa giao dịch">
                                                    <i class="ace-icon fa fa-trash-o bigger-120" onclick="deleteTransaction(${transaction.id})"></i>
                                                </button>
                                            </security:authorize>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                </div><!-- /.span -->
        </div>
        <div class="modal fade" id="editTransactionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true" style="font-family:'Times New Roman', Times, serif">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Nhập thông tin giao dịch</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Chi tiết giao dịch</label>
                            <div class="col-sm-9">
                                <input id="note" type="text" class="form-control" placeholder="Nhập chi tiết giao dịch"/>
                                <span class="error-message" style="color:red" id="noteError"></span>
                            </div>
                        </div>
                        <input type="hidden" id="customerId" value=""/>
                        <input type="hidden" id="code" value=""/>
                        <input type="hidden" id="transactionId" value=""/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateTransaction">Xác nhận</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->
<script>
    var ok = 1;
    function validateDataCustomer(json){
        $('.error-message').html('');
        if (json['name'] === '') {
            ok = 0;
            $('#fullNameError').html('Tên khách hàng không được trống')
        }
        if (json['phone'] === '') {
            ok = 0;
            $('#phoneError').html('Số điện thoại không được trống')
        }
        if (json['demand'] === '') {
            ok = 0;
            $('#demandError').html('Nhu cầu không được trống')
        }
    }

    $('#btnAddOrUpdateCustomer').click(function(e){
        e.preventDefault();
        var formData = $('#form-edit').serializeArray();
        var json = {};
        $.each(formData, function(i, it){
            json["" + it.name + ""] = it.value;
        })
        json['id'] = $('#id').val();
        ok = 1;
        validateDataCustomer(json);
        if (ok === 0){
            alert('Vui lòng điền đầy đủ các trường bắt buộc trước khi tiếp tục.');
        }
        else{
            if (json['id'] === ''){
                addCustomer(json);
            }
            else{
                updateCustomer(json);
            }
        }
    });
    function addCustomer(json){
        $.ajax({
            type : "POST",
            url: "/api/customers",
            data: JSON.stringify(json),
            dataType: "json",
            contentType : "application/json",
            success: function(response){
                alert(response.message);
                window.location.href = "/admin/customer-list";
            },
            error: function(response){
                const res = response.responseJSON;
                if (Array.isArray(res.data)) {
                    alert(res.data.join('\n'));
                } else {
                    alert(res.message || "Something went wrong");
                }
            }
        });
    }
    function updateCustomer(json){
        $.ajax({
            type : "PUT",
            url: "/api/customers",
            data: JSON.stringify(json),
            dataType: "json",
            contentType : "application/json",
            success: function(response){
                alert(response.message);
                window.location.href = "/admin/customer-list";
            },
            error: function(response){
                const res = response.responseJSON;
                if (Array.isArray(res.data)) {
                    alert(res.data.join('\n'));
                } else {
                    alert(res.message || "Something went wrong");
                }
            }
        });
    }
    function openCreateTransactionModal(code, customerId) {
        $('#editTransactionModal').modal();
        $('#transactionId').val('');
        $('#customerId').val(customerId);
        $('#note').val('');
        $('#code').val(code);
        $('#noteError').text('');
    }
    function openEditTransactionModal(transactionId, customerId, note, code) {
        $('#editTransactionModal').modal();
        $('#transactionId').val(transactionId);
        $('#customerId').val(customerId);
        $('#note').val(note);
        $('#code').val(code);
        $('#noteError').text('');
    }
    $('#btnAddOrUpdateTransaction').click(function(e) {
        e.preventDefault();
        var json = {};
        json['customerId'] = $('#customerId').val();
        json['code'] = $('#code').val();
        json['note'] = $('#note').val();
        json['id'] = $('#transactionId').val();
        if(json['note'] === '') {
            $('#noteError').html('Chi tiết giao dịch không được trống')
        }
        else {
            if (json.id === '') {
                createTransaction(json);
            } else {
                updateTransaction(json);
            }
        }
    });
    function createTransaction(json) {
        // Gui request xuong server
        $.ajax({
            type : "POST",
            url: "/api/transactions/",
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                console.log('success');
                location.reload();
                alert(response.message);
            },
            error: function(response) {
                console.log('Failed');
                alert(response.message);
            }
        });
    }
    function updateTransaction(json) {
        // Gui request xuong server
        $.ajax({
            type : "PUT",
            url: "/api/transactions/",
            data: JSON.stringify(json),
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                console.log('success');
                alert(response.message);
                location.reload();
            },
            error: function(response) {
                console.log('Failed');
                alert(response.message);
                location.reload();
            }
        });
    }
    function deleteTransaction(id) {
        if (!id) {
            alert('Id Not Found');
            return;
        }

        if (confirm("Are you sure you want to delete this transaction?")) {
            deleteTransactions([id]); // truyền dưới dạng mảng
        }
    }

    // Gửi request và cập nhật giao diện
    function deleteTransactions(ids) {

        $.ajax({
            type: "DELETE",
            url: "/api/transactions/" + ids,
            success: function(response) {
                alert(response.message);
                window.location.reload();
            },
            error: function(response) {
                const message = response.responseJSON?.message || "A problem occurred while deleting.";
                alert(message);
            }
        });
    }

</script>
</body>
</html>
