/* 这里汇聚了常用的表单验证函数
   * ========================= */

//用户名的表单验证
function checkUsername() {
    //1.获取用户名值
    var username = $("#username").val();
    //2.定义正则
    var reg_username = /^.{2,8}$/;
    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if (!flag) {
        //用户名合法
        $("#err-msg").html("用户名长度2~8位");
    }
    return flag;
}
//密码的表单验证
function checkPassword() {
    //1.获取密码值
    var password = $("#password").val();
    //2.定义正则
    var reg_password = /.{6,20}/;
    var reg_password_all_is_num = /^\d{6,20}$/;
    //3.判断，给出提示信息
    var flag1 = reg_password.test(password);
    var flag2 = reg_password_all_is_num.test(password);
    if (flag1 && !flag2) {
        return true;
    } else {
        $("#err-msg").html("密码长度6~20位,由数字和字母组成");
        return false;
    }
}
//邮箱的表单验证
function checkEmail() {
    //1.获取用户名值
    var username = $("#email").val();
    //2.定义正则
    var reg_email = /^\S+@\w{2,5}\.\w{1,5}$/;

    //3.判断，给出提示信息
    var flag = reg_email.test(username);
    if (!flag) {
        //用户名合法
        $("#err-msg").html("邮箱格式错误");
    }
    return flag;
}
//电话号码的表单验证
function checkPhone() {
    //1.获取用户名值
    var phone = $("#phone").val();
    //2.定义正则
    var reg_phone = /^\d{8,15}$/;

    //3.判断，给出提示信息
    var flag = reg_phone.test(phone);
    if (!flag) {
        //用户名合法
        $("#err-msg").html("电话格式错误");
    }
    return flag;
}
//发帖的表单验证
function checkContent() {
    //1.获取用户名值
    var content = $("textarea").val();
    //2.定义正则
    var reg_content = /^[\s \S]{10,}$/;
    //3.判断，给出提示信息
    var flag = reg_content.test(content);
    if (!flag) {
        //用户名合法
        $("#err-msg").css("color", "red");
        $("#err-msg").html("字数过少");
    } else {
        $("#err-msg").css("color", "black");
        $("#err-msg").html("发帖内容字数需要大于10字");
    }
    return flag;
}
//自我介绍的表单验证
function checkIntroduce() {
    //1.获取用户名值
    var introduce = $("#introduce").val();
    //2.定义正则
    var intruduce_reg = /^.{20,500}$/;

    //3.判断，给出提示信息
    var flag = intruduce_reg.test(introduce);
    if (!flag) {
        //用户名合法
        $("#err-msg").html("介绍不要少于20字");
    }
    return flag;
}
