package com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder;


import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteDetailData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("order")
    @Expose
    private List<Order> order = null;

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

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public class Order {

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

            if (!OrderResponse.Order.class.isAssignableFrom(obj.getClass())) {
                return false;
            }

            final OrderResponse.Order other = (OrderResponse.Order) obj;
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


    }
}