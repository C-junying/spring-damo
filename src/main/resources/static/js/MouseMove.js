// target是指li标签，operation是要添加的类选择器(也是给li添加的)
function MouseMove(target,operation1,operation2){
    this.target = target;
    this.operation1 = operation1;
    this.operation2 = operation2;
    // 移动到上一行
    this.movePrev = function(){
        var index = $(this.operation1).prevAll().length;
        if(index == 0){
            // 当前在第一行向上移动，移除背景，选择最后一个添加背景
            $(this.target).removeClass(this.operation2).eq($(this.target).length-1).addClass(this.operation2);
        }else{
            // 移到上一个位置，添加背景
            $(this.target).removeClass(this.operation2).eq(index-1).addClass(this.operation2);
        }
    };
    // 移动到下一行
    this.moveNext = function(){
        // 判断是否有类选择器
        if($(this.target).hasClass(this.operation2)){
            var index = $(this.operation1).prevAll().length;
            if(index == $(this.target).length-1){
                // 当前在最后一行向下移动，选取第一个，并添加背景
                $(this.target).removeClass(this.operation2).eq(0).addClass(this.operation2);
            }else{
                // 移到下一个位置，添加背景
                $(this.target).removeClass(this.operation2).eq(index+1).addClass(this.operation2);
            } 
        }else{
            // 移除背景，选取第一个，并添加背景
            $(this.target).removeClass(this.operation2).eq(0).addClass(this.operation2);
        }
    };
    // li标签下所有数据
    this.listChildren = function(){
        return $(this.operation1).children();
    }
    // 只监听鼠标进入与退出
    this.mouseListen = function(){
        var ul = document.querySelectorAll(this.target);
        let key = this.target;
        let value = this.operation2;
        for(let i of ul){
            // 鼠标进入
            i.addEventListener("mouseenter",function(e){
                $(key).removeClass(value);
                e.srcElement.classList.add(value);
            });
            // 鼠标离开
            i.addEventListener("mouseleave",function(e){
                e.srcElement.classList.remove(value);
            });
        }
    }
}