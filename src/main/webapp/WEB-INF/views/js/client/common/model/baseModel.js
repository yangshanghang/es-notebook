define(['common/http'], function (http) {

    /**
     * 描述：反馈
     */
    function addFeedback(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/feedback/add",
            type: "post",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    /**
     * 描述：收缩标志
     */
    function flexStorage(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/setflexsession",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    return {
        addFeedback: addFeedback,
        flexStorage: flexStorage
    }
});
