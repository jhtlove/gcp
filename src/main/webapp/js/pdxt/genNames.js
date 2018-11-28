var waitArr = [];//所有排队; 显示顺序要按照按编号排序？
//从index位置开始删除n个元素：waitArr.splice(index,n) //splic(index,1)
var displayArr = [];//显示队列
var calledArr = [];//已呼队列
// var returnArr = [];
var showIndex = 0;//指向下一次将要显示的内容
var lastCalled = "";//上一次 已呼未做 文本内容
var isMoving = false;
//滚动中，文本内容改变？
function genCalled(names) {
    // var names = ["患者1","患者2","患者3"];//   
    var namesText = "";
    if(names.length > 0){
        //names.length-1
        for(var i=0;i<20;i++){
            namesText = namesText + names[i].BRXM + ", ";
        }
        namesText = namesText + names[i].BRXM;
        // namesText = names.join(", ");
    }
    if(lastCalled !== namesText){
        //内容变化了,重新滚动？
        lastCalled = namesText;
    }
    $("span.called-names").text(namesText);
    elem = document.getElementById("marquee");
    elemWidth = elem.offsetWidth;
    leftLocation = document.getElementById("left-box").offsetLeft + document.getElementById("left-box").offsetWidth - elemWidth;
    if(!isMoving){
        // 首次递归调用
        moveElement();
        isMoving = true;
    }
}

//固定显示前几个
function disFrontWait(){
    genFrontDisArr();//取数据放到 displayArr
    var rows = displayArr;
    if(rows.length === 0){
        console.log("displayArr is empty.");
    }

    //如果等待为空，清空当前页面显示
    var names = $(".name");
    var numbers = $(".number");
    var rooms = $(".room");
    var hzlx = $(".hzlx");
    names.text("");//清空
    numbers.text("");
    rooms.text("");
    hzlx.text("");
    //displayArr的长度，取数据的时候已经设置过，要么正好和页面能显示个数一致，要么小于
    for(var i=0;i<rows.length;i++){
        var theName = rows[i].BRXM;
        var theNumber = rows[i].HYBM;
        var theRoom = "彩超一室";//检查房间???
        var theLX = "排队中";//排队状态？？？
        // if(theLX == ???){
        //*toggleClass("hzlx-called") 切换*/
        //hasClass()
        //      $(hzlx[i]).addClass("hzlx-called");
        //      $(hzlx[i]).removeClass("hzlx-called");
        // }else{

        // }
        $(names[i]).text(theName);
        $(numbers[i]).text(theNumber);
        $(rooms[i]).text(theRoom);
        $(hzlx[i]).text(theLX);

    }
}

function disWaited() {
    genDisplayArr();
    var rows = displayArr;
    if(rows.length === 0){
        console.log("displayArr is empty.");
    }

    //如果等待为空，清空当前页面显示
    var names = $(".name");
    var numbers = $(".number");
    var rooms = $(".room");
    var hzlx = $(".hzlx");
    names.text("");//清空
    numbers.text("");
    rooms.text("");
    hzlx.text("");
    //displayArr的长度，取数据的时候已经设置过，要么正好和页面能显示个数一致，要么小于
    for(var i=0;i<rows.length;i++){
        var theName = rows[i].BRXM;
        var theNumber = rows[i].HYBM;
        var theRoom = "彩超一室";//检查房间???
        var theLX = "排队中";//排队状态？？？
        // if(theLX == ???){
        //*toggleClass("hzlx-called") 切换*/
        //hasClass()
        //      $(hzlx[i]).addClass("hzlx-called");
        //      $(hzlx[i]).removeClass("hzlx-called");
        // }else{

        // }
        $(names[i]).text(theName);
        $(numbers[i]).text(theNumber);
        $(rooms[i]).text(theRoom);
        $(hzlx[i]).text(theLX);

    }
    
}
// var testData = [{"ZYBM":"TJ1001","BRXM":"张三","HYBM":"1001"},{"ZYBM":"TJ1001","BRXM":"张三三","HYBM":"1002"},{"ZYBM":"TJ1001","BRXM":"李四","HYBM":"1003"}]
// disWaited(testData);

function genTimeText(){
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    var hour = today.getHours();
    var minute = today.getMinutes();
    if(month < 10){
        month = "0" + month;
    }
    if(day < 10){ 
        day = "0" + day;
    }
    if(hour >0 && hour < 10){
        hour = "0" + hour;
    }
    if(minute>0 && minute < 10){
        minute = "0" + minute;
    }
    return year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分";
}

function setTimeText(){
    $(".top .time-text").text(genTimeText);
}

setTimeText();//初始设置一次
setInterval(setTimeText,5000);//每隔5s检查 更新一次时间文字


//已呼未做，内容文字 移动 暂停 效果；文字过长，显示不下？
var elem = document.getElementById("marquee");
var elemWidth = elem.offsetWidth;//有内容才有宽度，开始没内容，宽度为0
var leftLocation = document.getElementById("left-box").offsetLeft + document.getElementById("left-box").offsetWidth - elemWidth;
var rightLocation = document.getElementById("move-box").offsetWidth; 

var current = rightLocation;

function moveElement(){
    if(current > leftLocation){
        current -= 1;//移动步长 1px
        if(current < leftLocation){
            //放置最边上，不移除出去了
            current = leftLocation;
        }
        elem.style.left = current + "px";//jquery .css("left", xx)
        setTimeout(moveElement,20);//20ms 移动一下
    }else{
        //移到边上了;暂停，重置位置
        current = rightLocation;
        //setTimeout(moveElement,5000);//停留5s再移动
        setTimeout(moveElement,20);//20ms 移动一下
    }
}



// $("#marquee").marquee({
//     duration: 5000,
//     direction: 'right'
// });