package tybca.sem6.android.model;

public class usersModel {
    int id;
    String username;
    String contact;
    String email;
    String updated_at;

    public usersModel(int id, String username, String contact, String email, String updated_at) {
        this.id = id;
        this.username = username;
        this.contact = contact;
        this.email = email;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
