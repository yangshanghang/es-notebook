/**
 * Created by Administrator on 2016/12/5.
 */
define(['client/login/model/activeEmailModel', 'common/util', 'layer1', 'common/validateRules'], function (model, util, layer) {
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
        util.bindEvents([
            {
                el: '.js-user-pass',
                event: 'click',
                handler: function () {
                    // 校验通过，执行登录请求操作
                    if (_formValidate()) {
                        _sendActivateEmail();
                    }
                }
            }, {
                el: '#js-email',
                event: 'keypress',
                handler: function () {
                    // 回车绑定
                    if (event.keyCode == 13) {
                        $('.js-user-pass').click();
                        //取消表单提交的默认行为
                        window.event.returnValue = false; //IE
                        event.preventDefault(); //其他
                    }
                }
            }, {
                el: 'input',
                event: 'keydown',
                handler: function () {
                    $('.error-place').empty();
                }
            }
        ])
    }

    /**
     * 描述：发送激活邮件
     * @private
     */
    function _sendActivateEmail() {
        model.sendActivateEmail({
            data: {
                email: $("#js-email").val()
            },
            beforeSend: function () {
                layer.msg('正在发送邮件，请稍候', {icon: 16, time: -1, shade: 0.3});
            },
            callBack: function (data) {
                layer.closeAll();
                if (data.data) {
                    window.location.href = window.__NOTEBOOK__.root_url + "client/sendemailsuccess/activateemail"
                } else if (data.subStatus == '11') {
                    $('.error-place').html('<label class="error">此邮箱未注册，请先注册！</label>').show();
                } else if (data.subStatus == '12') {
                    $('.error-place').html('<label class="error">此邮箱已激活，请直接登录！</label>').show();
                } else {
                    $('.error-place').html('<label class="error">邮件发送失败，请重试!</label>').show();
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
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
                }
            },
            messages: {
                email: {
                    required: '邮箱不能为空',
                    email: '请输入正确的邮箱'
                }
            }
        }).form();
    }

    return {
        init: init
    }
})