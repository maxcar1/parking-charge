package com.maxcar.base.util.dasouche;

/**
 * 返回给客户端的状态码和状态信息
 * 
 * @ClassName: ResultCode
 * @author huangxu
 * @date 2018年1月22日 下午2:33:33
 *
 */
public enum ResultCode {
	GET_SUCCESS(200, "获取成功"), 
	CREATE_SUCCESS(201, "新增数据成功"), 
	PUT_SUCCESS(202, "修改数据成功"), 
	DELETE_SUCCESS(203, "删除数据成功"), 
	CREATE_ERROR(400,"新增数据失败"), 
	PUT_ERROR(402,"修改数据失败"), 
	DELETE_ERROR(403,"删除数据失败"), 
	TOKEN_ERRO(401,"没有权限"), 
	NOT_FOUNT(404, "访问记录不存在"), 
	NOT_ACCEPTABLE(406, "参数格式错误"), 
	SERVICE_ERROR(500, "服务器发生错误"),
	ERROR(600, "错误");
	/**
	 * 返回状态码
	 */
	private int code;
	/**
	 * 返回状态信息
	 */
	private String msg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
