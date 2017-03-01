define(['common/http'], function (http) {

    /**
     * 描述：获取专栏列表
     */
    function getColumnList(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/columns/query",
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
        getColumnList: getColumnList
    }
});