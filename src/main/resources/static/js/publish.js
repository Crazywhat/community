function add_tag_value(e){
    var inputTag = $("#tag");
    var tagValue = e.getAttribute("data-tag-value");
    var currentTags = inputTag.val();
    if(!currentTags || currentTags == ""){
        inputTag.val(tagValue);
    }else{
        if($.inArray(tagValue,currentTags.toString().split(',')) == -1)
            inputTag.val(currentTags + ',' + tagValue);
    }
}


$(function () {

    //标签选择区的关闭
    $(document).bind("click",function(e){
        var e = e || window.event;    //事件对象，兼容IE
        var target = e.target || e.srcElement;  //源对象，兼容火狐和IE

        var tagsChooseArea = $("#tags-choose-area");

        //点击标签输入框，显示标签选择区
        if(target.id && target.id == "tag"){
            tagsChooseArea.removeClass("d-none");
            return;
        }

        //点击标签选择区，不操作
        while(target){
            if (target.id && target.id == "tags-choose-area"){    //循环判断至根节点，防止点击的是#selected和它的子元素
                return;
            }
            target = target.parentNode;
        }

        //点击其他区域，关闭标签选择区
        if(!tagsChooseArea.hasClass("d-none")){
            tagsChooseArea.addClass("d-none");
        }
    })
});




