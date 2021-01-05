package com.laioffer.jupiter.entity;

//Alias的目的是match多个名字成同一个property
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//忽略没有见过的数据data，JSON have extra field than class。比如viewercart，写了ignoreUnknown = true就是忽略而不是抛出异常
@JsonIgnoreProperties(ignoreUnknown = true)
//proepry是null的时候返回不会发生出现"key：空白"的情况。忽略null，null不convert
@JsonInclude(JsonInclude.Include.NON_NULL)
//initialize时候初始化item的对象。用builder代替constructor。注意builder不是jackson
@JsonDeserialize(builder = Item.Builder.class)

public class Item {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("thumbnail_url")
    private final String thumbnailUrl;

    @JsonProperty("broadcaster_name")
    @JsonAlias({ "user_name" })

    private String broadcasterName;

    @JsonProperty("url")
    private String url;

    @JsonProperty("game_id")
    private String gameId;

    @JsonProperty("item_type")
    private ItemType type;

    private Item(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.url = builder.url;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.broadcasterName = builder.broadcasterName;
        this.gameId = builder.gameId;
        this.type = builder.type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getBroadcasterName() {
        return broadcasterName;
    }

    public void setBroadcasterName(String broadcasterName) {
        this.broadcasterName = broadcasterName;
    }

    public String getUrl() {
        return url;
    }

    public Item setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public ItemType getType() {
        return type;
    }

    public Item setType(ItemType type) {
        this.type = type;
        return this;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("url")
        private String url;

        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;

        @JsonProperty("broadcaster_name")
        @JsonAlias({ "user_name" })
        private String broadcasterName;

        @JsonProperty("game_id")
        private String gameId;

        @JsonProperty("item_type")
        private ItemType type;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder thumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder broadcasterName(String broadcasterName) {
            this.broadcasterName = broadcasterName;
            return this;
        }

        public Builder gameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder type(ItemType type) {
            this.type = type;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}