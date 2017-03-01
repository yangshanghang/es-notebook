/**
 * Created by Administrator on 2016/12/7.
 */
define(['client/login/model/changePasswordModel', 'common/util', 'layer1', 'common/validateRules'], function (model, util, layer) {
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
            el: '#submit',
            event: 'click',
            handler: function () {
                // 校验通过，执行登录请求操作
                if (_formValidate()) {
                    _modifypass();
                }
            }
        }, {
            el: '#password',
            event: 'keypress',
            handler: function () {
                if (event.keyCode == 13) {
                    $("#submit").click();
                    //取消表单提交的默认行为
                    window.event.returnValue = false; //IE
                    event.preventDefault(); //其他
                }
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
                password: {
                    required: true,
                    minlength: 6,
                    chrnum: true
                }
            },
            messages: {
                password: {
                    required: '密码不能为空',
                    minlength: '6-20个字符'
                }
            }
        }).form();
    }

    /**
     * 描述：密码修改
     * @private
     */
    function _modifypass() {
        model.modifypass({
            data: $('#js-form').serialize(),
            callBack: function (data) {
                if (data.data) {
                    layer.confirm("密码修改成功，是否立即登录？", {icon: 3, btn: ['是', '否']}, function () {
                        window.location.href = window.__NOTEBOOK__.root_url + "client/tologin?ref=index";
                    });
                } else {
                    layer.alert("密码修改失败，请重试", {icon: 2});
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