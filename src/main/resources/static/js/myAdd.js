var data = [];
var mouse = new MouseMove(".item",".addbg","addbg");
let flag = [false,false,false];
let dataList = [];
$(function(){
    $("input").attr("autocomplete","off");
    $(".add-infor").click(function(){
        let index = $(this);
        $(index[0].nextElementSibling).show();
    });
    $(".query-city").click(function (){
        if(flag[0]){
            data = dataList[0];
            return false;
        }
        $.ajax({
            url: "/admin/query-city",
            type: "post",
            data: '',
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    console.log(json);
                    data = json.data;
                    dataList[0] = data;
                    flag[0] = true;
                    console.log(data);
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回时产生未知的异常",xhr.message);
            }
        });
    });
    $(".query-airport").click(function (){
        if(flag[1]){
            data = dataList[1];
            return false;
        }
        $.ajax({
            url: "/admin/query-airport",
            type: "post",
            data: '',
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    data = json.data;
                    dataList[1] = data;
                    flag[1] = true;
                    console.log(data);
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回时产生未知的异常",xhr.message);
            }
        });
    });
    $(".query-type").click(function (){
        if(flag[2]){
            data = dataList[2];
            return false;
        }
        $.ajax({
            url: "/admin/query-type",
            type: "post",
            data: '',
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    data = json.data;
                    dataList[2] = data;
                    flag[2] = true;
                    console.log(data);
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回时产生未知的异常",xhr.message);
            }
        });
    });
    $(".form-submit").click(function(){
        if(checkEmppty($(this).parent().siblings())){
            return false;
        }
        let index = $(this).parents("form");
        let formData = $("."+index[0].classList[1]).serialize();
        formData = decodeURIComponent(formData,true);
        // console.log($(index[0].serialize()));
        if(index[0].classList[1] == "add-city"){
            console.log("增加城市");
            console.log(formData);
            $.ajax({
                url: "/admin/add-city",
                type: "post",
                data: formData,
                dataType: "json",
                // contentType : 'application/json',
                success:function (json){
                    hideLebal();
                    if(json.state == 200){
                        alert("添加城市成功");
                        console.log(json);
                    }else{
                        alert(json.message);
                    }
                },
                error:function (xhr){
                    hideLebal();
                    alert("添加城市时产生未知的异常",xhr.message);
                }
            });
        }else if(index[0].classList[1] == "add-air"){
            console.log("增加机场");
            console.log(formData);
            $.ajax({
                url: "/admin/add-airport",
                type: "post",
                data: formData,
                dataType: "json",
                // contentType : 'application/json',
                success:function (json){
                    hideLebal();
                    if(json.state == 200){
                        alert("添加机场成功");
                        console.log(json);
                    }else{
                        alert(json.message);
                    }
                },
                error:function (xhr){
                    hideLebal();
                    alert("添加机场时产生未知的异常",xhr.message);
                }
            });
        }else if(index[0].classList[1] == "add-terminal"){
            console.log("增加航站楼");
            console.log(formData);
            $.ajax({
                url: "/admin/add-terminal",
                type: "post",
                data: formData,
                dataType: "json",
                // contentType : 'application/json',
                success:function (json){
                    hideLebal();
                    if(json.state == 200){
                        alert("添加航站楼成功");
                        console.log(json);
                    }else{
                        alert(json.message);
                    }
                },
                error:function (xhr){
                    hideLebal();
                    alert("添加航站楼时产生未知的异常",xhr.message);
                }
            });
        }else if(index[0].classList[1] == "add-flight"){
            console.log("增加航班");
            console.log(formData);
            $.ajax({
                url: "/admin/add-flight",
                type: "post",
                data: formData,
                dataType: "json",
                // contentType : 'application/json',
                success:function (json){
                    hideLebal();
                    if(json.state == 200){
                        alert("添加航班成功");
                        console.log(json);
                    }else{
                        alert(json.message);
                    }
                },
                error:function (xhr){
                    hideLebal();
                    alert("添加航班时产生未知的异常",xhr.message);
                }
            });
        }
    });
});
function hideLebal(){
    $(".modal").find("aside").hide();
    $(".modal").find("input").val('');
    $(".modal").hide(400);
}
function checkEmppty(list){
    let sectionList = list;
    let flag = false;
    for(let i = 0;i<sectionList.length;i++){
        // console.log(sectionList[i].nextElementSibling);
        // 如果aside存在，并且当前input如果为空，就显示aside
        if(sectionList[i].nextElementSibling!=null){
            if(sectionList[i].lastElementChild.value == ""&&sectionList[i].nextElementSibling.localName == "aside"){
                $(sectionList[i].nextElementSibling).show();
                flag = true;
            }else if(sectionList[i].lastElementChild.value != ""&&sectionList[i].nextElementSibling.localName == "aside"){
                $(sectionList[i].nextElementSibling).hide();
            }
        }
    }
    return flag;
}
// 获取城市数据，添加标签
function getContent(obj){
    var kw = jQuery.trim($(obj).val());
    if(kw == ""){
        $(".append").hide();
        $(".append-ul").html("");
        return false;
    }
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.cityID.search(patt)>=0||i.cityName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon()'><span>"+i.cityID+"</span>"+"<span>"+i.cityName+"</span></li>";
        }
    }
    if(html != ""){
        $(".append").show(400);
        $(".append-ul").html(html);
    }else{
        $(".append").hide(400);
        $(".append-ul").html("");
    }
    mouse.mouseListen();
}
// 点击效果
function getCon(){
    var value = mouse.listChildren();
    $("#MemberAirCityID").val(value[0].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}
// 获取航站楼数据，添加标签
function getContent2(obj){
    var kw = jQuery.trim($(obj).val());
    if(kw == ""){
        $(".append").hide();
        $(".append-ul").html("");
        return false;
    }
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.cityID.search(patt)>=0||i.airportID.search(patt)>=0||i.airportName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon2()'><span>"+i.cityID+"</span>"+"<span>"+i.airportID+"</span>"+"<span>"+i.airportName+"</span>"+"</li>";
        }
    }
    if(html != ""){
        $(".append").show(400);
        $(".append-ul").html(html);
    }else{
        $(".append").hide(400);
        $(".append-ul").html("");
    }
    mouse.mouseListen();
}
// 点击效果
function getCon2(){
    var value = mouse.listChildren();
    $("#MemberTerminalAirID").val(value[1].innerHTML);
    $("#MemberTerminalName").val(value[2].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}
// 获取航班数据，添加标签
function getContent3(obj){
    var kw = jQuery.trim($(obj).val());
    if(kw == ""){
        $(".append").hide();
        $(".append-ul").html("");
        return false;
    }
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.typeID.search(patt)>=0||i.typeName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon3()'><span>"+i.typeID+"</span>"+"<span>"+i.typeName+"</span></li>";
        }
    }
    if(html != ""){
        $(".append").show(400);
        $(".append-ul").html(html);
    }else{
        $(".append").hide(400);
        $(".append-ul").html("");
    }
    mouse.mouseListen();
}
// 点击效果
function getCon3(){
    var value = mouse.listChildren();
    $("#MemberTypeID").val(value[0].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}