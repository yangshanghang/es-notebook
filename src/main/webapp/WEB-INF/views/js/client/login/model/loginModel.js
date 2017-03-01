define(['common/http'], function (http) {
    /**
     * 用户登录
     * @param options
     */
    function userLogin(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/login",
            data: options.data,
            type: "post",
            dataType:'json',
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    return {
        userLogin: userLogin
    }
});