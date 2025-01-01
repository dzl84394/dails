
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/dependencyMq/findPage",
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
                 + "<td>"+index.mqSn+"</td>"
                 + "<td>"+index.mqType+"</td>"
                 + "<td>"+index.topic+"</td>"
                 + "<td>"+index.role+"</td>"

                 + "<td>"+index.subProjectSnFollow+"</td>"
                 + "<td>"+index.subServiceSnFollow+"</td>"
                 + "<td>"+index.clientName+"</td>"
                 + "<td>"+index.groupName+"</td>"
                 + "<td>"+index.status+"</td>"
                 + "<td>"+index.env+"</td>"

				+"<td><a class='btn btn-info btn-sm' href='/dependencyMq/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/dependencyMq/editView?id="+index.id+"'>编辑</a>"
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
  data.mqSn = $('#mqSn').val();
  data.mqType = $('#mqType').val();
  data.topic = $('#topic').val();
  data.role = $('#role').val();

  data.subProjectSnFollow = $('#subProjectSnFollow').val();
  data.subServiceSnFollow = $('#subServiceSnFollow').val();
  data.clientName = $('#clientName').val();
  data.groupName = $('#groupName').val();
  data.status = $('#status').val();
  data.env = $('#env').val();

    $.ajax({
        type: "post",
        url: "/dependencyMq/save",
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
            go("/dependencyMq/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/dependencyMq/findById",
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
  $("#mqSn").text( index.mqSn);
  $("#mqType").text( index.mqType);
  $("#topic").text( index.topic);
  $("#role").text( index.role);

  $("#subProjectSnFollow").text( index.subProjectSnFollow);
  $("#subServiceSnFollow").text( index.subServiceSnFollow);
  $("#clientName").text( index.clientName);
  $("#groupName").text( index.groupName);
  $("#status").text( index.status);
  $("#env").text( index.env);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/dependencyMq/findById",
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
  $("#mqSn").val( index.mqSn);
  $("#mqType").val( index.mqType);
  $("#topic").val( index.topic);
  $("#role").val( index.role);

  $("#subProjectSnFollow").val( index.subProjectSnFollow);
  $("#subServiceSnFollow").val( index.subServiceSnFollow);
  $("#clientName").val( index.clientName);
  $("#groupName").val( index.groupName);
  $("#status").val( index.status);
  $("#env").val( index.env);


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
            url: "/dependencyMq/deleteById",
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
                go("/dependencyMq/indexView");
            }
        })
   }
}


 //获取种植类型清单
function findMqServiceList(){
    $.ajax({
        type : "post",
        url : "/subService/findMqList",
        contentType: 'application/json',
        dataType: "json",

        async : false,
        success : function(result) {
            let plantTypes = result.data;
            // 找到datalist元素
            let dataList = $('#mqSn');
            // 清空datalist元素
            dataList.empty();
            // 使用each方法动态加载数据到datalist中
            $.each(plantTypes, function(index, value) {
                let option = $('<option>',
                { value: value.serviceSn ,text: value.serviceName}
                ).attr('subType', value.subType);
                dataList.append(option);
            });
        }
    })
}
$('#mqSn').change(function() {
    let selectedOption = $(this).find('option:selected');
    let serviceType = selectedOption.attr('subType');
    $('#mqType').val(serviceType); // 将 serviceType 赋值给输入框
});


function findProjectList(){
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
            let plantTypes = result.data;
            // 找到datalist元素
            let dataList = $('#subProjectSnFollow');
            // 清空datalist元素
            dataList.empty();
            // 使用each方法动态加载数据到datalist中
            $.each(plantTypes, function(index, value) {
                let option = $('<option>',
                { value: value.projectSn,text: value.name }
                );
                dataList.append(option);
            });
        }
    })
}
$('#subProjectSnFollow').change(function() {
    let selectedOption = $(this).find('option:selected');
    let selectedValue = selectedOption.val(); // 获取选中的值
    findServiceList(selectedValue);
});
function findServiceList(subProjectSn){
    var data = new Object();
	data.subProjectSn  = subProjectSn
    $.ajax({
        type : "post",
        url : "/subService/findList",
        contentType: 'application/json',
        dataType: "json",
        data : JSON.stringify({
            data
        }),
        async : false,
        success : function(result) {
            let plantTypes = result.data;
            // 找到datalist元素
            let dataList = $('#subServiceSnFollow');
            // 清空datalist元素
            dataList.empty();
            // 使用each方法动态加载数据到datalist中
            $.each(plantTypes, function(index, value) {
                let option = $('<option>',
                { value: value.serviceSn,text: value.serviceName }
                );
                dataList.append(option);
            });
        }
    })
}
setActive("nav_dependencyMq");