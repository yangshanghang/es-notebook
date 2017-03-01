define(['common/http'], function (http) {

    /**
     * 描述：删除专栏
     */
    function deleteColumn(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/columns/delete",
            type: "post",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    return {
        deleteColumn: deleteColumn
    }
});