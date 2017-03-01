/**
 * Created by Administrator on 2016/11/10.
 */
define(['common/http'], function (http) {


    /**
     * 描述：获取笔记列表
     */
    function getNoteList(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/note/query",
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

    /**
     * 描述：删除笔记
     */
    function deleteNote(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/note/delete",
            type: "post",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    return {
        getNoteList: getNoteList,
        deleteNote: deleteNote
    }
});