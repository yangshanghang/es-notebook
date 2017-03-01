/**
 * Created by zhangmengwen on 2016/11/24.
 */
define(['common/http'], function (http) {
    /**
     * 保存我的关注设置
     * @param options
     */
    function saveMyFocus(options) {
        http.httpRequest({
            url: window.__NOTEBOOK__.root_url + "person/myfocus/add",
            type: 'post',
            data: options.data,
            beforeSend: options.beforeSend,
            serializable: true,
            success: function (data) {
                options.callBack(data);
            },
            error: options.error
        });
    }
    
    return{
        saveMyFocus: saveMyFocus
    }
});