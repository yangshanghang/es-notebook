define(['person/note/model/addAndEditModel', 'common/util', 'layer1', 'ueditor', 'common/validateRules'], function (model, util, layer) {

    function init() {
        //页面初始化操作
        _pageInit();
        //百度编辑器
        _ueditor();
        //事件绑定
        _bind();
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
                    var ue = UE.getEditor('container');
                    var content = ue.getContent();
                    if (_formValidate()) {
                        if (!content) {
                            layer.alert("请输入笔记内容", {icon: 0});
                            return false;
                        }
                        if ($(".js-allType .cur").size() == 0) {
                            layer.alert("请选择笔记类别", {icon: 0});
                            return false;
                        }
                        var typeId = $(".cur[js-type='1']").attr('js-category-id');
                        _addOrEditNote(typeId);
                    }
                    else {
                        $('html,body').animate({scrollTop: 0}, 300);
                    }
                }
            }, {
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
                    top.location = window.__NOTEBOOK__.root_url + "person/note/list";
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
            }
        ])
    }

    /**
     * 描述：新增/修改笔记
     * @private
     */
    function _addOrEditNote(typeId) {
        model.addOrEditNote({
            data: $("#js-form").serialize() + "&typeId=" + typeId,
            beforeSend: function () {
                layer.load(2, {shade: [0.5, '#f5f5f5']});
            },
            callBack: function (data) {
                if(data.status == 200){
                    //提交按钮取消页面离开/重载事件
                    window.onbeforeunload = null;
                    if ($('input[name=visibleStatus]:checked').val() == 0) {
                        layer.confirm("保存成功，是否分享此笔记？",
                            {icon: 3, btn: ['是', '否'],
                            end: function () {
                                    top.location = window.__NOTEBOOK__.root_url + "person/note/list";
                                }
                            }, function () {
                                top.location = window.__NOTEBOOK__.root_url + "person/note/list";
                                window.open(window.__NOTEBOOK__.root_url + "client/note/detail/" + data.data);
                            }, function () {
                                top.location = window.__NOTEBOOK__.root_url + "person/note/list";
                            }
                        );
                    } else {
                        top.location = window.__NOTEBOOK__.root_url + "person/note/list";
                    }
                }else if(data.status == 100){
                    //提交按钮取消页面离开/重载事件
                    window.onbeforeunload = null;
                    layer.alert("该笔记在专栏内，无法修改可见状态及类目，请将它移出专栏后，再进行此操作！", {icon: 0 ,end: function () {
                            location.reload();
                        }
                    }, function () {
                        location.reload();
                    });
                }

            }, error: function (jqXHR) {
                layer.closeAll();
                layer.alert('操作失败，请重试');
            }

        });
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
     * 百度编辑器
     * @private
     */
    function _ueditor() {
        var ue = UE.getEditor('container', {
            // toolbars: [
            //     ['fullscreen', 'source', 'undo', 'redo', 'bold']
            // ],
            // autoHeightEnabled: true,
            // autoFloatEnabled: true,
            initialFrameHeight: 400,
            initialFrameWidth:'100%'
        });

        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function (action) {
            switch (action) {
                // 上传图片
                case 'uploadimage':
                    return window.__NOTEBOOK__.root_url + 'client/ueditor/uploadImage';
                // 上传远程抓图
                case 'catchimage':
                    return window.__NOTEBOOK__.root_url + 'client/ueditor/uploadCatchImage';
                // 上传涂鸦
                case 'uploadscrawl':
                    return window.__NOTEBOOK__.root_url + 'client/ueditor/uploadScrawl';
                case 'uploadattachment':
                    return window.__NOTEBOOK__.root_url + 'client/ueditor/uploadAttachment';
                // 默认
                default:
                    return this._bkGetActionUrl.call(this, action);
            }
        };

        //进入修改页面时，展示内容
        if ($("#js-indexId").val()) {
            ue.ready(function () {
                ue.setContent($("#content").val());
            });
        }
    }

    /**
     * 表单校验
     * @returns {*|jQuery}
     * @private
     */
    function _formValidate() {
        return $('#js-form').validate({
            rules: {
                title: {
                    isNotBlack: true
                },
                summary: {
                    isNotBlack: true,
                    maxlength: 400
                },
                typeId: {
                    required: true
                }
            },
            messages: {
                title: {
                    isNotBlack: '请输入标题'
                },
                summary: {
                    isNotBlack: '请输入摘要',
                    maxlength: '摘要位数不可大于400'
                },
                typeId: {
                    required: '请选择类型'
                }
            }
        }).form();
    }

    return {
        init: init
    };
});
