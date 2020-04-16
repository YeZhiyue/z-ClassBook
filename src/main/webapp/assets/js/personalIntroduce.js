

/* 每次进入页面进行数据的初始化
   * ========================= */
//设置一个全局数据集合
var userData;
$.post("user/userMsg",{mateId:getMateId()}, function (data) {

    console.log(data);

    //如果没有获取到数据，直接跳出方法
    if (data == undefined) {
        return;
    }
    //对数据集合进行初始化
    userData = data;

    //编辑表单初始化
    intitEditForm(data.name, data.className, data.hobby, data.birthday, data.introduce);

    //个人基本信息初始化
    perMsgInit(data);

    //初始化所有私信
    perEmails(data,0);

    //初始化所有发信
    sendEmails(data,0);

    //初始化所有个人发帖
    var passages;
    if (data.perPassages) {
        passages = data.perPassages;
        var length = passages.length;
        if (length % 5 == 0) {
            pageFooterInit(1, Math.floor(length / 5), 5);
        } else {
            pageFooterInit(1, Math.floor(length / 5 + 1), 5);
        }
    }
    initPage(1);

    //一些图标信息的更新
    updateIco(data);

    //如果查出是非登录用户，需要进行额外处理
    if (!data.isHost) {
        notHostUpdateData();
    }


});

/* 个人基本信息初始化
   * ========================= */
function perMsgInit(data) {
    //第一部分，初始化所有的user基本信息
    var id = data.id;
    var name = data.name;
    var password = data.password;
    var email = data.email;
    var sex = data.sex;
    var phone = data.phone;
    var img = data.img;
    var qq = data.qq;
    var wChat = data.wChat;
    var className = data.className;
    var birthday = data.birthday;
    var hobby = data.hobby;
    var introduce = data.introduce;
    var login_data = data.login_data;
    $("#introduce-page-name").html(name);

    // 个人头像初始化
    $("#header-img").html("<img class='img-circle'  src='assets/img/head_img/" + img + "' alt='' style='width: 138px;height: 138px'>");

    //个人基本信息初始化
    $("#personal-message-1").html("" +
        "                <div class='col-sm-6'>" +
        "                    <ul class='list-unstyled'>" +
        "                        <li>Class：<a href=''><span class='social fa fa-linkedin'></span></span>" + className + "</a></a>" +
        "                        </li>" +
        "                        <li>Mail：<a href=''><span class='social fa fa-envelope-o'></span>" + email + "</a>" +
        "                        </li>" +
        "                        <li>Phone：<a href=''><span class='social fa fa-skype'></span></span>" + phone + "</a></a>" +
        "                        </li>" +
        "                    </ul>" +
        "                </div><!-- social 1st col end-->" +
        "" +
        "                <div class='col-sm-6'>" +
        "                    <ul class='list-unstyled'>" +
        "                        <li>Sex：<a href=''><span class='social fa fa-home'></span>" + sex + "</a>" +
        "                        </li>" +
        "                        <li>QQ：<a href=''><span class='social fa fa-facebook'></span></span>" + qq + "</a></a>" +
        "                        </li>" +
        "                        <li>WChat：<a href=''><span class='social fa fa-twitter'></span></span>" + wChat + "</a></a>" +
        "                        </li>" +
        "                    </ul>" +
        "                </div><!-- social 2nd col end-->"
    );

    //个人爱好信息初始化
    $("#personal-introduce-msg").html("            <h3>Personal Introduce: </h3><br/>" +
        "            <p><p class='text-info' style='font-weight: bold'>Hobbys：</p>" + hobby + "</p>" +
        "            <p><p class='text-info' style='font-weight: bold'>Birthday：</p>" + new Date(birthday).toLocaleString() + " </p>" +
        "            <p><p class='text-info' style='font-weight: bold'>Introduce：</p>" + introduce + "</p>" +
        "  ");
}

/* 初始化个人私信数据
   * ========================= */
var perEmailsCurrentPage=1;
function perEmails(userData,pFlag) {
    // console.log(userData);
    //如果该用户有私信的话，那么我们就对私信栏进行初始化
    var perEmail;
    if (userData.perEmails != null) {
        perEmail = userData.perEmails;

        var id;
        var user_from;
        var user_to;
        var date;
        var content;

        //头部信息的拼接
        var strHead =
            "                <br/>" +
            "                <br/>" +
            "";

        //中间信息段的拼接
        var strBody = "";
        
        var length = perEmail.length;

        if ((perEmailsCurrentPage+pFlag)<1||(perEmailsCurrentPage+pFlag)>Math.ceil(length/5)){
            return;
        } else {
            perEmailsCurrentPage=perEmailsCurrentPage+pFlag;
        }

        var pageStartNum = (perEmailsCurrentPage - 1) * 5;
        var pageEndNum;
        if ((pageStartNum + 5) < length) {
            pageEndNum = pageStartNum + 5;
        } else {
            pageEndNum = length;
        }
        for (var i = pageStartNum; i < pageEndNum; i++) {
            id = perEmail[i].id;
            user_from = perEmail[i].user_from;
            user_to = perEmail[i].user_to;
            date = perEmail[i].date;
            content = perEmail[i].content;
            strBody += "" +
                "                               <div class='row'>" +
                "                                   <img src='assets/img/head_img/" + perEmail[i].find_user_from.img + "' onclick='mateInterview("+user_from+")' alt=''>" +
                "                                   <h4>" + perEmail[i].find_user_from.name + "</h4>" +
                "                                   <p>" + new Date(date).toLocaleString() + "</p>" +
                "                                   <blockquote>" +
                "                                       <p>" + content + ".</p>" +
                "                                   </blockquote>" +
                "                               </div>" +
                "                               <br/>" +
                "                               <br/>";
        }

        $("#personal-email").html(strHead + strBody);
    }
}

/* 初始化个人发信数据
   * ========================= */
var sendEmailsCurrentPage=1;
function sendEmails(userData,pFlag) {
    // console.log(date);
    //如果该用户有私信的话，那么我们就对私信栏进行初始化
    var sendEmail;
    if (userData.sendEmails != null) {
        sendEmail = userData.sendEmails;
        // console.log(perEmail[0]);
        // console.log(perEmail[0].find_user_from);
        // console.log(perEmail.length);

        var id;
        var user_from;
        var user_to;
        var date;
        var content;

        var strBody = "";

        var length = sendEmail.length;

        if ((sendEmailsCurrentPage+pFlag)<1||(sendEmailsCurrentPage+pFlag)>Math.ceil(length/5)){
            return;
        } else {
            sendEmailsCurrentPage=sendEmailsCurrentPage+pFlag;
        }

        var pageStartNum = (sendEmailsCurrentPage - 1) * 5;
        var pageEndNum;
        if ((pageStartNum + 5) < length) {
            pageEndNum = pageStartNum + 5;
        } else {
            pageEndNum = length;
        }
        for (var i = pageStartNum; i < pageEndNum; i++) {
            id = sendEmail[i].id;
            user_from = sendEmail[i].user_from;
            user_to = sendEmail[i].user_to;
            date = sendEmail[i].date;
            content=sendEmail[i].content;
            strBody += "" +
                "                               <div class='row'>" +
                "                                   <img src='assets/img/head_img/" + userData.img + "' onclick='mateInterview("+userData.id+")'>" +
                "                                   <img src='assets/img/head_img/" + sendEmail[i].find_user_from.img + "'onclick='mateInterview("+user_to+")''>" +
                "                                   <h4>" + sendEmail[i].find_user_from.name + "</h4>" +
                "                                   <p>" + new Date(date).toLocaleString() + "</p>" +
                "                                   <blockquote>" +
                "                                       <p>" + content + ".</p>" +
                "                                   </blockquote>" +
                "                               </div>" +
                "                               <br/>" +
                "                               <br/>";
            content = sendEmail[i].content;
        }
        var strHead =
            "                <br/>" +
            "                <br/>" +
            "";
        $("#personal-send-email").html(strHead + strBody);
    }
}

/* 初始化个人发帖数据
   * ========================= */
function initPage(currentPage) {
    //第三部分：初始化所有个人发帖信息
    var passages;
    if (userData.perPassages) {
        passages = userData.perPassages;

        strBody = "";
        var pageStartNum = (currentPage - 1) * 5;
        var pageEndNum;
        if ((pageStartNum + 5) < passages.length) {
            pageEndNum = pageStartNum + 5;
        } else {
            pageEndNum = passages.length;
        }
        for (var i = pageStartNum; i < pageEndNum; i++) {
            strBody += "                <div class='row'>" +
                "                    <div class='col-md-9'>" +
                "                        <h4>" + userData.name + "</h4>" +
                "                        <p class='sub'><a href=''>" + userData.className + "</a>" +
                "                        </p>" +
                "                        <p>" + passages[i].content + "</p>" +
                "                    </div>" +
                "" +
                "                    <div class='year col-md-3'>" +
                "                        <p>" + new Date(passages[i].date).toLocaleString() + "</p>" +
                "                    </div>" +
                "                </div>" +
                "" +
                "                <hr>";
        }
        //进行页脚更新
        var length = passages.length;
        if (length % 5 == 0) {
            pageFooterInit(currentPage, Math.floor(length/5), 5);
        } else {
            pageFooterInit(currentPage, Math.floor(length / 5 + 1), 5);
        }


        $("#myPosters").html( strBody);
    }
}

/* 一些图标数据初始化
   * ========================= */
function updateIco(data) {
    var perEmail = data.perEmails;
    var sendEmail = data.sendEmails;
    var passages = data.perPassages;
    //私信条数更新
    if (perEmail == null) {
        $("#myPerEmailNum").html(0);
    } else {
        var perEmailLength = $(perEmail).size();
        $("#myPerEmailNum").html(perEmailLength);
    }
    //发信条数更新
    if (sendEmail == null) {
        $("#sendPerEmailNum").html(0);
    } else {
        var sendEmailLength = $(sendEmail).size();
        $("#sendPerEmailNum").html(sendEmailLength);
    }
    //个人发帖数更新
    if (passages == null) {
        $("#myPosterNum").html(0);
    } else {
        var perPassageLegth = $(passages).size();
        $("#myPosterNum").html(perPassageLegth);
    }
}

/* 初始化表单信息
   * ========================= */
function intitEditForm(username, className, hobby, birthday, introduce) {
    $("#username").val(username);
    $("#className").val(className);
    $("#hobby").val(hobby);
    $("#birthday").val(new Date(birthday).toLocaleString());
    $("#introduce").val(introduce);
}

/* 获取url中的需要访问用户的id
   * ========================= */
function getMateId() {
    var reg_url = /mateId=\d+/;
    var result = location.href.match(reg_url);
    if (result != null) {
        result = Number(result[0].substring(7));
        // console.log(result);
    }
    return result;
}

/* 用于非登录用户数据更新
   * ========================= */
function notHostUpdateData() {
    $(".if-not-host").css("visibility","hidden ")

}

/* 个人信息表单的信息编辑
   * ========================= */
$(function () {
    //用户编辑个人信息表单
    $("#edit-form-button").click(function () {
        if (checkUsername() && checkIntroduce()) {
            console.log($("form").serialize());
            $.post("user/updateUserMsg", $("form").serialize(), function (date) {
                    // console.log(date);
                    if (date != null) {
                        $("#err-msg").html("信息更新完毕");
                        //信息更新完毕后刷新页面
                        location.reload();
                    } else {
                        $("#err-msg").html("信息未更新");
                    }
                }
            );
        }
    });

    //表单的显示与隐藏事件注册
    $("#edit-form-cancel-btn").click(function () {
        $("#edit-form").css({"display": "none"})
    });
    $("#edit-form-btn").click(function () {
        $("#edit-form").css({"display": "inline"})
    });


});

$(".send-mail-btn1").click(function () {
    sendEmails(userData,-1);
});
$(".send-mail-btn2").click(function () {
    sendEmails(userData,1);

});
$(".perEmail-1").click(function () {
    perEmails(userData, -1);
});
$(".perEmail-2").click(function () {
    perEmails(userData, 1);
});

