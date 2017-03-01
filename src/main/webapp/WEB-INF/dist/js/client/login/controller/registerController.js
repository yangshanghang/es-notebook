/*! jQuery Validation Plugin - v1.15.1 - 7/22/2016
 * http://jqueryvalidation.org/
 * Copyright (c) 2016 Jörn Zaefferer; Licensed MIT */

define("common/util",["jquery"],function(){function e(e){$.each(e,function(e,t){"string"==typeof t.el?$(document).on(t.event,t.el,t.handler):$(t.el).on(t.event,t.handler)})}function t(e){var t={},r=[];return e&&-1!==e.indexOf("?")?($.each(e.substr(e.indexOf("?")+1).split("&"),function(e,i){r=i.split("="),t[r[0]]=r[1]}),t):t}function r(e){e&&$.cookie("exsToken",e,{path:"/",expires:7})}function i(){return $.cookie("exsToken")}function n(){$.cookie("exsToken",null,{path:"/"})}function s(e){for(var t,r,i,n="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",s=0,a=e.length,o="";a>s;){if(t=255&e.charCodeAt(s++),s==a){o+=n.charAt(t>>2),o+=n.charAt((3&t)<<4),o+="==";break}if(r=e.charCodeAt(s++),s==a){o+=n.charAt(t>>2),o+=n.charAt((3&t)<<4|(240&r)>>4),o+=n.charAt((15&r)<<2),o+="=";break}i=e.charCodeAt(s++),o+=n.charAt(t>>2),o+=n.charAt((3&t)<<4|(240&r)>>4),o+=n.charAt((15&r)<<2|(192&i)>>6),o+=n.charAt(63&i)}return o}function a(e){var t,r=[];return $.each(e,function(e,i){t=i.replace(/ /g,""),""!=t&&r.push(t)}),r}function o(e,t){var r,i;switch(e){case"phone":r="^[1][358][0-9]{9}$";break;case"ifSpecial":r="^[a-zA-Z0-9一-龥]+$";break;case"email":r="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";break;default:r=""}return i=new RegExp(r),i.test(t)}function l(e,t){var r=arguments[2]||new Date;switch(e){case"s":r=new Date(r.getTime()+1e3*t);break;case"n":r=new Date(r.getTime()+6e4*t);break;case"h":r=new Date(r.getTime()+36e5*t);break;case"d":r=new Date(r.getTime()+864e5*t);break;case"w":r=new Date(r.getTime()+6048e5*t);break;case"m":r=new Date(r.getFullYear(),r.getMonth()+t,r.getDate(),r.getHours(),r.getMinutes(),r.getSeconds());break;default:r=new Date(r.getFullYear()+t,r.getMonth(),r.getDate(),r.getHours(),r.getMinutes(),r.getSeconds())}return r=r.getTime()>=(new Date).getTime()?new Date:r,[r.getFullYear(),r.getMonth()+1,r.getDate()].join("/")}function d(e,t){var r=t,i=["日","一","二","三","四","五","六"];return r=r.replace(/yy/,e.getFullYear()),r=r.replace(/y/,e.getYear()%100>9?(e.getYear()%100).toString():"0"+e.getYear()%100),r=r.replace(/mm/,e.getMonth()>=9?(e.getMonth()+1).toString():"0"+(e.getMonth()+1)),r=r.replace(/m/g,e.getMonth()+1),r=r.replace(/w|W/g,i[e.getDay()]),r=r.replace(/dd/,e.getDate()>9?e.getDate().toString():"0"+e.getDate()),r=r.replace(/d/g,e.getDate()),r=r.replace(/hh/,e.getHours()>9?e.getHours().toString():"0"+e.getHours()),r=r.replace(/h/g,e.getHours()),r=r.replace(/mm/,e.getMinutes()>9?e.getMinutes().toString():"0"+e.getMinutes()),r=r.replace(/m/g,e.getMinutes()),r=r.replace(/ss/,e.getSeconds()>9?e.getSeconds().toString():"0"+e.getSeconds()),r=r.replace(/s/g,e.getSeconds())}return{bindEvents:e,getUrlParams:t,setToken:r,getToken:i,removeToken:n,base64Encode:s,trims:a,checkRagular:o,timeInterval:l,dateFormat:d}}),define("common/dom",["jquery"],function(e){function t(t,r){var i='<div class="overlay"></div>';e("body").append(i);var t=e(t),s=n[r],a=t.find(".js-placeholder");a.length&&a.remove(),t.prepend(s)}function r(t){var t=e(t);e(".overlay").remove(),t.find(".js-placeholder").remove()}function i(t){var r='<form id="'+t.formId+'" class="hidden" method="post">',i="",n="";return e.each(t.selectList,function(s,a){for(var o=0;o<t.fields.length;o++)i=t.inputName+"["+s+"]."+t.fields[o],n=e(this).data(t.fields[o].toLowerCase()),"undefined"==typeof n&&(n=""),r+='<input name="'+i+'" value="'+n+'" />'}),r+="</form>"}var n={"loading-large":'<div class="js-placeholder loading-large loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_48.gif" /><p>数据加载中，请稍后</p></div></div>',"empty-data":'<div class="js-placeholder empty-data loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/empty_data.png" /><p><span class="major">唔，未找到任何数据</span> 您还可以看看其它行情哦，或者稍后再试</p></div></div>',"loading-error":'<div class="js-placeholder loading-error loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_error.png" /><p>哎呀，似乎网络开小差了</p></div></div>',"wechatloading-large":'<div class="js-placeholder exception loading-large"><div class="vertical-center"><div class="preloader"></div><p>努力加载中...</p></div></div>',"wechatempty-data":'<div class="js-placeholder exception empty-data"><div class="nodata"><img src="../../img/nodata.png" width="46%" height="auto"/><p>唔，未找到任何数据！</p><span>您还可以看看其它行情哦，或者稍后再试。</span></div></div>',"wechatloading-error":'<div class="js-placeholder exception loading-error"><span class="icon-error"></span>加载失败，请稍后再试！</div>'};return{showPlaceholder:t,removePlaceholder:r,generateDldForm:i}}),define("common/http",["jquery","layer1","common/util","common/dom"],function(e,t,r,i){function n(r){var n=e.extend({},o,r);return n.actionConfig&&(n.beforeSend=function(){i.showPlaceholder(n.actionConfig.dom,n.actionConfig.type)}),n.url=s(r.url),n.success=function(e){return n.actionConfig&&i.removePlaceholder(n.actionConfig.dom),n.actionConfig&&"200"!=e.status?void i.showPlaceholder(n.actionConfig.dom,n.actionConfig.type):void setTimeout(function(){r.success&&r.success(e)},400)},n.error=function(e,s,a){return 401===e.status?(t.open({content:"您尚未登录或登录时间过长,请重新登录!",icon:3,title:"登录提示",yes:function(){window.location.href=window.__NOTEBOOK__.root_url},end:function(){t.closeAll()}}),!0):(n.actionErrorConfig&&i.showPlaceholder(n.actionErrorConfig.dom,n.actionErrorConfig.type),void(r.error&&r.error(e,s,a)))},n.serializable&&(n.contentType="application/json",n.data=JSON.stringify(n.data)),n}function s(t){var r={_t:(new Date).getTime()},i=/\?/gi.test(t)?"&":"?";return t+i+e.param(r)}function a(t){var r=e.ajax(n(t));return r}t.config({path:"/js/lib/layer/"});var o={async:!0,dataType:"json",type:"get",serializable:!1};return{httpRequest:a}}),define("client/login/model/registerModel",["common/http"],function(e){function t(t){e.httpRequest({url:window.__NOTEBOOK__.root_url+"client/register",data:t.data,type:"post",dataType:"json",beforeSend:function(){t.beforeSend()},success:function(e){t.callBack(e)},error:t.error})}return{userRegister:t}}),!function(e){"function"==typeof define&&define.amd?define("jquery.validate",["jquery"],e):"object"==typeof module&&module.exports?module.exports=e(require("jquery")):e(jQuery)}(function(e){e.extend(e.fn,{validate:function(t){if(!this.length)return void(t&&t.debug&&window.console&&console.warn("Nothing selected, can't validate, returning nothing."));var r=e.data(this[0],"validator");return r?r:(e(this).attr("novalidate","novalidate"),r=new e.validator(t,this[0]),e.data(this[0],"validator",r),r.settings.onsubmit&&(this.on("click.validate",":submit",function(t){if(r.settings.submitHandler&&(r.submitButton=t.target),e(this).hasClass("cancel")&&(r.cancelSubmit=!0),void 0!==e(this).attr("formnovalidate")){var i=document,n=i.documentMode;r.cancelSubmit=i.all&&n&&8===n&&null===t.target.attributes.formnovalidate?void 0:!0}}),this.on("submit.validate",function(t){function i(){var i,n;return r.settings.submitHandler?(r.submitButton&&(i=e("<input type='hidden'/>").attr("name",r.submitButton.name).val(e(r.submitButton).val()).appendTo(r.currentForm)),n=r.settings.submitHandler.call(r,r.currentForm,t),r.submitButton&&i.remove(),void 0!==n?n:!1):!0}return r.settings.debug&&t.preventDefault(),r.cancelSubmit?(r.cancelSubmit=!1,i()):r.form()?r.pendingRequest?(r.formSubmitted=!0,!1):i():(r.focusInvalid(),!1)})),r)},valid:function(){var t,r,i;return e(this[0]).is("form")?t=this.validate().form():(i=[],t=!0,r=e(this[0].form).validate(),this.each(function(){t=r.element(this)&&t,t||(i=i.concat(r.errorList))}),r.errorList=i),t},rules:function(t,r){var i,n,s,a,o,l,d=this[0];if(null!=d&&null!=d.form){if(t)switch(i=e.data(d.form,"validator").settings,n=i.rules,s=e.validator.staticRules(d),t){case"add":e.extend(s,e.validator.normalizeRule(r)),delete s.messages,n[d.name]=s,r.messages&&(i.messages[d.name]=e.extend(i.messages[d.name],r.messages));break;case"remove":return r?(l={},e.each(r.split(/\s/),function(t,r){l[r]=s[r],delete s[r],"required"===r&&e(d).removeAttr("aria-required")}),l):(delete n[d.name],s)}return a=e.validator.normalizeRules(e.extend({},e.validator.classRules(d),e.validator.attributeRules(d),e.validator.dataRules(d),e.validator.staticRules(d)),d),a.required&&(o=a.required,delete a.required,a=e.extend({required:o},a),e(d).attr("aria-required","true")),a.remote&&(o=a.remote,delete a.remote,a=e.extend(a,{remote:o})),a}}}),e.extend(e.expr[":"],{blank:function(t){return!e.trim(""+e(t).val())},filled:function(t){var r=e(t).val();return null!==r&&!!e.trim(""+r)},unchecked:function(t){return!e(t).prop("checked")}}),e.validator=function(t,r){this.settings=e.extend(!0,{},e.validator.defaults,t),this.currentForm=r,this.init()},e.validator.format=function(t,r){return 1===arguments.length?function(){var r=e.makeArray(arguments);return r.unshift(t),e.validator.format.apply(this,r)}:void 0===r?t:(arguments.length>2&&r.constructor!==Array&&(r=e.makeArray(arguments).slice(1)),r.constructor!==Array&&(r=[r]),e.each(r,function(e,r){t=t.replace(new RegExp("\\{"+e+"\\}","g"),function(){return r})}),t)},e.extend(e.validator,{defaults:{messages:{},groups:{},rules:{},errorClass:"error",pendingClass:"pending",validClass:"valid",errorElement:"label",focusCleanup:!1,focusInvalid:!0,errorContainer:e([]),errorLabelContainer:e([]),onsubmit:!0,ignore:":hidden",ignoreTitle:!1,onfocusin:function(e){this.lastActive=e,this.settings.focusCleanup&&(this.settings.unhighlight&&this.settings.unhighlight.call(this,e,this.settings.errorClass,this.settings.validClass),this.hideThese(this.errorsFor(e)))},onfocusout:function(e){this.checkable(e)||!(e.name in this.submitted)&&this.optional(e)||this.element(e)},onkeyup:function(t,r){var i=[16,17,18,20,35,36,37,38,39,40,45,144,225];9===r.which&&""===this.elementValue(t)||-1!==e.inArray(r.keyCode,i)||(t.name in this.submitted||t.name in this.invalid)&&this.element(t)},onclick:function(e){e.name in this.submitted?this.element(e):e.parentNode.name in this.submitted&&this.element(e.parentNode)},highlight:function(t,r,i){"radio"===t.type?this.findByName(t.name).addClass(r).removeClass(i):e(t).addClass(r).removeClass(i)},unhighlight:function(t,r,i){"radio"===t.type?this.findByName(t.name).removeClass(r).addClass(i):e(t).removeClass(r).addClass(i)}},setDefaults:function(t){e.extend(e.validator.defaults,t)},messages:{required:"This field is required.",remote:"Please fix this field.",email:"Please enter a valid email address.",url:"Please enter a valid URL.",date:"Please enter a valid date.",dateISO:"Please enter a valid date (ISO).",number:"Please enter a valid number.",digits:"Please enter only digits.",equalTo:"Please enter the same value again.",maxlength:e.validator.format("Please enter no more than {0} characters."),minlength:e.validator.format("Please enter at least {0} characters."),rangelength:e.validator.format("Please enter a value between {0} and {1} characters long."),range:e.validator.format("Please enter a value between {0} and {1}."),max:e.validator.format("Please enter a value less than or equal to {0}."),min:e.validator.format("Please enter a value greater than or equal to {0}."),step:e.validator.format("Please enter a multiple of {0}.")},autoCreateRanges:!1,prototype:{init:function(){function t(t){this.form||"undefined"==typeof e(this).attr("contenteditable")||(this.form=e(this).closest("form")[0]);var r=e.data(this.form,"validator"),i="on"+t.type.replace(/^validate/,""),n=r.settings;n[i]&&!e(this).is(n.ignore)&&n[i].call(r,this,t)}this.labelContainer=e(this.settings.errorLabelContainer),this.errorContext=this.labelContainer.length&&this.labelContainer||e(this.currentForm),this.containers=e(this.settings.errorContainer).add(this.settings.errorLabelContainer),this.submitted={},this.valueCache={},this.pendingRequest=0,this.pending={},this.invalid={},this.reset();var r,i=this.groups={};e.each(this.settings.groups,function(t,r){"string"==typeof r&&(r=r.split(/\s/)),e.each(r,function(e,r){i[r]=t})}),r=this.settings.rules,e.each(r,function(t,i){r[t]=e.validator.normalizeRule(i)}),e(this.currentForm).on("focusin.validate focusout.validate keyup.validate",":text, [type='password'], [type='file'], select, textarea, [type='number'], [type='search'], [type='tel'], [type='url'], [type='email'], [type='datetime'], [type='date'], [type='month'], [type='week'], [type='time'], [type='datetime-local'], [type='range'], [type='color'], [type='radio'], [type='checkbox'], [contenteditable]",t).on("click.validate","select, option, [type='radio'], [type='checkbox']",t),this.settings.invalidHandler&&e(this.currentForm).on("invalid-form.validate",this.settings.invalidHandler),e(this.currentForm).find("[required], [data-rule-required], .required").attr("aria-required","true")},form:function(){return this.checkForm(),e.extend(this.submitted,this.errorMap),this.invalid=e.extend({},this.errorMap),this.valid()||e(this.currentForm).triggerHandler("invalid-form",[this]),this.showErrors(),this.valid()},checkForm:function(){this.prepareForm();for(var e=0,t=this.currentElements=this.elements();t[e];e++)this.check(t[e]);return this.valid()},element:function(t){var r,i,n=this.clean(t),s=this.validationTargetFor(n),a=this,o=!0;return void 0===s?delete this.invalid[n.name]:(this.prepareElement(s),this.currentElements=e(s),i=this.groups[s.name],i&&e.each(this.groups,function(e,t){t===i&&e!==s.name&&(n=a.validationTargetFor(a.clean(a.findByName(e))),n&&n.name in a.invalid&&(a.currentElements.push(n),o=a.check(n)&&o))}),r=this.check(s)!==!1,o=o&&r,this.invalid[s.name]=r?!1:!0,this.numberOfInvalids()||(this.toHide=this.toHide.add(this.containers)),this.showErrors(),e(t).attr("aria-invalid",!r)),o},showErrors:function(t){if(t){var r=this;e.extend(this.errorMap,t),this.errorList=e.map(this.errorMap,function(e,t){return{message:e,element:r.findByName(t)[0]}}),this.successList=e.grep(this.successList,function(e){return!(e.name in t)})}this.settings.showErrors?this.settings.showErrors.call(this,this.errorMap,this.errorList):this.defaultShowErrors()},resetForm:function(){e.fn.resetForm&&e(this.currentForm).resetForm(),this.invalid={},this.submitted={},this.prepareForm(),this.hideErrors();var t=this.elements().removeData("previousValue").removeAttr("aria-invalid");this.resetElements(t)},resetElements:function(e){var t;if(this.settings.unhighlight)for(t=0;e[t];t++)this.settings.unhighlight.call(this,e[t],this.settings.errorClass,""),this.findByName(e[t].name).removeClass(this.settings.validClass);else e.removeClass(this.settings.errorClass).removeClass(this.settings.validClass)},numberOfInvalids:function(){return this.objectLength(this.invalid)},objectLength:function(e){var t,r=0;for(t in e)e[t]&&r++;return r},hideErrors:function(){this.hideThese(this.toHide)},hideThese:function(e){e.not(this.containers).text(""),this.addWrapper(e).hide()},valid:function(){return 0===this.size()},size:function(){return this.errorList.length},focusInvalid:function(){if(this.settings.focusInvalid)try{e(this.findLastActive()||this.errorList.length&&this.errorList[0].element||[]).filter(":visible").focus().trigger("focusin")}catch(t){}},findLastActive:function(){var t=this.lastActive;return t&&1===e.grep(this.errorList,function(e){return e.element.name===t.name}).length&&t},elements:function(){var t=this,r={};return e(this.currentForm).find("input, select, textarea, [contenteditable]").not(":submit, :reset, :image, :disabled").not(this.settings.ignore).filter(function(){var i=this.name||e(this).attr("name");return!i&&t.settings.debug&&window.console&&console.error("%o has no name assigned",this),"undefined"!=typeof e(this).attr("contenteditable")&&(this.form=e(this).closest("form")[0]),i in r||!t.objectLength(e(this).rules())?!1:(r[i]=!0,!0)})},clean:function(t){return e(t)[0]},errors:function(){var t=this.settings.errorClass.split(" ").join(".");return e(this.settings.errorElement+"."+t,this.errorContext)},resetInternals:function(){this.successList=[],this.errorList=[],this.errorMap={},this.toShow=e([]),this.toHide=e([])},reset:function(){this.resetInternals(),this.currentElements=e([])},prepareForm:function(){this.reset(),this.toHide=this.errors().add(this.containers)},prepareElement:function(e){this.reset(),this.toHide=this.errorsFor(e)},elementValue:function(t){var r,i,n=e(t),s=t.type;return"radio"===s||"checkbox"===s?this.findByName(t.name).filter(":checked").val():"number"===s&&"undefined"!=typeof t.validity?t.validity.badInput?"NaN":n.val():(r="undefined"!=typeof e(t).attr("contenteditable")?n.text():n.val(),"file"===s?"C:\\fakepath\\"===r.substr(0,12)?r.substr(12):(i=r.lastIndexOf("/"),i>=0?r.substr(i+1):(i=r.lastIndexOf("\\"),i>=0?r.substr(i+1):r)):"string"==typeof r?r.replace(/\r/g,""):r)},check:function(t){t=this.validationTargetFor(this.clean(t));var r,i,n,s=e(t).rules(),a=e.map(s,function(e,t){return t}).length,o=!1,l=this.elementValue(t);if("function"==typeof s.normalizer){if(l=s.normalizer.call(t,l),"string"!=typeof l)throw new TypeError("The normalizer should return a string value.");delete s.normalizer}for(i in s){n={method:i,parameters:s[i]};try{if(r=e.validator.methods[i].call(this,l,t,n.parameters),"dependency-mismatch"===r&&1===a){o=!0;continue}if(o=!1,"pending"===r)return void(this.toHide=this.toHide.not(this.errorsFor(t)));if(!r)return this.formatAndAdd(t,n),!1}catch(d){throw this.settings.debug&&window.console&&console.log("Exception occurred when checking element "+t.id+", check the '"+n.method+"' method.",d),d instanceof TypeError&&(d.message+=".  Exception occurred when checking element "+t.id+", check the '"+n.method+"' method."),d}}return o?void 0:(this.objectLength(s)&&this.successList.push(t),!0)},customDataMessage:function(t,r){return e(t).data("msg"+r.charAt(0).toUpperCase()+r.substring(1).toLowerCase())||e(t).data("msg")},customMessage:function(e,t){var r=this.settings.messages[e];return r&&(r.constructor===String?r:r[t])},findDefined:function(){for(var e=0;e<arguments.length;e++)if(void 0!==arguments[e])return arguments[e]},defaultMessage:function(t,r){"string"==typeof r&&(r={method:r});var i=this.findDefined(this.customMessage(t.name,r.method),this.customDataMessage(t,r.method),!this.settings.ignoreTitle&&t.title||void 0,e.validator.messages[r.method],"<strong>Warning: No message defined for "+t.name+"</strong>"),n=/\$?\{(\d+)\}/g;return"function"==typeof i?i=i.call(this,r.parameters,t):n.test(i)&&(i=e.validator.format(i.replace(n,"{$1}"),r.parameters)),i},formatAndAdd:function(e,t){var r=this.defaultMessage(e,t);this.errorList.push({message:r,element:e,method:t.method}),this.errorMap[e.name]=r,this.submitted[e.name]=r},addWrapper:function(e){return this.settings.wrapper&&(e=e.add(e.parent(this.settings.wrapper))),e},defaultShowErrors:function(){var e,t,r;for(e=0;this.errorList[e];e++)r=this.errorList[e],this.settings.highlight&&this.settings.highlight.call(this,r.element,this.settings.errorClass,this.settings.validClass),this.showLabel(r.element,r.message);if(this.errorList.length&&(this.toShow=this.toShow.add(this.containers)),this.settings.success)for(e=0;this.successList[e];e++)this.showLabel(this.successList[e]);if(this.settings.unhighlight)for(e=0,t=this.validElements();t[e];e++)this.settings.unhighlight.call(this,t[e],this.settings.errorClass,this.settings.validClass);this.toHide=this.toHide.not(this.toShow),this.hideErrors(),this.addWrapper(this.toShow).show()},validElements:function(){return this.currentElements.not(this.invalidElements())},invalidElements:function(){return e(this.errorList).map(function(){return this.element})},showLabel:function(t,r){var i,n,s,a,o=this.errorsFor(t),l=this.idOrName(t),d=e(t).attr("aria-describedby");o.length?(o.removeClass(this.settings.validClass).addClass(this.settings.errorClass),o.html(r)):(o=e("<"+this.settings.errorElement+">").attr("id",l+"-error").addClass(this.settings.errorClass).html(r||""),i=o,this.settings.wrapper&&(i=o.hide().show().wrap("<"+this.settings.wrapper+"/>").parent()),this.labelContainer.length?this.labelContainer.append(i):this.settings.errorPlacement?this.settings.errorPlacement.call(this,i,e(t)):i.insertAfter(t),o.is("label")?o.attr("for",l):0===o.parents("label[for='"+this.escapeCssMeta(l)+"']").length&&(s=o.attr("id"),d?d.match(new RegExp("\\b"+this.escapeCssMeta(s)+"\\b"))||(d+=" "+s):d=s,e(t).attr("aria-describedby",d),n=this.groups[t.name],n&&(a=this,e.each(a.groups,function(t,r){r===n&&e("[name='"+a.escapeCssMeta(t)+"']",a.currentForm).attr("aria-describedby",o.attr("id"))})))),!r&&this.settings.success&&(o.text(""),"string"==typeof this.settings.success?o.addClass(this.settings.success):this.settings.success(o,t)),this.toShow=this.toShow.add(o)},errorsFor:function(t){var r=this.escapeCssMeta(this.idOrName(t)),i=e(t).attr("aria-describedby"),n="label[for='"+r+"'], label[for='"+r+"'] *";return i&&(n=n+", #"+this.escapeCssMeta(i).replace(/\s+/g,", #")),this.errors().filter(n)},escapeCssMeta:function(e){return e.replace(/([\\!"#$%&'()*+,.\/:;<=>?@\[\]^`{|}~])/g,"\\$1")},idOrName:function(e){return this.groups[e.name]||(this.checkable(e)?e.name:e.id||e.name)},validationTargetFor:function(t){return this.checkable(t)&&(t=this.findByName(t.name)),e(t).not(this.settings.ignore)[0]},checkable:function(e){return/radio|checkbox/i.test(e.type)},findByName:function(t){return e(this.currentForm).find("[name='"+this.escapeCssMeta(t)+"']")},getLength:function(t,r){switch(r.nodeName.toLowerCase()){case"select":return e("option:selected",r).length;case"input":if(this.checkable(r))return this.findByName(r.name).filter(":checked").length}return t.length},depend:function(e,t){return this.dependTypes[typeof e]?this.dependTypes[typeof e](e,t):!0},dependTypes:{"boolean":function(e){return e},string:function(t,r){return!!e(t,r.form).length},"function":function(e,t){return e(t)}},optional:function(t){var r=this.elementValue(t);return!e.validator.methods.required.call(this,r,t)&&"dependency-mismatch"},startRequest:function(t){this.pending[t.name]||(this.pendingRequest++,e(t).addClass(this.settings.pendingClass),this.pending[t.name]=!0)},stopRequest:function(t,r){this.pendingRequest--,this.pendingRequest<0&&(this.pendingRequest=0),delete this.pending[t.name],e(t).removeClass(this.settings.pendingClass),r&&0===this.pendingRequest&&this.formSubmitted&&this.form()?(e(this.currentForm).submit(),this.formSubmitted=!1):!r&&0===this.pendingRequest&&this.formSubmitted&&(e(this.currentForm).triggerHandler("invalid-form",[this]),this.formSubmitted=!1)},previousValue:function(t,r){return r="string"==typeof r&&r||"remote",e.data(t,"previousValue")||e.data(t,"previousValue",{old:null,valid:!0,message:this.defaultMessage(t,{method:r})})},destroy:function(){this.resetForm(),e(this.currentForm).off(".validate").removeData("validator").find(".validate-equalTo-blur").off(".validate-equalTo").removeClass("validate-equalTo-blur")}},classRuleSettings:{required:{required:!0},email:{email:!0},url:{url:!0},date:{date:!0},dateISO:{dateISO:!0},number:{number:!0},digits:{digits:!0},creditcard:{creditcard:!0}},addClassRules:function(t,r){t.constructor===String?this.classRuleSettings[t]=r:e.extend(this.classRuleSettings,t)},classRules:function(t){var r={},i=e(t).attr("class");return i&&e.each(i.split(" "),function(){this in e.validator.classRuleSettings&&e.extend(r,e.validator.classRuleSettings[this])}),r},normalizeAttributeRule:function(e,t,r,i){/min|max|step/.test(r)&&(null===t||/number|range|text/.test(t))&&(i=Number(i),isNaN(i)&&(i=void 0)),i||0===i?e[r]=i:t===r&&"range"!==t&&(e[r]=!0)},attributeRules:function(t){var r,i,n={},s=e(t),a=t.getAttribute("type");for(r in e.validator.methods)"required"===r?(i=t.getAttribute(r),""===i&&(i=!0),i=!!i):i=s.attr(r),this.normalizeAttributeRule(n,a,r,i);return n.maxlength&&/-1|2147483647|524288/.test(n.maxlength)&&delete n.maxlength,n},dataRules:function(t){var r,i,n={},s=e(t),a=t.getAttribute("type");for(r in e.validator.methods)i=s.data("rule"+r.charAt(0).toUpperCase()+r.substring(1).toLowerCase()),this.normalizeAttributeRule(n,a,r,i);return n},staticRules:function(t){var r={},i=e.data(t.form,"validator");return i.settings.rules&&(r=e.validator.normalizeRule(i.settings.rules[t.name])||{}),r},normalizeRules:function(t,r){return e.each(t,function(i,n){if(n===!1)return void delete t[i];if(n.param||n.depends){var s=!0;switch(typeof n.depends){case"string":s=!!e(n.depends,r.form).length;break;case"function":s=n.depends.call(r,r)}s?t[i]=void 0!==n.param?n.param:!0:(e.data(r.form,"validator").resetElements(e(r)),delete t[i])}}),e.each(t,function(i,n){t[i]=e.isFunction(n)&&"normalizer"!==i?n(r):n}),e.each(["minlength","maxlength"],function(){t[this]&&(t[this]=Number(t[this]))}),e.each(["rangelength","range"],function(){var r;t[this]&&(e.isArray(t[this])?t[this]=[Number(t[this][0]),Number(t[this][1])]:"string"==typeof t[this]&&(r=t[this].replace(/[\[\]]/g,"").split(/[\s,]+/),t[this]=[Number(r[0]),Number(r[1])]))}),e.validator.autoCreateRanges&&(null!=t.min&&null!=t.max&&(t.range=[t.min,t.max],delete t.min,delete t.max),null!=t.minlength&&null!=t.maxlength&&(t.rangelength=[t.minlength,t.maxlength],delete t.minlength,delete t.maxlength)),t},normalizeRule:function(t){if("string"==typeof t){var r={};e.each(t.split(/\s/),function(){r[this]=!0}),t=r}return t},addMethod:function(t,r,i){e.validator.methods[t]=r,e.validator.messages[t]=void 0!==i?i:e.validator.messages[t],r.length<3&&e.validator.addClassRules(t,e.validator.normalizeRule(t))},methods:{required:function(t,r,i){if(!this.depend(i,r))return"dependency-mismatch";if("select"===r.nodeName.toLowerCase()){var n=e(r).val();return n&&n.length>0}return this.checkable(r)?this.getLength(t,r)>0:t.length>0},email:function(e,t){return this.optional(t)||/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test(e)},url:function(e,t){return this.optional(t)||/^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[\/?#]\S*)?$/i.test(e)},date:function(e,t){return this.optional(t)||!/Invalid|NaN/.test(new Date(e).toString())},dateISO:function(e,t){return this.optional(t)||/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test(e)},number:function(e,t){return this.optional(t)||/^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(e)},digits:function(e,t){return this.optional(t)||/^\d+$/.test(e)},minlength:function(t,r,i){var n=e.isArray(t)?t.length:this.getLength(t,r);return this.optional(r)||n>=i},maxlength:function(t,r,i){var n=e.isArray(t)?t.length:this.getLength(t,r);return this.optional(r)||i>=n},rangelength:function(t,r,i){var n=e.isArray(t)?t.length:this.getLength(t,r);return this.optional(r)||n>=i[0]&&n<=i[1]},min:function(e,t,r){return this.optional(t)||e>=r},max:function(e,t,r){return this.optional(t)||r>=e},range:function(e,t,r){return this.optional(t)||e>=r[0]&&e<=r[1]},step:function(t,r,i){var n,s=e(r).attr("type"),a="Step attribute on input type "+s+" is not supported.",o=["text","number","range"],l=new RegExp("\\b"+s+"\\b"),d=s&&!l.test(o.join()),u=function(e){var t=(""+e).match(/(?:\.(\d+))?$/);return t&&t[1]?t[1].length:0},h=function(e){return Math.round(e*Math.pow(10,n))},c=!0;if(d)throw new Error(a);return n=u(i),(u(t)>n||0!==h(t)%h(i))&&(c=!1),this.optional(r)||c},equalTo:function(t,r,i){var n=e(i);return this.settings.onfocusout&&n.not(".validate-equalTo-blur").length&&n.addClass("validate-equalTo-blur").on("blur.validate-equalTo",function(){e(r).valid()}),t===n.val()},remote:function(t,r,i,n){if(this.optional(r))return"dependency-mismatch";n="string"==typeof n&&n||"remote";var s,a,o,l=this.previousValue(r,n);return this.settings.messages[r.name]||(this.settings.messages[r.name]={}),l.originalMessage=l.originalMessage||this.settings.messages[r.name][n],this.settings.messages[r.name][n]=l.message,i="string"==typeof i&&{url:i}||i,o=e.param(e.extend({data:t},i.data)),l.old===o?l.valid:(l.old=o,s=this,this.startRequest(r),a={},a[r.name]=t,e.ajax(e.extend(!0,{mode:"abort",port:"validate"+r.name,dataType:"json",data:a,context:s.currentForm,success:function(e){var i,a,o,d=e===!0||"true"===e;s.settings.messages[r.name][n]=l.originalMessage,d?(o=s.formSubmitted,s.resetInternals(),s.toHide=s.errorsFor(r),s.formSubmitted=o,s.successList.push(r),s.invalid[r.name]=!1,s.showErrors()):(i={},a=e||s.defaultMessage(r,{method:n,parameters:t}),i[r.name]=l.message=a,s.invalid[r.name]=!0,s.showErrors(i)),l.valid=d,s.stopRequest(r,d)}},i)),"pending")}}});var t,r={};e.ajaxPrefilter?e.ajaxPrefilter(function(e,t,i){var n=e.port;"abort"===e.mode&&(r[n]&&r[n].abort(),r[n]=i)}):(t=e.ajax,e.ajax=function(i){var n=("mode"in i?i:e.ajaxSettings).mode,s=("port"in i?i:e.ajaxSettings).port;return"abort"===n?(r[s]&&r[s].abort(),r[s]=t.apply(this,arguments),r[s]):t.apply(this,arguments)})}),define("common/validateRules",["jquery","jquery.validate"],function(e){e.validator.addMethod("idCard",function(e,t){var r=function(e){var t,r,i,n,s,a={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},o=e.split("");if(null==a[parseInt(e.substr(0,2))])return{result:!1,e:"身份证号码不正确!"};switch(e.length){case 15:return s=(parseInt(e.substr(6,2))+1900)%4==0||(parseInt(e.substr(6,2))+1900)%100==0&&(parseInt(e.substr(6,2))+1900)%4==0?/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/:/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/,s.test(e)?{result:!0}:{result:!1,e:"身份证号码出生日期超出范围或含有非法字符!"};case 18:return s=parseInt(e.substr(6,4))%4==0||parseInt(e.substr(6,4))%100==0&&parseInt(e.substr(6,4))%4==0?/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/:/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/,s.test(e)?(i=7*(parseInt(o[0])+parseInt(o[10]))+9*(parseInt(o[1])+parseInt(o[11]))+10*(parseInt(o[2])+parseInt(o[12]))+5*(parseInt(o[3])+parseInt(o[13]))+8*(parseInt(o[4])+parseInt(o[14]))+4*(parseInt(o[5])+parseInt(o[15]))+2*(parseInt(o[6])+parseInt(o[16]))+1*parseInt(o[7])+6*parseInt(o[8])+3*parseInt(o[9]),t=i%11,n="F",r="10X98765432",n=r.substr(t,1),n==o[17]?{result:!0}:{result:!1,e:"身份证号码出生日期超出范围或含有非法字符!"}):{result:!1,e:"身份证号码出生日期超出范围或含有非法字符!"};default:return{result:!1,e:"身份证号码位数不对!"}}};return r(e).result},"请输入正确的身份证号"),e.validator.addMethod("checkMobile",function(e,t){var r=e.length,i=/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;return this.optional(t)||11==r&&i.test(e)},"请输入正确的手机号"),e.validator.addMethod("isChinese",function(e,t){var r=/^[\u4e00-\u9fa5]{2,20}$/;return r.test(e)}),e.validator.addMethod("chrnum",function(e,t){var r=/^([a-zA-Z0-9]+)$/;return this.optional(t)||r.test(e)},"只能输入数字和字母(字符A-Z, a-z, 0-9)"),e.validator.addMethod("isZipCode",function(e,t){var r=/^[0-9]{6}$/;return this.optional(t)||r.test(e)},"请正确填写您的邮政编码"),e.validator.addMethod("isPhone",function(e,t){var r=/^\d{3,4}-?\d{7,9}$/;return this.optional(t)||r.test(e)},"请正确填写的电话号码"),e.validator.addMethod("millionUnits",function(e,t){var r=/^[\-\+]?(0|[1-9]\d*)(\.\d{1,4})?$/;return this.optional(t)||r.test(e)},"请正确填写的金额"),e.validator.addMethod("units",function(e,t){var r=/^[\-\+]?(0|[1-9]\d*)(\.\d{1,2})?$/;return this.optional(t)||r.test(e)},"请正确填写的金额"),e.validator.addMethod("area",function(e,t){
var r=/^(0|[1-9]\d*)(\.\d{1,4})?$/;return this.optional(t)||r.test(e)},"请正确填写的面积"),e.validator.addMethod("percent",function(e,t){var r=/^(100(\.0{1,2})?|[1-9]\d(\.\d{1,3})?|\d(\.\d{1,3})?)$/;return this.optional(t)||r.test(e)},"请正确填写的百分比"),e.validator.addMethod("byteRangeLength",function(e,t,r){for(var i=e.length,n=0;n<e.length;n++)e.charCodeAt(n)>127&&i++;return this.optional(t)||i<=r[0]},e.validator.format("请确保输入的值在{0}个字节之间(一个中文字算2个字节)")),e.validator.addMethod("isNotBlack",function(e,t,r){var i=e.trim();return""!=i},e.validator.format("内容不可为空")),e.validator.addMethod("checkQQ",function(e,t){var r=/^[1-9]\d{4,9}$/;return this.optional(t)||r.test(e)},"请输入正确的QQ号")}),define("client/login/view/registerView",["client/login/model/registerModel","common/util","layer1","common/validateRules"],function(e,t,r){function i(){n(),$("#tickling").hide()}function n(){t.bindEvents([{el:".js-user-reg",event:"click",handler:function(){s()&&a()}},{el:"body",event:"keypress",handler:function(){13==event.keyCode&&$(".js-user-reg").click()}},{el:"input",event:"keydown",handler:function(){$(".js-error").empty()}},{el:".js-again-write",event:"click",handler:function(){$(".js-register-success").hide(),$("#js-form").show()}}])}function s(){return $("#js-form").validate({rules:{email:{required:!0,email:!0},password:{required:!0,minlength:6,chrnum:!0},realName:{isNotBlack:!0,isChinese:!0},qq:{required:!0,checkQQ:!0,maxlength:10}},messages:{email:{required:"邮箱不能为空",email:"请输入正确的邮箱"},password:{required:"密码不能为空",minlength:"6-20个字符"},realName:{isNotBlack:"姓名不可为空",isChinese:"请输入至少两个汉字"},qq:{required:"QQ不可为空",checkQQ:"请输入正确的QQ号",maxlength:"请输入正确的QQ号"}}}).form()}function a(){e.userRegister({data:$("#js-form").serialize(),beforeSend:function(){r.load(2,{shade:[.5,"#f5f5f5"]})},callBack:function(e){r.closeAll(),200==e.status?($(".js-register-success").show(),$("#js-form").hide(),$(".js-email").text($("input[name=email]").val()),""==e.data||null==e.data?$("#js-address").hide():($("#js-address").show(),$("#js-address").attr("href","http://"+e.data))):12==e.subStatus?$(".js-error").html('<label class="error">该邮箱已注册！</label>').show():13==e.subStatus?$(".js-error").html('<label class="error">激活邮件发送失败，请重试!</label>').show():r.alert("注册失败，请重试！")},error:function(e){r.alert("操作失败，请重试")}})}return{init:i}}),require(["client/login/view/registerView"],function(e){e.init()}),define("client/login/controller/registerController",function(){});