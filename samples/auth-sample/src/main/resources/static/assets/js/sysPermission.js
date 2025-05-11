
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/sysPermission/findPage",
        contentType: 'application/json',
        dataType: "json",
        data : JSON.stringify({
            data
        }),
        async : false,
        success : function(result) {
            if(result.resultCode != '200'){
                alert("接口异常")
                return;
            }
            var current = result.data.current;//当前页
            currentPage =current;
			if(currentPage<1)currentPage=1;
			var total = result.data.total;//总条数
			var pages =result.data.pages;
			if(pages<1)pages=1;


 			var listDiv = $("#tbodylist")

            listDiv.empty();
            var temp = "";

			/*<![CDATA[*/
		    for (var i = 0; i <  result.data.records.length; i++) {
			    var index = result.data.records[i];
				temp += "<tr>"
                 + "<td>"+index.id+"</td>"
                 + "<td>"+index.projectName+"</td>"
                 + "<td>"+index.serviceName+"</td>"
                 + "<td>"+index.parentId+"</td>"
                 + "<td>"+index.mtype+"</td>"
                 + "<td>"+index.httpMethod+"</td>"
                 + "<td>"+index.sort+"</td>"
                 + "<td>"+index.status+"</td>"
                 + "<td>"+index.path+"</td>"
                 + "<td>"+index.remarks+"</td>"

				+"<td><a class='btn btn-info btn-sm' href='/sysPermission/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/sysPermission/editView?id="+index.id+"'>编辑</a>"
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
      data.id = $('#id').val();
  data.projectName = $('#projectName').val();
  data.serviceName = $('#serviceName').val();
  data.parentId = $('#parentId').val();
  data.mtype = $('#mtype').val();
  data.httpMethod = $('#httpMethod').val();
  data.sort = $('#sort').val();
  data.status = $('#status').val();
  data.path = $('#path').val();
  data.remarks = $('#remarks').val();

    $.ajax({
        type: "post",
        url: "/sysPermission/save",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            data
        }),
        success: function (result) {
            if(result.resultCode != '200'){
                alert("接口异常")
                return;
            }
            go("/sysPermission/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/sysPermission/findById",
        contentType: 'application/json',
        dataType: "json",
        data: {
            id:$("#id").val()
        },
        success: function (result) {
            if(result.resultCode != '200'){
                alert("接口异常")
                return;
            }
            var index = result.data;

               $("#id").text( index.id);
  $("#projectName").text( index.projectName);
  $("#serviceName").text( index.serviceName);
  $("#parentId").text( index.parentId);
  $("#mtype").text( index.mtype);
  $("#httpMethod").text( index.httpMethod);
  $("#sort").text( index.sort);
  $("#status").text( index.status);
  $("#path").text( index.path);
  $("#remarks").text( index.remarks);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/sysPermission/findById",
        contentType: 'application/json',
        dataType: "json",
        data: {
            id:$("#id").val()
        },
        success: function (result) {
            if(result.resultCode != '200'){
                alert("接口异常")
                return;
            }
            var index = result.data;
              $("#id").val( index.id);
  $("#projectName").val( index.projectName);
  $("#serviceName").val( index.serviceName);
  $("#parentId").val( index.parentId);
  $("#mtype").val( index.mtype);
  $("#httpMethod").val( index.httpMethod);
  $("#sort").val( index.sort);
  $("#status").val( index.status);
  $("#path").val( index.path);
  $("#remarks").val( index.remarks);


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
            url: "/sysPermission/deleteById",
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify({
                data
            }),
            success: function (result) {
                if(result.resultCode != '200'){
                    alert("接口异常")
                    return;
                }
                go("/sysPermission/indexView");
            }
        })
   }
}

//setActive("nav_sysPermission");