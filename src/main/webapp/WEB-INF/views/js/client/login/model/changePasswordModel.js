define(['common/http'], function (http) {

    /**
     * 描述：密码修改
     */
    function modifypass(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/modifypass",
            data: options.data,
            type: "post",
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }


    return {
        modifypass: modifypass
    }
});