package com.example.NewsFeed.config;

// 성별 상수 설정 클래스
public enum Gender {
   M, F;

   // 성별 입력 확인
   public static Gender fromInt(int gender) {
       if (gender == 1) {
           return M;
       } else if (gender == 2){
           return F;
       } else {
           throw new IllegalArgumentException("성별은 1 또는 2만 가능합니다.");
       }
   }

}
