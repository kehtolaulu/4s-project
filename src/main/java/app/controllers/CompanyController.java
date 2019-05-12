package app.controllers;

import app.entities.Account;
import app.entities.Company;
import app.entities.Profile;
import app.repositories.CompanyRepository;
import app.repositories.ProfileRepository;
import app.services.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(path = "/company")
public class CompanyController {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProfileByIdPage(@PathVariable Long id,
                                     ModelMap modelMap) {
        final Optional<Company> profile = companyRepository.findById(id);
        modelMap.put("company", profile.get());
        return "company";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProfilePage(@AuthenticationPrincipal AccountDetails account, ModelMap modelMap) {
        final Account user = account.getUser();
        final Optional<Company> profile = companyRepository.findById(user.getId());
        modelMap.put("company", profile.get());
        return "company";
    }

}
