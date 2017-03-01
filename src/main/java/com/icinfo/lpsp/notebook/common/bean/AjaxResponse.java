/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author YuShunWei
 * @date 2016年3月26日
 * @version 1.0
 */
package com.icinfo.lpsp.notebook.common.bean;

import java.io.Serializable;

/**
 * 描述: ajax响应 .<br>
 *
 * @author YuShunWei
 * @date 2016年3月26日
 */
public class AjaxResponse<T> implements Serializable {

	private static final long serialVersionUID = -2021616574750699040L;

	/**
	 * 成功状态编码 .
	 */
	public static final String SUCCESS_STATUS = "200";

	/**
	 * 操作成功 .
	 */
	public static final String SUCCESS_MSG = "操作成功";

	/**
	 * 失败状态编码 .
	 */
	public static final String ERROR_STATUS = "100";

	/**
	 * 操作失败 .
	 */
	public static final String ERROR_MSG = "操作失败";

	/**
	 * 状态码.
	 */
	private String status;

	/**
	 * 状态码消息 .
	 */
	private String msg;

	/**
	 * 子状态编码，返回错误才进行设置
	 */
	private String subStatus;

	/**
	 * 子状态消息 ，返回错误才进行设置
	 */
	private String subMsg;

	/**
	 * 业务数据
	 */
	private T data;

	public AjaxResponse() {
	}

	/**
	 * 自定义构造函数，适用操作成功的场景
	 *
	 * @param data 业务数据
	 */
	public AjaxResponse(T data) {
		this.status = SUCCESS_STATUS;
		this.msg = SUCCESS_MSG;
		this.data = data;
	}

	/**
	 * 自定义构造函数，适用操作失败的场景
	 *
	 * @param subStatus 子状态码
	 * @param subMsg    子状态消息
	 */
	public AjaxResponse(String subStatus, String subMsg) {
		this.status = ERROR_STATUS;
		this.msg = ERROR_MSG;
		this.subStatus = subStatus;
		this.subMsg = subMsg;
	}

	/**
	 * 设置成功
	 *
	 * @param t 业务数据
	 */
	public void success(T t) {
		this.status = SUCCESS_STATUS;
		this.msg = SUCCESS_MSG;
		this.setData(t);
	}

	/**
	 * 设置失败
	 */
	public void failure(String subStatus, String subMsg) {
		this.status = ERROR_STATUS;
		this.msg = ERROR_MSG;
		this.subStatus = subStatus;
		this.subMsg = subMsg;
	}

	/**
	 * 判断是否为成功状态
	 *
	 * @return 成功true，失败false
	 */
	public boolean ok() {
		return SUCCESS_STATUS.equals(this.status);
	}

	/**
	 * 获取状态码信息
	 *
	 * @return 状态信息
	 */
	public String statusInfo() {
		return this.getStatus() + ":" + this.getMsg();
	}

	/**
	 * 获取错误子状态信息
	 *
	 * @return 子状态信息
	 */
	public String subStatusInfo() {
		return this.getSubStatus() + ":" + this.getSubMsg();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
