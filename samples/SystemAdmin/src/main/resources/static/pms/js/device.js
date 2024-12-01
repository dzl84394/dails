
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/device/findPage",
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
                 + "<td>"+index.deviceType+"</td>"
                 + "<td>"+index.deviceCenterSn+"</td>"
                 + "<td>"+index.deviceRoomSn+"</td>"
                 + "<td>"+index.rackSn+"</td>"
                 + "<td>"+index.deviceSn+"</td>"
                 + "<td>"+index.manufacturern+"</td>"
                 + "<td>"+index.modeln+"</td>"
                 + "<td>"+index.cpuModeln+"</td>"
                 + "<td>"+index.cpuCores+"</td>"
                 + "<td>"+index.memorySize+"</td>"
                 + "<td>"+index.storageSize+"</td>"
                 + "<td>"+index.networkInterfaces+"</td>"
                 + "<td>"+index.powerSupply+"</td>"
                 + "<td>"+index.os+"</td>"
                 + "<td>"+index.env+"</td>"
                 + "<td>"+index.status+"</td>"

				+"<td><a class='btn btn-info btn-sm' href='/device/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/device/editView?id="+index.id+"'>编辑</a>"
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
  data.deviceType = $('#deviceType').val();
  data.deviceCenterSn = $('#deviceCenterSn').val();
  data.deviceRoomSn = $('#deviceRoomSn').val();
  data.rackSn = $('#rackSn').val();
  data.deviceSn = $('#deviceSn').val();
  data.manufacturern = $('#manufacturern').val();
  data.modeln = $('#modeln').val();
  data.cpuModeln = $('#cpuModeln').val();
  data.cpuCores = $('#cpuCores').val();
  data.memorySize = $('#memorySize').val();
  data.storageSize = $('#storageSize').val();
  data.networkInterfaces = $('#networkInterfaces').val();
  data.powerSupply = $('#powerSupply').val();
  data.os = $('#os').val();
  data.env = $('#env').val();
  data.status = $('#status').val();

    $.ajax({
        type: "post",
        url: "/device/save",
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
            go("/device/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/device/findById",
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
  $("#deviceType").text( index.deviceType);
  $("#deviceCenterSn").text( index.deviceCenterSn);
  $("#deviceRoomSn").text( index.deviceRoomSn);
  $("#rackSn").text( index.rackSn);
  $("#deviceSn").text( index.deviceSn);
  $("#manufacturern").text( index.manufacturern);
  $("#modeln").text( index.modeln);
  $("#cpuModeln").text( index.cpuModeln);
  $("#cpuCores").text( index.cpuCores);
  $("#memorySize").text( index.memorySize);
  $("#storageSize").text( index.storageSize);
  $("#networkInterfaces").text( index.networkInterfaces);
  $("#powerSupply").text( index.powerSupply);
  $("#os").text( index.os);
  $("#env").text( index.env);
  $("#status").text( index.status);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/device/findById",
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
  $("#deviceType").val( index.deviceType);
  $("#deviceCenterSn").val( index.deviceCenterSn);
  $("#deviceRoomSn").val( index.deviceRoomSn);
  $("#rackSn").val( index.rackSn);
  $("#deviceSn").val( index.deviceSn);
  $("#manufacturern").val( index.manufacturern);
  $("#modeln").val( index.modeln);
  $("#cpuModeln").val( index.cpuModeln);
  $("#cpuCores").val( index.cpuCores);
  $("#memorySize").val( index.memorySize);
  $("#storageSize").val( index.storageSize);
  $("#networkInterfaces").val( index.networkInterfaces);
  $("#powerSupply").val( index.powerSupply);
  $("#os").val( index.os);
  $("#env").val( index.env);
  $("#status").val( index.status);


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
            url: "/device/deleteById",
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
                go("/device/indexView");
            }
        })
   }
}

setActive("nav_device");