<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/bas.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/res-build/res/plugin/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.css" rel="stylesheet">
    <link href="${ctx}/res-build/css/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
     <link rel="stylesheet" href="${ctx}/res-build/res/plugin/fileupload/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/res-build/res/plugin/fileupload/css/jquery.fileupload-ui.css">
</content>
<content tag="headerjsconfig">
    <%--<script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=&v=1.0"></script>--%>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=lYUVYI1SUwbp3mtDMzOEmQZ8"></script>
    <script src="${ctx}/res-build/res/plugin/bootstrap-summernote/summernote.js"></script>
    <%--    <script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=7tnSzYbr5FC01f6aCKgDFqro&services=&t=20150506165027"></script>--%>
</content>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

<body>
<h3 class="page-title">
    院内公告
    <small>新增公告</small>
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="${ctx}/admin/doc/announcement?pcode=DmdRe&subcode=DocAnnouncement">院内公告</a> <i
                class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">新增公告</a></li>
    </ul>
</div>

<div class="row">

    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="#" id="form_edit" class="form-horizontal" enctype="multipart/form-data" method="POST">
                    <div class="form-body">
                    <div class="form-group" style="display: none">
                        <label class="control-label col-md-3">发布时间</label>

                        <div class="col-md-4">
                            <input type="text" id="publishtime" name="publishtime" placeholder="发布时间" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">公告标题</label>

                        <div class="col-md-4">
                            <input type="text" id="title" name="title" placeholder="标题" class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="control-label col-md-3"> 发布人</label>

                        <div class="col-md-4">
                            <input type="text" id="publisher" name="publisher" placeholder="发布人" class="form-control">
                        </div>
                    </div>
                    <div class="form-group" id="fileupload">
                   
                        <label class="control-label col-md-3">附件地址</label>
                        <!-- Enclosure附件 -->
                       <!--  <div class="col-md-4">
                            <div id="selImg" class="form-control"><i class="iconfont">&#xe626;</i>
                                <span>选择图片</span></div>
                            <input type="file" id="file" name="file" class="">
                        </div>
                        <div class="col-md-4" style="line-height: 34px; color: #888888">
                            建议图片尺寸：1200x800(px)
                        </div> -->
                        <div class="fileupload-buttonbar col-md-4">

                            <span class="btn btn-success fileinput-button">
                                <i class="glyphicon glyphicon-plus"></i>
                                <span>选择文件</span>
                                <input type="file" name="files[]" multiple>
                            </span>
                            <button type="submit" class="btn btn-primary start">
                                <i class="glyphicon glyphicon-upload"></i>
                                <span>开始上传</span>
                            </button>
                            <button type="reset" class="btn btn-warning cancel">
                                <i class="glyphicon glyphicon-ban-circle"></i>
                                <span>取消上传</span>
                            </button>

             <table role="presentation" class="table table-striped "><tbody class="files"></tbody></table>
                        </div>
                       
                      
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">公告正文</label>

                        <div class="col-md-9" style="width: 50%">
                            <%--   <div id="summernote">summernote 1</div>--%>
                            <textarea id="summernote" name="introduce" class="form-control" rows="3"></textarea>
                        </div>
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn btn-success"><i class="iconfont">&#xe62c;</i>保存
                                </button>
                                <a href="${ctx}/admin/doc/announcement?pcode=DmdRe&subcode=DocAnnouncement"
                                   class="btn btn-default">返回</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div>
    <input type="hidden" id="webBasePath" value="${ctx}">
</div>

<div class="modal fade bs-example-modal-sm" id="modal-box" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">

        <div class="modal-content">

            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-success">
                    <div class="dialogtip-icon iconfont">&#xe614;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作成功</h4>

                        <div class="dialogtip-msg">数据添加成功</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="${ctx}/admin/doc/announcement?pcode=DmdRe&subcode=DocAnnouncement"
                   class="btn btn-success">返回公告列表</a>
                <a href="#" class="btn btn-danger j-modal-closebtn">关闭</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="modal-box-error" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">

        <div class="modal-content">


            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-error">
                    <div class="dialogtip-icon iconfont">&#xe616;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit">操作失败</h4>

                        <div class="dialogtip-msg">请上传gif|jpg|jpeg|png|xlsx|docx格式的图片</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-danger" data-dismiss="modal">我知道了</button>
            </div>
        </div>
    </div>
</div>
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p> <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
</body>

<content tag="jsconfig">

    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="announcement-add.js"></script>

<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.ui.widget.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/tmpl.min.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/load-image.all.min.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.iframe-transport.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.fileupload.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.fileupload-process.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.fileupload-image.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.fileupload-validate.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.fileupload-ui.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.fileupload-ui.js"></script>
<script src="${ctx}/res-build/res/plugin/fileupload/js/main.js"></script>
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="${ctx}/res-build/res/plugin/fileupload/js/jquery.xdr-transport.js"></script>
<![endif]-->
</content>
