package br.com.devmedia.helloworldservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.devmedia.helloworldservice.schema.Nome;
import br.com.devmedia.helloworldservice.schema.Saudacao;
import br.com.devmedia.helloworldservice.wsdl.DigaOlaException;
import br.com.devmedia.helloworldservice.wsdl.DigaOlaPortType;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldServiceTest {
	
	@Autowired
	DigaOlaPortType port;

	@Test
	public void requestResponseTest() throws Exception {
        Nome _digaOla_parametro = new Nome();
        try {
        	_digaOla_parametro.setConteudo("ze");
        	Saudacao ret = port.digaOla(_digaOla_parametro);
            Assert.assertEquals("Bom dia", ret.getConteudo());
        } catch (DigaOlaException e) { 
            System.out.println("Expected exception: DigaOlaException has occurred.");
            System.out.println(e.toString());
        }

        try {
        	_digaOla_parametro.setConteudo("maria");
        	Saudacao ret = port.digaOla(_digaOla_parametro);
            Assert.assertEquals("Ola", ret.getConteudo());
        } catch (DigaOlaException e) { 
            System.out.println("Expected exception: DigaOlaException has occurred.");
            System.out.println(e.toString());
        }
        try {
        	_digaOla_parametro.setConteudo("anonimo");
        	Saudacao ret = port.digaOla(_digaOla_parametro);
        	Assert.assertTrue(false);
        } catch (DigaOlaException e) { 
        	Assert.assertEquals("ERR_01",e.getFaultInfo().getConteudo());
            System.out.println("Expected exception: DigaOlaException has occurred.");
            System.out.println(e.toString());
        }
	}
}
