package com.nzh.community;

public class MyFunction {

    public static void pageCalculate(Integer totalPage, Integer totalCount, Integer page, Integer size){
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }


        if(page < 1) {
            page = 1;
        }

        if(page > totalPage) {
            page = totalPage;
        }
    }
}
