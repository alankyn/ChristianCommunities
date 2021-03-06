package ccomunities.alashka.com.ccommunities_dev.Model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ALANKIN on 10/10/16.
 */
public class User extends SugarRecord implements Serializable {

    String username;
    String password;
    String name;
    String lastName;
    String email;
    String pathPhoto;
    String created_at;
    String updated_at;
    Community community;
    @Ignore
    Long community_id;

    public User() {
    }

    public User(String username, String name, String lastName, String email, String pathPhoto, Community community, String password, String created_at, String updated_at) {
    }

    public User(String username, String password, String name, String lastName, String email, String pathPhoto, String created_at, String updated_at, Long community_id) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pathPhoto = pathPhoto;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.community_id = community_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }

    public List<UserAchievement> getAchievements() {
        return UserAchievement.find(UserAchievement.class, "user = ?", String.valueOf(this.getId()));
    }

    public List<Comment> getComments() {
        return Comment.find(Comment.class, "user = ?", String.valueOf(this.getId()));
    }

    public List<Publication> getPublications() {
        return Publication.find(Publication.class, "user = ?", String.valueOf(this.getId()));
    }

    public List<Rate> getRates() {
        return Rate.find(Rate.class, "user = ?", String.valueOf(this.getId()));
    }
}
