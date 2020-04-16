/* 这是一个分页更新函数，需要传入三个参数（当前页，总页数，每一页的信息条数）
 * 根据（前3后6原则）
   * ========================= */
function pageFooterInit(currentPage, totalPage, perPageNum) {
    //准备进行字符串的拼接
    var pageStr = "";
    /* 一、后退页的初始化
       * ========================= */
    if (currentPage == 1) {
        //当前页前面没有分页了，那么设置按钮不可用
        pageStr += "<li class='previous disabled'><a href='javascript:void(0)'>&larr; Older</a></li>";
    } else {
        //当前页前面还有分页，那么设置按钮可用，并且注册单击事件刷新页面
        pageStr += "<li class='previous active' onclick='initPage(" + (currentPage - 1) + ") '><a href='javascript:void(0)'>&larr; Older</a></li>";
    }
    /* 二、中间页码初始化，这个比较复杂，需要处理的情况比较多
       * ========================= */
    //中间页码的初始化（前3后6原则）
    //全局情况：如果总页数大于10，我们可能需要对页码进行移位
    if (totalPage > 10) {
        //情况一：不需要移动页码的情况
        if (currentPage <= 4) {
            for (var i = 1; i <= 10; i++) {
                //如果是当前页，我们为其设置样式
                if (currentPage == i) {
                    pageStr += "<li class='active'><a href=''>" + i + "</a></li>";
                } else {
                    pageStr += "<li  onclick='initPage(" + i + ")'><a href='javascript:void(0)'>" + i + "</a></li>";
                }
            }
        }
        //情况二：需要移动页码的情况
        if (currentPage > 4 && currentPage + 6 < totalPage) {
            for (var i = currentPage - 3; i <= currentPage + 6; i++) {
                //如果是当前页，我们为其设置样式
                if (currentPage == i) {
                    pageStr += "<li class='active'><a href=''>" + i + "</a></li>";
                } else {
                    pageStr += "<li  onclick='initPage(" + i + ")'><a href='javascript:void(0)'>" + i + "</a></li>";
                }
            }
        } else {
            //如果不满足上面条件，那么我们直接进行初始化
            for (var i = totalPage - 10; i <= totalPage; i++) {
                //如果是当前页，我们为其设置样式
                if (currentPage == i) {
                    pageStr += "<li class='active'><a href=''>" + i + "</a></li>";
                } else {
                    pageStr += "<li  onclick='initPage(" + i + ")'><a href='javascript:void(0)'>" + i + "</a></li>";
                }
            }
        }
    }
    //全局情况：如果总页数小于10
    if (totalPage <= 10) {
        for (var i = 1; i <= totalPage; i++) {
            //如果是当前页，我们为其设置样式
            if (currentPage == i) {
                pageStr += "<li class='active'><a href='javascript:void(0)'>" + i + "</a></li>";
            } else {
                pageStr += "<li  onclick='initPage(" + i + ")'><a href='javascript:void(0)'>" + i + "</a></li>";
            }
        }
    }
    /* 三、最后拼接尾部分页
       * ========================= */
    if (currentPage == totalPage) {
        pageStr += "<li class='next disabled'><a href='javascript:void(0)'>Newer &rarr;</a></li>";
    } else {
        pageStr += "<li class='next active' onclick='initPage(" + (currentPage + 1) + ")'><a href='javascript:void(0)'>Newer &rarr;</a></li>";
    }
    /* 四、最后动态输出到页面，这里需要你指定一个<ul id="paging">
       * ========================= */
    $("#paging").html(pageStr);
}