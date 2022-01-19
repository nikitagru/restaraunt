package nikitagru.restaraunt.services;

import nikitagru.restaraunt.entities.Menu;
import nikitagru.restaraunt.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private MenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }
}
