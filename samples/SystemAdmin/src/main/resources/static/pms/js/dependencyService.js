
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/dependencyService/findPage",
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
                 + "<td>"+index.aid+"</td>"
                 + "<td>"+index.serviceTypea+"</td>"
                 + "<td>"+index.subProjectSna+"</td>"
                 + "<td>"+index.subServiceSna+"</td>"
                 + "<td>"+index.namea+"</td>"

                 + "<td>"+index.subProjectSnb+"</td>"
                 + "<td>"+index.subServiceSnb+"</td>"
                 + "<td>"+index.serviceTypeb+"</td>"


				+"<td><a class='btn btn-info btn-sm' href='/dependencyService/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/dependencyService/editView?id="+index.id+"'>编辑</a>"
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
  data.aid = $('#aid').val();
  data.serviceTypeA = $('#serviceTypeA').val();
  data.subProjectSnA = $('#subProjectSnA').val();
  data.subServiceSnA = $('#subServiceSnA').val();
  data.nameA = $('#nameA').val();
  data.bid = $('#bid').val();
  data.serviceTypeB = $('#serviceTypeB').val();
  data.subProjectSnB = $('#subProjectSnB').val();
  data.subServiceSnB = $('#subServiceSnB').val();
  data.nameB = $('#nameB').val();

    $.ajax({
        type: "post",
        url: "/dependencyService/save",
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
            go("/dependencyService/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/dependencyService/findById",
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
  $("#aid").text( index.aid);
  $("#serviceTypeA").text( index.serviceTypeA);
  $("#subProjectSnA").text( index.subProjectSnA);
  $("#subServiceSnA").text( index.subServiceSnA);
  $("#nameA").text( index.nameA);
  $("#bid").text( index.bid);
  $("#serviceTypeB").text( index.serviceTypeB);
  $("#subProjectSnB").text( index.subProjectSnB);
  $("#subServiceSnB").text( index.subServiceSnB);
  $("#nameB").text( index.nameB);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/dependencyService/findById",
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
  $("#aid").val( index.aid);
  $("#serviceTypeA").val( index.serviceTypeA);
  $("#subProjectSnA").val( index.subProjectSnA);
  $("#subServiceSnA").val( index.subServiceSnA);
  $("#nameA").val( index.nameA);
  $("#bid").val( index.bid);
  $("#serviceTypeB").val( index.serviceTypeB);
  $("#subProjectSnB").val( index.subProjectSnB);
  $("#subServiceSnB").val( index.subServiceSnB);
  $("#nameB").val( index.nameB);


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
            url: "/dependencyService/deleteById",
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
                go("/dependencyService/indexView");
            }
        })
   }
}



function findSubService(subProjectSn,divid,selectedValue){
	var data = new Object();
	data.subProjectSn  = subProjectSn;

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
            let confs = result.data;
            // 找到datalist元素
            let data1List = $("#"+divid);
            // 清空datalist元素
            data1List.empty();

             $.each(confs, function(index, value) {
                 var option = $("<option>").text(value.serviceName)
                 .attr("value", value.serviceSn	)
                 .attr("subType", value.subType);;
                 if (selectedValue) {
                     if (value.projectSn === selectedValue) {
                         option.attr("selected", "selected"); // 选中该选项
                     }
                 }
                 data1List.append(option);
             });


		}
	})
}
setActive("nav_dependencyService");