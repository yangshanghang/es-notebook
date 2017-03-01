define(['person/note/model/myFocusModel', 'common/util', 'layer1', 'jquery.ui'], function (model, util, layer) {
    function init() {
        //拖动排序
        _sortable();
        //事件绑定
        _bind();
    }

    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([{
            el: '#save',
            event: 'click',
            handler: function () {
                _saveMyFocus();
            }
        }]);
    }

    /**
     * 保存我的分类
     * @private
     */
    function _saveMyFocus() {
        var list = [];
        var i = 0;

        $("#js-myFocus li").each(function () {
            list.push({orderNumber: i++, categoryId: $(this).data("id")});
        });

        model.saveMyFocus({
            data: list,
            beforeSend: function () {
                layer.load(2, {shade: [0.5, '#f5f5f5']});
            },
            callBack: function (data) {
                layer.closeAll();
                if(data.status==100){
                    layer.alert('操作失败，请重试');
                }else if (data.status == 200) {
                    layer.msg("保存成功！", {icon: 1, time: 1000}, function () {
                        window.location.reload();
                    });
                }
            },
            error: function (jqXHR) {
                layer.msg(jqXHR.responseText, {time: 1000, icon: 2});
            }
        });
    }

    /**
     * 类目拖动排序
     * @private
     */
    function _sortable() {
        $( "#js-myFocus, #js-allFocus" ).sortable({
            connectWith: ".connectedSortable",
            remove: function(event, ui) {
                if($('#js-myFocus li').length==0) {
                    $('#js-myFocus').sortable('cancel');
                }
            }
        }).disableSelection();
    }

    return {
        init: init
    }
});