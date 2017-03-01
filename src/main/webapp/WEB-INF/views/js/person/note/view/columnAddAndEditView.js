define(['person/note/model/columnAddAndEditModel', 'common/util', 'layer1', 'handlebars', 'common/validateRules', 'jquery.ui', 'jquery.serialize'], function (model, util, layer, handlebars) {
    /**
     * 已选笔记列表
     * @private
     */
    var selectedlist = [],noteIds=[];
    var queryParam, searchItem = null, num = 1,loading=false;
    var template = handlebars.compile($('#listTemplate').html());
    var flag = true;

    function init() {
        //页面初始化操作
        _pageInit();
        //事件绑定
        _bind();
        //拖动排序
        _sortable();
        //滚动加载
        _scrollAdd();
        //页面离开/重载事件
        window.onbeforeunload = function () {
            return '为确保内容不会丢失，建议保存后关闭页面';
        }
    }

    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([
            {
                el: '#js-submit',
                event: 'click',
                handler: function () {
                    if (_formValidate()) {
                        if ($(".js-allType .cur").size() == 0) {
                            layer.alert("请选择专栏类别", {icon: 0});
                            return false;
                        }

                        selectedlist = [];
                        $(".note-selected li").each(function (n, v) {
                            selectedlist.push({columnOrderNumber: n, id: $(this).data("id")});
                        });
                        var categoryId = $(".js-type[js-type='1']").attr('js-category-id');
                        var columnData = $("#js-form").serializeObject();
                        columnData.categoryId = categoryId;
                        columnData.noteList = selectedlist;
                        _addOrEditNote(columnData);
                    }
                    else {
                        $('html,body').animate({scrollTop: 0}, 300);
                    }
                }
            }, {
                el: '.js-look-note',
                event: 'click',
                handler: function () {
                    queryParam = $('.js-look-text').val();
                    noteFilter(0,true);
                }
            },{
                el: '.js-look-text',
                event: 'keypress',
                handler: function () {
                    if (event.keyCode == 13) {
                        $('.js-look-note').click();
                    }
                }
            },
            {
                el: '.js-type',
                event: 'click',
                handler: function () {
                    //添加样式
                    $('.js-type').removeClass('cur').removeAttr('js-type');
                    $(this).addClass('cur').attr('js-type', '1');
                    return false;
                }
            }, {
                el: '.js-hiddenType',
                event: 'click',
                handler: function () {
                    //清除类目选中样式
                    $(this).siblings().show();
                    $(".js-type").removeClass('cur').removeAttr('js-type');
                    $("#ded").remove();
                    //添加选中的更多类目
                    $(".js-allType li").last().before("<li id='ded'><a js-category-id='" + $(this).attr("js-category-id") + "' class='js-type cur ellips' js-type='1'>" + $(this).text() + "</a></li>");
                    $(this).hide();
                    if ($('.more-type a:visible').length == 0) {
                        $('.more').hide();
                    }
                    return false;
                }
            }, {
                el: '#cancel',
                event: 'click',
                handler: function () {
                    top.location = window.__NOTEBOOK__.root_url + "person/columns/list";
                }
            }, {
                el: '#inputTitle',
                event: 'keypress',
                handler: function () {
                    // 清除回车键默认提交表单
                    if (event.keyCode == 13) {
                        window.event.returnValue = false; //IE
                        event.preventDefault(); //其他
                    }
                }
            }, {

                el: '.js-note-minus',
                event: 'click',
                handler: function () {
                    $(this).attr('class', 'add-icon js-note-add').text('+');
                    var indexId = $(this).parent().data('id');
                    $.each(noteIds, function (n, v) {
                        if (v == indexId) {
                            noteIds.splice(n, 1);
                            return false;
                        }
                    });

                    $('.search-result').append($(this).parent());
                }
            }, {
                el: '.js-note-add',
                event: 'click',
                handler: function () {
                    //未选笔记栏不足十条时加载数据
                    if($('.search-result li').length<=10){
                        //等待异步加载完成后再次响应点击事件
                        if(!flag)return;
                        flag = false;
                        _columnAddNote($(this));
                        loading=true;
                        noteFilter(0);

                    }else{
                        _columnAddNote($(this));
                    }

                }
            }, {
                el: '.js-sort',
                event: document.mozHidden !== undefined ? 'DOMMouseScroll' : 'mousewheel',
                handler: function () {
                    //阻止内部滚动条影响外部滚动条
                    var scrollTop = this.scrollTop,
                        scrollHeight = this.scrollHeight,
                        height = this.clientHeight;

                    var delta = (event.wheelDelta) ? event.wheelDelta : -(event.detail || 0);

                    if ((delta > 0 && scrollTop <= delta) || (delta < 0 && scrollHeight - height - scrollTop <= -1 * delta)) {
                        // IE浏览器兼容
                        this.scrollTop = delta > 0 ? 0 : scrollHeight;
                        // 向上滚 || 向下滚
                        event.preventDefault();
                    }
                }
            }
        ])
    }

    /**
     * 描述：新增/修改笔记
     * @private
     */
    function _addOrEditNote(columnData) {
        model.addOrEditNote({
            data: columnData,
            beforeSend: function () {
                layer.load(2, {shade: [0.5, '#f5f5f5']});
            },
            callBack: function (data) {
                //提交按钮取消页面离开/重载事件
                window.onbeforeunload = null;
                top.location = window.__NOTEBOOK__.root_url + "person/columns/list";
            }, error: function (jqXHR) {
                layer.closeAll();
                layer.alert('专栏创建失败，请重试');
            }
        });
    }

    /**
     * 描述：已选笔记栏中添加笔记
     * @private
     */
    function _columnAddNote(node) {
        node.attr('class', 'minus-icon js-note-minus').html('&minus;');
        noteIds.push(node.parent().data("id"));
        $('.note-selected').append(node.parent());
    }
    /**
     * 描述：页面初始化操作
     * @private
     */
    function _pageInit() {
        var typeId = $("#typeId").val();
        //如果 $("#typeId").val() 为空 则是新增操作，否则是修改操作
        if ($("#typeId").val()) {
            $(".js-allType li a").each(function () {
                if ($(this).attr('js-category-id') == typeId) {
                    $(this).addClass('cur').attr("js-type", "1");
                    return false;
                }
            });

            $("#js-addType").children().each(function () {
                if ($(this).attr('js-category-id') == typeId) {
                    $(this).removeClass('cur');
                    $('#ded').remove();
                    $(this).hide();
                    $(".js-allType li").last().before("<li id='ded'><a js-type='1' js-category-id='" + $(this).attr('js-category-id') + "' class='cur js-hiddenType' title='" + $(this).text() + "'>" + $(this).text() + "</a></li>");
                    return false;
                }
            });

            //将已选笔记添加到数组,初始化
            $(".note-selected li").each(function () {
                noteIds.push($(this).data("id"));
            });

        }

        // 心跳，保持当前页面session持续生效
        _heartbeat();
    }

    /**
     * 10分钟心跳一次
     * @private
     */
    function _heartbeat() {
        window.setInterval(function () {
            model.heartbeat({
                data: {},
                callBack: function (data) {
                },
                error: function (jqXHR) {
                }
            });
        }, 600000);
    }

    /**
     * 表单校验
     * @returns {*|jQuery}
     * @private
     */
    function _formValidate() {
        return $('#js-form').validate({
            rules: {
                name: {
                    isNotBlack: true
                },
                introduce: {
                    isNotBlack: true,
                    maxlength: 180
                }
            },
            messages: {
                name: {
                    isNotBlack: '请输入专栏名称'
                },
                introduce: {
                    isNotBlack: '请输入专栏介绍',
                    maxlength: '专栏介绍位数不可大于180'
                }
            }
        }).form();
    }

    /**
     * 笔记顺序拖动排序
     * @private
     */
    function _sortable() {
        $(".js-sort").sortable({
            connectWith: ".connectedSortable",
            cursor: "move",
            remove: function (event, ui) {
                var $this = $(ui.item);

                if ($(event.target).hasClass('search-result')) {
                    $this.find('i').attr('class', 'minus-icon js-note-minus').html('&minus;');
                    noteIds.push($this.data("id"));
                    if($('.search-result li').length<=10){
                        loading=true;
                        noteFilter(0);
                    }
                } else {
                    $this.find('i').attr('class', 'add-icon js-note-add').text('+');
                    var indexId = $this.data('id');
                    $.each(noteIds, function (n, v) {
                        if (v == indexId) {
                            noteIds.splice(n, 1);
                            return false;
                        }
                    });
                }
            }
        }).disableSelection();
    }

    /**
     * 滚动加载更多笔记
     * @private
     */
    function _scrollAdd() {
        $('.search-result').scroll(function () {
            var scrollTop = this.scrollTop,
                scrollHeight = this.scrollHeight,
                height = this.clientHeight;
            if ((scrollHeight - height > scrollTop)||loading) {
                return false;
            }

            searchItem === queryParam ? num++ : num = 1;
            searchItem = queryParam;
            loading = true;

            noteFilter(num)
        })
    }

    /**
     * 笔记搜索
     * @private
     */
    function noteFilter(pageNum,isNewFilter) {
        var columnId = $('#js-columnId').val();
        model.noteFilter({
            data: {
                noteIds: noteIds,
                queryParam: queryParam,
                columnId: columnId,
                pageNum: pageNum,
                pageSize: 10
            },
            beforeSend: function () {
                if(pageNum!=0){
                    $('.search-result').append('<li class="center item-loading"><img src="../../../img/loading-large.gif"></li>');
                }

                if(isNewFilter&&pageNum==0){
                   $('.search-result').html('<li class="center item-loading alldialog"><img src="../../../img/loading-pic.gif"></li>');
                }
            },
            callBack: function (data) {
                flag =  true;
                $('.item-loading').remove();
                if (pageNum == 0) {
                    $('.search-result').html(template(data.data.hits));
                } else {
                    $('.search-result').append(template(data.data.hits));
                }
                loading=false;

            }, error: function (jqXHR) {
                $('.item-loading').remove();
                layer.alert('操作失败，请重试');
                loading=false;
                flag =  true;
            }

        });
    }


    return {
        init: init
    };
});
