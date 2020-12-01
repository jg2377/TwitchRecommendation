package com.laioffer.jupiter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
// 对于 json中有 Game中没有的field 就忽略 而不要抛出异常
@JsonInclude(JsonInclude.Include.NON_NULL)
// 对 null 的数据不convert
@JsonDeserialize(builder = Game.Builder.class)
// 创建 Game对象的时候使用 builder class 不要用其默认的 constructor
// @JsonIgnoreProperties(ignoreUnknown = true) indicates that other fields in the response can be safely ignored.
// Without this, you’ll get an exception at runtime.
// @JsonInclude(JsonInclude.Include.NON_NULL) indicates that null fields can be skipped and not included.
// @JsonDeserialize indicates that Jackson needs to use Game.Builder when constructing a Game object from JSON strings.
public class Game {
    // annotation  如果此处不写  会默认用 field name 来做mapping
//    @JsonProperty("name")
//    public String name;
//
//    @JsonProperty("developer")
//    public String developer;
//
//    @JsonProperty("release_time")
//    public String releaseTime;
//
//    @JsonProperty("website")
//    public String website;
//
//    @JsonProperty("price")
//    public double price;

//    public Game(String name, String developer, String releaseTime, String website, double price) {
//        this.name = name;
//        this.developer = developer;
//        this.releaseTime = releaseTime;
//        this.website = website;
//        this.price = price;
//    }

    // Note the @JsonProperty("") annotation is applied to the getter method instead of the field.
    // This is because the field is private and the way to get the data is by getter.
    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("box_art_url")
    private final String boxArtUrl;

    private Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.boxArtUrl = builder.boxArtUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("box_art_url")
        private String boxArtUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder boxArtUrl(String boxArtUrl) {
            this.boxArtUrl = boxArtUrl;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }


//    private final String name;    // 初始化后不能修改
//    private String developer;   // no modifier, package private
//    private String releaseDate;
//    private float price;
//
//
//    private Game(GameBuilder builder) {     // private constructor
//        this.name = builder.name;
//        this.developer = builder.developer;
//        this.releaseDate = builder.developer;
//        this.price = builder.price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDeveloper() {
//        return developer;
//    }
//
//    public String getReleaseDate() {
//        return releaseDate;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public static class GameBuilder {   // GameBuilder使用 不依赖于Game对象 需要是static
//        private String name;
//        private String developer;   // no modifier, package private
//        private String releaseDate;
//        private float price;
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public void setDeveloper(String developer) {
//            this.developer = developer;
//        }
//
//        public void setReleaseDate(String releaseDate) {
//            this.releaseDate = releaseDate;
//        }
//
//        public void setPrice(float price) {
//            this.price = price;
//        }
//
//        public Game build() {
//            return new Game(this);  // pass in GameBuilder object
//        }
//    }
}
// Game game = new Game("Vincent", "laioffer", "2020-11-28", "10");

// Game.GameBuilder builder = new Game.GameBuilder();
// or
// import com.laioffer.jupiter.entity.Game.GameBuilder;
// GameBuilder builder = new GameBuilder();

// builder.setName(" Vincent");
// builder.setDeveloper("laioffer");
// Game game = builder.build();

// Game game = new Game(builder);
