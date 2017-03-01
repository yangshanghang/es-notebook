/*! jQuery Validation Plugin - v1.15.1 - 7/22/2016
 * http://jqueryvalidation.org/
 * Copyright (c) 2016 Jörn Zaefferer; Licensed MIT */

define("common/util",["jquery"],function(){function e(e){$.each(e,function(e,t){"string"==typeof t.el?$(document).on(t.event,t.el,t.handler):$(t.el).on(t.event,t.handler)})}function t(e){var t={},i=[];return e&&-1!==e.indexOf("?")?($.each(e.substr(e.indexOf("?")+1).split("&"),function(e,r){i=r.split("="),t[i[0]]=i[1]}),t):t}function i(e){e&&$.cookie("exsToken",e,{path:"/",expires:7})}function r(){return $.cookie("exsToken")}function n(){$.cookie("exsToken",null,{path:"/"})}function s(e){for(var t,i,r,n="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",s=0,a=e.length,o="";a>s;){if(t=255&e.charCodeAt(s++),s==a){o+=n.charAt(t>>2),o+=n.charAt((3&t)<<4),o+="==";break}if(i=e.charCodeAt(s++),s==a){o+=n.charAt(t>>2),o+=n.charAt((3&t)<<4|(240&i)>>4),o+=n.charAt((15&i)<<2),o+="=";break}r=e.charCodeAt(s++),o+=n.charAt(t>>2),o+=n.charAt((3&t)<<4|(240&i)>>4),o+=n.charAt((15&i)<<2|(192&r)>>6),o+=n.charAt(63&r)}return o}function a(e){var t,i=[];return $.each(e,function(e,r){t=r.replace(/ /g,""),""!=t&&i.push(t)}),i}function o(e,t){var i,r;switch(e){case"phone":i="^[1][358][0-9]{9}$";break;case"ifSpecial":i="^[a-zA-Z0-9一-龥]+$";break;case"email":i="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";break;default:i=""}return r=new RegExp(i),r.test(t)}function l(e,t){var i=arguments[2]||new Date;switch(e){case"s":i=new Date(i.getTime()+1e3*t);break;case"n":i=new Date(i.getTime()+6e4*t);break;case"h":i=new Date(i.getTime()+36e5*t);break;case"d":i=new Date(i.getTime()+864e5*t);break;case"w":i=new Date(i.getTime()+6048e5*t);break;case"m":i=new Date(i.getFullYear(),i.getMonth()+t,i.getDate(),i.getHours(),i.getMinutes(),i.getSeconds());break;default:i=new Date(i.getFullYear()+t,i.getMonth(),i.getDate(),i.getHours(),i.getMinutes(),i.getSeconds())}return i=i.getTime()>=(new Date).getTime()?new Date:i,[i.getFullYear(),i.getMonth()+1,i.getDate()].join("/")}function d(e,t){var i=t,r=["日","一","二","三","四","五","六"];return i=i.replace(/yy/,e.getFullYear()),i=i.replace(/y/,e.getYear()%100>9?(e.getYear()%100).toString():"0"+e.getYear()%100),i=i.replace(/mm/,e.getMonth()>=9?(e.getMonth()+1).toString():"0"+(e.getMonth()+1)),i=i.replace(/m/g,e.getMonth()+1),i=i.replace(/w|W/g,r[e.getDay()]),i=i.replace(/dd/,e.getDate()>9?e.getDate().toString():"0"+e.getDate()),i=i.replace(/d/g,e.getDate()),i=i.replace(/hh/,e.getHours()>9?e.getHours().toString():"0"+e.getHours()),i=i.replace(/h/g,e.getHours()),i=i.replace(/mm/,e.getMinutes()>9?e.getMinutes().toString():"0"+e.getMinutes()),i=i.replace(/m/g,e.getMinutes()),i=i.replace(/ss/,e.getSeconds()>9?e.getSeconds().toString():"0"+e.getSeconds()),i=i.replace(/s/g,e.getSeconds())}return{bindEvents:e,getUrlParams:t,setToken:i,getToken:r,removeToken:n,base64Encode:s,trims:a,checkRagular:o,timeInterval:l,dateFormat:d}}),define("common/dom",["jquery"],function(e){function t(t,i){var r='<div class="overlay"></div>';e("body").append(r);var t=e(t),s=n[i],a=t.find(".js-placeholder");a.length&&a.remove(),t.prepend(s)}function i(t){var t=e(t);e(".overlay").remove(),t.find(".js-placeholder").remove()}function r(t){var i='<form id="'+t.formId+'" class="hidden" method="post">',r="",n="";return e.each(t.selectList,function(s,a){for(var o=0;o<t.fields.length;o++)r=t.inputName+"["+s+"]."+t.fields[o],n=e(this).data(t.fields[o].toLowerCase()),"undefined"==typeof n&&(n=""),i+='<input name="'+r+'" value="'+n+'" />'}),i+="</form>"}var n={"loading-large":'<div class="js-placeholder loading-large loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_48.gif" /><p>数据加载中，请稍后</p></div></div>',"empty-data":'<div class="js-placeholder empty-data loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/empty_data.png" /><p><span class="major">唔，未找到任何数据</span> 您还可以看看其它行情哦，或者稍后再试</p></div></div>',"loading-error":'<div class="js-placeholder loading-error loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_error.png" /><p>哎呀，似乎网络开小差了</p></div></div>',"wechatloading-large":'<div class="js-placeholder exception loading-large"><div class="vertical-center"><div class="preloader"></div><p>努力加载中...</p></div></div>',"wechatempty-data":'<div class="js-placeholder exception empty-data"><div class="nodata"><img src="../../img/nodata.png" width="46%" height="auto"/><p>唔，未找到任何数据！</p><span>您还可以看看其它行情哦，或者稍后再试。</span></div></div>',"wechatloading-error":'<div class="js-placeholder exception loading-error"><span class="icon-error"></span>加载失败，请稍后再试！</div>'};return{showPlaceholder:t,removePlaceholder:i,generateDldForm:r}}),define("common/http",["jquery","layer1","common/util","common/dom"],function(e,t,i,r){function n(i){var n=e.extend({},o,i);return n.actionConfig&&(n.beforeSend=function(){r.showPlaceholder(n.actionConfig.dom,n.actionConfig.type)}),n.url=s(i.url),n.success=function(e){return n.actionConfig&&r.removePlaceholder(n.actionConfig.dom),n.actionConfig&&"200"!=e.status?void r.showPlaceholder(n.actionConfig.dom,n.actionConfig.type):void setTimeout(function(){i.success&&i.success(e)},400)},n.error=function(e,s,a){return 401===e.status?(t.open({content:"您尚未登录或登录时间过长,请重新登录!",icon:3,title:"登录提示",yes:function(){window.location.href=window.__NOTEBOOK__.root_url},end:function(){t.closeAll()}}),!0):(n.actionErrorConfig&&r.showPlaceholder(n.actionErrorConfig.dom,n.actionErrorConfig.type),void(i.error&&i.error(e,s,a)))},n.serializable&&(n.contentType="application/json",n.data=JSON.stringify(n.data)),n}function s(t){var i={_t:(new Date).getTime()},r=/\?/gi.test(t)?"&":"?";return t+r+e.param(i)}function a(t){var i=e.ajax(n(t));return i}t.config({path:"/js/lib/layer/"});var o={async:!0,dataType:"json",type:"get",serializable:!1};return{httpRequest:a}}),define("client/login/model/retrievePassModel",["common/http"],function(e){function t(t){e.httpRequest({url:window.__NOTEBOOK__.root_url+"client/sendemail",beforeSend:t.beforeSend,data:t.data,dataType:"json",beforeSend:function(){t.beforeSend()},success:function(e){t.callBack(e)},error:t.error})}return{userRetrievePass:t}}),!function(e){"function"==typeof define&&define.amd?define("jquery.validate",["jquery"],e):"object"==typeof module&&module.exports?module.exports=e(require("jquery")):e(jQuery)}(function(e){e.extend(e.fn,{validate:function(t){if(!this.length)return void(t&&t.debug&&window.console&&console.warn("Nothing selected, can't validate, returning nothing."));var i=e.data(this[0],"validator");return i?i:(e(this).attr("novalidate","novalidate"),i=new e.validator(t,this[0]),e.data(this[0],"validator",i),i.settings.onsubmit&&(this.on("click.validate",":submit",function(t){if(i.settings.submitHandler&&(i.submitButton=t.target),e(this).hasClass("cancel")&&(i.cancelSubmit=!0),void 0!==e(this).attr("formnovalidate")){var r=document,n=r.documentMode;i.cancelSubmit=r.all&&n&&8===n&&null===t.target.attributes.formnovalidate?void 0:!0}}),this.on("submit.validate",function(t){function r(){var r,n;return i.settings.submitHandler?(i.submitButton&&(r=e("<input type='hidden'/>").attr("name",i.submitButton.name).val(e(i.submitButton).val()).appendTo(i.currentForm)),n=i.settings.submitHandler.call(i,i.currentForm,t),i.submitButton&&r.remove(),void 0!==n?n:!1):!0}return i.settings.debug&&t.preventDefault(),i.cancelSubmit?(i.cancelSubmit=!1,r()):i.form()?i.pendingRequest?(i.formSubmitted=!0,!1):r():(i.focusInvalid(),!1)})),i)},valid:function(){var t,i,r;return e(this[0]).is("form")?t=this.validate().form():(r=[],t=!0,i=e(this[0].form).validate(),this.each(function(){t=i.element(this)&&t,t||(r=r.concat(i.errorList))}),i.errorList=r),t},rules:function(t,i){var r,n,s,a,o,l,d=this[0];if(null!=d&&null!=d.form){if(t)switch(r=e.data(d.form,"validator").settings,n=r.rules,s=e.validator.staticRules(d),t){case"add":e.extend(s,e.validator.normalizeRule(i)),delete s.messages,n[d.name]=s,i.messages&&(r.messages[d.name]=e.extend(r.messages[d.name],i.messages));break;case"remove":return i?(l={},e.each(i.split(/\s/),function(t,i){l[i]=s[i],delete s[i],"required"===i&&e(d).removeAttr("aria-required")}),l):(delete n[d.name],s)}return a=e.validator.normalizeRules(e.extend({},e.validator.classRules(d),e.validator.attributeRules(d),e.validator.dataRules(d),e.validator.staticRules(d)),d),a.required&&(o=a.required,delete a.required,a=e.extend({required:o},a),e(d).attr("aria-required","true")),a.remote&&(o=a.remote,delete a.remote,a=e.extend(a,{remote:o})),a}}}),e.extend(e.expr[":"],{blank:function(t){return!e.trim(""+e(t).val())},filled:function(t){var i=e(t).val();return null!==i&&!!e.trim(""+i)},unchecked:function(t){return!e(t).prop("checked")}}),e.validator=function(t,i){this.settings=e.extend(!0,{},e.validator.defaults,t),this.currentForm=i,this.init()},e.validator.format=function(t,i){return 1===arguments.length?function(){var i=e.makeArray(arguments);return i.unshift(t),e.validator.format.apply(this,i)}:void 0===i?t:(arguments.length>2&&i.constructor!==Array&&(i=e.makeArray(arguments).slice(1)),i.constructor!==Array&&(i=[i]),e.each(i,function(e,i){t=t.replace(new RegExp("\\{"+e+"\\}","g"),function(){return i})}),t)},e.extend(e.validator,{defaults:{messages:{},groups:{},rules:{},errorClass:"error",pendingClass:"pending",validClass:"valid",errorElement:"label",focusCleanup:!1,focusInvalid:!0,errorContainer:e([]),errorLabelContainer:e([]),onsubmit:!0,ignore:":hidden",ignoreTitle:!1,onfocusin:function(e){this.lastActive=e,this.settings.focusCleanup&&(this.settings.unhighlight&&this.settings.unhighlight.call(this,e,this.settings.errorClass,this.settings.validClass),this.hideThese(this.errorsFor(e)))},onfocusout:function(e){this.checkable(e)||!(e.name in this.submitted)&&this.optional(e)||this.element(e)},onkeyup:function(t,i){var r=[16,17,18,20,35,36,37,38,39,40,45,144,225];9===i.which&&""===this.elementValue(t)||-1!==e.inArray(i.keyCode,r)||(t.name in this.submitted||t.name in this.invalid)&&this.element(t)},onclick:function(e){e.name in this.submitted?this.element(e):e.parentNode.name in this.submitted&&this.element(e.parentNode)},highlight:function(t,i,r){"radio"===t.type?this.findByName(t.name).addClass(i).removeClass(r):e(t).addClass(i).removeClass(r)},unhighlight:function(t,i,r){"radio"===t.type?this.findByName(t.name).removeClass(i).addClass(r):e(t).removeClass(i).addClass(r)}},setDefaults:function(t){e.extend(e.validator.defaults,t)},messages:{required:"This field is required.",remote:"Please fix this field.",email:"Please enter a valid email address.",url:"Please enter a valid URL.",date:"Please enter a valid date.",dateISO:"Please enter a valid date (ISO).",number:"Please enter a valid number.",digits:"Please enter only digits.",equalTo:"Please enter the same value again.",maxlength:e.validator.format("Please enter no more than {0} characters."),minlength:e.validator.format("Please enter at least {0} characters."),rangelength:e.validator.format("Please enter a value between {0} and {1} characters long."),range:e.validator.format("Please enter a value between {0} and {1}."),max:e.validator.format("Please enter a value less than or equal to {0}."),min:e.validator.format("Please enter a value greater than or equal to {0}."),step:e.validator.format("Please enter a multiple of {0}.")},autoCreateRanges:!1,prototype:{init:function(){function t(t){this.form||"undefined"==typeof e(this).attr("contenteditable")||(this.form=e(this).closest("form")[0]);var i=e.data(this.form,"validator"),r="on"+t.type.replace(/^validate/,""),n=i.settings;n[r]&&!e(this).is(n.ignore)&&n[r].call(i,this,t)}this.labelContainer=e(this.settings.errorLabelContainer),this.errorContext=this.labelContainer.length&&this.labelContainer||e(this.currentForm),this.containers=e(this.settings.errorContainer).add(this.settings.errorLabelContainer),this.submitted={},this.valueCache={},this.pendingRequest=0,this.pending={},this.invalid={},this.reset();var i,r=this.groups={};e.each(this.settings.groups,function(t,i){"string"==typeof i&&(i=i.split(/\s/)),e.each(i,function(e,i){r[i]=t})}),i=this.settings.rules,e.each(i,function(t,r){i[t]=e.validator.normalizeRule(r)}),e(this.currentForm).on("focusin.validate focusout.validate keyup.validate",":text, [type='password'], [type='file'], select, textarea, [type='number'], [type='search'], [type='tel'], [type='url'], [type='email'], [type='datetime'], [type='date'], [type='month'], [type='week'], [type='time'], [type='datetime-local'], [type='range'], [type='color'], [type='radio'], [type='checkbox'], [contenteditable]",t).on("click.validate","select, option, [type='radio'], [type='checkbox']",t),this.settings.invalidHandler&&e(this.currentForm).on("invalid-form.validate",this.settings.invalidHandler),e(this.currentForm).find("[required], [data-rule-required], .required").attr("aria-required","true")},form:function(){return this.checkForm(),e.extend(this.submitted,this.errorMap),this.invalid=e.extend({},this.errorMap),this.valid()||e(this.currentForm).triggerHandler("invalid-form",[this]),this.showErrors(),this.valid()},checkForm:function(){this.prepareForm();for(var e=0,t=this.currentElements=this.elements();t[e];e++)this.check(t[e]);return this.valid()},element:function(t){var i,r,n=this.clean(t),s=this.validationTargetFor(n),a=this,o=!0;return void 0===s?delete this.invalid[n.name]:(this.prepareElement(s),this.currentElements=e(s),r=this.groups[s.name],r&&e.each(this.groups,function(e,t){t===r&&e!==s.name&&(n=a.validationTargetFor(a.clean(a.findByName(e))),n&&n.name in a.invalid&&(a.currentElements.push(n),o=a.check(n)&&o))}),i=this.check(s)!==!1,o=o&&i,this.invalid[s.name]=i?!1:!0,this.numberOfInvalids()||(this.toHide=this.toHide.add(this.containers)),this.showErrors(),e(t).attr("aria-invalid",!i)),o},showErrors:function(t){if(t){var i=this;e.extend(this.errorMap,t),this.errorList=e.map(this.errorMap,function(e,t){return{message:e,element:i.findByName(t)[0]}}),this.successList=e.grep(this.successList,function(e){return!(e.name in t)})}this.settings.showErrors?this.settings.showErrors.call(this,this.errorMap,this.errorList):this.defaultShowErrors()},resetForm:function(){e.fn.resetForm&&e(this.currentForm).resetForm(),this.invalid={},this.submitted={},this.prepareForm(),this.hideErrors();var t=this.elements().removeData("previousValue").removeAttr("aria-invalid");this.resetElements(t)},resetElements:function(e){var t;if(this.settings.unhighlight)for(t=0;e[t];t++)this.settings.unhighlight.call(this,e[t],this.settings.errorClass,""),this.findByName(e[t].name).removeClass(this.settings.validClass);else e.removeClass(this.settings.errorClass).removeClass(this.settings.validClass)},numberOfInvalids:function(){return this.objectLength(this.invalid)},objectLength:function(e){var t,i=0;for(t in e)e[t]&&i++;return i},hideErrors:function(){this.hideThese(this.toHide)},hideThese:function(e){e.not(this.containers).text(""),this.addWrapper(e).hide()},valid:function(){return 0===this.size()},size:function(){return this.errorList.length},focusInvalid:function(){if(this.settings.focusInvalid)try{e(this.findLastActive()||this.errorList.length&&this.errorList[0].element||[]).filter(":visible").focus().trigger("focusin")}catch(t){}},findLastActive:function(){var t=this.lastActive;return t&&1===e.grep(this.errorList,function(e){return e.element.name===t.name}).length&&t},elements:function(){var t=this,i={};return e(this.currentForm).find("input, select, textarea, [contenteditable]").not(":submit, :reset, :image, :disabled").not(this.settings.ignore).filter(function(){var r=this.name||e(this).attr("name");return!r&&t.settings.debug&&window.console&&console.error("%o has no name assigned",this),"undefined"!=typeof e(this).attr("contenteditable")&&(this.form=e(this).closest("form")[0]),r in i||!t.objectLength(e(this).rules())?!1:(i[r]=!0,!0)})},clean:function(t){return e(t)[0]},errors:function(){var t=this.settings.errorClass.split(" ").join(".");return e(this.settings.errorElement+"."+t,this.errorContext)},resetInternals:function(){this.successList=[],this.errorList=[],this.errorMap={},this.toShow=e([]),this.toHide=e([])},reset:function(){this.resetInternals(),this.currentElements=e([])},prepareForm:function(){this.reset(),this.toHide=this.errors().add(this.containers)},prepareElement:function(e){this.reset(),this.toHide=this.errorsFor(e)},elementValue:function(t){var i,r,n=e(t),s=t.type;return"radio"===s||"checkbox"===s?this.findByName(t.name).filter(":checked").val():"number"===s&&"undefined"!=typeof t.validity?t.validity.badInput?"NaN":n.val():(i="undefined"!=typeof e(t).attr("contenteditable")?n.text():n.val(),"file"===s?"C:\\fakepath\\"===i.substr(0,12)?i.substr(12):(r=i.lastIndexOf("/"),r>=0?i.substr(r+1):(r=i.lastIndexOf("\\"),r>=0?i.substr(r+1):i)):"string"==typeof i?i.replace(/\r/g,""):i)},check:function(t){t=this.validationTargetFor(this.clean(t));var i,r,n,s=e(t).rules(),a=e.map(s,function(e,t){return t}).length,o=!1,l=this.elementValue(t);if("function"==typeof s.normalizer){if(l=s.normalizer.call(t,l),"string"!=typeof l)throw new TypeError("The normalizer should return a string value.");delete s.normalizer}for(r in s){n={method:r,parameters:s[r]};try{if(i=e.validator.methods[r].call(this,l,t,n.parameters),"dependency-mismatch"===i&&1===a){o=!0;continue}if(o=!1,"pending"===i)return void(this.toHide=this.toHide.not(this.errorsFor(t)));if(!i)return this.formatAndAdd(t,n),!1}catch(d){throw this.settings.debug&&window.console&&console.log("Exception occurred when checking element "+t.id+", check the '"+n.method+"' method.",d),d instanceof TypeError&&(d.message+=".  Exception occurred when checking element "+t.id+", check the '"+n.method+"' method."),d}}return o?void 0:(this.objectLength(s)&&this.successList.push(t),!0)},customDataMessage:function(t,i){return e(t).data("msg"+i.charAt(0).toUpperCase()+i.substring(1).toLowerCase())||e(t).data("msg")},customMessage:function(e,t){var i=this.settings.messages[e];return i&&(i.constructor===String?i:i[t])},findDefined:function(){for(var e=0;e<arguments.length;e++)if(void 0!==arguments[e])return arguments[e]},defaultMessage:function(t,i){"string"==typeof i&&(i={method:i});var r=this.findDefined(this.customMessage(t.name,i.method),this.customDataMessage(t,i.method),!this.settings.ignoreTitle&&t.title||void 0,e.validator.messages[i.method],"<strong>Warning: No message defined for "+t.name+"</strong>"),n=/\$?\{(\d+)\}/g;return"function"==typeof r?r=r.call(this,i.parameters,t):n.test(r)&&(r=e.validator.format(r.replace(n,"{$1}"),i.parameters)),r},formatAndAdd:function(e,t){var i=this.defaultMessage(e,t);this.errorList.push({message:i,element:e,method:t.method}),this.errorMap[e.name]=i,this.submitted[e.name]=i},addWrapper:function(e){return this.settings.wrapper&&(e=e.add(e.parent(this.settings.wrapper))),e},defaultShowErrors:function(){var e,t,i;for(e=0;this.errorList[e];e++)i=this.errorList[e],this.settings.highlight&&this.settings.highlight.call(this,i.element,this.settings.errorClass,this.settings.validClass),this.showLabel(i.element,i.message);if(this.errorList.length&&(this.toShow=this.toShow.add(this.containers)),this.settings.success)for(e=0;this.successList[e];e++)this.showLabel(this.successList[e]);if(this.settings.unhighlight)for(e=0,t=this.validElements();t[e];e++)this.settings.unhighlight.call(this,t[e],this.settings.errorClass,this.settings.validClass);this.toHide=this.toHide.not(this.toShow),this.hideErrors(),this.addWrapper(this.toShow).show()},validElements:function(){return this.currentElements.not(this.invalidElements())},invalidElements:function(){return e(this.errorList).map(function(){return this.element})},showLabel:function(t,i){var r,n,s,a,o=this.errorsFor(t),l=this.idOrName(t),d=e(t).attr("aria-describedby");o.length?(o.removeClass(this.settings.validClass).addClass(this.settings.errorClass),o.html(i)):(o=e("<"+this.settings.errorElement+">").attr("id",l+"-error").addClass(this.settings.errorClass).html(i||""),r=o,this.settings.wrapper&&(r=o.hide().show().wrap("<"+this.settings.wrapper+"/>").parent()),this.labelContainer.length?this.labelContainer.append(r):this.settings.errorPlacement?this.settings.errorPlacement.call(this,r,e(t)):r.insertAfter(t),o.is("label")?o.attr("for",l):0===o.parents("label[for='"+this.escapeCssMeta(l)+"']").length&&(s=o.attr("id"),d?d.match(new RegExp("\\b"+this.escapeCssMeta(s)+"\\b"))||(d+=" "+s):d=s,e(t).attr("aria-describedby",d),n=this.groups[t.name],n&&(a=this,e.each(a.groups,function(t,i){i===n&&e("[name='"+a.escapeCssMeta(t)+"']",a.currentForm).attr("aria-describedby",o.attr("id"))})))),!i&&this.settings.success&&(o.text(""),"string"==typeof this.settings.success?o.addClass(this.settings.success):this.settings.success(o,t)),this.toShow=this.toShow.add(o)},errorsFor:function(t){var i=this.escapeCssMeta(this.idOrName(t)),r=e(t).attr("aria-describedby"),n="label[for='"+i+"'], label[for='"+i+"'] *";return r&&(n=n+", #"+this.escapeCssMeta(r).replace(/\s+/g,", #")),this.errors().filter(n)},escapeCssMeta:function(e){return e.replace(/([\\!"#$%&'()*+,.\/:;<=>?@\[\]^`{|}~])/g,"\\$1")},idOrName:function(e){return this.groups[e.name]||(this.checkable(e)?e.name:e.id||e.name)},validationTargetFor:function(t){return this.checkable(t)&&(t=this.findByName(t.name)),e(t).not(this.settings.ignore)[0]},checkable:function(e){return/radio|checkbox/i.test(e.type)},findByName:function(t){return e(this.currentForm).find("[name='"+this.escapeCssMeta(t)+"']")},getLength:function(t,i){switch(i.nodeName.toLowerCase()){case"select":return e("option:selected",i).length;case"input":if(this.checkable(i))return this.findByName(i.name).filter(":checked").length}return t.length},depend:function(e,t){return this.dependTypes[typeof e]?this.dependTypes[typeof e](e,t):!0},dependTypes:{"boolean":function(e){return e},string:function(t,i){return!!e(t,i.form).length},"function":function(e,t){return e(t)}},optional:function(t){var i=this.elementValue(t);return!e.validator.methods.required.call(this,i,t)&&"dependency-mismatch"},startRequest:function(t){this.pending[t.name]||(this.pendingRequest++,e(t).addClass(this.settings.pendingClass),this.pending[t.name]=!0)},stopRequest:function(t,i){this.pendingRequest--,this.pendingRequest<0&&(this.pendingRequest=0),delete this.pending[t.name],e(t).removeClass(this.settings.pendingClass),i&&0===this.pendingRequest&&this.formSubmitted&&this.form()?(e(this.currentForm).submit(),this.formSubmitted=!1):!i&&0===this.pendingRequest&&this.formSubmitted&&(e(this.currentForm).triggerHandler("invalid-form",[this]),this.formSubmitted=!1)},previousValue:function(t,i){return i="string"==typeof i&&i||"remote",e.data(t,"previousValue")||e.data(t,"previousValue",{old:null,valid:!0,message:this.defaultMessage(t,{method:i})})},destroy:function(){this.resetForm(),e(this.currentForm).off(".validate").removeData("validator").find(".validate-equalTo-blur").off(".validate-equalTo").removeClass("validate-equalTo-blur")}},classRuleSettings:{required:{required:!0},email:{email:!0},url:{url:!0},date:{date:!0},dateISO:{dateISO:!0},number:{number:!0},digits:{digits:!0},creditcard:{creditcard:!0}},addClassRules:function(t,i){t.constructor===String?this.classRuleSettings[t]=i:e.extend(this.classRuleSettings,t)},classRules:function(t){var i={},r=e(t).attr("class");return r&&e.each(r.split(" "),function(){this in e.validator.classRuleSettings&&e.extend(i,e.validator.classRuleSettings[this])}),i},normalizeAttributeRule:function(e,t,i,r){/min|max|step/.test(i)&&(null===t||/number|range|text/.test(t))&&(r=Number(r),isNaN(r)&&(r=void 0)),r||0===r?e[i]=r:t===i&&"range"!==t&&(e[i]=!0)},attributeRules:function(t){var i,r,n={},s=e(t),a=t.getAttribute("type");for(i in e.validator.methods)"required"===i?(r=t.getAttribute(i),""===r&&(r=!0),r=!!r):r=s.attr(i),this.normalizeAttributeRule(n,a,i,r);return n.maxlength&&/-1|2147483647|524288/.test(n.maxlength)&&delete n.maxlength,n},dataRules:function(t){var i,r,n={},s=e(t),a=t.getAttribute("type");for(i in e.validator.methods)r=s.data("rule"+i.charAt(0).toUpperCase()+i.substring(1).toLowerCase()),this.normalizeAttributeRule(n,a,i,r);return n},staticRules:function(t){var i={},r=e.data(t.form,"validator");return r.settings.rules&&(i=e.validator.normalizeRule(r.settings.rules[t.name])||{}),i},normalizeRules:function(t,i){return e.each(t,function(r,n){if(n===!1)return void delete t[r];if(n.param||n.depends){var s=!0;switch(typeof n.depends){case"string":s=!!e(n.depends,i.form).length;break;case"function":s=n.depends.call(i,i)}s?t[r]=void 0!==n.param?n.param:!0:(e.data(i.form,"validator").resetElements(e(i)),delete t[r])}}),e.each(t,function(r,n){t[r]=e.isFunction(n)&&"normalizer"!==r?n(i):n}),e.each(["minlength","maxlength"],function(){t[this]&&(t[this]=Number(t[this]))}),e.each(["rangelength","range"],function(){var i;t[this]&&(e.isArray(t[this])?t[this]=[Number(t[this][0]),Number(t[this][1])]:"string"==typeof t[this]&&(i=t[this].replace(/[\[\]]/g,"").split(/[\s,]+/),t[this]=[Number(i[0]),Number(i[1])]))}),e.validator.autoCreateRanges&&(null!=t.min&&null!=t.max&&(t.range=[t.min,t.max],delete t.min,delete t.max),null!=t.minlength&&null!=t.maxlength&&(t.rangelength=[t.minlength,t.maxlength],delete t.minlength,delete t.maxlength)),t},normalizeRule:function(t){if("string"==typeof t){var i={};e.each(t.split(/\s/),function(){i[this]=!0}),t=i}return t},addMethod:function(t,i,r){e.validator.methods[t]=i,e.validator.messages[t]=void 0!==r?r:e.validator.messages[t],i.length<3&&e.validator.addClassRules(t,e.validator.normalizeRule(t))},methods:{required:function(t,i,r){if(!this.depend(r,i))return"dependency-mismatch";if("select"===i.nodeName.toLowerCase()){var n=e(i).val();return n&&n.length>0}return this.checkable(i)?this.getLength(t,i)>0:t.length>0},email:function(e,t){return this.optional(t)||/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test(e)},url:function(e,t){return this.optional(t)||/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[\/?#]\S*)?$/i.test(e)},date:function(e,t){return this.optional(t)||!/Invalid|NaN/.test(new Date(e).toString())},dateISO:function(e,t){return this.optional(t)||/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test(e)},number:function(e,t){return this.optional(t)||/^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(e)},digits:function(e,t){return this.optional(t)||/^\d+$/.test(e)},minlength:function(t,i,r){var n=e.isArray(t)?t.length:this.getLength(t,i);return this.optional(i)||n>=r},maxlength:function(t,i,r){var n=e.isArray(t)?t.length:this.getLength(t,i);return this.optional(i)||r>=n},rangelength:function(t,i,r){var n=e.isArray(t)?t.length:this.getLength(t,i);return this.optional(i)||n>=r[0]&&n<=r[1]},min:function(e,t,i){return this.optional(t)||e>=i},max:function(e,t,i){return this.optional(t)||i>=e},range:function(e,t,i){return this.optional(t)||e>=i[0]&&e<=i[1]},step:function(t,i,r){var n,s=e(i).attr("type"),a="Step attribute on input type "+s+" is not supported.",o=["text","number","range"],l=new RegExp("\\b"+s+"\\b"),d=s&&!l.test(o.join()),u=function(e){var t=(""+e).match(/(?:\.(\d+))?$/);return t&&t[1]?t[1].length:0},h=function(e){return Math.round(e*Math.pow(10,n))},c=!0;if(d)throw new Error(a);return n=u(r),(u(t)>n||0!==h(t)%h(r))&&(c=!1),this.optional(i)||c},equalTo:function(t,i,r){var n=e(r);return this.settings.onfocusout&&n.not(".validate-equalTo-blur").length&&n.addClass("validate-equalTo-blur").on("blur.validate-equalTo",function(){e(i).valid()}),t===n.val()},remote:function(t,i,r,n){if(this.optional(i))return"dependency-mismatch";n="string"==typeof n&&n||"remote";var s,a,o,l=this.previousValue(i,n);return this.settings.messages[i.name]||(this.settings.messages[i.name]={}),l.originalMessage=l.originalMessage||this.settings.messages[i.name][n],this.settings.messages[i.name][n]=l.message,r="string"==typeof r&&{url:r}||r,o=e.param(e.extend({data:t},r.data)),l.old===o?l.valid:(l.old=o,s=this,this.startRequest(i),a={},a[i.name]=t,e.ajax(e.extend(!0,{mode:"abort",port:"validate"+i.name,dataType:"json",data:a,context:s.currentForm,success:function(e){var r,a,o,d=e===!0||"true"===e;s.settings.messages[i.name][n]=l.originalMessage,d?(o=s.formSubmitted,s.resetInternals(),s.toHide=s.errorsFor(i),s.formSubmitted=o,s.successList.push(i),s.invalid[i.name]=!1,s.showErrors()):(r={},a=e||s.defaultMessage(i,{method:n,parameters:t}),r[i.name]=l.message=a,s.invalid[i.name]=!0,s.showErrors(r)),l.valid=d,s.stopRequest(i,d)}},r)),"pending")}}});var t,i={};e.ajaxPrefilter?e.ajaxPrefilter(function(e,t,r){var n=e.port;"abort"===e.mode&&(i[n]&&i[n].abort(),i[n]=r)}):(t=e.ajax,e.ajax=function(r){var n=("mode"in r?r:e.ajaxSettings).mode,s=("port"in r?r:e.ajaxSettings).port;return"abort"===n?(i[s]&&i[s].abort(),i[s]=t.apply(this,arguments),i[s]):t.apply(this,arguments)})}),define("common/validateRules",["jquery","jquery.validate"],function(e){e.validator.addMethod("idCard",function(e,t){var i=function(e){var t,i,r,n,s,a={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},o=e.split("");if(null==a[parseInt(e.substr(0,2))])return{result:!1,e:"身份证号码不正确!"};switch(e.length){case 15:return s=(parseInt(e.substr(6,2))+1900)%4==0||(parseInt(e.substr(6,2))+1900)%100==0&&(parseInt(e.substr(6,2))+1900)%4==0?/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/:/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/,s.test(e)?{result:!0}:{result:!1,e:"身份证号码出生日期超出范围或含有非法字符!"};case 18:return s=parseInt(e.substr(6,4))%4==0||parseInt(e.substr(6,4))%100==0&&parseInt(e.substr(6,4))%4==0?/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/:/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/,s.test(e)?(r=7*(parseInt(o[0])+parseInt(o[10]))+9*(parseInt(o[1])+parseInt(o[11]))+10*(parseInt(o[2])+parseInt(o[12]))+5*(parseInt(o[3])+parseInt(o[13]))+8*(parseInt(o[4])+parseInt(o[14]))+4*(parseInt(o[5])+parseInt(o[15]))+2*(parseInt(o[6])+parseInt(o[16]))+1*parseInt(o[7])+6*parseInt(o[8])+3*parseInt(o[9]),t=r%11,n="F",i="10X98765432",n=i.substr(t,1),n==o[17]?{result:!0}:{result:!1,e:"身份证号码出生日期超出范围或含有非法字符!"}):{result:!1,e:"身份证号码出生日期超出范围或含有非法字符!"};default:return{result:!1,e:"身份证号码位数不对!"}}};return i(e).result},"请输入正确的身份证号"),e.validator.addMethod("checkMobile",function(e,t){var i=e.length,r=/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;return this.optional(t)||11==i&&r.test(e)},"请输入正确的手机号"),e.validator.addMethod("isChinese",function(e,t){var i=/^[\u4e00-\u9fa5]{2,20}$/;return i.test(e)}),e.validator.addMethod("chrnum",function(e,t){var i=/^([a-zA-Z0-9]+)$/;return this.optional(t)||i.test(e)},"只能输入数字和字母(字符A-Z, a-z, 0-9)"),e.validator.addMethod("isZipCode",function(e,t){var i=/^[0-9]{6}$/;return this.optional(t)||i.test(e)},"请正确填写您的邮政编码"),e.validator.addMethod("isPhone",function(e,t){var i=/^\d{3,4}-?\d{7,9}$/;return this.optional(t)||i.test(e)},"请正确填写的电话号码"),e.validator.addMethod("millionUnits",function(e,t){var i=/^[\-\+]?(0|[1-9]\d*)(\.\d{1,4})?$/;return this.optional(t)||i.test(e)},"请正确填写的金额"),e.validator.addMethod("units",function(e,t){var i=/^[\-\+]?(0|[1-9]\d*)(\.\d{1,2})?$/;return this.optional(t)||i.test(e)},"请正确填写的金额"),e.validator.addMethod("area",function(e,t){
var i=/^(0|[1-9]\d*)(\.\d{1,4})?$/;return this.optional(t)||i.test(e)},"请正确填写的面积"),e.validator.addMethod("percent",function(e,t){var i=/^(100(\.0{1,2})?|[1-9]\d(\.\d{1,3})?|\d(\.\d{1,3})?)$/;return this.optional(t)||i.test(e)},"请正确填写的百分比"),e.validator.addMethod("byteRangeLength",function(e,t,i){for(var r=e.length,n=0;n<e.length;n++)e.charCodeAt(n)>127&&r++;return this.optional(t)||r<=i[0]},e.validator.format("请确保输入的值在{0}个字节之间(一个中文字算2个字节)")),e.validator.addMethod("isNotBlack",function(e,t,i){var r=e.trim();return""!=r},e.validator.format("内容不可为空")),e.validator.addMethod("checkQQ",function(e,t){var i=/^[1-9]\d{4,9}$/;return this.optional(t)||i.test(e)},"请输入正确的QQ号")}),define("client/login/view/retrievePassView",["client/login/model/retrievePassModel","common/util","layer1","common/validateRules"],function(e,t,i){function r(){n(),$("#tickling").hide()}function n(){t.bindEvents([{el:".js-user-pass",event:"click",handler:function(){s()&&a()}},{el:"#js-email",event:"keypress",handler:function(){13==event.keyCode&&($(".js-user-pass").click(),window.event.returnValue=!1,event.preventDefault())}},{el:"input",event:"keydown",handler:function(){$(".error-place").hide()}}])}function s(){return $("#js-form").validate({rules:{email:{required:!0,email:!0}},messages:{email:{required:"邮箱不能为空",email:"请输入正确的邮箱"}}}).form()}function a(){e.userRetrievePass({data:$(".js-pass-form").serialize(),beforeSend:function(){i.msg("正在发送邮件，请稍候",{icon:16,time:-1,shade:.3})},callBack:function(e){i.closeAll(),e.data?window.location.href=window.__NOTEBOOK__.root_url+"client/sendemailsuccess/retrievepassword":"11"==e.subStatus?$(".error-place").html('<label class="error">此邮箱未注册，请先注册！</label>').show():$(".error-place").html('<label class="error">密码找回失败，请重试！</label>').show()},error:function(e){i.alert("操作失败，请重试")}})}return{init:r}}),require(["client/login/view/retrievePassView"],function(e){e.init()}),define("client/login/controller/retrievePassController",function(){});