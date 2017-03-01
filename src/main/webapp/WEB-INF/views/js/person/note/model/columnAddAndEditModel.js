define(['common/http'], function (http) {


    /**
     * 描述：新增/修改专栏
     * @param options
     */
    function addOrEditNote(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/columns/addoredit",
            data: options.data,
            type: 'post',
            serializable:true,
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

    /**
     * 笔记筛选
     * @param options
     */
    function noteFilter(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/columns/querynotcolumnnote",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            beforeSend: options.beforeSend,
            error: options.error
        });
    }


    return {
        addOrEditNote: addOrEditNote,
        heartbeat: heartbeat,
        noteFilter:noteFilter
    }
});