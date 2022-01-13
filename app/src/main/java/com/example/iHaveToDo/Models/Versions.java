package com.example.iHaveToDo.Models;


public class Versions  {
    public String codeName,Version,apiLevel,Description;
    public boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Versions(String codeName, String version, String apiLevel, String description) {
        this.codeName = codeName;
        Version = version;
        this.apiLevel = apiLevel;
        Description = description;
        this.expandable = false;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String versionn) {
        Version = versionn;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Versions{" +
                "codeName='" + codeName + '\'' +
                ", Versionn='" + Version + '\'' +
                ", apiLevel='" + apiLevel + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
