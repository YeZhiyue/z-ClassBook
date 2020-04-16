/* 进行页面信息的初始化
   * ========================= */
initPage(1);

/* 页面信息初始化函数
   * ========================= */
function initPage(currentPageNum) {
    $.post("user/findAllUserByPageNum", {pageNum: currentPageNum}, function (data) {
        //查询成员数据进行展示
        classMatesPageInit(currentPageNum, data);
        //分页初始化
        pageFooterInit(currentPageNum, data.pageNum, 10);
        //更新班级人数信息
        var classNumEle = document.getElementsByClassName("class-mates-num");
        classNumEle[0].innerHTML = "班级人数:" + data.eachClassNum.物联网171;
        classNumEle[1].innerHTML = "班级人数:" + data.eachClassNum.物联网172;
        classNumEle[2].innerHTML = "班级人数:" + data.eachClassNum.物联网173;
        classNumEle[3].innerHTML = "班级人数:" + data.eachClassNum.物联网174;
        //一些总数信息
        $(".alert-link").html("当前班级总人数：" + data.msgNum + " &nbsp;&nbsp;&nbsp;当前总页数：" + data.pageNum);
    });
};
//分页查询的实现，这里简化分页查询，设置每一页展示的人数都为10个，只需要发送页码我们就可以实现分页效果
//并且对每个班级的人数实现动态更新
//分页查询函数
function classMatesPageInit(pageNum, data) {
    //动态添加排序id
    var count = (pageNum - 1) * 10 + 1;
    var tdStr = "";
    console.log(data.users);
    $(data.users).each(function (index, ele) {
        var date = new Date(ele.birthday);
        // console.log(ele);

        tdStr += "              <tr>" +
            "                <td>" + count++ + "</td>" +
            "                <td><span><img onclick='mateInterview(" + ele.id + ")' class='img-circle' src='assets/img/head_img/" + ele.img + "'> <font onclick='seeUserDetial(" + ele.id+","+"\""+ ele.name+"\""+ ")'>&nbsp;&nbsp;" + ele.name + "</font></span></td>" +
            "                <td><a href='javascript:void(0)' onclick='sendMsgOrSeeDetailMsg("+ele.id+")'>发送邮件</a></td>" +
            "                <td>" + ele.sex + "</td>" +
            "                <td>" + ele.className + "</td>" +
            "                <td>" + ele.wChat + "</td>" +
            "                <td>" + ele.qq + "</td>" +
            "                <td>" + ele.phone + "</td>" +
            "                <td>" + ele.email + "</td>" +
            "                <td>" + ele.hobby + "</td>" +
            "                <td>" + date.getFullYear() + "." + date.getMonth() + "." + date.getDay() + "</td>" +
            "              </tr>";
    });
    //表格中写入内容
    $("tbody").html(tdStr);
}

/* 进行私信的发送
   * ========================= */
function sendMsgOrSeeDetailMsg(user_to,mateId) {
    var flag = confirm("是否对他\她发送私信");
    if (flag) {
        var content = prompt("填写私信内容");
        $.post("perEmail/sendPerEmail", {user_to: user_to, content: content});
    }
}

/* 查看个人信息
   * ========================= */
function seeUserDetial(userId, userName) {
    if (confirm("是否要去"+userName+"的主页？")) {
        location.href = "personalIntroduce.html?isHost=false,userId="+userId+"";
    }
}


