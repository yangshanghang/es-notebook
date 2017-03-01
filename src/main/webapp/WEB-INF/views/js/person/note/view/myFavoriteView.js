define(['person/note/model/myFavoriteModel', 'common/util', 'layer1', 'handlebars', 'common/hbsHelper', 'pagination'], function (model, util, layer, handlebars) {
    var pageSize = 10;

    function init() {
        //初始化分页
        _getFirstPage();
        //事件绑定
        _bind();
    }

    /**
     * 描述：事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([{
            el: '#query',
            event: 'click',
            handler: function () {
                _initParamGetNot(0, $(".js-type[js-type='1']").attr('js-category-id'));
            }
        }, {
            el: '.js-type',
            event: 'click',
            handler: function () {
                //添加样式
                $(".js-type").removeClass("cur").removeAttr("js-type");
                $(this).addClass("cur").attr("js-type", "1");
                _initParamGetNot(0, $(this).attr("js-category-id"));
            }
        }, {
            el: '.js-cancel',
            event: 'click',
            handler: function () {
                var noteId = $(this).attr("js-category-id");
                layer.confirm("确认取消收藏？", {icon: 3}, function () {
                    //取消收藏笔记
                    _deleteMyFavorite(noteId);
                });
            }
        }, {
            el: '.js-hiddenType',
            event: 'click',
            handler: function () {
                //清除选中的样式
                $(this).siblings().show();
                $(".js-type").removeClass('cur').removeAttr('js-type');
                //清除隐藏添加的
                $("#ded").remove();
                //添加隐藏的
                $(".js-allType li").last().before("<li id='ded'><a class='js-type cur ellips' js-type='1'  title='"+$(this).text()+"' js-category-id='" + $(this).attr("js-category-id") + "'>" + $(this).text() + "</a></li>");
                $(this).hide();
                _initParamGetNot(0, $(this).attr("js-category-id"));
                if($('.more-type a:visible').length == 0){
                    $('.more').hide();
                }
            }
        }, {
            el: '#queryParam',
            event: 'keypress',
            handler: function () {
                if (event.keyCode == 13) {
                    $('#query').click();
                }
            }
        }
        ]);
    }

    /**
     * 描述:删除我的收藏
     * @param id 笔记id
     * @private
     */
    function _deleteMyFavorite(notId) {
        model.deleteMyFavorite({
            data: {noteId: notId},
            callBack: function (data) {
                if (data) {
                    layer.msg("取消收藏成功", {icon: 1, time: 500}, function () {
                        _initParamGetNot(0, $('.type .js-type[js-type="1"]').attr('js-category-id'));
                    })
                } else {
                    layer.msg("取消收藏失败", {icon: 2, time: 500});
                }

            }, error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 描述：初始化ajax参数
     * @private
     */
    function _initParamGetNot(pageNum, categoryId) {
        if (categoryId) {
            _queryMyFavorite(pageNum, categoryId);
        } else {
            _queryMyFavorite(pageNum);
        }
    }

    /**
     * 描述：获取收藏列表
     * @private
     */
    function _queryMyFavorite(pageNum, categoryId) {
        var queryParamLike = $.trim($("#queryParam").val());
        model.queryMyFavorite({
            data: {categoryId: categoryId, queryParamLike: queryParamLike, pageNum: pageNum + 1, pageSize: pageSize},
            beforeSend: function () {
                layer.load(2, {
                    shade: [0.1, '#d9d9d9'],
                    type: 3
                });
            },
            callBack: function (data) {
                layer.closeAll();
                //总条数
                var total = data.data.total;
                //设置用时(秒)
                $("#took").text(data.data.took / 1000);
                // 设置总条数
                $("#itemCount").text(total);
                //限制实际查看页数
                if (total > 1000) {
                    total = 1000;
                }
                //创建模板
                var template = handlebars.compile($('#listTemplate').html());
                //插入模板
                $('.js-template').html(template(data.data));
                //分页
                _pagination(total, pageNum);
                //若查询总条数为0 隐藏分页条
                if (total == '0') {
                    $("#js-pagination").hide();
                } else {
                    $("#js-pagination").show();
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 分页配置参数
     * @param data
     * @param index
     * @private
     */
    function _pagination(total, index) {
        $('#pagination').pagination(total, {
            items_per_page: pageSize,
            prev_text: '<i class="icon-triangle-left"></i>上一页',
            next_text: '下一页<i class="icon-triangle-right"></i>',
            num_display_entries: 10,
            current_page: index,
            link_to: 'javascript:void(0)',
            callback: _pageSelectCallback
        });
    }

    /**
     * 点击分页的回调函数
     * @param pageNum   当前点击的那个分页的页数索引值
     * @param jq        装载容器
     * @private
     */
    function _pageSelectCallback(pageNum, jq) {
        $('html,body').animate({scrollTop: 0}, 10);
        _initParamGetNot(pageNum, $(".js-type[js-type='1']").attr('js-category-id'));
    }

    /**
     * 渲染初始分页首页
     * @private
     */
    function _getFirstPage() {
        if ($('#itemCount').text() > 0) {
            var itemCount = $("#itemCount").text();
            //限制实际查看页数
            if (itemCount > 1000) {
                itemCount = 1000;
            }
        } else {
            $("#js-pagination").hide();
        }
        _pagination(itemCount, 0);
    }

    return {
        init: init
    }
});