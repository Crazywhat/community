//折叠子评论
function collapse_subcomment(e) {
    var commentId = e.getAttribute("data-cm-id");
    var comment = $('#comment-'+commentId);
    var subComment =  $('#subcomment-' + commentId);

    var collapse = e.getAttribute("data-cm-collapse");

    if(collapse){
        comment.removeClass("active");
        subComment.removeClass("show");
        e.removeAttribute("data-cm-collapse");
    }else{

        if(subComment.children().length == 2){
            $.getJSON("/comment/" + commentId,function (data) {
                $.each(data.data.reverse(), function (index, comment) {

                    var card = $("<div/>",{
                        "class": "card"
                    });

                    var cardBody = $("<div/>",{
                        "class": "card-body comment-bg"
                    });

                    var cardTitle = $("<h5/>",{
                        "class": "card-title text-white"
                    }).append($("<image/>",{
                        "class": "rounded avatarImg",
                        "src": comment.user.avatarUrl
                    })).append($("<a/>",{
                        "html": comment.user.name
                    }));

                    var cardText = $("<p/>",{
                        "class": "card-text text-white",
                        "html": comment.content
                    });

                    var commentDate = $("<span/>",{
                        "class": "float-right"
                    }).append($("<small/>",{
                        "class": "text-muted pr-5 text-white-50",
                        "html": moment(comment.gmtCreate).format("YYYY-MM-DD")
                    }));

                    cardBody.append(cardTitle).append(cardText).append(commentDate);
                    card.append(cardBody);
                    subComment.prepend(card);
                });
            });
        }

        comment.addClass("active");
        subComment.addClass("show");
        e.setAttribute("data-cm-collapse","true");
    }
}


//提交评论
function post_comment(type, parentId, content) {

    if(!content){
        alert("评论内容不能为空");
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "content": content,
            "type": type
        }),
        success: function (respond) {
            console.log(respond);
            debugger;
            if (respond.code == 200){

                 window.location.reload();
            } else {
                if(respond.code == 2003){
                    var isAccepted = confirm(respond.message);
                    if(isAccepted == true){
                        localStorage.setItem("closeable","true");
                        window.open("https://github.com/login/oauth/authorize?client_id=af839bc3aece69b5af99&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                    }
                }else{
                    alert(respond);
                }
            }
        },
        dataType: "json"
    });
}


//提交问题评论
function question_comment_commit(e) {
    var login = e.getAttribute("data-login");
    if(login == 0){
        alert("[ ！请先登录 ]");
        return;
    }

    var type = $("#question-comment-type").val();
    var parentId = $("#question-comment-parent").val();
    var content = $("#question-comment-content").val();

    post_comment(type, parentId, content);
}

//提交二级评论
function sub_comment_commit(e) {
    var login = e.getAttribute("data-login");
    if(login == 0){
        alert("[ ！请先登录 ]");
        return;
    }

    var type = 2;
    var parentId = e.getAttribute("data-parent-id");
    var content = $("#sub-edit-" + parentId).val();

    post_comment(type, parentId, content);
}


//若存在警告信息，则显示
$(function() {
    $('#myModal').modal('show');
});
