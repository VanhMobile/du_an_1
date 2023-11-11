package com.example.du_an_1.funtions;



public class IdGenerator {

    // hàm tạo id shop tự động dùng biến count cho id tặng tự động
    public static String generateNextShopId(int count, String s) {
        String Id = s + ++count;
        return Id;
    }

}
