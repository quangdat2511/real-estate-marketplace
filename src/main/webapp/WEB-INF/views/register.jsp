<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng ký</title>
</head>
<body>
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="container-fluid">
                <section class="gradient-custom">
                    <div class="page-wrapper">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                                    <div class="card-body p-5">
                                        <div class="mb-md-5 mt-md-4 pb-5 text-center">
                                            <h2 class="fw-bold mb-2 text-uppercase">ĐĂNG KÍ</h2>
                                            <p class="text-white-50 mb-5">Vui lòng nhập thông tin để đăng ký tài khoản!</p>

                                            <form id="formRegister">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="fullName">Họ và tên</label>
                                                    <input type="text" id="fullName" name="fullName" class="form-control" required>
                                                    <span class="error-message text-warning" style="color:red;" id="fullNameError"></span>
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="userName">Tên đăng nhập</label>
                                                    <input type="text" id="userName" name="userName" class="form-control" required>
                                                    <span class="error-message text-warning" style="color:red;" id="userNameError"></span>
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="password">Mật khẩu</label>
                                                    <input type="password" id="password" name="password" class="form-control" required>
                                                    <span class="error-message text-warning" style="color:red;" id="passwordError"></span>
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="retypePassword">Nhập lại mật khẩu</label>
                                                    <input type="password" id="retypePassword" name="retypePassword" class="form-control" required>
                                                    <span class="error-message text-warning" style="color:red;" id="retypePasswordError"></span>
                                                </div>
                                                <div class="form-check mb-4 text-start">
                                                    <input class="form-check-input" type="checkbox" id="agree" name="agree" required>
                                                    <label class="form-check-label text-white" for="agree">
                                                        Tôi đồng ý với các điều khoản và điều kiện
                                                    </label>
                                                    <br>
                                                    <span class="error-message text-warning" style="color:red;" id="agreeTermsError"></span>
                                                </div>

                                                <button type="submit" class="btn btn-primary" id="btnRegister">Đăng ký</button>
                                            </form>
                                        </div>
                                        <div class="text-center">
                                            <p class="mb-0">Đã có tài khoản?
                                                <a href="login" class="text-white-50 fw-bold">Đăng nhập</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    ok = 1;
    function validateDataRegister(json) {
        $('.error-message').html('');

        if (!json.fullName || json.fullName.trim() === '') {
            $('#fullNameError').html('Họ tên không được để trống');
            ok = 0;
        }

        if (!json.userName || json.userName.trim() === '') {
            $('#userNameError').html('Tên đăng nhập không được để trống');
            ok = 0;
        }

        if (!json.password || json.password.trim() === '') {
            $('#passwordError').html('Mật khẩu không được để trống');
            ok = 0;
        }

        if (!json.retypePassword || json.retypePassword.trim() === '') {
            $('#retypePasswordError').html('Vui lòng nhập lại mật khẩu');
            ok = 0;
        }

        if (json.password !== json.retypePassword) {
            $('#retypePasswordError').html('Mật khẩu nhập lại không khớp');
            ok = 0;
        }

        if (!$('#agree').is(':checked')) {
            $('#agreeTermsError').html('Bạn phải đồng ý với các điều khoản');
            ok = 0;
        }
    }

    // Xử lý nút xóa nhiều building
    $('#btnRegister').click(function(e) {
        e.preventDefault();
        var formData = $('#formRegister').serializeArray();
        const json = {};
        $.each(formData, function (i, it) {
            json["" + it.name + ""] = it.value;
        })
        ok = 1;
        validateDataRegister(json);
        if (ok === 0) {
            alert('Vui lòng điền đầy đủ các trường bắt buộc trước khi tiếp tục.');
        }
        else{
            register(json);
        }
    });


    function register(json){
        $.ajax({
            type : "POST",
            url: "/api/registers",
            data: JSON.stringify(json),
            dataType: "json",
            contentType : "application/json",
            success: function(response){
                alert(response.message);
                window.location.href = "/login";
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

    // Gán building cho user
</script>

</body>
</html>
