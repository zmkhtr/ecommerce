package com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckoutGlobalResponse implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("product")
    @Expose
    private Address.Product product;
    @SerializedName("address")
    @Expose
    private Address address;

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

    public Address.Product getProduct() {
        return product;
    }

    public void setProduct(Address.Product product) {
        this.product = product;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public class Address {

        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("negara")
        @Expose
        private String negara;
        @SerializedName("provinsi")
        @Expose
        private String provinsi;
        @SerializedName("kota")
        @Expose
        private String kota;
        @SerializedName("alamat")
        @Expose
        private String alamat;
        @SerializedName("postal_code")
        @Expose
        private String postalCode;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public String getNegara() {
            return negara;
        }

        public void setNegara(String negara) {
            this.negara = negara;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }

        public String getKota() {
            return kota;
        }

        public void setKota(String kota) {
            this.kota = kota;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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


        public class Product {

            @SerializedName("userId")
            @Expose
            private Integer userId;
            @SerializedName("code")
            @Expose
            private String code;
            @SerializedName("orderType")
            @Expose
            private Integer orderType;
            @SerializedName("amount")
            @Expose
            private String amount;
            @SerializedName("shippingType")
            @Expose
            private String shippingType;
            @SerializedName("shippingFee")
            @Expose
            private String shippingFee;
            @SerializedName("shippingMethod")
            @Expose
            private String shippingMethod;
            @SerializedName("uniquePrice")
            @Expose
            private Integer uniquePrice;
            @SerializedName("totalPrice")
            @Expose
            private Integer totalPrice;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("jenis_order")
            @Expose
            private String jenisOrder;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("id")
            @Expose
            private Integer id;

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public Integer getOrderType() {
                return orderType;
            }

            public void setOrderType(Integer orderType) {
                this.orderType = orderType;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getShippingType() {
                return shippingType;
            }

            public void setShippingType(String shippingType) {
                this.shippingType = shippingType;
            }

            public String getShippingFee() {
                return shippingFee;
            }

            public void setShippingFee(String shippingFee) {
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getJenisOrder() {
                return jenisOrder;
            }

            public void setJenisOrder(String jenisOrder) {
                this.jenisOrder = jenisOrder;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.error);
        dest.writeValue(this.status);
        dest.writeParcelable((Parcelable) this.product, flags);
        dest.writeParcelable((Parcelable) this.address, flags);
    }

    public CheckoutGlobalResponse() {
    }

    protected CheckoutGlobalResponse(Parcel in) {
        this.error = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.product = in.readParcelable(Address.Product.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Parcelable.Creator<CheckoutGlobalResponse> CREATOR = new Parcelable.Creator<CheckoutGlobalResponse>() {
        @Override
        public CheckoutGlobalResponse createFromParcel(Parcel source) {
            return new CheckoutGlobalResponse(source);
        }

        @Override
        public CheckoutGlobalResponse[] newArray(int size) {
            return new CheckoutGlobalResponse[size];
        }
    };
}