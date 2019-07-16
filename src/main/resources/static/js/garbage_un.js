var dataTable;
$(function () {
    // init date tables

    dataTable = $("#garbage_un_list").DataTable({
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        "aLengthMenu": [5, 10, 20, 30, 50, 100], //更改显示记录数选项
        "iDisplayLength": 20, //默认显示的记录数
        "ajax": {
            url: basePath + "/admin/garbageUnL",
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
            {"data": 'title', 'width': '15%', "visible": true},
            {"data": 'createTime', 'width': '20%', "visible": true},
            {
                "data": '操作',
                'width': '20%',
                "render": function (data, type, row) {
                    return function () {
                        // 归属分类 1 可回收 2 有害 3 湿垃圾 4 干垃圾
                        var html = '<p key="' + row.id + '">' +
                            '<button class="btn btn-primary btn-xs" onclick="ascertain(' + row.id + ',1)" type="button">可回收</button>  ' +
                            '&nbsp;&nbsp;' +
                            '<button class="btn btn-danger btn-xs" onclick="ascertain(' + row.id + ',2)" type="button">有害</button>  ' +
                            '&nbsp;&nbsp;' +
                            '<button class="btn btn-warning btn-xs"  onclick="ascertain(' + row.id + ',3)" type="button">湿垃圾</button>  ' +
                            '&nbsp;&nbsp;' +
                            '<button class="btn btn-inverse btn-xs"  onclick="ascertain(' + row.id + ',4)" type="button">干垃圾</button>  ' +
                            '&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '<button class="btn btn-danger btn-xs"  onclick="deletes(' + row.id + ')" type="button">删除</button>  ' +
                            '</p>';
                        return html;
                    };
                }
            }
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
});

function ascertain(id, val) {
    $.post(basePath + "/admin/ascertain", {unId: id, type: val}, function () {
        dataTable.draw(false);
    });
}

function deletes(id) {
    $.post(basePath + "/admin/unDel", {unId: id}, function () {
        dataTable.draw(false);
    });
}