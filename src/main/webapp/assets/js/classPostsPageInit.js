/* 发帖需求的实现部分
   * ========================= */
$(function () {
    //发帖表单的隐藏和显示属性设置
    $(".alert-link").on("click", function () {
        $("form").css("display", "inline");
    });
    $("#edit-form-cancel-btn").on("click", function () {
        $("form").css("display", "none");
    });

    //提交发帖表单
    $("#edit-form-button").on("click", function () {
            if (checkContent()) {
                $.post("perPassage/updatePost", $("form").serialize(), function () {
                        $("form").css("display", "none");
                        location.reload();
                    }
                )
            }
        }
    );

});

/* 分页初始化
   * ========================= */
initPage(1);

/* 分页初始化函数
   * ========================= */
function initPage(currentPageNum) {
    $.post("perPassage/findAllPerMsgByPageNum", {pageNum: currentPageNum}, function (data) {
        // console.log(data);
        //查询成员数据进行展示
        pageQuery(currentPageNum, data);
        //分页初始化(参数分别是当前页，总页数，分页总数)
        pageFooterInit(currentPageNum, data.pageNum, 10);
        //一些总数信息
        $(".alert-info small").html("当前评论总数：" + data.msgNum + " &nbsp;&nbsp;&nbsp;当前评论总页数：" + data.pageNum);
    });
};


/* 查询出评论数据
   * ========================= */
function pageQuery(pageNum, data) {
    //动态添加排序id
    var count = (pageNum - 1) * 12 + 1;
    var tdStr = "<div class='row' >";
    // console.log(data.perPassages);
    var eleLength = $(data.perPassages).length;
    $(data.perPassages).each(function (index, ele) {
        var date = new Date(ele.birthday);
        tdStr += "<div class='col-xs-6 col-lg-6'>" +
            "                    <div class='row'>\n" +
            "                        <div class='col-xs-offset-1 col-xs-3 col-lg-3'>\n" +
            "                        <img class='img-circle' width='100px' height='100px' src='assets/img/head_img/" + ele.user.img + "'>" +
            "                        </div>\n" +
            "                        <div class='col-xs-offset-1'>\n" +
            "                        <h4>" + ele.user.name + "&nbsp;&nbsp;&nbsp;" + ele.user.className + "</h4>" +
            "                        <p>" + new Date(ele.date).toLocaleString() + "</p>" +
            "                        <p>第 " + count++ + " 张发帖</p>" +
            "                        </div>\n" +
            "                    </div>" +
            "                        <hr/>" +
            "                        <div class='row'><div class='col-xs-offset-1'><p> " + ele.content +
            "</p></div></div>" +
            "                    </blockquote>" +
            "                    <hr/>" +
            "                </div>";
        //这里根据的是每一行的分行逻辑进行切分
        if (index == eleLength - 1) {
            tdStr += "</div>";
        } else if (index % 2 == 1) {
            tdStr += "</div>" +
                "<div class='row' >";
        }
    });
    //写入内容
    $("#class-posts").html(tdStr);
}



