/* 用于头像的一些事件处理
   * ========================= */

/* 用户头像点击进行该用户详细信息的访问
   * ========================= */
function mateInterview(mateId) {
    if (confirm("是否要查看他/她的信息")) {
        //如果需要进行查看，那么我们就跳转到他的主页
        location.href = "http://localhost:8080/personalIntroduce.html?mateId=" + mateId;
    }
}

$("#personal-msg img").click(function () {
    location.href = "personalIntroduce.html";
});
