define(['client/note/model/detailModel', 'common/util', 'layer1', 'handlebars', 'jquery', 'common/hbsHelper', 'common/validateRules'], function (model, util, layer, handlebars) {

    function init() {
        //初始化笔记内容
        _initContent();
        //判断是否显示评论删除图标
        _showCommentDelIcon();
        //事件绑定
        _bind();

        //右侧本专栏固定定位
        _scroll();
    }

    var pageNum = 1; //初始评论页码


    /**
     * 事件绑定
     * @private
     */
    function _bind() {
        util.bindEvents([
            {
                el: '#js-pushPraise',
                event: 'click',
                handler: function () {
                    //点赞
                    if ($('#js-pushPraise span').text() == '已点赞') {
                        return false;
                    }
                    _pushPraise($('#js-id').val());
                }
            },
            {
                el: '.js-comment',
                event: 'click',
                handler: function () {
                    if ($('#comment').val().trim() == '') {
                        $('#comment').val('');
                    }
                    if (_formValidate()) {
                        //发表评论
                        _makeComments();
                    }
                }

            },
            {
                el: '.js-favorite',
                event: 'click',
                handler: function () {
                    //判断用户是已经登录
                    if ($("#publisher").val()) {
                        //已登录进行收藏
                        if ($('.js-favorite span').text().indexOf('已收藏') != -1) {
                            return false;
                        }
                        _addMyFavorite();
                    } else {
                        window.location.href = window.__NOTEBOOK__.root_url + "client/tologin";
                    }
                }
            },
            {
                el: '#delete',
                event: 'click',
                handler: function () {
                    var indexId = $(this).attr("js-id");
                    layer.confirm("确认删除本笔记？", {icon: 3}, function () {
                        _deleteNote(indexId);
                    });
                }
            },
            {
                el: '#js-more',
                event: 'click',
                handler: function () {
                    var indexId = $("#js-id").val();
                    pageNum++;
                    //加载更多评论
                    _moreComments(indexId, pageNum, 10);
                }
            },
            {
                el: '.js-closeComment',
                event: 'click',
                handler: function () {
                    var commentId = $(this).attr('js-commentId');
                    noteId = $("#js-id").val();
                    layer.confirm("确认删除该评论？", {icon: 3}, function () {
                        _deleteComment(commentId, noteId);
                    });
                }
            }
        ]);
    }

    /**
     * 点赞
     * @private
     */
    function _pushPraise(indexId) {
        model.pushPraise({
            data: {indexId: indexId},
            callBack: function (data) {
                if (data.data) {
                    $('#js-pushPraise span').text("已点赞");
                    // $('#js-pushPraise').attr({"disabled": "disabled"});
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 发表评论
     * @private
     */
    function _makeComments() {
        model.makeComments({
            data: $('#form-comment').serialize(),
            callBack: function (data) {
                if (data.data) {
                    layer.msg("发表成功！", {icon: 1, time: 1000});
                    //清空评论框里内容
                    $("#comment").val('');
                    var indexId = $("#js-id").val();

                    $('.js-comments').html('');
                    pageNum = 1;
                    _moreComments(indexId, pageNum, 10);
                } else if (data.subStatus == 1) {
                    layer.alert("您尚未登录或登录时间过长,请重新登录!", {icon: 3, title: '登录提示'}, function () {
                        window.location.href = window.__NOTEBOOK__.root_url + "client/tologin";
                    });
                } else if (data.subStatus == 2) {
                    layer.msg("此笔记已删除！", {icon: 2});
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 表单校验
     * @returns {*|jQuery}
     * @private
     */
    function _formValidate() {
        return $('#form-comment').validate({
            rules: {
                content: {
                    required: true,
                    maxlength: 500
                }
            }, messages: {
                content: {
                    required: '评论内容没有填写！',
                    maxlength: '评论内容最多500字！'
                }
            }
        }).form();
    }

    /**
     * 描述：收藏笔记
     * @private
     */
    function _addMyFavorite() {
        model.addMyFavorite({
            data: {noteId: $("#js-id").val()},
            callBack: function (data) {
                if (data.data) {
                    layer.msg("收藏成功", {icon: 1, time: 1000});
                    $('.js-favorite span').text("已收藏")
                } else {
                    layer.msg("此笔记已删除", {icon: 2, time: 1000});
                }
            }, error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 描述：删除笔记
     * @private
     */
    function _deleteNote(indexId) {
        model.deleteNote({
            data: {indexId: indexId},
            callBack: function (data) {
                if (data.data) {
                    layer.msg("删除成功！", {icon: 1, time: 1000}, function () {
                        window.location.href = window.__NOTEBOOK__.root_url + "person/note/list"
                    });
                } else if (data.subStatus == 1) {
                    layer.msg("您无权限操作！", {icon: 2});
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
     * 加载更多评论
     * @param indexId
     * @param pageNum
     * @param pageSize
     * @private
     */
    function _moreComments(indexId, pageNum, pageSize) {
        model.getMoreComments({
            data: {indexId: indexId, pageNum: pageNum, pageSize: pageSize},
            callBack: function (data) {
                //总条数
                var total = data.data.total;
                // 设置总条数
                $("#itemCount").text(total);
                //创建模板
                var template = handlebars.compile($('#listTemplate').html());
                //插入模板
                $('.js-comments').append($(template(data.data)));

                if (!data.data.hasNextPage) {
                    $('#js-more').hide();
                } else {
                    $('#js-more').show();
                }
                //判断是否显示评论删除图标
                _showCommentDelIcon();
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 判断是否显示评论删除图标
     * @private
     */
    function _showCommentDelIcon() {
        var userId = $("#publisher").attr("js-userId");
        //鼠标进div事件
        $(".js-commentDiv").mouseenter(function () {
            var commentUserId = $(this).find(".js-commentUserId").attr('js-commentUserId');
            if (commentUserId == userId) {
                $(this).find(".js-closeComment").css("display", "inline");
            }
        });
        //鼠标出div事件
        $(".js-commentDiv").mouseleave(function () {
            var commentUserId = $(this).find(".js-commentUserId").attr('js-commentUserId');
            if (commentUserId == userId) {
                $(this).find(".js-closeComment").css("display", "none");
            }
        });
    }

    /**
     * 删除评论
     * @param commentId
     * @private
     */
    function _deleteComment(commentId, noteId) {
        model.deleteComment({
            data: {commentId: commentId, noteId: noteId},
            callBack: function (data) {
                if (data.data) {
                    //清空评论
                    $('.js-comments').html('');
                    _moreComments($("#js-id").val(), 1, 10);
                    layer.closeAll();
                } else if (data.subStatus == 1) {
                    layer.alert("您尚未登录或登录时间过长,请重新登录!", {icon: 3, title: '登录提示'}, function () {
                        window.location.href = window.__NOTEBOOK__.root_url + "client/tologin";
                    });
                } else if (data.subStatus == 2) {
                    layer.msg("此笔记已删除", {icon: 2, time: 1000});
                } else if (data.subStatus == 3) {
                    layer.msg("您无权限操作！", {icon: 2});
                } else {
                    layer.msg("评论删除失败，请重试！", {icon: 2});
                }
            },
            error: function (jqXHR) {
                layer.alert('操作失败，请重试');
            }
        });
    }

    /**
     * 初始化笔记内容
     * @private
     */
    function _initContent() {
        $('#content').html($('#js-content').val());
    }

    /**
     * 右侧本专栏固定定位
     * @private
     */
    function _scroll() {
        var hbox = $('.js-sidebar-outer').height();
        var hself = $('.js-sidebar').height();
        var flag = true;
        $(window).scroll(function () {
            var st = $(window).scrollTop();
            if (st >= hbox) {
                if (flag) {
                    $('.js-sidebar').removeClass('pos-static').addClass('pos-fixed about-column').css('top', -hself + 80);
                    $('.js-sidebar').stop().animate({
                        top: '88px'
                    }, 800);
                    flag = false;
                }
            } else {
                flag = true;
                $('.js-sidebar').removeClass('pos-fixed about-column').addClass('pos-static').css('top', "0");
            }
        })
    }

    return {
        init: init
    };
})
;
