/**
 * Created by Administrator on 2016/11/10.
 */
define(['common/http'], function (http) {


    /**
     * 描述：获取我的专栏列表
     */
    function getMyColumnList(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/columns/query",
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
        getMyColumnList: getMyColumnList,
        deleteColumn: deleteColumn
    }
});