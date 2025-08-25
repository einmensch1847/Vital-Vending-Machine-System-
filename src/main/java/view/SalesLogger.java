package view;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SalesLogger {

    private static final String FILE_NAME = "sales_log.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // قیمت محصولات
    private static final Map<String, Integer> prices = Map.of(
            "B1", 10000,
            "B2", 12000,
            "B3", 15000,
            "B4", 11000,
            "B5", 13000,
            "B6", 18000
    );

    // متد اصلی لاگ‌گیری
    public static void logSale(String productCode) {
        Map<String, SaleRecord> salesMap = loadSales();

        SaleRecord record = salesMap.getOrDefault(
                productCode,
                new SaleRecord(productCode, prices.getOrDefault(productCode, 0), 0, "", 0)
        );

        record.quantity++;
        record.lastOrderTime = LocalDateTime.now().format(formatter);
        record.totalRevenue = record.quantity * record.price;

        salesMap.put(productCode, record);

        saveSales(salesMap);
    }

    // خواندن فایل قبلی فروش
    private static Map<String, SaleRecord> loadSales() {
        Map<String, SaleRecord> map = new HashMap<>();

        if (!Files.exists(Paths.get(FILE_NAME))) return map;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            reader.readLine(); // رد کردن عنوان ستون‌ها
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String code = parts[0];
                    int price = Integer.parseInt(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    String time = parts[3];
                    int totalRevenue = Integer.parseInt(parts[4]);
                    map.put(code, new SaleRecord(code, price, quantity, time, totalRevenue));
                }
            }
        } catch (IOException e) {
            System.err.println("خطا در خواندن فایل فروش: " + e.getMessage());
        }

        return map;
    }

    // ذخیره در فایل
    private static void saveSales(Map<String, SaleRecord> salesMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("Product,Price,Quantity,LastOrderTime,TotalRevenue\n");
            for (SaleRecord record : salesMap.values()) {
                writer.write(String.format(
                        "%s,%d,%d,%s,%d\n",
                        record.productCode,
                        record.price,
                        record.quantity,
                        record.lastOrderTime,
                        record.totalRevenue
                ));
            }
        } catch (IOException e) {
            System.err.println("خطا در ذخیره فایل فروش: " + e.getMessage());
        }
    }

    // کلاس داخلی برای نگهداری اطلاعات هر محصول
    private static class SaleRecord {
        String productCode;
        int price;
        int quantity;
        String lastOrderTime;
        int totalRevenue;

        SaleRecord(String productCode, int price, int quantity, String lastOrderTime, int totalRevenue) {
            this.productCode = productCode;
            this.price = price;
            this.quantity = quantity;
            this.lastOrderTime = lastOrderTime;
            this.totalRevenue = totalRevenue;
        }
    }
    public static void clearSales() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("Product,Price,Quantity,LastOrderTime,TotalRevenue\n");

            for (Map.Entry<String, Integer> entry : prices.entrySet()) {
                String code = entry.getKey();
                int price = entry.getValue();
                writer.write(String.format("%s,%d,0,,0\n", code, price));
            }

            System.out.println("فروش‌ها با موفقیت صفر شدند.");
        } catch (IOException e) {
            System.err.println("خطا در صفر کردن فروش‌ها: " + e.getMessage());
        }
    }

}
