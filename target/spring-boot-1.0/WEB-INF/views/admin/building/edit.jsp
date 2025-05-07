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
                    Thông tin tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
        </div><!-- /.page-content -->
        <div class="row">
            <div class="col-xs-12">
                <form:form class="form-horizontal" role="form" id="form-edit" action="/admin/building-edit" method="GET" modelAttribute="buildingEdit">
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Tên tòa nhà</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="name"/>
                            <span class="error-message" style="color:red" id="name"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Quận</label>
                        <div class="col-xs-2">
                            <form:select path="district" class="form-control">
                                <form:option value="">---Chọn quận---</form:option>
                                <form:options items="${district}"/>
                            </form:select>
                            <span class="error-message" style="color:red" id="district"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Phường</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="ward"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Đường</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="street"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Kết cấu</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="structure"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Số tầng hầm</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="numberOfBasement"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Diện tích sàn</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="floorArea"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Hướng</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="direction"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Hạng</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="level"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Giá thuê</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentPrice"/>
                            <span class="error-message" style="color:red" id="rentPrice"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Mô tả giá</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentPriceDescription"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Phí dịch vụ</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="serviceFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Phí ô tô</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="carFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Phí mô tô</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="motobikeFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Phí ngoài giờ</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="overtimeFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Tiền nước</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="waterFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Tiền điện</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="electricityFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Đặt cọc</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="deposit"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Thanh toán</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="payment"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Thời hạn thuê</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentTime"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Thời gian trang trí</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="decorationTime"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Tên quản lý</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="managerName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">SĐT quản lý</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="managerPhoneNumber"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Phí môi giới</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="brokerageFee"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Loại tòa nhà</label>
                        <div class="col-xs-9">
                            <form:checkboxes path="typeCode" items="${type}"/>
                            <span class="error-message" style="color:red" id="typeCodeError"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Diện tích thuê</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="rentArea"/>
                            <span class="error-message" style="color:red" id="rentArea"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label"></label>
                        <div class="col-xs-9">
                            <c:if test="${not empty buildingEdit.id}">
                                <button type="button" class="btn btn-warning" id="btnAddBuilding">
                                    Cập nhật thông tin
                                </button>
                            </c:if>
                            <c:if test="${empty buildingEdit.id}">
                                <button type="button" class="btn btn-primary" id="btnAddBuilding">
                                    Thêm tòa nhà
                                </button>
                            </c:if>
                                <a href="/admin/building-list">
                                    <button type="button" class="btn btn-danger">
                                        Hủy thao tác
                                    </button>
                                </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Ghi chú</label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" name="note">
                        </div>
                    </div>
                    <input type="hidden" id="id" value="${buildingEdit.id}"></input>
                </form:form>

            </div>
        </div>
    </div>
</div><!-- /.main-content -->
<script>
    var ok = 1;
    function validateDataBuilding(json){
        $('.error-message').html('');
        if (json['name'] === '') {
            ok = 0;
            $('#name').after('<span style="color: red" class="error-message">Tên tòa nhà không được trống</span>')
        }
        if (json['district'] === ''){
            ok = 0;
            $('#district').after('<span style="color: red">Quận không được trống</span>')
        }
        if (json['rentArea'] === ''){
            ok = 0;
            $('#rentArea').after('<span style="color: red">Diện tích thuê không được trống</span>')
        }
        if (json['rentPrice'] === ''){
            ok = 0;
            $('#rentPrice').after('<span style="color: red">Giá thuê không được trống</span>')
        }
        if (json['typeCode'].length === 0){
            ok = 0;
            $('#typeCodeError').html('Loại tòa nhà không được để trống')
        }
    }

    $('#btnAddBuilding').click(function(){
        var formData = $('#form-edit').serializeArray();
        var json = {};
        var typeCode = [];
        $.each(formData, function(i, it){
            if (it.name != 'typeCode'){
                json["" + it.name + ""] = it.value;
            }
            else{
                typeCode.push(it.value);
            }
        })
        json['typeCode'] = typeCode;
        json['id'] = $('#id').val();
        ok = 1;
        // validateDataBuilding(json);
        if (ok === 0){
            alert('Failed');
        }
        else{
            if (json['id'] === ''){
                addBuilding(json);
            }
            else{
                updateBuilding(json);
            }
        }
    });
    function addBuilding(json){
        $.ajax({
            type : "POST",
            url: "/api/buildings",
            data: JSON.stringify(json),
            dataType: "json",
            contentType : "application/json",
            success: function(response){
                alert(response.responseJSON.message);
            },
            error: function(response){
                alert(response.responseJSON.data.join('\n'));
            }
        });
    }
    function updateBuilding(json){
        $.ajax({
            type : "PUT",
            url: "/api/buildings",
            data: JSON.stringify(json),
            dataType: "json",
            contentType : "application/json",
            success: function(response){
                alert(response.message);
            },
            error: function(response){
                console.log(response.message);
            }
        });
    }
</script>
</body>
</html>
