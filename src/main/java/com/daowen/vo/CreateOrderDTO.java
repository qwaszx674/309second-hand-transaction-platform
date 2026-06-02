package com.daowen.vo;

import java.util.List;

public class CreateOrderDTO {

    private int purchaser;
    private int publisher;
    private int receaddressid;
    private List<OrderItemInput> items;

    public int getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(int purchaser) {
        this.purchaser = purchaser;
    }

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public int getReceaddressid() {
        return receaddressid;
    }

    public void setReceaddressid(int receaddressid) {
        this.receaddressid = receaddressid;
    }

    public List<OrderItemInput> getItems() {
        return items;
    }

    public void setItems(List<OrderItemInput> items) {
        this.items = items;
    }

    public static class OrderItemInput {
        private int spid;
        private int quantity;
        private double price;

        public int getSpid() {
            return spid;
        }

        public void setSpid(int spid) {
            this.spid = spid;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}