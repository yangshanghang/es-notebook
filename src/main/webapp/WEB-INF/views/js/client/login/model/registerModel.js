define(['common/http'], function (http) {
    /**
     * 用户注册
     * @param options
     */
    function userRegister(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/register",
            data: options.data,
            type: "post",
            dataType:'json',
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
        userRegister: userRegister
    }
});