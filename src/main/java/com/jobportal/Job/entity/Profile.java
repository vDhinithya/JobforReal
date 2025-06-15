package com.jobportal.Job.entity;

import com.jobportal.Job.dto.Certification;
import com.jobportal.Job.dto.Experience;
import com.jobportal.Job.dto.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private byte[] picture;
    private Long totalExp;
    private List<String> skills;
    private List<Experience> experiences  = new ArrayList<>();
    private List<Certification> certifications;
    private List<Long> savedJobs;

    public ProfileDTO toDto() {
        return new ProfileDTO(this.id, this.name, this.email, this.jobTitle, this.company, this.location, this.about, this.picture != null ? Base64.getEncoder().encodeToString(this.picture) : null, this.totalExp, this.skills, this.experiences, this.certifications, this.savedJobs);
    }
}
