define(['common/http'], function (http) {

    /**
     * 描述：获取笔记列表
     */
    function getNoteList(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/note/query",
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
        getNoteList: getNoteList
    }
});