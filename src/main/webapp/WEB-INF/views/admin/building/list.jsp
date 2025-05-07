<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                        Danh sách tòa nhà
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->

            </div><!-- /.page-content -->
            <div class="row">

                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <span class="widget-toolbar">

										<a href="/admin/building-list" data-action="reload">
											<i class="ace-icon fa fa-refresh"></i>
										</a>

										<a href="#" data-action="collapse">
											<i class="ace-icon fa fa-chevron-up"></i>
										</a>

										<a href="#" data-action="close">
											<i class="ace-icon fa fa-times"></i>
										</a>
									</span>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form id="listForm" method="GET" action="/admin/building-list" modelAttribute="modelSearch">
                                    <div class = "row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label> Tên tòa nhà </label>
<%--                                                    <input type="text" id="name" name="name" placeholder="tên tòa nhà..." class="form-control" value="${modelSearch.name}">--%>
                                                    <form:input class="form-control" path="name"/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label> Diện tích sàn </label>
<%--                                                    <input type="number" id="floorArea" name="floorArea" placeholder="diện tích sàn..." class="form-control">--%>
                                                    <form:input class="form-control" path="floorArea"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label> Quận </label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="">---Chọn quận---</form:option>
                                                        <form:options items="${district}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label> Phường </label>
                                                    <form:input class="form-control" path="ward"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label> Đường </label>
                                                    <form:input class="form-control" path="street"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label> Số tầng hầm </label>
                                                    <form:input class="form-control" path="numberOfBasement"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label> Hướng </label>
                                                    <form:input class="form-control" path="direction"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label> Hạng </label>
                                                    <form:input class="form-control" path="level"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label> Diện tích từ </label>
                                                    <form:input class="form-control" path="areaFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label> Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label> Giá thuê từ </label>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label> Giá thuê đến </label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label> Tên quản lí </label>
                                                    <form:input class="form-control" path="managerName"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label> Số điện thoại quản lí </label>
                                                    <form:input class="form-control" path="managerPhoneNumber"/>
                                                </div>
                                                <div class="col-xs-2">
                                                    <label> Chọn nhân viên </label>
<%--                                                    <select name="staffId" id="staff" class="form-control">--%>
<%--                                                        <option value="">Chọn nhân viên</option>--%>
<%--                                                        <option value="1">Nguyễn Văn A</option>--%>
<%--                                                        <option value="2">Nguyễn Văn C</option>--%>
<%--                                                    </select>--%>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="">---Chọn Nhân Viên---</form:option>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <form:checkboxes path="typeCode" items="${type}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-purple btn-sm" id="btnSearchBuilding">
                                                        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                        <div class="pull-right">
                            <a href="/admin/building-edit">
                                <button class="btn btn-app btn-success btn-xs" title="Thêm tòa nhà">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </a>
                            <button class="btn btn-app btn-danger btn-xs" title="Xóa tòa nhà" id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </div>

                    </div>
                </div><!-- /.span -->
            </div>
            <div class="hr hr-20 hr-double"></div>
            <!-- Table List Building -->
            <div class="row">
                <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Tên quản lí</th>
                            <th>SĐT quản lí</th>
                            <th>Diện tích sàn</th>
                            <th>Diện tích thuê</th>
                            <th>Diện tích trống</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="building" items="${buildingSearchResponses}">
                                <tr>
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" id="id" value="${building.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>

                                    <td>${building.name}</td>
                                    <td>${building.address}</td>
                                    <td>${building.numberOfBasement}</td>
                                    <td>${building.managerName}</td>
                                    <td>${building.managerPhoneNumber}</td>
                                    <td>${building.floorArea}</td>
                                    <td>${building.rentArea}</td>
                                    <td></td>
                                    <td>${building.rentPrice}</td>
                                    <td>${building.serviceFee}</td>
                                    <td>${building.brokerageFee}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success" onclick="assignmentBuilding(${building.id})" title="Giao tòa nhà">
                                                <i class="ace-icon fa fa-users"></i>
                                            </button>
                                            <a href="/admin/building-edit-${building.id}" class="btn btn-xs btn-info" title="Sửa thông tin">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>
                                            <button class="btn btn-xs btn-danger" onclick="deleteBuilding(${building.id})" title="Xóa tòa nhà">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </div>

                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div>
        </div>
        <div class="modal fade" id="assignmentBuildingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Danh sách nhân viên</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table id="staffList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" id="idBuilding" value="1">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="center">Tên nhân viên</th>
                        </tr>
                        </thead>

                        <tbody>
                        </tbody>
                    </table>
                    <input type="hidden" id="buildingId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Hủy thao tác</button>
                    <button type="button" class="btn btn-primary" id="btnAssignBuilding">Giao tòa nhà</button>
                </div>
            </div>
        </div>
    </div>
    </div><!-- /.main-container -->
    <script>
        function assignmentBuilding(buildingId){
            console.log('id tòa nhà: ' + buildingId);
            $('#assignmentBuildingModal').modal();
            $('#buildingId').val(buildingId);
            loadStaff(buildingId);
        }
        function loadStaff(buildingId){
            $.ajax({
                type : "GET",
                url: "/api/buildings/" + buildingId + '/staffs',
                dataType: "json",
                success: function(response){
                    var row = '';
                    $.each(response.data, function(index, item){
                        row += '<tr>';
                        row += '<td class="center"> <input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class="center" ' + item.checked + '/> </td>';
                        row += '<td class="center">' + item.fullName + '</td>';
                        row += '</tr>';
                    });
                    $('#staffList tbody').html(row);
                },
                error: function (response) {
                    console.log('Failed');
                }
            });
        }

        $('#btnAssignBuilding').click(function (e) {
            e.preventDefault();
            var json = {};
            json['buildingId'] = $('#buildingId').val();
            var staffIds = $('#assignmentBuildingModal').find('tbody input[type=checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            json['staffs'] = staffIds;
            console.log("success");
            if (json['buildingId'] == ''){
                alert('Id not Found');
            }
            else{
                assignBuilding(json);
            }
        });
        $('#btnDeleteBuilding').click(function(e){
            e.preventDefault();
            var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function(){
                return $(this).val();
            }).get();
            console.log(buildingIds);
            if (buildingIds === ''){
                alert('No buildings selected');
            }
            else{
                deleteBuildings(buildingIds);
            }
        });
        function deleteBuilding(id){
            if (id == ''){
                alert('Id not Found');
            }
            else{
                deleteBuildings(id);
            }
        }
        function deleteBuildings(ids){
            $.ajax({
                type : "DELETE",
                url: "/api/buildings/" +ids,
                // data: JSON.stringify(json),
                dataType: "json",
                // contentType : "application/json",
                success: function(response){
                    console.log('Success');
                    console.log(url);
                    alert('Update Building Success');
                },
                error: function(response){
                    console.log('Failed');
                }
            });
        }
        function assignBuilding(json){
            $.ajax({
                type : "POST",
                url: "/api/assign",
                data: JSON.stringify(json),
                dataType: "json",
                contentType : "application/json",
                success: function(response){
                    console.log('Success');
                    alert(response.message);
                },
                error: function(response){
                    console.log('Failed');
                    alert(response.message);
                }
            });

        }
        $('#btnSearchBuilding').click(function(e){
           e.preventDefault();
            $('#listForm').submit();
        });
    </script>
</body>
</html>
