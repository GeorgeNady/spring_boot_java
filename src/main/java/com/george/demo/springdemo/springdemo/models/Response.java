package com.george.demo.springdemo.springdemo.models;

import java.util.List;

public abstract class Response {

    public static class ResList<T> {
        Boolean status;
        List<T> data;
        String message;
        long count;

        public ResList(Boolean status, List<T> data, String message, long count) {
            this.status = status;
            this.data = data;
            this.message = message;
            this.count = count;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public long getCount() {return count;}

        public void setCount(long count) {this.count = count;}
    }

    public static class ResSingle<T> {
        Boolean status;
        T data;
        String message;

        public ResSingle(Boolean status, T data, String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}