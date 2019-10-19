package com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CostResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("costs")
    @Expose
    private List<Costs> costs = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Costs> getCosts() {
        return costs;
    }

    public void setCosts(List<Costs> costs) {
        this.costs = costs;
    }

    public class Costs {

        @SerializedName("service")
        @Expose
        private String service;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("cost")
        @Expose
        private List<Cost> cost = null;

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Cost> getCost() {
            return cost;
        }

        public void setCost(List<Cost> cost) {
            this.cost = cost;
        }


        public class Cost {

            @SerializedName("value")
            @Expose
            private Integer value;
            @SerializedName("etd")
            @Expose
            private String etd;
            @SerializedName("note")
            @Expose
            private String note;

            public Integer getValue() {
                return value;
            }

            public void setValue(Integer value) {
                this.value = value;
            }

            public String getEtd() {
                return etd;
            }

            public void setEtd(String etd) {
                this.etd = etd;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

        }
    }
}