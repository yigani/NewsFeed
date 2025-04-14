package com.example.NewsFeed.config;

// 성별 상수 설정 클래스
public enum Gender {
    M(0), F(1);

    private final int code;

    Gender(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }

    // 성별 입력 확인
    public static Gender fromInt(int code) {
        if (code == 0) {
            return M;
        } else if (code == 1) {
            return F;
        } else {
            throw new IllegalArgumentException("성별은 M 또는 F만 가능합니다.");
        }
    }
}
