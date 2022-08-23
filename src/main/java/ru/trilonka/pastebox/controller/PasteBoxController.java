package ru.trilonka.pastebox.controller;

import org.springframework.web.bind.annotation.*;
import ru.trilonka.pastebox.api.request.PasteBoxRequest;

import java.util.Collections;
import java.util.List;

@RestController
public class PasteBoxController {

    @GetMapping("/")
    public List<String> getPublicPasteList() {
        return Collections.emptyList();
    }

    @GetMapping("/{hash}")
    public String getByHash(@PathVariable("hash") String hash) {
        return hash;
    }

    @PostMapping("/")
    public String add(@RequestBody PasteBoxRequest request) {
        return request.getData();
    }
}

