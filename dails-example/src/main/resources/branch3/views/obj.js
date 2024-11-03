
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/{objname}/findPage",
        contentType: 'application/json',
        dataType: "json",
        data : JSON.stringify({
            data
        }),
        async : false,
        success : function(result) {
            if(result.resultCode != 'SUCCESS'){
                alert("接口异常")
                return;
            }
            var current = result.data.current;//当前页
            currentPage =current;
			if(currentPage<1)currentPage=1;
			var total = result.data.total;//总条数
			var pages =result.data.pages;
 			var listDiv = $("#tbodylist")

            listDiv.empty();
            var temp = "";

			/*<![CDATA[*/
		    for (var i = 0; i <  result.data.records.length; i++) {
			    var index = result.data.records[i];
				temp += {temp1}
				+"<td><a class='btn btn-info btn-sm' href='/{objname}/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/{objname}/editView?id="+index.id+"'>编辑</a>"
				+"<a class='btn btn-danger btn-sm' onclick=\"deleteObj("+index.id+")\">删除</a>"
				+"</td></tr>"
			}
			listDiv.append(temp)
			var total = result.data.total;
                    // 将数据渲染到页面
                    // 调用分页函数.参数:当前所在页, 总页数(用总条数 除以 每页显示多少条,在向上取整), ajax函数
            setPage(currentPage, pages, findPage);
				/*]]>*/
		}
	})
}

 /**
     *
     * @param pageCurrent 当前所在页
     * @param pageSum 总页数
     * @param callback 调用ajax
     */
function setPage(pageCurrent, pageSum, callback) {
    $(".pagination").bootstrapPaginator({
        //设置版本号
        bootstrapMajorVersion : 3,
        // 显示第几页
        currentPage : pageCurrent,
        // 总页数
        totalPages : pageSum,
        //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
        onPageClicked : function(event, originalEvent, type, page) {
            // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
            currentPage = page
            callback && callback()
        }
    })
}

function save(){
    var data = new Object();
    {temp2}
    $.ajax({
        type: "post",
        url: "/{objname}/save",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            data
        }),
        success: function (result) {
            if(result.resultCode != 'SUCCESS'){
                alert("接口异常")
                return;
            }
            go("/{objname}/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/{objname}/findById",
        contentType: 'application/json',
        dataType: "json",
        data: {
            id:$("#id").val()
        },
        success: function (result) {
            if(result.resultCode != 'SUCCESS'){
                alert("接口异常")
                return;
            }
            var index = result.data;

             {temp3}


        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/{objname}/findById",
        contentType: 'application/json',
        dataType: "json",
        data: {
            id:$("#id").val()
        },
        success: function (result) {
            if(result.resultCode != 'SUCCESS'){
                alert("接口异常")
                return;
            }
            var index = result.data;
            {temp4}

        }
    })
}


function deleteObj(id){
    var data = new Object();
    data.id = id;
    var isOK = confirm("是否确定需要删除?");
    if (isOK) {
        $.ajax({
            type: "post",
            url: "/{objname}/deleteById",
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify({
                data
            }),
            success: function (result) {
                if(result.resultCode != 'SUCCESS'){
                    alert("接口异常")
                    return;
                }
                go("/{objname}/indexView");
            }
        })
   }
}

setActive("nav_{objname}");