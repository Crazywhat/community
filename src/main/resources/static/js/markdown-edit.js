$(function() {
    editormd("description-editor", {
        width: "100%",
        height: "350px",
        path   : "/lib/",
        watch : false,
        placeholder : "请输入你的问题描述...",
        imageUpload    : true,
        imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "./file/upload"
    });
});
