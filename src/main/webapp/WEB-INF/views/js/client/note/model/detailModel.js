define(['common/http'], function (http) {

    /**
     * 描述：点赞
     */
    function pushPraise(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "client/note/pushpraise",
            type: "post",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    /**
     * 发表评论
     * @param options
     */
    function makeComments(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/comment/makecomments",
            type: "post",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    /**
     * 判断用户是否已收藏此笔记
     * @param options
     */
    function isFavorite(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/myfavorite/isFavorite",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    /**
     * 收藏笔记
     * @param options
     */
    function addMyFavorite(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/myfavorite/add",
            type: "post",
            data: options.data,
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

    /**
     * 加载更多评论
     * @param options
     */
    function getMoreComments(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/comment/getmorecomments",
            data: options.data,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }

    /**
     * 删除评论
     * @param options
     */
    function deleteComment(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/comment/deletecomment",
            data: options.data,
            type: 'post',
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }
    
    return {
        pushPraise: pushPraise,
        makeComments: makeComments,
        isFavorite: isFavorite,
        addMyFavorite: addMyFavorite,
        deleteNote: deleteNote,
        getMoreComments: getMoreComments,
        deleteComment: deleteComment
    }
});