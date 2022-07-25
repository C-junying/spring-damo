
var slideIndex = 0;     //图片指定位置
//图片
var imgs = ["/img/air01.jpg","/img/air02.jpg","/img/air04.jpg","/img/air05.jpg","/img/air06.jpg"];
//获取点，并插入点
var dotlist = document.getElementsByClassName("dots");
for(let i = 0;i<imgs.length;i++){
    let dot = document.createElement("span");
    dot.className = "dot";
    dot.addEventListener("click",dotOnclick.bind(this,i));
    dotlist[0].appendChild(dot);
}
//获取img，dot
var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
//递归定时
function showSlides() {
    slideIndex++;
    if (slideIndex > imgs.length) {slideIndex = 1}    
    removeStyle();
    setTimeout(showSlides, 5000); // 切换时间为 2 秒
}
//点点击更换图片
function dotOnclick(index){
    slideIndex=index+1;
    removeStyle();
}
//更换点，图片
function removeStyle(){
    for (let i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace("active", "");
    }
	slides[0].src=imgs[slideIndex-1];
    dots[slideIndex-1].className += " active";
}
showSlides();
