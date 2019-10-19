package com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrderResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("order")
    @Expose
    private Datum.Order order;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Datum.Order getOrder() {
        return order;
    }

    public void setOrder(Datum.Order order) {
        this.order = order;
    }


    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("orderType")
        @Expose
        private Integer orderType;
        @SerializedName("amount")
        @Expose
        private Integer amount;
        @SerializedName("shippingType")
        @Expose
        private String shippingType;
        @SerializedName("shippingFee")
        @Expose
        private Integer shippingFee;
        @SerializedName("shippingMethod")
        @Expose
        private String shippingMethod;
        @SerializedName("uniquePrice")
        @Expose
        private Integer uniquePrice;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("noteAdmin")
        @Expose
        private String noteAdmin;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("no_resi")
        @Expose
        private String noResi;
        @SerializedName("jenis_order")
        @Expose
        private String jenisOrder;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("expired_at")
        @Expose
        private String expiredAt;

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (!AllOrderResponse.Datum.class.isAssignableFrom(obj.getClass())) {
                return false;
            }

            final AllOrderResponse.Datum other = (AllOrderResponse.Datum) obj;
            if (this.id != other.id) {
                return false;
            }

            if (this.code != other.code) {
                return false;
            }

            if (this.userId != other.userId) {
                return false;
            }
            if (this.orderType != other.orderType) {
                return false;
            }
            if (this.amount != other.amount) {
                return false;
            }
            if (this.shippingType != other.shippingType) {
                return false;
            }
            if (this.shippingFee != other.shippingFee) {
                return false;
            }
            if (this.shippingMethod != other.shippingMethod) {
                return false;
            }

            if (this.uniquePrice != other.uniquePrice) {
                return false;
            }

            if (this.totalPrice != other.totalPrice) {
                return false;
            }

            if (this.note != other.note) {
                return false;
            }

            if (this.noteAdmin != other.noteAdmin) {
                return false;
            }
            if (this.status != other.status) {
                return false;
            }

            if (this.noResi != other.noResi) {
                return false;
            }

            if (this.jenisOrder != other.jenisOrder) {
                return false;
            }

            if (this.createdAt != other.createdAt) {
                return false;
            }

            if (this.updatedAt != other.updatedAt) {
                return false;
            }

            if (this.expiredAt != other.expiredAt) {
                return false;
            }

            return true;
        }
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getOrderType() {
            return orderType;
        }

        public void setOrderType(Integer orderType) {
            this.orderType = orderType;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getShippingType() {
            return shippingType;
        }

        public void setShippingType(String shippingType) {
            this.shippingType = shippingType;
        }

        public Integer getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(Integer shippingFee) {
            this.shippingFee = shippingFee;
        }

        public String getShippingMethod() {
            return shippingMethod;
        }

        public void setShippingMethod(String shippingMethod) {
            this.shippingMethod = shippingMethod;
        }

        public Integer getUniquePrice() {
            return uniquePrice;
        }

        public void setUniquePrice(Integer uniquePrice) {
            this.uniquePrice = uniquePrice;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNoteAdmin() {
            return noteAdmin;
        }

        public void setNoteAdmin(String noteAdmin) {
            this.noteAdmin = noteAdmin;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNoResi() {
            return noResi;
        }

        public void setNoResi(String noResi) {
            this.noResi = noResi;
        }

        public String getJenisOrder() {
            return jenisOrder;
        }

        public void setJenisOrder(String jenisOrder) {
            this.jenisOrder = jenisOrder;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getExpiredAt() {
            return expiredAt;
        }

        public void setExpiredAt(String expiredAt) {
            this.expiredAt = expiredAt;
        }


        public class Order {

            @SerializedName("current_page")
            @Expose
            private Integer currentPage;
            @SerializedName("data")
            @Expose
            private List<Datum> data = null;
            @SerializedName("from")
            @Expose
            private Integer from;
            @SerializedName("last_page")
            @Expose
            private Integer lastPage;
            @SerializedName("next_page_url")
            @Expose
            private String nextPageUrl;
            @SerializedName("path")
            @Expose
            private String path;
            @SerializedName("per_page")
            @Expose
            private Integer perPage;
            @SerializedName("prev_page_url")
            @Expose
            private String prevPageUrl;
            @SerializedName("to")
            @Expose
            private Integer to;
            @SerializedName("total")
            @Expose
            private Integer total;

            public Integer getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(Integer currentPage) {
                this.currentPage = currentPage;
            }

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
            }

            public Integer getFrom() {
                return from;
            }

            public void setFrom(Integer from) {
                this.from = from;
            }

            public Integer getLastPage() {
                return lastPage;
            }

            public void setLastPage(Integer lastPage) {
                this.lastPage = lastPage;
            }

            public String getNextPageUrl() {
                return nextPageUrl;
            }

            public void setNextPageUrl(String nextPageUrl) {
                this.nextPageUrl = nextPageUrl;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public Integer getPerPage() {
                return perPage;
            }

            public void setPerPage(Integer perPage) {
                this.perPage = perPage;
            }

            public String getPrevPageUrl() {
                return prevPageUrl;
            }

            public void setPrevPageUrl(String prevPageUrl) {
                this.prevPageUrl = prevPageUrl;
            }

            public Integer getTo() {
                return to;
            }

            public void setTo(Integer to) {
                this.to = to;
            }

            public Integer getTotal() {
                return total;
            }

            public void setTotal(Integer total) {
                this.total = total;
            }

        }
    }
}