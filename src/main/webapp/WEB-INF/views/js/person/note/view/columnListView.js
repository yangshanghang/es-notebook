define(['person/note/model/columnListModel', 'common/util','layer1', 'handlebars', 'common/hbsHelper', 'pagination'], function (model, util,layer, handlebars) {
    var pageSize = 10;

    function init() {
        //初始化分页
        _getFirstPage();
        _bind();
    }

    /**
     * 描述：事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([{
            el: '.js-delete',
            event: 'click',
            handler: function () {
                var columnId = $(this).attr('js-id');
                layer.confirm("确认删除本专栏？", {icon: 3}, function () {
                    _deleteColumn(columnId);
                });
            }
        }, {
            el: '.js-type',
            event: 'click',
            handler: function () {
                //添加样式
                $('.js-type').removeClass('cur').removeAttr('js-type');
                $(this).addClass('cur').attr('js-type', '1');
                _initParamGetNote(0, $(this).attr('js-category-id'));
            }
        }, {
            el: '.js-hiddenType',
            event: 'click',
            handler: function () {
                //清除类目选中样式
                $(this).siblings().show();
                $(".js-type").removeClass('cur').removeAttr('js-type');
                //删除之前的更多类目
                $("#ded").remove();
                //添加选中的更多类目
                $(".js-allType li").last().before("<li id='ded'><a js-category-id='"+$(this).attr("js-category-id")+"' class='js-type cur ellips' js-type='1' title='"+$(this).text()+"'>"+$(this).text()+"</a></li>");
                $(this).hide();
                //添加样式
                _initParamGetNote(0, $(this).attr('js-category-id'));
                if($('.more-type a:visible').length == 0){
                    $('.more').hide();
                }
            }
        }, {
            el: '#query',
            event: 'click',
            handler: function () {
                _getMyColumnList(0, $(".js-type[js-type='1']").attr('js-category-id'));
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
     * 描述：删除专栏
     * @private
     */
    function _deleteColumn(columnId) {
        model.deleteColumn({
            data: {columnId: columnId},
            callBack: function (data) {
                if (data.data) {
                    layer.msg("删除成功！", {icon: 1, time: 500}, function () {
                        _initParamGetNote(0, $('.type .js-type[js-type="1"]').attr('js-category-id'));
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

    /**
     * 描述：初始化ajax参数
     * @private
     */
    function _initParamGetNote(pageNum, typeId) {
        if (typeId) {
            _getMyColumnList(pageNum, typeId);
        } else {
            _getMyColumnList(pageNum);
        }
    }

    /**
     * 描述：获取我的专栏列表
     * @private
     */
    function _getMyColumnList(pageNum, categoryId) {
        var queryParam = $.trim($("#queryParam").val());
        model.getMyColumnList({
            data: {categoryId: categoryId, queryParam: queryParam, pageNum: pageNum, pageSize: pageSize},
            beforeSend: function () {
                layer.load(2,{
                    shade: [0.1,'#d9d9d9'],
                    type:3
                });
            },
            callBack: function (data) {
                layer.closeAll();
                //总条数
                var total = data.data.total;
                //设置用时(秒)
                $("#took").text(data.data.took);
                // 设置总条数
                $("#itemCount").text(total);
                //限制实际查看页数
                if (total > 1000) {
                    total = 1000;
                }
                //创建模板
                var template = handlebars.compile($('#listTemplate').html());
                //拼接数据
                var hitsList = {hitsList: data.data.hits};
                //插入模板
                $('.js-template').html(template(hitsList));
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
        _initParamGetNote(pageNum, $(".js-type[js-type='1']").attr('js-category-id'));
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