define(['handlebars'], function (handlebars) {

    //注册判断器
    handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {
        switch (operator) {
            case '!=':
                return (v1 != v2) ? options.fn(this) : options.inverse(this);
                break;
            case '==':
                return (v1 == v2) ? options.fn(this) : options.inverse(this);
                break;
            case '===':
                return (v1 === v2) ? options.fn(this) : options.inverse(this);
                break;
            case '<':
                return (v1 < v2) ? options.fn(this) : options.inverse(this);
                break;
            case '<=':
                return (v1 <= v2) ? options.fn(this) : options.inverse(this);
                break;
            case '>':
                return (v1 > v2) ? options.fn(this) : options.inverse(this);
                break;
            case '>=':
                return (v1 >= v2) ? options.fn(this) : options.inverse(this);
                break;
            case '&&':
                return (v1 && v2) ? options.fn(this) : options.inverse(this);
                break;
            case '||':
                return (v1 || v2) ? options.fn(this) : options.inverse(this);
                break;
            case 'length':
                return v1.length === v2 ? options.fn(this) : options.inverse(this);
                break;
            default:
                return options.inverse(this);
                break;
        }
    });

    //注册计算器
    handlebars.registerHelper('math', function (v1, operator, v2) {
        if (operator == '+') {
            return v1 + v2;
        }
        if (operator == '-') {
            return v1 - v2;
        }
        if (operator == '*') {
            return v1 * v2;
        }
        if (operator == '/') {
            return v1 / v2;
        }
        if (operator == '%') {
            return v1 % v2;
        }
    });


    //表格序号索引+1
    handlebars.registerHelper('addOne', function (index) {
        //返回+1之后的结果
        return index + 1;
    });

    //时间：年月日时分秒
    handlebars.registerHelper('prettifyDate', function (timestamp) {
        return timestamp ? timestamp.replace("T", " ") : null;
    });

    // 字符串截取缩略图
    handlebars.registerHelper("substring", function (str) {
        if (str == null || str == "" || str.indexOf('&') < 0) {
            return str;
        }

        return str.substring(0, str.indexOf('&'));
    });

})

