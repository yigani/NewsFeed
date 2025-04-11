package com.example.NewsFeed.config;

public enum Gender {
   M, F;

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
