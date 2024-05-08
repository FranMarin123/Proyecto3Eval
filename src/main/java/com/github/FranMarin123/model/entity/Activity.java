package com.github.FranMarin123.model.entity;

import java.io.File;
import java.util.Objects;

public class Activity {
    private int id;
    private String name;
    private String description;
    private File mediaFile;
    private int percent;
    private Subject subject;
    private Inscription inscription;

    public Activity(int idActivity, String name, String description, String mediaFile, int percent, Subject subject, Inscription inscription) {
        this.id = idActivity;
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
        return id == activity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "idActivity=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", percent=" + percent +
                ", subject=" + subject +
                '}';
    }
}
