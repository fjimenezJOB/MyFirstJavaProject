package com.netmind.model;

public enum EnumStudents {

     ADD_STUDENT(1), CALCULATE_OLDST_STUDENT(2), CALCULATE_AVERAGE_AGE(3), UPDATE_STUDENT(4), EXIT(5);

    private int value;

	private EnumStudents(int value) {
		this.value = value;
	}

	public static EnumStudents fromValue(int value) {
		for (EnumStudents my : EnumStudents.values()) {
			if (my.value == value) {
				return my;
			}
		}

		return null;
	}

	public int value() {
		return value;
	}

}
