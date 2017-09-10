package com.junhangxintong.chuanzhangtong.mine.Utils;

public enum RoleEnum {
	ADMIN(1, "管理员"), SHIPMASTER(2, "船长"), SHIPMEMBER(3, "船员"), OTHER(4, "其他");

	RoleEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String descOf(int value) {
		RoleEnum[] settings = RoleEnum.values();
		for (RoleEnum flag : settings) {
			if (flag.getCode() == value) {
				return flag.getDesc();
			}
		}
		return null;
	}
}
