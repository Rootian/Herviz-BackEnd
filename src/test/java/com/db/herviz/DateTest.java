package com.db.herviz;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: Weiqi Chen
 * @Date: 2022/4/30 15:38
 */
public class DateTest {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfMonth(1);
        Map<String, Integer> sumRevenueByMonth = new HashMap<>();
        for (int i = 0; i < 12; i++) {

            sumRevenueByMonth.put(startDate.format(DateTimeFormatter.ofPattern("YYYY-MM")), 0);
            startDate = startDate.plusMonths(1);
        }
        System.out.println(sumRevenueByMonth);

        //for (int i = 0; i < 12; i++) {
        //
        //    System.out.println(startDate + "=to=" + endDate);
        //    System.out.println(startDate.format(DateTimeFormatter.ofPattern("YYYY-MM")));
        //
        //    startDate = startDate.plusMonths(1);
        //    endDate = endDate.plusMonths(1);
        //}

    }

    @Test
    public void test() throws Exception{
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM"));
        System.out.println(format);
    }
}
