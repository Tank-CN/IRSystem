<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<content tag="cssconfig">
    <link href="${ctx}/res-build/css/bas.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/res-build/res/plugin/jquery-ui-bootstrap/css/jquery-ui-1.10.3.custom.css"/>
    <link href="${ctx}/res-build/css/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <link href="${ctx}/res-build/res/plugin/jquery-ui-bootstrap/css/jquery.ui.1.10.3.ie.css" rel="stylesheet"/>
    <![endif]-->
</content>
<content tag="headerjsconfig">
    <script type="text/javascript"
            src="${ctx}/res-build/res/plugin/jquery-ui-bootstrap/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=lYUVYI1SUwbp3mtDMzOEmQZ8"></script>
</content>
<body>
<h3 class="page-title">
    活动管理
    <small>修改活动</small>
</h3>

<div class="page-bar clearfix">
    <ul class="page-breadcrumb">
        <li><i class="iconfont ico-home">&#xe60a;</i> <a href="index.html">主页</a> <i class="iconfont ico-angle-right">
            &#xe605;</i></li>
        <li><a href="#">活动管理</a> <i class="iconfont ico-angle-right">&#xe605;</i></li>
        <li><a href="#">修改活动</a></li>
    </ul>
</div>
<div class="row">

    <div class="col-md-12">
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="#" id="form_edit" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" id="id" name="id" value="${id}">

                <div class="form-body">
                    <div class="form-actions-top">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn btn-success j-edit"><i class="iconfont">&#xe61c;</i>
                                    我要编辑
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">商家ID</label>

                        <div class="col-md-4">
                            <input type="text" id="bid" name="bid" disabled placeholder="商家ID" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3">商家名称</label>

                        <div class="col-md-4">
                            <input type="text" id="bname" name="bname" disabled placeholder="商家名称" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3">标题</label>

                        <div class="col-md-4">
                            <input type="text" id="title" name="title" disabled placeholder="标题" class="form-control">
                        </div>
                    </div>


                    <div class="form-group">

                        <label class="control-label col-md-3">活动开始时间</label>

                        <div class="col-md-8">
                            <div class="form-inline">


                                <input type="text" id="sdate" name="sdate" disabled placeholder="活动开始日期"
                                       class="form-control">

                                <select id="sh" name="sh" disabled class="form-control">
                                    <option value="00">00</option>
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                </select> 点

                                <select id="sm" name="sm" disabled class="form-control">
                                    <option value="00">00</option>
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                    <option value="32">32</option>
                                    <option value="33">33</option>
                                    <option value="34">34</option>
                                    <option value="35">35</option>
                                    <option value="36">36</option>
                                    <option value="37">37</option>
                                    <option value="38">38</option>
                                    <option value="39">39</option>
                                    <option value="40">40</option>
                                    <option value="41">41</option>
                                    <option value="42">42</option>
                                    <option value="43">43</option>
                                    <option value="44">44</option>
                                    <option value="45">45</option>
                                    <option value="46">46</option>
                                    <option value="47">47</option>
                                    <option value="48">48</option>
                                    <option value="49">49</option>
                                    <option value="50">50</option>
                                    <option value="51">51</option>
                                    <option value="52">52</option>
                                    <option value="53">53</option>
                                    <option value="54">54</option>
                                    <option value="55">55</option>
                                    <option value="56">56</option>
                                    <option value="57">57</option>
                                    <option value="58">58</option>
                                    <option value="59">59</option>
                                </select> 分
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">活动结束时间</label>

                        <div class="col-md-8">
                            <div class="form-inline">


                                <input type="text" id="edate" disabled name="edate"
                                       placeholder="活动结束日期"
                                       class="form-control">

                                <select id="eh" name="eh" disabled class="form-control">
                                    <option value="00">00</option>
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                </select> 点

                                <select id="em" name="em" disabled class="form-control">
                                    <option value="00">00</option>
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                    <option value="32">32</option>
                                    <option value="33">33</option>
                                    <option value="34">34</option>
                                    <option value="35">35</option>
                                    <option value="36">36</option>
                                    <option value="37">37</option>
                                    <option value="38">38</option>
                                    <option value="39">39</option>
                                    <option value="40">40</option>
                                    <option value="41">41</option>
                                    <option value="42">42</option>
                                    <option value="43">43</option>
                                    <option value="44">44</option>
                                    <option value="45">45</option>
                                    <option value="46">46</option>
                                    <option value="47">47</option>
                                    <option value="48">48</option>
                                    <option value="49">49</option>
                                    <option value="50">50</option>
                                    <option value="51">51</option>
                                    <option value="52">52</option>
                                    <option value="53">53</option>
                                    <option value="54">54</option>
                                    <option value="55">55</option>
                                    <option value="56">56</option>
                                    <option value="57">57</option>
                                    <option value="58">58</option>
                                    <option value="59">59</option>
                                </select> 分
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">图片</label>

                        <div class="col-md-4" style="position: relative">
                            <div id="selImg" class="form-control"><i class="iconfont">&#xe626;</i>
                                <span>选择图片</span></div>
                            <input type="file" id="file" name="file" disabled class="">
                            <img src="" id="imgurl" style="display: none; margin-top: 6px;width: 320px;height: auto"
                                 alt=""/>
                        </div>
                        <div class="col-md-4" style="line-height: 34px; color: #888888">
                            建议图片尺寸：1200x800(px)
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">活动地点</label>

                        <div class="col-md-4">
                            <input type="text" id="address" name="address" disabled placeholder="活动地点"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <label class="control-label col-md-3">活动地点地图</label>

                        <div class="col-md-8">
                            <div id="allmap" style="width:600px;height:500px;"></div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">活动地点经度</label>

                        <div class="col-md-4">
                            <input type="text" id="longitude" name="longitude" disabled placeholder="医院经度"
                                   class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3">活动地点纬度</label>

                        <div class="col-md-4">
                            <input type="text" id="latitude" name="latitude" disabled placeholder="医院纬度"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">内容概要</label>

                        <div class="col-md-7">
                            <textarea id="des" name="des" class="form-control" disabled rows="5"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">内容</label>

                        <div class="col-md-6">
                            <textarea id="summernote" name="content" class="form-control" maxlength="1000" disabled
                                      rows="5"></textarea>
                        </div>
                        <div class="col-md-3" style="line-height: 34px; color: #888888">
                            最多1000字符
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">联系人</label>

                        <div class="col-md-4">
                            <input type="text" id="linkman" name="linkman" placeholder="联系人" disabled
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">联系电话</label>

                        <div class="col-md-4">
                            <input type="text" id="linkphone" name="linkphone" placeholder="联系电话" disabled
                                   class="form-control">
                        </div>
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn btn-success j-edit"><i class="iconfont">&#xe61c;</i>我要编辑
                                </button>
                                <button type="submit" class="btn btn-success j-save" style="display: none"><i
                                        class="iconfont">
                                    &#xe62c;</i>保存
                                </button>
                                <a href="${ctx}/admin/activity?pcode=Activity&subcode=ActivityList"
                                   class="btn btn-default">返回</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
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
                <a href="${ctx}/admin/business/activity?pcode=activity&subcode=activitylist"
                   class="btn btn-success">返回活动列表</a>
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

                        <div class="dialogtip-msg">请上传gif|jpg|jpeg|png格式的图片</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-danger" data-dismiss="modal">我知道了</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="ajax_fail" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <div class="dialogtip-con-wrap clearfix dialogtipg-warning">
                    <div class="dialogtip-icon iconfont">&#xe615;</div>
                    <div class="dialogtip-con">
                        <h4 class="dialogtip-tit" style="margin-top: 12px;">保存失败</h4>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-danger j-modal-closebtn" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>

<content tag="jsconfig">
    <script type="text/javascript">
        var activityID = ${id};
    </script>
    <script type="text/javascript" src="${ctx}/res-build/config.js" data-init="activity_update.js"></script>

</content>
