
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/serviceAdmin/findPage",
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
                 + "<td>"+index.iocUrl+"</td>"
                 + "<td>"+index.serviceType+"</td>"
                 + "<td>"+index.subProjectSn+"</td>"
                 + "<td>"+index.subServiceSn+"</td>"
                 + "<td>"+index.name+"</td>"
                 + "<td>"+index.detail+"</td>"
                 + "<td>"+index.serviceAdminUrl+"</td>"

				+"<td><a class='btn btn-info btn-sm' href='/serviceAdmin/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/serviceAdmin/editView?id="+index.id+"'>编辑</a>"
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
  data.iocUrl = $('#iocUrl').val();
  data.serviceType = $('#serviceType').val();
  data.subProjectSn = $('#subProjectSn').val();
  data.subServiceSn = $('#subServiceSn').val();
  data.name = $('#name').val();
  data.detail = $('#detail').val();
  data.serviceAdminUrl = $('#serviceAdminUrl').val();

    $.ajax({
        type: "post",
        url: "/serviceAdmin/save",
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
            go("/serviceAdmin/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/serviceAdmin/findById",
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
  $("#iocUrl").text( index.iocUrl);
  $("#serviceType").text( index.serviceType);
  $("#subProjectSn").text( index.subProjectSn);
  $("#subServiceSn").text( index.subServiceSn);
  $("#name").text( index.name);
  $("#detail").text( index.detail);
  $("#serviceAdminUrl").text( index.serviceAdminUrl);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/serviceAdmin/findById",
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
  $("#iocUrl").val( index.iocUrl);
  $("#serviceType").val( index.serviceType);
  $("#subProjectSn").val( index.subProjectSn);
  $("#subServiceSn").val( index.subServiceSn);
  $("#name").val( index.name);
  $("#detail").val( index.detail);
  $("#serviceAdminUrl").val( index.serviceAdminUrl);


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
            url: "/serviceAdmin/deleteById",
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
                go("/serviceAdmin/indexView");
            }
        })
   }
}

setActive("nav_serviceAdmin");