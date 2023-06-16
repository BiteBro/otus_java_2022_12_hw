package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;
import ru.otus.services.DBServiceClient;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppControllers {

    private final DBServiceClient repo;

    public AppControllers(DBServiceClient repo) {
        this.repo = repo;
    }

    @GetMapping({"/"})
    public String indexView() {
        return "index";
    }

    @GetMapping({"/clients"})
    public String clientsListView(Model model) {
        List<Client> clients = repo.getClients();
        model.addAttribute("clientsList", clients);
        Client client = new Client();
        client.setNumbers(Arrays.asList(new Phone(), new Phone()));
        client.setAddress(new Address());
        model.addAttribute("client", client);
        return "clients";
    }

    @PostMapping({"/client/save"})
    public RedirectView clientSave(@ModelAttribute Client client) {
        repo.saveClient(client);
        return new RedirectView("/clients", true);
    }
}
