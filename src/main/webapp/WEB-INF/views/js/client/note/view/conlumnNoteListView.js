define(['client/note/model/columnNoteListModel', 'common/util', 'layer1', 'handlebars', 'common/hbsHelper', 'pagination'], function (model, util, layer, handlebars) {

    function init() {
        //事件绑定
        _bind();
    }

    /**
     * 描述：事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([
            {
                el: '#delete-column',
                event: 'click',
                handler: function () {
                    var columnId = $('#js-columnId').val();
                    layer.confirm("确认删除本专栏？", {icon: 3}, function () {
                        _deleteColumn(columnId);
                    });
                }
            }
        ]);
    }

    /**
     * 描述：删除专栏
     * @private
     */
    function _deleteColumn(columnId) {
        model.deleteColumn({
            data: {columnId: columnId},
            callBack: function (data) {
                if (data.data) {
                    layer.msg("删除成功！", {icon: 1, time: 500}, function () {
                        window.location.href = window.__NOTEBOOK__.root_url + "person/columns/list"
                    });
                } else {
                    layer.msg("删除失败，请重试！", {icon: 2});
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    return {
        init: init
    }
});