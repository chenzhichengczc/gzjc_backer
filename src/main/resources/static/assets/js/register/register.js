/**
 * 非空校验
 * @param value 传入的是对象的值
 */
function notNull(value) {
    if(value == null || value == "" || value == undefined){
        return false;
    }
    return true;
}

$(function () {

    $(".button").click(function () {
        if(notNull($("#username").val()) && notNull($("#password").val())){
            var data = {"username":$("#username").val(),"password":$("#password").val()}
            $.post("../admin/registerAdmin",data,function (result) {
                if(result.code == 0){
                    alert("注册成功")
                    //3秒后跳转过去
                    window.setTimeout(window.location.href="index",3000)
                }else {
                    alert("插入管理者失败,请联系开发者")
                }
            },"json")
        }else {
            alert("信息为空,请确认!")
            return;
        }
    })
})





