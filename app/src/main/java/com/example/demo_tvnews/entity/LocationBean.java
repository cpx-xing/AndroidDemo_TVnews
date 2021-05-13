package com.example.demo_tvnews.entity;

import com.google.gson.annotations.SerializedName;

public class LocationBean {

    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private ResultDTO result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public static class ResultDTO {
        @SerializedName("location")
        private LocationDTO location;
        @SerializedName("formatted_address")
        private String formattedAddress;
        @SerializedName("business")
        private String business;
        @SerializedName("addressComponent")
        private AddressComponentDTO addressComponent;
        @SerializedName("cityCode")
        private Integer cityCode;

        public LocationDTO getLocation() {
            return location;
        }

        public void setLocation(LocationDTO location) {
            this.location = location;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public AddressComponentDTO getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentDTO addressComponent) {
            this.addressComponent = addressComponent;
        }

        public Integer getCityCode() {
            return cityCode;
        }

        public void setCityCode(Integer cityCode) {
            this.cityCode = cityCode;
        }

        public static class LocationDTO {
            @SerializedName("lng")
            private Double lng;
            @SerializedName("lat")
            private Double lat;

            public Double getLng() {
                return lng;
            }

            public void setLng(Double lng) {
                this.lng = lng;
            }

            public Double getLat() {
                return lat;
            }

            public void setLat(Double lat) {
                this.lat = lat;
            }
        }

        public static class AddressComponentDTO {
            @SerializedName("city")
            private String city;
            @SerializedName("direction")
            private String direction;
            @SerializedName("distance")
            private String distance;
            @SerializedName("district")
            private String district;
            @SerializedName("province")
            private String province;
            @SerializedName("street")
            private String street;
            @SerializedName("street_number")
            private String streetNumber;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreetNumber() {
                return streetNumber;
            }

            public void setStreetNumber(String streetNumber) {
                this.streetNumber = streetNumber;
            }
        }
    }
}
