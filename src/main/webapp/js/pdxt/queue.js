var isStart = true;
var isOver = true;//显示完一轮
var newWaitArr = [];//后台新来的等待队列数据
var newCalledArr = [];//后台新来的已呼数据

//获取后台数据,追加新数据，生成已呼队列
function genCalledArr(theData){
    var calledLen = calledArr.length;
    if(calledLen == 0){
        for(var i=0;i<theData.length;i++){
            calledArr.push(theData[i]);//放入已呼未做队列
        }
    }else{
        for(var j=0;j<theData.length;j++){
            var isNew = true;
            for(var k=0;k<calledLen;k++){
                //只用编号就够了？
                if(theData[j].HYBM == calledArr[k].HYBM){
                    //编号已经在已呼数组中；
                    // console.log(theData[j].HYBM + " is in the queue");
                    isNew = false;
                    break;
                }
            }
            if(isNew){
                calledArr.push(theData[j]);
            }
        }
    }
    //calledArr需要按照编号排序?  每次追加到末尾，保持前面不变? 已呼叫如何出队？
}

function sortZYBM(a,b){
    return a.ZYBM - b.ZYBM;//从小到大排序
}

//获取后台数据,生成等待队列
function genWaitArr(theData){

}
function genWaitArr_bak(theData){
    var waiterLen = waitArr.length;
    var tempArr = [];

    if(waiterLen == 0){
        for(var i=0;i<theData.length;i++){
            waitArr.push(theData[i]);//
        }
    }else{
        //排队队列不一定有序，全部比较一次，看是否在排队队列里面
        // var lastWaitElem = waitArr[waiterLen-1];
        // var lastDataElem = theData[theData.length-1];
        // if(lastWaitElem.HYBM >= lastDataElem.HYBM){
        //     //新来数据，全是已经在waitArr队列的;过号重新入队？
        //     console.log("no new element come");
        //     return;
        // }
        // for(var j=0;j<theData.length;j++){
        //     if(lastWaitElem.HYBM < theData[j].HYBM){
        //         waitArr.push(theData[j]);//追加新号源;过号如何重新入队？
        //     }
        // }
        for(var j=0;j<theData.length;j++){
            var isNew = true;
            for(var k=0;k<waiterLen;k++){
                //只用编号就够了？
                if(theData[j].HYBM == waitArr[k].HYBM){
                    //编号已经在排队数组中；
                    // console.log(theData[j].HYBM + " is in the queue");
                    isNew = false;
                    break;
                }
            }
            if(isNew){
                waitArr.push(theData[j]);//添加未出现的新数据
            }
        }
        //删除在新队列中不存在的数据
        for(var x=0;x<waitArr.length;x++){
            var isIn = false;
            for(var y=0;y<theData.length;y++){
                if(waitArr[x].HYBM == theData[y].HYBM){
                    isIn = true;
                    tempArr.push(waitArr[x]);
                    break;
                }
            }
            if(!isIn){
                if(x < showIndex){
                //删除showIndex之前的元素
                 showIndex = showIndex - 1;
                 }else if(showIndex == x){
            //相等:不为0 且 为最后一个元素
                if(showIndex > 0 && showIndex == waitArr.length-1){
                     showIndex = showIndex - 1;
                    }
                }
            }
        }
        waitArr = tempArr;//需要逐个元素复制？？？
    }
    // if(waitArr.length > 1){
    //     //sort 在原数组上排序;按照资源编码排序？前面已经呼叫的，重新入队？
    //     waitArr.sort(sortZYBM);
    // }
}

// 通过showIndex 循环从waitArr中取数据到displayArr
function genDisplayArr(){
    displayArr = [];//清空
    if(waitArr.length == 0){
        isOver = true;
        console.log("waitArr is empty.");
        return;
    }
    var disLen = $(".name").length;
    if(showIndex >= waitArr.length){
        showIndex = 0;//越界了，从头开始
        isOver = true;
        console.log("showIndex is over length:" + showIndex);
    }
    if(waitArr.length -showIndex <= disLen){
        //排队队列元素全部显示出来
        for(var i=showIndex;i<waitArr.length;i++){
            displayArr.push(waitArr[i]);
        }
        showIndex = 0;//showIndex从头开始
        isOver = true;
        console.log("one round is over")
        // waitArr = [];//全部已经显示；清空
    }else{
        //显示其中一部分
        for(var j=showIndex;j<disLen+showIndex;j++){
            displayArr.push(waitArr[j]);
        }
         showIndex=showIndex + disLen;//showIndex后移
        // waitArr.splice(0,disLen);//不出队，循环显示？
    }
    //展示完一圈，就更新数据（有可能没来新数据，或者来的最新数据没有变化，就继续展示旧数据）
    if(isOver){
            console.log("isOver");
            waitArr = newWaitArr;//显示完一圈，更新数组
            calledArr = newCalledArr;
            genCalled(calledArr);//已呼未做，整串更新
            // genWaitArr(newWaitArr);
            isOver = false
        }
}

//每次都从waitArr取前几个
function genFrontDisArr(){
    displayArr = [];//清空
    if(waitArr.length == 0){
        console.log("waitArr is empty.");
        return;
    }
    var disLen = $(".name").length;
    var len = disLen < waitArr.length ? disLen:waitArr.length;
    for(var i=0;i<len;i++){
        displayArr.push(waitArr[i]);
    }
}

function removeOne(){
    if(waitArr.length > 0){
        var removeElem = waitArr.shift();//shift()删除队首元素
        if(showIndex > 0){
            //如果showIndex=0 就不要移动
           showIndex = showIndex - 1; 
        }
        
        console.log("remove:" + removeElem.BRXM);
    }
}
//出队，在等待队列，状态为2 ？？？ 状态变化如何感知？
function removeGiven(elem){
    var index = -1;
    for(var i=0;i<waitArr.length;i++){
        //按照资源编码来删除
        if(waitArr[i].ZYBM == elem.ZYBM){
            index = i;
            break;
        }
    }
    if(index != -1){
        if(index < showIndex){
            //删除showIndex之前的元素
            showIndex = showIndex - 1;
        }else if(showIndex == index){
            //相等:不为0 且 为最后一个元素
            if(showIndex > 0 && showIndex == waitArr.length-1){
                showIndex = showIndex - 1;
            }
        }
        waitArr.splice(index,1);//删除一个元素
    }
}

$(function () {
    var socket;
    if(typeof(WebSocket) == "undefined"){
        alert("您的浏览器不支持WebSocket!");
        return;
    }
    var theType = getType();
    switch(theType){
        case 'TJ1001':
                $(".top .window-text").text("男区窗口");
            break;
        case 'TJ1002':
            $(".top .window-text").text("女区窗口");
            break;
        case 'TJ1003':
             $(".top .window-text").text("综合区窗口");
            break;
        default:
            console.error("theType is not correct: " + theType);
            return;

    }
    //实现化WebSocket对象，指定要连接的服务器地址与端口
    socket = new WebSocket("ws://192.168.3.20:4080/Tjhjsocket?tvid=" + theType);
    //打开事件
    socket.onopen = function() {
        console.log("Socket 已打开");
        //socket.send("这是来自客户端的消息" + location.href + new Date());
    };
    //获得消息事件
    socket.onmessage = function(msg) {
        //获取队列信息，展示排队情况
        // alert(msg.data);
        // console.log(typeof msg.data);//字符串
        // console.log(msg.data);
        var theData = JSON.parse(msg.data);
        //空记录，要传空数组: []
        newWaitArr = theData.xml1;//等待
        newCalledArr = theData.xml2;//已呼

        if(isStart){
            //第一次显示
            waitArr = newWaitArr;
            disWaited();
            //disFrontWait();
            genCalled(calledArr);//已呼未做，数据也会变化
            isStart = false;
        }

    };
    //关闭事件
    socket.onclose = function() {
        alert("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function() {
        alert("发生了错误");
    }

    setInterval(disWaited,15000);//15s 刷新一次显示
   // setInterval(disFrontWait,15000);//15s 刷新一次显示
    // setInterval(removeOne,10000);//模拟定时移除一个
    //模拟改变called文本内容？ names

});

//tvid
    function send(message) {
        if (!window.WebSocket) {
            return;
        }
        if (socket.readyState == WebSocket.OPEN) {
            socket.send(message);
        } else {
            alert("The socket is not open.");
        }
    }

//发送消息
$("#btnSend").click(function() {
    socket.send("这是来自客户端的消息" + location.href + new Date());
});

//关闭
$("#btnClose").click(function() {
    socket.close();
});

function getType(){
    var theLink = window.location.href;
    var arr = theLink.split("tvid=");
    var theType = '';
    if(arr.length === 2){
        theType = arr[1];
    }else{
        alert("url is not correct: " + theLink);
    }
    return theType;
}
