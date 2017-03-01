define(['client/login/model/registerModel', 'common/util', 'layer1', 'common/validateRules'], function (model, util, layer) {
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
            el: '.js-user-reg',
            event: 'click',
            handler: function () {
                // 校验通过，执行登录请求操作
                if (_formValidate()) {
                    _userRegister();
                }
            }
        }, {
            el: 'body',
            event: 'keypress',
            handler: function () {
                if (event.keyCode == 13) {
                    $('.js-user-reg').click();
                }
            }
        }, {
            el: 'input',
            event: 'keydown',
            handler: function () {
                $('.js-error').empty();
            }
        }, {
            el: '.js-again-write',
            event: 'click',
            handler: function () {
                $('.js-register-success').hide();
                $('#js-form').show();
            }
        }
        ])
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
                    required: true,
                    minlength: 6,
                    chrnum: true
                },
                realName: {
                    isNotBlack: true,
                    isChinese: true
                },
                qq: {
                    required: true,
                    checkQQ: true,
                    maxlength: 10,
                }
            },
            messages: {
                email: {
                    required: '邮箱不能为空',
                    email: '请输入正确的邮箱'

                },
                password: {
                    required: '密码不能为空',
                    minlength: '6-20个字符'
                },
                realName: {
                    isNotBlack: '姓名不可为空',
                    isChinese: '请输入至少两个汉字'
                },
                qq: {
                    required: 'QQ不可为空',
                    checkQQ: '请输入正确的QQ号',
                    maxlength: '请输入正确的QQ号'
                }
            }
        }).form();
    }

    /**
     * 用户注册
     * @private
     */
    function _userRegister() {
        model.userRegister({
            data: $('#js-form').serialize(),
            beforeSend: function () {
                layer.load(2, {shade: [0.5, '#f5f5f5']});
            },
            callBack: function (data) {
                layer.closeAll();
                if (data.status == 200) {
                    // 显示注册成功内容
                    $('.js-register-success').show();
                    $('#js-form').hide();
                    $('.js-email').text($('input[name=email]').val());
                    // 在配置中有该邮箱，则显示登录邮箱按钮
                    if (data.data == '' || data.data == null) {
                        $('#js-address').hide();
                    } else {
                        $('#js-address').show();
                        $('#js-address').attr('href', 'http://' + data.data);
                    }
                } else if (data.subStatus == 12) {
                    $('.js-error').html('<label class="error">该邮箱已注册！</label>').show();
                } else if (data.subStatus == 13) {
                    $('.js-error').html('<label class="error">激活邮件发送失败，请重试!</label>').show();
                } else {
                    layer.alert('注册失败，请重试！');
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