package hr.carparts.store.carpartsstore.controller.mvc;

import hr.carparts.store.carpartsstore.model.CarPartCategoryEnum;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;
import hr.carparts.store.carpartsstore.publisher.CustomSpringEventPublisher;
import hr.carparts.store.carpartsstore.service.CarPartsStoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/mvc")
@AllArgsConstructor
@SessionAttributes({"carParts", "carPartsSearchForm"})
public class CarPartsStoreSearchController {

    private CarPartsStoreService carPartsStoreService;

    private CustomSpringEventPublisher publisher;
    private static final String CAR_PART_SEARCH_FORM_STRING = "carPartsSearchForm";
    private static final String CAR_PARTS_STRING = "carParts";

    @Secured("ROLE_USER")
    @GetMapping("/carParts")
    public String filterCarParts(Model model, HttpServletRequest request) {

        request.getSession(true);
        publisher.publishCustomEvent("Filtering started!");

        model.addAttribute("carPartCategoryList", CarPartCategoryEnum.values());
        if(!model.containsAttribute(CAR_PART_SEARCH_FORM_STRING)) {
            model.addAttribute(CAR_PART_SEARCH_FORM_STRING, new CarPartsSearchForm());
        }
        if(!model.containsAttribute(CAR_PARTS_STRING)) {
            model.addAttribute(CAR_PARTS_STRING, carPartsStoreService.findAll());
        }
        return CAR_PARTS_STRING;
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/carParts")
    public String showFilteredCarParts(Model model, CarPartsSearchForm carPartsSearchForm, SessionStatus status) {
        model.addAttribute(CAR_PARTS_STRING, carPartsStoreService.filterByCriteria(carPartsSearchForm));
        model.addAttribute(CAR_PART_SEARCH_FORM_STRING, carPartsSearchForm);
        status.setComplete();
        return "redirect:/mvc/carParts";
    }

}
