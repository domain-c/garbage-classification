$(function () {
    // init date tables

    var dataTable = $("#garbage_list").DataTable({
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        "aLengthMenu": [5, 10, 20, 30, 50, 100], //更改显示记录数选项
        "iDisplayLength": 20, //默认显示的记录数
        "ajax": {
            url: basePath + "/admin/garbageL",
            type: "post",
            data: function (d) {
                var obj = {};
                obj.key = $('#key').val();
                obj.start = ((d.start / d.length) + 1);
                obj.length = d.length;
                return obj;
            }
        },
        "searching": false,
        "ordering": false,
        //"scrollX": true,	// X轴滚动条，取消自适应
        "columns": [
            // {"data": 'id', 'width': '10%', "visible": true},
            {
                "data": 'belongClassification', 'width': '15%', "visible": true,
                "render": function (data, type, row) {
                    return function () {
                        //归属分类 1 可回收 2 有害 3 湿垃圾 4 干垃圾
                        var html = "";
                        switch (row.belongClassification) {
                            case 1:
                                html = "可回收垃圾";
                                break;
                            case 2:
                                html = "有害垃圾";
                                break;
                            case 3:
                                html = "湿垃圾";
                                break;
                            case 4:
                                html = "干垃圾";
                                break;
                        }
                        return html;
                    };
                }
            },
            {"data": 'garbageName', 'width': '20%', "visible": true},
            {"data": 'detail', 'width': '20%', "visible": true}
        ],
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 条记录",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "sInfoEmpty": "无记录",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });

    $("#searchBtn").on('click', function () {
        dataTable.draw(false);
    });

    //点击tr进入详情
    // $("#user_list").on('click', 'tr', function() {
    //     var data = dataTable.row(this).data();
    //     // alert( 'You clicked on '+ data.d1 +' \'s row' );
    //     window.location.href = basePath + "/admin/points/detail/" + data.id;
    // } );

    // 更新
    $("#user_list").on('click', '.update', function () {
        var id = $(this).parent('p').attr("key");
        console.log(id);
        var row = confData[id];
        window.location.href = basePath + "/admin/points/detail/" + id;
    });

    // 新增
    $("#add").on('click', function () {
        $('#addModal').modal('show');
    });

    var addModalValidate = $("#addModal .form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: true,
        rules: {
            garbageName: {
                required: true
            },
            detail: {
                required: false
            }
        },
        messages: {
            garbageName: {
                required: '请输入垃圾名称',
                rangelength: "长度限制为1~50"
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (form) {
            $.post(basePath + "/admin/add", {
                'belongClassification': $("#addModal .form select[name='type']").val(),
                'garbageName': $("#addModal .form input[name='garbageName']").val(),
                'detail': $("#addModal .form input[name='detail']").val()
            }, function (data, status) {
                if (data.code == 200) {
                    if (data.value == 1) {
                        layer.open({
                            icon: '1',
                            content: '新增成功',
                            end: function (layero, index) {
                                dataTable.draw();
                                $('#addModal').modal('hide');
                            }
                        });
                    } else {
                        layer.open({
                            icon: '2',
                            content: (data.msg || '新增失败')
                        });
                    }
                } else {
                    layer.open({
                        icon: '2',
                        content: (data.msg || '新增失败')
                    });
                }
            });
        }
    });

    // jquery.validate 自定义校验
    jQuery.validator.addMethod("myValid01", function (value, element) {
        var length = value.length;
        var valid = /^[\u4e00-\u9fa5]*$/;
        return this.optional(element) || valid.test(value);
    }, "限制输入需中文");

    // tecTips
    var tecCache = {};
    $("#conf_list").on('click', '.tecTips', function () {
        var cacheKey = $(this).attr("cacheKey");
        var cacheValue = tecCache[cacheKey];
        ComAlertTec.show(cacheValue);
    });


    var confData = {};

    // 删除
    $("#conf_list").on('click', '.delete', function () {

        var key = $(this).parent('p').attr("key");

        layer.confirm("确定要删除配置：" + key, {
            icon: 3,
            title: '系统提示',
            btn: ['确定', '取消']
        }, function (index) {
            layer.close(index);

            $.post(
                base_url + "/points/delete",
                {
                    "env": env,
                    "key": key
                },
                function (data, status) {
                    if (data.code == 200) {
                        layer.open({
                            icon: '1',
                            content: '删除成功',
                            end: function (layero, index) {
                                dataTable.fnDraw();
                            }
                        });
                    } else {
                        layer.open({
                            icon: '2',
                            content: (data.msg || '删除失败')
                        });
                    }
                }
            );

        });

    });

    $("#addModal").on('hide.bs.modal', function () {
        $("#addModal .form")[0].reset()
    });

    var updateModalValidate = $("#updateModal .form").validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: true,
        rules: {
            key: {
                required: true,
                rangelength: [4, 100],
                myValid01: true
            },
            title: {
                required: true
            }
        },
        messages: {
            key: {
                required: '请输入配置Key',
                rangelength: "配置Key长度限制为4~100"
            },
            title: {
                required: '请输入配置描述'
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            element.parent('div').append(error);
        },
        submitHandler: function (form) {
            $.post(base_url + "/points/update", $("#updateModal .form").serialize(), function (data, status) {
                if (data.code == 200) {
                    layer.open({
                        icon: '1',
                        content: '更新成功',
                        end: function (layero, index) {
                            dataTable.fnDraw();
                            $('#updateModal').modal('hide');
                        }
                    });
                } else {
                    layer.open({
                        icon: '2',
                        content: (data.msg || '更新失败')
                    });
                }
            });
        }
    });
    $("#updateModal").on('hide.bs.modal', function () {
        $("#updateModal .form")[0].reset()
    });

});