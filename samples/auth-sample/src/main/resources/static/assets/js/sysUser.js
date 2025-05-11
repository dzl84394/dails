
function findPage(){
	var data = new Object();
	data.size  = 10;
	data.currentPage  = currentPage;
	$.ajax({
        type : "post",
        url : "/sysUser/findPage",
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

                 + "<td>"+index.username+"</td>"

                 + "<td>"+index.mobile+"</td>"
                 + "<td>"+index.email+"</td>"


				+"<td><a class='btn btn-info btn-sm' href='/sysUser/showView?id="+index.id+"'>展示</a>"
				+"<a class='btn btn-primary btn-sm' href='/sysUser/editView?id="+index.id+"'>编辑</a>"
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
  data.deptId = $('#deptId').val();
  data.roleId = $('#roleId').val();
  data.role = $('#role').val();
  data.username = $('#username').val();
  data.umCode = $('#umCode').val();
  data.mobile = $('#mobile').val();
  data.email = $('#email').val();
  data.status = $('#status').val();
  data.reportId = $('#reportId').val();
  data.contactId = $('#contactId').val();
  data.deleted = $('#deleted').val();
  data.enabled = $('#enabled').val();
  data.accountNonExpired = $('#accountNonExpired').val();
  data.accountNonLocked = $('#accountNonLocked').val();
  data.credentialsNonExpired = $('#credentialsNonExpired').val();
  data.password = $('#password').val();

    $.ajax({
        type: "post",
        url: "/sysUser/save",
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
            go("/sysUser/indexView");
        }
   })
}

function show(){
    $.ajax({
        type: "get",
        url: "/sysUser/findById",
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
  $("#deptId").text( index.deptId);
  $("#roleId").text( index.roleId);
  $("#role").text( index.role);
  $("#username").text( index.username);
  $("#umCode").text( index.umCode);
  $("#mobile").text( index.mobile);
  $("#email").text( index.email);
  $("#status").text( index.status);
  $("#reportId").text( index.reportId);
  $("#contactId").text( index.contactId);
  $("#deleted").text( index.deleted);
  $("#enabled").text( index.enabled);
  $("#accountNonExpired").text( index.accountNonExpired);
  $("#accountNonLocked").text( index.accountNonLocked);
  $("#credentialsNonExpired").text( index.credentialsNonExpired);
  $("#password").text( index.password);



        }
    })
}

function editshow(){
    $.ajax({
        type: "get",
        url: "/sysUser/findById",
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
  $("#deptId").val( index.deptId);
  $("#roleId").val( index.roleId);
  $("#role").val( index.role);
  $("#username").val( index.username);
  $("#umCode").val( index.umCode);
  $("#mobile").val( index.mobile);
  $("#email").val( index.email);
  $("#status").val( index.status);
  $("#reportId").val( index.reportId);
  $("#contactId").val( index.contactId);
  $("#deleted").val( index.deleted);
  $("#enabled").val( index.enabled);
  $("#accountNonExpired").val( index.accountNonExpired);
  $("#accountNonLocked").val( index.accountNonLocked);
  $("#credentialsNonExpired").val( index.credentialsNonExpired);
  $("#password").val( index.password);


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
            url: "/sysUser/deleteById",
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
                go("/sysUser/indexView");
            }
        })
   }
}

//setActive("nav_sysUser");