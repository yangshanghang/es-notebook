define("client/note/model/indexModel",function(){}),define("common/util",["jquery"],function(){function e(e){$.each(e,function(e,t){"string"==typeof t.el?$(document).on(t.event,t.el,t.handler):$(t.el).on(t.event,t.handler)})}function t(e){var t={},n=[];return e&&-1!==e.indexOf("?")?($.each(e.substr(e.indexOf("?")+1).split("&"),function(e,r){n=r.split("="),t[n[0]]=n[1]}),t):t}function n(e){e&&$.cookie("exsToken",e,{path:"/",expires:7})}function r(){return $.cookie("exsToken")}function a(){$.cookie("exsToken",null,{path:"/"})}function o(e){for(var t,n,r,a="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",o=0,i=e.length,c="";i>o;){if(t=255&e.charCodeAt(o++),o==i){c+=a.charAt(t>>2),c+=a.charAt((3&t)<<4),c+="==";break}if(n=e.charCodeAt(o++),o==i){c+=a.charAt(t>>2),c+=a.charAt((3&t)<<4|(240&n)>>4),c+=a.charAt((15&n)<<2),c+="=";break}r=e.charCodeAt(o++),c+=a.charAt(t>>2),c+=a.charAt((3&t)<<4|(240&n)>>4),c+=a.charAt((15&n)<<2|(192&r)>>6),c+=a.charAt(63&r)}return c}function i(e){var t,n=[];return $.each(e,function(e,r){t=r.replace(/ /g,""),""!=t&&n.push(t)}),n}function c(e,t){var n,r;switch(e){case"phone":n="^[1][358][0-9]{9}$";break;case"ifSpecial":n="^[a-zA-Z0-9一-龥]+$";break;case"email":n="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";break;default:n=""}return r=new RegExp(n),r.test(t)}function g(e,t){var n=arguments[2]||new Date;switch(e){case"s":n=new Date(n.getTime()+1e3*t);break;case"n":n=new Date(n.getTime()+6e4*t);break;case"h":n=new Date(n.getTime()+36e5*t);break;case"d":n=new Date(n.getTime()+864e5*t);break;case"w":n=new Date(n.getTime()+6048e5*t);break;case"m":n=new Date(n.getFullYear(),n.getMonth()+t,n.getDate(),n.getHours(),n.getMinutes(),n.getSeconds());break;default:n=new Date(n.getFullYear()+t,n.getMonth(),n.getDate(),n.getHours(),n.getMinutes(),n.getSeconds())}return n=n.getTime()>=(new Date).getTime()?new Date:n,[n.getFullYear(),n.getMonth()+1,n.getDate()].join("/")}function u(e,t){var n=t,r=["日","一","二","三","四","五","六"];return n=n.replace(/yy/,e.getFullYear()),n=n.replace(/y/,e.getYear()%100>9?(e.getYear()%100).toString():"0"+e.getYear()%100),n=n.replace(/mm/,e.getMonth()>=9?(e.getMonth()+1).toString():"0"+(e.getMonth()+1)),n=n.replace(/m/g,e.getMonth()+1),n=n.replace(/w|W/g,r[e.getDay()]),n=n.replace(/dd/,e.getDate()>9?e.getDate().toString():"0"+e.getDate()),n=n.replace(/d/g,e.getDate()),n=n.replace(/hh/,e.getHours()>9?e.getHours().toString():"0"+e.getHours()),n=n.replace(/h/g,e.getHours()),n=n.replace(/mm/,e.getMinutes()>9?e.getMinutes().toString():"0"+e.getMinutes()),n=n.replace(/m/g,e.getMinutes()),n=n.replace(/ss/,e.getSeconds()>9?e.getSeconds().toString():"0"+e.getSeconds()),n=n.replace(/s/g,e.getSeconds())}return{bindEvents:e,getUrlParams:t,setToken:n,getToken:r,removeToken:a,base64Encode:o,trims:i,checkRagular:c,timeInterval:g,dateFormat:u}}),define("client/note/view/indexView",["client/note/model/indexModel","common/util"],function(e,t){function n(){r()}function r(){t.bindEvents([{el:"#query",event:"click",handler:function(){a()}},{el:"#queryParam",event:"keypress",handler:function(){13==event.keyCode&&($("#query").click(),event.preventDefault())}}])}function a(){return""==$("#queryParam").val().trim()?void window.location.reload():void $("#js-form").submit()}return{init:n}}),require(["client/note/view/indexView"],function(e){e.init()}),define("client/note/controller/indexController",function(){});