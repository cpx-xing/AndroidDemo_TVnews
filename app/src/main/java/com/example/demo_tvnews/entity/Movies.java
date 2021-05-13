package com.example.demo_tvnews.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {

    @SerializedName("status")
    private Integer status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("result")
    private ResultDTO result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public static class ResultDTO {
        @SerializedName("city")
        private String city;
        @SerializedName("cityid")
        private String cityid;
        @SerializedName("date")
        private String date;
        @SerializedName("list")
        private List<ListDTO> list;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<ListDTO> getList() {
            return list;
        }

        public void setList(List<ListDTO> list) {
            this.list = list;
        }

        public static class ListDTO {
            @SerializedName("movieid")
            private String movieid;
            @SerializedName("moviename")
            private String moviename;
            @SerializedName("pic")
            private String pic;

            public String getMovieid() {
                return movieid;
            }

            public void setMovieid(String movieid) {
                this.movieid = movieid;
            }

            public String getMoviename() {
                return moviename;
            }

            public void setMoviename(String moviename) {
                this.moviename = moviename;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
