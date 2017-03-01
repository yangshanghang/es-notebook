define("common/util",["jquery"],function(){function e(e){$.each(e,function(e,t){"string"==typeof t.el?$(document).on(t.event,t.el,t.handler):$(t.el).on(t.event,t.handler)})}function t(e){var t={},n=[];return e&&-1!==e.indexOf("?")?($.each(e.substr(e.indexOf("?")+1).split("&"),function(e,i){n=i.split("="),t[n[0]]=n[1]}),t):t}function n(e){e&&$.cookie("exsToken",e,{path:"/",expires:7})}function i(){return $.cookie("exsToken")}function r(){$.cookie("exsToken",null,{path:"/"})}function a(e){for(var t,n,i,r="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",a=0,o=e.length,s="";o>a;){if(t=255&e.charCodeAt(a++),a==o){s+=r.charAt(t>>2),s+=r.charAt((3&t)<<4),s+="==";break}if(n=e.charCodeAt(a++),a==o){s+=r.charAt(t>>2),s+=r.charAt((3&t)<<4|(240&n)>>4),s+=r.charAt((15&n)<<2),s+="=";break}i=e.charCodeAt(a++),s+=r.charAt(t>>2),s+=r.charAt((3&t)<<4|(240&n)>>4),s+=r.charAt((15&n)<<2|(192&i)>>6),s+=r.charAt(63&i)}return s}function o(e){var t,n=[];return $.each(e,function(e,i){t=i.replace(/ /g,""),""!=t&&n.push(t)}),n}function s(e,t){var n,i;switch(e){case"phone":n="^[1][358][0-9]{9}$";break;case"ifSpecial":n="^[a-zA-Z0-9一-龥]+$";break;case"email":n="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";break;default:n=""}return i=new RegExp(n),i.test(t)}function c(e,t){var n=arguments[2]||new Date;switch(e){case"s":n=new Date(n.getTime()+1e3*t);break;case"n":n=new Date(n.getTime()+6e4*t);break;case"h":n=new Date(n.getTime()+36e5*t);break;case"d":n=new Date(n.getTime()+864e5*t);break;case"w":n=new Date(n.getTime()+6048e5*t);break;case"m":n=new Date(n.getFullYear(),n.getMonth()+t,n.getDate(),n.getHours(),n.getMinutes(),n.getSeconds());break;default:n=new Date(n.getFullYear()+t,n.getMonth(),n.getDate(),n.getHours(),n.getMinutes(),n.getSeconds())}return n=n.getTime()>=(new Date).getTime()?new Date:n,[n.getFullYear(),n.getMonth()+1,n.getDate()].join("/")}function l(e,t){var n=t,i=["日","一","二","三","四","五","六"];return n=n.replace(/yy/,e.getFullYear()),n=n.replace(/y/,e.getYear()%100>9?(e.getYear()%100).toString():"0"+e.getYear()%100),n=n.replace(/mm/,e.getMonth()>=9?(e.getMonth()+1).toString():"0"+(e.getMonth()+1)),n=n.replace(/m/g,e.getMonth()+1),n=n.replace(/w|W/g,i[e.getDay()]),n=n.replace(/dd/,e.getDate()>9?e.getDate().toString():"0"+e.getDate()),n=n.replace(/d/g,e.getDate()),n=n.replace(/hh/,e.getHours()>9?e.getHours().toString():"0"+e.getHours()),n=n.replace(/h/g,e.getHours()),n=n.replace(/mm/,e.getMinutes()>9?e.getMinutes().toString():"0"+e.getMinutes()),n=n.replace(/m/g,e.getMinutes()),n=n.replace(/ss/,e.getSeconds()>9?e.getSeconds().toString():"0"+e.getSeconds()),n=n.replace(/s/g,e.getSeconds())}return{bindEvents:e,getUrlParams:t,setToken:n,getToken:i,removeToken:r,base64Encode:a,trims:o,checkRagular:s,timeInterval:c,dateFormat:l}}),define("common/dom",["jquery"],function(e){function t(t,n){var i='<div class="overlay"></div>';e("body").append(i);var t=e(t),a=r[n],o=t.find(".js-placeholder");o.length&&o.remove(),t.prepend(a)}function n(t){var t=e(t);e(".overlay").remove(),t.find(".js-placeholder").remove()}function i(t){var n='<form id="'+t.formId+'" class="hidden" method="post">',i="",r="";return e.each(t.selectList,function(a,o){for(var s=0;s<t.fields.length;s++)i=t.inputName+"["+a+"]."+t.fields[s],r=e(this).data(t.fields[s].toLowerCase()),"undefined"==typeof r&&(r=""),n+='<input name="'+i+'" value="'+r+'" />'}),n+="</form>"}var r={"loading-large":'<div class="js-placeholder loading-large loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_48.gif" /><p>数据加载中，请稍后</p></div></div>',"empty-data":'<div class="js-placeholder empty-data loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/empty_data.png" /><p><span class="major">唔，未找到任何数据</span> 您还可以看看其它行情哦，或者稍后再试</p></div></div>',"loading-error":'<div class="js-placeholder loading-error loading-pos"><div class="vertical-center"><img class="loading-img" src="../../img/loading_error.png" /><p>哎呀，似乎网络开小差了</p></div></div>',"wechatloading-large":'<div class="js-placeholder exception loading-large"><div class="vertical-center"><div class="preloader"></div><p>努力加载中...</p></div></div>',"wechatempty-data":'<div class="js-placeholder exception empty-data"><div class="nodata"><img src="../../img/nodata.png" width="46%" height="auto"/><p>唔，未找到任何数据！</p><span>您还可以看看其它行情哦，或者稍后再试。</span></div></div>',"wechatloading-error":'<div class="js-placeholder exception loading-error"><span class="icon-error"></span>加载失败，请稍后再试！</div>'};return{showPlaceholder:t,removePlaceholder:n,generateDldForm:i}}),define("common/http",["jquery","layer1","common/util","common/dom"],function(e,t,n,i){function r(n){var r=e.extend({},s,n);return r.actionConfig&&(r.beforeSend=function(){i.showPlaceholder(r.actionConfig.dom,r.actionConfig.type)}),r.url=a(n.url),r.success=function(e){return r.actionConfig&&i.removePlaceholder(r.actionConfig.dom),r.actionConfig&&"200"!=e.status?void i.showPlaceholder(r.actionConfig.dom,r.actionConfig.type):void setTimeout(function(){n.success&&n.success(e)},400)},r.error=function(e,a,o){return 401===e.status?(t.open({content:"您尚未登录或登录时间过长,请重新登录!",icon:3,title:"登录提示",yes:function(){window.location.href=window.__NOTEBOOK__.root_url},end:function(){t.closeAll()}}),!0):(r.actionErrorConfig&&i.showPlaceholder(r.actionErrorConfig.dom,r.actionErrorConfig.type),void(n.error&&n.error(e,a,o)))},r.serializable&&(r.contentType="application/json",r.data=JSON.stringify(r.data)),r}function a(t){var n={_t:(new Date).getTime()},i=/\?/gi.test(t)?"&":"?";return t+i+e.param(n)}function o(t){var n=e.ajax(r(t));return n}t.config({path:"/js/lib/layer/"});var s={async:!0,dataType:"json",type:"get",serializable:!1};return{httpRequest:o}}),define("client/note/model/listModel",["common/http"],function(e){function t(t){e.httpRequest({url:window.__NOTEBOOK__.root_url+"client/note/query",data:t.data,beforeSend:function(){t.beforeSend()},success:function(e){t.callBack(e)},error:t.error})}return{getNoteList:t}}),define("common/hbsHelper",["handlebars"],function(e){e.registerHelper("ifCond",function(e,t,n,i){switch(t){case"!=":return e!=n?i.fn(this):i.inverse(this);case"==":return e==n?i.fn(this):i.inverse(this);case"===":return e===n?i.fn(this):i.inverse(this);case"<":return n>e?i.fn(this):i.inverse(this);case"<=":return n>=e?i.fn(this):i.inverse(this);case">":return e>n?i.fn(this):i.inverse(this);case">=":return e>=n?i.fn(this):i.inverse(this);case"&&":return e&&n?i.fn(this):i.inverse(this);case"||":return e||n?i.fn(this):i.inverse(this);case"length":return e.length===n?i.fn(this):i.inverse(this);default:return i.inverse(this)}}),e.registerHelper("math",function(e,t,n){return"+"==t?e+n:"-"==t?e-n:"*"==t?e*n:"/"==t?e/n:"%"==t?e%n:void 0}),e.registerHelper("addOne",function(e){return e+1}),e.registerHelper("prettifyDate",function(e){return e?e.replace("T"," "):null}),e.registerHelper("substring",function(e){return null==e||""==e||e.indexOf("&")<0?e:e.substring(0,e.indexOf("&"))})}),define("client/note/view/listView",["client/note/model/listModel","common/util","layer1","handlebars","common/hbsHelper","pagination"],function(e,t,n,i){function r(){d(),a()}function a(){t.bindEvents([{el:".js-type",event:"click",handler:function(){$(".js-type").removeClass("cur").removeAttr("js-type"),$(this).addClass("cur").attr("js-type","1"),o(0,$(this).attr("js-category-id"))}},{el:".js-hiddenType",event:"click",handler:function(){$(this).siblings().show(),$(".js-type").removeAttr("js-type").removeClass("cur"),$("#ded").remove(),$(".js-allType li").last().before("<li id='ded'><a js-type='1' js-category-id='"+$(this).attr("js-category-id")+"' class='js-type cur ellips' title='"+$(this).text()+"'>"+$(this).text()+"</a></li>"),$(this).hide(),o(0,$(this).attr("js-category-id")),0==$(".more-type a:visible").length&&$(".more").hide()}},{el:"#query",event:"click",handler:function(){s(0,$(".js-type[js-type='1']").attr("js-category-id"))}},{el:"#queryParam",event:"keypress",handler:function(){13==event.keyCode&&$("#query").click()}}])}function o(e,t){t?s(e,t):s(e)}function s(t,r){var a=$.trim($("#queryParam").val());e.getNoteList({data:{categoryId:r,queryParam:a,pageNum:t,pageSize:u},beforeSend:function(){n.load(2,{shade:[.3,"#d9d9d9"],type:3})},callBack:function(e){n.closeAll();var r=e.data.total;$("#took").text(e.data.took),$("#itemCount").text(r),r>1e3&&(r=1e3);var a=i.compile($("#listTemplate").html()),o={hitsList:e.data.hits};$(".js-template").html(a(o)),c(r,t),"0"==r?$("#js-pagination").hide():$("#js-pagination").show()},error:function(e){n.alert("操作失败，请重试")}})}function c(e,t){$("#pagination").pagination(e,{items_per_page:u,prev_text:'<i class="icon-triangle-left"></i>上一页',next_text:'下一页<i class="icon-triangle-right"></i>',num_display_entries:10,current_page:t,link_to:"javascript:void(0)",callback:l})}function l(e,t){$("html,body").animate({scrollTop:0},10),o(e,$(".js-type[js-type='1']").attr("js-category-id"))}function d(){if($("#itemCount").text()>0){var e=$("#itemCount").text();e>1e3&&(e=1e3)}else $("#js-pagination").hide();c(e,0)}var u=10;return{init:r}}),+function(e){"function"==typeof define&&define.amd?define("common/backToTop",["jquery"],e):e($)}(function(e){"use strict";function t(t){try{e("body").append(n),t.showDistance=t.showDistance||100,t.fadeTime=t.fadeTime||300,t.scrollTime=t.scrollTime||200;var i=function(){e(window).scrollTop()>t.showDistance?e("#"+t.domId).fadeIn(t.fadeTime):e("#"+t.domId).fadeOut(t.fadeTime)};i(),e(window).scroll(function(){i()}),e("#"+t.domId).click(function(){return e("body,html").animate({scrollTop:0},t.scrollTime),!1})}catch(r){}}var n='<p id="back-to-top"><a href="javascript:void(0)">顶部</a></p>';t({domId:"back-to-top",showDistance:100,fadeTime:300,scrollTime:200})}),require(["client/note/view/listView","common/backToTop"],function(e){e.init()}),define("client/note/controller/listController",function(){});