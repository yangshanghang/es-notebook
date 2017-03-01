require.config({
    baseUrl: '/js',
    paths: {
        'jquery': 'lib/jquery-1.12.3.min',
        'bootstrap': 'lib/bootstrap/js/bootstrap.min',
        'handlebars': 'lib/handlebars-1.0.0',
        'layer1': 'lib/layer/layer',
        'laydate': 'lib/laydate/laydate',
        'pagination': 'lib/pagination/jquery.pagination',
        'html5shiv.min': 'lib/bootstrap/js/html5shiv.min',
        'respond.min': 'lib/bootstrap/js/respond.min',
        'jquery.validate': 'lib/validate/jquery.validate.min',
        'jquery.placeholder': 'lib/jquery.placeholder',
        "ueditor.config":"lib/ueditor/ueditor.config",
        "ueditor":"lib/ueditor/ueditor.all.min",
        'zeroClipboard':"lib/ueditor/third-party/zeroclipboard/ZeroClipboard",
        'jquery.ui': 'lib/jquery-ui',
        'jquery.serialize': 'lib/jquery.serialize-object.min'
    },
    shim: {
        'bootstrap': {
            deps: ['jquery','html5shiv.min','respond.min','lib/css!lib/bootstrap/css/bootstrap.min.css']
        },
        'layer1': {
            deps: ['jquery']
        },
        'pagination': {
            deps: ['jquery','lib/css!lib/pagination/pagination.css']
        },
        'laydate': {
            deps: ['jquery']
        },
        'ueditor':{
            deps:['jquery','ueditor.config']
        }
    }
});
