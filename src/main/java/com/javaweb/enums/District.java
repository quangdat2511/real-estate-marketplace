package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum District {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8"),
    QUAN_9("Quận 9"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_12("Quận 12"),
    QUAN_BINH_THANH("Quận Bình Thạnh"),
    QUAN_BINH_TAN("Quận Bình Tân"),
    QUAN_TAN_BINH("Quận Tân Bình"),
    QUAN_TAN_PHU("Quận Tân Phú"),
    QUAN_PHU_NHUAN("Quận Phú Nhuận"),
    QUAN_GO_VAP("Quận Gò Vấp"),

    HUYEN_BINH_CHANH("Huyện Bình Chánh"),
    HUYEN_CAN_GIO("Huyện Cần Giờ"),
    HUYEN_CU_CHI("Huyện Củ Chi"),
    HUYEN_HOC_MON("Huyện Hóc Môn"),
    HUYEN_NHA_BE("Huyện Nhà Bè"),

    THANH_PHO_THU_DUC("Thành phố Thủ Đức");

    private final String districtName;
    District(String districtName) {
        this.districtName = districtName;
    }
    public static Map<String, String>getDistrict(){
        Map<String, String> districts = new LinkedHashMap<>();
        for (District district:District.values()){
            districts.put(district.toString(), district.districtName);
        }
        return districts;
    }
    public static String getDistrictName(String district) {
        if (district != null && !district.isEmpty()){
            return District.valueOf(district).districtName;
        }
        return null;
    }

}