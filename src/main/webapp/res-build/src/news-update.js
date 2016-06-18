/**

 *
 */
define(function(require, exports, module) {
    require("res-build/res/plugin/form/jquery.form.js");
    require("res-build/res/plugin/jquery-formautofill/jquery.formautofill.min.js");
    require("res-build/res/plugin/jquery-validation-1.13.1/dist/jquery.validate.min.js");

    var fixedOnScroll = require("res-build/src/fixedOnScroll.js");
    var $addForm = $("#form_edit");
    var $etitor = $("#summernote");
    var editTag = false;
    //选择图片
    var $selImg = $("#selImg");
    var $file = $("#file");
    //工具函数
    var hzuitl = {
        formatDate: function(timestamp, format) {
            return formatDate(timestamp, format);
        },
        byteLength: function(str) {
            return byteLength(str);
        }
    };
    fixedOnScroll.scroll($addForm.find(".form-actions-fixtop"));

    function formatDate(timestamp, format) {
        var d = new Date(timestamp);
        var year = d.getFullYear();
        var month = d.getMonth() + 1;
        var date = d.getDate();
        var hour = d.getHours();
        var minute = d.getMinutes();
        var second = d.getSeconds();
        if (format)
            return year + "-" + month + "-" + date;
        else
            return year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":" + second;
    }

    function byteLength(str) {
        var byteLen = 0,
            len = str.length;
        if (!str) return 0;
        for (var i = 0; i < len; i++)
            byteLen += str.charCodeAt(i) > 255 ? 2 : 1;
        return byteLen;
    }

    $('#columncode').multiselect({
        buttonClass: 'btn',
        buttonWidth: 'auto',
        buttonText: function(options) {
            if (options.length == 0) {
                return 'None selected';
            } else if (options.length > 9) {
                return options.length ;
            } else {
                var selected = '';
                options.each(function() {
                    selected += $(this).text() + ', ';
                });
                return selected.substr(0, selected.length - 2);
            }
        },
    });
    $('#crowd').multiselect({
        buttonClass: 'btn',
        buttonWidth: 'auto',
        buttonText: function(options) {
            if (options.length == 0) {
                return 'None selected';
            } else if (options.length > 9) {
                return options.length;
            } else {
                var selected = '';
                options.each(function() {
                    selected += $(this).text() + ', ';
                });
                return selected.substr(0, selected.length - 2);
            }
        },
    });
    var Utilitiy = {
        init: function() {
            this.bind();

            this.initForm();

        },
        initForm: function() {

            $.ajax({
                type: "POST",
                url: ROOTPAth + "/admin/pop/news/detail/" + newsID,
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    var $option = $("#columncode").find("option[selected=selected]").removeAttr('selected');
                    var $option2 = $("#crowd").find("option[selected=selected]").removeAttr('selected');
                    $addForm[0].reset();
                    $addForm.autofill(data);
                    $("#summernote-con").html(data.content);

                    if (data.imgurl) {
                        $("#imgurl").attr("src", data.imgurl).show();
                    }
                    $('#summernote').summernote('destroy');
                    $('#summernote').hide();

                    var columncode_arr = data.columncode.split(','),
                        crowd_arr = data.crowd.split(',');
                    console.log(columncode_arr);
                    columncode_arr.pop();
                    columncode_arr.shift();
                    crowd_arr.pop();
                    crowd_arr.shift();
                    console.log(columncode_arr);
                    $('#columncode').multiselect({
                        noneSelectedText: "==请选择==",
                        buttonWidth: '500px'
                    });
                    $('#crowd').multiselect({
                        noneSelectedText: "==请选择==",
                        buttonWidth: '500px'
                    });
                    $('#columncode').val(columncode_arr);
                    $('#columncode').multiselect("refresh");
                    $('#crowd').val(crowd_arr);
                    $('#crowd').multiselect("refresh");
                    // var $option = $("#columncode").find("option");
                    // var $option2 = $("#crowd").find("option");
                    //  $option[0].selected = "selected";
                    // console.log($option);
                    // for (var i = 0; i < columncode_arr.length; i++) {
                    //     $option[parseInt(columncode_arr[i]) - 1].selected = "selected";

                    // }
                    // ;
                    // for (var j = 0; j < crowd_arr.length; j++) {
                    //     $option2[parseInt(crowd_arr[j]) - 1].selected = "selected";
                    // }


                    // var $li= $(".dropdown-menu").eq(0).find("li");
                    //  var $checkbox= $(".dropdown-menu").eq(0).find("input[type=checkbox]");
                    //  // var text_arr=[{"1":"老人"},{"2":"慢性病"},{"3":"急救"},{"4":"生活"},{"5":"育儿"},{"6":"心理"},{"7":"健身"},{"8":"营养"},{"9":"情感"}]
                    // var text_arr=["老人","慢性病","急救","生活","育儿","心理","健身","营养","情感"];
                    // var text_tit="";
                    //  for (var i = 0; i < columncode_arr.length; i++) {
                    //     $li[parseInt(columncode_arr[i])-1].className="active";
                    //     $checkbox[parseInt(columncode_arr[i])-1].checked="checked";
                    //       text_tit+=text_arr[parseInt(columncode_arr[i])-1]+",";

                    //  }
                    //  $(".multiselect").eq(0).attr({
                    //      title: text_tit
                    //  }).find(".multiselect-selected-text").html(text_tit);
                    //  console.log(text_tit);


                    //  var $li= $(".dropdown-menu").eq(1).find("li");
                    //  var $checkbox= $(".dropdown-menu").eq(1).find("input[type=checkbox]");
                    //  // var text_arr=[{"1":"老人"},{"2":"慢性病"},{"3":"急救"},{"4":"生活"},{"5":"育儿"},{"6":"心理"},{"7":"健身"},{"8":"营养"},{"9":"情感"}]
                    // var text_arr=["儿童","老年人","孕产妇","慢性病","女性","男性","其他"];
                    // var text_tit2="";
                    //  for (var i = 0; i < crowd_arr.length; i++) {
                    //     $li[parseInt(crowd_arr[i])-1].className="active";
                    //     $checkbox[parseInt(crowd_arr[i])-1].checked="checked";
                    //       text_tit2+=text_arr[parseInt(crowd_arr[i])-1]+",";

                    //  }
                    //  $(".multiselect").eq(1).attr({
                    //      title: text_tit2
                    //  }).find(".multiselect-selected-text").html(text_tit2);
                    //  console.log(text_tit2);


                }
            });
        },
        bind: function() {
            //选择图片
            $selImg.on("click", function() {
                $file.click();
            });

            $file.on("change", function() {

                $selImg.find(".has-img").remove();
                $selImg.append('<span class="has-img">' + $file.val() + '</span>');
            });
            //我要编辑
            $addForm.on("click", ".j-edit", function(event) {
                editTag = true;
                var formDom = event.delegateTarget;
                /*$(formDom).find(".form-actions-top").hide();*/
                //$(this).hide();
                $(formDom).find(".j-edit").hide();
                $(formDom).find(".j-save").show();
                $(formDom).find("[disabled]").prop("disabled", false);
                $(formDom).find("[name=spell]").prop("disabled", true);
                $(formDom).find("#summernote-con").remove();
                $(formDom).find(".disabled").removeClass('disabled');

                //编辑器
                $('#summernote').summernote({
                    height: 300,
                    lang: 'zh-CN',
                    focus: true
                        // keyup: function(e) {
                        //   //$('#summernote').summernote('code').val("");

                    //   $('#summernote').summernote('code').html("");
                    //  $('#summernote').summernote('code').val($('#summernote').summernote('code').code())
                    // }
                });
            });

            //


            //验证表单
            $addForm.validate({
                rules: {
                    title: {
                        required: true
                    },
                    des: {
                        required: true
                    },
                    // columncode: {
                    //     required: true
                    // },
                    // crowd: {
                    //     required: true
                    // },
                    file: {
                        required: true
                    },
                    // content: {
                    //     required: true
                    // }
                },
                messages: {
                    title: {
                        required: "请填写标题"
                    },
                    des: {
                        required: "请填写描述"
                    },
                    // columncode: {
                    //     required: "请选择分类"
                    // },
                    // crowd: {
                    //     required: "请选择人群"
                    // },
                    file: {
                        required: "请上传图片"
                    },
                    // content: {
                    //     required: "请填写正文"
                    // }
                },
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input


                invalidHandler: function(event, validator) { //display error alert on form submit
                    //                  $('.alert-danger', $('.login-form')).show();
                },
                highlight: function(element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function(label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function(error, element) {
                    error.insertAfter(element);
                },
                submitHandler: function(fm) {
                    if ($file.val().length && (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($file.val()))) {
                        $("#modal-box-error").modal("show");
                        $file.focus();
                        return
                    }
                    $(fm).ajaxSubmit({
                        type: "POST",
                        dataType: "html",
                        url: ROOTPAth + "/admin/pop/news/saveorupdate",
                        beforeSubmit: function(formData, jqForm, options) {
                            if (hzuitl.byteLength($('#summernote').summernote('code')) / (1024 * 1024) > 4) {
                                alert("文本内容总数不得超过4M 请减小图片大小或者精简文字");
                                return false;
                            }
                            var isSuccess = $addForm.validate().form();
                            var markupStr = $('#summernote').summernote("code");

                            $('#summernote').summernote('code', markupStr);
                            /*if(isSuccess)
                             $('body').modalmanager('loading');*/

                            return isSuccess;
                        },
                        success: function(data) {
                            if (data) {
                                var $tipModal = $('#modal-box');
                                var newdata = JSON.parse(data);
                                if (newdata.code === 1) {

                                    $tipModal.on('show.bs.modal', function(event) {
                                        $tipModal.find(".j-modal-closebtn").attr("href", ROOTPAth + "/admin/pop/news/updateView?id=" + newdata.data + "&pcode=News&subcode=PopNews")
                                    });
                                    $tipModal.modal("show");
                                }


                            }
                        }


                    });
                }
            });
        },

    };
    Utilitiy.init()
});
