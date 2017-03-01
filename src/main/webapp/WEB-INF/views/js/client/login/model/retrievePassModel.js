define(['common/http'], function (http) {
    /**
     * 用户找回密码
     * @param options
     */
    function userRetrievePass(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/sendemail",
            beforeSend: options.beforeSend,
            data: options.data,
            dataType: 'json',
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
        userRetrievePass: userRetrievePass
    }
});