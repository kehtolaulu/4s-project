package app.controllers;

import app.entities.Account;
import app.entities.Company;
import app.entities.Profile;
import app.exceptions.AccountNotFoundException;
import app.services.AccountDetails;
import app.services.AccountService;
import app.services.CompanyService;
import app.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/company")
public class CompanyController {
    private final CompanyService companyService;
    private final AccountService accountService;
    private final ProfileService profileService;

    @Autowired
    public CompanyController(CompanyService companyService, AccountService accountService, ProfileService profileService) {
        this.companyService = companyService;
        this.accountService = accountService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProfileByIdPage(@PathVariable Long id,
                                     ModelMap modelMap,
                                     @AuthenticationPrincipal AccountDetails account) {
        final Account profile = accountService.accountById(id).orElseThrow(AccountNotFoundException::new);
        final Company company = companyService.companyById(id).orElseThrow(AccountNotFoundException::new);
        modelMap.put("profile", profile);
        modelMap.put("company", company);
        modelMap.put("user", account.getUser());
        return "company";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProfilePage(@AuthenticationPrincipal AccountDetails account, ModelMap modelMap) {
        final Account user = account.getUser();
        final Company profile = companyService.companyById(user.getId()).orElseThrow(AccountNotFoundException::new);
        modelMap.put("company", profile);
        modelMap.put("profile", profile);
        modelMap.put("user", user);
        return "company";
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.POST)
    public String editCompany(@AuthenticationPrincipal AccountDetails account,
                              String about,
                              @PathVariable long id) {
        final Company company = companyService.companyById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        assert company.getId() == id;
        company.setDescription(about);
        companyService.save(company);
        return "redirect:/company";
    }

    @RequestMapping(path = "/{id}/interest", method = RequestMethod.POST)
    public String addCompanyToInterests(@PathVariable Long id, @AuthenticationPrincipal AccountDetails account) {
        final Company company = companyService.companyById(id).orElseThrow(AccountNotFoundException::new);
        final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        profileService.add(company).to(profile);
        return "redirect:/company/{id}";
    }

    @RequestMapping(path = "/{id}/interest", method = RequestMethod.DELETE)
    public String deleteCompanyFromInterests(@PathVariable Long id, @AuthenticationPrincipal AccountDetails account) {
        final Company company = companyService.companyById(id).orElseThrow(AccountNotFoundException::new);
        final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        profileService.delete(company).to(profile);
        return "redirect:/company/{id}";
    }

}
