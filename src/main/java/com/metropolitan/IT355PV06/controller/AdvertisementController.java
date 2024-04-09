package com.metropolitan.IT355PV06.controller;

import com.metropolitan.IT355PV06.entity.Advertisement;
import com.metropolitan.IT355PV06.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Controller
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;


    public List<Long> korpa = new ArrayList<Long>();







    @GetMapping("/")
    public String index(Model model) {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        model.addAttribute("advertisements", advertisements);
        return "index";
    }

    @GetMapping("/korpa")
    public String korpa(Model model) {
        List<Advertisement> punadvertisements = advertisementRepository.findAll();
        List<Advertisement> advertisements = new ArrayList<>();
        int total=0;
        for (Advertisement ad : punadvertisements)
        {
            for (Long korpaid : korpa)  {
                if (ad.getId() == korpaid) {
                    advertisements.add(ad);
                    String str = ad.getPrice();
                    str = str.replaceAll("\\D", "");
                    int broj = parseInt(str);
                    total = total + broj;
                }

            }
        }
        model.addAttribute("advertisements",  advertisements);
        model.addAttribute("total", total);
        return "korpa";
    }

    @GetMapping("/dodaj/{id}")
    public String dodaj (@PathVariable Long id) {

          korpa.add(id);



        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@Validated Advertisement advertisement, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        advertisementRepository.save(advertisement);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Advertisement> advertisement = advertisementRepository.findById(id);
        if (advertisement.isPresent()) {
            model.addAttribute("advertisement", advertisement.get());
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        advertisementRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/checkout")
    public String checkout() {

        return "checkout";
    }
}

