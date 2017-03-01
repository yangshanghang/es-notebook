define(['client/login/model/loginModel', 'common/util', 'layer1', 'common/validateRules'], function (model, util, layer) {
    /**
     * 初始化渲染
     */
    function init() {
        //事件绑定
        _bind();
        //隐藏反馈按钮
        $('#tickling').hide();
    }

    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([{
            el: '.js-user-log',
            event: 'click',
            handler: function () {
                // 校验通过，执行登录请求操作
                if (_formValidate()) {
                    _userLogin();
                }
            }
        }, {
            el: 'body',
            event: 'keypress',
            handler: function () {
                if (event.keyCode == 13) {
                    $('.js-user-log').click();
                }
            }
        },{
            el:'input',
            event:'keydown',
            handler:function () {
                $('.error-place').empty();
            }
        }])
    }

    /**
     * 表单校验
     * @returns {*|jQuery}
     * @private
     */
    function _formValidate() {
        return $('#js-form').validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                email: {
                    required: '邮箱不可为空',
                    email: '请输入正确的邮箱'
                }, password: {
                    required: '密码不可为空'
                }
            }
        }).form();
    }

    /**
     * 用户登录
     * @param pageNum
     * @private
     */
    function _userLogin() {
        model.userLogin({
            data: $('#js-form').serialize(),
            callBack: function (data) {
                if (data.data) {
                    if ($('#ref').val() == "person") {
                        //进入我的笔记页面
                        window.location.href = window.__NOTEBOOK__.root_url + "person/note/list";
                    }else if(document.referrer.indexOf("detail")>-1){
                        //详情页点击登录
                        window.location.href = document.referrer;
                    }else if ($('#ref').val() == "index" || document.referrer == "") {
                        //头部登录按钮除详情页，以及无来源url时
                        window.location.href = window.__NOTEBOOK__.root_url + "client/index";
                    } else {
                        window.location.href = document.referrer;
                    }
                } else if (data.subStatus == '14') {
                    $('.error-place').html('<label class="error logfail">你的账号被锁定，请联系管理员。</label>').show();
                }
                else if (data.subStatus == '15') {
                    $('.error-place').html(' <a id="js-activate"  href="/client/toactivateemail">你的账号未激活，请点击激活！</a>').show();
                }
                else {
                    $('.error-place').html('<label class="error logfail">账号或密码错误，请重新输入。</label>').show();
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    return {
        init: init
    }
});