/**
 * Created by feiwen8772 on 15/5/12.
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \\|     |// '.
 *                 / \\|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \\\  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *               佛祖保佑         永无BUG
 *
 */
define(function (require, exports, module) {
    var Page = require("page");
    var juicer = require("juicer");
    require("res-build/res/plugin/bs-confirmation/bootstrap-confirmation.js");
    var tool = require("tool");
    var pageIndex;
    var $table = $("#datatable_ajax");
    var $hospitalList = $("#hospital-list");
    var pagelength = 10; //一页多少条；
    var $searchForm = $("#search-form");
    var $body = $('body');


    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    var listTpl = juicer(
        [
            '{@if total === 0}',
            '<tr>',
            '<td colspan="6" style="text-align:center">',
            '暂无记录,请添加',
            '</td>',

            '</tr>',

            '{@else}',
            '{@each data as item,index}',
            '{@if index%2==0}',
            '<tr role="row" class="odd" data-organizationid="${item.id}">',
            '{@else}',
            '<tr role="row" class="even" data-organizationid="${item.id}">',
            '{@/if}',
            '    <td>${item.id}</td>',
            '    <td>${item.nickname}</td>',
            '    <td><a href="#" class="has-popover" data-img="${item.header}">查看图片</a></td>',
            '    <td>${item.mobile}</td>',
            '    <td>${item.viptext}</td>',
            '    <td class="">',
            '{@if item.vip==1}',
            ' <button type="button" class="btn btn-danger btn-xs j-del" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61d;</span>取消VIP</button>',
            '{@else}',
            ' <button type="button" class="btn btn-danger btn-xs j-add" data-toggle="confirmation" data-placement="left"><span   class="iconfont iconfont-xs">&#xe61f;</span>开通VIP</button>',
            '{@/if}',
            '    </td>',
            '</tr>',
            '{@/each}',
            '{@/if}'
        ].join(""));
    var Utilitiy = {
        init: function () {
            var self = this;
            this.bind();
            tool.startPageLoading();

            pageIndex = new Page({
                ajax: {
                    url: ROOTPAth + '/admin/bas/user/list',
                    type: 'POST',
                    dataType: 'json',
                    data: function () {
                        var nickname = $searchForm.find("input[name=nickname]").val();
                        var phone = $searchForm.find("input[name=phone]").val();
                        var data = {
                            length: pagelength,
                            phone: phone,
                            nickname: nickname
                        };
                        return data;
                    },
                    success: function (res) {
                        tool.stopPageLoading();
                        if (res.code === 1) {
                            var newData = $.extend({}, res);
                            if (res.total > 0) {
                                $.each(newData.data, function (i, val) {
                                    newData.data[i].currentpage = pageIndex.current;
                                    newData.data[i].viptext = val.vip === 1 ? "是(" + new Date(val.viptime).Format("yyyy-MM-dd hh:mm:ss") + ")" : "否";
                                });
                            }

                            //共多少条记录
                            $hospitalList.find(".page-info-num").text(res.total);
                            $table.find("tbody").empty().append(listTpl.render(newData));
                            $hospitalList.find(".has-popover").popover({
                                content: function () {
                                    var $this = $(this);
                                    var url = $this.data("img");
                                    return '<img src="' + url + '" style="width: 100px;height: 100px" alt=""/>'
                                },
                                html: true,
                                trigger: "hover",
                                placement: "top",
                                title: "图片预览"
                            });
                            $hospitalList.find(".j-add").confirmation({
                                title: "确定要开通VIP吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.additem($(element));
                                }
                            });
                            $hospitalList.find(".j-del").confirmation({
                                title: "确定要取消VIP吗？",
                                btnOkLabel: "确定",
                                btnCancelLabel: "取消",
                                onConfirm: function (event, element) {
                                    event.preventDefault();
                                    self.delitem($(element));
                                }
                            });
                        }

                    },
                    error: function () {
                        tool.stopPageLoading();
                        $("#ajax_fail").modal("show")
                    },
                },
                pageName: "page",
                /*tpl: {
                 go: true //隐藏跳转到第几页
                 },*/
                getTotalPage: function (res) {
                    //返回总页数
                    return Math.ceil(res.total / pagelength);
                },
                pageWrapper: '.table-page'
            });
            var windowurl = window.location.href;
            var returnUrl = windowurl.indexOf("currentpage=");

            if (returnUrl == -1 || returnUrl == "") {
                pageIndex.resetgoto(1);
            } else {
                var returnUrl_val = parseInt(windowurl.substring(returnUrl + 12));
                if (returnUrl_val != 1) {
                    pageIndex.resetgoto(returnUrl_val);
                } else {
                    pageIndex.reset()
                }
            }

        },
        bind: function () {
            //修改每页显示条数
            $hospitalList.on("change", ".j-length", function () {
                var $this = $(this);
                pagelength = $this.val();
                var index = $this.get(0).selectedIndex;
                $hospitalList.find(".j-length").not(this).get(0).selectedIndex = index;
                pageIndex.reset();
            });
            $searchForm.on("submit", function (e) {
                e.preventDefault();
                pageIndex.reset();
            });
            $searchForm.on("click", ".j-showall", function (e) {
                e.preventDefault();
                $searchForm[0].reset();
                pageIndex.reset();
            });
        },
        additem: function ($that) {
            var $tr = $that.closest("tr");
            var organizationid = $tr.data("organizationid");
            var delPath = ROOTPAth + '/admin/bas/user/vip/' + organizationid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {
                    pageIndex.resetgoto(pageIndex.current)
                },
                error: function () {
                    tool.stopPageLoading();
                    $("#ajax_fail").modal("show")
                }
            });
        },
        delitem: function ($that) {
            var $tr = $that.closest("tr");
            var organizationid = $tr.data("organizationid");
            var delPath = ROOTPAth + '/admin/bas/user/unvip/' + organizationid;
            $.ajax({
                url: delPath,
                type: "POST",
                success: function (data) {
                    pageIndex.resetgoto(pageIndex.current)
                },
                error: function () {
                    tool.stopPageLoading();
                    $("#ajax_fail").modal("show")
                }
            });
        }

    };
    Utilitiy.init();
});