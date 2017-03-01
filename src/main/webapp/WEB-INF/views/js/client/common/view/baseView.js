/**
 * 通用基础js
 */
define(['client/common/model/baseModel', 'common/util', 'layer1', 'jquery.validate', 'common/backToTop'], function (model, util, layer) {


    // layer配置
    layer.config({
        path: '/js/lib/layer/' //layer.js所在的目录，可以是绝对目录，也可以是相对目录
    });

    /**
     * 初始化
     * @private
     */
    function _init() {
        // 事件绑定
        _bind();
        // 增加反馈按钮
        _addTicklingButton();
    }

    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([
            {
                el: '.log-out',
                event: 'click',
                handler: _logout
            }, {
                el: '#tickling',
                event: 'click',
                handler: function () {
                    //如果用户已登录则弹出意见反馈框，否则跳转到登录页
                    if ($("#js-user").val()) {
                        _showFeedbank();
                    } else {
                        top.location = window.__NOTEBOOK__.root_url + "client/tologin";
                    }
                }
            },
            {
                el: '#js-myNote',
                event: 'click',
                handler: function () {
                    //如果用户已登录则直接进入我的笔记页面，否则弹出登录提示框
                    if ($('#js-user').val()) {
                        top.location = window.__NOTEBOOK__.root_url + "person/note/list";
                    } else {
                        top.location = window.__NOTEBOOK__.root_url + "client/tologin?ref=person"
                    }
                }
            }, {
                el: '.js-side-flex',
                event: 'click',
                handler: function () {
                    // 侧边栏收缩
                    $('.sidebar').addClass('sidebar-flexing');
                    var topWid=$(window).width()-80;

                    $('.top-menu').stop().animate({width:topWid+'px'},200);
                    $('.sidebar').stop().animate({width: "80px", paddingLeft: "20px"}, 200, 'swing', function () {
                        $('.flex-trigger b').html('&raquo');
                        $('.js-side-flex').addClass('js-side').removeClass('js-side-flex');
                        _flexStorage(true);
                    }).next('.main').stop().animate({marginLeft: "80px"}, 200);
                }
            }, {
                el: '.js-side',
                event: 'click',
                handler: function () {
                    // 侧边栏展开
                    $('.sidebar').removeClass('sidebar-flexing');
                    var topWid=$(window).width()-220;

                    $('.top-menu').stop().animate({width:topWid+'px'},200);

                    $('.main').stop().animate({marginLeft: "220px"}, 200);
                    $('.sidebar').stop().animate({width: "220px", paddingLeft: "40px"}, 200, 'swing', function () {
                        $('.flex-trigger b').html('&laquo');
                        $('.js-side').addClass('js-side-flex').removeClass('js-side');
                        _flexStorage(false);
                    });

                    $('.sidebar').removeClass('sidebar-flexed');

                }
            },
            {
                el: '#js-my-Note',
                event: 'click',
                handler: function () {
                    // 点击header中的 我的笔记 侧边栏展开
                    _flexStorage(false);
                }
            }
        ])
    }

    /**
     * 增加反馈按钮
     * @private
     */
    function _addTicklingButton() {
        $('#back-to-top').before('<p id="tickling">反馈</p>');
    }

    /**
     * 描述：弹出意见反馈框
     * @private
     */
    function _showFeedbank() {
        layer.open({
            skin: 'tickling',
            type: 0,
            title: "感谢您的反馈",
            area: ['476px', '320px'], //宽高
            content: '<form id="validate"><textarea id="feedback" maxlength="1000" name="feedback" style="width: 430px;height: 155px;"></textarea><br><div id="info-position"></div></form>',
            btn: ['确定', '取消'],
            yes: function () {
                //保存反馈
                if (_formValidate()) {
                    _addFeedback($("#feedback").val());
                }
            }
        });
    }

    /**
     * 描述：保存反馈信息
     * @private
     */
    function _addFeedback(info) {
        model.addFeedback({
            data: {content: info},
            callBack: function (data) {
                layer.closeAll();
                layer.msg("反馈成功", {icon: 1});
            },
            error: function (jqXHR) {
                layer.msg("反馈失败，请重试", {icon: 2});
            }
        });
    }

    /**
     * 表单校验
     * @returns {*|jQuery}
     * @private
     */
    function _formValidate() {
        return $('#validate').validate({
            errorPlacement: function (error, element) {
                error.appendTo($("#info-position"));
            },
            rules: {
                feedback: {
                    required: true
                }
            },
            messages: {
                feedback: {
                    required: '请输入反馈内容！'
                }
            }
        }).form();
    }

    /**
     * 设置收缩标志
     * @private
     */
    function _flexStorage(flex) {
        model.flexStorage({
            data: {flex: flex},
            callBack: function (data) {

            },
            error: function (jqXHR) {

            }
        });
    }

    /**
     * 用户退出
     * @private
     */
    function _logout() {
        layer.confirm("您确定要退出吗？",
            {
                icon: 3,
                btn: ['确定', '取消'] //按钮
            },
            function () {
                window.location.href = window.__NOTEBOOK__.root_url + "client/logout";
            }
        )
    }

    // 返回
    return {
        init: _init
    }
});