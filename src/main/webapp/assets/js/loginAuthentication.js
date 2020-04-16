/* 用户登录验证，直接访问服务器查看是否有用户session数据
   * ========================= */
$.post("user/isLogin", function (data) {
    // console.log(data);
    if (data == null || data == undefined) {
        //设置头像悬浮框隐藏
        $("#personal-msg").css("display", "none");
        //进行未登录访客拦截
        $("a").each(function (index,ele) {
            if ($(ele).attr("href")!="login.html"&&$(ele).attr("href")!="register.html") {
                ele.href = "javascript:void(0)";
                $(ele).click(function () {
                    if (confirm("您尚未登录，是否进行登录")) {
                        location.href="login.html";
                    }
                });
            }
        });
    } else {
        //设置头像框显示
        $("#personal-msg").css("display", "inline");
        //设置用户名
        $("#personal-msg font").html(data.name);
        //设置头像框
        document.getElementById("personal-head-img").src = "assets/img/head_img/" + data.img;
        //注册退出登录事件，退出登录的话直接返回登录界面
        $("#personal-msg a").click(function () {
            $.get("user/exit");
            location.href = "login.html";
        });
    }
});