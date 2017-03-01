/**
 * Created by Administrator on 2016/11/28.
 */
define(['common/http'], function (http) {
    /**
     * 描述：取消我的收藏
     */
    function deleteMyFavorite(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/myfavorite/delete",
            type: "post",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }


    /**
     * 描述：ajax查询收藏
     * @param options
     */
    function queryMyFavorite(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/myfavorite/querymyfavorite",
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
        deleteMyFavorite: deleteMyFavorite,
        queryMyFavorite: queryMyFavorite
    }
});