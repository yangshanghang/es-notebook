/**
 * Created by zhangmengwen on 2016/11/14.
 */
define(['client/note/model/indexModel', 'common/util'], function (model, util) {
    // 初始化
    function init() {
        //事件绑定
        _bind();
    }

    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([
            {
                el: '#query',
                event: 'click',
                handler: function () {
                    _getNoteList();
                }
            }, {
                el: '#queryParam',
                event: 'keypress',
                handler: function () {
                    if (event.keyCode == 13) {
                        $('#query').click();
                        //停止事件冒泡
                        event.preventDefault();
                    }
                }
            },{
                el: '.banner-close',
                event: 'click',
                handler: function () {
                    $(this).parent('.top-banner').animate({
                        height: "0"
                    }, 500);

                }
            }
        ])
    }

    /**
     * 获取笔记列表，(查询条件输入时，才提交表单)
     * @private
     */
    function _getNoteList() {
        if ($('#queryParam').val().trim() == '') {
            window.location.reload();
            return;
        }
        $('#js-form').submit();
    }

    return {
        init: init
    }
});