/**
 * dom操作
 */
define(['jquery'], function ($) {

    // html代码段集合
    var htmlMap = {
        'loading-large': '<div class="js-placeholder loading-large loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_48.gif" /><p>数据加载中，请稍后</p></div></div>',
        'empty-data': '<div class="js-placeholder empty-data loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/empty_data.png" /><p><span class="major">唔，未找到任何数据</span> 您还可以看看其它行情哦，或者稍后再试</p></div></div>',
        'loading-error': '<div class="js-placeholder loading-error loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_error.png" /><p>哎呀，似乎网络开小差了</p></div></div>',

        'wechatloading-large': '<div class="js-placeholder exception loading-large"><div class="vertical-center"><div class="preloader"></div><p>努力加载中...</p></div></div>',
        'wechatempty-data': '<div class="js-placeholder exception empty-data"><div class="nodata"><img src="../../img/nodata.png" width="46%" height="auto"/><p>唔，未找到任何数据！</p><span>您还可以看看其它行情哦，或者稍后再试。</span></div></div>',
        'wechatloading-error': '<div class="js-placeholder exception loading-error"><span class="icon-error"></span>加载失败，请稍后再试！</div>',
    };

    /**
     * 加载中占位符
     * @param ele string(selector)
     * @param type string
     */
    function showPlaceholder(ele, type) {
        var overlay = '<div class="overlay"></div>';
        $('body').append(overlay);
        var ele = $(ele);
        var html = htmlMap[type];
        var placeHolder = ele.find('.js-placeholder');
        if (placeHolder.length) {
            placeHolder.remove();
        }
        ele.prepend(html);
    }

    /**
     * 删除加载占位符
     * @param ele string(selector)
     */
    function removePlaceholder(ele) {
        var ele = $(ele);
        $('.overlay').remove();
        ele.find('.js-placeholder').remove();
    }

    /**
     * 生成下载form表单页面元素
     * info:{
         *      formId:xxx, 表单ID
         *      inputName:yyy, 隐藏域名称
         *      fields:[xxx,yyy,zzz]，下载内容
         *      selectList:[xxx,yyy,zzz]，选中项
         * }
     */
    function generateDldForm(info) {
        var form = '<form id="' + info.formId + '" class="hidden" method="post">',
            key = '',
            value = '';
        $.each(info.selectList, function (i, v) {
            for (var index = 0; index < info.fields.length; index++) {
                key = info.inputName + '[' + i + '].' + info.fields[index];
                value = $(this).data(info.fields[index].toLowerCase());
                if (typeof value == 'undefined') {
                    value = '';
                }
                form += '<input name="' + key + '" value="' + value + '" />';
            }
        });
        form += '</form>';
        return form;
    }


    return {
        showPlaceholder: showPlaceholder,
        removePlaceholder: removePlaceholder,
        generateDldForm: generateDldForm
    }
});