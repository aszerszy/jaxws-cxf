package com.codenotfound.soap.http.cxf;

import static org.junit.Assert.assertEquals;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codenotfound.services.helloworld.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/cxf-client.xml" })
public class HelloWorldClientImplTest {

    private static String ENDPOINT_ADDRESS = "http://localhost:9090/cnf/services/helloworld";

    @Autowired
    private HelloWorldClientImpl helloWorldClientImplBean;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
        jaxWsServerFactoryBean.setServiceBean(new HelloWorldServerImplMock());
        jaxWsServerFactoryBean.setAddress(ENDPOINT_ADDRESS);
        jaxWsServerFactoryBean.create();
    }

    @Test
    public void testSayHello() {
        Person person = new Person();
        person.setFirstName("Sherlock");
        person.setLastName("Holmes");

        assertEquals("Hello Sherlock Holmes!",
                helloWorldClientImplBean.sayHello(person));
    }
}
