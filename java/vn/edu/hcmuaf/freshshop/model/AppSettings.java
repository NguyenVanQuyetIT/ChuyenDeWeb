package vn.edu.hcmuaf.freshshop.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class AppSettings {
    @Value(value = "nonglamfreshshop@gmail.com")
    private String email;
}

