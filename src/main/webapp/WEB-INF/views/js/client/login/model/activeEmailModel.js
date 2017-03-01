/**
 * Created by Administrator on 2016/12/5.
 */
define(['common/http'], function (http) {


    /**
     * 描述：发送激活邮件
     * @param options
     */
    function sendActivateEmail(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/sendactivateemail",
            data: options.data,
            beforeSend: function () {
                options.beforeSend();
            },
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    return {
        sendActivateEmail: sendActivateEmail
    }
});