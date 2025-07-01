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
                            <span class="error-message" style="color:red" id="nameError"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" >Quận</label>
                        <div class="col-xs-2">
                            <form:select path="district" class="form-control">
                                <form:option value="">---Chọn quận---</form:option>
                                <form:options items="${district}"/>
                            </form:select>
                            <span class="error-message" style="color:red" id="districtError"></span>
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
                            <span class="error-message" style="color:red" id="rentPriceError"></span>
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
                            <form:input class="form-control" path="motoFee"/>
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
                            <span class="error-message" style="color:red" id="rentAreaError"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Ghi chú</label>
                        <div class="col-xs-9">
                            <form:input class="form-control" path="note"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                        <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                        <div class="col-sm-9">
                            <c:if test="${not empty buildingEdit.image}">
                                <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                            </c:if>
                            <c:if test="${empty buildingEdit.image}">
                                <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label"></label>
                        <div class="col-xs-9">
                                <c:choose>
                                    <c:when test="${not empty buildingEdit.id}">
                                        <button type="button" class="btn btn-warning" id="btnAddOrUpdateBuilding">
                                            Cập nhật thông tin
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                            Thêm tòa nhà
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                                <a href="/admin/building-list">
                                    <button type="button" class="btn btn-danger">
                                        Hủy thao tác
                                    </button>
                                </a>
                        </div>
                    </div>
                    <input type="hidden" id="id" value="${buildingEdit.id}"/>
                    <input type="hidden" id="oldImage" value="${buildingEdit.image}" />
                </form:form>

            </div>
        </div>
    </div>
</div><!-- /.main-content -->
<script>
    var ok = 1;
    var imageBase64 = '';
    var imageName = '';
    function validateDataBuilding(json){
        $('.error-message').html('');
        if (json['name'] === '') {
            ok = 0;
            $('#nameError').html('Tên tòa nhà không được trống')
        }
        if (json['district'] === ''){
            ok = 0;
            $('#districtError').html('Quận không được trống')
        }
        if (json['rentArea'] === ''){
            ok = 0;
            $('#rentAreaError').html('Diện tích thuê không được trống')
        }
        if (json['rentPrice'] === ''){
            ok = 0;
            $('#rentPriceError').html('Giá thuê không được trống')
        }
        if (json['typeCode'].length === 0){
            ok = 0;
            $('#typeCodeError').html('Loại tòa nhà không được để trống')
        }
    }

    $('#btnAddOrUpdateBuilding').click(function(){
        var formData = $('#form-edit').serializeArray();
        var json = {};
        var typeCode = [];
        $.each(formData, function(i, it){
            if (it.name !== 'typeCode'){
                json["" + it.name + ""] = it.value;
            }
            else{
                typeCode.push(it.value);
            }
            if ('' !== imageBase64) {
                json['imageBase64'] = imageBase64;
                json['imageName'] = imageName;
            }
            else {
                json['image'] = $('#oldImage').val();
            }
        })
        json['typeCode'] = typeCode;
        json['id'] = $('#id').val();
        ok = 1;
        // validateDataBuilding(json);
        if (ok === 0){
            alert('Vui lòng điền đầy đủ các trường bắt buộc trước khi tiếp tục.');
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
                alert(response.message);
                window.location.href = "/admin/building-list";
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
    function updateBuilding(json){
        $.ajax({
            type : "PUT",
            url: "/api/buildings",
            data: JSON.stringify(json),
            dataType: "json",
            contentType : "application/json",
            success: function(response){
                alert(response.message);
                window.location.href = "/admin/building-list";
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
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });


    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

</script>
</body>
</html>
