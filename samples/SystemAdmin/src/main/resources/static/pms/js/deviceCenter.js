
function findPage(deviceType){
	var data = new Object();
	data.deviceType=deviceType;
	data.size  = 10;

	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/deviceCenter/findPage",
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
				temp += "<tr>";

                temp +=  "<td>"+index.id+"</td>"
                 + "<td>"+index.deviceType+"</td>"
                 + "<td>"+index.deviceCenter+"</td>"
                 + "<td>"+index.deviceCenterSn+"</td>";

                 if(index.deviceType==='room'||index.deviceType==='rack'){
                    temp +=  "<td>"+index.deviceRoom+"</td>"
                          + "<td>"+index.deviceRoomSn+"</td>";

                 }
                 if(index.deviceType==='rack'){
                    temp +=
                          "<td>"+index.rowcell+"</td>"
                         + "<td>"+index.cabinetSn+"</td>"
                         + "<td>"+index.rack+"</td>"
                         + "<td>"+index.high+"</td>"
                         + "<td>"+index.deviceSn+"</td>"
                         + "<td>"+index.env+"</td>"
                         + "<td>"+index.status+"</td>";
                 }


				temp +=  "<td><a class='btn btn-info btn-sm' href='/deviceCenter/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/deviceCenter/editView?id="+index.id+"'>编辑</a>"
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
  data.deviceCenter = $('#deviceCenter').val();
  data.deviceCenterSn = $('#deviceCenterSn').val();
  data.deviceRoom = $('#deviceRoom').val();
  data.deviceRoomSn = $('#deviceRoomSn').val();
  data.cabinetName = $('#cabinetName').val();
  data.rowcell = $('#rowcell').val();
  data.cabinetSn = $('#cabinetSn').val();
  data.rack = $('#rack').val();
  data.high = $('#high').val();
  data.deviceSn = $('#deviceSn').val();
  data.env = $('#env').val();
  data.status = $('#status').val();

    $.ajax({
        type: "post",
        url: "/deviceCenter/save",
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
            go("/deviceCenter/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/deviceCenter/findById",
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
  $("#deviceCenter").text( index.deviceCenter);
  $("#deviceCenterSn").text( index.deviceCenterSn);
  $("#deviceRoom").text( index.deviceRoom);
  $("#deviceRoomSn").text( index.deviceRoomSn);
  $("#cabinetName").text( index.cabinetName);
  $("#rowcell").text( index.rowcell);
  $("#cabinetSn").text( index.cabinetSn);
  $("#rack").text( index.rack);
  $("#high").text( index.high);
  $("#deviceSn").text( index.deviceSn);
  $("#env").text( index.env);
  $("#status").text( index.status);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/deviceCenter/findById",
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
  $("#deviceCenter").val( index.deviceCenter);
  $("#deviceCenterSn").val( index.deviceCenterSn);
  $("#deviceRoom").val( index.deviceRoom);
  $("#deviceRoomSn").val( index.deviceRoomSn);
  $("#cabinetName").val( index.cabinetName);
  $("#rowcell").val( index.rowcell);
  $("#cabinetSn").val( index.cabinetSn);
  $("#rack").val( index.rack);
  $("#high").val( index.high);
  $("#deviceSn").val( index.deviceSn);
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
            url: "/deviceCenter/deleteById",
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
                go("/deviceCenter/indexView");
            }
        })
   }
}




function findDcList(dcDiv){
    var data = new Object();
    data.deviceType='dc';
    $.ajax({
        type : "post",
        url : "/deviceCenter/findList",
        contentType: 'application/json',
        dataType: "json",
        data : JSON.stringify({
            data
        }),
        async : false,
        success : function(result) {
            let confs = result.data;
            // 找到datalist元素
            let data1List = $(dcDiv);
            // 清空datalist元素
            data1List.empty();


            // 使用each方法动态加载数据到datalist中
            $.each(confs, function(index, value) {
                var option = $("<option>").text(value.deviceCenter).attr("value", value.deviceCenterSn	);
                data1List.append(option);

            });
        }
    })
}
setActive("nav_deviceCenter");