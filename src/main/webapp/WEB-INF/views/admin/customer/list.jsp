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
    <title>Danh sách khách hàng</title>
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
                        Danh sách khách hàng
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
										<a href="/admin/customer-list" data-action="reload">
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
                                <form:form id="listForm" method="GET" action="/admin/customer-list" modelAttribute="modelSearch">
                                    <div class="row">

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <label>Tên khách hàng</label>
                                                <form:input class="form-control" path="fullName"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-6">
                                                <label>Di động</label>
                                                <form:input class="form-control" path="phone"/>
                                            </div>
                                            <div class="col-xs-6">
                                                <label>Email</label>
                                                <form:input class="form-control" path="email"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-6">
                                                <label>Trạng thái</label>
                                                <form:select path="status" class="form-control">
                                                    <form:option value="">---Chọn tất cả trạng thái---</form:option>
                                                    <form:options items="${status}"/>
                                                </form:select>
                                            </div>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <div class="col-xs-2">
                                                    <label>Chọn nhân viên</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="">---Chọn tất cả nhân viên---</form:option>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </security:authorize>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-primary btn-sm" id="btnSearchCustomer">
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
                        <a href="/admin/customer-edit">
                            <button class="btn btn-app btn-success btn-xs" title="Thêm khách hàng">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
                                        <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
                                        <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5"/>
                                    </svg>
                            </button>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                            <button class="btn btn-app btn-danger btn-xs" title="Xóa khách hàng" id="btnDeleteCustomer">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-x-fill" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6m6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708"/>
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
                    <display:table name="${customerSearchResponses.listResult}" htmlId="customerList" cellspacing="0" cellpadding="0"
                           requestURI="/admin/customer-list" partialList="true" sort="external"
                           size="${customerSearchResponses.totalItems}" defaultsort="2" defaultorder="ascending"
                           id="tableList" pagesize="${customerSearchResponses.maxPageItems}"
                           export="false"
                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em;">


                            <display:column title="" escapeXml="false" style="display:none" headerClass="hidden"><span class="hidden-id" data-id="${tableList.id}"></span>
                            </display:column>
                            <security:authorize access="hasRole('MANAGER')">
                                <display:column title="" escapeXml="false" headerClass="center" class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" value="${tableList.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </display:column>
                            </security:authorize>
                            <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                            <display:column headerClass="text-left" property="phone" title="Di động"/>
                            <display:column headerClass="text-left" property="email" title="Email"/>
                            <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                            <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                            <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                            <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" onclick="assigmentCustomer(${tableList.id})" title="Giao khách hàng">
                                        <i class="ace-icon fa fa-users bigger-120"></i>
                                    </button>
                                </security:authorize>
                                <a href="/admin/customer-edit-${tableList.id}" class="btn btn-xs btn-info" title="Sửa thông tin">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="xóa khách hàng">
                                        <i class="ace-icon fa fa-trash-o bigger-120" onclick="deleteCustomers(${tableList.id})"></i>
                                    </button>
                                </security:authorize>
                            </div>
                           </display:column>
                    </display:table>
                </div><!-- /.span -->
            </div>
        </div>
        <div class="modal fade" id="assignmentCustomerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true" style="font-family:'Times New Roman', Times, serif">
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
                    <input type="hidden" id="customerId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Hủy thao tác</button>
                    <button type="button" class="btn btn-primary" id="btnAssignCustomer">Giao khách hàng</button>
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
                    if (id) row.id = 'customer-row-' + id;
                }
            });
        });
        function assigmentCustomer(customerId) {
            console.log('id khách hàng: ' + customerId);
            $('#assignmentCustomerModal').modal();
            $('#customerId').val(customerId);
            loadStaff(customerId);
        }

        function loadStaff(customerId) {
            $.ajax({
                type : "GET",
                url: "/api/customers/" + customerId + "/staffs",
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

        $('#btnAssignCustomer').click(function(e) {
            e.preventDefault();
            var json = {};
            json['customerId'] = $('#customerId').val();
            var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function() {
                return $(this).val();
            }).get();
            json['staffIds'] = staffIds;
            if(json['customerId'] === '') {
                alert('Id Not Found');
            }
            else {
                assignCustomer(json);
            }
        });
        $('#btnDeleteCustomer').click(function(e) {
            e.preventDefault();
            var customerIds = $('#customerList').find('tbody input[type=checkbox]:checked').map(function() {
                return $(this).val();
            }).get();
            console.log(customerIds);
            if (customerIds.length === 0) {
                alert('Please select at least one customer to delete.');
            }
            else {
                const message = customerIds.length === 1
                    ? "Are you sure you want to delete the selected customer?"
                    : "Are you sure you want to delete the selected " + customerIds.length + " customers?";
                if (confirm(message)) {
                    deleteCustomers(customerIds);
                }
            }
        });

      function deleteCustomer(id) {
              if (!id) {
                alert('Id Not Found');
                return;
            }

            if (confirm("Are you sure you want to delete this customer?")) {
                deleteCustomers([id]); // truyền dưới dạng mảng
            }
        }

        // Gửi request và cập nhật giao diện
        function deleteCustomers(ids) {
            // const idString = ids.join(',');

            $.ajax({
                type: "DELETE",
                url: "/api/customers/" + ids,
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

        function assignCustomer(json) {
            // Gui request xuong server
            $.ajax({
                type : "POST",
                url: "/api/assign/customers",
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

        $('#btnSearchCustomer').click(function(e) {
            e.preventDefault();
            $('#listForm').submit();
        });
    </script>
</body>
</html>