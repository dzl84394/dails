function go(url) {
    window.location = url;
}

function sub(id, value) {
    $("#" + id).val(value);
    $("." + id).removeClass("btn-info").addClass("btn-default");
    $("." + id + ":contains(" + value + ")").addClass("btn-info");
}

function deleteContact(url) {
    var isOK = confirm("Are you sure to delete?");

    if (isOK) {
        go(url);
    }
}

function deleteContext(url, context) {

    var isOK = confirm(context);
    if (isOK) {
        go(url);
    }
}

function deleteContext(url, id, context) {
    var isOK = confirm(context);
    if (isOK) {
        go(url + id);
    }
}

function po(obj, property, func) {
    // eval(obj.functionname);
    var str = '';
    for (prop in obj) {
        if (typeof (obj[prop]) != 'function') {
            if (property != false) {
                str += prop + ':' + obj[prop] + '\n';
            }
        } else if (func != false) {
            str += prop + ':' + typeof (obj[prop]) + '\n';
        }
    }
    return str;
}


function isRealNum(val) {
    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除，

    // if (val === "" || val == null) {
    //     return false;
    // }
    // if (!isNaN(val)) {
    //     //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
    //     //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
    //     return true;
    // } else {
    //     return false;
    // }
    if (parseFloat(val).toString() == "NaN") {

        return false;
    } else {
        return true;
    }
}


/**
 * 计算小数位数
 * @returns {number}
 */
Number.prototype.countDecimals = function () {
    if(Math.floor(this.valueOf()) === this.valueOf()) return 0;
    return this.toString().split(".")[1].length || 0;
}

/**
 * 保留几位小数，
 * 如果没有小数或位数不够，就不管，
 * 如果小数位数超了，就四舍五入
 * @param count
 * @returns {string}
 */
Number.prototype.toFixedDecimals = function (count) {
    if (isNaN(this.valueOf())){
        return "0";
    }
    var flo = parseFloat(this.valueOf());
    var val = flo.toString() ;
    var vals = val.split(".");
    if(vals.length==1||vals[1].length<=count){
        return val;
    }else{
        return flo.toFixed(count);
    }
}



//金额转大写
Number.prototype.moneyUp = function () {
    money = this.valueOf();
    //汉字的数字
    var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
    //基本单位
    var cnIntRadice = new Array('', '拾', '佰', '仟');
    //对应整数部分扩展单位
    var cnIntUnits = new Array('', '万  ', '亿  ', '兆 ');
    //对应小数部分单位
    var cnDecUnits = new Array('角', '分', '毫', '厘');
    //整数金额时后面跟的字符
    var cnInteger = '整';
    //整型完以后的单位
    var cnIntLast = '元  ';
    //最大处理的数字
    var maxNum = 999999999999999.9999;
    //金额整数部分
    var integerNum;
    //金额小数部分
    var decimalNum;
    //输出的中文金额字符串
    var chineseStr = '';
    //分离金额后用的数组，预定义
    var parts;
    if (money == '') { return ''; }
    money = parseFloat(money);
    if (money >= maxNum) {
        //超出最大处理数字
        return '';
    }
    if (money == 0) {
        chineseStr = cnNums[0] + cnIntLast + cnInteger;
        return chineseStr;
    }
    //转换为字符串
    money = money.toString();
    if (money.indexOf('.') == -1) {
        integerNum = money;
        decimalNum = '';
    } else {
        parts = money.split('.');
        integerNum = parts[0];
        decimalNum = parts[1].substr(0, 4);
    }
    //获取整型部分转换
    if (parseInt(integerNum, 10) > 0) {
        var zeroCount = 0;
        var IntLen = integerNum.length;
        for (var i = 0; i < IntLen; i++) {
            var n = integerNum.substr(i, 1);
            var p = IntLen - i - 1;
            var q = p / 4;
            var m = p % 4;
            if (n == '0') {
                zeroCount++;
            } else {
                if (zeroCount > 0) {
                    chineseStr += cnNums[0];
                }
                //归零
                zeroCount = 0;
                chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
            }
            if (m == 0 && zeroCount < 4) {
                chineseStr += cnIntUnits[q];
            }
        }
        chineseStr += cnIntLast;
    }
    //小数部分
    if (decimalNum != '') {
        var decLen = decimalNum.length;
        for (var i = 0; i < decLen; i++) {
            var n = decimalNum.substr(i, 1);
            if (n != '0') {
                chineseStr += cnNums[Number(n)] + cnDecUnits[i];
            }
        }
    }
    if (chineseStr == '') {
        chineseStr += cnNums[0] + cnIntLast + cnInteger;
    } else if (decimalNum == '') {
        chineseStr += cnInteger;
    }
    return chineseStr;
}

Number.prototype.moneyLow  = function (count) {
    if(count==null)count=3;

    money = this.valueOf().toString();

    if (money.indexOf('.') == -1) {
        integerNum = money;
        decimalNum = '';
    } else {
        parts = money.split('.');
        integerNum = parts[0];
        decimalNum = parts[1];
    }
    var l = integerNum.split("");
    var temp ="";
    var ll =l.length
    for (i = 0; i < ll; i++) {
        temp += l[i] + ((ll - i -1) % count == 0 && (i + 1) != ll ? "," : "");
    }

    if(decimalNum!=''){
        temp += "."+decimalNum;
    }

    return temp;

}

function trim(str, chars) {
    return ltrim(rtrim(str, chars), chars);
}

function ltrim(str, chars) {
    chars = chars || "\\s";
    return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
}

function rtrim(str, chars) {
    chars = chars || "\\s";
    return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
}

/**
 * 去掉尾巴的符号(逗号)
 * @param str
 * @returns
 */
function end_douhao(str, chars) {
    if (str.endWith(chars)) {
        str = str.substring(0, str.length - 1);
    }
    return str;
}

String.prototype.endWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substring(this.length - str.length) == str)
        return true;
    else
        return false;
    return true;
};
String.prototype.startWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    if (this.substr(0, s.length) == s)
        return true;
    else
        return false;
    return true;
}

String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
};


Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};


//
//判断值是否为空（null, undefined, "", " " 返回true）
function isEmpty(value) {
    if (value === null || value === undefined || $.trim("" + value).length == 0 || $.trim(value) == "") {
        return true;
    }
    return false;
}

function isNotEmpty(value) {
    return !isEmpty(value);
}

/**
 * 获取url
 * @param url
 * @returns
 */
function getUrlConnector(url) {
    if (url) {
        if (url.lastIndexOf("?") >= 0) {
            return "&";
        }
        return "?";
    }
}

/**
 * url 添加参数，如果已存在，就更新，如果不存在就添加
 * @param url
 * @param paramName
 * @param paramValue
 * @returns {*}
 */
function getURL(url, paramName, paramValue) {
    if (isEmpty(url)) {
        return null;
    }
    //paramValue可以为空，这时要删掉
    if (isEmpty(paramName)) {
        return url;
    }
    var connector = getUrlConnector(url);
    //如果已经有了，就不能加啦
    var has = true;
    if (url.indexOf("?") > 0) {
        var params = url.split("?")[1].split("&");
        for (var i = 0; i < params.length; i++) {
            if (params[i].split("=")[0] == paramName) {
                if (!isEmpty(paramValue)) {

                    url = url.replaceAll(params[i], paramName + '=' + paramValue);
                } else {
                    url = url.replaceAll("&" + params[i], '');//如果paramValue可以为空，这时要删掉
                }
                has = false;
            }
        }
    }
    if (has) {
        url = url + connector + paramName + "=" + paramValue;
    }
    return url;
}


function timeago(dateTimeStamp) {   //dateTimeStamp是一个时间毫秒，注意时间戳是秒的形式，在这个毫秒的基础上除以1000，就是十位数的时间戳。13位数的都是时间毫秒。
    var minute = 1000 * 60;      //把分，时，天，周，半个月，一个月用毫秒表示
    var hour = minute * 60;
    var day = hour * 24;
    var week = day * 7;
    var halfamonth = day * 15;
    var month = day * 30;
    var now = new Date().getTime();   //获取当前时间毫秒

    var diffValue = now - dateTimeStamp;//时间差
    if (diffValue < 0) {
        return '有点晕';
    }
    var minC = diffValue / minute;  //计算时间差的分，时，天，周，月
    var hourC = diffValue / hour;
    var dayC = diffValue / day;
    var weekC = diffValue / week;
    var monthC = diffValue / month;

    if (monthC >= 1 && monthC <= 3) {
        result = " " + parseInt(monthC) + "月前"
    } else if (weekC >= 1 && weekC < 4) {
        result = " " + parseInt(weekC) + "周前"
    } else if (dayC >= 1 && dayC < 7) {
        result = " " + parseInt(dayC) + "天前"
    } else if (hourC >= 1 && hourC < 24) {
        result = " " + parseInt(hourC) + "小时前"
    } else if (minC >= 1 && minC < 60) {
        result = " " + parseInt(minC) + "分钟前"
    } else if (diffValue >= 0 && diffValue <= minute) {
        result = "刚刚"
    } else {
        var datetime = new Date();
        datetime.setTime(dateTimeStamp);
        var Nyear = datetime.getFullYear();
        var Nmonth = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var Ndate = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        var Nhour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
        var Nminute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        var Nsecond = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        result = Nyear + "-" + Nmonth + "-" + Ndate
    }

    return result;

}

function timeagoyyyyMMdd(date) {
//2021-01-13T08:53:40.000+0000
//2021-01-13T16:53:40.000+08:00
    dateStr = date.substring(0, 19);
    dateStr = dateStr.replace("T", " ");
    dateStr = dateStr.replace(/-/g, '/'); //必须把日期'-'转为'/'
    var timestamp = new Date(dateStr).getTime();
    if (date.endWith("0000")) {//0时区
        timestamp += 1000 * 60 * 60 * 8;
    }
    return timeago(timestamp)
}
var jsonData = {}; // 定义一个空的JSON对象;
function addSelect(subType,divId){
        var listDiv = $("#"+divId+"")
        listDiv.empty();
        var temp = "";
        /*<![CDATA[*/
          for (var i = 0; i <  jsonData.length; i++) {

             if (jsonData[i].subType === subType) {

                 temp += '<option value="'+jsonData[i].code+'">'+jsonData[i].name+'</option>'
              }
           }
        listDiv.append(temp)
        /*]]>*/
}

function initSelect(){
    if ($.isEmptyObject(jsonData)) {
        var data = new Object();
        	data.clsType= 'common';
        	return new Promise(function(resolve, reject) {
                $.ajax({
                    type : "post",
                    url : "/confComm/findListByClsType",
                    contentType: 'application/json',
                    dataType: "json",
                    data: JSON.stringify({
                           data
                    }),
                    success : function(result) {
                        if(result.resultCode != 'SUCCESS'){
                            alert("接口异常")
                            return;
                        }
                        /*<![CDATA[*/
                        jsonData = result.data;
                        console.log(jsonData);
                        /*]]>*/
                        resolve(); // 异步操作完成后resolve Promise
                    },
                     error: function(jqXHR, textStatus, errorThrown) {
                         console.error('Ajax请求失败');
                         reject();
                     }
                })
        	})
    }

}

function setActive(nav, sidebar) {

    $("#nav_subPorject").removeClass("active");
    $("#nav_device").removeClass("active");


    var dnav = $("#" + nav)
    dnav.addClass("active");


    if (sidebar == null) return;



}


function fun_date(num) {
    var dd = new Date();
    dd.setDate(dd.getDate() + num);//获取num天后的日期
    var y = dd.getFullYear();
    var m = (dd.getMonth() + 1) < 10 ? "0" + (dd.getMonth() + 1) : (dd.getMonth() + 1);//获取当前月份的日期，不足10补0
    var d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate();//获取当前几号，不足10补0
    return y + "-" + m + "-" + d;
}

function getParameterValue(parameter){
    // 获取包含参数的查询字符串部分
    const queryString = window.location.search;
    // 使用URLSearchParams对象解析查询字符串
    const urlParams = new URLSearchParams(queryString);
    // 获取特定参数的值
    const parameterValue = urlParams.get(parameter);
    return parameterValue;
}

function setParameterValue(key,value){
    // 获取包含参数的查询字符串部分
    const queryString = window.location.search;
      // 修改currentPage为2
      var newUrl = url.replace(/(\?|&)currentPage=\d+/, '$1currentPage='+value);

      // 重定向到新的URL
      window.location.href = newUrl;
}



function findSubProjectList(projectSn){
    var data = new Object();

    $.ajax({
        type : "post",
        url : "/subProject/findList",
        contentType: 'application/json',
        dataType: "json",
        data : JSON.stringify({
            data
        }),
        async : false,
        success : function(result) {
            let confs = result.data;
            // 找到datalist元素
            let data1List = $(projectSn);
            // 清空datalist元素
            data1List.empty();


            // 使用each方法动态加载数据到datalist中
            $.each(confs, function(index, value) {
                var option = $("<option>").text(value.name).attr("value", value.nameEn	);
                data1List.append(option);
            });
        }
    })
}