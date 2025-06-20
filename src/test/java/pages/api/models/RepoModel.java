package pages.api.models;


import com.google.gson.annotations.SerializedName;

public class RepoModel {
    private String name;
    private String description;

    @SerializedName("private")
    private boolean isPrivate;
    public RepoModel(String name, String description, boolean isPrivate){
        this.description = description;
        this.name = name;
        this.isPrivate = isPrivate;
    }
}