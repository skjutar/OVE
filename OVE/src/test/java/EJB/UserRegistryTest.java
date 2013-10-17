/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Account;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kristofferskjutar
 */
public class UserRegistryTest {
    
    public UserRegistryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class UserRegistry.
     */
    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        Account t = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        instance.add(t);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UserRegistry.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        instance.remove(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class UserRegistry.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Account t = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        Account expResult = null;
        Account result = instance.update(t);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class UserRegistry.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        Account expResult = null;
        Account result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRange method, of class UserRegistry.
     */
    @Test
    public void testGetRange() throws Exception {
        System.out.println("getRange");
        int first = 0;
        int nItems = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        List expResult = null;
        List result = instance.getRange(first, nItems);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class UserRegistry.
     */
    @Test
    public void testGetCount() throws Exception {
        System.out.println("getCount");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postContruct method, of class UserRegistry.
     */
    @Test
    public void testPostContruct() throws Exception {
        System.out.println("postContruct");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        instance.postContruct();
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByName method, of class UserRegistry.
     */
    @Test
    public void testGetByName() throws Exception {
        System.out.println("getByName");
        String name = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        List expResult = null;
        List result = instance.getByName(name);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccount method, of class UserRegistry.
     */
    @Test
    public void testGetAccount() throws Exception {
        System.out.println("getAccount");
        String username = "";
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserRegistry instance = (UserRegistry)container.getContext().lookup("java:global/classes/UserRegistry");
        Account expResult = null;
        Account result = instance.getAccount(username, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}