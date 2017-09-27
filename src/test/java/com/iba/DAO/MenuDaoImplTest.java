package com.iba.DAO;


import com.iba.DAO.dao.implementations.MenuDaoImpl;
import com.iba.Models.MenuModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("/test-context.xml")
@WebAppConfiguration
public class MenuDaoImplTest {


    @Autowired
    MenuDaoImpl MenuDao;

    MenuModel MenuActual;
    MenuModel MenuExpected;

    @Before
    public void init()
    {
        MenuActual = new MenuModel("test_menu");
        MenuExpected = new MenuModel();
    }

    @After
    public void destroy()
    {
        MenuActual = null;
        MenuExpected = null;
    }


    @Test
    public void testSave()
    {
        Boolean saved = MenuDao.save(MenuActual);
        Assert.assertTrue("MenuSave() failed", saved);
    }

    @Test
    public void testGetAll()
    {
        List<MenuModel> allDocs = MenuDao.getAll();
        Assert.assertTrue("MenuGetAll() failed", allDocs.size() >= 2);
    }

    @Test
    public void testGetById()
    {
        List<MenuModel> allDocs = MenuDao.getAll();
        MenuActual = allDocs.get(0);
        MenuExpected = MenuDao.getById(MenuActual.get_id());
        Assert.assertEquals("MenuGetById() failed", MenuActual, MenuExpected);
    }

    @Test
    public void testUpdate()
    {
        MenuActual = MenuDao.getById("test_update");
        MenuActual.setMenu_name("testing2");
        MenuDao.update(MenuActual);
        MenuExpected = MenuDao.getById("test_update");

        Assert.assertEquals("MenuUpdate() failed", MenuActual.getMenu_name(), MenuExpected.getMenu_name());
    }

    @Test
    public void testDelete()
    {
        List<MenuModel> allDocs = MenuDao.getAll();
        List<String> idS = new ArrayList<>();

        for (MenuModel model: allDocs)
        {
            idS.add(model.get_id());
        }

        if (idS.contains("test_delete"))
        {
            MenuActual = MenuDao.getById("test_delete");
            MenuDao.delete(MenuActual);
        }
        else
        {
            MenuActual.set_id("test_delete");
            MenuDao.save(MenuActual);
        }

        Boolean deleted;

        List<MenuModel> allDocsAfterDelete = MenuDao.getAll();

        if (!allDocsAfterDelete.contains(MenuActual)) deleted = true;
        else deleted = false;

        Assert.assertTrue("MenuDelete() failed", deleted);
    }


}