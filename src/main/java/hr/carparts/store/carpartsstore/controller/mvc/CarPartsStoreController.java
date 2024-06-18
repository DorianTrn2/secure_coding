package hr.carparts.store.carpartsstore.controller.mvc;

import hr.carparts.store.carpartsstore.dto.CarPartDTO;
import hr.carparts.store.carpartsstore.model.CarPartCategoryEnum;
import hr.carparts.store.carpartsstore.service.CarPartsStoreService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
@AllArgsConstructor
public class CarPartsStoreController {

    private CarPartsStoreService carPartsStoreService;

    @Secured("ROLE_USER")
    @GetMapping("/carPartsStore")
    public String getCarParts(Model model) {
        model.addAttribute("carPartCategoryList", CarPartCategoryEnum.values());
        model.addAttribute("carPartDTO", new CarPartDTO());
        return "carPartsStore";
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/carPartsStore")
    public String saveNewCarPart(@ModelAttribute CarPartDTO carPartDTO, Model model) {
        carPartsStoreService.save(carPartDTO);
        return "redirect:carPartsStore";
    }

}
