package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;
public enum Transaction {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");
    private final String transactionName;
    Transaction(String transactionName) {
        this.transactionName = transactionName;
    }
    public String getTransactionName() {
        return transactionName;
    }
    public static Map<String, String> getTransaction(){
        Map<String, String> map = new LinkedHashMap<>();
        for (Transaction transaction : Transaction.values()) {
            map.put(transaction.getTransactionName(), transaction.getTransactionName());
        }
        return map;
    }
}
