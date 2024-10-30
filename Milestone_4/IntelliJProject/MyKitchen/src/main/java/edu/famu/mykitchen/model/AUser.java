package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import edu.famu.mykitchen.util.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.text.ParseException;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AUser {
    @DocumentId
    private @Nullable String userId;
    private PersonalInfo userInfo;
    private String profilePic;
    private String bio;
    private String username;
    private boolean isPrivate;
    private boolean isVerified;
    private boolean isAdministrator;
    private Timestamp joinedOn;


    public void setJoinedOn(String joinedOn) throws ParseException {
        this.joinedOn = Timestamp.fromProto(Timestamps.parse(joinedOn));
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
