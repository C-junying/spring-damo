$(function(){
    $(".add-infor").click(function(){
        let index = $(this);
        $(index[0].nextElementSibling).show();
    });
    $(".form-submit").click(function(){
        if(checkEmppty($(this).parent().siblings())){
            return false;
        }
        let index = $(this).parents("form");
        let formData = $("."+index[0].classList[1]).serialize();
        // console.log($(index[0].serialize()));
        if(index[0].classList[1] == "add-city"){
            console.log("增加城市");
            console.log(formData);
        }else if(index[0].classList[1] == "add-air"){
            console.log("增加机场");
            console.log(formData);
        }else if(index[0].classList[1] == "add-flight"){
            console.log("增加航班");
            console.log(formData);
        }
    });
});
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