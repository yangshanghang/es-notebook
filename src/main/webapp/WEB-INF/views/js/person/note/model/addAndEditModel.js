define(['common/http'], function (http) {


    /**
     * 描述：新增/修改笔记
     * @param options
     */
    function addOrEditNote(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/note/addoredit",
            data: options.data,
            type: 'post',
            beforeSend: function () {
                options.beforeSend();
            },
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }


    /**
     * 心跳请求
     * @param options
     */
    function heartbeat(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/note/heartbeat",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    return {
        addOrEditNote: addOrEditNote,
        heartbeat: heartbeat
    }
});