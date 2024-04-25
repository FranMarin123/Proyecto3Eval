package com.github.FranMarin123.model.entity;

import java.io.File;
import java.util.Objects;

public class Activity {
    private int idActivity;
    private String name;
    private String description;
    private File mediaFile;
    private int percent;
    private Subject subject;
    private Inscription inscription;

    public Activity(int idActivity, String name, String description, String mediaFile, int percent, Subject subject, Inscription inscription) {
        this.idActivity = idActivity;
        this.name = name;
        this.description = description;
        setMediaFile(mediaFile);
        this.percent = percent;
        this.subject = subject;
        this.inscription = inscription;
    }

    public Activity() {
        this(-1, "", "", "", -1, new Subject(), new Inscription());
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(String mediaFileURL) {
        this.mediaFile = new File(mediaFileURL);
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Activity activity = (Activity) object;
        return idActivity == activity.idActivity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivity);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "idActivity=" + idActivity +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", percent=" + percent +
                ", subject=" + subject +
                '}';
    }
}
