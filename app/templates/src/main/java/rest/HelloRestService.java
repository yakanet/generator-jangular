package <%= packageName %>.rest;

import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/hello")
@Singleton
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class HelloRestService {

    @GET
    public HelloObject sayHello() {
        return new HelloObject();
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    static class HelloObject {
        public String date;
        @XmlElement(name = "java_version")
        public String javaVersion;
        @XmlElement(name = "os_name")
        public String osName;
        @XmlElement(name = "user_home")
        public String userHome;
        
        public HelloObject() {
            this.date = new Date().toString();
            this.javaVersion = System.getProperty("java.version");
            this.osName = System.getProperty("os.name");
            this.userHome = System.getProperty("user.home");
        }
    }
}
