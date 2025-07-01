<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/27/2025
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
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
                                    <div class="row">

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>Tên tòa nhà</label>
<%--                                                    <input type="text" id="name" name="name" class="form-control" value="${modelSearch.name}">--%>
                                                    <form:input class="form-control" path="name"/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label>Diện tích sàn</label>
<%--                                                    <input type="number" class="form-control" name="floorArea" value="${modelSearch.floorArea}">--%>
                                                    <form:input class="form-control" path="floorArea"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label>Quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="">---Chọn Quận---</form:option>
                                                        <form:options items="${district}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Phường</label>
<%--                                                    <input type="text" class="form-control" name="ward" value="${modelSearch.ward}">--%>
                                                    <form:input class="form-control" path="ward"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Đường</label>
<%--                                                    <input type="text" class="form-control" name="street" value="${modelSearch.street}">--%>
                                                    <form:input class="form-control" path="street"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label>Số tầng hầm</label>
<%--                                                    <input type="number" class="form-control" name="numberOfBasement" value="${modelSearch.numberOfBasement}">--%>
                                                    <form:input class="form-control" path="numberOfBasement"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hướng</label>
<%--                                                    <input type="text" class="form-control" name="direction" value="${modelSearch.direction}">--%>
                                                    <form:input class="form-control" path="direction"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hạng</label>
<%--                                                    <input type="number" class="form-control" name="level" value="${modelSearch.level}">--%>
                                                    <form:input class="form-control" path="level"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label>Diện tích từ</label>
<%--                                                    <input type="number" class="form-control" name="areaFrom" value="${modelSearch.areaFrom}">--%>
                                                    <form:input class="form-control" path="areaFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Diện tích đến</label>
<%--                                                    <input type="number" class="form-control" name="areaTo" value="${modelSearch.areaTo}">--%>
                                                    <form:input class="form-control" path="areaTo"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê từ</label>
<%--                                                    <input type="number" class="form-control" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}">--%>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label>Tên quản lý</label>
<%--                                                    <input type="text" class="form-control" name="managerName" value="${modelSearch.managerName}">--%>
                                                    <form:input class="form-control" path="managerName"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>SĐT quản lý</label>
<%--                                                    <input type="text" class="form-control" name="managerPhone" value="${modelSearch.managerPhoneNumber}">--%>
                                                    <form:input class="form-control" path="managerPhone"/>
                                                </div>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <div class="col-xs-2">
                                                        <label>Chọn nhân viên</label>
                                                        <form:select path="staffId" class="form-control">
                                                            <form:option value="">---Chọn nhân viên---</form:option>
                                                            <form:options items="${staffs}"/>
                                                        </form:select>
                                                    </div>
                                                </security:authorize>
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
                                                    <button type="button" class="btn btn-primary btn-sm" id="btnSearchBuilding">
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
                        <security:authorize access="hasRole('MANAGER')">
                            <button class="btn btn-app btn-danger btn-xs" title="Xóa tòa nhà" id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </security:authorize>
                    </div>
                </div><!-- /.span -->
            </div>
            <div class="hr hr-double hr-dotted"></div>
            <!-- Table list building -->
            <div class="row">
                <div class="col-xs-12">
                    <display:table name="${buildingSearchResponses.listResult}" htmlId="buildingList" cellspacing="0" cellpadding="0"
                           requestURI="/admin/building-list" partialList="true" sort="external"
                           size="${buildingSearchResponses.totalItems}" defaultsort="2" defaultorder="ascending"
                           id="tableList" pagesize="${buildingSearchResponses.maxPageItems}"
                           export="false"
                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em;">


                            <display:column title="" escapeXml="false"><span class="hidden-id" data-id="${tableList.id}"></span></display:column>
                            <security:authorize access="hasRole('MANAGER')">
                                <display:column title="" escapeXml="false" headerClass="center" class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" value="${tableList.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </display:column>
                            </security:authorize>
                            <display:column headerClass="text-left" property="name" title="Tên"/>
                            <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                            <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                            <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                            <display:column headerClass="text-left" property="managerPhoneNumber" title="SĐT quản lý"/>
                            <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn"/>
                            <display:column headerClass="text-left" property="rentArea" title="Diện tích cho thuê"/>
                            <display:column headerClass="text-left" property="availableArea" title="Diện tích trống"/>
                            <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                            <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                            <display:column headerClass="text-left" property="brokerageFee" title="Phí môi giới"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" onclick="assigmentBuilding(${tableList.id})" title="Giao tòa nhà">
                                        <i class="ace-icon fa fa-users bigger-120"></i>
                                    </button>
                                </security:authorize>
                                <a href="/admin/building-edit-${tableList.id}" class="btn btn-xs btn-info" title="Sửa thông tin">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="xóa tòa nhà">
                                        <i class="ace-icon fa fa-trash-o bigger-120" onclick="deleteBuilding(${tableList.id})"></i>
                                    </button>
                                </security:authorize>
                            </div>
                           </display:column>
                    </display:table>
                </div><!-- /.span -->
            </div>
        </div>
        <div class="modal fade" id="assignmentBuildingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true" style="font-family:'Times New Roman', Times, serif">
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
                                <label class="pos-rel">Chọn
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
    </div><!-- /.main-content -->
    <script>
        window.addEventListener('DOMContentLoaded', () => {
            const rows = document.querySelectorAll('table tbody tr');
            rows.forEach(row => {
                const span = row.querySelector('.hidden-id');
                if (span) {
                    const id = span.getAttribute('data-id');
                    if (id) row.id = 'building-row-' + id;
                }
            });
        });
        function assigmentBuilding(buildingId) {
            console.log('id tòa nhà: ' + buildingId);
            $('#assignmentBuildingModal').modal();
            $('#buildingId').val(buildingId);
            loadStaff(buildingId);
        }

        function loadStaff(buildingId) {
            $.ajax({
                type : "GET",
                url: "/api/buildings/" + buildingId + "/staffs",
                dataType: "json",
                success: function(response) {
                    var row = "";
                    $.each(response.data, function (index, item) {
                        row += '<tr>';
                        row += '<td class="center"> <input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class="center"' + item.checked + '/> </td>';
                        row += '<td class="center">' + item.fullName + '</td>';
                        row += '</tr>';
                    });
                    console.log(row);
                    $('#staffList tbody').html(row);
                },
                error: function(response) {
                    alert(response.message);
                }
            });
        }

        $('#btnAssignBuilding').click(function(e) {
            e.preventDefault();
            var json = {};
            json['buildingId'] = $('#buildingId').val();
            var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function() {
                return $(this).val();
            }).get();
            json['staffIds'] = staffIds;
            if(json['buildingId'] === '') {
                alert('Id Not Found');
            }
            else {
                assignBuilding(json);
            }
        });
        $('#btnDeleteBuilding').click(function(e) {
            e.preventDefault();
            var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function() {
                return $(this).val();
            }).get();
            console.log(buildingIds);
            if (buildingIds.length === 0) {
                alert('Please select at least one building to delete.');
            }
            else {
                const message = buildingIds.length === 1
                    ? "Are you sure you want to delete the selected building?"
                    : "Are you sure you want to delete the selected " + buildingIds.length + " buildings?";
                if (confirm(message)) {
                    deleteBuildings(buildingIds);
                }
            }
        });

      function deleteBuilding(id) {
              if (!id) {
                alert('Id Not Found');
                return;
            }

            if (confirm("Are you sure you want to delete this building?")) {
                deleteBuildings([id]); // truyền dưới dạng mảng
            }
        }

        // Gửi request và cập nhật giao diện
        function deleteBuildings(ids) {
            // const idString = ids.join(',');

            $.ajax({
                type: "DELETE",
                url: "/api/buildings/" + ids,
                success: function(response) {
                    alert(response.message);
                    // ids.forEach(function(id) {
                    //     $('#building-row-' + id).remove();
                    // });
                    window.location.reload();
                },
                error: function(response) {
                    const message = response.responseJSON?.message || "A problem occurred while deleting.";
                    alert(message);
                }
            });
        }

            function assignBuilding(json) {
                // Gui request xuong server
                $.ajax({
                    type : "POST",
                    url: "/api/assign/buildings",
                    data: JSON.stringify(json),
                    dataType: "json",
                    contentType: "application/json",
                    success: function(response) {
                        console.log('success');
                        alert(response.message);
                    },
                    error: function(response) {
                        console.log('Failed');
                        alert(response.message);
                    }
                });
            }

        $('#btnSearchBuilding').click(function(e) {
            e.preventDefault();
            $('#listForm').submit();
        });
    </script>
</body>
</html>