package hr.carparts.store.carpartsstore.controller.mvc;

import hr.carparts.store.carpartsstore.model.CarPartCategoryEnum;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;
import hr.carparts.store.carpartsstore.publisher.CustomSpringEventPublisher;
import hr.carparts.store.carpartsstore.service.CarPartsStoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/mvc")
@AllArgsConstructor
@SessionAttributes({"carParts", "carPartsSearchForm"})
public class CarPartsStoreSearchController {

    private CarPartsStoreService carPartsStoreService;

    private CustomSpringEventPublisher publisher;

    @GetMapping("/carParts")
    public String filterCarParts(Model model, HttpServletRequest request) {

        request.getSession(true);
        publisher.publishCustomEvent("Filtering started!");

        model.addAttribute("carPartCategoryList", CarPartCategoryEnum.values());
        if(!model.containsAttribute("carPartsSearchForm")) {
            model.addAttribute("carPartsSearchForm", new CarPartsSearchForm());
        }
        if(!model.containsAttribute("carParts")) {
            model.addAttribute("carParts", carPartsStoreService.findAll());
        }
        return "carParts";
    }

    @PostMapping("/carParts")
    public String showFilteredCarParts(Model model, CarPartsSearchForm carPartsSearchForm) {
        model.addAttribute("carParts", carPartsStoreService.filterByCriteria(carPartsSearchForm));
        model.addAttribute("carPartsSearchForm", carPartsSearchForm);
        return "redirect:/mvc/carParts";
    }

}
