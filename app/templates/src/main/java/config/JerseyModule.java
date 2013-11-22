package <%= packageName %>.config;

import java.util.HashMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class JerseyModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        
        // bind jackson converters for JAXB/JSON serialization 
        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

        //
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("com.sun.jersey.config.property.packages", "<%= packageName %>.rest");
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/rest/*").with(GuiceContainer.class, params);
    }
}
