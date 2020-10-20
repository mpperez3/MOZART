package es.uvigo.ei.sing.MOZART.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.uvigo.ei.sing.MOZART.utils.Functions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "smile_descriptors",
        indexes = {@Index(columnList = ("created"), name = "created", unique = false)},
        uniqueConstraints = @UniqueConstraint(columnNames = {"hash"})
)
public class SmileDescriptorsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "smile", columnDefinition = "LONGTEXT")
    private String smile;


    @Basic
    @Column(name = "hash")
    private String hash;


    @Basic
    @Column(name = "created")
    private LocalDateTime created;


    @Basic
    @Column(name = "json_descriptors", columnDefinition = "LONGTEXT")
    private String jsonDescriptors;


    public SmileDescriptorsEntity(String smile, Map<String, String> descriptors) {
        this.smile = smile.trim();
        this.hash = this.smile;
        try {
            this.hash = Functions.makeSHA1Hash(smile);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.created = LocalDateTime.now();
        Gson gson = new GsonBuilder().create();

        this.jsonDescriptors = gson.toJson(descriptors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmileDescriptorsEntity that = (SmileDescriptorsEntity) o;
        return id == that.id &&
                Objects.equals(smile, that.smile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, smile);
    }

    public void setSmile(String smile) {
        this.smile = smile.trim();
        this.hash = this.smile;
        try {
            this.hash = Functions.makeSHA1Hash(smile);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
