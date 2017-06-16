package com.kshrd.articlecms.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pirang on 6/14/17.
 */

public class ArticleResponse {

    @SerializedName("CODE")
    private String code;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("DATA")
    private List<Article> articlelist;
    @SerializedName("PAGINATION")
    private Pagination pagination;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Article> getArticlelist() {
        return articlelist;
    }

    public void setArticlelist(List<Article> articlelist) {
        this.articlelist = articlelist;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public static class Author implements Parcelable {
        @SerializedName("ID")
        private int id;
        @SerializedName("NAME")
        private String name;
        @SerializedName("EMAIL")
        private String email;
        @SerializedName("GENDER")
        private String gender;
        @SerializedName("TELEPHONE")
        private String telephone;
        @SerializedName("STATUS")
        private String status;
        @SerializedName("FACEBOOK_ID")
        private String facebookId;
        @SerializedName("IMAGE_URL")
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(String facebookId) {
            this.facebookId = facebookId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.email);
            dest.writeString(this.gender);
            dest.writeString(this.telephone);
            dest.writeString(this.status);
            dest.writeString(this.facebookId);
            dest.writeString(this.imageUrl);
        }

        public Author() {
        }

        protected Author(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.email = in.readString();
            this.gender = in.readString();
            this.telephone = in.readString();
            this.status = in.readString();
            this.facebookId = in.readString();
            this.imageUrl = in.readString();
        }

        public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
            @Override
            public Author createFromParcel(Parcel source) {
                return new Author(source);
            }

            @Override
            public Author[] newArray(int size) {
                return new Author[size];
            }
        };

        @Override
        public String toString() {
            return "Author{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", gender='" + gender + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", status='" + status + '\'' +
                    ", facebookId='" + facebookId + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    '}';
        }
    }

    public static class Category implements Parcelable {
        @SerializedName("ID")
        private int id;
        @SerializedName("NAME")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
        }

        public Category() {
        }

        protected Category(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
        }

        public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
            @Override
            public Category createFromParcel(Parcel source) {
                return new Category(source);
            }

            @Override
            public Category[] newArray(int size) {
                return new Category[size];
            }
        };
    }

    public static class Article implements Parcelable {
        @SerializedName("ID")
        private int id;
        @SerializedName("TITLE")
        private String title;
        @SerializedName("DESCRIPTION")
        private String description;
        @SerializedName("CREATED_DATE")
        private String createdDate;
        @SerializedName("AUTHOR")
        private Author author;
        @SerializedName("STATUS")
        private String status;
        @SerializedName("CATEGORY")
        private Category category;
        @SerializedName("IMAGE")
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.description);
            dest.writeString(this.createdDate);
            dest.writeParcelable(this.author, flags);
            dest.writeString(this.status);
            dest.writeParcelable(this.category, flags);
            dest.writeString(this.image);
        }

        public Article() {
        }

        protected Article(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.description = in.readString();
            this.createdDate = in.readString();
            this.author = in.readParcelable(Author.class.getClassLoader());
            this.status = in.readString();
            this.category = in.readParcelable(Category.class.getClassLoader());
            this.image = in.readString();
        }

        public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
            @Override
            public Article createFromParcel(Parcel source) {
                return new Article(source);
            }

            @Override
            public Article[] newArray(int size) {
                return new Article[size];
            }
        };

        @Override
        public String toString() {
            return "Article{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", createdDate='" + createdDate + '\'' +
                    ", author=" + author +
                    ", status='" + status + '\'' +
                    ", category=" + category +
                    ", image='" + image + '\'' +
                    '}';
        }
    }

    public static class Pagination {
        @SerializedName("PAGE")
        private int page;
        @SerializedName("LIMIT")
        private int limit;
        @SerializedName("TOTAL_COUNT")
        private int totalCount;
        @SerializedName("TOTAL_PAGES")
        private int totalPages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", articlelist=" + articlelist +
                ", pagination=" + pagination +
                '}';
    }
}
