/**
 * 工具集
 */
define(['jquery'], function () {
    /**
     * 事件绑定
     * @param bindings [{el:x,event:y,handler:z}]
     *                  el为页面元素 event为绑定事件 handler为事件响应函数
     */
    function bindEvents(bindings) {
        $.each(bindings, function (i, v) {
            if (typeof v.el == 'string') {
                $(document).on(v.event, v.el, v.handler);
            } else {
                $(v.el).on(v.event, v.handler);
            }
        });
    }

    /**
     * 获取Url参数
     * @param url url地址
     */
    function getUrlParams(url) {
        // 需要返回的参数集合
        var rtnParams = {},
        // 参数键值对
            paramPair = [];
        if (!url || url.indexOf('?') === -1) {
            return rtnParams;
        }

        $.each(url.substr(url.indexOf('?') + 1).split('&'), function (i, v) {
            paramPair = v.split('=');
            rtnParams[paramPair[0]] = paramPair[1];
        });
        return rtnParams;
    }

    /**
     * 设置Token
     * @param token 令牌值
     */
    function setToken(token) {
        if (!token) {
            return;
        }

        $.cookie('exsToken', token, {
            path: '/', expires: 7
        });
    }

    /**
     * 获取token
     */
    function getToken() {
        return $.cookie('exsToken');
    }

    /**
     * 删除token
     */
    function removeToken() {
        $.cookie('exsToken', null, {path: '/'});
    }

    /**
     * 字符串base64编码
     * @param str 源字符串
     * @returns {string} 结果字符串
     */
    function base64Encode(str) {
        var c1, c2, c3,
            base64EncodeChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
            i = 0,
            len = str.length,
            string = '';
        while (i < len) {
            c1 = str.charCodeAt(i++) & 0xff;
            if (i == len) {
                string += base64EncodeChars.charAt(c1 >> 2);
                string += base64EncodeChars.charAt((c1 & 0x3) << 4);
                string += '==';
                break;
            }
            c2 = str.charCodeAt(i++);
            if (i == len) {
                string += base64EncodeChars.charAt(c1 >> 2);
                string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
                string += base64EncodeChars.charAt((c2 & 0xF) << 2);
                string += '=';
                break;
            }
            c3 = str.charCodeAt(i++);
            string += base64EncodeChars.charAt(c1 >> 2);
            string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            string += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
            string += base64EncodeChars.charAt(c3 & 0x3F)
        }
        return string;
    }

    /**
     * 去除空格
     * @param array
     * @returns {Array}
     */
    function trims(array) {
        var newArr = [],
            ele;
        $.each(array, function (i, v) {
            ele = v.replace(/ /g, '');
            ele != '' && newArr.push(ele);
        });
        return newArr;
    }


    /***
     * 检查正则类型
     * param
     * @type  'phone' or  'email' or''
     * @checkSource  '13456788888'
     * **/
    function checkRagular(type, data) {
        var rgx, ragular;
        switch (type) {
            //新用户注册 发送 手机号
            case 'phone' :
                rgx = '^[1][358][0-9]{9}$';
                break;
            case 'ifSpecial':
                rgx = '^[a-zA-Z0-9\u4e00-\u9fa5]+$';
                break;
            case 'email':
                rgx = '[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}';
                break;
            default :
                rgx = '';
                break;
        }
        ragular = new RegExp(rgx);
        return ragular.test(data);
    }

    /**
     *  时间间隔计算工具
     * @param strInterval
     * @param num
     * @returns {string}
     */
    function timeInterval(strInterval, num) {
        var date = arguments[2] || new Date();
        switch (strInterval) {
            case 's' :
                date = new Date(date.getTime() + (1000 * num));
                break;
            case 'n' :
                date = new Date(date.getTime() + (60000 * num));
                break;
            case 'h' :
                date = new Date(date.getTime() + (3600000 * num));
                break;
            case 'd' :
                date = new Date(date.getTime() + (86400000 * num));
                break;
            case 'w' :
                date = new Date(date.getTime() + ((86400000 * 7) * num));
                break;
            case 'm' :
                date = new Date(date.getFullYear(), (date.getMonth()) + num, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
                break;
            default:
                date = new Date((date.getFullYear() + num), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
        }
        date = date.getTime() >= (new Date()).getTime() ? new Date() : date;
        return [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('/');
    }

    /**
     * 时间格式化工具
     * @param date 时间
     * @param formatStr 格式
     * @returns {*}
     */
    function dateFormat(date,formatStr) {
        var str = formatStr;
        var Week = ['日', '一', '二', '三', '四', '五', '六'];

        str = str.replace(/yy/, date.getFullYear());
        str = str.replace(/y/, (date.getYear() % 100) > 9 ? (date.getYear() % 100).toString() : '0' + (date.getYear() % 100));

        str = str.replace(/mm/, date.getMonth() >= 9 ? (date.getMonth() + 1).toString() : '0' + (date.getMonth() + 1));
        str = str.replace(/m/g, date.getMonth() + 1);

        str = str.replace(/w|W/g, Week[date.getDay()]);

        str = str.replace(/dd/, date.getDate() > 9 ? date.getDate().toString() : '0' + date.getDate());
        str = str.replace(/d/g, date.getDate());

        str = str.replace(/hh/, date.getHours() > 9 ? date.getHours().toString() : '0' + date.getHours());
        str = str.replace(/h/g, date.getHours());
        str = str.replace(/mm/, date.getMinutes() > 9 ? date.getMinutes().toString() : '0' + date.getMinutes());
        str = str.replace(/m/g, date.getMinutes());

        str = str.replace(/ss/, date.getSeconds() > 9 ? date.getSeconds().toString() : '0' + date.getSeconds());
        str = str.replace(/s/g, date.getSeconds());

        return str;
    }

    return {
        // 事件绑定
        bindEvents: bindEvents,
        // 获取Url地址参数
        getUrlParams: getUrlParams,
        // 设置token
        setToken: setToken,
        // 获取token
        getToken: getToken,
        // 移除token
        removeToken: removeToken,
        // base64字符编码
        base64Encode: base64Encode,
        // 去除空格
        trims: trims,
        // 检查正则类型
        checkRagular: checkRagular,
        // 时间间隔计算工具
        timeInterval: timeInterval,
        // dateFormat
        dateFormat: dateFormat
    };
});
