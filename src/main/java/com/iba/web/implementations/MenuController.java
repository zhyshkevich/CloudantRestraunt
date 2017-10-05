package com.iba.web.implementations;

import com.iba.models.MenuModel;
import com.iba.services.implementations.MenuServiceImpl;
import com.iba.services.interfaces.BaseService;
import com.iba.web.abstracts.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController extends CrudController<MenuModel> {

    private MenuServiceImpl menuService;

    @Autowired
    public MenuController(MenuServiceImpl menuService)
    {
        this.menuService = menuService;
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<String> saveMenu(@RequestBody MenuModel menuModel)
//    {
//        return save(menuModel);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity updateMenu(@RequestBody MenuModel menuModel, @PathVariable String id)
//    {
//        return update(menuModel);
//    }

    @Override
    public BaseService<MenuModel> getService() {
        return menuService;
    }
}