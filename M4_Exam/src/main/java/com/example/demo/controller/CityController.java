package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.city.ICityService;
import com.example.demo.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    ICityService cityService;
    @Autowired
    ICountryService countryService;

    @ModelAttribute("allCountries")
    public Iterable<Country> getAllCountry() {
        return countryService.findAll();
    }

    @GetMapping
    public ModelAndView listCity() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("view/{id}")
    public ModelAndView viewDetail(@PathVariable("id") Long cityId){
        City city = cityService.findById(cityId).get();
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("city",city);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView create(@Valid @ModelAttribute("city") City city, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("error","Create fail");
            return modelAndView;
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("createMessage", "New city created successfully");
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateCategory(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("error","Edit fail");
            return modelAndView;
        }

        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("createMessage", "Edit city successful!");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        City city = cityService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("delete")
    public ModelAndView delete(@ModelAttribute("city") City city){
        cityService.remove(city.getCityId());
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("createMessage", "Delete city successful!");
        return modelAndView;
    }

}
