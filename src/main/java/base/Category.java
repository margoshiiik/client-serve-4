package base;

import lombok.Builder;

@Builder
public class Category {

    private String title;
    private String description;

    public Category(final String title, final String description){
        this.title = title;
        this.description = description;
    }


    @Override
    public String toString() {
        return "{\"title\" : \"" + title + "\"" +
                ",\"description\" : \"" + description + "\"}"+"\n";
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
}
