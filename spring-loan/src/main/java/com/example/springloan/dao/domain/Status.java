package com.example.springloan.dao.domain;

public enum Status {
   ACCEPT(0), PROCESS(1), FINISH(2),CANCELLED(3);
   private int value;

   Status(int value) {
      this.value = value;
   }

   public int getValue() {
      return value;
   }
}
